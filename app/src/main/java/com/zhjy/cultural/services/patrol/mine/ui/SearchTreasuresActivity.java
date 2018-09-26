package com.zhjy.cultural.services.patrol.mine.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.base.BaseFragment;
import com.zhjy.cultural.services.patrol.mine.presenter.SearchTreasuresPresenter;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 我的文物  搜索页面
 */
public class SearchTreasuresActivity extends BaseActivity<SearchTreasuresPresenter.SearchTreasuresUI, SearchTreasuresPresenter> implements SearchTreasuresPresenter.SearchTreasuresUI {


    @Override
    protected SearchTreasuresPresenter.SearchTreasuresUI createUI() {
        return this;
    }

    @Override
    protected SearchTreasuresPresenter createPresenter() {
        return new SearchTreasuresPresenter();
    }

    @Override
    protected void initViews() {

        RxView.clicks(getUI().finder().find(R.id.blackiv)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {

            finish();
        });


        RxView.clicks(getUI().finder().find(R.id.search_tv)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().Search();
        });


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search_treasures;
    }

    @Override
    public SearchEditText getsearchedit() {
        return getUI().finder().find(R.id.search_edit);
    }

    @Override
    public TextView getnodate() {
        return getUI().finder().find(R.id.search_tv_nodate);
    }

    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.search_rv);
    }
}
