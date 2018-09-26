package com.zhjy.cultural.services.patrol.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.network.NoticeDetails;

import java.util.List;

public class PopWindowAdapter extends BaseQuickAdapter<TreasuresBean, BaseViewHolder> {
    public PopWindowAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreasuresBean documents) {
        helper.setText(R.id.map_search_tv_title, documents.getTitle());
        helper.setText(R.id.map_search_tv_leave, documents.getWwTypeStr());
        helper.setText(R.id.map_search_tv_address, documents.getAddress());
    }
}
