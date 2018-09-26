package com.zhjy.cultural.services.patrol.ui.route;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RouteBean;
import com.zhjy.cultural.services.patrol.db.RouteDao;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by jialg on 2018/1/9.
 */

public class RouteViewModel extends ViewModel {

    CultureApi cultureApi;

    RouteDao routeDao;

    MutableLiveData<List<RouteBean>> liveRouteList  = new MutableLiveData<>();

    @Inject
    RouteViewModel(CultureApi cultureApi,RouteDao routeDao){
        this.cultureApi = cultureApi;
        this.routeDao = routeDao;
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
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    @MainThread
    public LiveData<List<RouteBean>> loadDbData(){
        //保存上次查询结果
        //routeDao.findByStatus("10000","0").observeForever(new SimpleObserver<>(liveRouteList));
        //只获取当前的查询结果
        //LiveData<List<RouteBean>> liveData = routeDao.findByStatus("10000","0");
        return routeDao.findByStatus("10000","0");
    }

}
