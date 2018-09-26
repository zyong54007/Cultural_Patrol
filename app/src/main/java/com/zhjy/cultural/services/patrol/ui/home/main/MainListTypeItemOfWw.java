package com.zhjy.cultural.services.patrol.ui.home.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.databinding.MainItemListItemWwBinding;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;

/**
 * MainListTypeItem
 */
class MainListTypeItemOfWw extends RecyclerView.ViewHolder implements View.OnClickListener {

    MainItemListItemWwBinding binding;

    MainListTypeItemOfWw(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TreasuresBean bean) {
        itemView.setTag(bean);
        binding.setTreasuresBean(bean);
        String picturePath = bean.getPicturePath();
        int totalStatus2 = bean.getTotalStatus2();
        int totalStatus3 = bean.getTotalStatus3();
        int num = totalStatus2 + totalStatus3;
        binding.wenwuerrornum.setText("异常信息:" + num);

        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + bean.getPicturePath());
            } else {
                binding.setImageUrlPath(Constants.BASEIMGURL + bean.getPicturePath());
            }
        }
//        binding.setImageUrlPath("http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg");

//        String imgurl = Constants.BASEURL + bean.getPicturePath();

//        Log.i("TAG", "===============" + imgurl);

        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        TreasuresBean bean = (TreasuresBean) v.getTag();
        Intent intent = new Intent(v.getContext(), TreasuresActivity.class);
        intent.putExtra("id", bean.getId());
        v.getContext().startActivity(intent);
    }
}