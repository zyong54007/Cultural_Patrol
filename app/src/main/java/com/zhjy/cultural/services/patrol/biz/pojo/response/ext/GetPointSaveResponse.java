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
public abstract class GetPointSaveResponse extends BaseResponse {

    public static TypeAdapter<GetPointSaveResponse> typeAdapter(Gson gson){

        return new AutoValue_GetPointSaveResponse.GsonTypeAdapter(gson);

    }

    @SerializedName("id")
    public abstract String getId();

    @Nullable
    @SerializedName("point")
    public abstract String getPoint();

    @Nullable
    @SerializedName("createTime")
    public abstract String getCreateTime();

    @SerializedName("recordId")
    public abstract String getRecordId();

}
