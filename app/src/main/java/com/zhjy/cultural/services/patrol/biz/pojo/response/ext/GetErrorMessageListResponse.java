package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.util.List;

@AutoValue
public abstract class GetErrorMessageListResponse extends BaseResponse {

    public static TypeAdapter<GetErrorMessageListResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetErrorMessageListResponse.GsonTypeAdapter(gson);
    }

    @SerializedName("total")
    public abstract int getTotal();

    @SerializedName("pageSize")
    public abstract int getPageSize();

    @Nullable
    @SerializedName("datas")
    public abstract List<RecordBean> getList();

}
