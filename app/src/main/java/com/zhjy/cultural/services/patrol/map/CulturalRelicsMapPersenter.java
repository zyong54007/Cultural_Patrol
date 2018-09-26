package com.zhjy.cultural.services.patrol.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.baiduapplication.LocationConvert;
import com.baidu.mapapi.baiduapplication.MyOrientationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.squareup.picasso.Picasso;
import com.zhjy.cultural.services.patrol.NavigationActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;
import com.zhjy.cultural.services.patrol.mvp.FragmentPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMapViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.OnStreetListInteractionListener;
import com.zhjy.cultural.services.patrol.ui.home.map.search.MapSearchPopupWindow;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 导航的p层
 */
public class CulturalRelicsMapPersenter extends FragmentPresenter<CulturalRelicsMapPersenter.CulturalUI> {

    public interface CulturalUI extends GEMUI {
//        RadioButton getallradiobutton();
//
//        RadioButton getcountryradiobutton();
//
//        RadioButton getcityradiobutton();
//
//        RadioButton getcountyradiobutton();
//
//        RadioButton getstreetradiobutton();

        LinearLayout getmaproot();

        MapView getbaidumap();

//        ImageView search();

    }

    private LocationClient mLocClient;  //定位
    private BaiduMap mBaidumap;
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    final public static int REQUEST_CODE_ACCESS_FINE_LOCATION = 000001;
    private BaiduLocationListenner baiduListener = new BaiduLocationListenner();
    private PlanNode stNode;   //定位相关
    private BNRoutePlanNode sNode = null;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    private LatLng bdLocation = null;
    private boolean isFirst = true;
    private List<TreasuresBean> alllist = new ArrayList<>();   //全部
    private List<TreasuresBean> countrylist = new ArrayList<>();  //国家
    private List<TreasuresBean> citylist = new ArrayList<>();     //城市
    private List<TreasuresBean> countylist = new ArrayList<>();   //县区
    private List<TreasuresBean> surveylist = new ArrayList<>();   //文物普查
    double latitude;
    double longitude;

