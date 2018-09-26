package com.zhjy.cultural.services.patrol.routing.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.routing.RoutingSearchPresenter;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;

import java.util.concurrent.TimeUnit;

/**
 * 巡检搜索页面
 */
public class RoutingSearchActivity extends BaseActivity<RoutingSearchPresenter.RoutingSearchUI, RoutingSearchPresenter> implements RoutingSearchPresenter.RoutingSearchUI {


    @Override
    protected RoutingSearchPresenter.RoutingSearchUI createUI() {
        return this;
    }

    @Override
    protected RoutingSearchPresenter createPresenter() {
        return new RoutingSearchPresenter();
    }

    @Override
    protected void initViews() {
        RxView.clicks(getblack()).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            finish();
        });

        RxView.clicks(getSearch()).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().Search();
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_routing_search;

    }


    @Override
    public ImageView getblack() {
        return getUI().finder().find(R.id.blackiv);
    }

    @Override
    public TextView getSearch() {
        return getUI().finder().find(R.id.search_tv);
    }

    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.search_rv);
    }

    @Override
    public SearchEditText getsearchedit() {
        return getUI().finder().find(R.id.search_edit);
    }

    @Override
    public TextView getnodate() {
        return getUI().finder().find(R.id.search_tv_nodate);
    }
}
