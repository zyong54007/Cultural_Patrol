package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

@AutoValue
public abstract class PostRecordSaveResponse  extends BaseResponse {

    public static TypeAdapter<PostRecordSaveResponse> typeAdapter(Gson gson){

        return new AutoValue_PostRecordSaveResponse.GsonTypeAdapter(gson);

    }

    @Nullable
    @SerializedName("status")
    public abstract String getStatus();

    @Nullable
    @SerializedName("msg")
    public abstract String getMsg();

}
