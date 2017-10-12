package test.demo.com.appmanager.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.wlf.filedownloader.file_download.http_downloader.Download;

import java.util.List;

import test.demo.com.appmanager.AppInfo;

/**
 * Created by Administrator on 2017/10/12.
 */

public class DownloadAdapter extends BaseSectionQuickAdapter<AppInfo, BaseViewHolder> {

    public DownloadAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AppInfo item) {

    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {

    }
}
