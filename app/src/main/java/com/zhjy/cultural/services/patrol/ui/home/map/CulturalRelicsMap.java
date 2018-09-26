package com.zhjy.cultural.services.patrol.ui.home.map;

//import android.Manifest;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.databinding.DataBindingUtil;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.baiduapplication.BNDemoGuideActivity;
//import com.baidu.mapapi.baiduapplication.BusLineActivity;
//import com.baidu.mapapi.baiduapplication.CustomProgressDialog;
//import com.baidu.mapapi.baiduapplication.LocationConvert;
//import com.baidu.mapapi.baiduapplication.MyApplication;
//import com.baidu.mapapi.baiduapplication.MyOrientationListener;
//import com.baidu.mapapi.baiduapplication.NavigationBusListAdapter;
//import com.baidu.mapapi.map.BaiduMap;
//import com.baidu.mapapi.map.BitmapDescriptor;
//import com.baidu.mapapi.map.BitmapDescriptorFactory;
//import com.baidu.mapapi.map.CircleOptions;
//import com.baidu.mapapi.map.InfoWindow;
//import com.baidu.mapapi.map.MapPoi;
//import com.baidu.mapapi.map.MapStatusUpdate;
//import com.baidu.mapapi.map.MapStatusUpdateFactory;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.MapView;
//import com.baidu.mapapi.map.Marker;
//import com.baidu.mapapi.map.MarkerOptions;
//import com.baidu.mapapi.map.MyLocationConfiguration;
//import com.baidu.mapapi.map.MyLocationData;
//import com.baidu.mapapi.map.Overlay;
//import com.baidu.mapapi.map.OverlayOptions;
//import com.baidu.mapapi.map.Stroke;
//import com.baidu.mapapi.model.LatLng;
//import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
//import com.baidu.mapapi.overlayutil.OverlayManager;
//import com.baidu.mapapi.overlayutil.PoiOverlay;
//import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
//import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
//import com.baidu.mapapi.search.core.PoiInfo;
//import com.baidu.mapapi.search.core.RouteLine;
//import com.baidu.mapapi.search.core.SearchResult;
//import com.baidu.mapapi.search.geocode.GeoCodeOption;
//import com.baidu.mapapi.search.geocode.GeoCodeResult;
//import com.baidu.mapapi.search.geocode.GeoCoder;
//import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
//import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
//import com.baidu.mapapi.search.poi.PoiDetailResult;
//import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
//import com.baidu.mapapi.search.poi.PoiIndoorResult;
//import com.baidu.mapapi.search.poi.PoiResult;
//import com.baidu.mapapi.search.poi.PoiSearch;
//import com.baidu.mapapi.search.route.BikingRouteResult;
//import com.baidu.mapapi.search.route.DrivingRouteLine;
//import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
//import com.baidu.mapapi.search.route.DrivingRouteResult;
//import com.baidu.mapapi.search.route.IndoorRouteResult;
//import com.baidu.mapapi.search.route.MassTransitRouteResult;
//import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
//import com.baidu.mapapi.search.route.PlanNode;
//import com.baidu.mapapi.search.route.RoutePlanSearch;
//import com.baidu.mapapi.search.route.TransitRouteLine;
//import com.baidu.mapapi.search.route.TransitRoutePlanOption;
//import com.baidu.mapapi.search.route.TransitRouteResult;
//import com.baidu.mapapi.search.route.WalkingRouteLine;
//import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
//import com.baidu.mapapi.search.route.WalkingRouteResult;
//import com.baidu.navisdk.adapter.BNCommonSettingParam;
//import com.baidu.navisdk.adapter.BNOuterLogUtil;
//import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
//import com.baidu.navisdk.adapter.BNRoutePlanNode;
//import com.baidu.navisdk.adapter.BNaviSettingManager;
//import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.jakewharton.rxbinding.view.RxView;
import com.zhjy.cultural.services.patrol.NavigationActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.base.BaseFragment;
//import com.zhjy.cultural.services.patrol.biz.api.Constants;
//import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
//import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;
//import com.zhjy.cultural.services.patrol.databinding.CulturalRelicsMapBinding;
//import com.zhjy.cultural.services.patrol.databinding.FragmentMainMapInfoWindowBinding;
import com.zhjy.cultural.services.patrol.map.CulturalRelicsMapPersenter;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.view.Topbar;
//import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
//import com.zhjy.cultural.services.patrol.ui.home.map.search.MapSearchPopupWindow;
//import com.zhjy.cultural.services.patrol.util.ViewFinder;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jialg on 2018/3/19.
 */

public class CulturalRelicsMap extends BaseFragment<CulturalRelicsMapPersenter.CulturalUI, CulturalRelicsMapPersenter> implements CulturalRelicsMapPersenter.CulturalUI {
    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View inflate = inflater.inflate(R.layout.cultural_relics_map, null);
//        return inflate;
//    }

    private int isshow = 1;

    @Override
    public int getContentLayout() {
        return R.layout.cultural_relics_map;
    }

    @Override
    protected CulturalRelicsMapPersenter createPresenter() {
        return new CulturalRelicsMapPersenter();
    }

    @Override
    protected CulturalRelicsMapPersenter.CulturalUI createUI() {
        return this;
    }

    @Override
    protected void initViews() {

        //初始化定位

        getPresenter().initmap();

        RxView.clicks(getUI().finder().radiobutton(R.id.nav_all)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            if (isshow == 1) {
                return;
            }
            isshow = 1;
            getPresenter().Alladdmarker();
        });


