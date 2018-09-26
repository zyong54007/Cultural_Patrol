package com.zhjy.cultural.services.patrol.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.adapter.TreasuresChoiceAdapter;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.bean.TreasuresEntity;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * 开始巡检进入的我的文物列表
 */
public class TreasuresChoicePresenter extends ActPresenter<TreasuresChoicePresenter.TreasuresChoiceUI> implements SearchEditText.OnSearchClickListener {


    private int page = 0;

    private List<TreasuresEntity.Datas> list;  //数据集合

    private TreasuresChoiceAdapter adapter;


    public interface TreasuresChoiceUI extends GEMUI {

        SearchEditText getSearchEdit();

        ImageView getSearch();

        RecyclerView getrecyclerview();

        LinearLayout getLinearlayout();

        TextView gettvnodate();

    }

    @Override
    public void onUIReady(MVPActivity activity, TreasuresChoiceUI ui) {
        super.onUIReady(activity, ui);
        initAdapter();
        RequestDate();
        getUI().getSearchEdit().setOnSearchClickListener(this);

    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new TreasuresChoiceAdapter(R.layout.item_treasures_rc, list);
        adapter.openLoadAnimation();
        adapter.isFirstOnly(false);   //动画一直执行
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//                Intent intent = new Intent(getActivity(), TreasuresActivity.class);
//                intent.putExtra("id", list.get(position).getId());
//                getActivity().startActivity(intent);

                Intent intent = new Intent(getActivity(), PunchClockActivity.class);
                intent.putExtra("wwId", list.get(position).getId());
                intent.putExtra(Contant.POINT, list.get(position).getPoint());
                getActivity().startActivity(intent);
//                getActivity().finish();

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


    private List<TreasuresEntity.Datas> tlist;

    /**
     * 搜索框的处理
     */
    public void showDate() {

        //判断搜索框的状态显示数据
        if (getUI().getLinearlayout().getVisibility() == View.VISIBLE) {   //搜索框显示 不做操作
            tlist = new ArrayList<>();
            tlist.addAll(list);
        } else {    //搜索框隐藏  显示默认加载的列表数据
            list.addAll(tlist);
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * 键盘搜索的监听
     *
     * @param view
     */

    @Override
    public void onSearchClick(View view, String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            ToastUtil.showToastMsg("搜索不能为空");
            return;
        }
        SearchDate(keyword);
    }

    /**
     * 搜索事件
     */
    public void Search() {
        String title = getUI().getSearchEdit().getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showToastMsg("搜索不能为空");
            return;
        }
        SearchDate(title);
    }


    /**
     * 加载数据
     */
    private void RequestDate() {
        int pagesize = page * 9;
        Log.i("TAG", "============" + pagesize);
        String url = "wwInfo/listInterface;jsessionid=" + AppContext.getJsessionid() + "?wwType=0&pager.offset=" + pagesize;
        Log.i("TAG", "开始巡检================" + url);
        new GRetrofit().request(GemService.class).getTreasureslist(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<TreasuresEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                        getActivity().finish();
//                        MainActivity.getInstance().finish();

                    }

                    @Override
                    public void onNext(TreasuresEntity treasuresEntity) {
                        int pageSize = treasuresEntity.getPageSize();
                        int total = treasuresEntity.getTotal();
                        List<TreasuresEntity.Datas> datas = treasuresEntity.getDatas();
                        list.addAll(datas);


                        if (datas.size() < 9) {
                            adapter.loadMoreEnd();
                        } else if (datas.size() == 9) {
                            adapter.loadMoreComplete();
                        }

                        if (list.size() == 0) {
                            getUI().gettvnodate().setVisibility(View.VISIBLE);
                        }

                        adapter.notifyDataSetChanged();


                    }
                });


    }


    /**
     * 搜索数据
     *
     * @param title
     */
    public void SearchDate(String title) {
        page = 0;
        int pagesize = page * 9;
        Log.i("TAG", "============" + pagesize);
        String url = "wwInfo/listInterface;jsessionid=" + AppContext.getJsessionid() + "?wwType=0&title=" + title + "&pager.offset=" + pagesize;
        Log.i("TAG", "开始巡检================" + url);
        new GRetrofit().request(GemService.class).getTreasureslist(url)
                .compose(GRetrofit.observeOnMainThread(getUI()))
                .subscribe(new Observer<TreasuresEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        getUI().gettvnodate().setVisibility(View.VISIBLE);
                        Log.i("TAG", "=========================" + e.getMessage());
                    }

                    @Override
                    public void onNext(TreasuresEntity treasuresEntity) {
                        int pageSize = treasuresEntity.getPageSize();
                        int total = treasuresEntity.getTotal();
                        list.clear();
                        List<TreasuresEntity.Datas> datas = treasuresEntity.getDatas();
                        list.addAll(datas);
//                        if (datas.size() < 9) {
                        adapter.loadMoreEnd();
//                        } else if (datas.size() == 9) {
//                            adapter.loadMoreComplete();
//                        }

                        Log.i("TAG", "==============" + pageSize + "===========" + total);
                        adapter.notifyDataSetChanged();


                    }
                });


    }
}
