package com.zhjy.cultural.services.patrol.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.util.ToastUtil;
import com.zhjy.cultural.services.patrol.util.ViewFinder;

import java.util.List;


public abstract class BaseActivity<V extends GEMUI, P extends ActPresenter<V>> extends MVPActivity<V, P> implements GEMUI {
    private ViewFinder finder;

    private Dialog dialog;

//    private int dialogRef;
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void hideStatus() {
        if (finder == null)    //此处做判空处理
            finder = new ViewFinder(this);
        if (finder.find(R.id.item_head) != null)
            finder.find(R.id.item_head).setVisibility(View.GONE);
    }

    @Override
    public ViewFinder finder() {
        if (finder == null)
            finder = new ViewFinder(this);

        return finder;
    }

    @Override
    public void showProgress(String title, String content) {
//        if (!isAlive())
//            return;
//
//        dialogRef++;
//
//        if (dialog != null && dialog.isShowing())
//            return;
//
//
//        if (dialog == null) {
//            dialog = new Dialog(this, R.style.loading_dialog);
//            dialog.setContentView(R.layout.dialog_loading);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setCancelable(false);
//        }
//
//        if (!TextUtils.isEmpty(title))
//            dialog.setTitle(title);
//        if (!TextUtils.isEmpty(content))
//            ((TextView) dialog.findViewById(R.id.loading_hint_text)).setText(content);
//
//
//        dialog.show();
    }

    @Override
    public void dismissProgress() {
        if (isAlive() && dialog != null)
            dialog.dismiss();

        dialog = null;
    }


    /**
     * q
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }


    /**
     * 请求权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        switch (requestCode) {
//            case Constant.REQ_CODE_PERMISSION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED&&permissions[0].equals(Manifest.permission.CAMERA)) {
//                    EventBus.getDefault().post(new ScanEvent());
//                }
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED&&permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    EventBus.getDefault().post(new MassiteEvent());
//                }
//                return;
//            }
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancelToast();
//        PopupController.SingleTon.getInstance().wipe();
//        DialogController.SingleTon.getInstance().wipe();
    }

}

