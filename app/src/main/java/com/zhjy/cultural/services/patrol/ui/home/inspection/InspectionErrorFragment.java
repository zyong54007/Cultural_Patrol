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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.InspectionFailedAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.base.BaseFragment;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.mvp.LazyFragment;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityActivity;
import com.zhjy.cultural.services.patrol.ui.view.EndlessRecyclerViewScrollListener;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jialg on 2018/1/24.
 * 巡检  异常记录   为了省事  直接复用了懒加载的fragment 没有用mvp
 */

public class InspectionErrorFragment extends LazyFragment implements View.OnClickListener {
    //    http://wwgl.hdggwh.com/wwgl/record/loadAppList;jsessionid=DA1D656E74860E382B53C2B0535B39F1?recordType=r&title=&flag=1&pager.offset=10


    private InspectionFailedAdapter adapter;

    private List<InSpecListEntity.Datas> list;   //全部
    private List<InSpecListEntity.Datas> totallist;   //全部

    private List<InSpecListEntity.Datas> waitlist;  //待处理
    private List<InSpecListEntity.Datas> filelist;  //已归档


    private RecyclerView recyclerView;

    private int page = 0;

    private int flag = 1;    //当前页面状态  1 为全部  2 为待处理  3 为已归档


    @Override
    protected int setContentView() {
        return R.layout.fragment_main_inspection_errorr;
    }


    //    InspectionErrorItem
    @Override
    protected void lazyLoad() {
        if (isInit) {
            initAdapte();

        }
    }


    private RadioButton rb_total, rb_wait, rb_file;

    private TextView tv_nodate;

    /**
     * 初始化adapte
     */
    private void initAdapte() {

        recyclerView = findViewById(R.id.record_error_list);
        tv_nodate = findViewById(R.id.tv_error_nodate);
        rb_total = findViewById(R.id.total);
        rb_wait = findViewById(R.id.wait);
        rb_file = findViewById(R.id.file);

        rb_wait.setText("待处理(" + SPUtils.get(URLs.WAITNUM, 0) + ")");
        rb_file.setText("已归档(" + SPUtils.get(URLs.SUMNUM, 0) + ")");
        rb_total.setOnClickListener(this);
        rb_wait.setOnClickListener(this);
        rb_file.setOnClickListener(this);

        rb_total.setChecked(true);

        list = new ArrayList<>();
        waitlist = new ArrayList<>();
        filelist = new ArrayList<>();
        totallist = new ArrayList<>();

        adapter = new InspectionFailedAdapter(R.layout.item_inspention, list);
        adapter.openLoadAnimation();   //开启动画
        adapter.isFirstOnly(false);   //动画一直执行
        if (isInit) {
            page = 0;
            RequestErrorDate();
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    Log.i("TAG", "=============加载回调" + page);
                    RequestErrorDate();
                }
            }, recyclerView);
//            adapter.disableLoadMoreIfNotFullPage();  //第一次加载不回调
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), InspectionAbnormalityActivity.class);
                intent.putExtra("wwId", list.get(position).getWw().getId());
                intent.putExtra("recordId", list.get(position).getId());
                getActivity().startActivity(intent);
            }
        });
    }

    /**
     * 加载数据
     */
    private void RequestErrorDate() {
        int pagesize = page * 10;
        String url = "record/loadAppList;jsessionid=" + AppContext.getJsessionid() + "?recordType=y&title=&flag=0&pager.offset=" + pagesize;
        Log.i("TAG", "url异常记录==================" + url);
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
//                        list.addAll(datas);
                        totallist.addAll(datas);
//                        <c:if test="${item.status == 2 }">
//<c:if test="${item.flow == 1 }">待反馈</c:if>
//<c:if test="${item.flow == 2 }">待处理</c:if>
//<c:if test="${item.flow == 3 }">待归档</c:if>
//</c:if>
//<c:if test="${item.status == 3 }">已归档</c:if>


                        if (flag == 1) {
                            list.addAll(datas);
                        } else if (flag == 2) {
                            for (int i = 0; i < datas.size(); i++) {
                                int status = datas.get(i).getStatus();
                                if (status == 2) {
                                    int flow = datas.get(i).getFlow();
                                    if (flow == 2) {
                                        list.add(datas.get(i));
                                    }
                                }
                            }

                        } else if (flag == 3) {

                            for (int i = 0; i < datas.size(); i++) {
                                int status = datas.get(i).getStatus();
                                if (status == 3) {   //已归档
                                    list.add(datas.get(i));
                                }

                            }


                        }


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.total:

                Log.i("TAG", "===========点击方法执行");
                flag = 1;
                list.clear();
                list.addAll(totallist);
                adapter.notifyDataSetChanged();
                break;
            case R.id.wait:
                flag = 2;
                list.clear();
                waitlist.clear();
                for (int i = 0; i < totallist.size(); i++) {
                    int status = totallist.get(i).getStatus();
                    if (status == 2) {    //未归档
                        int flow = totallist.get(i).getFlow();
                        if (flow == 2) {//待处理
                            waitlist.add(totallist.get(i));
                        }

                    }
                }

                list.addAll(waitlist);
                adapter.notifyDataSetChanged();
                break;
            case R.id.file:
                flag = 3;
                list.clear();
                filelist.clear();
                for (int i = 0; i < totallist.size(); i++) {
                    int status = totallist.get(i).getStatus();
                    if (status == 3) {   //已归档
                        filelist.add(totallist.get(i));
                    }

                }

                list.addAll(filelist);
                adapter.notifyDataSetChanged();
                break;
        }
    }


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_inspection_errorr, null);
//        Log.i("TAG", "异常记录页面也创建了================");
//        return view;
//    }
    //    @Override