    /**
     * 初始化百度地图信息
     */
    @SuppressLint("CheckResult")
    public void initmap() {
        mCulturalRelicsMapViewModel = ViewModelProviders.of(getActivity(), viewModelFactory()).get(CulturalRelicsMapViewModel.class);
        getUI().finder().radiobutton(R.id.nav_all).setChecked(true);
        getUI().getbaidumap().showZoomControls(false);
        mBaidumap = getUI().getbaidumap().getMap();
        mBaidumap.setTrafficEnabled(true);
        mBaidumap.setMyLocationEnabled(true);
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.compass); // 自定义图标
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL; // 罗盘模式
        mBaidumap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));

        //定位开始
        if (Build.VERSION.SDK_INT >= 23) {
            int checkAccessFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ACCESS_FINE_LOCATION);
            } else {
                getBaiduLocation();
            }
        } else {
            getBaiduLocation();
        }


        getMapDataList();


    }

    /**
     * 全部
     */
    public void Alladdmarker() {
        addOverlayOptions(alllist);
    }

    /**
     * 国家
     */
    public void CountryAddmarker() {

        addOverlayOptions(countrylist);
    }

    /**
     * 城市
     */
    public void CityAddmarker() {
        addOverlayOptions(citylist);
    }

    /**
     * 县区
     */
    public void CountyAddmarker() {
        addOverlayOptions(countylist);
    }

    /**
     * 文物普查
     */
    public void StreetAddmarker() {
        addOverlayOptions(surveylist);
    }

    MapSearchPopupWindow mapSearchPopupWindow;

    /**
     * 搜索
     */
    public void showsearch() {
//        if (null == mapSearchPopupWindow)
        mapSearchPopupWindow = new MapSearchPopupWindow(getActivity(), mStreetListener, mCulturalRelicsMapViewModel);
        mapSearchPopupWindow.showAtLocation(getUI().getmaproot(), Gravity.TOP, 0, 0);

    }


    public OnStreetListInteractionListener mStreetListener = new OnStreetListInteractionListener() {
        @Override
        public void OnStreetListInteractionListener(TreasuresBean bean) {
            Log.i("CulturalRelicsMap", String.format("ID: %d NAME: %s", bean.getId(), bean.getTitle()));
            mapSearchPopupWindow.dismiss();
            updateListDismissView(bean);
        }
    };

    public void updateListDismissView(TreasuresBean bean) {
        String point = bean.getPoint();
        if (point.isEmpty() || !point.contains(",")) {
            return;
        }
        String[] points = point.split(",");
        double lng, lat;
        try {
            lng = Double.valueOf(points[0]);
            lat = Double.valueOf(points[1]);
        } catch (java.lang.NumberFormatException e) {
            return;
        }
        initInfoWindow(0, lng, lat, bean);
    }


    CulturalRelicsMapViewModel mCulturalRelicsMapViewModel;


    public void getMapDataList() {
        GetMapDataRequest request = new GetMapDataRequest();
        mCulturalRelicsMapViewModel.getMapDataListResult(request).observe(getActivity(), new Observer<List<TreasuresBean>>() {
            @Override
            public void onChanged(@Nullable List<TreasuresBean> treasuresBeans) {
//                Log.i("TAG", "============地图marker" + treasuresBeans.toString());
                updateMapDataView(treasuresBeans);
            }
        });

    }

    public void updateMapDataView(List<TreasuresBean> treasuresBeans) {
        if (null == treasuresBeans) {
//            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();

            Toast.makeText(getActivity(), "应用已退出 需要重新登录", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);
            getActivity().finish();
            return;
        }
        alllist = treasuresBeans;

        for (TreasuresBean bean : treasuresBeans) {
            int wwType = bean.getWwType();
            if (wwType == 1) {
                countrylist.add(bean);
            }

            if (wwType == 2) {
                citylist.add(bean);
            }

            if (wwType == 3) {
                countylist.add(bean);
            }
            if (wwType == 4) {
                surveylist.add(bean);
            }
        }


//        Log.i("TAG", "绘制在地图上的点====" + treasuresBeans.toString());


        addOverlayOptions(treasuresBeans);   //绘制marker的方法
    }

    public void addOverlayOptions(List<TreasuresBean> treasuresBeans) {
        if (treasuresBeans.size() == 0) {
            getUI().getbaidumap().getMap().clear();

            return;
        }
        getUI().getbaidumap().getMap().clear();


        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.map_wz);

        for (int i = 0; i < treasuresBeans.size(); i++) {
            TreasuresBean bean = treasuresBeans.get(i);
            String point = bean.getPoint();
            if (point.isEmpty() || !point.contains(",")) {
                continue;
            }
            Bundle mBundle = new Bundle();
            mBundle.putInt("index", i);
            mBundle.putSerializable("bean", bean);
            String[] points = point.split(",");
            double lng, lat;
            try {
                lng = Double.valueOf(points[0]);
                lat = Double.valueOf(points[1]);
            } catch (java.lang.NumberFormatException e) {
                continue;
            }
            mBundle.putDouble("lng", lng);
            mBundle.putDouble("lat", lat);
            LatLng mLatLng = new LatLng(lat, lng);
            OverlayOptions mOverlayOptions = new MarkerOptions()
                    .position(mLatLng)
                    .title(bean.getTitle())
                    .icon(bitmap)
                    .extraInfo(mBundle);
            options.add(mOverlayOptions);
        }
        mBaidumap.addOverlays(options);
        mBaidumap.setOnMarkerClickListener(onMarkerClickListener);
    }


    BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Log.i("TAG", "=======marker点击事件");
            Intent intent = new Intent();
            Bundle bundle = marker.getExtraInfo();
            int index = bundle.getInt("index");
            double lng = bundle.getDouble("lng");
            double lat = bundle.getDouble("lat");
            TreasuresBean bean = (TreasuresBean) bundle.getSerializable("bean");
            initInfoWindow(index, lng, lat, bean);
            return false;
        }
    };


    private void initInfoWindow(int index, double lng, double lat, TreasuresBean bean) {
        LatLng llCircle = new LatLng(lat, lng);
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(llCircle, 19);
        //以动画方式更新地图状态，动画耗时 300 ms
        mBaidumap.animateMapStatus(mMapStatusUpdate);
        OverlayOptions ooCircle = new CircleOptions().fillColor(0x707B4242)
                .center(llCircle).stroke(new Stroke(5, 0xAA7B4242)).radius(30);
        //设置颜色和透明度，均使用16进制显示，0xAARRGGBB，如 0xAA000000 其中AA是透明度，000000为颜色
        final Overlay circleOverlay = mBaidumap.addOverlay(ooCircle);

        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_map_info_window, null);

        ImageView info_iv = inflate.findViewById(R.id.info_iv);
        TextView info_name = inflate.findViewById(R.id.info_name);
        TextView info_grade = inflate.findViewById(R.id.info_grade);
        TextView info_type = inflate.findViewById(R.id.info_type);
//        TextView info_company = inflate.findViewById(R.id.company);
        TextView info_status = inflate.findViewById(R.id.status);
        TextView info_time = inflate.findViewById(R.id.time);
        TextView info_address = inflate.findViewById(R.id.address);

        TextView info_details = inflate.findViewById(R.id.info_details);
        TextView info_route = inflate.findViewById(R.id.route);
        TextView info_close = inflate.findViewById(R.id.info_close);


