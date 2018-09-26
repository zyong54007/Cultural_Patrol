package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.util.List;

@AutoValue
public abstract class GetImageListResponse<ImageBean> extends BaseResponse {

    public static <ImageBean> TypeAdapter<GetImageListResponse<ImageBean>> typeAdapter(Gson gson,
                                                                                       TypeToken<? extends GetImageListResponse<ImageBean>> typeToken) {
        return new AutoValue_GetImageListResponse.GsonTypeAdapter(gson, typeToken);
    }

    public abstract List<ImageBean> dataList();

}
