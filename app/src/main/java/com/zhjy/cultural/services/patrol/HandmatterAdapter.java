package com.zhjy.cultural.services.patrol;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.bean.HandleMatter;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;

import java.util.List;

/**
 * 巡检代办
 */
public class HandmatterAdapter extends BaseQuickAdapter<HandleMatter, BaseViewHolder> {
    public HandmatterAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HandleMatter item) {
        String picturePath = item.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Picasso.with(mContext).load(Constants.BASE_URL_UP_IMG + item.getWw().getPicturePath()).into((ImageView) helper.getView(R.id.item_handlee_matter_iv));
//                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + bean.getPicturePath());
            } else {
                Picasso.with(mContext).load(Constants.BASEIMGURL + item.getWw().getPicturePath()).into((ImageView) helper.getView(R.id.item_handlee_matter_iv));

            }
        }


        helper.setText(R.id.item_handle_matter_tv_name, item.getWw().getTitle());
        helper.setText(R.id.item_handle_matter_tv_time, item.getWriteTime());

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
