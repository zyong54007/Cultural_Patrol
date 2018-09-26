
package com.baidu.mapapi.baiduapplication;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

public class MainViewpagerAdapter extends PagerAdapter {
	Context ctx = null;
    private SparseArray<View> viewDatas = null;
    public MainViewpagerAdapter(Context ct , SparseArray<View> viewDatas) {
        this.ctx = ct;
        this.viewDatas = viewDatas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View view = viewDatas.get(position);
        //view.setRotation(-90);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return viewDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