        RxView.clicks(getUI().finder().radiobutton(R.id.nav_country)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            if (isshow == 2) {
                return;
            }
            isshow = 2;
            getPresenter().CountryAddmarker();
        });
        RxView.clicks(getUI().finder().radiobutton(R.id.nav_city)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            if (isshow == 3) {
                return;
            }
            isshow = 3;
            getPresenter().CityAddmarker();
        });

        RxView.clicks(getUI().finder().radiobutton(R.id.nav_county)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            if (isshow == 4) {
                return;
            }
            isshow = 4;
            getPresenter().CountyAddmarker();
        });

        RxView.clicks(getUI().finder().radiobutton(R.id.nav_street)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            if (isshow == 5) {
                return;
            }
            isshow = 5;
            getPresenter().StreetAddmarker();
        });

        RxView.clicks(getUI().finder().find(R.id.route_map)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
            Intent intent = new Intent(getActivity(), NavigationActivity.class);
            intent.putExtra(Contant.NAVIGTYPE, "nomap");
            startActivity(intent);
        });

//        RxView.clicks(getUI().finder().find(R.id.img_search)).throttleFirst(500, TimeUnit.MICROSECONDS).subscribe(aVoid -> {
//
//
//        });


        Topbar topbar = getUI().finder().find(R.id.map_topbar);
        topbar.setLeftSearchvisible();
        topbar.setLeftSearchListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().showsearch();
            }
        });

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public LinearLayout getmaproot() {
        return getUI().finder().find(R.id.map_root);
    }

    @Override
    public MapView getbaidumap() {
        return getUI().finder().find(R.id.nva_map);
    }
}


//        AacFragment<CulturalRelicsMapBinding> implements BaiduMap.OnMapClickListener,
//        OnGetRoutePlanResultListener, OnGetGeoCoderResultListener {

