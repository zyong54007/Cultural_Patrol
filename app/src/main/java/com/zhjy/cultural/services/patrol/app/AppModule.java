package com.zhjy.cultural.services.patrol.app;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.evernote.android.job.JobManager;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.api.impl.OkHttpUtil;
import com.zhjy.cultural.services.patrol.biz.api.impl.okhttp.CultureApiOkHttpImpl;
import com.zhjy.cultural.services.patrol.db.AppDb;
import com.zhjy.cultural.services.patrol.db.CultureUserDao;
import com.zhjy.cultural.services.patrol.db.RouteDao;
import com.zhjy.cultural.services.patrol.job.DemoJobCreator;
import com.zhjy.cultural.services.patrol.ui.map.BaiduLocationService;
import com.zhjy.cultural.services.patrol.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;



@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Singleton
    @Provides
    public AppContext provideAppContext(Application application){
        return (AppContext)application;
    }

    @Singleton
    @Provides
    public JobManager provideJobManager(Application application){
        JobManager jobManager = JobManager.create(application);
        jobManager.addJobCreator(new DemoJobCreator());
        return  jobManager;

    }

    @Singleton
    @Provides
    public ClearableCookieJar provideCookieJar (Application application){
        return new PersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(application));
    }

    @Provides
    CultureApi providerCultureApi(OkHttpClient client) {
        return new CultureApiOkHttpImpl(client);
    }

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient(ClearableCookieJar cookieJar) {
        return OkHttpUtil.newOkHttpClient(cookieJar);
    }

    @Provides
    @Singleton
    Gson providerGson() {
        return new GsonBuilder().registerTypeAdapterFactory(AdapterFactory.create()).create();
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder builder) {
        return new ViewModelFactory(builder.build());
    }

    @Singleton
    @Provides
    AppDb provideDb(Application application) {
        return Room.databaseBuilder(application, AppDb.class,"app.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    CultureUserDao provideCultureUserDao(AppDb db){
        return db.cultureUserDao();
    }

    @Singleton
    @Provides
    RouteDao provideRouteDao(AppDb db){
        return db.routeDao();
    }

    @Singleton
    @Provides
    BaiduLocationService provideBaiduLocationService(Application application){
        return  new BaiduLocationService(application);
    }
}