package com.zhjy.cultural.services.patrol.mine.ui;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.mine.presenter.SettingPresenter;

import java.util.concurrent.TimeUnit;
////import com.zhjy.cultural.services.patrol.databinding.FragmentMainSettingBinding;
//import com.zhjy.cultural.services.patrol.ui.LoginActivity;
//import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
//import com.zhjy.cultural.services.patrol.ui.base.BaseFragment;
//import com.zhjy.cultural.services.patrol.ui.setup.aboutus.AboutUsActivity;
//import com.zhjy.cultural.services.patrol.ui.setup.checkversion.CheckVersionActivity;
//import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackActivity;
//import com.zhjy.cultural.services.patrol.ui.setup.information.MyInformationActivity;
//import com.zhjy.cultural.services.patrol.ui.setup.resetpass.ResetPassActivity;
//import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListActivity;

/**
 * Created by jialg on 2018/1/22.
 */
// AacFragment<FragmentMainSettingBinding>

public class SettingFragment extends com.zhjy.cultural.services.patrol.base.BaseFragment<SettingPresenter.SettingUI, SettingPresenter> implements SettingPresenter.SettingUI {


    @Override
    protected void initViews() {

        //意见反馈
        RxView.clicks(getUI().finder().textView(R.id.mine_feedback)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().FeedBack();
        });

//         关于我们
        RxView.clicks(getUI().finder().textView(R.id.mine_about)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().About();
        });

//         修改密码
        RxView.clicks(getUI().finder().textView(R.id.mine_editpass)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().EditPass();
        });
//        我的文物
        RxView.clicks(getUI().finder().textView(R.id.mine_culturalrelic)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().MineCulturalRelic();
        });
//        检测版本
        RxView.clicks(getUI().finder().textView(R.id.mine_testting)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().MineTestting();
        });

        // 退出登录
        RxView.clicks(getUI().finder().textView(R.id.mine_sing_out)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().SingOut();
        });

        getPresenter().init();


    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_main_setting;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected SettingPresenter.SettingUI createUI() {
        return this;
    }


    //    @Override
//    protected int getFragmentLayout() {
//        return R.layout.fragment_main_setting;
//    }
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
//        initListener();
//    }
//
//
//    private void initListener(){
//        binding.imgUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.textUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MyInformationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.textUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineYjfk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, FeedBackActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineGywm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AboutUsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineXgmm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ResetPassActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineWdww.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, TreasuresListActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineJcbb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, CheckVersionActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.btnAppExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().finish();
//                //System.exit(0);
//            }
//        });
//
//    }

}
