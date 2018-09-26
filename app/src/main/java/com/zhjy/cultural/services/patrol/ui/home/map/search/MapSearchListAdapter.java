package com.zhjy.cultural.services.patrol.ui.home.map.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.ui.home.map.OnStreetListInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/1/30.
 */

public class MapSearchListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;

    private List<TreasuresBean> data = new ArrayList<>();

    public OnStreetListInteractionListener mListener ;

    public MapSearchListAdapter( List<TreasuresBean> data, OnStreetListInteractionListener mListener ) {
        this.data = data;
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
        return new MapSearchItem(layoutInflater.inflate(R.layout.map_search_list_item, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MapSearchItem) {
            MapSearchItem item = (MapSearchItem) holder;
            item.bind((TreasuresBean) data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clearData(){
        data.clear();
//        notifyDataSetChanged();
    }

    public void notifyDataSetChanged( List<TreasuresBean> list) {
        if (list != null) {
            data = list;
            notifyDataSetChanged();
        }
    }
}
