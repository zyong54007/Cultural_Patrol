package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.HandleBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionHandlesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class InspectionHandlesItem extends RecyclerView.ViewHolder implements View.OnClickListener {

    ActivityInspectionHandlesItemBinding binding;

    ImageAdapter mImageAdapter;

    ArrayList<TImage> imagesList;

    FragmentManager fragmentManager;

    OnImageClickListener imageClickListener;

    InspectionHandlesItem(View itemView, FragmentManager fragmentManager, OnImageClickListener imageClickListener) {
        super(itemView);
        this.fragmentManager = fragmentManager;
        this.imageClickListener = imageClickListener;
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
        binding.editContent.setFocusable(false);
        binding.editContent.setFocusableInTouchMode(false);
    }

    void bind(HandleBean bean, int position) {
        itemView.setTag(bean);
        binding.editContent.setText(bean.getHandleDescription());
        mImageAdapter = new ImageAdapter(fragmentManager, imageClickListener, position);
        binding.imgRecyclerList.setAdapter(mImageAdapter);
        List<ImageBean> imageList = bean.getImageList();
        imagesList = new ArrayList<>();
        for (ImageBean IBean : imageList) {

//            String imageUrl = String.format("%s%s%s", Constants.BASEIMGURLONLY, "wwgl/wwfile", IBean.getImgPath());
            String imageUrl = "";
            String imgPath = IBean.getImgPath();
            if (!TextUtils.isEmpty(imgPath)) {
                   //测试环境下  巡检图片显示
                if (imgPath.startsWith("uploadfile")) {
                    imageUrl = Constants.BASEURL + imgPath;
                } else {
                    imageUrl = Constants.DEBUG_BASE_URL_FILE + imgPath;
                }
            }

            Log.i("TAG", "这是000000000000000000000000000000000000000000==================" + imageUrl);
            TImage imageAdd = TImage.of(imageUrl, TImage.FromType.OTHER);
            imagesList.add(imageAdd);
        }
        mImageAdapter.notifyDataSetChanged(imagesList);
        //消除数据刷新时闪动问题
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {

    }

}
