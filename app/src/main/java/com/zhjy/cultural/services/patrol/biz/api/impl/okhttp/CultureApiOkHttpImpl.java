package com.zhjy.cultural.services.patrol.biz.api.impl.okhttp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.api.CultureApi;
import com.zhjy.cultural.services.patrol.biz.api.impl.OkHttpUtil;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ImageBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetCultureListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetErrorMessageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetFeedBackRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetImageListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetLoginRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetMapDataRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetNullListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetPointSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordAppListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordErrorInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTotalMessageRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTotalNullRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetUpdatePwdRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostHandleSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.PostRecordSaveRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetCultureListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetErrorMessageListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetFeedBackResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetLoginResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetNullListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetPointSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordErrorInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTotalMessageResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTotalNullResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetUpdatePwdResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostHandleSaveResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostImageResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.PostRecordSaveResponse;
import com.zhjy.cultural.services.patrol.core.log.AppLog;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * API  集合
 */
public class CultureApiOkHttpImpl implements CultureApi {


    //搜索

    private OkHttpClient okHttpClient;

    private Gson gson;

    public CultureApiOkHttpImpl(OkHttpClient client) {
        okHttpClient = client;
        gson = InjectHelp.appComponent().gson();
    }

    @Override
    public LiveData<GetCultureListResponse> getCultureList(GetCultureListRequest request) {
        AppLog.d("OkHttpImpl.getCultureList request = " + request);

        String url = "http://www.hdggwh.com/home/Api/Activity/getList/%s";
        url = String.format(url, request.toString());

        MutableLiveData<GetCultureListResponse> data = new MutableLiveData<>();
        call(url, GetCultureListResponse.class, data);

        return data;
    }

