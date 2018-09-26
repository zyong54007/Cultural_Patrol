package com.zhjy.cultural.services.patrol.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadRequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Connection", "close")
                .header("Authorization", "1212")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }
}
