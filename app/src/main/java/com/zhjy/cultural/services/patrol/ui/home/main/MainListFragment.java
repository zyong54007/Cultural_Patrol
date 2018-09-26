package com.zhjy.cultural.services.patrol.ui.home.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.baidu.location.BDLocation;
import com.zhjy.cultural.services.patrol.HandleMatterActivity;
import com.zhjy.cultural.services.patrol.MoreNoMatterActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.AppNumEntity;
import com.zhjy.cultural.services.patrol.bean.HandleMatter;
import com.zhjy.cultural.services.patrol.bean.Msg;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainListBinding;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.CustomVersionDialogActivity;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.UpdateService;
import com.zhjy.cultural.services.patrol.ui.setup.notice.list.MyNoticeActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListActivity;
import com.zhjy.cultural.services.patrol.ui.view.MyAnimationDrawable;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * HotNewsFragment <br/>
 */
public class MainListFragment extends AacFragment<FragmentMainListBinding> {

    private String TAG = "MainListFragment";

    public static MainListFragment newInstance() {
        return new MainListFragment();
    }

    private MainListViewModel mainListViewModel;
    private InspectionViewModel inspectionViewModel;
    private MainListAdapterForXunjian adapterForXj;    //巡检列表daapter

    private MainListAdapterForWenWu adapterForWw;         //监管文物adapter

    private StopGif stopGif;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        if (SPUtils.get("upflag", 0) != 1) {
            updateVersion();
        }
        loadData(0);
        initLocationData();
        initListen();
        initAnimation();


    }


    @Override
    public void onResume() {
        super.onResume();
        binding.errorsize.setText("(" + SPUtils.get(URLs.WAITNUM, 0) + ")");
    }

    private void initView() {
        mainListViewModel = ViewModelProviders.of(this, viewModelFactory()).get(MainListViewModel.class);
        inspectionViewModel = ViewModelProviders.of(this, viewModelFactory()).get(InspectionViewModel.class);
        initListViewOfXj();
        initListViewOfWw();
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorOrg);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(0);
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        binding.btnZhiwei.setGifResource(R.mipmap.zhiwei_ico);
        binding.btnZhiwei.getGifResource();
        binding.btnZhiwei.pause();
        stopGif = new StopGif();

    }

    private void initAnimation() {
        MyAnimationDrawable animationDrawable = new MyAnimationDrawable();
        for (int i = 1; i <= 11; i++) {
            int id = getResources().getIdentifier("press_image_" + i, "mipmap", getActivity().getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 200);
        }
        animationDrawable.setOneShot(true);
        animationDrawable.setAnimationFinishListener(new MyAnimationDrawable.IAnimationFinishListener() {
            @Override
            public void onAnimationFinished() {
                binding.textPress.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        });
        binding.imgPress.setImageDrawable(animationDrawable);
        animationDrawable.start();
        binding.textPress.setVisibility(View.INVISIBLE);
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            initAnimation();
        }
    };


    private void initListViewOfXj() {
        adapterForXj = new MainListAdapterForXunjian(getChildFragmentManager());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.xunjianList.setLayoutManager(linearLayoutManager);
        binding.xunjianList.setNestedScrollingEnabled(false);
        binding.xunjianList.setHasFixedSize(true);
        binding.xunjianList.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));
        binding.xunjianList.setAdapter(adapterForXj);

    }

    private void initListViewOfWw() {
        adapterForWw = new MainListAdapterForWenWu(getChildFragmentManager());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.janguanWenwuList.setLayoutManager(linearLayoutManager);
        binding.janguanWenwuList.setNestedScrollingEnabled(false);
        binding.janguanWenwuList.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));
        binding.janguanWenwuList.setAdapter(adapterForWw);
    }

    private void loadData(int page) {

        Observable.just("1").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                loadTreasuresListReqult(page);   //我监管的文物
                return s;
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                loadTotalNullResult(page);    //  巡检提醒

                return s;
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                LoadAppnum();
                //获取全局所需要的条目数量
                return s;
            }


        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                LoadMessage();
                return s;
            }
        }).subscribe(new rx.Observer<String>() {


            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {

            }
        });


