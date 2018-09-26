package com.zhjy.cultural.services.patrol.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.util.DebugLog;

import java.util.List;


/**
 * Real base core fragment for whole project
 * Created by wpy on 2017/7/22.
 */

public abstract class BaseCoreFragment extends Fragment {
    private View rootView;

    private boolean isActive;

    public BaseCoreFragment() {
        super();
        setArguments(new Bundle());
    }

    /**
     * 这个不会由Fragment自身的生命周期发起 而是由 {@link android.support.v4.app.FragmentPagerAdapter}
     * 和 {@link android.support.v4.app.FragmentStatePagerAdapter} 来调用，所以一般情况下，只有在ViewPager
     * 中才会有
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DebugLog.i(getClass().getSimpleName() + "------enter------  userVisible:" + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugLog.i(getClass().getSimpleName() + "------enter------");

        if (rootView == null) {
            setRootView(createView(inflater, container, savedInstanceState));

            executeOnceAfterCreateView();
        }

        if (rootView.getParent() != null)
            ((ViewGroup) rootView.getParent()).removeView(rootView);

        return rootView;
    }

    /**
     * 在Fragment show hide 的时候被调用，但是第一次不会调用，可以查看{@link android.support.v4.app.FragmentManager}
     * 源码，了解调用时机
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (getActivity() instanceof BaseCoreActivity) {
            isActive = !hidden && ((BaseCoreActivity) getActivity()).isActive();
        } else
            isActive = !hidden;

        List<Fragment> frgs = getChildFragmentManager().getFragments();
        if (frgs != null)
            for (Fragment item : frgs) {
                if (item != null && item.isAdded()) {
                    //两个有一个为隐藏 则认为是隐藏模式的
                    item.onHiddenChanged(hidden || item.isHidden());
                }
            }

        DebugLog.i(getClass().getSimpleName() + "------enter------ hidden:" + hidden);
    }

    /**
     * 只执行一次
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void executeOnceAfterCreateView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onStart() {
        super.onStart();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onResume() {
        super.onResume();

        isActive = isVisible();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onPause() {
        isActive = false;
        super.onPause();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onStop() {
        super.onStop();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DebugLog.i(getClass().getSimpleName() + "------enter------");
    }

    protected void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public View getRootView() {
        return rootView;
    }

    public boolean isAlive() {
        return isAdded() && !getActivity().isFinishing();
    }

    public boolean isActive() {
        return isActive;
    }
}
