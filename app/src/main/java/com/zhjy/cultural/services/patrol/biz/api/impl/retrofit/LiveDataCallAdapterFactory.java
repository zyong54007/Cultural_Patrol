package com.zhjy.cultural.services.patrol.biz.api.impl.retrofit;

import android.arch.lifecycle.LiveData;

import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (!BaseResponse.class.isAssignableFrom(rawObservableType)) {
            throw new IllegalArgumentException("type must extends BaseResponse");
        }
        return new LiveDataCallAdapter<>(observableType);
    }
}