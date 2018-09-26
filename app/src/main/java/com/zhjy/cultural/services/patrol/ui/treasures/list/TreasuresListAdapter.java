package com.zhjy.cultural.services.patrol.ui.treasures.list;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/1/25.
 */

public class TreasuresListAdapter  extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    public TreasuresListAdapter(FragmentManager fragmentManager) {
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
        return new TreasuresListTypeItem(layoutInflater.inflate(R.layout.main_item_list_item_ww, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TreasuresListTypeItem) {
            TreasuresListTypeItem item = (TreasuresListTypeItem) holder;
            item.bind((TreasuresBean) data.get(position));
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

    public void notifyDataSetChanged(GetTreasuresListResponse response) {
        if (response.getDatas() != null) {
            int count = response.getDatas().size();
            data.addAll(response.getDatas());
            notifyItemRangeChanged(getItemCount()-count,count);
        }
    }
}
