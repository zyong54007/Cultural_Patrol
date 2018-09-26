package com.zhjy.cultural.services.patrol.ui.culture;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityCultureMainBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListFragment;
import com.zhjy.cultural.services.patrol.util.BottomNavigationViewHelper;


public class CultureMainActivity extends AacBaseActivity<ActivityCultureMainBinding> {

    private String TAG = "CultureMainActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_culture_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        initListener();
    }

    private void initListener(){
        binding.viewpager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 将当前的页面对应的底部标签设为选中状态
                binding.bottomNavigationBar.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.shouye:
                        // 跳转到对应的页面
                        binding.viewpager.setCurrentItem(0);
                        break;
                    case R.id.daohang:
                        binding.viewpager.setCurrentItem(1);
                        break;
                    case R.id.xunjian:
                        binding.viewpager.setCurrentItem(2);
                        break;
                    case R.id.wode:
                        binding.viewpager.setCurrentItem(3);
                        break;
                }
                // 这里必须返回true才能响应点击事件
                return true;
            }
        });
        //反射关闭动画
        BottomNavigationViewHelper.disableShiftMode(binding.bottomNavigationBar);
    }

    class PageAdapter extends FragmentStatePagerAdapter {

        public PageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment ft = null;
            if (0 == position) {
                ft = MainListFragment.newInstance();
            }else  if (1 == position){
                ft = MainListFragment.newInstance();
            }else  if (2 == position){
                ft = MainListFragment.newInstance();
            }else  if (3 == position){
                ft = MainListFragment.newInstance();
            }
            return ft;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
