package com.zhjy.cultural.services.patrol.ui;


import android.Manifest;
import android.app.Activity;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Intent;
//import android.content.pm.PackageManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
import com.AlertDialogView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetLoginRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetLoginResponse;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.presenter.LoginPresenter;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.LongToIntFunction;
//import com.zhjy.cultural.services.patrol.app.AppContext;
//import com.zhjy.cultural.services.patrol.app.InjectHelp;
//import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetLoginRequest;
//import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
//import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetLoginResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityLoginBinding;
import com.zhjy.cultural.services.patrol.util.ToastUtil;


public class LoginActivity extends AacBaseActivity<ActivityLoginBinding> {

    private static String TAG = "LoginActivity";

    private MainViewModel mainViewModel;
    private AlertDialogView alertDialogView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory()).get(MainViewModel.class);

        initView();
        initListener();
        loadData();
    }

    private void initView() {
        alertDialogView = new AlertDialogView(this);

    }

    private void initListener() {

        binding.textFindPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhoneInfo();
            }
        });


        RxView.clicks( binding.btnLogin).throttleFirst(1000,TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getLoginResult();
        });
//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getLoginResult();
//            }
//        });
    }

    private void loadData() {
        mainViewModel.loadUserData().observe(this, new Observer<CultureUser>() {
            @Override
            public void onChanged(@Nullable CultureUser cultureUser) {
                if (cultureUser != null) {
//                    Log.i(TAG, String.format("数据加载成功: %s %s", cultureUser.username, cultureUser.password));
                    binding.editUsername.setText(cultureUser.username);
                    binding.editPass.setText(cultureUser.password);
                }

            }
        });
    }

    private void inserData(GetLoginResponse getLoginResponse) {
        String userName = binding.editUsername.getText().toString();
        String passWord = "";
        if (binding.check.isChecked())
            passWord = binding.editPass.getText().toString();
        CultureUser cultureUser = new CultureUser("1", userName, "", passWord, getLoginResponse.getJsessionid(), "");
        mainViewModel.insertUser(cultureUser);
        finish();
    }

    private void deleteUser() {
        CultureUser cultureUser = new CultureUser("1", "", "", "", "", "");
        mainViewModel.deleteUser(cultureUser);
        finish();
    }

    private void getLoginResult() {
        String userName = binding.editUsername.getText().toString();
        String passWord = binding.editPass.getText().toString();


        if (TextUtils.isEmpty(userName) ||  TextUtils.isEmpty(passWord)) {
//            ToastUtil.showToastMsg("用户名密码不能为空");
            alertDialogView.alertHitInfo("", "用户名密码不能为空");
            return;
        }

        GetLoginRequest request = new GetLoginRequest(userName, passWord);
        mainViewModel.getLoginInfo(request).observe(this, new Observer<GetLoginResponse>() {
            @Override
            public void onChanged(@Nullable GetLoginResponse getLoginResponse) {
                updateLoginResultHandle(getLoginResponse);
            }
        });
    }

    private void updateLoginResultHandle(GetLoginResponse getLoginResponse) {
//        String jsessionid = getLoginResponse.getJsessionid();
//        Log.i("TAG", getLoginResponse.toString() + "登陆============");

        if (null == getLoginResponse) {

            Toast.makeText(LoginActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        if (getLoginResponse.getFailure()) {
            Toast.makeText(LoginActivity.this, R.string.network_time_error, Toast.LENGTH_LONG).show();
            return;
        }
        if ("success".equals(getLoginResponse.getStatus())) {
            inserData(getLoginResponse);
            AppContext.setJsessionid(getLoginResponse.getJsessionid());
            String username = getLoginResponse.getuserbean().getUsername();
            String mobile = getLoginResponse.getuserbean().getMobile();
            String name = getLoginResponse.getuserbean().getOrganization().getName();
            String iconOpen = getLoginResponse.getuserbean().getOrganization().getIconOpen();
//
            SPUtils.set(Contant.JESSSIONID,getLoginResponse.getJsessionid());
            SPUtils.set(Contant.USERNAME, username);
            SPUtils.set(Contant.USERPHONE, mobile);
            SPUtils.set(Contant.USERGRADE, name);
            SPUtils.set(Contant.USERIMG, iconOpen);

//            Log.i("TAG", "================" + username);
            startToNext();
        } else {
            alertDialogView.alertHitInfo("", getLoginResponse.getMsg());
//            Toast.makeText(this, getLoginResponse.getMsg(), Toast.LENGTH_LONG).show();
        }
    }

    private void startToNext() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showPhoneInfo() {
        new MaterialDialog.Builder(this)
                .title("点击确定拨打电话")
                .titleColorRes(R.color.org_bg_normal)
                .content("010-68944225-8218")
                .contentColorRes(R.color.gray_bg_normal)
                .positiveText("确定")
                .positiveColorRes(R.color.org_bg_normal)
                .negativeText("取消")
                .negativeColorRes(R.color.org_bg_normal)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            //Toast.makeText(LoginActivity.this, "确定", Toast.LENGTH_LONG).show();
                            if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Uri uri = Uri.parse("tel:" + "010-68944225-8218");
                                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                                startActivity(intent);
                                return;
                            }
                        } else if (which == DialogAction.NEGATIVE) {
                            //Toast.makeText(LoginActivity.this, "取消", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .show();
    }

}
