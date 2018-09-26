package com.zhjy.cultural.services.patrol.mvp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.core.BaseCoreActivity;
import com.zhjy.cultural.services.patrol.util.StatusBarUtil;


/**
 * Created by wpy on 2017/7/22.
 */

/**
 * Base类的基类
 *
 * @param <V>
 * @param <P>
 */
public abstract class MVPActivity<V extends GEMUI, P extends ActPresenter<V>> extends BaseCoreActivity {
    private P presenter;

    private V ui;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ui = createUI();

        setContentView(ui.getContentLayout());
        StatusBarUtil.setColor(this, getResources().getColor(R.color.color_844949), 0);


        presenter = createPresenter();
        initViews();

        presenter.onUIReady(this, getUI());

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        } else {
            hideStatus();
        }
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            View decorView = getWindow().getDecorView();
//
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            decorView.setSystemUiVisibility(option);
//
////        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白
//            getWindow().setStatusBarColor(getResources().getColor(R.color.color_statusbar_bg));
//        } else {
//            hideStatus();
//        }
        //GTPush initialize

//        PushManager.getInstance().initialize(this.getApplicationContext(), wallet.gem.com.GTPushService.class);
//        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GTPushIntentService.class);
    }

    public abstract void hideStatus();

    protected abstract V createUI();

    protected abstract P createPresenter();

    public V getUI() {
        return ui;
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract void initViews();


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
