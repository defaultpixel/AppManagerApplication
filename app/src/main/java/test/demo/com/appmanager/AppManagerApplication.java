package test.demo.com.appmanager;

import android.app.Application;
import android.content.Intent;
import android.os.Environment;

import org.wlf.filedownloader.BuildConfig;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.FileDownloader;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import file.downloader.FileDownloadService;
import test.demo.com.appmanager.AppInfo;

/**
 * Created by Administrator on 2017/9/28.
 */

public class AppManagerApplication extends Application {

    public static List<AppInfo> testData(){
        List<AppInfo> list = new ArrayList<>();
        list.add(new AppInfo(true, "null", true));
        list.add(new AppInfo(true, "发掘新游戏", true));
        list.add(new AppInfo(false, "null", false, "率土之滨", "http://m.ssu8.cn/#/gameinfo?forumId=42e553b0a02f4ea8a5eac9c15f4b00be"));
        list.add(new AppInfo(false, "null", false, "大话西游", "http://m.ssu8.cn/#/gameinfo?forumId=d73cd4821cfd468f95d3d395e37dba2e"));
        list.add(new AppInfo(false, "null", false, "阴阳师", "http://m.ssu8.cn/#/gameinfo?forumId=a8ab7e85cbb141fb8ddc2a7f47920a7d"));
        list.add(new AppInfo(false, "null", false, "坦克世界", "http://m.ssu8.cn/#/gameinfo?forumId=7e0b2f7de30748708dfde13e9604e975"));
        list.add(new AppInfo(true, "已安装的游戏", true));
        /*list.add(new MySection(true, "小编推荐", true));
        list.add(new MySection(false, "App1", true));
        list.add(new MySection(true, "Section 2", true));
        list.add(new MySection(false, "App1", true));*/
        return list;
    }

/*    @Override
    public void onCreate(){
        super.onCreate();
        //初始化initfiledownloader
        initfiledownloader();
        //启动downloadservice
        Intent downloadintent = new Intent(this, FileDownloadService.class);
        startService(downloadintent);
    }

    public void initfiledownloader(){
        // 1、创建Builder
        FileDownloadConfiguration.Builder builder = new FileDownloadConfiguration.Builder(this);
        // 2.配置Builder
        // 配置下载文件保存的文件夹
        builder.configFileDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "FileDownloader");
        // 配置同时下载任务数量，如果不配置默认为2
        builder.configDownloadTaskSize(10);
        // 配置失败时尝试重试的次数，如果不配置默认为0不尝试
        builder.configRetryDownloadTimes(5);
        // 开启调试模式，方便查看日志等调试相关，如果不配置默认不开启
        builder.configDebugMode(true);
        // 配置连接网络超时时间，如果不配置默认为15秒
        builder.configConnectTimeout(25000);// 25秒
        // 3、使用配置文件初始化FileDownloader
        FileDownloadConfiguration configuration = builder.build();
        FileDownloader.init(configuration);
    }

    public void releaseFileDownloader(){
        org.wlf.filedownloader.FileDownloader.release();
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
