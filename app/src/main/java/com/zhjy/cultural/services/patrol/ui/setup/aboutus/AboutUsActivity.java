package com.zhjy.cultural.services.patrol.ui.setup.aboutus;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.gyf.barlibrary.ImmersionBar;
import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.mine.presenter.AboutUsPerenter;

import java.util.concurrent.TimeUnit;

//AacBaseActivity<ActivityAboutUsBinding>

public class AboutUsActivity extends BaseActivity<AboutUsPerenter.AboutUsUI, AboutUsPerenter> implements AboutUsPerenter.AboutUsUI {

//    AboutUsViewModel aboutUsViewModel;

    public static AboutUsActivity aboutUsActivity;

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_about_us;
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        aboutUsActivity = this;
//        InjectHelp.appComponent().inject(this);
//        aboutUsViewModel = ViewModelProviders.of(this, viewModelFactory()).get(AboutUsViewModel.class);
//        VersionFileProvider ss= new VersionFileProvider();
//        initView();
//    }
//
////    private void initView(){
//
//        initListener();
//    }

//    private void initListener(){
//        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }

    public void updateVersion() {
        VersionParams.Builder builder = new VersionParams.Builder()
                .setRequestUrl("https://www.baidu.com")
                .setService(UpdateService.class);

        builder.setDownloadAPKPath("/storage/emulated/0/");

        CustomVersionDialogActivity.customVersionDialogIndex = 2;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isCustomDownloading = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isForceUpdate = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        builder.setOnlyDownload(false);

        builder.setShowNotification(true);

        builder.setShowDownloadingDialog(true);

        AllenChecker.startVersionCheck(getApplication(), builder.build());

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_about_us;
    }


    @Override
    protected AboutUsPerenter.AboutUsUI createUI() {
        return this;
    }

    @Override
    protected AboutUsPerenter createPresenter() {
        return new AboutUsPerenter();
    }

    @Override
    protected void initViews() {
        aboutUsActivity = this;
        ImmersionBar.with(this).init();


        RxView.clicks(getUI().finder().button(R.id.btn_update)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            updateVersion();
//            getPresenter().

        });


    }


//    public void cancleUpdate(View view){
//        AllenChecker.cancelMission();
//    }

}
