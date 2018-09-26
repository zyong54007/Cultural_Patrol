package com.zhjy.cultural.services.patrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.ui.view.PasswordView;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.util.concurrent.TimeUnit;

/**
 * 安装序列号
 */
public class InstallActivity extends BaseActivity<InstallPresenter.installUI, InstallPresenter> implements InstallPresenter.installUI {
    @Override
    public int getContentLayout() {
        return R.layout.activity_install;
    }

    @Override
    protected InstallPresenter.installUI createUI() {
        return this;
    }

    @Override
    protected InstallPresenter createPresenter() {
        return new InstallPresenter();
    }

    @Override
    protected void initViews() {

        RxView.clicks(getUI().finder().find(R.id.instali_btn)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().Commit();
        });
    }

    @Override
    public EditText getinstaliseria() {
        return getUI().finder().editText(R.id.instali_seria);
    }

//    private PasswordView passwordView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_install);
//        passwordView = findViewById(R.id.instali_code);
//        passwordView.setPasswordListener(this);
//    }
//
//
//    @Override
//    public void passwordChange(String changeText) {
//
//    }
//
//    @Override
//    public void passwordComplete() {
//        String serializecode = passwordView.getpasswordstr();
//        SPUtils.set(Contant.ISLOGIN, true);
//
//        startActivity(new Intent(this, LoginActivity.class));
//        finish();
//    }
//
//    @Override
//    public void keyEnterPress(String password, boolean isComplete) {
//
//    }
}
