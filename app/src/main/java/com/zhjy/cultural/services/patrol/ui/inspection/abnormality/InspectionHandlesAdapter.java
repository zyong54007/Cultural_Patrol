package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.HandleBean;
import java.util.ArrayList;
import java.util.List;

public class InspectionHandlesAdapter extends RecyclerView.Adapter  {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<HandleBean> data = new ArrayList<HandleBean>();
    private OnImageClickListener mListener ;
    private OnImageClickListener imageClickListener;

    public InspectionHandlesAdapter(FragmentManager fragmentManager,OnImageClickListener imageClickListener ) {
        this.manager = fragmentManager;
        this.imageClickListener = imageClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new InspectionHandlesItem(layoutInflater.inflate(R.layout.activity_inspection_handles_item, parent, false),manager,imageClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InspectionHandlesItem) {
            InspectionHandlesItem item = (InspectionHandlesItem) holder;
            item.bind((HandleBean) data.get(position),position);
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

    public void notifyDataSetChanged(List<HandleBean> mapList) {
        if (mapList != null) {
            int count = mapList.size();
            data.addAll(mapList);
            notifyItemRangeChanged(getItemCount()-count,count);
        }
    }

}
