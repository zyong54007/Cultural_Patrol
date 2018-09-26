package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.UserBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.io.Serializable;

/**
 * Created by jialg on 2018/4/3.
 */

@AutoValue
public abstract class GetLoginResponse extends BaseResponse {

    public static TypeAdapter<GetLoginResponse> typeAdapter(Gson gson) {

        return new AutoValue_GetLoginResponse.GsonTypeAdapter(gson);

    }

    @Nullable
    @SerializedName("userInfo")
    public abstract UserBean getuserbean();


    @SerializedName("status")
    public abstract String getStatus();

    @Nullable
    @SerializedName("jsessionid")
    public abstract String getJsessionid();

    @Nullable
    @SerializedName("msg")
    public abstract String getMsg();


}
