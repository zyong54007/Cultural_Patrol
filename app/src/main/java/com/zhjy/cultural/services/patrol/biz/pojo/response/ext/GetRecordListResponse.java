package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.util.List;

/**
 * Created by jialg on 2018/4/3.
 */
@AutoValue
public abstract class GetRecordListResponse extends BaseResponse {

    public static TypeAdapter<GetRecordListResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetRecordListResponse.GsonTypeAdapter(gson);
    }

    @SerializedName("total")
    public abstract int getTotal();

    @SerializedName("pageSize")
    public abstract int getPageSize();

    @Nullable
    @SerializedName("datas")
    public abstract List<RecordBean> getList();

}
