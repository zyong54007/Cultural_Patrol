package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.TreasuresBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.util.List;

@AutoValue
public abstract class GetMapDataResponse  extends BaseResponse {

    public static TypeAdapter<GetMapDataResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetMapDataResponse.GsonTypeAdapter(gson);
    }

    public abstract List<TreasuresBean> getList();

}
