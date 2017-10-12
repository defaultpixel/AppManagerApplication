package test.demo.com.appmanager.applicationsearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import test.demo.com.appmanager.AppInfo;


/**
 * Created by Administrator on 2017/9/13.
 */

public class AppSearcher {

    private static final int GET_USER_APP_FINISH = 1;
    private AppInfoProvider provider;
    public static List<AppInfo> searchlist;
    public static boolean flag = false;
    public static List<AppInfo> appInfoList;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage( Message msg ) {
            switch(msg.what){
                case GET_USER_APP_FINISH :
                    flag = true;
                    appInfoList = searchlist;
                    break;
                default:
                    break;
            }
        }
    };

    public void initUI(final Activity activity){
        flag = false;
        new Thread(){
            public void run(){
                provider = new AppInfoProvider(activity);
                searchlist = provider.getAllApps();
                Message msg = new Message();
                msg.what = GET_USER_APP_FINISH;
                handler.sendMessage(msg);
            }
        }.start();
    }

}
