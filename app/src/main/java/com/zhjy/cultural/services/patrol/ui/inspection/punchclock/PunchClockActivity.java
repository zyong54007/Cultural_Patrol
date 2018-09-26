package com.zhjy.cultural.services.patrol.ui.inspection.punchclock;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointSaveResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityPunchClockBinding;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.Distance;
import com.zhjy.cultural.services.patrol.network.GRetrofit;
import com.zhjy.cultural.services.patrol.network.GemService;
import com.zhjy.cultural.services.patrol.network.URLs;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.add.InspectionAddActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class PunchClockActivity extends AacBaseActivity<ActivityPunchClockBinding> {

    PunchClockViewModel punchClockViewModel;

    private LatLng latlng;

    private int wwId;

    private int recordId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_punch_clock;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private String point;   //传过来的文物的经纬度

    private void init() {
        InjectHelp.appComponent().inject(this);
        punchClockViewModel = ViewModelProviders.of(this, viewModelFactory()).get(PunchClockViewModel.class);
        Intent intent = getIntent();
        point = intent.getStringExtra(Contant.POINT);
        wwId = intent.getIntExtra("wwId", 0);
        recordId = intent.getIntExtra("recordId", 0);
        initLocationData();
        initListener();
    }

    /**
     * 根据经纬度位置判断打卡状态
     */
    private void initView() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .build();
        GemService gemService = retrofit.create(GemService.class);
//        http://wwgl.hdggwh.com/wwgl/task/getDistance;jsessionid=123DC416FF693FE66A6A3A30039A989F
        String url = "task/getDistance;jsessionid=" + AppContext.getJsessionid();
//        Log.i("TAG", "url===========" + url);
        Call<ResponseBody> call = (Call<ResponseBody>) gemService.DISTANCE(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String json = response.body().string();
                    Gson gson = new Gson();
                    Distance distance = gson.fromJson(json, Distance.class);
                    String distance1 = distance.getDistance();
                    String[] split = point.split(",");
                    String str_longitude = split[0];
                    String latitude = split[1];
                    Double d_longitude = Double.valueOf(str_longitude);
                    Double d_latitude = Double.valueOf(latitude);
                    double v = GetShortDistance(d_latitude, d_longitude, latlng.latitude, latlng.longitude);
                    double disintance = Double.valueOf(distance1);

                    if (disintance == 0) {
                        //当前限制距离为0  可以打卡
                        binding.normal.setVisibility(View.VISIBLE);
                    } else {
                        //不为0  判断距离
                        if (v > disintance) {  //距离大于返回的距离   不能打卡
                            //当前位置不能打卡
                            binding.abnormal.setVisibility(View.VISIBLE);

                        } else {
                            binding.normal.setVisibility(View.VISIBLE);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.d("", "error");
            }
        });

//        new GRetrofit().request(GemService.class).DISTANCE()
//                .compose(GRetrofit.observeOnMainThread())


    }

    public void initListener() {

    }

    private void savePointResult() {
        String point = String.format("%f,%f", latlng.latitude, latlng.longitude);
        GetPointSaveRequest request = new GetPointSaveRequest(point);
        punchClockViewModel.getPointResult(request).observe(this, new Observer<GetPointSaveResponse>() {
            @Override
            public void onChanged(@Nullable GetPointSaveResponse getPointSaveResponse) {
                updatePointView(getPointSaveResponse);
            }
        });
    }

    private void updatePointView(GetPointSaveResponse response) {
        if (null == response) {
            Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, InspectionAddActivity.class);
        intent.putExtra("wwId", wwId);
        intent.putExtra("recordId", recordId);
        intent.putExtra("point_ids", response.getId());
        startActivity(intent);
        finish();
    }

    private void initLocationData() {
        punchClockViewModel.getLicationLiveData().observe(this, new Observer<BDLocation>() {
            @Override
            public void onChanged(@Nullable BDLocation bdLocation) {

                double latitude = bdLocation.getLatitude();
                double longitude = bdLocation.getLongitude();
                setMapLocation(bdLocation);
            }
        });
    }

    private void setMapLocation(BDLocation bdLocation) {
        latlng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latlng, 19);
        binding.map.getMap().animateMapStatus(mMapStatusUpdate);

        // 当不需要定位图层时关闭定位图层
        binding.map.getMap().setMyLocationEnabled(true);

        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.punch_clock_location_a);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        binding.map.getMap().setMyLocationConfiguration(config);

        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())//
                .direction(bdLocation.getDirection())// 方向
                .latitude(bdLocation.getLatitude())//
                .longitude(bdLocation.getLongitude())//
                .build();
        binding.map.getMap().setMyLocationData(locData);
        initView();


    }

    public void stepToNext(View view) {
        if (null != latlng) {
            savePointResult();
        }
    }

    public void finish(View view) {
        finish();
    }


    static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI = 6.28318530712; // 2*PI
    static double DEF_PI180 = 0.01745329252; // PI/180.0
    static double DEF_R = 6370693.5; // radius of earth

    //
//    /**
//     * 跳过打卡
//     */
    public void AbnormalNext(View view) {
        Intent intent = new Intent(this, InspectionAddActivity.class);
        intent.putExtra("wwId", wwId);
        intent.putExtra("recordId", recordId);
        intent.putExtra("point_ids", "");
        startActivity(intent);
        finish();

    }


    /**
     * 计算两个经纬度点之前的距离
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     */
    public double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }


}
