package com.zhjy.cultural.services.patrol.ui.setup.feedback;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.AlertDialogView;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetFeedBackRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetFeedBackResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityFeedBackBinding;
import com.zhjy.cultural.services.patrol.ui.base.PhotoBaseActivity;
import com.zhjy.cultural.services.patrol.ui.view.PopupWindowForImage;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;


public class FeedBackActivity extends PhotoBaseActivity<ActivityFeedBackBinding> {

    private String TAG = "FeedBackActivity";


    @Inject
    CustomHelper customHelper;

    FeedBackViewModel feedBackViewModel;

    FeedBackAdapter feedBackAdapter;

    private ArrayList<TImage> photoList;

    PopupWindowForImage popupWindowForImage;

    private List<File> files;

    private List<PostImageResponse> imageId;
    private AlertDialogView alertDialogView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        InjectHelp.appComponent().inject(this);
        feedBackViewModel = ViewModelProviders.of(this, viewModelFactory()).get(FeedBackViewModel.class);
        initView();
        initData();
        alertDialogView = new AlertDialogView(this);
    }

    private void initView() {

        feedBackAdapter = new FeedBackAdapter(getSupportFragmentManager(), imageClickListener);
        binding.recyclerList.setAdapter(feedBackAdapter);
        initListener();

//
    }

    private void initListener() {

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void uploadData() {

        String trim = binding.editContent.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToastMsg("请输入反馈意见");
            return;
        }


        if (photoList.size() > 0) {
            startUploadThread();
            return;
        }
        getFeedBackResult();
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
            getFeedBackResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void postImageData(List<File> files, CountDownLatch latch) {
        feedBackViewModel.postImageResult(files).observe(this, new Observer<PostImageResponse>() {
            @Override
            public void onChanged(@Nullable PostImageResponse postImageResponse) {
                if (null == postImageResponse) {
                    closeLoadingProgressBar();
                    Toast.makeText(FeedBackActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
                    return;
                }
                imageId.add(postImageResponse);
                latch.countDown();
            }
        });
    }

    private void getFeedBackResult() {
        String content = binding.editContent.getText().toString();
        String ids = processIds();
        GetFeedBackRequest request = new GetFeedBackRequest(content, ids);


        if (TextUtils.isEmpty(content)) {
            alertDialogView.alertHitInfo("", "意见不能为空");
            return;
        }
        if (photoList.size() == 0) {
            alertDialogView.alertHitInfo("", "图片不能为空");
            return;
        }


        feedBackViewModel.getFeedBackResult(request).observe(this, new Observer<GetFeedBackResponse>() {
            @Override
            public void onChanged(@Nullable GetFeedBackResponse getFeedBackResponse) {
                closeLoadingProgressBar();
                if (null == getFeedBackResponse) {
                    Toast.makeText(FeedBackActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println(getFeedBackResponse.toString());
                if ("success".equals(getFeedBackResponse.getStatus())) {
                    alertDialogView.alertHitInfo("", "反馈提交成功");
//                    Toast.makeText(FeedBackActivity.this, "已经反馈提交成功", Toast.LENGTH_LONG).show();
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

    /**
     * 初始化数据
     */
    private void initData() {
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
        int limit = 9 - photoList.size();
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

        @Override
        public void OnImageClickListener(View view, TImage image, int position) {
            if (null == image || "".equals(image.getOriginalPath())) {
                takeImages(view);
            } else {
                Log.i(TAG, String.format("Path: %s", image.getOriginalPath()));
                showPopWindow(position);
            }
        }
    };


    /**
     * 图片弹出层
     * ############################################################################
     */

    public void showPopWindow(int position) {
        popupWindowForImage = new PopupWindowForImage(FeedBackActivity.this, photoList, position, popImageListener);
        popupWindowForImage.showAtLocation(binding.lineRoot, Gravity.CENTER, 0, 0);
    }

    /**
     * 弹出层点击回调
     */
    public OnImageClickListener popImageListener = new OnImageClickListener() {

        @Override
        public void OnImageClickListener(View view, TImage image) {
            Log.i(TAG, String.format("Path: %s", image.getOriginalPath()));
            Log.i("TAG", "====================删除的点击事件");
            popupWindowForImage.dismiss();
            showImg();
        }

        @Override
        public void OnImageClickListener(View view, TImage image, int position) {
            Log.i(TAG, "===================什么时候执行的" + image.getOriginalPath());
//            popupWindowForImage.dismiss();
//            showImg();
        }
    };


    /**
     * ############################################################################
     * 图片弹出层结束
     */


}
