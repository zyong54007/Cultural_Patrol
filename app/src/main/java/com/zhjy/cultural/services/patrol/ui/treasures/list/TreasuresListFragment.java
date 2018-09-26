package com.zhjy.cultural.services.patrol.ui.treasures.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
import com.zhjy.cultural.services.patrol.databinding.FragmentTreasuresListBinding;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
import com.zhjy.cultural.services.patrol.ui.view.EndlessRecyclerViewScrollListener;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.util.SPUtils;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

/**
 * Created by jialg on 2018/1/25.
 */

public class TreasuresListFragment extends AacFragment<FragmentTreasuresListBinding> {

    private static String TAG = "TreasuresListFragment";

    private TreasuresListViewModel treasuresListViewModel;

    private EndlessRecyclerViewScrollListener scrollListener;

    private TreasuresListAdapter adapter;

    private int pageSize = 0;

    private String titleName;

    private int wwType;

    public static TreasuresListFragment newInstance(int wwType) {
        TreasuresListFragment mTreasuresListFragment = new TreasuresListFragment();
        mTreasuresListFragment.wwType = wwType;
        return mTreasuresListFragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_treasures_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData(0);


    }

    private void initView() {
        treasuresListViewModel = ViewModelProviders.of(this, viewModelFactory()).get(TreasuresListViewModel.class);
        adapter = new TreasuresListAdapter(getFragmentManager());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerList.setLayoutManager(linearLayoutManager);
        binding.recyclerList.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));

        binding.recyclerList.setAdapter(adapter);

        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorOrg);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                initData(0);
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (pageSize > page) {
                    initData(page + 1);
                }
            }
        };
        binding.recyclerList.addOnScrollListener(scrollListener);

    }

    private void initData(int page) {
        GetTreasuresListRequest request = new GetTreasuresListRequest(wwType, titleName, page);
        treasuresListViewModel.getTreasuresListReqult(request).observe(this, new Observer<GetTreasuresListResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresListResponse getTreasuresListResponse) {
                updateListView(getTreasuresListResponse);
            }
        });
    }

    private void updateListView(GetTreasuresListResponse response) {
        if (null == response) {
            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }

        if (response.getPageSize() == 0) {
            //当前数据为空
            binding.empty.setVisibility(View.VISIBLE);
        } else {
            binding.empty.setVisibility(View.GONE);
        }

        pageSize = response.getPageSize();
        adapter.notifyDataSetChanged(response);
    }

    OnTitleNameSearchListener mOnTitleNameSearchListener = new OnTitleNameSearchListener() {

        @Override
        public void OnTitleNameSearchListener(String title) {
            adapter.clearData();
            titleName = title;
            initData(0);
        }
    };
}
