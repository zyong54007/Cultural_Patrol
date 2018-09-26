package com.zhjy.cultural.services.patrol.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;

import java.util.List;

public class TreaseListAdapter extends BaseQuickAdapter<TreasuresEntity.Datas, BaseViewHolder> {
    public TreaseListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreasuresEntity.Datas documents) {


        helper.setText(R.id.item_treasures_tv_title, documents.getTitle());
        helper.setText(R.id.item_treasures_tv_leave, "保护等级:" + documents.getWwTypeStr());

        helper.setText(R.id.item_treasures_tv_type, "风险类别:" + documents.getFxTypeStr());

        helper.setText(R.id.item_treasures_tv_address, documents.getAddress());

        helper.setText(R.id.item_treasures_tv_totalrecord, "巡检" + documents.getTotalRecord() + "次");
        int size = documents.getTotalStatus3() + documents.getTotalStatus2();
        helper.setText(R.id.item_treasures_tv_error_num, "异常信息:" + size);
        helper.setText(R.id.item_treasures_tv_file_num, "已归档(" + documents.getTotalStatus3() + ")");
        helper.setText(R.id.item_treasures_tv_nofile_num, "未归档(" + documents.getTotalStatus2() + ")");
        String picturePath = documents.getPicturePath();

        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Glide.with(mContext).load(Constants.BASE_URL_UP_IMG + picturePath).into((ImageView) helper.getView(R.id.item_treasures_image));
            } else {
                Glide.with(mContext).load(Constants.BASEIMGURL + picturePath).into((ImageView) helper.getView(R.id.item_treasures_image));
            }
        }


    }
}
