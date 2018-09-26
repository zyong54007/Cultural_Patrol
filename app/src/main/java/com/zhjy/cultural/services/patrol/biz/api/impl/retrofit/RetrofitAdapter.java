package com.zhjy.cultural.services.patrol.biz.api.impl.retrofit;


import com.zhjy.cultural.services.patrol.core.net.AppException;

abstract class RetrofitAdapter<T> {


    abstract T call() throws Exception;

    protected T get() throws AppException {
        T t;
        try {
            t = call();
        } catch (Exception e) {
            throw new AppException(e);
        }
        return t;
    }
}