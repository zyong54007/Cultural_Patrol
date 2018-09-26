package com.zhjy.cultural.services.patrol.ui.inspection.info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetImageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.view.PopupWindowForImage;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.util.ArrayList;
import java.util.List;


public class InspectionActivity extends AacBaseActivity<ActivityInspectionBinding> {

    private final String TAG = "InspectionActivity";

    ImageAdapter mImageAdapter;

    PopupWindowForImage popupWindowForImage;

    ArrayList<TImage> imagesList;

    RecordInfoViewModel recordInfoViewModel;

    private int wwId;

    private int recordId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        InjectHelp.appComponent().inject(this);
        recordInfoViewModel = ViewModelProviders.of(this, viewModelFactory()).get(RecordInfoViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId", 0);
        recordId = intent.getIntExtra("recordId", 0);
        initVIew();
        initListener();
    }

    private void initVIew() {
        //binding.scrollView.scrollTo(0,0);
        binding.editContent.setFocusable(false);
        binding.editContent.setFocusableInTouchMode(false);

        mImageAdapter = new ImageAdapter(getSupportFragmentManager(), imageClickListener);
        binding.recyclerList.setAdapter(mImageAdapter);
        initData();
    }

    private void initData() {
        initTreasuresData();
        initRecordInfo();
        initImageData();
    }

    private void initTreasuresData() {
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);

        Log.i("TAG", "文物巡检信息111111111111=============" + request.toString());


        recordInfoViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresInfoResponse response) {
                Log.i("TAG", "文物巡检信息111111111111111111=============" + request.toString());
                updateTreasuresView(response);
            }
        });
    }

    private void updateTreasuresView(GetTreasuresInfoResponse response) {
        if (null == response) {
            Toast.makeText(InspectionActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        binding.setTreasuresBean(response);


//        binding.setImageUrl(Constants.BASEURL + response.getPicturePath());
        String picturePath = response.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrl(Constants.BASE_URL_UP_IMG + response.getPicturePath());
            } else {
                binding.setImageUrl(Constants.BASEIMGURL + response.getPicturePath());
            }
        }


//        binding.setImageUrlPath(Constants.BASEURL + response.getPicturePath());
    }

    @BindingAdapter({"bind:image"})
    public static void imageLoader(ImageView imageView, String url) {
//        ImageLoader.getInstance().displayImage(url, imageView);
        Glide.with(imageView.getContext())
                .load(url)
//                .placeholder(R.mipmap.default_iv).
//                error(R.mipmap.default_iv)
                .into(imageView);
    }

    private void initRecordInfo() {
        GetRecordInfoRequest request = new GetRecordInfoRequest(recordId);
        Log.i("TAG", "文物巡检信息2222222222222222=============" + request.toString());
        recordInfoViewModel.getRecordInfoResult(request).observe(this, new Observer<GetRecordInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetRecordInfoResponse getRecordInfoResponse) {
                Log.i("TAG", "文物巡检信息222222222222222=============" + getRecordInfoResponse.toString());
                updateRecordView(getRecordInfoResponse);
            }
        });
    }

    private void updateRecordView(GetRecordInfoResponse response) {
        if (null == response) {
            Toast.makeText(InspectionActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        binding.setRecordBean(response);
    }

    private void initImageData() {
        GetImageListRequest request = new GetImageListRequest(recordId, 2);
        Log.i("TAG", "巡检信息33333333333333333==============" + request.toString());
        recordInfoViewModel.getImageListResult(request).observe(this, new Observer<List<ImageBean>>() {
            @Override
            public void onChanged(@Nullable List<ImageBean> listImage) {
                Log.i("TAG", "巡检信息33333333333333333==============" + listImage.size());
                updateImageView(listImage);
            }
        });
    }

    private void updateImageView(List<ImageBean> listImage) {
        if (null == listImage) {
            Toast.makeText(InspectionActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        imagesList = new ArrayList<>();
        for (int i = 0; i < listImage.size(); i++) {
            String imagePath = listImage.get(i).getImgPath();
            TImage imageAdd = TImage.of(String.format("%s%s%s", Constants.BASEURL, "wwgl/wwfile", imagePath), TImage.FromType.OTHER);
            imagesList.add(imageAdd);
        }
        mImageAdapter.notifyDataSetChanged(imagesList);
    }

    public OnImageClickListener imageClickListener = new OnImageClickListener() {

        @Override
        public void OnImageClickListener(View view, TImage image) {

        }

        @Override
        public void OnImageClickListener(View view, TImage image, int position) {
            Log.i(TAG, String.format("Path: %s", image.getOriginalPath()));
            showPopWindow(position);
        }

    };

    private void initListener() {

        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    /**
     * 图片弹出层
     * ############################################################################
     */

    public void showPopWindow(int position) {
        popupWindowForImage = new PopupWindowForImage(InspectionActivity.this, imagesList, position, popImageListener, false);
        popupWindowForImage.showAtLocation(binding.lineRoot, Gravity.CENTER, 0, 0);
    }

    /**
     * 弹出层点击回调
     */
    public OnImageClickListener popImageListener = new OnImageClickListener() {

        @Override
        public void OnImageClickListener(View view, TImage image) {
            Log.i(TAG, String.format("Path: %s", image.getOriginalPath()));
            popupWindowForImage.dismiss();
        }

        @Override
        public void OnImageClickListener(View view, TImage image, int position) {

        }
    };

    /**
     * ############################################################################
     * 图片弹出层结束
     */
}
