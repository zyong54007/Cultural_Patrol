package com.zhjy.cultural.services.patrol.ui.home.map;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainMapStreetItemBinding;

/**
 * Created by jialg on 2018/1/29.
 */

public class StreetNameItem extends RecyclerView.ViewHolder implements View.OnClickListener{
    FragmentMainMapStreetItemBinding  binding;

    private OnStreetListInteractionListener mStreetListener ;

    StreetNameItem(View itemView,OnStreetListInteractionListener mStreetListener ) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.mStreetListener = mStreetListener;
        itemView.setOnClickListener(this);
    }

    void bind(TreasuresBean bean) {

        itemView.setTag(bean);
        binding.setNameInfo(bean.getTitle());
        binding.setNumber(Integer.valueOf(bean.getId()));
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        mStreetListener.OnStreetListInteractionListener((TreasuresBean)v.getTag());
    }
}
