package test.demo.com.appmanager.applicationsearch;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import test.demo.com.appmanager.AppInfo;

public class AppInfoProvider {
    private String[] gamename = {"倩女幽魂","梦幻西游","率土之滨","光明大陆","镇魔曲","迷雾世界",
                   "阴阳师","魔法禁书目录","大话西游手游","荆棘王座","大唐无双","勇士x勇士","我守护的一切",
                   "无尽战区觉醒","天下手游","劲舞团","坦克连","三少爷的剑","飞刀又见飞刀","钢铁黎明",
                   "秘宝猎人","天启联盟","元气战姬学院"};

    private PackageManager packageManager;
    //获取一个包管理器
    public AppInfoProvider(Context context){
        packageManager = context.getPackageManager();

    }

    public List<AppInfo> getAllApps(){
        List<String> gamenamelist = Arrays.asList(gamename);
        List<AppInfo> list = new ArrayList<AppInfo>();
        AppInfo myAppInfo;
        //获取到所有安装了的应用程序的信息，包括那些卸载了的，但没有清除数据的应用程序
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for(PackageInfo info:packageInfos){
            //拿到应用程序的信息
            ApplicationInfo appInfo = info.applicationInfo;
            //拿到应用程序的程序名
            String appName = appInfo.loadLabel(packageManager).toString();
            //if(gamenamelist.contains(appName)) {
                //拿到应用程序的图标
                Drawable icon = appInfo.loadIcon(packageManager);
                //拿到包名
                String packageName = info.packageName;
                //拿到应用程序的大小
                //long codesize = packageStats.codeSize;
                //Log.i("info", "-->"+codesize);
                myAppInfo = new AppInfo(false, "app", false, appName, icon, packageName);
                String one = info.sharedUserId;
                if(one != null){
                    Log.e("sharedUserId", one);
                }
                String two = info.versionName;
                if(two != null){
                    Log.e("versionName", two);
                }
                if (filterApp(appInfo)) {
                    /*myAppInfo.setSystemApp(false);
                    Log.e("AppName", appName);*/
                    list.add(myAppInfo);
                } else {
                   /* myAppInfo.setSystemApp(true);*/
                }
                /*list.add(myAppInfo);*/
            //}
        }
        return list;
    }


    /**
     *判断某一个应用程序是不是系统的应用程序，
     *如果是返回true，否则返回false。
     */
    public boolean filterApp(ApplicationInfo info){
        //有些系统应用是可以更新的，如果用户自己下载了一个系统的应用来更新了原来的，它还是系统应用，这个就是判断这种情况的
        if((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){
            return true;
        }else if((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0){//判断是不是系统应用
            return true;
        }
        return false;
    }

    //getPackageSizeInfo是PackageManager中的一个private方法，所以需要通过反射机制来调用
/*    public static void getPkgSize(final Context context, String pkgName, final AppInfo appInfo){
        Method method;
        try{

        }
    }*/
}
