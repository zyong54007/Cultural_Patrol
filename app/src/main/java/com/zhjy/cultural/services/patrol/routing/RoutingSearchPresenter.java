package com.zhjy.cultural.services.patrol.routing;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.RoutingSearchAdapter;
import com.zhjy.cultural.services.patrol.adapter.TreasuresChoiceAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.SearchRoutingEntity;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

public class RoutingSearchPresenter extends ActPresenter<RoutingSearchPresenter.RoutingSearchUI> implements SearchEditText.OnSearchClickListener {

    //        http://192.168.100.188:8093/wwgl/record/loadAppList;jsessionid=1901BF71129FA95674CB3E1399A49643?
//     record/loadAppList;jsessionid=1901BF71129FA95674CB3E1399A49643?recordType=r&title=慈寿寺&flag=0&pager.offset=0*10
    private RoutingSearchAdapter adapter;
    private List<SearchRoutingEntity.Datas> list;
    private int page = 0;
    private String title;   //搜索框内容

    public interface RoutingSearchUI extends GEMUI {
        ImageView getblack();

        TextView getSearch();

        RecyclerView getrecyclerview();

        SearchEditText getsearchedit();

        TextView getnodate();
    }


    @Override
    public void onUIReady(MVPActivity activity, RoutingSearchUI ui) {
        super.onUIReady(activity, ui);
        getUI().getsearchedit().setOnSearchClickListener(this);
        initAdapter();

    }

    private void RequestSearchDate(String title) {
        int pagesize = page * 10;

        String url = "record/loadAppList;jsessionid=" + AppContext.getJsessionid() + "?recordType=r&title=" + title + "&flag=0&pager.offset=" + pagesize;
        new GRetrofit().request(GemService.class).getSearchRouting(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<SearchRoutingEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getUI().getnodate().setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(SearchRoutingEntity searchRoutingEntity) {
                        List<SearchRoutingEntity.Datas> datas = searchRoutingEntity.getDatas();
                        list.addAll(datas);
                        getUI().getnodate().setVisibility(datas.size() == 0 ? View.VISIBLE : View.GONE);
                        if (datas.size() < 9) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.loadMoreComplete();
                        }
                        adapter.notifyDataSetChanged();

                    }
                });


    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new RoutingSearchAdapter(R.layout.item_search_routing, list);
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);   //动画一直执行
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), InspectionActivity.class);
                intent.putExtra("wwId", list.get(position).getWw().getId());
                intent.putExtra("recordId", list.get(position).getId());
                getActivity().startActivity(intent);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                RequestSearchDate(title);
            }
        }, getUI().getrecyclerview());

        getUI().getrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getUI().getrecyclerview().setAdapter(adapter);


    }



    /**
     * 键盘搜索的监听
     *
     * @param view
     * @param keyword
     */
    @Override
    public void onSearchClick(View view, String keyword) {
        title = keyword;
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showToastMsg("搜索条件不能为空");
            return;
        }
        list.clear();
        RequestSearchDate(keyword);
    }

    /**
     * 搜索的点击事件
     */
    public void Search() {
        title = getUI().getsearchedit().getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showToastMsg("搜索条件不能为空");
            return;
        }
        list.clear();
        RequestSearchDate(title);

    }


}
