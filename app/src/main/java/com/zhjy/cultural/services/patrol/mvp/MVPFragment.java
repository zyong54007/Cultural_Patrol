package com.zhjy.cultural.services.patrol.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.core.BaseCoreFragment;
import com.zhjy.cultural.services.patrol.util.Assert;
import com.zhjy.cultural.services.patrol.util.ToastUtil;


/**
 * Created by wpy on 2017/7/22.
 */

public abstract class MVPFragment<V extends GEMUI, P extends FragmentPresenter<V>> extends BaseCoreFragment {
    private P presenter;

    private V ui;

    public P getPresenter() {
        return presenter;
    }

    public V getUI() {
        return ui;
    }

    protected abstract P createPresenter();

    protected abstract V createUI();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (presenter == null)
            presenter = createPresenter();

        presenter.onAttach(context, this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void setRootView(View rootView) {
        super.setRootView(rootView);
    }

    @Override
    protected void executeOnceAfterCreateView() {
        Assert.judge(getActivity() instanceof MVPActivity, "Please extend MVPActivity");
        presenter.onUIReady((MVPActivity) getActivity(), getUI());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        presenter.onActivityCreated(savedInstanceState);
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ToastUtil.cancelToast();
        presenter.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (presenter != null)
            presenter.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ui = createUI();

        return inflater.inflate(ui.getContentLayout(), container, false);
    }
}
