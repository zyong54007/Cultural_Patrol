package com.zhjy.cultural.services.patrol;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.AlertDialogView;
import com.zhjy.cultural.services.patrol.bean.LoginEntity;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.response.StatusResponse;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.util.SPUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import rx.Observer;

public class InstallPresenter extends ActPresenter<InstallPresenter.installUI> {
    public interface installUI extends GEMUI {

        EditText getinstaliseria();

    }


    @Override
    public void onUIReady(MVPActivity activity, installUI ui) {
        super.onUIReady(activity, ui);
        alertDialogView = new AlertDialogView(getActivity());
    }

    private AlertDialogView alertDialogView;

    public void Commit() {


        String seria = getUI().getinstaliseria().getText().toString().trim();
        if (TextUtils.isEmpty(seria) || seria.length() != 8) {

            alertDialogView.alertHitInfo("", "输入正确的安装序列号");
//            ToastUtil.showToastMsg("");
            return;
        }
        new GRetrofit().request(GemService.class).Instll(seria)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<StatusResponse<LoginEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("TAG", "错误=================" + e.getMessage());
                    }

                    @Override
                    public void onNext(StatusResponse<LoginEntity> loginEntityStatusResponse) {
                        String status = loginEntityStatusResponse.getStatus();


                        if (status.equals("y")) {
                            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                            SPUtils.set(Contant.INSTALL, true);
                            getActivity().finish();
                        } else {
//                        ToastUtil.showToastMsg("输入正确的安装序列号");
                            alertDialogView.alertHitInfo("", "输入正确的安装序列号");

                        }


                    }
                });
    }


//    EasySubscriber.create(b -> {
//        String status = b.getStatus();
//        if (status.equals("y")) {
//            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
//            SPUtils.set(Contant.INSTALL, true);
//            getActivity().finish();
//        } else {
////                        ToastUtil.showToastMsg("输入正确的安装序列号");
//            alertDialogView.alertHitInfo("", "输入正确的安装序列号");
//
//        }
//    })


}
