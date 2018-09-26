package com.zhjy.cultural.services.patrol.ui.inspection.info;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jph.takephoto.model.TImage;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.databinding.ActivityFeedBackListItemBinding;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

/**
 * Created by jialg on 2018/2/1.
 */

public class ImageItem extends RecyclerView.ViewHolder implements View.OnClickListener{

    ActivityFeedBackListItemBinding binding;
    public OnImageClickListener mListener;
    public int position;
    ImageItem(View itemView, OnImageClickListener mListener) {
        super(itemView);
        this.mListener = mListener;
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TImage imageUrl,int position) {

        this.position = position;
        itemView.setTag(imageUrl);
        Picasso.with(itemView.getContext()).load(imageUrl.getOriginalPath()).into(binding.image);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        TImage bean = (TImage) v.getTag();
        mListener.OnImageClickListener(v,bean,position);
    }

}
