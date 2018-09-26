package com.zhjy.cultural.services.patrol.ui.inspection.add;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostRecordSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionAddBinding;
import com.zhjy.cultural.services.patrol.ui.base.PhotoBaseActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.addresult.InspectionAddResultActivity;
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
 * 巡检信息页面
 */
public class InspectionAddActivity extends PhotoBaseActivity<ActivityInspectionAddBinding> {

    String TAG = "InspectionAddActivity";

    @Inject
    CustomHelper customHelper;

    InspectionAddViewModel inspectionAddViewModel;

    FeedBackAdapter feedBackAdapter;

    private ArrayList<TImage> photoList;

    PopupWindowForImage popupWindowForImage;

    private int wwId;

    private int recordId;

    private PostRecordSaveRequest mInspectionBean;

    private List<PostImageResponse> imageId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_add;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        InjectHelp.appComponent().inject(this);
        inspectionAddViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionAddViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId", 0);
        recordId = intent.getIntExtra("recordId", 0);
        String point_ids = intent.getStringExtra("point_ids");
        mInspectionBean = new PostRecordSaveRequest();
        mInspectionBean.setWwId(wwId);
        mInspectionBean.setRecordId(recordId);
        mInspectionBean.setPoint_ids(String.format("%s%s", point_ids, ","));
        initView();
        initData();
    }


    private void initView() {

        feedBackAdapter = new FeedBackAdapter(getSupportFragmentManager(), imageClickListener);
        binding.recyclerList.setAdapter(feedBackAdapter);

        initListen();
    }

    public void initData() {
        getTreasuresInfo();
        initImageData();
    }

    public void getTreasuresInfo() {
        Log.i("TAG", "======巡检信息页面===" + wwId);
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);
        inspectionAddViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresInfoResponse response) {
                updateTreasuresView(response);
            }
        });
    }

    public void updateTreasuresView(GetTreasuresInfoResponse response) {
        if (null == response) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
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
                .load(url).
//                .placeholder(R.mipmap.default_iv).
//                error(R.mipmap.default_iv).
                into(imageView);
    }

    public void setLineAOneYesCheck() {
        binding.lineAOneYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgAOneYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textAOneYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineAOneNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgAOneNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textAOneNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setRemoval(true);
    }

    public void setLineAOneNoCheck() {
        binding.lineAOneYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgAOneYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textAOneYes.setTextColor(Color.parseColor("#258784"));

        binding.lineAOneNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgAOneNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textAOneNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setRemoval(false);
    }

    public void setLineATwoYesCheck() {
        binding.lineATwoYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgATwoYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textATwoYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineATwoNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgATwoNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textATwoNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setDestroy(true);
    }

    public void setLineATwoNoCheck() {
        binding.lineATwoYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgATwoYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textATwoYes.setTextColor(Color.parseColor("#258784"));

        binding.lineATwoNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgATwoNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textATwoNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setDestroy(false);
    }

    public void setLineAThreeYesCheck() {
        binding.lineAThreeYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgAThreeYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textAThreeYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineAThreeNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgAThreeNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textAThreeNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setFireComplete(true);
    }

    public void setLineAThreeNoCheck() {
        binding.lineAThreeYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgAThreeYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textAThreeYes.setTextColor(Color.parseColor("#258784"));

        binding.lineAThreeNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgAThreeNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textAThreeNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setFireComplete(false);
    }

    public void setLineBOneYesCheck() {
        binding.lineBOneYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgBOneYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textBOneYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineBOneNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgBOneNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textBOneNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setManage(true);
    }

    public void setLineBOneNoCheck() {
        binding.lineBOneYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgBOneYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textBOneYes.setTextColor(Color.parseColor("#258784"));

        binding.lineBOneNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgBOneNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textBOneNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setManage(false);
    }

    public void setLineBTwoYesCheck() {
        binding.lineBTwoYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgBTwoYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textBTwoYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineBTwoNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgBTwoNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textBTwoNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setConstruction(true);
    }

    public void setLineBTwoNoCheck() {
        binding.lineBTwoYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgBTwoYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textBTwoYes.setTextColor(Color.parseColor("#258784"));

        binding.lineBTwoNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgBTwoNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textBTwoNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setConstruction(false);
    }

    private void startUploadThread() {
        if (photoList.size() == 0) {
            Toast.makeText(InspectionAddActivity.this, "请选择巡检图片！", Toast.LENGTH_LONG).show();
            return;
        }
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
        mInspectionBean.setImage_ids("");
        for (TImage image : photoList) {
            List<File> tempFiles = new ArrayList<>();
            tempFiles.add(new File(image.getOriginalPath()));
            postImageData(tempFiles, latch);
        }
        try {
            latch.await();
            startActivityToNext();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void postImageData(List<File> files, CountDownLatch latch) {
        inspectionAddViewModel.postImageResult(files).observe(this, new Observer<PostImageResponse>() {
            @Override
            public void onChanged(@Nullable PostImageResponse postImageResponse) {
                if (null == postImageResponse) {
                    closeLoadingProgressBar();
                    Toast.makeText(InspectionAddActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println(postImageResponse.toString());
                imageId.add(postImageResponse);
                synchronized (this) {
                    String imageIds = "";
                    if (!"".equals(mInspectionBean.getImage_ids())) {
                        imageIds = mInspectionBean.getImage_ids();
                    }
                    imageIds += String.format("%s%s", postImageResponse.getId(), ',');
                    mInspectionBean.setImage_ids(imageIds);
                    latch.countDown();
                    ;
                }
            }
        });
    }


    private void startActivityToNext() {
        closeLoadingProgressBar();
        mInspectionBean.setInsideExplain("");
        mInspectionBean.setOutsideExplain("");
        Intent intent = new Intent(InspectionAddActivity.this, InspectionAddResultActivity.class);
        intent.putExtra("wwId", wwId);
        intent.putExtra("mInspectionBean", mInspectionBean);
        startActivity(intent);
        finish();
    }

    public void initListen() {
        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.lineStepNewx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUploadThread();
            }
        });

        binding.lineAOneYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineAOneYesCheck();
            }
        });

        binding.lineAOneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineAOneNoCheck();
            }
        });

        binding.lineATwoYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineATwoYesCheck();
            }
        });

        binding.lineATwoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineATwoNoCheck();
            }
        });

        binding.lineAThreeYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineAThreeYesCheck();
            }
        });

        binding.lineAThreeNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineAThreeNoCheck();
            }
        });

        binding.lineBOneYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineBOneYesCheck();
            }
        });

        binding.lineBOneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineBOneNoCheck();
            }
        });

        binding.lineBTwoYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineBTwoYesCheck();
            }
        });

        binding.lineBTwoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLineBTwoNoCheck();
            }
        });

    }

    /**
     * 初始化数据
     */
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
//        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
//        builder.setWithOwnGallery(true);
//        getTakePhoto().setTakePhotoOptions(builder.create());
//        configTakePhotoOption(getTakePhoto());

        int limit = 10 - photoList.size();
        customHelper.takeImagesCall(view, getTakePhoto(), limit);
    }

    /**
     * 从相机获取图片
     *
     * @param view
     */
    public void takeImagesCreame(View view) {
//        TakePhoto takePhoto = getTakePhoto();

        customHelper.takeImages(view, getTakePhoto());
    }


    @Override
    public void takeCancel() {
        Log.i("TAG", "============取消");
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {

        super.takeFail(result, msg);
        Log.i("TAG", "============错误" + msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.i("TAG", "============成功");
//        ArrayList<TImage> images = result.getImages();
//        for (int i = 0; i < images.size(); i++) {
//            TImage.FromType fromType1 = images.get(i).getFromType();
//            String compressPath = images.get(i).getCompressPath();
//            TImage.FromType fromType = images.get(i).getFromType();
//            String originalPath = images.get(i).getOriginalPath();
//            Log.i("TAG", "图片信息=========" + compressPath + "==========" + originalPath);
//        }

        showImg(result.getImages());
    }


    private void showImg(ArrayList<TImage> images) {
        feedBackAdapter.clearData();
        if (images.size() > 0)
            photoList.addAll(images);
        if (photoList.size() < 10) {
            ArrayList<TImage> imagesList = new ArrayList<>();
            imagesList.addAll(photoList);
            TImage imageAdd = TImage.of("", TImage.FromType.CAMERA);
            imagesList.add(imageAdd);
            feedBackAdapter.notifyDataSetChanged(imagesList);
            Log.i("TAG", "11111111111====================");
        } else {
            Log.i("TAG", "2222222222222222222====================");
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
            Log.i("TAG", "=========图片点击事件");
            if (null == image || "".equals(image.getOriginalPath())) {
                Log.i("TAG", "=========图片选中事件");


                new ActionSheetDialog(InspectionAddActivity.this)
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
        popupWindowForImage = new PopupWindowForImage(InspectionAddActivity.this, photoList, position, popImageListener);
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

}
