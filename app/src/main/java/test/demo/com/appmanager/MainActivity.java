package test.demo.com.appmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import test.demo.com.appmanager.Dialog.ZxingDialog;
import test.demo.com.appmanager.adapter.SectionAdapter;
import test.demo.com.appmanager.applicationsearch.AppSearcher;
import test.demo.com.appmanager.entity.MySection;

import static java.lang.System.in;
import static java.lang.Thread.sleep;
import static test.demo.com.appmanager.applicationsearch.AppSearcher.appInfoList;
import static test.demo.com.appmanager.applicationsearch.AppSearcher.flag;
import static test.demo.com.appmanager.applicationsearch.AppSearcher.searchlist;

public class MainActivity extends AppCompatActivity {

    private String testtxt;
    private RecyclerView mRecyclerView;
    private List<AppInfo> mData;

    /*
     进度条测试
     */
    private ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        /*
         进度条测试
         */
        /*progressBar.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgressStatus < 100){
                    //mProgressStatus = dowork();
                    mProgressStatus = 90;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();*/

        AppSearcher appSearcher = new AppSearcher();
        appSearcher.initUI(this);

       AppManagerApplication appManagerApplication = (AppManagerApplication) getApplicationContext();

        // setBackBtn();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mData = appManagerApplication.testData();

        //获取assests中test.txt
        try{
            InputStream is = getAssets().open("test.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                sBuilder.append(line);
            }
            /*int size = is.available();
            byte[] buffer = new byte[size];
            in.read();
            in.close();
            testtxt = new String(buffer,"unicode");*/
            testtxt = sBuilder.toString();
            Log.e("test.txt", testtxt);
        }catch (Exception e){
            Log.e("test.txt", testtxt);
            e.printStackTrace();
        }



        try{
        sleep(5000);}
        catch (Exception e){

        }
        Toast.makeText(this, testtxt, Toast.LENGTH_LONG).show();


            if(searchlist!=null) {
                for (int i = 0; i < searchlist.size(); i++) {
                    mData.add(new AppInfo(false, "App1", false, searchlist.get(i).getAppName(), searchlist.get(i).getIcon(), searchlist.get(i).getPackageName()));
                }
            }

            SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData);
            sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AppInfo appInfo = mData.get(position);
                    if (appInfo.isHeader) {
                        Toast.makeText(MainActivity.this, "Head" + position, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Body" + position + "", Toast.LENGTH_SHORT).show();
                        String mPackageName = appInfo.getPackageName();
                        String mAppName = appInfo.getAppName();
                        showNormalDialog(mAppName, mPackageName);
                        /*Intent intent = MainActivity.this.getPackageManager().getLaunchIntentForPackage(mPackageName);
                        MainActivity.this.startActivity(intent);*/
                    }
                }
            });

            sectionAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    AppInfo appInfo = mData.get(position);
                    if (appInfo.isHeader) {
                        Toast.makeText(MainActivity.this, "Head" + position +"Long", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Body" + position + appInfo.getAppName(), Toast.LENGTH_SHORT).show();
                        Drawable a = null;
                        if(position >=6 ) {
                            new ZxingDialog(MainActivity.this, appInfo.getAppName(), appInfo.getIcon(), a, new ZxingDialog.OnCloseListener(){
                                @Override
                                public void onClick(){

                                }
                            }).setTitle("!")
                            .show();
                        }
                        /*String mPackageName = appInfo.getPackageName();
                        String mAppName = appInfo.getAppName();
                        showNormalDialog(mAppName, mPackageName);*/
                    }
                    return true;
                }
            });

            sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch(view.getId()){
                        case R.id.appmanager_download:
                            Toast.makeText(MainActivity.this, "appmanager_download" + position, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.appmanager_profile:
                            Toast.makeText(MainActivity.this, "appmanager_profile" + position, Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.btn_back:
                            Toast.makeText(MainActivity.this, "btn_back" + position, Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "onChildClick" + position, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            mRecyclerView.setAdapter(sectionAdapter);
        }

    private void showNormalDialog(String appName, final String packagename){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setIcon(R.drawable.dialog_1);
        normalDialog.setTitle("危险！");
        normalDialog.setMessage(" "+ appName + " " + "不是官方应用，很可能被植入了恶意程序和广告！");
        normalDialog.setPositiveButton("立即替换",
                new DialogInterface.OnClickListener(){
                 @Override
                  public void onClick(DialogInterface dialog, int which){
                     //To do
                 }
              });
        normalDialog.setNegativeButton("继续运行",
                new DialogInterface.OnClickListener(){
                   @Override
                    public void onClick(DialogInterface dialog, int which){
                       //To do
                       Intent intent = MainActivity.this.getPackageManager().getLaunchIntentForPackage(packagename);
                       MainActivity.this.startActivity(intent);
                   }
                });
        normalDialog.show();
    }

}

