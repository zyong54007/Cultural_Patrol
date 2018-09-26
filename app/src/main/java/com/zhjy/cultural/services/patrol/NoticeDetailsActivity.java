package com.zhjy.cultural.services.patrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zhjy.cultural.services.patrol.base.BaseActivity;

/**
 * 消息详情
 */
public class NoticeDetailsActivity extends BaseActivity<NoticeDetailsPresenter.NoticeDetailsUI, NoticeDetailsPresenter> implements NoticeDetailsPresenter.NoticeDetailsUI {


    @Override
    protected NoticeDetailsPresenter.NoticeDetailsUI createUI() {
        return this;
    }

    @Override
    protected NoticeDetailsPresenter createPresenter() {
        return new NoticeDetailsPresenter();
    }

    @Override
    protected void initViews() {


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_notice_details;
    }

    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.notice_details_rv);
    }

}
