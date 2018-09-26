package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionAbnormalityStepItemBinding;

import java.util.Map;

/**
 * Created by jialg on 2018/2/1.
 */

public class InspectionAbnormalityStepItem extends RecyclerView.ViewHolder implements View.OnClickListener{

    ActivityInspectionAbnormalityStepItemBinding binding;

    public int position;

    public final static String [] stepInfo = {"异常记录","领导反馈","反馈处理","完毕归档"};

    InspectionAbnormalityStepItem(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(Map<Object, Object> map, int position) {
        boolean status = (boolean)map.get(position);
        this.position = position;
        itemView.setTag(map);
        if(status){
            Picasso.with(itemView.getContext()).load(R.mipmap.inspection_step_a).into(binding.stepImage);
            binding.stepTitleInfo.setTextColor(Color.parseColor("#844949"));
            binding.stepTitleNumber.setTextColor(Color.parseColor("#ffffff"));
        }
        else{
            Picasso.with(itemView.getContext()).load(R.mipmap.inspection_step_b).into(binding.stepImage);
            binding.stepTitleInfo.setTextColor(Color.parseColor("#474455"));
            binding.stepTitleNumber.setTextColor(Color.parseColor("#474455"));
        }
        binding.stepTitleInfo.setText(String.valueOf(stepInfo[position % 4]));
        binding.stepTitleNumber.setText(String.valueOf(position + 1));
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
    }

}
