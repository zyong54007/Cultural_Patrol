package com.baidu.mapapi.baiduapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.clusterutil.projection.SecondBean;
import com.baidu.mapapi.clusterutil.ui.SyncHorizontalScrollView;
import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("HandlerLeak")
public class MapFragment extends FragmentActivity implements OnClickListener {
	public static final String ARGUMENTS_NAME = "arg";
	public static final String TITLE = "title";
	public static final String ID = "id";
	public static final String IMG = "img";
	public static final String POS = "pos";
	private RelativeLayout rl_nav;
	private SyncHorizontalScrollView mHsv;
	private RadioGroup rg_nav_content;
	private ImageView iv_nav_indicator;
	private ImageView iv_nav_left;
	private ImageView iv_nav_right;
	private ViewPager mViewPager;
	private int indicatorWidth;
	private LayoutInflater mInflater;
	// 本地缓存map key=id value=网络返回对象
	protected Map<String, String> localMap = new HashMap<String, String>();
	public PageAdapter adapter;
	private int currentIndicatorLeft = 0;
	private RelativeLayout rl_tab;
	public List<SecondBean> secondList;
	private MapFragment mActivity = this;
	// 当前Fragment的索引
	private int currentFragmentIndex = 0;
	private ImageView titleBack;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_perimeter_navigation);
		titleBack = (ImageView) findViewById(R.id.title_back);
		titleBack.setOnClickListener(this);
		title = (TextView) findViewById(R.id.title);
		rl_tab = (RelativeLayout) findViewById(R.id.rl_tab);
		findViewById();
		super.onCreate(savedInstanceState);
	}

	private void setListener() {

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (rg_nav_content != null
						&& rg_nav_content.getChildCount() > position) {
					((RadioButton) rg_nav_content.getChildAt(position))
							.performClick();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		rg_nav_content
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						if (rg_nav_content.getChildAt(checkedId) != null) {
							rg_nav_content.getChildAt(0).isClickable();
							TranslateAnimation animation = new TranslateAnimation(
									currentIndicatorLeft,
									((RadioButton) rg_nav_content
											.getChildAt(checkedId)).getLeft(),
									0f, 0f);
							animation.setInterpolator(new LinearInterpolator());
							animation.setDuration(100);
							animation.setFillAfter(true);
							// 执行位移动画
							iv_nav_indicator.startAnimation(animation);
							mViewPager.setCurrentItem(checkedId); // ViewPager
																	// 跟随一起 切换
							// 记录当前 下标的距最左侧的 距离
							currentIndicatorLeft = ((RadioButton) rg_nav_content
									.getChildAt(checkedId)).getLeft();
							currentFragmentIndex = checkedId;

							/*mHsv.smoothScrollTo(
									(checkedId == 1 ? ((RadioButton) rg_nav_content
											.getChildAt(checkedId)).getLeft()
											: 0)
											- ((RadioButton) rg_nav_content
													.getChildAt(0)).getLeft(), 0);*/

						}
					}
				});
	}

	private void initView() {
		if (secondList.size() <= 1) {
			rl_tab.setVisibility(View.GONE);
		}
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//自定义宽度
		if (secondList.size() >= 4) {
			indicatorWidth = (dm.widthPixels - 96) / 4;
		} else if (secondList.size() == 3) {
			indicatorWidth = (dm.widthPixels) / 3;
		} else if (secondList.size() == 2) {
			indicatorWidth = (dm.widthPixels  - 96) / 2;
		}

		LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
		cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
		iv_nav_indicator.setLayoutParams(cursor_Params);

		mHsv.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, this);
		mHsv.showAndHideArrow(secondList.size());
		// 获取布局填充器
		mInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		initNavigationHSV();
		if (adapter == null) {
			adapter = new PageAdapter(getSupportFragmentManager());
			mViewPager.setAdapter(adapter);
		}
		setListener();
	}
	//横竖屏切换问题
	public void changeView(){
		if (secondList.size() <= 1) {
			rl_tab.setVisibility(View.GONE);
		}
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (secondList.size() >= 4) {
			indicatorWidth = (dm.widthPixels - 96) / 4;
		} else if (secondList.size() == 3) {
			indicatorWidth = (dm.widthPixels - 32) / 3;
		} else if (secondList.size() == 2) {
			indicatorWidth = (dm.widthPixels - 32) / 2;
		}

		LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
		cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
		iv_nav_indicator.setLayoutParams(cursor_Params);

		mHsv.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, this);
		mHsv.showAndHideArrow(secondList.size());
		// 获取布局填充器
		mInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		initNavigationHSV();
	}

	private void getData() {
		
		secondList = new ArrayList<SecondBean>();
		SecondBean bean = new SecondBean();
		bean.setName("交通导航");
		bean.setId("0x00");
		secondList.add(bean);

		SecondBean bean1 = new SecondBean();
		bean1.setName("周边服务");
		bean1.setId("0x01");
		secondList.add(bean1);

		initView();

	}

	private void initNavigationHSV() {

		rg_nav_content.removeAllViews();

		for (int i = 0; i < secondList.size(); i++) {

			RadioButton rb = (RadioButton) mInflater.inflate(
					R.layout.nav_radiogroup_item, null);
			rb.setId(i);
			rb.setText(secondList.get(i).getName());
			rb.setLayoutParams(new LayoutParams(indicatorWidth,
					LayoutParams.MATCH_PARENT));

			rg_nav_content.addView(rb);
			/*if (i == 0 && currentFragmentIndex == 0) {
				rb.setChecked(true);
			}else */
			if(i == currentFragmentIndex){
				rb.setChecked(true);
			}
		}

	}

	private void findViewById() {

		rl_nav = (RelativeLayout) findViewById(R.id.rl_nav);

		mHsv = (SyncHorizontalScrollView) findViewById(R.id.mHsv);

		rg_nav_content = (RadioGroup) findViewById(R.id.rg_nav_content);

		iv_nav_indicator = (ImageView) findViewById(R.id.iv_nav_indicator);
		iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
		iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);

		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		getData();
	}

	private class PageAdapter extends FragmentStatePagerAdapter {

		public PageAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment ft = null;
			SecondBean bean = secondList.get(position);
			if (secondList.get(position).getId().equals("0x00")) {
				ft = NavigationAddressFragment.newInstance(mActivity, bean);
			}else if (secondList.get(position).getId().equals("0x01")){
				ft = PerimeterFragment.newInstance(mActivity, bean);
			}
			return ft;
		}

		@Override
		public int getCount() {
			return secondList.size();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_back:
			finish();
			break;
		default:
			break;
		}

	}

	public int getCurrentFragmentIndex() {
		return currentFragmentIndex;
	}


}
