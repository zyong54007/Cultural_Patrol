package com.zhjy.cultural.services.patrol.ui.home.inspection;


import android.view.View;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseFragment;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.routing.InspectionPresenter;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by jialg on 2018/1/24.
 * 巡检
 */
public class InspectionFragment extends BaseFragment<InspectionPresenter.InspectionUI, InspectionPresenter> implements InspectionPresenter.InspectionUI {

    @Override
    protected InspectionPresenter createPresenter() {
        return new InspectionPresenter();
    }

    @Override
    protected InspectionPresenter.InspectionUI createUI() {
        return this;
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

        getPresenter().initFragment();

        RxView.clicks(getUI().finder().find(R.id.inspec_rb_normal)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().setFragment();
        });
        RxView.clicks(getUI().finder().find(R.id.inspec_rb_error)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            getPresenter().setFragmentError();
        });


        getUI().finder().radiobutton(R.id.inspec_rb_normal).setText("巡检记录(" + SPUtils.get(URLs.NORMALNUM, 0) + ")");
        getUI().finder().radiobutton(R.id.inspec_rb_error).setText("巡检记录(" + SPUtils.get(URLs.ERRORNUM, 0) + ")");


    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_main_inspection;
    }


}


//    private static String TAG = "InspectionRecordFragment";
//
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
//    public static InspectionFragment newInstance() {
//        InspectionFragment instance = new InspectionFragment();
//        return instance;
//    }
//
//    @Override
//    protected int getFragmentLayout() {
//        return R.layout.fragment_main_inspection;
//    }
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
//        hashCode = hashCode();
//        initViewPage();
//        initListen();
//        EventBus.getDefault().register(this);
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
//        PageAdapter adapter = new PageAdapter(getChildFragmentManager());
//        binding.mViewPager.setPageTransformer(true, new FadePageTransformer());
//        binding.mViewPager.setAdapter(adapter);
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
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
//        binding.lineFlag.setVisibility(View.GONE);
//        binding.lineXjjlTab.setBackgroundResource(R.mipmap.xj_top_w_bg);
//        binding.lineXjycTab.setBackgroundResource(R.mipmap.xj_top_o_bg_a);
//        currentFragmentIndex = 0;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//
//    }
//
//    private void setYcjlCheck() {
//        binding.lineFlag.setVisibility(View.VISIBLE);
//        binding.lineXjjlTab.setBackgroundResource(R.mipmap.xj_top_o_bg_b);
//        binding.lineXjycTab.setBackgroundResource(R.mipmap.xj_top_w_bg);
//
//        currentFragmentIndex = 1;
//        binding.mViewPager.setCurrentItem(currentFragmentIndex);
//    }
//
//    private void setQbStatus() {
//        setTabChange(0);
//
//        binding.tabQbStatus.setTextColor(Color.parseColor("#ffffff"));
//        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
//
//        binding.tabDclStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//        binding.tabYgdStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//    }
//
//    private void setDclStatus() {
//        setTabChange(1);
//
//        binding.tabQbStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//        binding.tabDclStatus.setTextColor(Color.parseColor("#ffffff"));
//        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
//
//        binding.tabYgdStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//    }
//
//    private void setYgdStatus() {
//        setTabChange(2);
//
//        binding.tabQbStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabQbStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//        binding.tabDclStatus.setTextColor(Color.parseColor("#844949"));
//        binding.tabDclStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_b);
//
//        binding.tabYgdStatus.setTextColor(Color.parseColor("#ffffff"));
//        binding.tabYgdStatus.setBackgroundResource(R.drawable.xj_list_btn_tab_a);
//    }
//
//    private void setTabChange(int flag) {
//        if (mFragments != null && mFragments.size() >= 1) {
//            Fragment mFragment = mFragments.get(1);
//            if (mFragment instanceof InspectionErrorFragment) {
//                InspectionErrorFragment fragment = (InspectionErrorFragment) mFragment;
//                fragment.mOnTabTextChangeListener.OnChangeListener(flag);
//            }
//        }
//    }
//
//    private void setSearchStatus() {
//        int visiStatus = binding.frameSearch.getVisibility();
//        if (View.GONE == visiStatus)
//            binding.frameSearch.setVisibility(View.VISIBLE);
//        if (View.VISIBLE == visiStatus)
//            binding.frameSearch.setVisibility(View.GONE);
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
//        if (mFragment instanceof InspectionRecordFragment) {
//            InspectionRecordFragment mInspectionRecordFragment = (InspectionRecordFragment) mFragment;
//            String titleName = binding.editSearch.getText().toString().trim();
//            mInspectionRecordFragment.mOnTitleNameSearchListener.OnTitleNameSearchListener(titleName);
//        } else if (mFragment instanceof InspectionErrorFragment) {
//            InspectionErrorFragment mInspectionErrorFragment = (InspectionErrorFragment) mFragment;
//            String titleName = binding.editSearch.getText().toString().trim();
//            mInspectionErrorFragment.mOnTitleNameSearchListener.OnTitleNameSearchListener(titleName);
//        }
//    }
//
//    private void initListen() {
//        binding.textAddXj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), TreasuresChoiceActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        binding.lineXjjlTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setXjjlCheck();
//            }
//        });
//
//        binding.lineXjycTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setYcjlCheck();
//            }
//        });
//
//        binding.tabQbStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setQbStatus();
//            }
//        });
//
//        binding.tabDclStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setDclStatus();
//            }
//        });
//
//        binding.tabYgdStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setYgdStatus();
//            }
//        });
//
//        /**
//         * 左上角搜索按钮
//         */
//        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), RoutingSearchActivity.class);
//                startActivity(intent);
//
////                setSearchStatus();
//            }
//        });
//
//        binding.imgStartSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onTitleSearch();
//            }
//        });
//
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void Event(MessageEvent messageEvent) {
//        if (messageEvent.getType() == 1) {
//
//            int num = messageEvent.getNum();
//            binding.xjnum.setText("巡检记录(" + num + ")");
//        } else {
//            int num = messageEvent.getNum();
//            int handlenum = messageEvent.getHandlenum();
//            int completennum = messageEvent.getCompletennum();
////            tab_dcl_status
////             tab_dcl_status
//            binding.tabDclStatus.setText("待处理(" + handlenum + ")");
//            binding.tabYgdStatus.setText("已归档(" + completennum + ")");
//            binding.wwerrornum.setText("文物异常记录(" + num + ")");
//        }
//
//    }
////
////    @Subscribe(threadMode = ThreadMode.MAIN)
////    public void EventError(MessageEvent messageEvent) {
////        if (messageEvent.getType() == 2) {
////            int num = messageEvent.getNum();
////            int type = messageEvent.getType();
////            int completennum = messageEvent.getCompletennum();
////            binding.wwerrornum.setText("文物异常记录(" + num + ")");
//////            binding.tabdclstatus.setText("待处理(" + type + ")");
//////            binding.tabygdstatus.setText("已归档(" + completennum + ")");
////
////        }
////
////    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//    }
//}
