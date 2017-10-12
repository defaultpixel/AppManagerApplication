package test.demo.com.appmanager;

import android.graphics.drawable.Drawable;
import android.text.BoringLayout;

import com.chad.library.adapter.base.entity.SectionEntity;

import test.demo.com.appmanager.entity.Video;


/**
 * Created by Administrator on 2017/9/20.
 */

public class AppInfo extends SectionEntity<Video> {
    private boolean isMore;
    private Drawable icon;
    private String appName;
    private String packageName;

    public AppInfo(boolean isHeader, String header, boolean isMore){
        super(isHeader, header);
        this.isMore = isMore;
    }
    public AppInfo(boolean isHeader, String header, boolean isMore, String appName, Drawable icon, String packageName){
        super(isHeader, header);
        this.isMore = isMore;
        this.appName = appName;
        this.icon = icon;
        this.packageName = packageName;
    }

    public AppInfo(boolean isHeader, String header, boolean isMore, String appName, String packageName){
        super(isHeader, header);
        this.isMore = isMore;
        this.appName = appName;
        this.packageName = packageName;
    }

    public AppInfo(Video t){ super(t); }
    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public boolean getisMore(){ return isMore; }
    public void setisMore(boolean isMore){
        this.isMore = isMore;
    }
}
