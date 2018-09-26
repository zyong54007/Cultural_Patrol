package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresItemErrorBinding;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityActivity;

/**
 * Created by jialg on 2018/2/5.
 */

public class TreasuresErrorItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityTreasuresItemErrorBinding binding;

    OnListMoreListener listMoreListener;

    TreasuresErrorItem(View itemView, OnListMoreListener listMoreListener) {
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


        int status = bean.getStatus();
//        Log.i("TAG", "判断的标题中=============" + status);

        if (status == 2) {
            int flow = bean.getFlow();
//            Log.i("TAG", "判断的标题中=============" + flow);

            if (flow == 1) {
//                Log.i("TAG", "判断的标题中待反馈=============" + flow);
                binding.setStatus("已提交");
                binding.status.setBackgroundResource(R.drawable.shape_type_green);
//                binding.setStatuscolor(R.drawable.shape_type_green);
            }
            if (flow == 2) {
                binding.setStatus("待处理");
                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
//                binding.setStatuscolor(R.drawable.inspection_list_text_dcl);
            }
            if (flow == 3) {
                binding.setStatus("待处理");
                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
//                binding.status.setBackgroundResource(R.drawable.shape_type_green);
//                binding.setStatuscolor(R.drawable.shape_type_green);
            }

        }
        if (status == 3) {
            binding.setStatus("已归档");
            binding.status.setBackgroundResource(R.drawable.shape_type_vio);
//            binding.setStatuscolor(R.drawable.shape_type_vio);

        }


        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }


    @Override
    public void onClick(View view) {
        RecordBean bean = (RecordBean) view.getTag();
        Intent intent = new Intent(view.getContext(), InspectionAbnormalityActivity.class);
        intent.putExtra("wwId", bean.getWw().getId());
        intent.putExtra("recordId", bean.getId());
        view.getContext().startActivity(intent);
    }


}
