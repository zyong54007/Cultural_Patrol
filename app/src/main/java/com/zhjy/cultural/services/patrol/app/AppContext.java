package com.zhjy.cultural.services.patrol.app;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.evernote.android.job.JobManager;
import com.zhjy.cultural.services.patrol.job.DemoJobCreator;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.network.HttpConfig;
import com.zhjy.cultural.services.patrol.util.SPUtils;

import javax.inject.Inject;


/**
 * AppContext <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class AppContext extends Application {

    private static String jsessionid;

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getBaseContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        HttpConfig.init(this);
        initInject();
        initBaiduMap();
        //initJobManager();
    }

    void initJobManager() {
        JobManager.create(this).addJobCreator(new DemoJobCreator());
    }

    void initBaiduMap() {
        SDKInitializer.initialize(this);
    }

    void initInject() {
        InjectHelp.init(this);
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return factory;
    }

    public static void setJsessionid(String jsessionid) {
        instance.jsessionid = jsessionid;
    }

    public static String getJsessionid() {
        if (TextUtils.isEmpty(instance.jsessionid)) {
            String jessionid = SPUtils.get(Contant.JESSSIONID, "");
            return jessionid;
        }
        return instance.jsessionid;
    }


}