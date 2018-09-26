package com.zhjy.cultural.services.patrol.mine.presenter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.RoutingSearchAdapter;
import com.zhjy.cultural.services.patrol.adapter.SearchTreasuresAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.InSpecListEntity;
import com.zhjy.cultural.services.patrol.bean.SearchRoutingEntity;
import com.zhjy.cultural.services.patrol.bean.SearchTreasuresEntity;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

public class SearchTreasuresPresenter extends ActPresenter<SearchTreasuresPresenter.SearchTreasuresUI> implements SearchEditText.OnSearchClickListener {

//    http://192.168.100.188:8093/wwgl/wwInfo/listInterface;jsessionid=FA323DFB78C7116E4BC17CFF8A07BB15?wwType=0&pager.offset=0*9


    private int page = 0;


    private String title;

    private SearchTreasuresAdapter adapter;

    private List<SearchTreasuresEntity.Datas> list;

    public interface SearchTreasuresUI extends GEMUI {
        SearchEditText getsearchedit();

        TextView getnodate();

        RecyclerView getrecyclerview();
    }


    @Override
    public void onUIReady(MVPActivity activity, SearchTreasuresUI ui) {
        super.onUIReady(activity, ui);

        getUI().getsearchedit().setOnSearchClickListener(this);
        initAdapter();
    }

    /**
     * 搜索监听
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
        SearchDate(keyword);

    }


    /**
     * 搜索
     */
    public void Search() {
        String title = getUI().getsearchedit().getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showToastMsg("搜索条件不能为空");
            return;
        }
        list.clear();
        SearchDate(title);


    }


    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new SearchTreasuresAdapter(R.layout.item_search_treasures, list);
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);   //动画一直执行
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), TreasuresActivity.class);
                intent.putExtra("id", list.get(position).getId());
                getActivity().startActivity(intent);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                SearchDate(title);
            }
        }, getUI().getrecyclerview());

        getUI().getrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getUI().getrecyclerview().setAdapter(adapter);


    }


    /**
     * 搜索数据
     */
    private void SearchDate(String title) {
        int pagesize = page * 10;
//      wwInfo/listInterface;jsessionid=FA323DFB78C7116E4BC17CFF8A07BB15?wwType=0&title=挨收拾&pager.offset=0*9
        String url = "wwInfo/listInterface;jsessionid=" + AppContext.getJsessionid() + "?wwType=0&title=" + title + "&pager.offset=" + pagesize;
        Log.i("TAG", "我的文物搜索=============" + url);
        new GRetrofit().request(GemService.class).SearchTreasures(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<SearchTreasuresEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        getUI().getnodate().setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onNext(SearchTreasuresEntity searchTreasuresEntity) {
                        List<SearchTreasuresEntity.Datas> datas = searchTreasuresEntity.getDatas();
                        list.addAll(datas);
                        if (datas.size() < 10) {
                            getUI().getnodate().setVisibility(View.GONE);
                            adapter.loadMoreEnd();
                        } else {
                            getUI().getnodate().setVisibility(View.GONE);

                            adapter.loadMoreEnd();
                        }
                        if (datas == null || datas.size() == 0) {
                            getUI().getnodate().setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
    }

}
