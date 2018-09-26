package com.zhjy.cultural.services.patrol.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.SearchRoutingEntity;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;

import java.util.List;

public class RoutingSearchAdapter extends BaseQuickAdapter<SearchRoutingEntity.Datas, BaseViewHolder> {
    public RoutingSearchAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchRoutingEntity.Datas documents) {
        helper.setText(R.id.search_tv_title, documents.getWw().getTitle());
        helper.setText(R.id.search_tv_time, documents.getWriteTime());
        String picturePath = documents.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.search_routing_iv));
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.search_routing_iv));
            }
        }
        int status = documents.getStatus();
        if (status == 1) {
            helper.setText(R.id.search_tv_status, "巡检正常");
            helper.getView(R.id.search_tv_status).setBackgroundResource(R.drawable.shape_type_green);
        } else {
            helper.setText(R.id.search_tv_status, "巡检异常");
            helper.getView(R.id.search_tv_status).setBackgroundResource(R.drawable.inspection_list_text_dcl);
        }



    }
}