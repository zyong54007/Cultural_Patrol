package com.zhjy.cultural.services.patrol.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.zhjy.cultural.services.patrol.InstallActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.ActivityStartBinding;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.culture.CultureMainActivity;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.util.List;

public class StartActivity extends AacBaseActivity<ActivityStartBinding> {

    private final int requestCode = 0x10000;
    private final int activityCode = 400;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initPermission();
    }

    private void init() {
        StartViewModel viewModel = ViewModelProviders.of(this, viewModelFactory()).get(StartViewModel.class);
    }

    private void initPermission() {
        AndPermission.with(StartActivity.this)
                .requestCode(requestCode)
                .permission(
                        Permission.LOCATION,
                        Permission.CAMERA,
                        Permission.STORAGE,
                        Permission.PHONE
                )
                .callback(this)
                .start();
    }

    private void gotoNext() {
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SPUtils.get(Contant.INSTALL, false)) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    finish();
                } else {

                    startActivity(new Intent(getContext(), InstallActivity.class));
                    finish();
                    //startActivity(new Intent(getContext(), MainActivity.class));
//                finish();\
                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    @PermissionYes(requestCode)
    private void getPermissionYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        gotoNext();
    }

    @PermissionNo(requestCode)
    private void getPermissionNo(List<String> deniedPermissions) {
        // TODO 申请权限失败。
        AndPermission.defaultSettingDialog(StartActivity.this, activityCode)
                .setTitle("权限申请失败")
                .setMessage("您拒绝了我们必要的一些权限，暂时无法开启应用，请在设置中授权！")
                .setPositiveButton("好，去设置")
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case activityCode: { // 这个400就是上面defineSettingDialog()的第二个参数。
                // 你可以在这里检查你需要的权限是否被允许，并做相应的操作。
                initPermission();
                break;
            }
        }
    }
}