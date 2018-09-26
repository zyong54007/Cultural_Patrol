package com.zhjy.cultural.services.patrol.ui.home.inspection.path;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.pojo.bean.PathBean;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainInspectionMapPathItemBinding;

/**
 * Created by jialg on 2018/1/30.
 */

public class PathItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    FragmentMainInspectionMapPathItemBinding binding;

    private OnPathListInteractionListener mListener ;

    PathItem(View itemView,OnPathListInteractionListener mListener ) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.mListener = mListener;
        itemView.setOnClickListener(this);
    }

    void bind(PathBean bean) {

        itemView.setTag(bean);

        binding.setNameInfo(bean.getName());
        binding.setIndexNumber(Integer.valueOf(bean.getId()));
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        mListener.OnPathListInteractionListener((PathBean)v.getTag());
    }
}
