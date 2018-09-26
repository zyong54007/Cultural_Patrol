package com.zhjy.cultural.services.patrol.ui.inspection.addresult;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostRecordSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostRecordSaveResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionAddResultBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class InspectionAddResultActivity extends AacBaseActivity<ActivityInspectionAddResultBinding> {

    InspectionAddResultViewModel inspectionAddResultViewModel;

    private int wwId;

    private PostRecordSaveRequest mInspectionBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_add_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        InjectHelp.appComponent().inject(this);
        inspectionAddResultViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionAddResultViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId", 0);
        mInspectionBean = (PostRecordSaveRequest) intent.getSerializableExtra("mInspectionBean");
        initListen();
        initData();
    }

    public void initData() {
        getTreasuresInfo();
    }

    public void getTreasuresInfo() {
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);
        inspectionAddResultViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
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
//        binding.setImageUrlPath(Constants.BASEURL + response.getPicturePath());
        String picturePath = response.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + response.getPicturePath());
            } else {
                binding.setImageUrlPath(Constants.BASEIMGURL + response.getPicturePath());
            }
        }

    }

    public void postRecordSaveResult() {
        if (0 == mInspectionBean.getStatus()) {
            Toast.makeText(InspectionAddResultActivity.this, "请选择巡检结果！", Toast.LENGTH_LONG).show();
            return;
        }
        String content = binding.editContent.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(InspectionAddResultActivity.this, "请输入巡检描述信息！", Toast.LENGTH_LONG).show();
            return;
        }
        mInspectionBean.setStatusDescription(content);
        showLoadingProgressBar();
        inspectionAddResultViewModel.postRecordSaveResult(mInspectionBean).observe(this, new Observer<PostRecordSaveResponse>() {
            @Override
            public void onChanged(@Nullable PostRecordSaveResponse response) {
                closeLoadingProgressBar();
                updateSaveResultView(response);
            }
        });
    }

    public void updateSaveResultView(PostRecordSaveResponse response) {
        String msg = response.getMsg();
        String status = response.getStatus();

        Log.i("TAG", status + "重大bug 巡检提交失败=============" + msg);
        if (null == response) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        if ("success".equals(response.getStatus())) {
            finish();
            return;
        }
        Toast.makeText(this, "巡检信息提交失败！", Toast.LENGTH_LONG).show();
    }

    public void setLineAOneYesCheck() {
        binding.lineAOneYes.setBackgroundResource(R.drawable.inspection_text_bg_a);
        binding.imgAOneYes.setImageResource(R.mipmap.inspection_img_y_a);
        binding.textAOneYes.setTextColor(Color.parseColor("#ffffff"));

        binding.lineAOneNo.setBackgroundResource(R.drawable.inspection_text_bg_d);
        binding.imgAOneNo.setImageResource(R.mipmap.inspection_img_n_b);
        binding.textAOneNo.setTextColor(Color.parseColor("#844949"));

        mInspectionBean.setStatus(1);
    }

    public void setLineAOneNoCheck() {
        binding.lineAOneYes.setBackgroundResource(R.drawable.inspection_text_bg_b);
        binding.imgAOneYes.setImageResource(R.mipmap.inspection_img_y_b);
        binding.textAOneYes.setTextColor(Color.parseColor("#258784"));

        binding.lineAOneNo.setBackgroundResource(R.drawable.inspection_text_bg_c);
        binding.imgAOneNo.setImageResource(R.mipmap.inspection_img_n_a);
        binding.textAOneNo.setTextColor(Color.parseColor("#ffffff"));

        mInspectionBean.setStatus(2);
    }

    public void initListen() {
        binding.lineFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRecordSaveResult();
                //finish();
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
    }

    public void finish(View view) {
        finish();
    }

}
