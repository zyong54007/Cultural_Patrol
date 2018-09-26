package com.zhjy.cultural.services.patrol.ui.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;

/**
 * Created by jialg on 2018/2/1.
 */

public class ImageAdapter extends PagerAdapter {

    private ArrayList<TImage> data = new ArrayList<>();

    private OnImageClickListener mListener ;

    private SparseArray<ImageItem> ImageItemDatas = new SparseArray<>();


    public ImageAdapter(OnImageClickListener mListener ,ArrayList<TImage> data ) {
        this.data = data;
        this.mListener = mListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        if(ImageItemDatas.get(position) != null && ((TImage)ImageItemDatas.get(position).getRootView().getTag()) == data.get(position) ){
            ImageItem imageItem  = ImageItemDatas.get(position);
            container.addView(imageItem.getRootView());
            return imageItem.getRootView();
        }
        Context context = container.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageItem imageItem = new ImageItem(layoutInflater.inflate(R.layout.activity_feed_back_image_item, container, false),mListener);
        imageItem.bind((TImage) data.get(position));
        container.addView(imageItem.getRootView());
        ImageItemDatas.put(position,imageItem);
        return imageItem.getRootView();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void removeData(int position){
        if(data.size() == 0 )
            return;
        if(data.get(position) != null)
            data.remove(position);
        if(ImageItemDatas.get(position) != null)
            ImageItemDatas.remove(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
