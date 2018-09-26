package com.zhjy.cultural.services.patrol;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.MoreNoMatterPresenter;
import com.zhjy.cultural.services.patrol.base.BaseActivity;

/**
 * 巡检提醒更多
 */
public class MoreNoMatterActivity extends BaseActivity<MoreNoMatterPresenter.MoreNoMatterUI, MoreNoMatterPresenter> implements MoreNoMatterPresenter.MoreNoMatterUI {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_more_no_matter);
//    }

    @Override
    protected MoreNoMatterPresenter.MoreNoMatterUI createUI() {
        return this;
    }

    @Override
    protected MoreNoMatterPresenter createPresenter() {
        return new MoreNoMatterPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_more_no_matter;
    }

    @Override
    public RecyclerView getRecyclerview() {
        return getUI().finder().find(R.id.more_nomatter_recycler);
    }

    @Override
    public TextView getnodate() {
        return getUI().finder().find(R.id.more_tv_nodate);
    }

//    @Override
//    public SwipeRefreshLayout getSwipeRefreshLayout() {
//        return getUI().finder().find(R.id.more_swipeLayout);
//    }
}
