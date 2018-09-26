package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@AutoValue
public abstract class ImageBean  implements Serializable {

    public static TypeAdapter<ImageBean> typeAdapter(Gson gson) {
        return new AutoValue_ImageBean.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract int getId() ;

    @SerializedName("imgPath")
    public abstract String getImgPath() ;

    @SerializedName("relationId")
    public abstract int getRelationId() ;

    @SerializedName("type")
    public abstract int getType() ;

}
