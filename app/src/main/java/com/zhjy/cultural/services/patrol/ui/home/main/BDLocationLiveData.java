package com.zhjy.cultural.services.patrol.ui.home.main;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.MainThread;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.zhjy.cultural.services.patrol.ui.map.BaiduLocationService;

import javax.inject.Singleton;

/**
 * Created by jialg on 2018/1/26.
 */

public class BDLocationLiveData  extends LiveData<BDLocation> {

    private static BDLocationLiveData sInstance;

    private static Context locationContext;

    private static BaiduLocationService baiduLocationService;


    @MainThread
    @Singleton
    public static BDLocationLiveData get(Context context,BaiduLocationService locationService) {
        if (sInstance == null) {
            locationContext = context;
            baiduLocationService = locationService;
            sInstance = new BDLocationLiveData();
        }
        return sInstance;
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
                setValue(bdLocation);
            }
        }
    };

    @Singleton
    public BDLocationLiveData() {
        baiduLocationService.setLocationOption(baiduLocationService.getDefaultLocationClientOption());
    }

    @Override
    protected void onActive() {
        baiduLocationService.registerListener(mListener);
        baiduLocationService.start();
    }

    @Override
    protected void onInactive() {
        baiduLocationService.unregisterListener(mListener); //注销掉监听
        baiduLocationService.stop(); //停止定位服务
    }
}
