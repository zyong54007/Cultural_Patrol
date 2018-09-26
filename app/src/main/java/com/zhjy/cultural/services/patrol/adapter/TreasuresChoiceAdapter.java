package com.zhjy.cultural.services.patrol.adapter;

import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;

import java.util.List;

public class TreasuresChoiceAdapter extends BaseQuickAdapter<TreasuresEntity.Datas, BaseViewHolder> {
    public TreasuresChoiceAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreasuresEntity.Datas documents) {

        helper.setText(R.id.item_treasures_tv_title, documents.getTitle());
        helper.setText(R.id.item_treasures_tv_type, "保护等级: " + documents.getWwTypeStr());
        helper.setText(R.id.item_treasures_tv_fx_type, "风险类别: " + documents.getFxTypeStr());

        String picturePath = documents.getPicturePath();

        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.item_treasures_iv));
//                binding.setImageUrl(Constants.BASE_URL_UP_IMG + bean.getWw().getPicturePath());
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.item_treasures_iv));
//                binding.setImageUrl(Constants.BASEIMGURL + bean.getWw().getPicturePath());
            }
        }


//        helper.setText(R.id.enclosure_name, documents.getFileName());
//        helper.addOnClickListener(R.id.enclosure_download);
//        helper.addOnClickListener(R.id.item_notice_details_open);
////        try {
//
//        if (documents.isFlag()) {
//            helper.setVisible(R.id.enclosure_download, false);
//            helper.setVisible(R.id.item_notice_details_open, true);
//            helper.setText(R.id.item_noticedetails_catalog, "文件目录: " + "Download");
//
//        } else {
//            helper.setVisible(R.id.enclosure_download, true);
//            helper.setVisible(R.id.item_notice_details_open, false);
//        }

    }

//
}
