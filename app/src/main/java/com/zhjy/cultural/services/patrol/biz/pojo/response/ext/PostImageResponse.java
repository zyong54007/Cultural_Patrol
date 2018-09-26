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
public abstract class PostImageResponse extends BaseResponse {

    public static TypeAdapter<PostImageResponse> typeAdapter(Gson gson){

        return new AutoValue_PostImageResponse.GsonTypeAdapter(gson);

    }

    @SerializedName("id")
    public abstract int getId();

    @Nullable
    @SerializedName("imgPath")
    public abstract String getImgPath();

    @SerializedName("relationId")
    public abstract int getRelationId();

    @SerializedName("type")
    public abstract int getType();

}