//    private final static String authBaseArr[] =
//            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
//
//    private final static String authComArr[] = {Manifest.permission.READ_PHONE_STATE};
//
//    private final static int authBaseRequestCode = 1;
//
//    private final static int authComRequestCode = 2;
//
//    private boolean hasInitSuccess = false;
//
//    private boolean hasRequestComAuth = false;
//
//    private Boolean isStartGuide = false;
//
//    private int goStatus = 0;
//
//    private int nodeIndex = -1;//节点索引,供浏览节点时使用
//
//    private RouteLine route = null;
//
//    private OverlayManager routeOverlay = null;
//
//    private boolean useDefaultIcon = false;
//
//    private TextView popupText = null;//泡泡view
//
//    private BaiduMap mBaidumap = null;
//
//    //搜索相关
//    private RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
//
//    //停车场
//    private PoiSearch parkPoiSearch = null;
//
//    //坐标反查
//    private GeoCoder gSearch = null; // 搜索模块，也可去掉地图模块独立使用
//
//    private String mSDCardPath = null;
//
//    private static final String APP_FOLDER_NAME = "BNSDKDemo";
//
//    public static final String ROUTE_PLAN_NODE = "routePlanNode";
//
//    final public static int REQUEST_CODE_ACCESS_FINE_LOCATION = 000001;
//
//    private int mapZoom = 15;
//
//    private LocationClient mLocClient;
//
//    private CulturalRelicsMap.BaiduLocationListenner baiduListener = new CulturalRelicsMap.BaiduLocationListenner();
//
//    private PlanNode stNode;
//
//    private BNRoutePlanNode sNode = null;
//
//    private BNRoutePlanNode eNode = null;
//
//    private LatLng endLocation = null;
//
//    private String endAddress = null;
//
//    private String startAddress = null;
//
//    private LatLng bdLocation = null;
//
//    private BNRoutePlanNode.CoordinateType coType = BNRoutePlanNode.CoordinateType.GCJ02;
//
//    private BitmapDescriptor mCurrentMarker;
//
//    private MyLocationConfiguration.LocationMode mCurrentMode;
//
//    private ArrayList<TransitRouteLine> arrList;
//
//    private NavigationBusListAdapter adapter;
//
//    private MyOrientationListener myOrientationListener;
//
//    private float mCurrentX;
//
//    private boolean isFirst = true;
//
//    private boolean isSearch = false;
//
//    private CustomProgressDialog mLoadingDialog;
//
//    protected void closeLoadingProgressBar() {
//        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
//            this.mLoadingDialog.dismiss();
//        }
//    }
//
//    protected void showLoadingProgressBar() {
//        if (this.mLoadingDialog == null) {
//            this.mLoadingDialog = CustomProgressDialog.createDialog(getActivity());
//            this.mLoadingDialog.setMessage("路线规划中.....");
//        }
//        this.mLoadingDialog.show();
//    }
//
//    CulturalRelicsMapViewModel mCulturalRelicsMapViewModel;
//
//    MapSearchPopupWindow mapSearchPopupWindow;
//
//    public static CulturalRelicsMap newInstance() {
//        return new CulturalRelicsMap();
//    }
//
//    @Override
//    protected int getFragmentLayout() {
//        return R.layout.cultural_relics_map;
//    }
//
//    @Override
//    protected void init(Bundle savedInstanceState) {
////        mCulturalRelicsMapViewModel = ViewModelProviders.of(this, viewModelFactory()).get(CulturalRelicsMapViewModel.class);
////        binding.map.showZoomControls(false);
////        mBaidumap = binding.map.getMap();
////        mBaidumap.setTrafficEnabled(true);
////        mBaidumap.setMyLocationEnabled(true);
////        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.compass); // 自定义图标
////        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL; // 罗盘模式
////        mBaidumap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
////
////        binding.pre.setOnClickListener(nodeClickListener);
////        binding.next.setOnClickListener(nodeClickListener);
////        binding.pre.setVisibility(View.GONE);
////        binding.next.setVisibility(View.GONE);
////        binding.drive.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////        binding.transit.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////        binding.walk.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////        binding.yuyin.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////
////        binding.exchange.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                switchSearch();
////            }
////        });
////        binding.serchBtn.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////        binding.textSearch.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                SearchButtonProcess(v);
////            }
////        });
////        binding.lineDrive.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                setButtonView(v);
////            }
////        });
////        binding.lineBus.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                setButtonView(v);
////            }
////        });
////        binding.lineWalk.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                setButtonView(v);
////            }
////        });
////        arrList = new ArrayList<TransitRouteLine>();
////        adapter = new NavigationBusListAdapter(getActivity(), arrList);
////        binding.listView1.setAdapter(adapter);
////        binding.listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
////                if (MyApplication.getBusRouteResult() != null) {
////                    Intent intent = new Intent(getActivity(), BusLineActivity.class);
////                    Bundle bundle = new Bundle();
////                    bundle.putInt("position", position);
////                    intent.putExtras(bundle);
////                    startActivity(intent);
////                }
////            }
////        });
////
////        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                showSearchPop();
////            }
////        });
////
////        //地图点击事件处理
////        mBaidumap.setOnMapClickListener(this);
////        // 初始化搜索模块，注册事件监听
////        mSearch = RoutePlanSearch.newInstance();
////        mSearch.setOnGetRoutePlanResultListener(this);
////        parkPoiSearch = PoiSearch.newInstance();
////        parkPoiSearch.setOnGetPoiSearchResultListener(parkPoiListener);
////        gSearch = GeoCoder.newInstance();
////        gSearch.setOnGetGeoCodeResultListener(this);
////        Intent intent = getActivity().getIntent();
////        String endLatitude = intent.getStringExtra("latitude");
////        String endLongitude = intent.getStringExtra("longitude");
////        System.out.println(endLatitude + ":" + endLongitude);
////        endAddress = intent.getStringExtra("address");
////        binding.end.setText(endAddress);
////
////        if (endLatitude != null && endLongitude != null) {
////            endLocation = new LatLng(Double.valueOf(endLatitude), Double.valueOf(endLongitude));
////            BitmapDescriptor bitmap = BitmapDescriptorFactory
////                    .fromResource(R.mipmap.red_pushpin);
////            OverlayOptions option = new MarkerOptions()
////                    .position(endLocation)
////                    .icon(bitmap);
////            mBaidumap.addOverlay(option);
////            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(endLocation, mapZoom);
////            mBaidumap.animateMapStatus(u);
////        }
////        if (Build.VERSION.SDK_INT >= 23) {
////            int checkAccessFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
////            if (checkAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED) {
////                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ACCESS_FINE_LOCATION);
////            } else {
////                getBaiduLocation();
////            }
////        } else {
////            getBaiduLocation();
////        }
////        getMapDataList();
////    }
////
////    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
////        switch (requestCode) {
////            case REQUEST_CODE_ACCESS_FINE_LOCATION:
////                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                    getBaiduLocation();
////                } else {
////                    Toast.makeText(MyApplication.getInstance(), "定位失败:动态获取权限被拒绝", Toast.LENGTH_SHORT).show();
////                }
////                break;
////        }
//    }
//
//    /**
//     * 点击修改样式
//     *
//     * @param v
//     */
//    public void setButtonView(View v) {
//
//        if (v.getId() == R.id.line_drive) {
//            binding.imgDrive.setImageResource(R.mipmap.race_on);
//            binding.viewDrive.setVisibility(View.VISIBLE);
//            binding.imgBus.setImageResource(R.mipmap.car);
//            binding.viewBus.setVisibility(View.INVISIBLE);
//            binding.imgWalk.setImageResource(R.mipmap.people);
//            binding.viewWalk.setVisibility(View.INVISIBLE);
//            binding.serchBtn.setVisibility(View.VISIBLE);
//            binding.lineList.setVisibility(View.GONE);
//            binding.pre.setVisibility(View.GONE);
//            binding.next.setVisibility(View.GONE);
//            goStatus = 0;
//            if (isSearch) {
//                SearchButtonProcess(v);
//            }
//        } else if (v.getId() == R.id.line_bus) {
//            binding.imgDrive.setImageResource(R.mipmap.race);
//            binding.viewDrive.setVisibility(View.INVISIBLE);
//            binding.imgBus.setImageResource(R.mipmap.car_on);
//            binding.viewBus.setVisibility(View.VISIBLE);
//            binding.imgWalk.setImageResource(R.mipmap.people);
//            binding.viewWalk.setVisibility(View.INVISIBLE);
//            binding.serchBtn.setVisibility(View.GONE);
//            binding.pre.setVisibility(View.GONE);
//            binding.next.setVisibility(View.GONE);
//            goStatus = 1;
//            if (isSearch) {
//                SearchButtonProcess(v);
//            }
//        } else if (v.getId() == R.id.line_walk) {
//            binding.imgDrive.setImageResource(R.mipmap.race);
//            binding.viewDrive.setVisibility(View.INVISIBLE);
//            binding.imgBus.setImageResource(R.mipmap.car);
//            binding.viewBus.setVisibility(View.INVISIBLE);
//            binding.imgWalk.setImageResource(R.mipmap.people_on);
//            binding.viewWalk.setVisibility(View.VISIBLE);
//            binding.serchBtn.setVisibility(View.GONE);
//            binding.lineList.setVisibility(View.GONE);
//            goStatus = 2;
//            if (isSearch) {
//                SearchButtonProcess(v);
//            }
//        }
//    }
//
//    public void switchSearch() {
//        if (!TextUtils.isEmpty(binding.start.getText().toString()) && !TextUtils.isEmpty(binding.end.getText().toString())) {
//            String switchText = binding.start.getText().toString();
//            binding.start.setText(binding.end.getText().toString());
//            binding.end.setText(switchText);
//        }
//    }
//
//    /**
//     * 发起路线规划搜索示例
//     *
//     * @param v
//     */
//    public void SearchButtonProcess(View v) {
//        if (TextUtils.isEmpty(binding.start.getText().toString())) {
//            binding.start.setError("请输入起始地点");
//            return;
//        }
//        if (TextUtils.isEmpty(binding.end.getText().toString())) {
//            binding.end.setError("请输入目的地点");
//            return;
//        }
//        //重置浏览节点的路线数据
//        route = null;
//        binding.pre.setVisibility(View.GONE);
//        binding.next.setVisibility(View.GONE);
//        //设置起终点信息，对于tranist search 来说，城市名无意义
//        //stNode = PlanNode.withCityNameAndPlaceName("北京", binding.start.getText().toString());
//        if (startAddress != null && !startAddress.equals(binding.start.getText().toString())) {
//            stNode = PlanNode.withCityNameAndPlaceName("北京", binding.start.getText().toString());
//        } else if ("我的位置".equals(binding.start.getText().toString())) {
//            stNode = PlanNode.withLocation(bdLocation);
//        }
//        PlanNode enNode;
//        if (endLocation != null && endAddress != null && endAddress.equals(binding.end.getText().toString())) {
//            enNode = PlanNode.withLocation(endLocation);
//        } else {
//            if ("我的位置".equals(binding.end.getText().toString())) {
//                enNode = PlanNode.withLocation(bdLocation);
//            } else {
//                enNode = PlanNode.withCityNameAndPlaceName("北京", binding.end.getText().toString());
//            }
//
//        }
//
//        if (v.getId() == R.id.text_search) {
//            isSearch = true;
//            if (goStatus == 1) {
//                showLoadingProgressBar();
//                mBaidumap.clear();
//                mSearch.transitSearch((new TransitRoutePlanOption())
//                        .policy(TransitRoutePlanOption.TransitPolicy.EBUS_TIME_FIRST)
//                        .from(stNode)
//                        .city("北京")
//                        .to(enNode));
//                return;
//            }
//            if (goStatus == 2) {
//                showLoadingProgressBar();
//                mBaidumap.clear();
//                mSearch.walkingSearch((new WalkingRoutePlanOption())
//                        .from(stNode)
//                        .to(enNode));
//                return;
//            }
//            if (goStatus == 0) {
//                showLoadingProgressBar();
//                mBaidumap.clear();
//                mSearch.drivingSearch((new DrivingRoutePlanOption())
//                        .from(stNode)
//                        .to(enNode));
//            }
//        }
//        if (v.getId() == R.id.serch_btn) {
//            if (goStatus == 0) {
//                BNOuterLogUtil.setLogSwitcher(true);
//                if (initDirs()) {
//                    initNavi();
//                }
//                if (endLocation != null && endAddress != null && endAddress.equals(binding.end.getText().toString())) {
//                    eNode = new BNRoutePlanNode(endLocation.longitude, endLocation.latitude, "目的地", null, BNRoutePlanNode.CoordinateType.GCJ02);
//                    if (BaiduNaviManager.isNaviInited()) {
//                        routeplanToNavi();
//                    }
//                } else {
//                    showLoadingProgressBar();
//                    //语音导航
//                    gSearch.geocode(new GeoCodeOption().city("北京").address(binding.end.getText().toString()));
//                }
//            }
//        }
//    }
//
//    //导航代码********************************************
//    private void routeplanToNavi() {
//        if (!hasInitSuccess) {
//            Toast.makeText(getActivity(), "还未初始化!", Toast.LENGTH_SHORT).show();
//        }
//        // 权限申请
//        if (Build.VERSION.SDK_INT >= 23) {
//            // 保证导航功能完备
//            if (!hasCompletePhoneAuth()) {
//                if (!hasRequestComAuth) {
//                    hasRequestComAuth = true;
//                    this.requestPermissions(authComArr, authComRequestCode);
//                    return;
//                } else {
//                    Toast.makeText(getActivity(), "没有完备的权限!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }
//
//        //sNode = new BNRoutePlanNode(12947471,4846474,  "我的位置", null, coType);
//        //eNode = new BNRoutePlanNode(12958160,4825947,  "目的地", null, coType);
//        if (sNode != null && eNode != null) {
//            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
//            list.add(sNode);
//            list.add(eNode);
//            BaiduNaviManager.getInstance().launchNavigator(getActivity(), list, 1, true, new DemoRoutePlanListener(sNode));
//        }
//    }
//
//    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {
//
//        private BNRoutePlanNode mBNRoutePlanNode = null;
//
//        public DemoRoutePlanListener(BNRoutePlanNode node) {
//            mBNRoutePlanNode = node;
//        }
//
//        @Override
//        public void onJumpToNavigator() {
//            /*
//             * 设置途径点以及resetEndNode会回调该接口
//             */
//
//            /*for (Activity ac : getActivity().activityList) {
//                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
//                    return;
//                }
//            }*/
//            Intent intent = new Intent(getActivity(), BNDemoGuideActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
//            intent.putExtras(bundle);
//            startActivity(intent);
//
//        }
//
//        @Override
//        public void onRoutePlanFailed() {
//            // TODO Auto-generated method stub
//            Toast.makeText(getActivity(), "算路失败", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private boolean initDirs() {
//        mSDCardPath = getSdcardDir();
//        if (mSDCardPath == null) {
//            return false;
//        }
//        File f = new File(mSDCardPath, APP_FOLDER_NAME);
//        if (!f.exists()) {
//            try {
//                f.mkdir();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    private void initNavi() {
//        final String[] authinfo = new String[1];
//        BNOuterTTSPlayerCallback ttsCallback = null;
//
//        // 申请权限
//        if (Build.VERSION.SDK_INT >= 23) {
//
//            if (!hasBasePhoneAuth()) {
//
//                this.requestPermissions(authBaseArr, authBaseRequestCode);
//                return;
//
//            }
//        }
//
//        BaiduNaviManager.getInstance().init(getActivity(), mSDCardPath, APP_FOLDER_NAME, new BaiduNaviManager.NaviInitListener() {
//            @Override
//            public void onAuthResult(int status, String msg) {
//                if (0 == status) {
//                    authinfo[0] = "key校验成功!";
//                } else {
//                    authinfo[0] = "key校验失败, " + msg;
//                }
//                getActivity().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        //Toast.makeText(getContext(), authinfo, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            public void initSuccess() {
//                //Toast.makeText(getActivity(), "导航开始", Toast.LENGTH_SHORT).show();
//                hasInitSuccess = true;
//                initSetting();
//            }
//
//            public void initStart() {
//                //Toast.makeText(getActivity(), "导航开始", Toast.LENGTH_SHORT).show();
//            }
//
//            public void initFailed() {
//                Toast.makeText(getActivity(), "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
//            }
//
//        }, null, ttsHandler, ttsPlayStateListener);
//
//    }
//
//    private void initSetting() {
//        // BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
//        BNaviSettingManager
//                .setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
//        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
//        // BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
//        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
//        Bundle bundle = new Bundle();
//        // 必须设置APPID，否则会静音
//        bundle.putString(BNCommonSettingParam.TTS_APP_ID, "10703820");
//        BNaviSettingManager.setNaviSdkParam(bundle);
//    }
//
//    private String getSdcardDir() {
//        if (Environment.getExternalStorageState().equalsIgnoreCase(
//                Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString();
//        }
//        return null;
//    }
//
//    /**
//     * 内部TTS播报状态回传handler
//     */
//    private Handler ttsHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            int type = msg.what;
//            switch (type) {
//                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
//                    // showToastMsg("Handler : TTS play start");
//                    break;
//                }
//                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
//                    // showToastMsg("Handler : TTS play end");
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };
//
//    /**
//     * 内部TTS播报状态回调接口
//     */
//    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {
//
//        @Override
//        public void playEnd() {
//            // showToastMsg("TTSPlayStateListener : TTS play end");
//        }
//
//        @Override
//        public void playStart() {
//            // showToastMsg("TTSPlayStateListener : TTS play start");
//        }
//    };
//
//    //导航代码结束********************************************
//
//
//    private View.OnClickListener nodeClickListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            nodeClick(v);
//        }
//    };
//
//    /**
//     * 节点浏览示例
//     *
//     * @param v
//     */
//    public void nodeClick(View v) {
//        if (route == null ||
//                route.getAllStep() == null) {
//            return;
//        }
//        if (nodeIndex == -1 && v.getId() == R.id.pre) {
//            return;
//        }
//        //设置节点索引
//        if (v.getId() == R.id.next) {
//            if (nodeIndex < route.getAllStep().size() - 1) {
//                nodeIndex++;
//            } else {
//                return;
//            }
//        } else if (v.getId() == R.id.pre) {
//            if (nodeIndex > 0) {
//                nodeIndex--;
//            } else {
//                return;
//            }
//        }
//        //获取节结果信息
//        LatLng nodeLocation = null;
//        String nodeTitle = null;
//        Object step = route.getAllStep().get(nodeIndex);
//        if (step instanceof DrivingRouteLine.DrivingStep) {
//            nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
//            nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
//        } else if (step instanceof WalkingRouteLine.WalkingStep) {
//            nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
//            nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
//        } else if (step instanceof TransitRouteLine.TransitStep) {
//            nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
//            nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
//        }
//
//        if (nodeLocation == null || nodeTitle == null) {
//            return;
//        }
//        //移动节点至中心
//        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
//        // show popup
//        popupText = new TextView(getActivity());
//        popupText.setBackgroundResource(R.mipmap.popup);
//        popupText.setPadding(12, 12, 12, 12);
//        popupText.setMaxEms(20);
//        popupText.setTextColor(0xFF000000);
//        popupText.setText(nodeTitle);
//        mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));
//
//    }
//
//
//    @Override
//    public void onGetWalkingRouteResult(WalkingRouteResult result) {
//        closeLoadingProgressBar();
//
//        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
//        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//            //result.getSuggestAddrInfo()
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            nodeIndex = -1;
//            binding.pre.setVisibility(View.VISIBLE);
//            binding.next.setVisibility(View.VISIBLE);
//            route = result.getRouteLines().get(0);
//            WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaidumap);
//            mBaidumap.setOnMarkerClickListener(overlay);
//            routeOverlay = overlay;
//            overlay.setData(result.getRouteLines().get(0));
//            overlay.addToMap();
//            overlay.zoomToSpan();
//        }
//
//    }
//
//    @Override
//    public void onGetTransitRouteResult(TransitRouteResult result) {
//        closeLoadingProgressBar();
//
//        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
//        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//            //result.getSuggestAddrInfo()
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            nodeIndex = -1;
//            //binding.pre.setVisibility(View.VISIBLE);
//            //binding.next.setVisibility(View.VISIBLE);
//            route = result.getRouteLines().get(0);
//            TransitRouteOverlay overlay = new TransitRouteOverlay(mBaidumap);
//            mBaidumap.setOnMarkerClickListener(overlay);
//            routeOverlay = overlay;
//            overlay.setData(result.getRouteLines().get(0));
//            overlay.addToMap();
//            overlay.zoomToSpan();
//
//            MyApplication.setBusRouteResult(result);
//            binding.lineList.setVisibility(View.VISIBLE);
//            arrList.clear();
//            for (TransitRouteLine routeLine : result.getRouteLines()) {
//                arrList.add(routeLine);
//            }
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
//
//    }
//
//    @Override
//    public void onGetDrivingRouteResult(DrivingRouteResult result) {
//        closeLoadingProgressBar();
//
//        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
//        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//            //result.getSuggestAddrInfo()
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            nodeIndex = -1;
//            //binding.pre.setVisibility(View.VISIBLE);
//            //binding.next.setVisibility(View.VISIBLE);
//            route = result.getRouteLines().get(0);
//            DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaidumap);
//            routeOverlay = overlay;
//            mBaidumap.setOnMarkerClickListener(overlay);
//            overlay.setData(result.getRouteLines().get(0));
//            overlay.addToMap();
//            overlay.zoomToSpan();
//        }
//    }
//
//    @Override
//    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
//
//    }
//
//    @Override
//    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
//
//    }
//
//
//    //定位代码开始***************************************************
//    public void getBaiduLocation() {
//        // 定位初始化
//        mLocClient = new LocationClient(getActivity());
//        mLocClient.registerLocationListener(baiduListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);// 打开gps
//        option.setCoorType("bd09ll"); //设置坐标类型百度坐标
//        option.setScanSpan(LocationClientOption.MIN_SCAN_SPAN * 10);
//        option.setIsNeedAddress(true);
//        option.setIgnoreKillProcess(true);
//        mLocClient.setLocOption(option);
//        mLocClient.start();
//
//        myOrientationListener = new MyOrientationListener(getActivity());
//        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
//            @Override
//            public void onOrientationChanged(float x) {
//                mCurrentX = x;
//                System.err.println("mCurrentX:" + mCurrentX);
//                if (mBaidumap != null && mBaidumap.getLocationData() != null) {
//                    MyLocationData locData = new MyLocationData.Builder()
//                            .accuracy(mBaidumap.getLocationData().accuracy)
//                            // 此处设置开发者获取到的方向信息，顺时针0-360
//                            .direction(mCurrentX).latitude(mBaidumap.getLocationData().latitude)
//                            .longitude(mBaidumap.getLocationData().longitude).build();
//                    mBaidumap.setMyLocationData(locData);
//                }
//
//            }
//        });
//        myOrientationListener.start();
//    }
//
//
//    //定位代码结束***************************************************
//
//    //坐标反查代码开始***************************************************
//    @Override
//    public void onGetGeoCodeResult(GeoCodeResult result) {
//        closeLoadingProgressBar();
//        if (result == null || result.getLocation() == null) {
//            return;
//        }
//        // TODO Auto-generated method stub
//        String strInfo = String.format("纬度：%f 经度：%f",
//                result.getLocation().latitude, result.getLocation().longitude);
//        //Toast.makeText(getActivity(), strInfo, Toast.LENGTH_SHORT).show();
//        //BDLocation bd = new BDLocation();
//        //bd.setLatitude(result.getLocation().latitude);
//        //bd.setLongitude(result.getLocation().longitude);
//        //bd = LocationClient.getBDLocationInCoorType(bd, BDLocation.BDLOCATION_BD09LL_TO_GCJ02);
//        LocationConvert convert = new LocationConvert();
//        convert.bd_decrypt(result.getLocation().latitude, result.getLocation().longitude);
//        if (convert.gg_lon_out > 0 && convert.gg_lat_out > 0) {
//            double gg_lon_out = convert.gg_lon_out;
//            double gg_lat_out = convert.gg_lat_out;
//            convert.gg_lon_out = 0;
//            convert.gg_lat_out = 0;
//            eNode = new BNRoutePlanNode(gg_lon_out, gg_lat_out, "目的地", null, BNRoutePlanNode.CoordinateType.GCJ02);
//        }
//        strInfo = String.format("纬度：%f 经度：%f",
//                result.getLocation().latitude, result.getLocation().longitude);
//        //Toast.makeText(getActivity(), strInfo, Toast.LENGTH_SHORT).show();
//        Log.e("NavigationInfo:", strInfo);
//        if (BaiduNaviManager.isNaviInited()) {
//            routeplanToNavi();
//        }
//    }
//
//    @Override
//    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
//        // TODO Auto-generated method stub
//        closeLoadingProgressBar();
//        if (result == null || result.getLocation() == null) {
//            return;
//        }
//        endAddress = result.getAddress();
//        binding.end.setText(endAddress);
//    }
//
//    //坐标反查代码结束*****************************************************************
//
//    //停车场搜索开始********************************************************************
//
//    private class MyPoiOverlay extends PoiOverlay {
//
//        public MyPoiOverlay(BaiduMap baiduMap) {
//            super(baiduMap);
//        }
//
//        @Override
//        public boolean onPoiClick(int index) {
//            super.onPoiClick(index);
//            PoiInfo poi = getPoiResult().getAllPoi().get(index);
//            // if (poi.hasCaterDetails) {
//            parkPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
//                    .poiUid(poi.uid));
//            // }
//            return true;
//        }
//    }
//
//    OnGetPoiSearchResultListener parkPoiListener = new OnGetPoiSearchResultListener() {
//        @Override
//        public void onGetPoiDetailResult(PoiDetailResult result) {
//            // TODO Auto-generated method stub
//            if (result.error != SearchResult.ERRORNO.NO_ERROR) {
//                Toast.makeText(getActivity(), "未找到结果  请稍后重试...", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MyApplication.getInstance(), result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//        }
//
//        @Override
//        public void onGetPoiResult(PoiResult result) {
//            // TODO Auto-generated method stub
//            //closeParkLoadingProgressBar();
//            if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//                Toast.makeText(MyApplication.getInstance(), "未找到结果  请稍后重试...", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//                //mBaidumap.clear();
//                PoiOverlay overlay = new MyPoiOverlay(mBaidumap);
//                mBaidumap.setOnMarkerClickListener(overlay);
//                overlay.setData(result);
//                overlay.addToMap();
//                overlay.zoomToSpan();
//                return;
//            }
//        }
//    };
//
//    //停车场搜索结束********************************************************************
//
//    //根据坐标和地址自动获取路径
//    public void getRoutePlanAuto() {
//        if (endLocation != null && endAddress != null) {
//            binding.end.setText(endAddress);
//            route = null;
//            //binding.pre.setVisibility(View.INVISIBLE);
//            //binding.next.setVisibility(View.INVISIBLE);
//            //设置起终点信息，对于tranist search 来说，城市名无意义
//            PlanNode enNode = PlanNode.withLocation(endLocation);
//            // 实际使用中请对起点终点城市进行正确的设定
//            showLoadingProgressBar();
//            mBaidumap.clear();
//            mSearch.drivingSearch((new DrivingRoutePlanOption())
//                    .from(stNode)
//                    .to(enNode));
//        } else if (endLocation != null && endAddress == null) {
//            showLoadingProgressBar();
//            gSearch.reverseGeoCode(new ReverseGeoCodeOption().location(endLocation));
//            binding.end.setText(endAddress);
//            route = null;
//            //binding.pre.setVisibility(View.INVISIBLE);
//            //binding.next.setVisibility(View.INVISIBLE);
//            //设置起终点信息，对于tranist search 来说，城市名无意义
//            PlanNode enNode = PlanNode.withLocation(endLocation);
//            // 实际使用中请对起点终点城市进行正确的设定
//            showLoadingProgressBar();
//            mBaidumap.clear();
//            mSearch.drivingSearch((new DrivingRoutePlanOption())
//                    .from(stNode)
//                    .to(enNode));
//        } else if (endAddress != null && endLocation == null) {
//            binding.end.setText(endAddress);
//            route = null;
//            //binding.pre.setVisibility(View.INVISIBLE);
//            //binding.next.setVisibility(View.INVISIBLE);
//            //设置起终点信息，对于tranist search 来说，城市名无意义
//            PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", binding.end.getText().toString());
//            // 实际使用中请对起点终点城市进行正确的设定
//            showLoadingProgressBar();
//            mBaidumap.clear();
//            mSearch.drivingSearch((new DrivingRoutePlanOption())
//                    .from(stNode)
//                    .to(enNode));
//        }
//
//    }
//
//    private boolean hasCompletePhoneAuth() {
//        // TODO Auto-generated method stub
//
//        PackageManager pm = getActivity().getPackageManager();
//        for (String auth : authComArr) {
//            if (pm.checkPermission(auth, getActivity().getPackageName()) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean hasBasePhoneAuth() {
//        // TODO Auto-generated method stub
//
//        PackageManager pm = getActivity().getPackageManager();
//        for (String auth : authBaseArr) {
//            if (pm.checkPermission(auth, getActivity().getPackageName()) != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void onMapClick(LatLng point) {
//        mBaidumap.hideInfoWindow();
//    }
//
//    @Override
//    public boolean onMapPoiClick(MapPoi poi) {
//        return false;
//    }
//
//
//    /**
//     * 定位SDK监听函数
//     */
//    public class BaiduLocationListenner implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // map view 销毁后不在处理新接收的位置
//            if (location == null || binding.map == null)
//                return;
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentX).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaidumap.setMyLocationData(locData);
//            bdLocation = new LatLng(location.getLatitude(), location.getLongitude());
//
//            if (isFirst) {
//                isFirst = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                //MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, mapZoom);
//                //mBaidumap.animateMapStatus(u);
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.start.setText("我的位置");
//                    }
//                });
//                //binding.start.setText("我的位置");
//                startAddress = "我的位置";
//                //binding.start.setText(location.getAddrStr());
//                //startAddress = location.getAddrStr();
//                stNode = PlanNode.withLocation(ll);
//
//                LocationConvert convert = new LocationConvert();
//                convert.bd_decrypt(location.getLatitude(), location.getLongitude());
//                if (convert.gg_lon_out > 0 && convert.gg_lat_out > 0) {
//                    double gg_lon_out = convert.gg_lon_out;
//                    double gg_lat_out = convert.gg_lat_out;
//                    convert.gg_lon_out = 0;
//                    convert.gg_lat_out = 0;
//                    sNode = new BNRoutePlanNode(gg_lon_out, gg_lat_out, "我的位置", null, BNRoutePlanNode.CoordinateType.GCJ02);
//                    //getRoutePlanAuto();
//                }
//                //setButtonView(binding.lineDrive);
//            }
//
//        }
//
//
//        public void onReceivePoi(BDLocation poiLocation) {
//        }
//    }
//
//
//    public OnStreetListInteractionListener mStreetListener = new OnStreetListInteractionListener() {
//        @Override
//        public void OnStreetListInteractionListener(TreasuresBean bean) {
//            Log.i("CulturalRelicsMap", String.format("ID: %d NAME: %s", bean.getId(), bean.getTitle()));
//            mapSearchPopupWindow.dismiss();
//            updateListDismissView(bean);
//        }
//    };
//
//    public void updateListDismissView(TreasuresBean bean) {
//        String point = bean.getPoint();
//        if (point.isEmpty() || !point.contains(",")) {
//            return;
//        }
//        String[] points = point.split(",");
//        double lng, lat;
//        try {
//            lng = Double.valueOf(points[0]);
//            lat = Double.valueOf(points[1]);
//        } catch (java.lang.NumberFormatException e) {
//            return;
//        }
//        initInfoWindow(0, lng, lat, bean);
//    }
//
//
//    public void showSearchPop() {
//        if (null == mapSearchPopupWindow)
//            mapSearchPopupWindow = new MapSearchPopupWindow(getActivity(), mStreetListener, mCulturalRelicsMapViewModel);
//        mapSearchPopupWindow.showAtLocation(binding.getRoot(), Gravity.TOP, 0, 0);
//    }
//
//
//
//
//
//
//
//
//
//    public void getMapDataList() {
//        GetMapDataRequest request = new GetMapDataRequest();
//        mCulturalRelicsMapViewModel.getMapDataListResult(request).observe(this, new Observer<List<TreasuresBean>>() {
//            @Override
//            public void onChanged(@Nullable List<TreasuresBean> treasuresBeans) {
//                updateMapDataView(treasuresBeans);
//            }
//        });
//
//    }
//
//
//
//
//
//
//
//    public void updateMapDataView(List<TreasuresBean> treasuresBeans) {
//        if (null == treasuresBeans) {
//            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        Log.i("TAG", "绘制在地图上的点====" + treasuresBeans.toString());
//
//        addOverlayOptions(treasuresBeans);   //绘制marker的方法
//    }
//
//
//
//
//
//
//    public void addOverlayOptions(List<TreasuresBean> treasuresBeans) {
//        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.map_wz);
//
//        for (int i = 0; i < treasuresBeans.size(); i++) {
//            TreasuresBean bean = treasuresBeans.get(i);
//            String point = bean.getPoint();
//            if (point.isEmpty() || !point.contains(",")) {
//                continue;
//            }
//            Bundle mBundle = new Bundle();
//            mBundle.putInt("index", i);
//            mBundle.putSerializable("bean", bean);
//            String[] points = point.split(",");
//            double lng, lat;
//            try {
//                lng = Double.valueOf(points[0]);
//                lat = Double.valueOf(points[1]);
//            } catch (java.lang.NumberFormatException e) {
//                continue;
//            }
//            mBundle.putDouble("lng", lng);
//            mBundle.putDouble("lat", lat);
//            LatLng mLatLng = new LatLng(lat, lng);
//            OverlayOptions mOverlayOptions = new MarkerOptions()
//                    .position(mLatLng)
//                    .title(bean.getTitle())
//                    .icon(bitmap)
//                    .extraInfo(mBundle);
//            options.add(mOverlayOptions);
//        }
//        mBaidumap.addOverlays(options);
//        mBaidumap.setOnMarkerClickListener(onMarkerClickListener);
//    }
//
//
//
//
//    BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
//        @Override
//        public boolean onMarkerClick(Marker marker) {
//
//            Log.i("TAG", "=======marker点击事件");
//            Intent intent = new Intent();
//            Bundle bundle = marker.getExtraInfo();
//            int index = bundle.getInt("index");
//            double lng = bundle.getDouble("lng");
//            double lat = bundle.getDouble("lat");
//            TreasuresBean bean = (TreasuresBean) bundle.getSerializable("bean");
//            initInfoWindow(index, lng, lat, bean);
//            return false;
//        }
//    };
//
//
//
//
//    private void initInfoWindow(int index, double lng, double lat, TreasuresBean bean) {
//        LatLng llCircle = new LatLng(lat, lng);
//        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(llCircle, 19);
//        //以动画方式更新地图状态，动画耗时 300 ms
//        binding.map.getMap().animateMapStatus(mMapStatusUpdate);
//        OverlayOptions ooCircle = new CircleOptions().fillColor(0x707B4242)
//                .center(llCircle).stroke(new Stroke(5, 0xAA7B4242)).radius(30);
//        //设置颜色和透明度，均使用16进制显示，0xAARRGGBB，如 0xAA000000 其中AA是透明度，000000为颜色
//        final Overlay circleOverlay = binding.map.getMap().addOverlay(ooCircle);
//
//        //创建InfoWindow展示的view
//        FragmentMainMapInfoWindowBinding InfoWindowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_main_map_info_window, null, false);
//        InfoWindowBinding.setMTreasuresBean(bean);
//        InfoWindowBinding.setImageUrl(Constants.BASEURL + bean.getPicturePath());
//        InfoWindowBinding.textClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                circleOverlay.remove();
//                Log.i("TAG", "=========详情点击事件");
//
//                binding.map.getMap().hideInfoWindow();
//            }
//        });
//        //定义用于显示该InfoWindow的坐标点
//        LatLng pt = new LatLng(lat, lng);
//        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
//        InfoWindow mInfoWindow = new InfoWindow(InfoWindowBinding.getRoot(), pt, -47);
//        //显示InfoWindow
//        binding.map.getMap().showInfoWindow(mInfoWindow);
//    }
//
//
//
//
//    @Override
//    public void onPause() {
//        binding.map.onPause();
//        super.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        binding.map.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mLocClient != null)
//            mLocClient.stop();
//        mSearch.destroy();
//        gSearch.destroy();
//        binding.map.onDestroy();
//        //binding.map = null;
//        // 停止方向传感器
//        myOrientationListener.stop();
//        super.onDestroy();
//    }
//}
