package com.zhjy.cultural.services.patrol.ui.culture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.evernote.android.job.JobManager;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RouteBean;
import com.zhjy.cultural.services.patrol.databinding.ActivityCultureLocationBinding;
import com.zhjy.cultural.services.patrol.job.DemoSyncJob;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.list.CultureListActivity;
import com.zhjy.cultural.services.patrol.ui.map.BaiduForegroundActivity;
import com.zhjy.cultural.services.patrol.ui.map.BaiduMapActivity;
import com.zhjy.cultural.services.patrol.ui.photo.MyTakePhotoActivity;
import com.zhjy.cultural.services.patrol.ui.route.LocationService;
import com.zhjy.cultural.services.patrol.ui.route.RouteViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.UpdateVersionActivity;
import com.zhjy.cultural.services.patrol.util.CultureUtils;

import java.util.List;

import javax.inject.Inject;


public class CultureLocationActivity extends AacBaseActivity<ActivityCultureLocationBinding> {


    private Intent lntentServices;

    @Inject
    AppContext appContext;

    @Inject
    JobManager jobManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_culture_location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
    }

    public void getLocation(View view){
        lntentServices = new Intent(appContext, LocationService.class);
        startService(lntentServices);
    }

    public void stopLocation(View view){
        lntentServices = new Intent(appContext, LocationService.class);
        stopService(lntentServices);
        /*AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent in  = new Intent(this,LocationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,LocationService.LocationId,in,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);*/
    }

    public void startZhihuList(View view){
        startActivity(new Intent(getContext(), CultureMainActivity.class));
    }

    public void loadDbData(View view){
       /* CultureMainViewModel cultureMainViewModel = ViewModelProviders.of(this, viewModelFactory()).get(CultureMainViewModel.class);
        cultureMainViewModel.loadDbData().observe(this, new Observer<List<CultureUser>>() {
            @Override
            public void onChanged(@Nullable List<CultureUser> cultureList) {
                for(CultureUser user : cultureList){
                    Log.i("db select",user.id + user.username);
                }
            }
        });*/

        RouteViewModel routeViewModel = ViewModelProviders.of(this, viewModelFactory()).get(RouteViewModel.class);
        routeViewModel.loadDbData().observe(this, new Observer<List<RouteBean>>() {
            @Override
            public void onChanged(@Nullable List<RouteBean> list) {
                if(list != null && list.size() > 0){
                    for(RouteBean bean : list){
                        Log.i("db select route",String.format("%s %s %s %s ", bean.id, bean.dateTime,bean.latitude,bean.longitude));
                    }
                }
            }
        });
    }

    public void loadServicesList(View view){
        CultureUtils.getServicesList(this);
    }

    public void startServicesJob(View view){
        DemoSyncJob.scheduleJob();
    }

    public void StopServicesJob(View view){

        DemoSyncJob.cancelAll();
    }

    public void updateVersion(View view){

        Intent intent = new Intent(this,UpdateVersionActivity.class);
        startActivity(intent);

       /* VersionParams.Builder builder = new VersionParams.Builder()
                .setRequestUrl("http://www.hdggwh.com/version.xml")
                .setService(UpdateService.class);
        AllenChecker.startVersionCheck(AppContext.getInstance(), builder.build());*/
    }

    public void takePhoto(View view){
        Intent intent = new Intent(this,MyTakePhotoActivity.class);
        startActivity(intent);
    }

    public void startList(View view){
        Intent intent = new Intent(this,CultureListActivity.class);
        startActivity(intent);
    }

    public void startBaiduForeground(View view){
        Intent intent = new Intent(this,BaiduForegroundActivity.class);
        startActivity(intent);
    }

    public void startBaiduLocation(View view){
        Intent intent = new Intent(this,BaiduMapActivity.class);
        startActivity(intent);
    }
}
