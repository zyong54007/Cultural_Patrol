package com.zhjy.cultural.services.patrol.ui.treasures.choice;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresListListItemBinding;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;

/**
 * Created by jialg on 2018/3/22.
 */

public class TreasuresChoiceTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityTreasuresListListItemBinding binding;

    TreasuresChoiceTypeItem(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TreasuresBean bean) {
        itemView.setTag(bean);
        binding.setTreasuresBean(bean);


//        binding.setImageUrl(Constants.BASEURL + bean.getPicturePath());


        String picturePath = bean.getPicturePath();

        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrl(Constants.BASE_URL_UP_IMG + bean.getPicturePath());
            } else {
                binding.setImageUrl(Constants.BASEIMGURL + bean.getPicturePath());
            }
        }


//        binding.setImageUrlPath(Constants.BASEURL + bean.getPicturePath());
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        TreasuresBean bean = (TreasuresBean) v.getTag();
        Intent intent = new Intent(v.getContext(), PunchClockActivity.class);
        intent.putExtra("wwId", bean.getId());
        String point = bean.getPoint();
        intent.putExtra(Contant.POINT, point);
        v.getContext().startActivity(intent);
        ((Activity) v.getContext()).finish();
    }

    @BindingAdapter({"bind:image"})
    public static void imageLoader(ImageView imageView, String url) {
//        ImageLoader.getInstance().displayImage(url, imageView);


        Glide.with(imageView.getContext())
                .load(url).
//                .placeholder(R.mipmap.default_iv).
//                error(R.mipmap.default_iv).
                into(imageView);
    }

}
