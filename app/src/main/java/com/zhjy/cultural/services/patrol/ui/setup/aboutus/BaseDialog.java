package com.zhjy.cultural.services.patrol.ui.setup.aboutus;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Created by Allen Liu on 2017/2/23.
 */
public class BaseDialog extends Dialog {
    private View view;

    public BaseDialog(Context context, int theme, View view) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(view);
        this.view = view;

        setCanceledOnTouchOutside(false);
    }

}