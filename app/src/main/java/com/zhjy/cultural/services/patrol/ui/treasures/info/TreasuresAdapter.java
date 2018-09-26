package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/2/5.
 */

public class TreasuresAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    private OnListMoreListener listMoreListener;



    public TreasuresAdapter(FragmentManager fragmentManager, OnListMoreListener listMoreListener) {
        this.manager = fragmentManager;
        this.listMoreListener = listMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new TreasuresItem(layoutInflater.inflate(R.layout.activity_treasures_item, parent, false), listMoreListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TreasuresItem) {
            TreasuresItem item = (TreasuresItem) holder;
            item.bind((RecordBean) data.get(position));
        }
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(GetRecordListResponse response, int type) {
        if (response.getList() != null) {
            int startIndex = getItemCount();
            int total = response.getTotal();
            if (type == 1) {
                //加载  只加载三条
                if (total > 3) {
                    List<RecordBean> threelist = new ArrayList<>();
                    List<RecordBean> list = response.getList();
                    for (int i = 0; i < 3; i++) {
                        RecordBean recordBean = list.get(i);
                        threelist.add(recordBean);
                    }
                    data.addAll(threelist);
                    notifyItemRangeInserted(startIndex + 1, getItemCount() - startIndex);
                } else {
                    data.addAll(response.getList());
                    notifyItemRangeInserted(startIndex + 1, getItemCount() - startIndex);
                }


            } else {
                data.addAll(response.getList());
                notifyItemRangeInserted(startIndex + 1, getItemCount() - startIndex);
//                  加载更多  全部加载出来
            }


            //notifyItemRangeChanged(0,getItemCount());
        }
    }



}
