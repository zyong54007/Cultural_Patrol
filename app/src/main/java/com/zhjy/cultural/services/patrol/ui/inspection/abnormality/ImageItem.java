package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.databinding.ActivityFeedBackListItemBinding;

/**
 * Created by jialg on 2018/2/1.
 */

public class ImageItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityFeedBackListItemBinding binding;
    public OnImageClickListener mListener;
    public int position;
    public int listIndex;

    ImageItem(View itemView, OnImageClickListener mListener, int listIndex) {
        super(itemView);
        this.mListener = mListener;
        this.listIndex = listIndex;
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TImage imageUrl, int position) {

        this.position = position;
        itemView.setTag(imageUrl);


        Log.i("TAG", "异常待办图片地址==========================" + imageUrl.getOriginalPath());
        String originalPath = imageUrl.getOriginalPath();
        if (!TextUtils.isEmpty(originalPath)) {
//            if (originalPath.startsWith("uploadfile")) {
//                Glide.with(itemView.getContext()).load(originalPath).into(binding.image);
//            } else {
                Glide.with(itemView.getContext()).load(originalPath).into(binding.image);
//            }
        }


//        Picasso.with(itemView.getContext()).load(imageUrl.getOriginalPath()).into(binding.image);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        TImage bean = (TImage) v.getTag();
        mListener.OnImageClickListener(v, listIndex, bean, position);
    }

}
