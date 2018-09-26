package com.zhjy.cultural.services.patrol.ui.view.adapter;

import android.view.View;

import com.jph.takephoto.model.TImage;

/**
 * Created by jialg on 2018/2/1.
 */

public interface OnImageClickListener {

    void OnImageClickListener(View view, TImage image);

    void OnImageClickListener(View view, TImage image,int position);

}
