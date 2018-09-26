package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ArchiveBean;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.util.ArrayList;
import java.util.List;

public class InspectionArchivesAdapter extends RecyclerView.Adapter  {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<ArchiveBean> data = new ArrayList<ArchiveBean>();
    private OnImageClickListener mListener ;

    public InspectionArchivesAdapter(FragmentManager fragmentManager ) {
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
        return new InspectionArchivesItem(layoutInflater.inflate(R.layout.activity_inspection_archives_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InspectionArchivesItem) {
            InspectionArchivesItem item = (InspectionArchivesItem) holder;
            item.bind((ArchiveBean)data.get(position));
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

    public void notifyDataSetChanged(List<ArchiveBean> mapList) {
        if (mapList != null) {
            int count = mapList.size();
            data.addAll(mapList);
            notifyItemRangeChanged(getItemCount()-count,count);
        }
    }

}
