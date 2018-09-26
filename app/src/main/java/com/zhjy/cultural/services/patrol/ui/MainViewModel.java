package com.zhjy.cultural.services.patrol.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetLoginRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetLoginResponse;
import com.zhjy.cultural.services.patrol.db.CultureUserDao;
import com.zhjy.cultural.services.patrol.util.SimpleObserver;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * MainViewModel <br/>
 * Created by xiaqiulei on 2017-05-25.
 */
public class MainViewModel extends ViewModel {

    CultureApi cultureApi;
    CultureUserDao cultureUserDao;

    MutableLiveData<GetLoginResponse> loginInfo = new MutableLiveData<>();

    @Inject
    MainViewModel(CultureApi cultureApi,CultureUserDao cultureUserDao) {
        this.cultureApi = cultureApi;
        this.cultureUserDao = cultureUserDao;

    }

    LiveData<GetLoginResponse> getLoginInfo(GetLoginRequest request){
        cultureApi.getLoginResult(request).observeForever(new SimpleObserver<>(loginInfo));
        return loginInfo;
    }


    @MainThread
    public LiveData<CultureUser> loadUserData(){
        //保存上次查询结果
        //cultureUserDao.findAll().observeForever(new SimpleObserver<>(liveUserList));
        //只获取当前的查询结果
        LiveData<CultureUser> liveData = cultureUserDao.findById("1");
        return liveData;
    }

    @WorkerThread
    public void insertUser(final CultureUser cultureUser) {
        Observable.just(cultureUser)
                .subscribeOn(Schedulers.io())
                .subscribe( new Subscriber<CultureUser>() {
                    @Override
                    public void onNext(CultureUser cultureUser) {
                        cultureUserDao.insert(cultureUser);
                    }

                    @Override
                    public void onCompleted() {
                        Log.i("insertUser","数据插入成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    @WorkerThread
    public void deleteUser(final CultureUser cultureUser) {
        Observable.just(cultureUser)
                .subscribeOn(Schedulers.io())
                .subscribe( new Subscriber<CultureUser>() {
                    @Override
                    public void onNext(CultureUser cultureUser) {
                        cultureUserDao.deleteAll(cultureUser);
                    }

                    @Override
                    public void onCompleted() {
                        Log.i("deleteUser","数删除成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

}