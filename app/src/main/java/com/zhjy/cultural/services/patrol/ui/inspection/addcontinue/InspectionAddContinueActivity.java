package com.zhjy.cultural.services.patrol.ui.inspection.addcontinue;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityInspectionAddContinueBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class InspectionAddContinueActivity extends AacBaseActivity<ActivityInspectionAddContinueBinding> {

    InspectionAddContinueViewModel inspectionAddContinueViewModel;

    private int recordId ;

    private int wwId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_add_continue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        InjectHelp.appComponent().inject(this);
        inspectionAddContinueViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionAddContinueViewModel.class);
        Intent intent = getIntent();
        wwId = intent.getIntExtra("wwId",0);
        recordId = intent.getIntExtra("recordId",0);
        initTreasuresData();
    }

    private void initTreasuresData(){
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(wwId);
        inspectionAddContinueViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresInfoResponse response) {
                updateTreasuresView(response);
            }
        });
    }

    private void updateTreasuresView(GetTreasuresInfoResponse response){
        if(null == response){
            Toast.makeText(this,R.string.network_error,Toast.LENGTH_LONG).show();
            return;
        }
        binding.setTreasuresBean(response);
        binding.setImageUrlPath(Constants.BASEURL + response.getPicturePath());
    }
    private void postData(){

    }

    public void stepToNext(View view){
        finish();
    }
}
