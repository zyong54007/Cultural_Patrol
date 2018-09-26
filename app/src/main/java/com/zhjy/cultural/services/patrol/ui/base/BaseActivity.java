package com.zhjy.cultural.services.patrol.ui.base;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.mapapi.baiduapplication.CustomProgressDialog;
import com.zhjy.cultural.services.patrol.app.AppContext;


public abstract class BaseActivity extends AppCompatActivity implements ILoadDataView {

    static final String LOADING_DIALOG_TAG = "loading_dialog";

    private DialogFragment loadingDialogFragment;
    protected Handler uiHandler;

    private CustomProgressDialog mLoadingDialog;

    protected void closeLoadingProgressBar() {
        if(this.mLoadingDialog != null && this.mLoadingDialog.isShowing()){
            this.mLoadingDialog.dismiss();
        }
    }

    protected void showLoadingProgressBar() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = CustomProgressDialog.createDialog(this);
            this.mLoadingDialog.setMessage("正在加载中.....");
        }
        this.mLoadingDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();

        initActionBar();
        uiHandler = new Handler(getMainLooper());
    }

    protected void initContentView() {
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    // ***************************************** ActionBar *****************************
    private void initActionBar() {
        if (getSupportActionBar() != null) {
            int options = ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE;
            getSupportActionBar().setDisplayOptions(options);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    // ***************************************** ILoadDataView *****************************

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        showLoading(LOADING_TYPE_DEFAULT);
    }

    @Override
    public final void showLoading(int loadingType) {
        switch (loadingType) {
            case LOADING_TYPE_DEFAULT:
            default:
                showDefaultStyleLoadingDialog(null);
                break;
        }
    }

    // 显示用默认样式的Loading对话框
    private void showDefaultStyleLoadingDialog(String loadingTitle) {
        hideDefaultStyleLoadingDialog();

        DialogFragment newFragment = LoadingDialogFragment.newInstance(loadingTitle);
        newFragment.show(getSupportFragmentManager(), LOADING_DIALOG_TAG);
        loadingDialogFragment = newFragment;
    }

    @Override
    public void hideLoading() {
        hideLoading(LOADING_TYPE_DEFAULT);
    }

    @Override
    public final void hideLoading(int loadingType) {
        switch (loadingType) {
            case LOADING_TYPE_DEFAULT:
            default:
                hideDefaultStyleLoadingDialog();
                break;
        }
    }

    //  隐藏默认样式的loading对话框
    private void hideDefaultStyleLoadingDialog() {
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismiss();
            loadingDialogFragment = null;
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    protected final ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }
}