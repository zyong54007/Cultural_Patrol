package com.zhjy.cultural.services.patrol.ui.setup.resetpass;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetUpdatePwdRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetUpdatePwdResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityResetPassBinding;
import com.zhjy.cultural.services.patrol.mine.presenter.ResetPassPersenter;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import org.w3c.dom.Text;

//AacBaseActivity<ActivityResetPassBinding>
public class ResetPassActivity extends AacBaseActivity<ActivityResetPassBinding> {

    ResetPassViewModel resetPassViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        resetPassViewModel = ViewModelProviders.of(this, viewModelFactory()).get(ResetPassViewModel.class);
        initVIew();
    }


    private void initVIew() {

        initListener();
    }

    private void initListener() {

        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePwdInfo();
            }
        });
    }

    private void updatePwdInfo() {
        binding.editNewPass1.setError(null);
        String oldPass = binding.editOldPass.getText().toString();
        String newPass = binding.editNewPass.getText().toString();
        String newPassA = binding.editNewPass1.getText().toString();


        if (TextUtils.isEmpty(oldPass) && TextUtils.isEmpty(newPassA) && TextUtils.isEmpty(newPassA)) {
            ToastUtil.showToastMsg("密码不能为空");
            return;
        }
        if (newPass.length() < 6 && newPassA.length() < 6) {
            ToastUtil.showToastMsg("新密码长度不能低于6位");
            return;
        }
        if (!newPass.equals(newPassA)) {
            binding.editNewPass1.setError("两次新密码输入不一致");
            return;
        }


        GetUpdatePwdRequest request = new GetUpdatePwdRequest(oldPass, newPass);
        resetPassViewModel.getLoginInfo(request).observe(this, new Observer<GetUpdatePwdResponse>() {
            @Override
            public void onChanged(@Nullable GetUpdatePwdResponse getUpdatePwdResponse) {
                updatePwdInfoHandle(getUpdatePwdResponse);
            }
        });
    }

    private void updatePwdInfoHandle(GetUpdatePwdResponse getUpdatePwdResponse) {
        if (null == getUpdatePwdResponse) {
            Toast.makeText(ResetPassActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        if ("success".equals(getUpdatePwdResponse.getStatus())) {
            Toast.makeText(ResetPassActivity.this, getUpdatePwdResponse.getMsg(), Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(ResetPassActivity.this, getUpdatePwdResponse.getMsg(), Toast.LENGTH_LONG).show();
        }
    }

}
