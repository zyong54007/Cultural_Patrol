package com.zhjy.cultural.services.patrol.ui.setup.feedback;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jph.takephoto.model.TImage;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.ActivityFeedBackListItemBinding;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.io.File;
import java.net.URI;

/**
 * Created by jialg on 2018/2/1.
 */

public class FeedBackItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityFeedBackListItemBinding binding;
    public OnImageClickListener mListener;
    public int position;

    FeedBackItem(View itemView, OnImageClickListener mListener) {
        super(itemView);
        this.mListener = mListener;
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TImage image, int position) {

        this.position = position;
        itemView.setTag(image);
        if ("".equals(image.getOriginalPath())) {
            Picasso.with(itemView.getContext()).load(R.mipmap.yjfk_img_add_a).into(binding.image);
        } else {
            String originalPath = image.getOriginalPath();
            Log.i("TAG", "============" + originalPath);
            File file = new File(image.getOriginalPath());
            String path = file.getPath();
            String absolutePath = file.getAbsolutePath();
            URI uri = file.toURI();
            String s = String.valueOf(uri);
            String path1 = uri.getPath();

            Log.i("TAG", absolutePath + "=======路径对比============" + path + "==================" + s + "==================" + path1);


            Picasso.with(itemView.getContext()).load(new File(image.getOriginalPath())).into(binding.image);
//            Picasso.with(itemView.getContext()).load(String.valueOf(uri)).into(binding.image);
        }
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        TImage bean = (TImage) v.getTag();
        mListener.OnImageClickListener(v, bean, position);
    }

}
