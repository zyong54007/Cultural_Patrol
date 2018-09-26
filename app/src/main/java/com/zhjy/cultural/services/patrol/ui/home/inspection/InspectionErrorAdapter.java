package com.zhjy.cultural.services.patrol.ui.home.inspection;

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
 * Created by jialg on 2018/1/24.
 */

public class InspectionErrorAdapter extends RecyclerView.Adapter{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    public InspectionErrorAdapter(FragmentManager fragmentManager) {
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
        return new InspectionErrorItem(layoutInflater.inflate(R.layout.fragment_main_inspection_list_item_error, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InspectionErrorItem) {
            InspectionErrorItem item = (InspectionErrorItem) holder;
            item.bind((RecordBean) data.get(position));
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

    public void notifyDataSetChanged(GetRecordListResponse response) {
        if (response.getList() != null) {
            int count = response.getList().size();
            data.addAll(response.getList());
            //notifyItemRangeChanged(getItemCount()-count,count);
            notifyDataSetChanged();
        }
    }

}
