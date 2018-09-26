package com.zhjy.cultural.services.patrol.ui.inspection.abnormality;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jialg on 2018/2/1.
 */

public class InspectionAbnormalityAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BOTTOM = 1;

    private FragmentManager manager;
    private List<Map<Object, Object>> data = new ArrayList<Map<Object, Object>>();
    private OnImageClickListener mListener ;

    public InspectionAbnormalityAdapter(FragmentManager fragmentManager ) {
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
        return new InspectionAbnormalityStepItem(layoutInflater.inflate(R.layout.activity_inspection_abnormality_step_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InspectionAbnormalityStepItem) {
            InspectionAbnormalityStepItem item = (InspectionAbnormalityStepItem) holder;
            item.bind((Map<Object, Object>) data.get(position),position);
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

    public void notifyDataSetChanged(ArrayList<Map<Object, Object>> mapList) {
        if (mapList != null) {
            data.clear();
            data.addAll(mapList);
            notifyDataSetChanged();
        }
    }

}
