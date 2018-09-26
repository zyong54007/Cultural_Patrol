package com.zhjy.cultural.services.patrol.adapter;

import android.os.Environment;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.network.NoticeDetails;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NoticeDetailsAdapter extends BaseQuickAdapter<NoticeDetails.Documents, BaseViewHolder> {
    public NoticeDetailsAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeDetails.Documents documents) {
        helper.setText(R.id.enclosure_name, documents.getFileName());
        helper.addOnClickListener(R.id.enclosure_download);
        helper.addOnClickListener(R.id.item_notice_details_open);
//        try {

        if (documents.isFlag()) {
            helper.setVisible(R.id.enclosure_download, false);
            helper.setVisible(R.id.item_notice_details_open, true);
            helper.setText(R.id.item_noticedetails_catalog, "文件目录: " + "Download");

        } else {
            helper.setVisible(R.id.enclosure_download, true);
            helper.setVisible(R.id.item_notice_details_open, false);
        }

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        helper.addOnClickListener(R.id.item_child);
//        ImageView view = helper.getView(R.id.iv_item);
//        int layoutPosition = helper.getLayoutPosition();
        //        helper.setText(R.id.text, item.getTitle());
//        helper.setImageResource(R.id.icon, item.getImageResource());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
//        helper.setText(R.id.item_tv, item.getMsg());
    }

    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }
}
