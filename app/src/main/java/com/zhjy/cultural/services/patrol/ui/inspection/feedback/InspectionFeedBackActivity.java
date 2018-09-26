package com.zhjy.cultural.services.patrol.ui.inspection.feedback;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostHandleSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostHandleSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionFeedBackBinding;
import com.zhjy.cultural.services.patrol.ui.base.PhotoBaseActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.add.InspectionAddActivity;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.CustomHelper;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackAdapter;
import com.zhjy.cultural.services.patrol.ui.view.ActionSheetDialog;
import com.zhjy.cultural.services.patrol.ui.view.PopupWindowForImage;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

/**
 * 异常记录 反馈信息页面
 */
public class InspectionFeedBackActivity extends PhotoBaseActivity<ActivityInspectionFeedBackBinding> {

    private final String tag = "InspectionFeedBackActivity";

    @Inject
    CustomHelper customHelper;

    InspectionFeedBackViewModel inspectionFeedBackViewModel;

    PopupWindowForImage popupWindowForImage;

    ArrayList<TImage> imagesList;

    ArrayList<TImage> photoList;

    FeedBackAdapter feedBackAdapter;

    private int recordId;

    private int wwId;

    private List<PostImageResponse> imageId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_feed_back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        InjectHelp.appComponent().inject(this);
        inspectionFeedBackViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionFeedBackViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId", 0);
        recordId = intent.getIntExtra("recordId", 0);
        initView();
    }

    private void initView() {

        //binding.editContent.setFocusable(false);
        //binding.editContent.setFocusableInTouchMode(false);

        feedBackAdapter = new FeedBackAdapter(getSupportFragmentManager(), imageClickListener);
        binding.imgRecyclerList.setAdapter(feedBackAdapter);

        initListen();
        initData();
    }

    private void initData() {
        initTreasuresData();
        initImageData();
    }

    private void initTreasuresData() {
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);
        inspectionFeedBackViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
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
        String picturePath = response.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + response.getPicturePath());
            } else {
                binding.setImageUrlPath(Constants.BASEIMGURL + response.getPicturePath());
            }
        }


