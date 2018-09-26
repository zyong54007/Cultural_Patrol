package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.annotation.SuppressLint;
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
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ArchiveBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.HandleBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordErrorInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordErrorInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionAbnormalityBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.feedback.InspectionFeedBackActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.view.PopupWindowForImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常记录 巡检信息页面
 */
public class InspectionAbnormalityActivity extends AacBaseActivity<ActivityInspectionAbnormalityBinding> {

    private final String TAG = "InspectionFeedBackActivity";

    InspectionAbnormalityViewModel inspectionAbnormalityViewModel;

    InspectionHandlesAdapter mInspectionHandlesAdapter;

    PopupWindowForImage popupWindowForImage;

    ArrayList<TImage> imagesList;

    ArrayList<ArrayList<TImage>> allImagesList;

    InspectionAbnormalityAdapter mInspectionAbnormalityAdapter;

    InspectionArchivesAdapter mInspectionArchivesAdapter;

    ArrayList<Map<Object, Object>> stepList;

    private int wwId;

    private int recordId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_abnormality;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        InjectHelp.appComponent().inject(this);
        inspectionAbnormalityViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionAbnormalityViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId", 0);
        recordId = intent.getIntExtra("recordId", 0);
        initView();
    }

    public void initView() {

        mInspectionAbnormalityAdapter = new InspectionAbnormalityAdapter(getSupportFragmentManager());
        binding.stepRecyclerList.setAdapter(mInspectionAbnormalityAdapter);

        mInspectionHandlesAdapter = new InspectionHandlesAdapter(getSupportFragmentManager(), imageClickListener);
        binding.handlesRecyclerList.setAdapter(mInspectionHandlesAdapter);

        mInspectionArchivesAdapter = new InspectionArchivesAdapter(getSupportFragmentManager());
        binding.archivesRecyclerList.setAdapter(mInspectionArchivesAdapter);

        initData();

        initListen();

    }

    private void initData() {
        initListData();
        initTreasuresData();
        initRecordErrorData();
    }


    private void initTreasuresData() {
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);
        inspectionAbnormalityViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresInfoResponse response) {
                updateTreasuresView(response);
            }
        });
    }

    private void updateTreasuresView(GetTreasuresInfoResponse response) {
        if (null == response) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        binding.setTreasuresBean(response);
//        String createTime = response.getCreateTime();


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
                .load(url).
//                .placeholder(R.mipmap.default_iv).
//                error(R.mipmap.default_iv).
        into(imageView);
    }


    private void initRecordErrorData() {
        GetRecordErrorInfoRequest request = new GetRecordErrorInfoRequest(recordId);
        inspectionAbnormalityViewModel.getRecordErrorInfoResult(request).observe(this, new Observer<GetRecordErrorInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetRecordErrorInfoResponse getRecordErrorInfoResponse) {
                updataRecordErrorView(getRecordErrorInfoResponse);
            }
        });
    }

    private void updataRecordErrorView(GetRecordErrorInfoResponse response) {
        if (null == response) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }

