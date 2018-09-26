package com.zhjy.cultural.services.patrol.ui.home.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/3/16.
 */

public class MainListAdapterForXunjian extends RecyclerView.Adapter  {

    private static final int TYPE_ITEM = 0;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    public MainListAdapterForXunjian(FragmentManager fragmentManager) {
        this.manager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
            return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MainListTypeItemOfXj(layoutInflater.inflate(R.layout.main_item_list_item_xj, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainListTypeItemOfXj item = (MainListTypeItemOfXj) holder;
        item.bind((RecordBean) data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size() < 3 ? data.size(): 3 ;
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(GetNullListResponse response) {
        if (response.getList() != null) {
            clearData();;
            data.addAll(response.getList());
            notifyItemRangeChanged(0,getItemCount());
        }
    }


}
