package com.zhjy.cultural.services.patrol.ui.home.map.search;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.databinding.MapSearchListItemBinding;
import com.zhjy.cultural.services.patrol.ui.home.map.OnStreetListInteractionListener;

/**
 * Created by jialg on 2018/1/30.
 */

public class MapSearchItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    MapSearchListItemBinding binding;

    public OnStreetListInteractionListener mListener ;

    MapSearchItem(View itemView, OnStreetListInteractionListener mListener ) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.mListener = mListener;
        itemView.setOnClickListener(this);
    }

    void bind(TreasuresBean bean) {
        itemView.setTag(bean);
        binding.setMTreasuresBean(bean);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        mListener.OnStreetListInteractionListener((TreasuresBean)v.getTag());
    }
}
