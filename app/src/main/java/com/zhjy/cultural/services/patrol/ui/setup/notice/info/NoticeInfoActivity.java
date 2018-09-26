package com.zhjy.cultural.services.patrol.ui.setup.notice.info;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityNoticeInfoBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class NoticeInfoActivity extends AacBaseActivity<ActivityNoticeInfoBinding> {

    NoticeInfoViewModel noticeInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        noticeInfoViewModel = ViewModelProviders.of(this, viewModelFactory()).get(NoticeInfoViewModel.class);

        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_info;
    }

    private void initView(){

        initListener();
    }

    private void initListener(){
        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