//    protected void lazyLoad() {
//
//    }
//
//    @Override
//    protected int setContentView() {
//        return R.layout.fragment_main_inspection_errorr;
//    }
}
//        extends AacFragment<FragmentMainInspectionRecordBinding> {
//
//    private static String TAG = "InspectionErrorFragment";
//
//    private InspectionViewModel inspectionViewModel;
//
//    private EndlessRecyclerViewScrollListener scrollListener;
//
//    private InspectionErrorAdapter adapter;
//
//    private int pageSize = 0;
//
//    private int totalSize = 1;
//
//    private int flag = 0;
//
//
//    private boolean isfirst = false;
//
//    private String title = "";
//
//    public static InspectionErrorFragment newInstance() {
//        InspectionErrorFragment instance = new InspectionErrorFragment();
//        return instance;
//    }
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
////        initData(pageSize);
//    }
//
//    private void initView() {
//        inspectionViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionViewModel.class);
//        adapter = new InspectionErrorAdapter(getChildFragmentManager());
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
//                initData(pageSize);
//                binding.swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//
//        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                int nextPage = page++;
//                if (totalSize > nextPage) {
//                    isfirst = false;
//                    initData(nextPage);
//                }
//            }
//        };
//        binding.recordList.addOnScrollListener(scrollListener);
//    }
//
//    private void initData(int page) {
//        GetRecordAppListRequest request = new GetRecordAppListRequest("y", flag, title, page);
//        inspectionViewModel.getRecordListResult(request).observe(this, new Observer<GetRecordListResponse>() {
//            @Override
//            public void onChanged(@Nullable GetRecordListResponse getRecordListResponse) {
////                Log.i("TAG", "=========异常巡检记录仪" + getRecordListResponse.toString());
//                updateListView(getRecordListResponse);
//            }
//        });
//    }
//
//
//    private int num;
//    private int handlernum = 0;
//    private int completenum = 0;
//
//
//    private void updateListView(GetRecordListResponse response) {
//        if (null == response) {
//            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
//            return;
//        }
//        int size = response.getList().size();
//
//
//        if (!isfirst) {
//
//            List<RecordBean> list = response.getList();
//            for (int i = 0; i < list.size(); i++) {
//                int status = list.get(i).getStatus();
//                if (status == 2) {
//                    handlernum++;
//
//                } else if (status == 3) {
//                    completenum++;
//                }
//
//
//            }
//            num = response.getTotal();
////            num = list.size();
//
//            isfirst = true;
//        }
//
//        EventBus.getDefault().post(new MessageEvent(num, handlernum, completenum, 2));
//        SPUtils.set(Contant.SIZE, size);
//        totalSize = response.getPageSize();
//        adapter.notifyDataSetChanged(response);
//    }
//
//    private void initListen() {
//
//    }
//
//    public OnTabTextChangeListener mOnTabTextChangeListener = new OnTabTextChangeListener() {
//
//        @Override
//        public void OnChangeListener(int f) {
//            flag = f;
//            adapter.clearData();
//            initData(0);
//        }
//    };
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
