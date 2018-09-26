package com.zhjy.cultural.services.patrol.ui.setup.personaldata;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityPersonalDataBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class PersonalDataActivity extends AacBaseActivity<ActivityPersonalDataBinding> {

    PersonalDataViewModel personalDataViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        personalDataViewModel = ViewModelProviders.of(this, viewModelFactory()).get(PersonalDataViewModel.class);

        initListener();
    }

    private void initListener(){
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
