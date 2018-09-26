package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

/**
 * Created by jialg on 2018/4/3.
 */

@AutoValue
public abstract class GetFeedBackResponse extends BaseResponse {

    public static TypeAdapter<GetFeedBackResponse> typeAdapter(Gson gson){

        return new AutoValue_GetFeedBackResponse.GsonTypeAdapter(gson);

    }

    @SerializedName("status")
    public abstract String getStatus();

    @Nullable
    @SerializedName("msg")
    public abstract String getMsg();

}
