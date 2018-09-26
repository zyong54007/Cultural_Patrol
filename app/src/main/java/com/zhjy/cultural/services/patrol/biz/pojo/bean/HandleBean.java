package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@AutoValue
public abstract class HandleBean implements Serializable {

    public static TypeAdapter<HandleBean> typeAdapter(Gson gson) {
        return new AutoValue_HandleBean.GsonTypeAdapter(gson);
    }

    @SerializedName("recordId")
    public abstract int getRecordId();

    @SerializedName("handleDescription")
    public abstract String getHandleDescription();

    @Nullable
    @SerializedName("handleImages")
    public abstract List<ImageBean> getImageList();

}
