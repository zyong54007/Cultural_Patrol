package com.zhjy.cultural.services.patrol.ui.presenter;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.text.style.EasyEditSpan;
import android.util.DebugUtils;
import android.util.Log;
import android.widget.CheckBox;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.ui.MainViewModel;
import com.zhjy.cultural.services.patrol.util.SPUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

/**
 * 登录
 */
public class LoginPresenter extends ActPresenter<LoginPresenter.LoginUI> {

    public interface LoginUI extends GEMUI {

    }

    @Override
    public void onUIReady(MVPActivity activity, LoginUI ui) {

        super.onUIReady(activity, ui);

//        mainViewModel = ViewModelProviders.of(this, getActivity().viewModelFactory()).get(MainViewModel.class);


    }


    /**
     * 登录
     */
    public void Login() {


//        String username = getUI().finder().editText(R.id.login_user).getText().toString().trim();
//        String password = getUI().finder().editText(R.id.login_pass).getText().toString().trim();
//        if (TextUtils.isEmpty(username)) {
//            ToastUtil.showToastMsg("用户名不能为空");
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//            ToastUtil.showToastMsg("密码不能为空");
//            return;
//        }
//
//        CheckBox checkBox = getUI().finder().find(R.id.login_is_pass);
//
//
//        String Secret_password = stringToAscii(password);
//
//        new GRetrofit()
//                .request(GemService.class)
//                .Login("http://wwgl.hdggwh.com/wwgl/loginInterface?username=outers&pwd=69,70,71,72,73,74,")
//                .compose(GRetrofit.observeOnMainThread(getUI()))
//                .subscribe(EasySubscriber.create(b -> {
//                    String status = b.getStatus();
//                    if (status.equals("success")) {
//                        if (checkBox.isChecked()) {
//                            SPUtils.set(Contant.USERNAME, username);
//                            SPUtils.set(Contant.PASSWORD, password);
//                        }
//                        String jsessionid = b.getJsessionid();
//
////                        Log.i("TAG", "=====userid===" + jsessionid);
//                        SPUtils.set("userid", jsessionid);
//                        Log.i("TAG", "==========seesionid====" + jsessionid);
////                        SPUtils.set(Contant.ISLOGIN, true);
//                        AppContext.setJsessionid(b.getJsessionid());
////                        URLs.setSeesionid(b.getJsessionid());
//
//                        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
//                        getActivity().finish();
//
//
//                    } else {
//                        ToastUtil.showToastMsg("用户或密码错误");
//                        return;
//                    }
//
//                }));


    }

//    private MainViewModel mainViewModel;


//    private void inserData(String seesionid) {
////        String userName = binding.editUsername.getText().toString();
//        String userName = getUI().finder().editText(R.id.login_user).getText().toString().trim();
//        String passWord = "";
////        if (binding.check.isChecked())
////            passWord = binding.editPass.getText().toString();
//        CultureUser cultureUser = new CultureUser("1", userName, "", passWord, seesionid, "");
//        mainViewModel.insertUser(cultureUser);
//    }


    /**
     * 找回密码
     */
    public void findpass() {
        new MaterialDialog.Builder(getActivity())
                .title("点击确定拨打电话")
                .titleColorRes(R.color.org_bg_normal)
                .content("15999999999")
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
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Uri uri = Uri.parse("tel:" + "15999999999");
                                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                                getActivity().startActivity(intent);
                                return;
                            }
                        } else if (which == DialogAction.NEGATIVE) {
                            //取消
                        }
                    }
                })
                .show();
    }

    /**
     * 密码编码的方法
     *
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sbu.append((int) chars[i] + 20).append(",");
        }
        return sbu.toString();
    }

}
