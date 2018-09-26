package com.zhjy.cultural.services.patrol.ui.route;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.MainThread;
import android.support.v4.content.ContextCompat;

import javax.inject.Singleton;

/**
 * Created by jialg on 2018/1/5.
 */

public class LocationLiveData extends LiveData<Location> {
    private static LocationLiveData sInstance;
    private static Context mContext;
    private LocationManager locationManager;
    private static final int REQUESTCODE = 1001;

    @MainThread
    @Singleton
    public static LocationLiveData get(Context context) {
        if (sInstance == null) {
            sInstance = new LocationLiveData(context.getApplicationContext());
        }
        mContext = context;
        return sInstance;
    }

    private SimpleLocationListener listener = new SimpleLocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setValue(location);
        }
    };

    @Singleton
    public LocationLiveData(Context context) {
        locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
    }

    @Override
    protected void onActive() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15 * 60 * 1000, 0, listener);
            }else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15 * 60 * 1000, 0, listener);
            }
        }
    }

    @Override
    protected void onInactive() {
        locationManager.removeUpdates(listener);
    }
}
