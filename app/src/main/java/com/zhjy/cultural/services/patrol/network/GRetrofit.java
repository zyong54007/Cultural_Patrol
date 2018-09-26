package com.zhjy.cultural.services.patrol.network;

import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.network.exception.APIStatusErrorException;
import com.zhjy.cultural.services.patrol.network.exception.NullResponseException;
import com.zhjy.cultural.services.patrol.network.response.StatusResponse;

import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.zhjy.cultural.services.patrol.network.HttpConfig.CER;

public class GRetrofit {
    private static Retrofit retrofit;
    private static Retrofit shiftRetrofit;

    public static void init() {
        retrofit = new Retrofit.Builder()

                .client(HttpConfig.getTrusClient(new Buffer()
                        .writeUtf8(CER)
                        .inputStream()))
                .baseUrl(URLs.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shiftRetrofit = new Retrofit.Builder()
                .client(HttpConfig.getNormalClient())
                .baseUrl(URLs.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T request(Class<T> service) {
        return retrofit.create(service);
    }

    public <T> T requestShift(Class<T> service) {
        return shiftRetrofit.create(service);
    }


    /**
     * transform net response to data. remove the wrapper
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<StatusResponse<T>, T> dataTransformer() {
        return observable -> observable.flatMap(response -> {
            return Observable.create(subscriber -> {
                subscriber.onStart();
                if (response == null) {
                    subscriber.onError(new NullResponseException());
                } else if (response.isSuccess()) {
//                    subscriber.onNext(response.getData());
                    subscriber.onNext(response.getData());
                    subscriber.onCompleted();
                } else
                    subscriber.onError(new APIStatusErrorException(response.getStatus(), response.getStatus()));
            });
        });
    }

    /**
     * 网络请求的线程切换
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> observeOnMainThread(GEMUI ui) {
        return observeOnMainThread(ui, Schedulers.io());
    }

    public static <T> Observable.Transformer<T, T> observeOnMainThread(GEMUI ui, Scheduler scheduler) {
        if (ui == null) {
            return observable -> observable.subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return observable -> observable.subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
//                    .takeUntil(ui.getUIChangeSubject().filter(uievent -> //保证在相关的生命周期发生的时候，能够及时的取消订阅
//                            uievent == UIChangeEvent.ONDESTROY
//                                    || uievent == UIChangeEvent.ONDETTACH
//                                    || uievent == UIChangeEvent.ONDESTROYVIEW
//                    ).take(1))
                    .filter(data -> ui.isAlive());  //有可能用户界面已经finish掉了，但是还没有调用相关的回调
        }
    }


}

