package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@AutoValue
public abstract class UserInfo implements Serializable {
    public static TypeAdapter<UserInfo> typeAdapter(Gson gson) {

        return new AutoValue_UserInfo.GsonTypeAdapter(gson);

    }


    @SerializedName("id")
    public abstract int getid();

    @SerializedName("username")
    public abstract String getUsername();

}
