package file.downloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import test.demo.com.appmanager.R;

public class DownloadApkService extends Service {
    //    通知栏下载进度
    private Notification notification = null;
    private NotificationManager manager = null;
    private static final int NOTIFICATION_ID = 0x12;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (intent.hasExtra("url")) {
            String url = intent.getStringExtra("url");
            if (isEmpty(url)) {
                stopSelf();
            } else {
                initNotify();
                downLoadApk(url);
            }
        } else {
            stopSelf();
        }
    }


    /**
     * 初始化通知栏
     */
    private void initNotify() {
        notification = new Notification(R.mipmap.ic_launcher, "下载提醒", System.currentTimeMillis());
        notification.contentView = new RemoteViews(getApplication().getPackageName(), R.layout.layout_down_progress);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 下载
     *
     * @param url
     */
    private void downLoadApk(String url) {
        File root = Environment.getExternalStorageDirectory();
        String file  =root.getAbsolutePath()+File.separator + System.currentTimeMillis() + ".apk";
        final RequestParams params = new RequestParams(url);
        params.setSaveFilePath(file);
        params.setAutoResume(true);
        params.setCancelFast(true);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                manager.cancel(NOTIFICATION_ID);
                installApk(getApplication(), result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                manager.cancel(NOTIFICATION_ID);
                stopSelf();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                manager.cancel(NOTIFICATION_ID);
                stopSelf();
            }
            @Override
            public void onFinished() {
                stopSelf();
            }
            @Override
            public void onWaiting() {
            }
            @Override
            public void onStarted() {
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                float prog = ((float) current / (float) total) * 100;
                notification.contentView.setProgressBar(R.id.prg_bar, (int) total, (int) current, false);
                notification.contentView.setTextViewText(R.id.tv_prog, "下载进度   " + (int) prog + "%");
                manager.notify(NOTIFICATION_ID, notification);
            }
        });
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 判断是否为空字符
     *
     * @param str
     * @return
     */
    public boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.equals("null");
    }


    /**
     * 描述：打开并安装文件.
     *
     * @param context the context
     * @param file    apk文件路径
     */
    public void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
