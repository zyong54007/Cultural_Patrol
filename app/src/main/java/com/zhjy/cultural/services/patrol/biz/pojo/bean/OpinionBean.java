package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@AutoValue
public abstract class OpinionBean  implements Serializable {

    public static TypeAdapter<OpinionBean> typeAdapter(Gson gson) {
        return new AutoValue_OpinionBean.GsonTypeAdapter(gson);
    }

    @SerializedName("recordId")
    public abstract int getRecordId();

    @SerializedName("opinionStatus")
    public abstract int getOpinionStatus();

    @SerializedName("opinionDescription")
    public abstract String getOpinionDescription();

    @SerializedName("createTime")
    public abstract String getCreateTime();

    @Nullable
    @SerializedName("user")
    public abstract UserBean getUser() ;


}
