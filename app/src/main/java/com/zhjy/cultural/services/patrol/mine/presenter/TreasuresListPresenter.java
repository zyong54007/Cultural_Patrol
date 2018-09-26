package com.zhjy.cultural.services.patrol.mine.presenter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.TreaseListAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.SearchTreasuresEntity;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.mine.ui.SearchTreasuresActivity;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

public class TreasuresListPresenter extends ActPresenter<TreasuresListPresenter.TreasuresListUI> {
    private int page = 0;
//    http://192.168.100.188:8093/wwgl/wwInfo/listInterface;jsessionid=03E6E7769FB879050FB9692C3CD8EF63?wwType=0&pager.offset=0*9

    public interface TreasuresListUI extends GEMUI {
        RecyclerView getrecyclerview();

        TextView getnodate();

        RadioButton getrb();
    }

    private List<TreasuresEntity.Datas> list;   //容器
    private List<TreasuresEntity.Datas> totallist;
    private List<TreasuresEntity.Datas> countrylist;
    private List<TreasuresEntity.Datas> citylist;
    private List<TreasuresEntity.Datas> countyist;
    private List<TreasuresEntity.Datas> recordlist;
    private TreaseListAdapter adapter;


    @Override
    public void onUIReady(MVPActivity activity, TreasuresListUI ui) {
        super.onUIReady(activity, ui);
        initAdapter();
        RequestDate();
    }


    public void StartSearch() {
        Intent intent = new Intent(getActivity(), SearchTreasuresActivity.class);
        getActivity().startActivity(intent);
    }

    /**
     * 全部
     */
    public void showTotal() {
        list.clear();
        list.addAll(totallist);
        if (list.size() == 0) {
            getUI().getnodate().setVisibility(View.VISIBLE);
        } else {
            getUI().getnodate().setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 国家
     */
    public void showcountry() {
        list.clear();
        countrylist.clear();
        for (TreasuresEntity.Datas data : totallist) {
            int wwType = data.getWwType();
            if (wwType == 1) {
                countrylist.add(data);
            }
        }

        list.addAll(countrylist);
        if (list.size() == 0) {
            getUI().getnodate().setVisibility(View.VISIBLE);
        } else {
            getUI().getnodate().setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }


    /**
     * 市
     */
    public void showCity() {
        list.clear();
        citylist.clear();
        for (TreasuresEntity.Datas data : totallist) {
            int wwType = data.getWwType();
            if (wwType == 2) {
                citylist.add(data);
            }
        }
        list.addAll(citylist);
        if (list.size() == 0) {
            getUI().getnodate().setVisibility(View.VISIBLE);
        } else {
            getUI().getnodate().setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }


    /**
     * 曲县
     */
    public void showcounty() {
        list.clear();
        countyist.clear();
        for (TreasuresEntity.Datas data : totallist) {
            int wwType = data.getWwType();
            if (wwType == 3) {
                countyist.add(data);
            }
        }

        list.addAll(countyist);
        if (list.size() == 0) {
            getUI().getnodate().setVisibility(View.VISIBLE);
        } else {
            getUI().getnodate().setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }


    /**
     * 普查
     */
    public void showrecord() {
        list.clear();
        recordlist.clear();

        for (TreasuresEntity.Datas data : totallist) {
            int wwType = data.getWwType();
            if (wwType == 4) {
                recordlist.add(data);
            }
        }

        list.addAll(recordlist);
        if (list.size() == 0) {
            getUI().getnodate().setVisibility(View.VISIBLE);
        } else {
            getUI().getnodate().setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }


    private void initAdapter() {
        getUI().getrb().setChecked(true);
        list = new ArrayList<>();
        totallist = new ArrayList<>();
        countrylist = new ArrayList<>();
        citylist = new ArrayList<>();
        countyist = new ArrayList<>();
        recordlist = new ArrayList<>();
        adapter = new TreaseListAdapter(R.layout.item_treasures_list, list);
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
                RequestDate();
            }
        }, getUI().getrecyclerview());

        getUI().getrecyclerview().setLayoutManager(new LinearLayoutManager(getActivity()));
        getUI().getrecyclerview().setAdapter(adapter);
    }

    private void RequestDate() {
        int pagesize = page * 9;
        String url = "wwInfo/listInterface;jsessionid=" + AppContext.getJsessionid() + "?wwType=0&pager.offset=" + pagesize;
        Log.i("TAG", "==============" + url);
        new GRetrofit().request(GemService.class).getTreasuresList(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<TreasuresEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TreasuresEntity treasuresEntity) {
                        List<TreasuresEntity.Datas> datas = treasuresEntity.getDatas();
                        list.addAll(datas);
                        totallist.addAll(datas);
                        if (datas.size() < 9) {
                            adapter.loadMoreEnd();
                        } else {
                            adapter.loadMoreComplete();
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

    }


}
