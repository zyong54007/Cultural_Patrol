package com.zhjy.cultural.services.patrol.ui.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by jialg on 2018/2/2.
 */

public class LastInputEditText extends android.support.v7.widget.AppCompatEditText {

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if(selStart==selEnd){
            setSelection(getText().length());
        }

    }

}
