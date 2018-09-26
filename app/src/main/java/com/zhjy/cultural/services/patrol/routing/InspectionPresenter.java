package com.zhjy.cultural.services.patrol.routing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.mine.ui.SettingFragment;
import com.zhjy.cultural.services.patrol.mvp.FragmentPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.routing.UI.RoutingSearchActivity;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionErrorFragment;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionFragment;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionRecordFragment;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListFragment;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMap;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceActivity;

public class InspectionPresenter extends FragmentPresenter<InspectionPresenter.InspectionUI> {


    private int checkedId = 0;


    private InspectionRecordFragment mInspectionRecordFragment = new InspectionRecordFragment();
    private InspectionErrorFragment mInspectionErrorFragment = new InspectionErrorFragment();
    private FragmentManager fragmentManager;


    public interface InspectionUI extends GEMUI {
    }





    /**
     * 设置页面为巡检记录
     */
    public void setFragment() {
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.inspec_fl, mInspectionRecordFragment);
        fragmentTransaction1.commit();
    }

    /**
     * 设置页面为异常记录
     */
    public void setFragmentError() {
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.inspec_fl, mInspectionErrorFragment);
        fragmentTransaction1.commit();
    }


    /**
     * 初始化为巡检记录页面
     */
    public void initFragment() {
        fragmentManager = getFragment().getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.inspec_fl, mInspectionRecordFragment);
        fragmentTransaction.commit();
    }


    /**
     * 跳转搜索页面
     */
    public void startSearch() {
        Intent intent = new Intent(getActivity(), RoutingSearchActivity.class);
        getActivity().startActivity(intent);
    }


    /**
     * 跳转添加巡检页面
     */
    public void StartAdd() {
        Intent intent = new Intent(getActivity(), TreasuresChoiceActivity.class);
        getActivity().startActivity(intent);
    }



}