//        bean.getTitle();   //名称
//        bean.getWwTypeStr();  //级别
//        bean.getFxTypeStr();   //风险类别
//        bean.getWwStatusStr();  //现状
//        bean.getOpentime();   //开放时间
//        String address = bean.getAddress();
//        bean.getPicturePath();

        String picturePath = bean.getPicturePath();
        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                Picasso.with(getActivity()).load(Constants.BASE_URL_UP_IMG + bean.getPicturePath())
                        .error(R.mipmap.default_iv)
                        .placeholder(R.mipmap.default_iv)
                        .into(info_iv);
            } else {
                Picasso.with(getActivity()).load(Constants.BASEIMGURL + bean.getPicturePath())
                        .error(R.mipmap.default_iv)
                        .placeholder(R.mipmap.default_iv)
                        .into(info_iv);
            }
        }


        info_name.setText(bean.getTitle());
        info_grade.setText(bean.getWwTypeStr());
        info_type.setText(bean.getFxTypeStr());
        info_status.setText(bean.getWwStatusStr());
        String opentime = bean.getOpentime();
        if (TextUtils.isEmpty(opentime)) {
            info_time.setText("未知");
        }
        info_address.setText(bean.getAddress());

        info_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TreasuresActivity.class);
                intent.putExtra("id", bean.getId());
                getActivity().startActivity(intent);
                Log.i("TAG", "======详情点击事件");
            }
        });

        info_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), NavigationActivity.class));
                Log.i("TAG", "======路线点击事件");
            }
        });

        info_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleOverlay.remove();
                mBaidumap.hideInfoWindow();
//                getUI().getbaidumap().getMap().centerAndZoom(new BM ap.Point(116.4035,39.915), 14);
                Log.i("TAG", "======关闭点击事件");
                Log.i("TAG", "中心的设置=======" + latitude + "==========" + longitude);
                LatLng cenpt = new LatLng(latitude, longitude);
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(cenpt)
                        .zoom(12)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus); //改变地图状态
                mBaidumap.setMapStatus(mMapStatusUpdate);
//                getBaiduLocation();
            }
        });


//        textView.setText(address);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("TAG", "===============点击时间出发");
//            }
//        });


        //创建InfoWindow展示的view
//        FragmentMainMapInfoWindowBinding InfoWindowBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_main_map_info_window, null, false);
//        InfoWindowBinding.setMTreasuresBean(bean);
//        InfoWindowBinding.setImageUrl(Constants.BASEURL + bean.getPicturePath());
//        InfoWindowBinding.textClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                circleOverlay.remove();
//                Log.i("TAG", "=========详情点击事件");
//
//                mBaidumap.hideInfoWindow();
//            }
//        });
        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(lat, lng);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
//        InfoWindow mInfoWindow = new InfoWindow(InfoWindowBinding.getRoot(), pt, -47);
        InfoWindow mInfoWindow = new InfoWindow(inflate, pt, -47);

        //显示InfoWindow
        mBaidumap.showInfoWindow(mInfoWindow);
    }


    public void getBaiduLocation() {
//        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(baiduListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); //设置坐标类型百度坐标
        option.setScanSpan(LocationClientOption.MIN_SCAN_SPAN * 10);
        option.setIsNeedAddress(true);
        option.setIgnoreKillProcess(true);
        mLocClient.setLocOption(option);
        mLocClient.start();

        myOrientationListener = new MyOrientationListener(getActivity());
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
                System.err.println("mCurrentX:" + mCurrentX);
                if (mBaidumap != null && mBaidumap.getLocationData() != null) {
                    MyLocationData locData = new MyLocationData.Builder()
                            .accuracy(mBaidumap.getLocationData().accuracy)
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                            .direction(mCurrentX).latitude(mBaidumap.getLocationData().latitude)
                            .longitude(mBaidumap.getLocationData().longitude).build();
                    mBaidumap.setMyLocationData(locData);
                }

            }
        });
        myOrientationListener.start();
    }


    public class BaiduLocationListenner implements BDLocationListener {
        //
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || getUI().getbaidumap() == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentX).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaidumap.setMyLocationData(locData);
            bdLocation = new LatLng(location.getLatitude(), location.getLongitude());
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            if (isFirst) {
                isFirst = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                //MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, mapZoom);
                //mBaidumap.animateMapStatus(u);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        binding.start.setText("我的位置");
                    }
                });
                //binding.start.setText("我的位置");
//                startAddress = "我的位置";
                //binding.start.setText(location.getAddrStr());
                //startAddress = location.getAddrStr();
                stNode = PlanNode.withLocation(ll);

                LocationConvert convert = new LocationConvert();
                convert.bd_decrypt(location.getLatitude(), location.getLongitude());
                if (convert.gg_lon_out > 0 && convert.gg_lat_out > 0) {
                    double gg_lon_out = convert.gg_lon_out;
                    double gg_lat_out = convert.gg_lat_out;
                    convert.gg_lon_out = 0;
                    convert.gg_lat_out = 0;
                    sNode = new BNRoutePlanNode(gg_lon_out, gg_lat_out, "我的位置", null, BNRoutePlanNode.CoordinateType.GCJ02);
                    //getRoutePlanAuto();
                }
                //setButtonView(binding.lineDrive);
            }

        }

    }


    protected ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }

    @Override
    public void onDestroy() {
        if (mLocClient != null)
            mLocClient.stop();
//        mSearch.destroy();
//        gSearch.destroy();
        getUI().getbaidumap().onDestroy();
//        binding.map = null;
        // 停止方向传感器
        myOrientationListener.stop();
        super.onDestroy();
    }


}
