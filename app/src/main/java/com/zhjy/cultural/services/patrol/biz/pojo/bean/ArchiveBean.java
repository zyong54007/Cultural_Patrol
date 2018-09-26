package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@AutoValue
public abstract class ArchiveBean implements Serializable {

    public static TypeAdapter<ArchiveBean> typeAdapter(Gson gson) {
        return new AutoValue_ArchiveBean.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract int getId();

    @SerializedName("recordId")
    public abstract int getRecordId();

    @SerializedName("archiveStatus")
    public abstract int getArchiveStatus();

    @SerializedName("archiveDescription")
    public abstract String getArchiveDescription();

    @SerializedName("createTime")
    public abstract String getCreateTime();

    @SerializedName("user")
    public abstract UserBean getUser();


}
