package com.zhjy.cultural.services.patrol.ui.home.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureListBean;
import com.zhjy.cultural.services.patrol.databinding.MainItemListItemBinding;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;

/**
 * MainListTypeItem
 */
class MainListTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    MainItemListItemBinding binding;

    MainListTypeItem(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(CultureListBean bean) {

        itemView.setTag(bean);
        binding.setTitleInfo(bean.getTitle());
        binding.setImageUrlPath(bean.getImageThumb());

        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),TreasuresActivity.class);
        v.getContext().startActivity(intent);
    }
}