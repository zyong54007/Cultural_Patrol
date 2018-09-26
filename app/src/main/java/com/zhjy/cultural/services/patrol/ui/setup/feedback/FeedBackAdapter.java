package com.zhjy.cultural.services.patrol.ui.setup.feedback;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/2/1.
 */

public class FeedBackAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();
    private OnImageClickListener mListener;

    public FeedBackAdapter(FragmentManager fragmentManager, OnImageClickListener mListener) {
        this.manager = fragmentManager;
        this.mListener = mListener;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new FeedBackItem(layoutInflater.inflate(R.layout.activity_feed_back_list_item, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FeedBackItem) {
            FeedBackItem item = (FeedBackItem) holder;
            item.bind((TImage) data.get(position), position);
        }
    }

    @Override
    public int getItemCount() {

        return data.size() >= 9 ? 9 : data.size();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(ArrayList<TImage> fileList) {
        if (fileList != null) {
            int count = fileList.size();
            data.addAll(fileList);
            notifyItemRangeChanged(getItemCount() - count, count);
        }
    }

}
