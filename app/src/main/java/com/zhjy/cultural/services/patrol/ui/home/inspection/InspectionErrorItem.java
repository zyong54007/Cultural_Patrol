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
import com.zhjy.cultural.services.patrol.databinding.FragmentMainInspectionListItemErrorBinding;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityActivity;

/**
 * Created by jialg on 2018/1/24.
 */

public class InspectionErrorItem extends RecyclerView.ViewHolder implements View.OnClickListener {
    FragmentMainInspectionListItemErrorBinding binding;

    InspectionErrorItem(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(RecordBean bean) {
        itemView.setTag(bean);
        binding.setBean(bean);
//        binding.setImageUrlPath(Constants.BASEURL + bean.getWw().getPicturePath());
//        binding.setImageUrl(Constants.BASEURL + bean.getWw().getPicturePath());
        String picturePath = bean.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrl(Constants.BASE_URL_UP_IMG + bean.getWw().getPicturePath());
            } else {
                binding.setImageUrl(Constants.BASEIMGURL + bean.getWw().getPicturePath());
            }
        }


        int status = bean.getStatus();
        if (status == 2) {
            int flow = bean.getFlow();
            if (flow == 1) {
                binding.setStatus("已提交");
//                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
                binding.status.setBackgroundResource(R.drawable.shape_type_green);

            }
            if (flow == 2) {
                binding.setStatus("待处理");
                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
            }
            if (flow == 3) {
                binding.setStatus("待处理");
                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
//                binding.status.setBackgroundResource(R.drawable.shape_type_green);
            }
        }
        if (status == 3) {
            binding.setStatus("已归档");
            binding.status.setBackgroundResource(R.drawable.shape_type_vio);
        }

        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        RecordBean bean = (RecordBean) v.getTag();
        Intent intent = new Intent(v.getContext(), InspectionAbnormalityActivity.class);
        intent.putExtra("wwId", bean.getWw().getId());
        intent.putExtra("recordId", bean.getId());
        v.getContext().startActivity(intent);
    }

    @BindingAdapter({"bind:image"})
    public static void imageLoader(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url).
//                .placeholder(R.mipmap.default_iv).
//                error(R.mipmap.default_iv).
                into(imageView);
    }

}
