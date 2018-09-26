package com.zhjy.cultural.services.patrol.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.PopwindProgressbarBinding;

public class PopWindProgressbar extends PopupWindow {

    private Context context;
    private PopwindProgressbarBinding popBinding;

    public PopWindProgressbar(Context context){
        super(context);
        this.context = context;
        initPopupWindow();
    }
    private void initPopupWindow() {
        popBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.popwind_progressbar,null,false);
        this.setContentView(popBinding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth((LinearLayout.LayoutParams.MATCH_PARENT));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
    }
}
