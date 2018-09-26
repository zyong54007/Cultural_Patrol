package com.zhjy.cultural.services.patrol.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.network.NoticeList;

import java.util.List;

public class InSpectionListAdapter extends BaseQuickAdapter<InSpecListEntity.Datas, BaseViewHolder> {
    public InSpectionListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InSpecListEntity.Datas item) {
        helper.setText(R.id.inspec_tv_title, item.getWw().getTitle());
        helper.setText(R.id.inspec_tv_time, item.getWriteTime());

        String picturePath = item.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.inspec_iv));
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.inspec_iv));
            }
        }
        int status = item.getStatus();
        if (status == 1) {
            helper.setText(R.id.inspec_tv_status, "巡检正常");
            helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.shape_type_green);
        } else {
            helper.setText(R.id.inspec_tv_status, "巡检异常");
            helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.inspection_list_text_dcl);
        }


//        String createTime = item.getNotice().getCreateTime();
//        helper.setText(R.id.item_notice_time, createTime);
//        helper.setText(R.id.message_title, item.getNotice().getTheme());
//        helper.setText(R.id.message_info, item.getNotice().getContent());
//        helper.addOnClickListener(R.id.item_child);
//        ImageView view = helper.getView(R.id.iv_item);
//        int layoutPosition = helper.getLayoutPosition();
        //        helper.setText(R.id.text, item.getTitle());
//        helper.setImageResource(R.id.icon, item.getImageResource());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
//        helper.setText(R.id.item_tv, item.getMsg());
    }
}
