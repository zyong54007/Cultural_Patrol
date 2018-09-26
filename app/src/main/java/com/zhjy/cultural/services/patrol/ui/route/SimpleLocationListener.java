package com.zhjy.cultural.services.patrol.ui.route;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jialg on 2017/12/29.
 */

public class SimpleLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {
        Log.i("LocationChanged",String.format("%d , %d" ,location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
