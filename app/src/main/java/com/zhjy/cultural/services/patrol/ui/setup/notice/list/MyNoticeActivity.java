package com.zhjy.cultural.services.patrol.ui.setup.notice.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.zhjy.cultural.services.patrol.MyNoticePresenter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;


public class MyNoticeActivity extends BaseActivity<MyNoticePresenter.MyNoticeUI, MyNoticePresenter> implements MyNoticePresenter.MyNoticeUI {

    @Override
    public int getContentLayout() {
        return R.layout.activity_my_notice;
    }

    @Override
    protected MyNoticePresenter.MyNoticeUI createUI() {
        return this;
    }

    @Override
    protected MyNoticePresenter createPresenter() {
        return new MyNoticePresenter();
    }

    @Override
    protected void initViews() {

    }


    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.recycler_list);
    }

    @Override
    public SwipeRefreshLayout getswiprefreshlayout() {
        return getUI().finder().find(R.id.swipeRefreshLayout);
    }
}


