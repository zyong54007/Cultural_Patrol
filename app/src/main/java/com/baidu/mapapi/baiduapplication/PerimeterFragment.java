package com.baidu.mapapi.baiduapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.clusterutil.projection.SecondBean;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;
import java.util.Iterator;

public class PerimeterFragment extends Fragment  implements BaiduMap.OnMapClickListener,OnGetGeoCoderResultListener {
	    private OverlayManager routeOverlay = null;
	    boolean useDefaultIcon = false;
	    //地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
	    //如果不处理touch事件，则无需继承，直接使用MapView即可
	    MapView mMapView = null;    // 地图View
	    BaiduMap mBaidumap = null;
	    //停车场
		private PoiSearch parkPoiSearch = null;
		public static final String ROUTE_PLAN_NODE = "routePlanNode";
		final public static int REQUEST_CODE_ACCESS_FINE_LOCATION = 000001;
		private int mapZoom=15;
		private LocationClient mLocClient;
		private LocationListenner locationListenner = new LocationListenner();
		private PerimeterListAdapter adapter;
		private ListView listView;
		private LinearLayout lineList;
		private ArrayList<PerimeterBean> arrList ;
		private PoiResult poiResult;

		private String keyword ="美食";
		private TextView titleName;
		private LinearLayout lineTop;
		private FrameLayout frameMap;
		private View viewListPopTop;
		private LinearLayout linepop;
		private Boolean isToTop = false;
		private Boolean isSearch = false;
		private MapFragment mActivity;
		private SecondBean bean;
		private String endLatitude;
		private String endLongitude;
		private String endAddress;
		private GeoCoder gSearch;
	public static PerimeterFragment newInstance(MapFragment mActivity, SecondBean bean) {
			PerimeterFragment instance = new PerimeterFragment();
			instance.mActivity = mActivity;
			instance.bean = bean;
			return instance;
		}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			if(mActivity == null & getActivity() instanceof MapFragment){
				mActivity = (MapFragment) getActivity();
				bean = mActivity.secondList.get(mActivity.getCurrentFragmentIndex());
			}
			View rootView = inflater.inflate(R.layout.fragment_perimeter,
				container, false);
			TextView MeishiText = (TextView) rootView.findViewById(R.id.meishi);
			MeishiText.setOnClickListener(startSearchListener);

			TextView  xiaochiText= (TextView) rootView.findViewById(R.id.xiaochi );
			xiaochiText.setOnClickListener(startSearchListener);

			TextView  huoguoText = (TextView) rootView.findViewById(R.id.huoguo );
			huoguoText.setOnClickListener(startSearchListener);
			TextView  zhongcaiText = (TextView) rootView.findViewById(R.id.zhongcai );
			zhongcaiText.setOnClickListener(startSearchListener);
			TextView jiubaText  = (TextView) rootView.findViewById(R.id.jiuba );
			jiubaText.setOnClickListener(startSearchListener);
			TextView   kafeiText= (TextView) rootView.findViewById(R.id.kafeiting );
			kafeiText.setOnClickListener(startSearchListener);
			TextView jiudianText  = (TextView) rootView.findViewById(R.id.jiudian );
			jiudianText	 .setOnClickListener(startSearchListener);
			TextView kuajieText  = (TextView) rootView.findViewById(R.id.kuaijiejiudian );
			kuajieText.setOnClickListener(startSearchListener);
			TextView xingjiText  = (TextView) rootView.findViewById(R.id.xingjijiudian );
			xingjiText .setOnClickListener(startSearchListener);
			TextView tejiaText  = (TextView) rootView.findViewById(R.id.tejiajiudian );
			tejiaText.setOnClickListener(startSearchListener);
			TextView bingaText  = (TextView) rootView.findViewById(R.id.bingua );
			bingaText .setOnClickListener(startSearchListener);
			TextView  gongyuText = (TextView) rootView.findViewById(R.id.gongyujiudian );
			gongyuText.setOnClickListener(startSearchListener);
			TextView zhaodaiText  = (TextView) rootView.findViewById(R.id.zhaodaisuo );
			zhaodaiText.setOnClickListener(startSearchListener);
			TextView qingnianText  = (TextView) rootView.findViewById(R.id.qingnianlvshe );
			qingnianText .setOnClickListener(startSearchListener);

