package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.ArchiveBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionArchivesItemBinding;

public class InspectionArchivesItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityInspectionArchivesItemBinding binding;

    InspectionArchivesItem(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(ArchiveBean bean) {
        itemView.setTag(bean);
        binding.setMArchiveBean(bean);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
    }

}
