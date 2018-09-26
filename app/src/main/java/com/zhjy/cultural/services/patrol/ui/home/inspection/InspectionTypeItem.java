package com.zhjy.cultural.services.patrol.ui.home.inspection;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainInspectionListItemBinding;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;

/**
 * Created by jialg on 2018/1/24.
 */

public class InspectionTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    FragmentMainInspectionListItemBinding binding;

    InspectionTypeItem(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(RecordBean bean) {
        if (bean != itemView.getTag()) {
            itemView.setTag(bean);
            binding.setBean(bean);
            String.format("巡检时间:%s", bean.getCreateTime());


//            binding.setImageUrl(Constants.BASEURL + bean.getWw().getPicturePath());
            String picturePath = bean.getWw().getPicturePath();
            if (!TextUtils.isEmpty(picturePath)) {
                if (picturePath.startsWith("uploadfile")) {
                    binding.setImageUrl(Constants.BASE_URL_UP_IMG + bean.getWw().getPicturePath());
                } else {
                    binding.setImageUrl(Constants.BASEIMGURL + bean.getWw().getPicturePath());
                }
            }


            int status = bean.getStatus();
            if (status == 1) {
                binding.setStatus("巡检正常");
                binding.inspecstatus.setBackgroundResource(R.drawable.shape_type_green);
            } else {
                binding.setStatus("巡检异常");
                binding.inspecstatus.setBackgroundResource(R.drawable.inspection_list_text_dcl);
            }

//            binding.setImageUrlPath(Constants.BASEURL + bean.getWw().getPicturePath());
            //消除数据刷新时闪动问题
            binding.executePendingBindings();


        }


    }

    @Override
    public void onClick(View v) {
        RecordBean bean = (RecordBean) v.getTag();
        Intent intent = new Intent(v.getContext(), InspectionActivity.class);
        intent.putExtra("wwId", bean.getWw().getId());
        intent.putExtra("recordId", bean.getId());
        v.getContext().startActivity(intent);
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
