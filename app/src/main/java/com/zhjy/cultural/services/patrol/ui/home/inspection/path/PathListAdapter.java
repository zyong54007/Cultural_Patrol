package com.zhjy.cultural.services.patrol.ui.home.inspection.path;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.PathBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/1/30.
 */

public class PathListAdapter  extends RecyclerView.Adapter {

    private FragmentManager manager;

    private static final int TYPE_ITEM = 0;

    private List<Object> data = new ArrayList<>();

    private OnPathListInteractionListener mListener ;

    public PathListAdapter(FragmentManager fragmentManager,List<Object> data,OnPathListInteractionListener mListener ) {
        this.manager = fragmentManager;
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
        return new PathItem(layoutInflater.inflate(R.layout.fragment_main_inspection_map_path_item, parent, false),mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PathItem) {
            PathItem item = (PathItem) holder;
            item.bind((PathBean) data.get(position));
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

    public void notifyDataSetChanged( List<PathBean> list) {
        if (list != null) {
            int count = list.size();
            data.addAll(list);
            notifyItemRangeChanged(getItemCount()-count,count);
        }
    }
}