//        loadTreasuresListReqult(page);   //我监管的文物
//
//        loadTotalNullResult(page);    //  巡检提醒
//
//        LoadTotalErrorResult();        //待处理

    }

    /**
     * 获取全局条目数量
     */
    public void LoadAppnum() {
        String url = "record/loadRecordData;jsessionid=" + AppContext.getJsessionid();
        Log.i("TAG", "=====================" + url);
        new GRetrofit().request(GemService.class).getappnum(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<AppNumEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "==================33333333333333333333" + e.getMessage());
//                        Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        getActivity().finish();
                    }

                    @Override
                    public void onNext(AppNumEntity appNumEntity) {
//                        （正常记录条数d1、异常记录条数d2、待处理条数d3、已归档条数d4）
                        SPUtils.set(URLs.NORMALNUM, appNumEntity.getD1());
                        SPUtils.set(URLs.ERRORNUM, appNumEntity.getD2());
                        SPUtils.set(URLs.WAITNUM, appNumEntity.getD3());
                        SPUtils.set(URLs.SUMNUM, appNumEntity.getD4());
                        binding.errorsize.setText("(" + appNumEntity.getD3() + ")");
                    }
                });

    }


    /**
     * 获取消息未读数量
     */
    public void LoadMessage() {
        String url = "notice/loadTotalUnread;jsessionid=" + AppContext.getJsessionid();
        new GRetrofit().request(GemService.class).getmeaage(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<Msg>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "==================4444444444444444444");
                        Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onNext(Msg msg) {

                        int unread = msg.getUnread();
                        if (unread == 0) {
                            binding.circleView.setVisibility(View.INVISIBLE);
                        }
                    }
                });

    }


    private void loadTotalNullResult(int page) {
        GetNullListRequest request = new GetNullListRequest(page);
        String s = request.toString();
        mainListViewModel.getTotalNullResult(request).observe(this, new Observer<GetNullListResponse>() {
            @Override
            public void onChanged(@Nullable GetNullListResponse getNullListResponse) {
                updateListView(getNullListResponse);
            }
        });
    }

    private void updateListView(GetNullListResponse response) {


        if (null == response) {
            Log.i("TAG", "==================22222222222222222");
            Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
            return;
        }
        if (response.getFailure()) {
            Toast.makeText(getActivity(), R.string.network_time_error, Toast.LENGTH_LONG).show();
            return;
        }
        adapterForXj.notifyDataSetChanged(response);
    }

    private void loadTreasuresListReqult(int page) {
        GetTreasuresListRequest request = new GetTreasuresListRequest(page);
        mainListViewModel.getTreasuresListReqult(request).observe(this, new Observer<GetTreasuresListResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresListResponse getTreasuresListResponse) {
                updateTreasuresListView(getTreasuresListResponse);
            }
        });
    }

    private void updateTreasuresListView(GetTreasuresListResponse response) {
        if (null == response) {
            Log.i("TAG", "==================1111111111111");
//            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
            return;
        }
        if (response.getFailure()) {
            Toast.makeText(getActivity(), R.string.network_time_error, Toast.LENGTH_LONG).show();
            return;
        }
        adapterForWw.notifyDataSetChanged(response);
    }

    private void initLocationData() {
        mainListViewModel.getLicationLiveData().observe(getActivity(), new Observer<BDLocation>() {
            @Override
            public void onChanged(@Nullable BDLocation bdLocation) {
                binding.textLocation.setText(bdLocation.getAddrStr());
            }
        });
    }

    public class StopGif implements Runnable {

        @Override
        public void run() {
            if (binding.btnZhiwei.isPlaying())
                binding.btnZhiwei.pause();
            Intent intent = new Intent(getActivity(), TreasuresChoiceActivity.class);
            startActivity(intent);
        }
    }


    public void initListen() {
        binding.imgYlTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyNoticeActivity.class);
                startActivity(intent);

            }
        });

        /**
         *         巡检记录
         */
        binding.lineRcxj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InspectionActivity.class);
                startActivity(intent);


            }
        });
        /**
         * 异常待办
         */
        binding.lineYcxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HandleMatterActivity.class);
                startActivity(intent);
            }
        });
        binding.wwMoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TreasuresListActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 更多巡检
         */
        binding.xjMoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MyNoticeActivity.class);
                Intent intent = new Intent(getActivity(), MoreNoMatterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnZhiwei.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP://松开事件发生后执行代码的区域
                        if (binding.btnZhiwei.isPlaying())
                            binding.btnZhiwei.pause();
                        Intent intent = new Intent(getActivity(), TreasuresChoiceActivity.class);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_DOWN://按住事件发生后执行代码的区域
                        binding.btnZhiwei.play();
                        break;
                    default:
                        break;
                }
                return true;
            }

        });

        binding.framePress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TreasuresChoiceActivity.class);
                startActivity(intent);

            }
        });

    }

    VersionParams.Builder builder;

    public void updateVersion() {
        builder = new VersionParams.Builder()
                .setRequestUrl("https://www.baidu.com")
                .setService(UpdateService.class);

        builder.setDownloadAPKPath("/storage/emulated/0/");

        CustomVersionDialogActivity.customVersionDialogIndex = 2;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isCustomDownloading = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        CustomVersionDialogActivity.isForceUpdate = true;
        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);

        builder.setOnlyDownload(false);

        builder.setShowNotification(true);

        builder.setShowDownloadingDialog(true);


        AllenChecker.startVersionCheck(getActivity().getApplication(), builder.build());

        SPUtils.set("upflag", 1);
    }

}