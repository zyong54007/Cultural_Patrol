package com.zhjy.cultural.services.patrol.biz.pojo.response.ext;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.ArchiveBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.HandleBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.OpinionBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.response.BaseResponse;

import java.util.List;

@AutoValue
public abstract class GetRecordErrorInfoResponse extends BaseResponse {

    /**
     * archives
     * handles :
     * opinion :
     * record :
     */

    public static TypeAdapter<GetRecordErrorInfoResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetRecordErrorInfoResponse.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("archives")
    public abstract List<ArchiveBean> getArchives() ;

    @Nullable
    @SerializedName("handles")
    public abstract List<HandleBean> getHandles() ;

    @Nullable
    @SerializedName("opinion")
    public abstract OpinionBean getOpinion() ;

    @SerializedName("record")
    public abstract RecordBean getRecordInfo() ;

}
