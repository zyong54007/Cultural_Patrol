package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.view.View;

import com.jph.takephoto.model.TImage;

public interface OnImageClickListener {

    void OnImageClickListener(View view, int listIndex,TImage image,int position);

}