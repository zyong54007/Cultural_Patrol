package com.zhjy.cultural.services.patrol.ui.list;

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
 * HotNewsAdapter <br/>
 */
public class CultureListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 2;
    private FragmentManager manager;
    private List<Object> data = new ArrayList<>();

    public CultureListAdapter(FragmentManager fragmentManager) {
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
        if (viewType == TYPE_ITEM) {
            return new CultureNewsTypeItem(layoutInflater.inflate(R.layout.culture_item_last_item, parent, false));
        }
        return new CultureNewsTypeItem(layoutInflater.inflate(R.layout.culture_item_last_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CultureNewsTypeItem) {
            CultureNewsTypeItem item = (CultureNewsTypeItem) holder;
            item.bind((CultureListBean) data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void notifyDataSetChanged(GetCultureListResponse response) {
        data.clear();
        if (response.getHtml() != null) {
            data.addAll(response.getHtml());
        }
        super.notifyDataSetChanged();
    }
}