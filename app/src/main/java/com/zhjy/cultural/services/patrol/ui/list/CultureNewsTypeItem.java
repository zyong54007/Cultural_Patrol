package com.zhjy.cultural.services.patrol.ui.list;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureListBean;
import com.zhjy.cultural.services.patrol.databinding.CultureItemLastItemBinding;

/**
 * HotNewsTypeItem <br/>
 */
class CultureNewsTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    CultureItemLastItemBinding binding;

    CultureNewsTypeItem(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(CultureListBean bean) {
        itemView.setTag(bean);

        binding.setTitleName(bean.getTitle());
        if (bean.getThumb() != null && !bean.getThumb().isEmpty()) {
            binding.setImageUrlPath(bean.getImageThumb());
        }
    }

    @Override
    public void onClick(View v) {

    }
}