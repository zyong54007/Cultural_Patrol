package com.zhjy.cultural.services.patrol.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 * AacBaseActivity <br/>
 */
public abstract class AacBaseActivity<T extends ViewDataBinding> extends BaseActivity {

    protected T binding;

    @Override
    protected void initContentView() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }
}