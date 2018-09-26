package com.zhjy.cultural.services.patrol.ui.treasures.list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.databinding.MainItemListItemWwBinding;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;

/**
 * Created by jialg on 2018/1/25.
 */

public class TreasuresListTypeItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    MainItemListItemWwBinding binding;

    TreasuresListTypeItem(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    void bind(TreasuresBean bean) {
        itemView.setTag(bean);
        binding.setTreasuresBean(bean);


        binding.setImageUrlPath(Constants.BASEURL + bean.getPicturePath());

        String picturePath = bean.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {

            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + bean.getPicturePath());
            } else {
                binding.setImageUrlPath(Constants.BASEIMGURL + bean.getPicturePath());
            }
        }


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
