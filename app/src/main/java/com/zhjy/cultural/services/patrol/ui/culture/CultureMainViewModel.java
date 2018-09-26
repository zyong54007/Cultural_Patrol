package com.zhjy.cultural.services.patrol.ui.culture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureUser;
import com.zhjy.cultural.services.patrol.db.CultureUserDao;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * MainViewModel <br/>
 */
public class CultureMainViewModel extends ViewModel {

    CultureApi cultureApi;
    CultureUserDao cultureUserDao;

    @Inject
    CultureMainViewModel(CultureApi cultureApi,CultureUserDao cultureUserDao) {
        this.cultureApi = cultureApi;
        this.cultureUserDao = cultureUserDao;
    }



    @WorkerThread
    public void insert(final CultureUser cultureUser) {
        Observable.just(cultureUser)
                .subscribeOn(Schedulers.io())
                .subscribe( new Subscriber<CultureUser>() {
                    @Override
                    public void onNext(CultureUser cultureUser) {
                        cultureUserDao.insert(cultureUser);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
        });
    }

    /**
        LiveData<List<CultureUser>> liveData = cultureUserDao.findAll();
        liveData = Transformations.map(liveData, new Function<List<CultureUser>, List<CultureUser>>(){
        @Override
        public List<CultureUser> apply(List<CultureUser> inputList) {
        for(CultureUser user : inputList){
        Log.i("user ",user.username);
        }
        return inputList;
        }
        });

        Transformations.map(liveData, userList ->  {
            for(CultureUser user : userList){
                Log.i("user ",user.username);
            }
            return userList;
        });
         @return
     */

    @MainThread
    public LiveData<List<CultureUser>> loadDbData(){
        //保存上次查询结果
        //cultureUserDao.findAll().observeForever(new SimpleObserver<>(liveUserList));
        //只获取当前的查询结果
        LiveData<List<CultureUser>> liveData = cultureUserDao.findAll();
        return liveData;
    }


}