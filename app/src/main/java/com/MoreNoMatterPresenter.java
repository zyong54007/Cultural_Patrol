package com;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.MoreMatterAdapter;
import com.zhjy.cultural.services.patrol.NoticeDetailsActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.MessageAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MoreNoMatterPresenter extends ActPresenter<MoreNoMatterPresenter.MoreNoMatterUI> {

    private int page = 0;
    private MoreMatterAdapter adapter;
    private List<RecordBean> list = new ArrayList<>();
    private MainListViewModel mainListViewModel;

    public interface MoreNoMatterUI extends GEMUI {
        RecyclerView getRecyclerview();

        TextView getnodate();
//        SwipeRefreshLayout getSwipeRefreshLayout();
    }


    @Override
    public void onUIReady(MVPActivity activity, MoreNoMatterUI ui) {
        super.onUIReady(activity, ui);
        mainListViewModel = ViewModelProviders.of(getActivity(), viewModelFactory()).get(MainListViewModel.class);
        initAdapter();

    }


    @Override
    public void onResume() {
        super.onResume();
        requestNetWork(page, 0);
    }

    private void initAdapter() {
        getUI().getRecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MoreMatterAdapter(R.layout.activity_my_notice_list_item, list);
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);   //动画一直执行
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), PunchClockActivity.class);
                intent.putExtra("wwId", list.get(position).getWw().getId());
                intent.putExtra("recordId", list.get(position).getId());
                intent.putExtra(Contant.POINT, list.get(position).getWw().getPoint());
                getActivity().startActivity(intent);

            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page += 10;
                requestNetWork(page, 0);
            }
        }, getUI().getRecyclerview());

//        adapter.disableLoadMoreIfNotFullPage();
        getUI().getRecyclerview().setAdapter(adapter);

//        getUI().getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                requestNetWork(0, 1);
//            }
//        });


    }


    private void requestNetWork(int page, int type) {

        GetNullListRequest request = new GetNullListRequest(page);
        mainListViewModel.getTotalNullResult(request).observe(getActivity(), new Observer<GetNullListResponse>() {
            @Override
            public void onChanged(@Nullable GetNullListResponse getNullListResponse) {
                updateListView(getNullListResponse, type);
            }
        });


    }

    private void updateListView(GetNullListResponse response, int type) {


        if (null == response) {
            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);
            getActivity().finish();
//            adapter.loadMoreFail();
            return;
        }
        if (response.getFailure()) {
            Toast.makeText(getActivity(), R.string.network_time_error, Toast.LENGTH_LONG).show();
            return;
        }

//        List<RecordBean> recordBeanslist = new ArrayList<>();
        List<RecordBean> responselist = response.getList();
        list.addAll(responselist);
        if (responselist == null) {

            getUI().getnodate().setVisibility(View.VISIBLE);
        }
        if (responselist.size() < 10) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }

        adapter.notifyDataSetChanged();

//        if (recordBeanslist == null) {
//            getUI().getSwipeRefreshLayout().setRefreshing(false);
//            ToastUtil.showToastMsg("暂无数据");
//            return;
//        }

//        if (recordBeanslist.size() < 10) {
//            adapter.loadMoreEnd();
//        } else {
//            adapter.loadMoreComplete();
//        }
//        if (type == 0) {
//            this.list.addAll(recordBeanslist);
//            adapter.notifyDataSetChanged();
//        } else {
//            this.list = new ArrayList<>();
//            this.list.addAll(recordBeanslist);
//            adapter.notifyDataSetChanged();
//            if (recordBeanslist.size() < 10) {
//                adapter.loadMoreEnd();
//            } else {
//                adapter.loadMoreComplete();
//            }

//            getUI().getSwipeRefreshLayout().setRefreshing(false);
//        }
    }


    protected ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }

}
