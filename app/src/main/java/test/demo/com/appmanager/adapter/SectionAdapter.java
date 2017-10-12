package test.demo.com.appmanager.adapter;

import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import test.demo.com.appmanager.AppInfo;
import test.demo.com.appmanager.MainActivity;
import test.demo.com.appmanager.R;
import test.demo.com.appmanager.entity.MySection;
import test.demo.com.appmanager.entity.Video;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<AppInfo, BaseViewHolder> {
    private MainActivity mainActivity;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final AppInfo item) {
        if(item.header.equals("已安装的游戏")||item.header.equals("发掘新游戏")) {
            Log.e("ConvertedHead", item.header);
            if(item.header.equals("已安装的游戏")){
                helper.setText(R.id.more, "长按分享");
            }
            if(item.header.equals("发掘新游戏")){
                helper.setText(R.id.more, "更多");
            }
            helper.setText(R.id.header, item.header);
            helper.setVisible(R.id.more, item.getisMore());
            helper.addOnClickListener(R.id.more);
        }else{
            Log.e("ConvertedHead", item.header);
            helper.setVisible(R.id.acc_imgf, false);
            helper.setVisible(R.id.acc_img, true);
            helper.setText(R.id.appmanager_phone, android.os.Build.MODEL);
            helper.setText(R.id.appmanager_number, getCpuInfo());
            helper.setText(R.id.appmanager_rom, getTotalMemory());
            helper.addOnClickListener(R.id.btn_back);
            helper.addOnClickListener(R.id.appmanager_download);
            helper.addOnClickListener(R.id.appmanager_profile);
        }
    }


    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        //Video video = (Video) item.t;
        /*switch (helper.getLayoutPosition() %
                2) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.m_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.m_img2);
                break;

        }
        helper.setText(R.id.tv, item.header);*/
        helper.setText(R.id.tv, item.getAppName());
        if(item.header.equals("null")){
            if(item.getAppName().equals("率土之滨")){
                helper.setImageResource(R.id.iv, R.drawable.a2);
            }
            if(item.getAppName().equals("大话西游")){
                helper.setImageResource(R.id.iv, R.drawable.a3);
            }
            if(item.getAppName().equals("阴阳师")){
                helper.setImageResource(R.id.iv, R.drawable.a5);
            }
            if(item.getAppName().equals("坦克世界")){
                helper.setImageResource(R.id.iv, R.drawable.a6);
            }
        }else {
            helper.setImageDrawable(R.id.iv, item.getIcon());
            //helper.setImageResource(R.id.iv, R.mipmap.m_img2);
        }
    }


    public String getCpuInfo()
    {
        String CpuInfo = "unknow";
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = { "", "" };
        String[] arrayOfString;
        try
        {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++)
            {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        }
        catch (IOException e)
        {
        }
        //tvHardwareInfo.append("CPU型号 " + cpuInfo[0] + "\n" + "CPU频率: " + cpuInfo[1] + "\n");
        CpuInfo = "CPU：" + cpuInfo[0];
        return CpuInfo;
    }

    private String getTotalMemory()
    {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try
        {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
/*            for (String num : arrayOfString)
            {
                Log.i(str2, num + "\t");
            }*/
            //initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() / 1024;
            localBufferedReader.close();
        }
        catch (IOException e)
        {
        }
        String Memory = "运行内存：" + initial_memory + "MB";
        //return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
        return Memory;
    }
}
