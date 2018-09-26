package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class GetTotalNullResponse {

    public static TypeAdapter<GetTotalNullResponse> typeAdapter(Gson gson){

        return new AutoValue_GetTotalNullResponse.GsonTypeAdapter(gson);

    }

    @SerializedName("totalNull")
    public abstract int getTotalNull();

}