    @Override
    public LiveData<GetLoginResponse> getLoginResult(GetLoginRequest request) {
        AppLog.d("OkHttpImpl.getLoginResult request = " + request);

        String url = "%swwgl/loginInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetLoginResponse> data = new MutableLiveData<>();
        call(url, GetLoginResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetUpdatePwdResponse> getUpdatePwdResult(GetUpdatePwdRequest request) {
        AppLog.d("OkHttpImpl.getUpdatePwdResult request = " + request);

        String url = "%swwgl/userInfo/updatePwd%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetUpdatePwdResponse> data = new MutableLiveData<>();
        call(url, GetUpdatePwdResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetTreasuresListResponse> getTreasuresListResult(GetTreasuresListRequest request) {
        AppLog.d("OkHttpImpl.getTreasuresListReqult request = " + request);

        String url = "%swwgl/wwInfo/listInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetTreasuresListResponse> data = new MutableLiveData<>();
        call(url, GetTreasuresListResponse.class, data);

        return data;
    }

    @Override
    public LiveData<GetTreasuresInfoResponse> getTreasuresInfoResult(GetTreasuresInfoRequest request) {
        AppLog.d("OkHttpImpl.getTreasuresInfoResult request = " + request);

        String url = "%swwgl/wwInfo/detailInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetTreasuresInfoResponse> data = new MutableLiveData<>();
        call(url, GetTreasuresInfoResponse.class, data);
        return data;
    }


    @Override
    public LiveData<GetRecordListResponse> getRecordListResult(GetRecordListRequest request) {
        AppLog.d("OkHttpImpl.getRecordListResult request = " + request);

        String url = "%swwgl/record/listInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetRecordListResponse> data = new MutableLiveData<>();
        call(url, GetRecordListResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetRecordInfoResponse> getRecordInfoResult(GetRecordInfoRequest request) {
        AppLog.d("OkHttpImpl.getRecordInfoResult request = " + request);

        String url = "%swwgl/record/detailInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());
        MutableLiveData<GetRecordInfoResponse> data = new MutableLiveData<>();
        call(url, GetRecordInfoResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetRecordErrorInfoResponse> getRecordErrorInfoResult(GetRecordErrorInfoRequest request) {
        AppLog.d("OkHttpImpl.getRecordErrorInfoResult request = " + request);

        String url = "%swwgl/record/detailInterface2%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetRecordErrorInfoResponse> data = new MutableLiveData<>();
        call(url, GetRecordErrorInfoResponse.class, data);
        return data;
    }

    @Override
    public LiveData<PostImageResponse> postImageResult(List<File> files) {
        AppLog.d("OkHttpImpl.postImageResult request = " + files.size());

        String url = "%swwgl/upload/uploadImage";
        url = String.format(url, Constants.BASEURL);

        MutableLiveData<PostImageResponse> data = new MutableLiveData<>();
        callPostImage(url, files, PostImageResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetFeedBackResponse> getFeedBackResult(GetFeedBackRequest request) {
        AppLog.d("OkHttpImpl.getFeedBackResult request = " + request);

        String url = "%swwgl/question/save%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetFeedBackResponse> data = new MutableLiveData<>();
        call(url, GetFeedBackResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetPointSaveResponse> getPointSaveResult(GetPointSaveRequest request) {
        AppLog.d("OkHttpImpl.getPointSaveResult request = " + request);

        String url = "%swwgl/point/save%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetPointSaveResponse> data = new MutableLiveData<>();
        call(url, GetPointSaveResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetPointListResponse> getPointListResult(GetPointListRequest request) {
        AppLog.d("OkHttpImpl.getPointListResult request = " + request);

        String url = "%swwgl/point/listInterface%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetPointListResponse> data = new MutableLiveData<>();
        call(url, GetPointListResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetTotalMessageResponse> getTotalMessageResult(GetTotalMessageRequest request) {
        AppLog.d("OkHttpImpl.getTotalMessageResult request = " + request);

        String url = "%swwgl/record/loadTotalMessage%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetTotalMessageResponse> data = new MutableLiveData<>();
        call(url, GetTotalMessageResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetTotalNullResponse> getTotalNullResult(GetTotalNullRequest request) {
        AppLog.d("OkHttpImpl.getTotalNullResult request = " + request);

        String url = "%swwgl/record/loadTotalNull%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetTotalNullResponse> data = new MutableLiveData<>();
        call(url, GetTotalNullResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetErrorMessageListResponse> getErrorMessageListResult(GetErrorMessageListRequest request) {
        AppLog.d("OkHttpImpl.getErrorMessageListResult request = " + request);

        String url = "%swwgl/record/loadMessage%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetErrorMessageListResponse> data = new MutableLiveData<>();
        call(url, GetErrorMessageListResponse.class, data);
        return data;
    }

    @Override
    public LiveData<GetNullListResponse> getNullListResult(GetNullListRequest request) {
        AppLog.d("OkHttpImpl.getNullListResult request = " + request);

        String url = "%swwgl/record/loadNullList%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetNullListResponse> data = new MutableLiveData<>();
        call(url, GetNullListResponse.class, data);
        return data;
    }

    @Override
    public LiveData<List<TreasuresBean>> getMapDataListResult(GetMapDataRequest request) {
        AppLog.d("OkHttpImpl.getMapDataListResult request = " + request);

        String url = "%swwgl/wwInfo/loadMapData%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<List<TreasuresBean>> data = new MutableLiveData<>();
        Class<List<TreasuresBean>> clazz = (Class) List.class;
        callList(url, TreasuresBean.class, data);
        return data;
    }

    @Override
    public LiveData<GetRecordListResponse> getRecordAppListResult(GetRecordAppListRequest request) {
        AppLog.d("OkHttpImpl.getRecordAppListResult request = " + request);

        String url = "%swwgl/record/loadAppList%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<GetRecordListResponse> data = new MutableLiveData<>();
        call(url, GetRecordListResponse.class, data);
        return data;
    }

    @Override
    public LiveData<PostRecordSaveResponse> postRecordSaveResult(PostRecordSaveRequest request) {
        AppLog.d("OkHttpImpl.postRecordSaveResult request = " + request);

        String url = "%swwgl/record/save%s";
        url = String.format(url, Constants.BASEURL, request.toString());
        FormBody.Builder builder = request.BuilderOf();
        MutableLiveData<PostRecordSaveResponse> data = new MutableLiveData<>();
        call(url, builder, PostRecordSaveResponse.class, data);
        return data;
    }

    @Override
    public LiveData<PostHandleSaveResponse> postHandleSaveResult(PostHandleSaveRequest request) {
        AppLog.d("OkHttpImpl.postHandleSaveResult request = " + request);

        String url = "%swwgl/handle/save%s";
        url = String.format(url, Constants.BASEURL, request.toString());
        FormBody.Builder builder = request.BuilderOf();
        MutableLiveData<PostHandleSaveResponse> data = new MutableLiveData<>();
        call(url, builder, PostHandleSaveResponse.class, data);
        return data;
    }

    @Override
    public LiveData<List<ImageBean>> getImageListResult(GetImageListRequest request) {
        AppLog.d("OkHttpImpl.getImageListResult request = " + request);

        String url = "%swwgl/upload/listImage%s";
        url = String.format(url, Constants.BASEURL, request.toString());

        MutableLiveData<List<ImageBean>> data = new MutableLiveData<>();
        Class<List<ImageBean>> clazz = (Class) List.class;
        callList(url, ImageBean.class, data);
        return data;
    }

    private <T> void callList(final String url, final Class<T> tClass, final MutableLiveData<List<T>> data) {
        AppLog.i("OkHttpImpl.call url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .cacheControl(OkHttpUtil.getCacheControl())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AppLog.i("OkHttpImpl.call url = " + url);
                AppLog.e("OkHttpImpl.call e = " + e, e);
                data.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AppLog.i("OkHttpImpl.call url = " + url);

                AppLog.i("response = " + response);
                if (response != null && response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String result = body.string();
                        if (isGoodJson(result)) {
                            List<T> listResult = jsonToBeanList(result, tClass);
                            data.postValue(listResult);
                        } else {
                            data.postValue(null);
                        }
                    } else {
                        data.postValue(null);
                    }
                }
            }
        });
    }

    public <T> List<T> jsonToBeanList(String json, Class<T> t) {
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonarray) {
            list.add(gson.fromJson(element, t));
        }
        return list;
    }

    private <T> void call(final String url, final Class<T> tClass, final MutableLiveData<T> data) {
        AppLog.i("OkHttpImpl.call url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .cacheControl(OkHttpUtil.getCacheControl())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AppLog.i("OkHttpImpl.call url = " + url);
                AppLog.e("OkHttpImpl.call e = " + e, e);
                data.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AppLog.i("OkHttpImpl.call url   文物列表= " + url);

                AppLog.i("response 文物列表 = " + response);

                if (response != null && response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String result = body.string();
                        if (isGoodJson(result)) {
                            try {
                                data.postValue(gson.fromJson(result, tClass));
                            } catch (JsonSyntaxException e) {
                                ToastUtil.showToast(R.string.server_error);
                            }


                        } else {
                            data.postValue(null);
                        }
                    } else {
                        data.postValue(null);
                    }
                }
            }
        });
    }

    private <T> void call(final String url, final FormBody.Builder builder, final Class<T> tClass, final MutableLiveData<T> data) {
        AppLog.i("OkHttpImpl.call url = " + url);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .cacheControl(OkHttpUtil.getCacheControl())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AppLog.i("OkHttpImpl.call url = " + url);
                AppLog.e("OkHttpImpl.call e = " + e, e);
                if (MainActivity.getInstance() != null) {
                    ToastUtil.showToastMsg("登录已过期 请重新登录");
                    MainActivity.getInstance().finish();
                }
                data.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AppLog.i("OkHttpImpl.call url = " + url);

                AppLog.i("response = " + response);

                if (response != null && response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String result = body.string();
                        if (isGoodJson(result)) {
                            data.postValue(gson.fromJson(result, tClass));
                        } else {
                            data.postValue(null);
                        }
                    } else {
                        if (MainActivity.getInstance() != null) {
                            ToastUtil.showToastMsg("登录已过期 请重新登录");
                            MainActivity.getInstance().finish();
                        }
                        data.postValue(null);
                    }
                }
            }
        });
    }

    private <T> void callPostImage(final String url, List<File> files, final Class<T> tClass, final MutableLiveData<T> data) {
        AppLog.i("OkHttpImpl.callPostImage url = " + url);
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        if (files != null) {
            for (File file : files) {
                multipartBodyBuilder.addFormDataPart("file", file.getName(), RequestBody.create(Constants.MEDIA_TYPE_PNG, file));
            }
        }
        RequestBody requestBody = multipartBodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .cacheControl(OkHttpUtil.getCacheControl())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AppLog.i("OkHttpImpl.callPostImage url = " + url);
                AppLog.e("OkHttpImpl.callPostImage e = " + e, e);
                if (MainActivity.getInstance() != null) {
                    ToastUtil.showToastMsg("登录已过期 请重新登录");
                    MainActivity.getInstance().finish();
                }
                data.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AppLog.i("OkHttpImpl.callPostImage url = " + url);

                AppLog.i("response = " + response);

                if (response != null && response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String result = body.string();
                        if (isGoodJson(result)) {
                            data.postValue(gson.fromJson(result, tClass));
                        } else {
                            Log.i("TAG","错误在这里============"+result);
                            data.postValue(gson.fromJson(Constants.ErrorInfo, tClass));
                        }
                    } else {

                        if (MainActivity.getInstance() != null) {
                            ToastUtil.showToastMsg("登录已过期 请重新登录");
                            MainActivity.getInstance().finish();
                        }
                        data.postValue(null);
                    }
                }
            }
        });
    }

    public static boolean isGoodJson(String json) {
        if ((0 == json.length() || "".equals(json))) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}