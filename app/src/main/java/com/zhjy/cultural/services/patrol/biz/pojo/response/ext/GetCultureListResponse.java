package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureListBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;
import java.util.List;


/**
 * Created by jialg on 2018/1/12.
 */
@AutoValue
public abstract class GetCultureListResponse extends BaseResponse {

    public static TypeAdapter<GetCultureListResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetCultureListResponse.GsonTypeAdapter(gson);
    }

    @SerializedName("pagecount")
    public abstract int  getPagecount();

    @Nullable
    @SerializedName("html")
    public abstract List<CultureListBean> getHtml();
}
