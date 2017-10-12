package file.downloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener;

/**
 * Created by Administrator on 2017/9/29.
 */

public class FileDownloadService extends Service implements OnRetryableFileDownloadStatusListener {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // 将当前service注册为FileDownloader下载状态监听器
        FileDownloader.registerDownloadStatusListener(this);
        // 如果希望service启动就开始下载所有未完成的任务，则开启以下实现
        FileDownloader.continueAll(true);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 将当前service取消注册为FileDownloader下载状态监听器
        FileDownloader.unregisterDownloadStatusListener(this);
        // 如果希望service停止就停止所有下载任务，则开启以下实现
        FileDownloader.pauseAll();// 暂停所有下载任务
    }

    @Override
    public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long
            remainingTime) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
        // 发送通知或者广播
    }
    @Override
    public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, FileDownloadStatusFailReason failReason) {
        // 发送通知或者广播
    }
}
