package com.zhjy.cultural.services.patrol.ui.home.inspection;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.InSpectionListAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.base.BaseFragment;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.mvp.LazyFragment;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.routing.InspectionRecordPresenter;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.view.EndlessRecyclerViewScrollListener;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jialg on 2018/1/24.
 * 巡检  巡检记录
 */

public class InspectionRecordFragment extends LazyFragment {

//        extends LazyFragment {
//        BaseFragment<InspectionRecordPresenter.InspectionRecordUI, InspectionRecordPresenter> implements InspectionRecordPresenter.InspectionRecordUI {

    private int page = 0;
    private RecyclerView recyclerView;

    private List<InSpecListEntity.Datas> list;
    private InSpectionListAdapter adapter;

    public InspectionRecordFragment() {


    }


    @Override
    protected void lazyLoad() {
        Log.i("TAG", "===============12121212121");
        if (isInit) {
            initDate();
        }

    }


    private TextView tv_nodate;

    @Override
    protected int setContentView() {
        return R.layout.fragment_main_inspection_record;
    }


    private void initDate() {
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.record_list);
        tv_nodate = findViewById(R.id.tv_nodate);
        adapter = new InSpectionListAdapter(R.layout.item_inspention, list);
        adapter.openLoadAnimation();   //开启动画
        adapter.isFirstOnly(false);   //动画一直执行
        if (isInit) {
            //可见  加载网络
            page = 0;
            ReqestDate();
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    ReqestDate();
                }
            }, recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), InspectionActivity.class);
                intent.putExtra("wwId", list.get(position).getWw().getId());
                intent.putExtra("recordId", list.get(position).getId());
                getActivity().startActivity(intent);
            }
        });

    }

    private void ReqestDate() {
        int pagesize = page * 10;
//        http://wwgl.hdggwh.com/wwgl/record/loadAppList;jsessionid=DA1D656E74860E382B53C2B0535B39F1?recordType=r&title=&flag=1&pager.offset=10
        String url = "record/loadAppList;jsessionid=" + AppContext.getJsessionid() + "?recordType=r&title=&flag=1&pager.offset=" + pagesize;
        Log.i("TAG", "url巡检记录==================" + url);
        new GRetrofit().request(GemService.class).getInspeclist(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<InSpecListEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onNext(InSpecListEntity inSpecListEntity) {
                        List<InSpecListEntity.Datas> datas = inSpecListEntity.getDatas();
                        list.addAll(datas);
                        if (datas.size() < 10) {
                            adapter.loadMoreEnd();
                        } else if (datas.size() == 10) {
                            adapter.loadMoreComplete();
                        }
                        tv_nodate.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();

                    }
                });
    }


}


//    @Override
//    protected void initViews() {
//
//
//    }
//
//    @Override
//    public int getContentLayout() {
//        return R.layout.fragment_main_inspection_record;
//    }
//
//    @Override
//    protected InspectionRecordPresenter createPresenter() {
//        return new InspectionRecordPresenter();
//    }
//
//    @Override
//    protected InspectionRecordPresenter.InspectionRecordUI createUI() {
//        return this;
//    }


//        AacFragment<FragmentMainInspectionRecordBinding> {
//
//    private static String TAG = "InspectionRecordFragment";
//
//    private InspectionViewModel inspectionViewModel;
//
//    private EndlessRecyclerViewScrollListener scrollListener;
//
//    private InspectionAdapter adapter;
//
//    private int totalSize = 1;
//
//    private String title = "";
//
//    public static InspectionRecordFragment newInstance() {
//        InspectionRecordFragment instance = new InspectionRecordFragment();
//        return instance;
//    }
//
//
//    @Override
//    protected int getFragmentLayout() {
//        return R.layout.fragment_main_inspection_record;
//    }
//
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
////        initView();
//        Log.i("TAG", "初始化加载=============");
////        initData(0);
//    }
//
//    private void initView() {
//        inspectionViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionViewModel.class);
//        adapter = new InspectionAdapter(getChildFragmentManager());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        binding.recordList.setLayoutManager(linearLayoutManager);
//        binding.recordList.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));
//
//        binding.recordList.setAdapter(adapter);
//
//        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorOrg);
//
//        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                adapter.clearData();
//                initData(0);
//                binding.swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                int nextPage = page++;
//                if (totalSize > nextPage) {
//                    initData(nextPage);
//                }
//            }
//        };
//        binding.recordList.addOnScrollListener(scrollListener);
//
//    }
//
//    private void initData(int page) {
//        GetRecordAppListRequest request = new GetRecordAppListRequest("r", title, page);
//        Log.i("TAG", "xuanjia=============" + request.toString());
//        inspectionViewModel.getRecordListResult(request).observe(this, new Observer<GetRecordListResponse>() {
//            @Override
//            public void onChanged(@Nullable GetRecordListResponse getRecordListResponse) {
////                Log.i("TAG", "=========正常巡检记录" + getRecordListResponse.toString());
//                updateListView(getRecordListResponse);
//            }
//        });
//    }
//
//    private void updateListView(GetRecordListResponse response) {
//        if (null == response) {
//            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
//            return;
//        }
////        Log.i(TAG, "巡检记录==========" + response.toString());
//        totalSize = response.getPageSize();
//        int total = response.getTotal();
//        Log.i("TAG", "=====================" + total);
//
////        SPUtils.set(Contant.RECORDNUM, total);
//
//        EventBus.getDefault().post(new MessageEvent(total, 1));
//        adapter.notifyDataSetChanged(response);
//    }
//
//    public OnTitleNameSearchListener mOnTitleNameSearchListener = new OnTitleNameSearchListener() {
//
//        @Override
//        public void OnTitleNameSearchListener(String titleName) {
//            title = titleName;
//            adapter.clearData();
//            initData(0);
//        }
//    };
//
//}
