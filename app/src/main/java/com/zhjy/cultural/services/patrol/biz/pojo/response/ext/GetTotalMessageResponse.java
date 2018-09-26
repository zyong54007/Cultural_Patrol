package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

@AutoValue
public abstract class GetTotalMessageResponse extends BaseResponse {

    public static TypeAdapter<GetTotalMessageResponse> typeAdapter(Gson gson){

        return new AutoValue_GetTotalMessageResponse.GsonTypeAdapter(gson);

    }

    @SerializedName("totalMessage")
    public abstract int getTotalMessage();

}
