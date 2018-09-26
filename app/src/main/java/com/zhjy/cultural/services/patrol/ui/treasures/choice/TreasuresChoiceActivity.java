package com.zhjy.cultural.services.patrol.ui.treasures.choice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
//import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
//import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
//import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
//import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresChoiceBinding;
import com.zhjy.cultural.services.patrol.home.TreasuresChoicePresenter;
//import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
//import com.zhjy.cultural.services.patrol.ui.view.EndlessRecyclerViewScrollListener;
import com.zhjy.cultural.services.patrol.ui.view.SearchEditText;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;

import java.util.concurrent.TimeUnit;

/**
 * 文物列表
 */
public class TreasuresChoiceActivity extends BaseActivity<TreasuresChoicePresenter.TreasuresChoiceUI, TreasuresChoicePresenter> implements TreasuresChoicePresenter.TreasuresChoiceUI {

    @Override
    public int getContentLayout() {
        return R.layout.activity_treasures_choice;
    }

    @Override
    protected TreasuresChoicePresenter.TreasuresChoiceUI createUI() {
        return this;
    }

    @Override
    protected TreasuresChoicePresenter createPresenter() {
        return new TreasuresChoicePresenter();
    }

    @Override
    protected void initViews() {
        Topbar topbar = getUI().finder().find(R.id.topbar);
        topbar.setRightIv(getResources().getDrawable(R.mipmap.search_find_ico));
        topbar.setrightlistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUI().gettvnodate().setVisibility(View.GONE);
                getUI().getLinearlayout().setVisibility(getUI().getLinearlayout().getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                getUI().gettvnodate().setVisibility(getUI().getLinearlayout().getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                getPresenter().showDate();
            }
        });


        RxView.clicks(getUI().getSearch()).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().Search();
        });
    }

    @Override
    public SearchEditText getSearchEdit() {
        return getUI().finder().find(R.id.treasureschoic_search);
    }

    @Override
    public ImageView getSearch() {
        return getUI().finder().imageView(R.id.search_iv);
    }

    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.recycler_list);
    }

    @Override
    public LinearLayout getLinearlayout() {
        return getUI().finder().find(R.id.treasureschoic_search_ll);
    }

    @Override
    public TextView gettvnodate() {
        return getUI().finder().find(R.id.nodate);
    }


}


//    http://192.168.100.188:8093/wwgl/wwInfo/listInterface;jsessionid=C6BE5DDFF63FF550B488AC08A6F77B80?wwType=0&pager.offset=2*9   //监管的文物列表
//    http://192.168.100.188:8093/wwgl/wwInfo/listInterface;jsessionid=C6BE5DDFF63FF550B488AC08A6F77B80?wwType=0&title=慈寿寺&pager.offset=0*9


