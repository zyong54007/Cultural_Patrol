package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/2/1.
 */

public class ImageAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();
    private OnImageClickListener mListener ;
    private int listIndex;

    public ImageAdapter(FragmentManager fragmentManager, OnImageClickListener mListener,int listIndex ) {
        this.manager = fragmentManager;
        this.mListener = mListener;
        this.listIndex = listIndex;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ImageItem(layoutInflater.inflate(R.layout.activity_feed_back_list_item, parent, false),mListener,listIndex);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageItem) {
            ImageItem item = (ImageItem) holder;
            item.bind((TImage) data.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(ArrayList<TImage> urlList) {
        if (urlList != null) {
            int count = urlList.size();
            data.addAll(urlList);
            notifyItemRangeChanged(getItemCount()-count,count);
        }
    }

}
