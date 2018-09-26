package com.zhjy.cultural.services.patrol.ui.home.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.databinding.MainItemBottomItemBinding;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListActivity;

/**
 * MainListTypeItem
 */
class MainListBottomTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    MainItemBottomItemBinding binding;

    MainListBottomTypeItem(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(Integer num) {
        itemView.setTag(num);
        binding.setIndexNum(num);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),TreasuresListActivity.class);
        v.getContext().startActivity(intent);
    }
}