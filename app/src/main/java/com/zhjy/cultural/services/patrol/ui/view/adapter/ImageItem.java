package com.zhjy.cultural.services.patrol.ui.view.adapter;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;

import com.jph.takephoto.model.TImage;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.databinding.ActivityFeedBackImageItemBinding;

import java.io.File;

/**
 * Created by jialg on 2018/2/1.
 */

public class ImageItem implements View.OnClickListener {

    ActivityFeedBackImageItemBinding binding;

    private OnImageClickListener mListener;

    ImageItem(View itemView, OnImageClickListener mListener) {
        binding = DataBindingUtil.bind(itemView);
        this.mListener = mListener;
        itemView.setOnClickListener(this);
    }

    void bind(TImage image) {
        binding.getRoot().setTag(image);
        if (image.getOriginalPath().startsWith("http")) {
            Log.i("TAG", "巡检图片展示========================" + image.getOriginalPath());
            Picasso.with(binding.getRoot().getContext()).load(image.getOriginalPath()).into(binding.image);
        } else {
            Picasso.with(binding.getRoot().getContext()).load(new File(image.getOriginalPath())).into(binding.image);
        }
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        mListener.OnImageClickListener(v, (TImage) v.getTag());
    }

}