//        response.toString();


        binding.setOpinionBean(response.getOpinion());
        binding.setCreateTime(response.getRecordInfo().getWriteTime());
        mInspectionHandlesAdapter.notifyDataSetChanged(response.getHandles());
        mInspectionArchivesAdapter.notifyDataSetChanged(response.getArchives());
        processImage(response.getHandles());
        processStepData(response);
    }

    private void processImage(List<HandleBean> listHandles) {
        if (listHandles == null)
            return;
        for (HandleBean bean : listHandles) {
            List<ImageBean> imageList = bean.getImageList();
            ArrayList<TImage> imagesList = new ArrayList<>();
            for (ImageBean IBean : imageList) {
                String imgPath = IBean.getImgPath();
                Log.i("TAG", "这是展示的图片地址吗==============" + imgPath);

                String imageUrl = "";
                if (!TextUtils.isEmpty(imgPath)) {

                    if (imgPath.startsWith("uploadfile")) {
                        imageUrl = Constants.BASEURL + imgPath;
                    } else {
                        imageUrl = Constants.DEBUG_BASE_URL_FILE + imgPath;
                    }
                }

//                String imageUrl = String.format("%s%s%s", Constants.BASEIMGURLONLY, "wwgl/wwfile", IBean.getImgPath() + ",,,");


                TImage imageAdd = TImage.of(imageUrl, TImage.FromType.OTHER);
                imagesList.add(imageAdd);
            }
            allImagesList.add(imagesList);
        }
    }

    private void processStepData(GetRecordErrorInfoResponse response) {
        Log.i("TAG", "巡检信息===========" + response.toString());

        if (null == response.getOpinion()) {
            //等待领导审批
            stepList.get(1).put(1, false);
            binding.setStepFlag(0);
            binding.setStepStatus(0);
        } else {
            if (1 == response.getOpinion().getOpinionStatus()) {
                //立即处理
                stepList.get(1).put(1, true);
                binding.setStepFlag(1);
                binding.setStepStatus(1);
            } else if (2 == response.getOpinion().getOpinionStatus()) {
                //拒绝处理
                stepList.get(1).put(1, true);
                //binding.lineStepNext.setVisibility(View.GONE);
                binding.setStepFlag(0);
                binding.setStepStatus(1);
            }
        }
        if (null == response.getHandles()) {
            //上传异常信息
            stepList.get(2).put(2, false);
        } else {
            stepList.get(2).put(2, true);
            binding.setStepStatus(2);
        }
        if (0 < response.getArchives().size()) {
            //归档处理
            int mArchiveStatus = 0;
            List<ArchiveBean> mArchiveList = response.getArchives();
            for (ArchiveBean bean : mArchiveList) {
                if (1 == bean.getArchiveStatus()) {
                    //同意归档
                    mArchiveStatus = 1;
                } else if (2 == bean.getArchiveStatus()) {
                    //拒绝归档
                    if (0 == mArchiveStatus)
                        mArchiveStatus = 2;
                }
            }
            if (0 == mArchiveStatus) {
                stepList.get(3).put(3, false);
            } else if (1 == mArchiveStatus) {
                stepList.get(3).put(3, true);
                binding.setStepFlag(0);
                binding.setStepStatus(3);
            } else if (2 == mArchiveStatus) {

            } else {
                stepList.get(3).put(3, true);
                binding.setStepFlag(1);
                binding.setStepStatus(3);
            }
        }
        mInspectionAbnormalityAdapter.notifyDataSetChanged(stepList);
    }

    private void initListData() {
        imagesList = new ArrayList<>();
        allImagesList = new ArrayList<ArrayList<TImage>>();
        stepList = new ArrayList<Map<Object, Object>>();
        for (int i = 0; i < 4; i++) {
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put(i, false);
            stepList.add(map);
        }
        //stepList.get(0).clear();
        stepList.get(0).put(0, true);
        mInspectionAbnormalityAdapter.notifyDataSetChanged(stepList);
    }

    public OnImageClickListener imageClickListener = new OnImageClickListener() {

        @SuppressLint("LongLogTag")
        @Override
        public void OnImageClickListener(View view, int listIndex, TImage image, int position) {
            Log.i(TAG, String.format("Path: %s", image.getOriginalPath()));
            //imagesList.clear();
            imagesList = allImagesList.get(listIndex);
            showPopWindow(position);
        }

    };

    /**
     * 图片弹出层
     * ############################################################################
     */

    public void showPopWindow(int position) {
        String originalPath = imagesList.get(0).getOriginalPath();
        Log.i("TAG", "当前图片地hi================" + originalPath);
        popupWindowForImage = new PopupWindowForImage(InspectionAbnormalityActivity.this, imagesList, position, popImageListener, false);
        popupWindowForImage.showAtLocation(binding.lineRoot, Gravity.CENTER, 0, 0);
    }

    /**
     * 弹出层点击回调
     */
    public com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener popImageListener = new com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener() {

        @SuppressLint("LongLogTag")
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

    public void stepToNext(View view) {
        Intent intent = new Intent(this, InspectionFeedBackActivity.class);
        intent.putExtra("wwId", wwId);
        intent.putExtra("recordId", recordId);
        startActivity(intent);
        finish();
    }

    public void stepToInfo(View view) {
        Intent intent = new Intent(this, InspectionActivity.class);
        intent.putExtra("wwId", wwId);
        intent.putExtra("recordId", recordId);
        startActivity(intent);
    }

    public void initListen() {
        binding.lineStepNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stepToNext(view);
            }
        });
        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.lineStepToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepToInfo(view);
            }
        });
    }

}