//        binding.setImageUrlPath(Constants.BASEURL + response.getPicturePath());
    }


    private void uploadData() {
        String content = binding.editContent.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(InspectionFeedBackActivity.this, "请输入巡检描述信息！", Toast.LENGTH_LONG).show();
            return;
        }

        if (photoList.size() == 0) {
            Toast.makeText(InspectionFeedBackActivity.this, "请选择巡检图片！", Toast.LENGTH_LONG).show();
            return;
        }

        if (photoList.size() > 0) {
            startUploadThread();
            return;
        }
    }

    private void startUploadThread() {
        showLoadingProgressBar();
        Thread thread = new Thread() {
            @Override
            public void run() {
                processImageData();
            }
        };
        thread.start();
    }

    private void processImageData() {
        imageId = new ArrayList<>();
        int latchNumber = photoList.size();
        final CountDownLatch latch = new CountDownLatch(latchNumber);
        for (TImage image : photoList) {
            List<File> tempFiles = new ArrayList<>();
            tempFiles.add(new File(image.getOriginalPath()));
            postImageData(tempFiles, latch);
        }
        try {
            latch.await();
            postSaveResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void postImageData(List<File> files, CountDownLatch latch) {
        inspectionFeedBackViewModel.postImageResult(files).observe(this, new Observer<PostImageResponse>() {
            @Override
            public void onChanged(@Nullable PostImageResponse postImageResponse) {
                if (null == postImageResponse) {
                    closeLoadingProgressBar();
                    Toast.makeText(InspectionFeedBackActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println(postImageResponse.toString());
                imageId.add(postImageResponse);
                latch.countDown();
                ;
            }
        });
    }

    private void postSaveResult() {
        String content = binding.editContent.getText().toString();
        String ids = processIds();
        PostHandleSaveRequest request = new PostHandleSaveRequest(recordId, content, ids);
        inspectionFeedBackViewModel.postHandleSaveResult(request).observe(this, new Observer<PostHandleSaveResponse>() {
            @Override
            public void onChanged(@Nullable PostHandleSaveResponse postHandleSaveResponse) {
                closeLoadingProgressBar();
                if (null == postHandleSaveResponse) {
                    Toast.makeText(InspectionFeedBackActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println(postHandleSaveResponse.toString());
                if ("success".equals(postHandleSaveResponse.getStatus())) {
                    Toast.makeText(InspectionFeedBackActivity.this, "已经反馈提交成功", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private String processIds() {
        String ids = "";
        if (null == imageId) {
            return ids;
        }
        for (PostImageResponse bean : imageId) {
            ids = String.format("%s%s%s", ids, bean.getId(), ",");
        }
        return ids;
    }

    private void initImageData() {
        photoList = new ArrayList<>();
        ArrayList<TImage> imagesList = new ArrayList<>();
        TImage imageAdd = TImage.of("", TImage.FromType.OTHER);
        imagesList.add(imageAdd);
        feedBackAdapter.notifyDataSetChanged(imagesList);
    }


    /**
     * #################################################################
     * 选择相册图片
     *
     * @param view
     */
    public void takeImages(View view) {
        int limit = 10 - photoList.size();
        customHelper.takeImagesCall(view, getTakePhoto(), limit);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }


    private void showImg(ArrayList<TImage> images) {
        feedBackAdapter.clearData();
        if (images.size() > 0)
            photoList.addAll(images);
        if (photoList.size() < 10) {
            ArrayList<TImage> imagesList = new ArrayList<>();
            imagesList.addAll(photoList);
            TImage imageAdd = TImage.of("", TImage.FromType.OTHER);
            imagesList.add(imageAdd);
            feedBackAdapter.notifyDataSetChanged(imagesList);
        } else {
            feedBackAdapter.notifyDataSetChanged(photoList);
        }

    }

    private void showImg() {
        ArrayList<TImage> images = new ArrayList<TImage>();
        showImg(images);
    }

    /**
     * #########################################################################
     * 相册图片选择结束
     */

    /**
     * 相册图片点击回调
     */
    public OnImageClickListener imageClickListener = new OnImageClickListener() {

        @Override
        public void OnImageClickListener(View view, TImage image) {

        }

        @SuppressLint("LongLogTag")
        @Override
        public void OnImageClickListener(View view, TImage image, int position) {
            if (null == image || "".equals(image.getOriginalPath())) {

                new ActionSheetDialog(InspectionFeedBackActivity.this)
                        .builder()
                        .setTitle("选择")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.BLAck,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {


                                        takeImagesCreame(view);
                                    }
                                }).addSheetItem("图库", ActionSheetDialog.SheetItemColor.BLAck,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                takeImages(view);

                            }
                        }).show();

            } else {
                Log.i(tag, String.format("Path: %s", image.getOriginalPath()));
                showPopWindow(position);
            }
        }
    };


    /**
     * 从相机获取图片
     *
     * @param view
     */
    public void takeImagesCreame(View view) {
//        TakePhoto takePhoto = getTakePhoto();

        customHelper.takeImages(view, getTakePhoto());
    }


    /**
     * 图片弹出层
     * ############################################################################
     */

    public void showPopWindow(int position) {
        popupWindowForImage = new PopupWindowForImage(InspectionFeedBackActivity.this, photoList, position, popImageListener);
        popupWindowForImage.showAtLocation(binding.lineRoot, Gravity.CENTER, 0, 0);
    }

    /**
     * 弹出层点击回调
     */
    public OnImageClickListener popImageListener = new OnImageClickListener() {

        @SuppressLint("LongLogTag")
        @Override
        public void OnImageClickListener(View view, TImage image) {
            Log.i(tag, String.format("Path: %s", image.getOriginalPath()));
            popupWindowForImage.dismiss();
            showImg();
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
        uploadData();
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
    }


}
