package com.zhjy.cultural.services.patrol.ui.treasures.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
//import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresListBinding;
import com.zhjy.cultural.services.patrol.mine.presenter.TreasuresListPresenter;
import com.zhjy.cultural.services.patrol.mine.ui.SearchTreasuresActivity;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.view.FadePageTransformer;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 我的文物
 */
public class TreasuresListActivity extends BaseActivity<TreasuresListPresenter.TreasuresListUI, TreasuresListPresenter> implements TreasuresListPresenter.TreasuresListUI {
    @Override
    public int getContentLayout() {
        return R.layout.activity_treasures_list;
    }

    @Override
    protected TreasuresListPresenter.TreasuresListUI createUI() {
        return this;
    }

    @Override
    protected TreasuresListPresenter createPresenter() {
        return new TreasuresListPresenter();
    }

    @Override
    protected void initViews() {
        Topbar topbar = getUI().finder().find(R.id.topbar);
        topbar.setRightIv(getResources().getDrawable(R.mipmap.search_find_ico));
        topbar.setrightlistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().StartSearch();
            }
        });

        RxView.clicks(getUI().finder().find(R.id.treasures_rb_count)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().showTotal();
        });
        RxView.clicks(getUI().finder().find(R.id.treasures_rb_country)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().showcountry();
        });
        RxView.clicks(getUI().finder().find(R.id.treasures_rb_city)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().showCity();
        });
        RxView.clicks(getUI().finder().find(R.id.treasures_rb_county)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().showcounty();
        });
        RxView.clicks(getUI().finder().find(R.id.treasures_rb_record)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().showrecord();
        });

    }

    @Override
    public RecyclerView getrecyclerview() {
        return getUI().finder().find(R.id.recycler_list);
    }

    @Override
    public TextView getnodate() {
        return getUI().finder().find(R.id.nodate);
    }

    @Override
    public RadioButton getrb() {
        return  getUI().finder().find(R.id.treasures_rb_count);
    }


//        AacBaseActivity<ActivityTreasuresListBinding> {


}
//
//    private static String TAG = "TreasuresListActivity";
//
//    private int currentFragmentIndex = 0;
//
//    private ArrayList<Fragment> mFragments;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_treasures_list;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();
//        initViewPage();
//        initListener();
//    }
//
//    private void initView() {
//
//    }
//
//
//    public void StartSearch() {
//        Intent intent = new Intent(this, SearchTreasuresActivity.class);
//        startActivity(intent);
//    }
//
//
//    private void initViewPage() {
//
//        mFragments = new ArrayList<>();
//        TreasuresListFragment ftqb = TreasuresListFragment.newInstance(0);
//        mFragments.add(ftqb);
//        TreasuresListFragment ftgj = TreasuresListFragment.newInstance(1);
//        mFragments.add(ftgj);
//        TreasuresListFragment ftbj = TreasuresListFragment.newInstance(2);
//        mFragments.add(ftbj);
//        TreasuresListFragment fthd = TreasuresListFragment.newInstance(3);
//        mFragments.add(fthd);
//        TreasuresListFragment ftpc = TreasuresListFragment.newInstance(4);
//        mFragments.add(ftpc);
//
//        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
//        binding.mViewPager.setPageTransformer(true, new FadePageTransformer());
//        binding.mViewPager.setAdapter(adapter);
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//
//    private class PageAdapter extends FragmentStatePagerAdapter {
//
//        public PageAdapter(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//    }
//
//    private void setLineWwqbTabChekc() {
//        binding.lineWwqbTab.setBackgroundResource(R.mipmap.ww_list_ico_b);
//        binding.lineWwgjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwbjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwhdTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwpcTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        currentFragmentIndex = 0;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setLineWwgjTabCheck() {
//        binding.lineWwqbTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwgjTab.setBackgroundResource(R.mipmap.ww_list_ico_b);
//        binding.lineWwbjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwhdTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwpcTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        currentFragmentIndex = 1;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setLineWwbjTabCheck() {
//        binding.lineWwqbTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwgjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwbjTab.setBackgroundResource(R.mipmap.ww_list_ico_b);
//        binding.lineWwhdTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwpcTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        currentFragmentIndex = 2;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setLineWwhdTabCheck() {
//        binding.lineWwqbTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwgjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwbjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwhdTab.setBackgroundResource(R.mipmap.ww_list_ico_b);
//        binding.lineWwpcTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        currentFragmentIndex = 3;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setLineWwpcTabCheck() {
//        binding.lineWwqbTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwgjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwbjTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwhdTab.setBackgroundResource(R.mipmap.ww_list_ico_a);
//        binding.lineWwpcTab.setBackgroundResource(R.mipmap.ww_list_ico_b);
//        currentFragmentIndex = 4;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void onTitleSearch() {
//        if (null == mFragments) {
//            return;
//        }
//        int index = binding.mViewPager.getCurrentItem();
//        Fragment mFragment = mFragments.get(index);
//        if (null == mFragment) {
//            return;
//        }
//        if (mFragment instanceof TreasuresListFragment) {
//            TreasuresListFragment mTreasuresListFragment = (TreasuresListFragment) mFragment;
//            String titleName = binding.editSearch.getText().toString().trim();
//            mTreasuresListFragment.mOnTitleNameSearchListener.OnTitleNameSearchListener(titleName);
//        }
//    }
//
//    private void initListener() {
//        binding.imgeBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        binding.lineWwqbTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setLineWwqbTabChekc();
//            }
//        });
//
//        binding.lineWwgjTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setLineWwgjTabCheck();
//            }
//        });
//
//        binding.lineWwbjTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setLineWwbjTabCheck();
//            }
//        });
//        binding.lineWwhdTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setLineWwhdTabCheck();
//            }
//        });
//
//        binding.lineWwpcTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setLineWwpcTabCheck();
//            }
//        });
//        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                StartSearch();
////                onTitleSearch();
//            }
//        });
//    }
//}
