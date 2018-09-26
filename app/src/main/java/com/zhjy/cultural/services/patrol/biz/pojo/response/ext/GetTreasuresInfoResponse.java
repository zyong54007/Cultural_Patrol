package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

@AutoValue
public abstract class GetTreasuresInfoResponse extends BaseResponse {

    public static TypeAdapter<GetTreasuresInfoResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetTreasuresInfoResponse.GsonTypeAdapter(gson);
    }


    @SerializedName("id")
    public abstract int getId();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("wwType")
    public abstract int getWwType();

    @Nullable
    @SerializedName("wwTypeStr")
    public abstract String getWwTypeStr();

    @Nullable
    @SerializedName("address")
    public abstract String getAddress();

    @SerializedName("fxType")
    public abstract int getFxType();

    @Nullable
    @SerializedName("fxTypeStr")
    public abstract String getFxTypeStr();

    @Nullable
    @SerializedName("opentime")
    public abstract String getOpentime();

    @Nullable
    @SerializedName("content")
    public abstract String getContent();

    @SerializedName("wwStatus")
    public abstract int getWwStatus();

    @Nullable
    @SerializedName("wwStatusStr")
    public abstract String getWwStatusStr();

    @Nullable
    @SerializedName("point")
    public abstract String getPoint();

    @Nullable
    @SerializedName("picturePath")
    public abstract String getPicturePath();

    /* @Nullable
     @SerializedName("managementUnit")
     public abstract Object getManagementUnit();
 */
    @SerializedName("deleted")
    public abstract Boolean isDeleted();

    @SerializedName("totalRecord")
    public abstract int getTotalRecord();

    @SerializedName("totalStatus0")
    public abstract int getTotalStatus0();

    @SerializedName("totalStatus1")
    public abstract int getTotalStatus1();

    @SerializedName("totalStatus2")
    public abstract int getTotalStatus2();

    @SerializedName("totalStatus3")
    public abstract int getTotalStatus3();


//    @SerializedName("showDetail")
//    public abstract Boolean isShowDetail();
//
//    @Nullable
//    @SerializedName("createTime")
//    public abstract String getCreateTime();
//
//    @Nullable
//    @SerializedName("updateTime")
//    public abstract String getUpdateTime();

}
