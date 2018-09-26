package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresItemBinding;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;

/**
 * Created by jialg on 2018/2/5.
 */

public class TreasuresItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityTreasuresItemBinding binding;

    OnListMoreListener listMoreListener;

    TreasuresItem(View itemView, OnListMoreListener listMoreListener) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        this.listMoreListener = listMoreListener;
        itemView.setOnClickListener(this);
    }

    void bind(RecordBean bean) {
        itemView.setTag(bean);
        binding.setBean(bean);


//        binding.setImageUrl(Constants.BASEURL + bean.getWw().getPicturePath());

        String picturePath = bean.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrl(Constants.BASE_URL_UP_IMG + bean.getWw().getPicturePath());
            } else {
                binding.setImageUrl(Constants.BASEIMGURL + bean.getWw().getPicturePath());
            }
        }


        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View view) {
        RecordBean bean = (RecordBean) view.getTag();
        Intent intent = new Intent(view.getContext(), InspectionActivity.class);
        intent.putExtra("wwId", bean.getWw().getId());
        intent.putExtra("recordId", bean.getId());
        view.getContext().startActivity(intent);
    }


}
