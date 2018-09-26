package com.zhjy.cultural.services.patrol.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionFragment;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListFragment;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMap;
import com.zhjy.cultural.services.patrol.mine.ui.SettingFragment;

public class MainPresenter extends ActPresenter<MainPresenter.MainUI> {

    private int checkedId = 0;

    public interface MainUI extends GEMUI {

        RadioGroup getTabLayout();

//        void showShift(boolean b);

//        NotificationButton getRedPointVIew();
    }


    @Override
    public void onUIReady(MVPActivity activity, MainUI ui) {
        super.onUIReady(activity, ui);

        getUI().getTabLayout().setOnCheckedChangeListener(
                (group, checkedId) -> {
                    if (checkedId == this.checkedId) {
                        return;
                    }
                    this.checkedId = checkedId;
                    getUI().getTabLayout().setVisibility(View.VISIBLE);
                    switch (checkedId) {
                        case R.id.main_home_tab:   //首页
                            showFragment(MainListFragment.class, null);
                            break;
                        case R.id.main_market_tab:   //导行
                            showFragment(CulturalRelicsMap.class, null);
                            break;
                        case R.id.main_dapp_tab:      //巡检
                            showFragment(InspectionFragment.class, null);
                            break;
                        case R.id.main_dapp_tab_test:      //我的
                            showFragment(SettingFragment.class, null);
                            break;

                    }
                });
        getUI().getTabLayout().check(R.id.main_home_tab);

    }

    /**
     * Fragement的显示与隐藏
     *
     * @param clazz
     * @param bundle
     */
    public void showFragment(Class clazz, Bundle bundle) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.getFragments() != null) {
            for (Fragment frg : manager.getFragments()) {
                if (frg != null) {
                    transaction.hide(frg);
                }
            }
        }

        Fragment target = getActivity().instanceFragment(clazz.getName(), bundle, clazz.getName());
        if (!target.isAdded())
            transaction.add(R.id.main_container, target, clazz.getName());

        transaction.show(target).commitAllowingStateLoss();
    }

}
