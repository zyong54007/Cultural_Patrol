package com.zhjy.cultural.services.patrol.core;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import java.util.List;

/**
 * Real base act for whole project
 * Created by wpy on 2017/7/22.
 */

public class BaseCoreActivity extends FragmentActivity {
    private boolean isAlive;

    private boolean isActive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAlive = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        isActive = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        isAlive = false;
        super.onDestroy();
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isAlive() {
        return isAlive && !isFinishing();
    }


    public Fragment instanceFragment(String fName, Bundle bundle, String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            if (fragment.getArguments() != null && bundle != null)
                fragment.getArguments().putAll(bundle);
            return fragment;
        }

        List<Fragment> frgs = getSupportFragmentManager().getFragments();
        if (frgs != null) {
            for (Fragment item : frgs) {
                //这里有可能会有问题，因为多个相同的fragment到这里就会出错
                if (item ==null || !item.getClass().getName().equals(fName))
                    continue;

                if (item.getArguments() != null && bundle != null)
                    item.getArguments().putAll(bundle);
                return item;
            }
        }

        fragment = Fragment.instantiate(this, fName, bundle);

        return fragment;
    }

    public Fragment instanceFragment(String fName, Bundle bundle) {
        return instanceFragment(fName, bundle, fName);
    }
}
