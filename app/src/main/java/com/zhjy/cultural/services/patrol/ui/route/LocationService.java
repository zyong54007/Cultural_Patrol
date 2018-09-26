package com.zhjy.cultural.services.patrol.ui.route;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.arch.lifecycle.LifecycleService;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.evernote.android.job.JobManager;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RouteBean;
import com.zhjy.cultural.services.patrol.db.RouteDao;
import com.zhjy.cultural.services.patrol.job.DemoSyncJob;
import com.zhjy.cultural.services.patrol.util.CultureUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jialg on 2018/1/5.
 */

public class LocationService extends LifecycleService {

    public static final String TAG = "LocationService";
    public static final int LocationId = 0x1001;
    public boolean isRunning = false;

    @Inject
    RouteDao routeDao;

    @Inject
    CultureApi cultureApi;

    @Inject
    AppContext appContext;

    @Inject
    JobManager jobManager;

    @Override
    public void onCreate() {
        super.onCreate();
        InjectHelp.appComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        if(!isRunning){
            work();
        }
        isRunning = true;
        alarmWork();
        //return super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }


    public void work(){
        LocationLiveData.get(appContext).observe(this,  new Observer<Location>(){
            @Override
            public void onChanged(@Nullable Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                String dateTime = CultureUtils.getDateTime();
                String id = CultureUtils.getUUID();
                Log.i(TAG, String.format("lat : %f lon: %f",lat,lon));
                RouteBean bean = new RouteBean(id,String.valueOf(lat),String.valueOf(lon),"10000","userName",dateTime,"0");
                insert(bean);
                startForeground();
            }
        });
        DemoSyncJob.scheduleJob();
    }


    public void startForeground(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("定位服务");
            builder.setContentText("定位服务正在运行...");
            Notification notification = builder.build();
            notification.flags = Notification.FLAG_ONGOING_EVENT;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            startForeground(1,notification);
        }else {
            Notification notification = new Notification();
            notification.flags = Notification.FLAG_ONGOING_EVENT;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            startForeground(1, notification);
        }
    }

    public void alarmWork(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int  time = 1000*60*15;
        long startTime = SystemClock.elapsedRealtime()+time;
        Intent in  = new Intent(appContext, LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,LocationId,in,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, startTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, startTime, pendingIntent);
        } else {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, startTime, time, pendingIntent);
        }

    }

    public void stopAlarmWork(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent in  = new Intent(appContext, LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,LocationId,in,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    public void startWork(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //5秒的时间
        int  time = 1000*60*15;
        //获取系统开机至今所经历的毫秒
        long startTime = SystemClock.elapsedRealtime()+time;
        Intent in  = new Intent(appContext, LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,LocationId,in,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,startTime,pendingIntent);
    }

    @WorkerThread
    public void insert(final RouteBean bean) {
        Observable.just(bean)
                .subscribeOn(Schedulers.io())
                .subscribe( new Subscriber<RouteBean>() {
                    @Override
                    public void onNext(RouteBean bean) {
                        routeDao.insert(bean);
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, String.format("insert lat : %s lon: %s",bean.latitude,bean.longitude));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, String.format("insert route error"));
                    }
                });
    }


    @MainThread
    public LiveData<List<RouteBean>> loadDbData(){
        LiveData<List<RouteBean>> liveData = routeDao.findByStatus("1","0");
        return liveData;
    }

    @Override
    public void onDestroy() {
        DemoSyncJob.cancelAll();
        stopAlarmWork();
        stopForeground(true);
        isRunning = false;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return null;
    }


}