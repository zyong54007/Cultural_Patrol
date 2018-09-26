package com.zhjy.cultural.services.patrol.ui.home.map.search;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.Keyboard;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.NoticeDetailsActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.PopWindowAdapter;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;
import com.zhjy.cultural.services.patrol.databinding.MapSearchListPopBinding;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMapViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.OnStreetListInteractionListener;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;
import com.zhjy.cultural.services.patrol.util.KeyBoardUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/3/22.
 */

public class MapSearchPopupWindow extends PopupWindow implements SearchEditText.OnSearchClickListener {

    private Context context;

    private List<TreasuresBean> searchNameList;
    private List<TreasuresBean> searchlist;

//    private MapSearchListAdapter adapter;

    public OnStreetListInteractionListener mStreetListener;

    private CulturalRelicsMapViewModel mCulturalRelicsMapViewModel;

    private MapSearchListPopBinding popBinding;

    public MapSearchPopupWindow(Context context, OnStreetListInteractionListener mStreetListener, CulturalRelicsMapViewModel mCulturalRelicsMapViewModel) {
        super(context);
        this.context = context;
        this.mStreetListener = mStreetListener;
        this.mCulturalRelicsMapViewModel = mCulturalRelicsMapViewModel;
        searchNameList = new ArrayList<>();
        searchlist = new ArrayList<>();
        initPopupWindow();
        getDataList();
    }


    private PopWindowAdapter adapter;

    private void initPopupWindow() {
        popBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.map_search_list_pop, null, false);
        popBinding.editSearch.setOnSearchClickListener(this);

        popBinding.recyclerList.setLayoutManager(new LinearLayoutManager(context));
        adapter = new PopWindowAdapter(R.layout.item_map_search, searchNameList);

        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);   //动画一直执行
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                int id = datas.get(position).getNotice().getId();
//                Intent intent = new Intent(getActivity(), NoticeDetailsActivity.class);
//                intent.putExtra(Contant.ID, id);
//                getActivity().startActivity(intent);
                mStreetListener.OnStreetListInteractionListener(searchNameList.get(position));
            }
        });
        popBinding.recyclerList.setAdapter(adapter);

//        adapter = new MapSearchListAdapter(searchNameList, mStreetListener);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        popBinding.recyclerList.setLayoutManager(linearLayoutManager);
//        popBinding.recyclerList.addItemDecoration(new RecycleViewDivider(
//                context, LinearLayoutManager.HORIZONTAL, 1, context.getResources().getColor(R.color.gray_back)));
//        popBinding.recyclerList.setAdapter(adapter);


        popBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        popBinding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(popBinding.editSearch.getText().toString().trim())) {
                    ToastUtil.showToastMsg("搜索条件不能为空");
                    return;
                }
                KeyBoardUtils.closeKeybord(popBinding.editSearch, context);
                getDataList();
            }
        });
        this.setContentView(popBinding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth((LinearLayout.LayoutParams.MATCH_PARENT));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);


        /**
         * 输入框得监听
         */
        popBinding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(s)) {  //输入为空 显示全部
                    Log.i("TAG", "输入框内容==================" + s);
                    popBinding.popSearch.setVisibility(View.GONE);
                    searchNameList.clear();
                    searchNameList.addAll(searchlist);
//                    adapter.clearData();
//                    adapter.notifyDataSetChanged(searchNameList);

                } else {    //不为空  循环遍历数据    判断标题是否含有输入内容 添加到集合里面  适配数据

                    Log.i("TAG", "=================不为空");
                    List<TreasuresBean> list = new ArrayList<>();
                    for (TreasuresBean bean : searchlist) {
                        String title = bean.getTitle();
                        if (title.contains(s)) {
                            list.add(bean);
                        }
                    }

                    if (list.size() == 0) {   //没有数据  显示没有结果
                        popBinding.popSearch.setVisibility(View.VISIBLE);
                    } else {     //   有数据 展示
                        searchNameList.clear();
                        searchNameList.addAll(list);
                        adapter.notifyDataSetChanged();
//                        adapter.clearData();
//                        adapter.notifyDataSetChanged(list);
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void getDataList() {
        String title = popBinding.editSearch.getText().toString().trim();
        GetMapDataRequest request = new GetMapDataRequest(title);
        mCulturalRelicsMapViewModel.getMapDataListResult(request).observe((FragmentActivity) context, new Observer<List<TreasuresBean>>() {
            @Override
            public void onChanged(@Nullable List<TreasuresBean> treasuresBeans) {
                updateListView(treasuresBeans);
            }
        });
    }

    public void updateListView(List<TreasuresBean> treasuresBeans) {
        if (null == treasuresBeans) {
            Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        popBinding.load.setVisibility(View.GONE);

        searchNameList.clear();
        searchlist.clear();
        searchNameList.addAll(treasuresBeans);
        searchlist.addAll(treasuresBeans);


        if (searchNameList.size() == 0) {
            popBinding.popSearch.setVisibility(View.VISIBLE);
        } else {

            popBinding.popSearch.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }


    /**
     * 键盘搜索框的监听
     *
     * @param view
     * @param keyword
     */

    @Override
    public void onSearchClick(View view, String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            ToastUtil.showToastMsg("搜索条件不能为空");
            return;
        }
        getDataList();
    }
}
