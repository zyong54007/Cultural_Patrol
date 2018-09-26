package com.zhjy.cultural.services.patrol.network;

import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        onAfter();
    }

    @Override
    public void onError(Throwable e) {
        onAfter();
    }

    @Override
    public void onNext(T t) {

    }

    public void onAfter() {

    }
}
