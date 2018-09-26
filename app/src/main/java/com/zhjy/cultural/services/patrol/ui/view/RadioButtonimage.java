package com.zhjy.cultural.services.patrol.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

public class RadioButtonimage extends RadioButton {
    public RadioButtonimage(Context context) {
        super(context);
    }

    public RadioButtonimage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioButtonimage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = drawables[0];
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int gravity = getGravity();
        int left = 0;
        if (gravity == Gravity.CENTER) {
            left = ((int) (getWidth() / 2 - getPaint().measureText(getText().toString())));
        }
        drawable.setBounds(left,0,left+drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
    }
}