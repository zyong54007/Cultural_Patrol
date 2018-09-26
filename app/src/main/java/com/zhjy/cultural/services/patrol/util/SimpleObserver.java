package com.zhjy.cultural.services.patrol.util;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * SimpleObserver <br/>
 */
public class SimpleObserver<T> implements Observer<T> {

    @NonNull
    private final MutableLiveData<T> data;

    public SimpleObserver(@NonNull MutableLiveData<T> data) {
        this.data = data;
    }

    @Override
    public void onChanged(@Nullable T t) {
        data.postValue(t);
    }
}