			TextView jingdianText  = (TextView) rootView.findViewById(R.id.jingdian );
			jingdianText.setOnClickListener(startSearchListener);
			TextView fengjingText  = (TextView) rootView.findViewById(R.id.fengjing );
			fengjingText.setOnClickListener(startSearchListener);
			TextView gongyuanText  = (TextView) rootView.findViewById(R.id.gongyuan );
			gongyuanText.setOnClickListener(startSearchListener);
			TextView  dongwuText = (TextView) rootView.findViewById(R.id.dongwu );
			dongwuText .setOnClickListener(startSearchListener);
			TextView zhiwuText  = (TextView) rootView.findViewById(R.id.zhiwu );
			zhiwuText.setOnClickListener(startSearchListener);
			TextView  bowuText = (TextView) rootView.findViewById(R.id.bowu );
			bowuText .setOnClickListener(startSearchListener);
			TextView  yuleText = (TextView) rootView.findViewById(R.id.yule );
			yuleText .setOnClickListener(startSearchListener);
			TextView ktvText  = (TextView) rootView.findViewById(R.id.ktv );
			ktvText .setOnClickListener(startSearchListener);
			TextView  jianshenText = (TextView) rootView.findViewById(R.id.jianshen );
			jianshenText .setOnClickListener(startSearchListener);
			TextView  xiyuText = (TextView) rootView.findViewById(R.id.xiyu );
			xiyuText .setOnClickListener(startSearchListener);
			TextView tiyuText  = (TextView) rootView.findViewById(R.id.tiyu );
			tiyuText.setOnClickListener(startSearchListener);
			TextView  dianyingText = (TextView) rootView.findViewById(R.id.dianying );
			dianyingText .setOnClickListener(startSearchListener);
			
			TextView titleBack = (TextView) rootView.findViewById(R.id.title_back);
			titleBack.setOnClickListener(clickListener);
			TextView titleFinish = (TextView) rootView.findViewById(R.id.title_finish);
			titleFinish.setOnClickListener(clickListener);
			titleName = (TextView) rootView.findViewById(R.id.title_name);
			
			lineTop  = (LinearLayout) rootView.findViewById(R.id.line_top);
			frameMap = (FrameLayout) rootView.findViewById(R.id.fram_map);
			
