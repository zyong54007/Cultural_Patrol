package com.zhjy.cultural.services.patrol;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.adapter.MessageAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.EasySubscriber;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.NoticeList;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * 我的消息
 */
public class MyNoticePresenter extends ActPresenter<MyNoticePresenter.MyNoticeUI> {
    private MessageAdapter msgadapter;
    private List<NoticeList.Datas> datas;

    public interface MyNoticeUI extends GEMUI {
        RecyclerView getrecyclerview();

        SwipeRefreshLayout getswiprefreshlayout();

    }

    @Override
    public void onUIReady(MVPActivity activity, MyNoticeUI ui) {
        super.onUIReady(activity, ui);
        initAdapter();
        requestnetwork(1);

    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        getUI().getrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        msgadapter = new MessageAdapter(R.layout.activity_my_notice_list_item, datas);
        msgadapter.openLoadAnimation();
        msgadapter.isFirstOnly(false);   //动画一直执行
//        initloadlistener();
        msgadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = datas.get(position).getNotice().getId();
                Intent intent = new Intent(getActivity(), NoticeDetailsActivity.class);
                intent.putExtra(Contant.ID, id);
                getActivity().startActivity(intent);
            }
        });
        getUI().getrecyclerview().setAdapter(msgadapter);
    }


    /**
     * 请求消息数据
     */
    public void requestnetwork(int type) {
        String url = "notice/loadNoticeData;jsessionid=" + AppContext.getJsessionid() + "?pager.offset=0";
//        Log.i("TAG", "消息url================================================================" + url);
        new GRetrofit().request(GemService.class).getnotice(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Subscriber<NoticeList>() {
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
                    public void onNext(NoticeList noticeList) {

                        switch (type) {
                            case 1:
                                //首次加载
                                datas = noticeList.getDatas();
                                msgadapter.addData(datas);
                                break;
                            case 2:
//                            刷新
                                datas = new ArrayList<>();
                                datas = noticeList.getDatas();
                                msgadapter.setNewData(datas);
                                getUI().getswiprefreshlayout().setRefreshing(false);
                                break;
                            case 3:
                                msgadapter.loadMoreEnd();
                                break;
                        }


                    }
                });


    }
}
