package com.zhjy.cultural.services.patrol.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;

import java.util.List;

public class InspectionFailedAdapter extends BaseQuickAdapter<InSpecListEntity.Datas, BaseViewHolder> {
    public InspectionFailedAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InSpecListEntity.Datas item) {

        helper.setText(R.id.inspec_tv_title, item.getWw().getTitle());
        helper.setText(R.id.inspec_tv_time, item.getWriteTime());
//
        String picturePath = item.getWw().getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.inspec_iv));
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.inspec_iv));
            }
        }

        int status = item.getStatus();

        if (status == 2) {
            int flow = item.getFlow();
            if (flow == 1) {
                helper.setText(R.id.inspec_tv_status, "已提交");
//                binding.setStatus("已提交");
//                binding.status.setBackgroundResource(R.drawable.inspection_list_text_dcl);
                helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.shape_type_green);

            }
            if (flow == 2) {
//                binding.setStatus("待处理");
                helper.setText(R.id.inspec_tv_status, "待处理");
                helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.inspection_list_text_dcl);
            }
            if (flow == 3) {
                helper.setText(R.id.inspec_tv_status, "待处理");

//                binding.setStatus("待处理");
                helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.inspection_list_text_dcl);
//                binding.status.setBackgroundResource(R.drawable.shape_type_green);
            }
        }
        if (status == 3) {
//            binding.setStatus("已归档");
            helper.setText(R.id.inspec_tv_status, "已归档");
            helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.shape_type_vio);
        }


//        int status = item.getStatus();
//        if (status == 1) {
//            helper.setText(R.id.inspec_tv_status, "巡检正常");
//            helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.shape_type_green);
//        } else {
//            helper.setText(R.id.inspec_tv_status, "巡检异常");
//            helper.getView(R.id.inspec_tv_status).setBackgroundResource(R.drawable.inspection_list_text_dcl);
//        }


//
    }
}
