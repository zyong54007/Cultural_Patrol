package com.zhjy.cultural.services.patrol.ui.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityCultureListBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;


public class CultureListActivity extends AacBaseActivity<ActivityCultureListBinding> {

    private String TAG = "CultureListActivity";

    private CultureListViewModel cultureListViewModel;

    private CultureListAdapter adapter;;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_culture_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        cultureListViewModel = ViewModelProviders.of(this, viewModelFactory()).get(CultureListViewModel.class);
        initView();
        loadData();
    }

    private void initView(){
        adapter = new CultureListAdapter(getSupportFragmentManager());
        binding.cultureList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cultureList.setAdapter(adapter);
    }

    private void loadData(){
        cultureListViewModel.setRequest(0,3);
        cultureListViewModel.getLiveData().observe(this, new Observer<GetCultureListResponse>() {
            @Override
            public void onChanged(@Nullable GetCultureListResponse getCultureListResponse) {
                Log.i(TAG,String.valueOf(getCultureListResponse.getPagecount()));
                updateListView(getCultureListResponse);
            }
        });
    }

    private void updateListView(GetCultureListResponse response) {
        adapter.notifyDataSetChanged(response);
    }
}
