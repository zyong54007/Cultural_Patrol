package com.zhjy.cultural.services.patrol.base;

import android.os.Bundle;

import com.zhjy.cultural.services.patrol.mvp.FragmentPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPFragment;
import com.zhjy.cultural.services.patrol.util.ViewFinder;

public abstract class BaseFragment<V extends GEMUI, P extends FragmentPresenter<V>> extends MVPFragment<V, P>
        implements GEMUI {
    private ViewFinder finder;
    private P presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        finder = new ViewFinder(getActivity());
        presenter = createPresenter();
        initViews();
        presenter.onActivityCreated(savedInstanceState);
    }


    protected abstract void initViews();

    public ViewFinder finder() {
        return finder;
    }

    @Override
    public void showProgress(String title, String content) {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).showProgress(title, content);
    }

    @Override
    public void dismissProgress() {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).dismissProgress();
    }

}