			listView = (ListView)rootView.findViewById(R.id.listView1);
			lineList = (LinearLayout)rootView.findViewById(R.id.line_list);
            lineList.setOnClickListener(clickListener);
			viewListPopTop= (View)rootView.findViewById(R.id.view_list_pop_top);
			viewListPopTop.setOnClickListener(clickListener);
			linepop = (LinearLayout)rootView.findViewById(R.id.linepop);
		    arrList = new ArrayList<PerimeterBean>();
			adapter = new PerimeterListAdapter(getActivity(), arrList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {  
	            @Override  
	            public void onItemClick(AdapterView<?> adapterView, View view, int position,  
	                long id) {  
		    		 PoiInfo poi = poiResult.getAllPoi().get(position);
		    		 //if (poi.hasCaterDetails) {
						parkPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
									.poiUid(poi.uid));
					 //}
	            }  
	        });  
	        //初始化地图
	        mMapView = (MapView)rootView.findViewById(R.id.map);
	        mMapView.showZoomControls(false);
	        mBaidumap = mMapView.getMap();
	        mBaidumap.setTrafficEnabled(true);
	        mBaidumap.setMyLocationEnabled(true);
	        //地图点击事件处理
	        mBaidumap.setOnMapClickListener(this);
	        // 初始化搜索模块，注册事件监听
	        parkPoiSearch = PoiSearch.newInstance();
	        parkPoiSearch.setOnGetPoiSearchResultListener(parkPoiListener);
			gSearch = GeoCoder.newInstance();
			gSearch.setOnGetGeoCodeResultListener(this);
		return rootView;
	}
	
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    	switch (requestCode) {
    		case REQUEST_CODE_ACCESS_FINE_LOCATION:
    			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    				getBaiduLocation();
    			} else {
					Toast.makeText(MyApplication.getInstance(), "定位失败:动态获取权限被拒绝", Toast.LENGTH_SHORT).show();
    			}
    			break;
    	}
    }
	
	//定位代码开始***************************************************
		public void getBaiduLocation(){
			System.out.println("******getBaiduLocation******");
			// 定位初始化
			mLocClient = new LocationClient(getActivity());
			LocationClientOption option = new LocationClientOption();
			option.setOpenGps(true);// 打开gps
			option.setCoorType("bd09ll"); //设置坐标类型百度坐标
			option.setScanSpan(0*1000);
			option.setIsNeedAddress(true);
			option.setIgnoreKillProcess(true);
			mLocClient.setLocOption(option);
			mLocClient.registerLocationListener(locationListenner);
			mLocClient.start();
		}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
		if(geoCodeResult == null || geoCodeResult.getLocation() == null ){
			return;
		}
		LocationConvert convert = new LocationConvert();
		convert.bd_decrypt(geoCodeResult.getLocation().latitude, geoCodeResult.getLocation().longitude);
		if(convert.gg_lon_out > 0 &&  convert.gg_lat_out > 0){
			double gg_lon_out =convert.gg_lon_out;
			double gg_lat_out = convert.gg_lat_out;
			convert.gg_lon_out = 0;
			convert.gg_lat_out = 0;
			System.out.println(endLatitude + ":" +endLongitude);
			endLatitude = String.valueOf(gg_lat_out);
			endLongitude =  String.valueOf(gg_lon_out);
			startSearch();
		}
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
		if(reverseGeoCodeResult == null || reverseGeoCodeResult.getLocation() == null ){
			return;
		}
	}

	/**
		 * 定位SDK监听函数
		 */
		public class LocationListenner implements BDLocationListener {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if(!isSearch){
					return;
				}
				isSearch = false;
				// map view 销毁后不在处理新接收的位置
				if (location == null || mMapView == null)
					return;
				MyLocationData locData = new MyLocationData.Builder()
						.accuracy(location.getRadius())
						// 此处设置开发者获取到的方向信息，顺时针0-360
						.direction(100).latitude(location.getLatitude())
						.longitude(location.getLongitude()).build();
				mBaidumap.setMyLocationData(locData);
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, mapZoom);
				mBaidumap.animateMapStatus(u);
				LocationConvert convert = new LocationConvert();
				convert.bd_decrypt(location.getLatitude(), location.getLongitude());
				if(convert.gg_lon_out > 0 &&  convert.gg_lat_out > 0){
					double gg_lon_out =convert.gg_lon_out;
					double gg_lat_out = convert.gg_lat_out;
					convert.gg_lon_out = 0;
					convert.gg_lat_out = 0;
				}
				startSearch(ll);
			}

			public void onReceivePoi(BDLocation poiLocation) {
			}
		}

	public void startSearch(){
		BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.red_pushpin); // 自定义图标
		MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL; // 罗盘模式
		mBaidumap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
		LatLng latlng = new LatLng(Double.valueOf(endLatitude),Double.valueOf(endLongitude));
		MyLocationData locData = new MyLocationData.Builder()
				.latitude(latlng.latitude)
				.longitude(latlng.longitude).build();
		mBaidumap.setMyLocationData(locData);
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latlng, mapZoom);
		mBaidumap.animateMapStatus(u);
		startSearch(latlng);
	}
		//定位代码结束***************************************************
		public void startSearch(LatLng latlng){
			//showParkLoadingProgressBar();
	   		PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption()
		        .location(latlng)
				.keyword(keyword)
				.sortType(PoiSortType.distance_from_near_to_far)
				.radius(50000)// 检索半径，单位是米
				.pageNum(0);
            parkPoiSearch.searchNearby(nearbySearchOption);// 发起附近检索请求
		}
		//停车场搜索开始********************************************************************
		private class MyPoiOverlay extends PoiOverlay {

			public MyPoiOverlay(BaiduMap baiduMap) {
				super(baiduMap);
			}

			@Override
			public boolean onPoiClick(int index) {
				super.onPoiClick(index);
				PoiInfo poi = getPoiResult().getAllPoi().get(index);
				//if (poi.hasCaterDetails) {
				parkPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
							.poiUid(poi.uid));
				//}
				return true;
			}
		}
		
		OnGetPoiSearchResultListener  parkPoiListener = new OnGetPoiSearchResultListener(){
			@Override
			public void onGetPoiDetailResult(PoiDetailResult result) {
				// TODO Auto-generated method stub
				if (result.error != SearchResult.ERRORNO.NO_ERROR) {
					Toast.makeText(MyApplication.getInstance(), "未找到结果  请稍后重试...", Toast.LENGTH_SHORT).show();
				} else {
					//CustomToast.makeText(MyApplication.getInstance(), result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getActivity(), WebViewActivity.class);
	                Bundle bundle = new Bundle();
	                bundle.putString("name",result.getName());
	                bundle.putString("url",result.getDetailUrl());
	                intent.putExtras(bundle);
	                startActivity(intent);
				}
				System.out.println(result.getDetailUrl());
				System.out.println(result.getUid());
			}

			@Override
			public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

			}

			@Override
			public void onGetPoiResult(PoiResult result) {
				// TODO Auto-generated method stub
				//closeParkLoadingProgressBar();
				if (result == null|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
					Toast.makeText(MyApplication.getInstance(), "未找到结果  请稍后重试...", Toast.LENGTH_SHORT).show();
					return;
				}
				if (result.error == SearchResult.ERRORNO.NO_ERROR) {
					//mBaidumap.clear();
					PoiOverlay overlay = new MyPoiOverlay(mBaidumap);
					mBaidumap.setOnMarkerClickListener(overlay);
					overlay.setData(result);
					overlay.addToMap();
					overlay.zoomToSpan();
					
					arrList.clear();
					poiResult = result;
					for (Iterator iter = result.getAllPoi().iterator(); iter.hasNext();) {
						PoiInfo info = (PoiInfo)iter.next();
						PerimeterBean bean = new PerimeterBean();
						bean.setName(info.name);
						bean.setDistance(info.address);
						arrList.add(bean);
					}
					adapter.notifyDataSetChanged();
					lineList.setVisibility(View.VISIBLE);
					return;
				}
			}  
		};

	    @Override
	    public void onMapClick(LatLng point) {
	        mBaidumap.hideInfoWindow();
	    }

	    @Override
	    public boolean onMapPoiClick(MapPoi poi) {
	    	return false;
	    }

	    @Override
		public void onPause() {
	        mMapView.onPause();
	        super.onPause();
	    }

	    @Override
		public void onResume() {
			mMapView.onResume();
	        super.onResume();
	    }

	    @Override
		public void onDestroy() {
			if (mLocClient != null)
	           	mLocClient.stop();
	        parkPoiSearch.destroy();
	        mMapView.onDestroy();
	       	mMapView = null;
	        super.onDestroy();
	    }

	private OnClickListener startSearchListener  = new OnClickListener(){
	        public void onClick(View v) {
				isSearch = true;
				lineList.setVisibility(View.GONE);
	        	mBaidumap.clear();
            	keyword = ((TextView)v).getText().toString();
            	System.err.println(keyword);
            	titleName.setText(keyword);
            	lineTop.setVisibility(View.GONE);
            	frameMap.setVisibility(View.VISIBLE);
				Intent intent = getActivity().getIntent();
				endLatitude = intent.getStringExtra("latitude");
				endLongitude = intent.getStringExtra("longitude");
				endAddress = intent.getStringExtra("address");
				if(TextUtils.isEmpty(endLatitude) || TextUtils.isEmpty(endLongitude)){
					gSearch.geocode(new GeoCodeOption().city("北京").address(endAddress));
				}else {
					System.out.println(endLatitude + ":" +endLongitude );
					startSearch();
				}
            	/*if (Build.VERSION.SDK_INT >= 23) {
     	   		   int checkAccessFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION);
     	   		   if(checkAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED){
     	   			ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ACCESS_FINE_LOCATION);
     	   		   }else{
     	   			  getBaiduLocation();
     	   		   }
     	   	   	} else {
     	   	   		getBaiduLocation();
     	   	   	}*/
	         }
		};

	// 注意 这里没有 @Override 标签
	public void startSearchInfo(View view) {
		// TODO Auto-generated method stub
		lineList.setVisibility(View.GONE);
		keyword = ((TextView)view).getText().toString();
		lineTop.setVisibility(View.GONE);
		frameMap.setVisibility(View.VISIBLE);
		if (Build.VERSION.SDK_INT >= 23) {
			int checkAccessFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION);
			if(checkAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED){
				ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ACCESS_FINE_LOCATION);
			}else{
				getBaiduLocation();
			}
		} else {
			getBaiduLocation();
		}
	}

	private void scrollToTop(){
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		int height = (int) (480 * scale + 0.5f); //dp
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
		params.gravity =  Gravity.BOTTOM;
		lineList.setLayoutParams(params);
		isToTop = true;
	}

	private void scrollToBottom(){
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		int height = (int) (192 * scale + 0.5f); //dp
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
		params.gravity =  Gravity.BOTTOM;
		lineList.setLayoutParams(params);
		isToTop = false;
	}

	private OnClickListener clickListener  = new OnClickListener(){
        public void onClick(View v) {
            switch(v.getId()){
            case R.id.title_back:
            	lineTop.setVisibility(View.VISIBLE);
            	frameMap.setVisibility(View.GONE);
            	break;
            case R.id.title_finish:
				lineTop.setVisibility(View.VISIBLE);
				frameMap.setVisibility(View.GONE);
            	//getActivity().finish();
            	break;
			case R.id.view_list_pop_top:
				//Toast.makeText(getActivity(),"######",Toast.LENGTH_SHORT).show();
				//scrollToTopAnima();
				break;
            case R.id.line_list:
				if(!isToTop){
					scrollToTop();
				}else{
					scrollToBottom();
				}
                break;
            default:
                break;
            }
         }
	};
}
