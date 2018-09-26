package com.zhjy.cultural.services.patrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;

import java.io.Serializable;
import java.util.List;

/**
 * 异常待办
 */
public class HandleMatterActivity extends BaseActivity<HandleMatterPreenter.HandleMatterUI, HandleMatterPreenter> implements HandleMatterPreenter.HandleMatterUI {

    @Override
    protected HandleMatterPreenter.HandleMatterUI createUI() {
        return this;
    }

    @Override
    protected HandleMatterPreenter createPresenter() {
        return new HandleMatterPreenter();
    }

    @Override
    protected void initViews() {


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_handle_matter;
    }

    @Override
    public RecyclerView getRecyclerview() {
        return getUI().finder().find(R.id.handl_matter_rv);
    }

    @Override
    public TextView getnodate() {
        return getUI().finder().find(R.id.handl_matter_tv_nodate);
    }
}
