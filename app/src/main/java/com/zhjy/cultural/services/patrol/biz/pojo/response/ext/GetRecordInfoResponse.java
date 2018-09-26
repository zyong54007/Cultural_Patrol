package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

@AutoValue
public abstract class GetRecordInfoResponse extends BaseResponse {

    /**
     * id : 21
     * removal : false
     * destroy : false
     * fireComplete : true
     * insideExplain : 文物不完整
     * manage : false
     * construction : false
     * outsideExplain : 文物不安全
     * wwId : 1
     * userId : 2
     * status : 2
     * statusDescription :
     * flag : 2
     * flow : 3
     * flowPath : /1/2/3/
     * writeTime : 2018-03-28 16:44
     * createTime : null
     * updateTime : null
     * selected : false
     */

    public static TypeAdapter<GetRecordInfoResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetRecordInfoResponse.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract int getId() ;

    @SerializedName("removal")
    public abstract boolean isRemoval() ;

    @SerializedName("destroy")
    public abstract boolean isDestroy() ;

    @SerializedName("fireComplete")
    public abstract boolean isFireComplete() ;

    @Nullable
    @SerializedName("insideExplain")
    public abstract String getInsideExplain() ;

    @SerializedName("manage")
    public abstract boolean isManage() ;

    @SerializedName("construction")
    public abstract boolean isConstruction() ;

    @Nullable
    @SerializedName("outsideExplain")
    public abstract String getOutsideExplain() ;


    @SerializedName("wwId")
    public abstract int getWwId() ;

    @SerializedName("userId")
    public abstract int getUserId() ;

    @SerializedName("status")
    public abstract int getStatus() ;

    @Nullable
    @SerializedName("statusDescription")
    public abstract String getStatusDescription() ;

    @SerializedName("flag")
    public abstract int getFlag() ;

    @SerializedName("flow")
    public abstract int getFlow() ;

    @Nullable
    @SerializedName("flowPath")
    public abstract String getFlowPath() ;

    @Nullable
    @SerializedName("writeTime")
    public abstract String getWriteTime();


   /* @SerializedName("createTime")
    public abstract String getCreateTime();


    @SerializedName("updateTime")
    public abstract String getUpdateTime() ;*/

    @SerializedName("selected")
    public abstract boolean isSelected() ;


}
