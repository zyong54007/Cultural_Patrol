package com.zhjy.cultural.services.patrol.ui.home.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureListBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * MainListAdapter
 */
public class MainListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    public MainListAdapter(FragmentManager fragmentManager) {
        this.manager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        if(position < data.size()){
            return TYPE_ITEM;
        }else {
            return TYPE_BOTTOM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == TYPE_BOTTOM) {
            return new MainListBottomTypeItem(layoutInflater.inflate(R.layout.main_item_bottom_item, parent, false));
        }
        return new MainListTypeItem(layoutInflater.inflate(R.layout.main_item_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainListTypeItem) {
            MainListTypeItem item = (MainListTypeItem) holder;
            item.bind((CultureListBean) data.get(position));
        }else if(holder instanceof MainListBottomTypeItem){
            MainListBottomTypeItem item = (MainListBottomTypeItem)holder;
            item.bind(data.size());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() > 0 ? data.size()+ 1 : 0  ;
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(GetCultureListResponse response) {
        if (response.getHtml() != null) {
            data.addAll(response.getHtml());
            notifyItemRangeChanged(0,getItemCount());
        }
    }

}