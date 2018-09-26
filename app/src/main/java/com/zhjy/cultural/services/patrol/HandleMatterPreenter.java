package com.zhjy.cultural.services.patrol;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.HandleMatter;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityActivity;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

public class HandleMatterPreenter extends ActPresenter<HandleMatterPreenter.HandleMatterUI> {

    private List<HandleMatter> list;
    private HandmatterAdapter adapter;

    public interface HandleMatterUI extends GEMUI {
        RecyclerView getRecyclerview();

        TextView getnodate();
    }

    @Override
    public void onUIReady(MVPActivity activity, HandleMatterUI ui) {
        super.onUIReady(activity, ui);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        RequestDate();

    }

    private void initAdapter() {
        list = new ArrayList<>();
        getUI().getRecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HandmatterAdapter(R.layout.item_handle_matter, list);
        adapter.openLoadAnimation();   //开启动画
        adapter.isFirstOnly(false);   //动画一直执行
        getUI().getRecyclerview().setAdapter(adapter);
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

    private void RequestDate() {
        String url = "record/loadMessage;jsessionid=" + AppContext.getJsessionid();
        new GRetrofit().request(GemService.class).getHomeErrorMatter(url).compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<List<HandleMatter>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onNext(List<HandleMatter> handleMatters) {
                        list.addAll(handleMatters);

                        if (handleMatters.size() == 0) {
                            getUI().getnodate().setVisibility(View.VISIBLE);
                        } else {
                            SPUtils.set(URLs.WAITNUM, handleMatters.size());
                        }

                        adapter.loadMoreEnd();
                        adapter.notifyDataSetChanged();

                    }
                });
    }
}
