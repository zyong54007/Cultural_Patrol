package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by jialg on 2018/1/25.
 */

public class ImagePageAdapter extends PagerAdapter {

    public String[] imageUrls = {"http://www.hdggwh.com/uploadfile/2017/0816/20170816044816453.jpg",
            "http://www.hdggwh.com/uploadfile/2017/0816/20170816044816233.jpg",
            "http://www.hdggwh.com/uploadfile/2017/0816/20170816044816580.jpg"};

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        Picasso.with(container.getContext()).load(imageUrls[position]).into(view);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

}
