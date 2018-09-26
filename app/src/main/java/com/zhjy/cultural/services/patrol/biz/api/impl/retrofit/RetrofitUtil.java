package com.zhjy.cultural.services.patrol.biz.api.impl.retrofit;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {

    public static <T> T createApi(Class<T> tClass, OkHttpClient client, Gson gson) {
        return new Retrofit
                .Builder()
                .client(client)
                .baseUrl("https://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(tClass);
    }
}