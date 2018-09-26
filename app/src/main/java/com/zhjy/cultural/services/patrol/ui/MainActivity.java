package com.zhjy.cultural.services.patrol.ui;

import android.os.Handler;
import android.os.Message;
import android.widget.RadioGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.zhjy.cultural.services.patrol.Main.MainPresenter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.util.ToastUtil;
//AacBaseActivity<ActivityMainBinding>

public class MainActivity extends BaseActivity<MainPresenter.MainUI, MainPresenter> implements MainPresenter.MainUI {


    private static boolean isExit = false;  // 标识是否退出
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    private static final MainActivity instance = new MainActivity();


    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public RadioGroup getTabLayout() {
        return getUI().finder().find(R.id.main_tab);
    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter.MainUI createUI() {
        return this;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initViews() {

        ImmersionBar.with(this).init();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {


        if (!isExit) {
            isExit = true;
            ToastUtil.toastLong("再按一次退出文物巡检app");
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
//            this.finish();
            System.exit(0);
        }
    }


}