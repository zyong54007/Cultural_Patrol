package com.zhjy.cultural.services.patrol.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.bean.SearchTreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;

import java.util.List;

public class SearchTreasuresAdapter extends BaseQuickAdapter<SearchTreasuresEntity.Datas, BaseViewHolder> {
    public SearchTreasuresAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchTreasuresEntity.Datas item) {
        helper.setText(R.id.item_search_treasures_tv_title, item.getTitle());
        helper.setText(R.id.item_search_treasures_tv_leave, "保护等级:" + item.getWwStatusStr());
        helper.setText(R.id.item_search_treasures_tv_type, "风险类别:" + item.getFxTypeStr());
        helper.setText(R.id.item_search_treasures_tv_address, item.getAddress());
        String picturePath = item.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.item_search_treasures_iv));
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.item_search_treasures_iv));
            }
        }


    }
}
