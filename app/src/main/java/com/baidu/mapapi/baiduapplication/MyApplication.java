package com.baidu.mapapi.baiduapplication;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.service.LocationService;

public class MyApplication extends Application {
	private static MyApplication instance;
	private static TransitRouteResult busRouteResult;
	public LocationService locationService;
	public Vibrator mVibrator;
	@SuppressWarnings("static-access")
	@Override
	public void onCreate() {
		//setTheme(R.style.Theme.AppStartLoad);
		super.onCreate();
		final Context mContext = this;
		instance = this;
		locationService = new LocationService(getApplicationContext());
		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
		SDKInitializer.initialize(getApplicationContext());
	}
	public static MyApplication getInstance(){
		// 这里不用判断instance是否为空
		return instance;
	}

	public static void setBusRouteResult (TransitRouteResult busRouteResult){
		MyApplication.busRouteResult = busRouteResult;
	}
	public static TransitRouteResult getBusRouteResult(){
		return busRouteResult;
	}

}