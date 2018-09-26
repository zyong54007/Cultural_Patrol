package com.zhjy.cultural.services.patrol.ui.setup.information;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityMyInformationBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class MyInformationActivity extends AacBaseActivity<ActivityMyInformationBinding> {

    InformationViewModel informationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        informationViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InformationViewModel.class);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_information;
    }

    private void initView(){
        initListener();
    };

    private void initListener(){
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
