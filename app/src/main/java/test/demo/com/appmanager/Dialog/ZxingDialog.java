package test.demo.com.appmanager.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import test.demo.com.appmanager.R;

public class ZxingDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private TextView appName;
    private ImageView appIcon, appZxing, appCancel;
    private String appname;
    private Drawable appicon, appzxing;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public ZxingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public ZxingDialog(Context context, int themeResId, String content) {
        super(context);
        this.mContext = context;
        this.content = content;
    }

    public ZxingDialog(Context context, String appName, Drawable appIcon, Drawable appZxing,OnCloseListener listener) {
        super(context);
        this.appname = appName;
        this.appicon = appIcon;
        this.appzxing = appZxing;
        this.listener = listener;
    }

    protected ZxingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public ZxingDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public ZxingDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public ZxingDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appmanager_game_share);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        appName = (TextView) findViewById(R.id.appmanager_share_appname_text);
        appIcon = (ImageView) findViewById(R.id.appmanager_share_icon);
        appZxing = (ImageView) findViewById(R.id.appmanager_share_zxing);
        appCancel = (ImageView) findViewById(R.id.appmanager_share_cancel);
        appCancel.setOnClickListener(this);

        if(1==1){
            appName.setText(appname);
        }
        if(appicon!=null){
            appIcon.setImageDrawable(appicon);
        }
        if(appzxing!=null){
            appZxing.setImageDrawable(appzxing);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appmanager_share_cancel:
                if(listener != null){
                    //listener.onClick(this, false);
                    listener.onClick();
                }
                this.dismiss();
                break;
        }
    }

    public interface OnCloseListener{
        //void onClick(Dialog dialog, boolean confirm);
        void onClick();
    }
}
