package com.zhjy.cultural.services.patrol.ui.home.inspection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.Main.InspectionPresenter;
import com.zhjy.cultural.services.patrol.R;
//import com.zhjy.cultural.services.patrol.databinding.FragmentMainInspectionBinding;
import com.zhjy.cultural.services.patrol.base.BaseActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceActivity;
import com.zhjy.cultural.services.patrol.ui.view.FadePageTransformer;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class InspectionActivity extends BaseActivity<InspectionPresenter.InspectionUI, InspectionPresenter> implements InspectionPresenter.InspectionUI {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_main_inspection);
//    }

    @Override
    protected InspectionPresenter.InspectionUI createUI() {
        return this;
    }

    @Override
    protected InspectionPresenter createPresenter() {
        return new InspectionPresenter();
    }

    @Override
    protected void initViews() {
        Topbar topbar = getUI().finder().find(R.id.topbar);
        topbar.setLeftSearchvisible();
        topbar.setLeftSearchListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startSearch();

            }
        });
        topbar.setrightTvListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().StartAdd();
            }
        });

//        getPresenter().initFragment();
//
//        RxView.clicks(getUI().finder().find(R.id.inspec_rb_normal)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
//            getPresenter().setFragment();
//        });
//        RxView.clicks(getUI().finder().find(R.id.inspec_rb_error)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
//            getPresenter().setFragmentError();
//        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_main_inspection;
    }

    @Override
    public RadioGroup getTabLayout() {
        return getUI().finder().find(R.id.inspec_rg_tab);
    }
}

//    private int hashCode;
//
//    private int type = 0;
//
//    private int status = 0;
//
//    private int currentFragmentIndex = 0;
//
//    private ArrayList<Fragment> mFragments;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_main_inspection;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        init();
//    }
//
//    private void init() {
//        Intent intent = getIntent();
//        int index = intent.getIntExtra("index", 0);
//        if (1 == index) {
//            setYcjlCheck();
//        }
////        binding.imgSearch.setImageDrawable(getResources().getDrawable(R.mipmap.back_left_ico));
//        initViewPage();
//        initListen();
//    }
//
//    private void initViewPage() {
//        if (null == mFragments) {
//            mFragments = new ArrayList<>();
//            InspectionRecordFragment mInspectionRecordFragment = InspectionRecordFragment.newInstance();
//            mFragments.add(mInspectionRecordFragment);
//
//            InspectionErrorFragment mInspectionErrorFragment = InspectionErrorFragment.newInstance();
//            mFragments.add(mInspectionErrorFragment);
//        }
//
//        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
////        binding.mViewPager.setPageTransformer(true, new FadePageTransformer());
////        binding.mViewPager.setAdapter(adapter);
////        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
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
//    private void setXjjlCheck() {
////        binding.lineXjjlTab.setBackgroundResource(R.mipmap.xj_top_w_bg);
////        binding.lineXjycTab.setBackgroundResource(R.mipmap.xj_top_o_bg_a);
//
//        currentFragmentIndex = 0;
////        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//
//    }
//
//    private void setYcjlCheck() {
////        binding.lineXjjlTab.setBackgroundResource(R.mipmap.xj_top_o_bg_b);
////        binding.lineXjycTab.setBackgroundResource(R.mipmap.xj_top_w_bg);
//
//        currentFragmentIndex = 1;
////        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setQbStatus() {
//
////        binding.tabQbStatus.setTextColor(Color.parseColor("#ffffff"));
////        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
////
////        binding.tabDclStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
////
////        binding.tabYgdStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//    }
//
//    private void setDclStatus() {
////        binding.tabQbStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
////
////        binding.tabDclStatus.setTextColor(Color.parseColor("#ffffff"));
////        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
////
////        binding.tabYgdStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//    }
//
//    private void setYgdStatus() {
////        binding.tabQbStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
////
////        binding.tabDclStatus.setTextColor(Color.parseColor("#844949"));
////        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
////
////        binding.tabYgdStatus.setTextColor(Color.parseColor("#ffffff"));
////        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
//    }
//
//    private void setSearchStatus() {
////        int visiStatus = binding.frameSearch.getVisibility();
////        if (View.GONE == visiStatus)
////            binding.frameSearch.setVisibility(View.VISIBLE);
////        if (View.VISIBLE == visiStatus)
////            binding.frameSearch.setVisibility(View.GONE);
//    }
//
//    private void onTitleSearch() {
////        if (null == mFragments) {
////            return;
////        }
////        int index = binding.mViewPager.getCurrentItem();
////        Fragment mFragment = mFragments.get(index);
////        if (null == mFragment) {
////            return;
////        }
////        if (mFragment instanceof InspectionRecordFragment) {
////            InspectionRecordFragment mInspectionRecordFragment = (InspectionRecordFragment) mFragment;
////            String titleName = binding.editSearch.getText().toString().trim();
////            mInspectionRecordFragment.mOnTitleNameSearchListener.OnTitleNameSearchListener(titleName);
////        } else if (mFragment instanceof InspectionErrorFragment) {
////            InspectionErrorFragment mInspectionErrorFragment = (InspectionErrorFragment) mFragment;
////            String titleName = binding.editSearch.getText().toString().trim();
////            mInspectionErrorFragment.mOnTitleNameSearchListener.OnTitleNameSearchListener(titleName);
////        }
//    }
//
//    private void initListen() {
////        binding.textAddXj.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(InspectionActivity.this, TreasuresChoiceActivity.class);
////                ;
////                startActivity(intent);
////            }
////        });
////
////        binding.lineXjjlTab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                setXjjlCheck();
////            }
////        });
////
////        binding.lineXjycTab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                setYcjlCheck();
////            }
////        });
////
////        binding.tabQbStatus.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                setQbStatus();
////            }
////        });
////
////        binding.tabDclStatus.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                setDclStatus();
////            }
////        });
////
////        binding.tabYgdStatus.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                setYgdStatus();
////            }
////        });
////
////        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                setSearchStatus();
////                finish();
////            }
////        });
////        binding.imgStartSearch.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                onTitleSearch();
////            }
////        });
//    }
//
//}
