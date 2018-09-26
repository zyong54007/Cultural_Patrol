package com.zhjy.cultural.services.patrol.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by jialg on 2018/1/9.
 */

public  class CultureUtils {
    private static final String TAG = "CultureUtils";
    public static String getDateTime(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    public static String getUUID(){
        return java.util.UUID.randomUUID().toString();
    }

    public static void getServicesList(Context context){
        ActivityManager activityManger = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);// 获取Activity管理器
        List<ActivityManager.RunningServiceInfo> serviceList = activityManger.getRunningServices(100);// 从窗口管理器中获取正在运行的Service
        for(ActivityManager.RunningServiceInfo service : serviceList){
            Log.i(TAG,service.service.getClassName());
        }
    }


}
