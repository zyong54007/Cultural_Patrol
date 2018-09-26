package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jialg on 2018/4/3.
 */
@AutoValue
public abstract class OrganizationBean implements Serializable{

    public static TypeAdapter<OrganizationBean> typeAdapter(Gson gson) {
        return new AutoValue_OrganizationBean.GsonTypeAdapter(gson);
    }
    /**
     * id : 1
     * pId : 0
     * name : 文化单位
     * path : /1/
     * iconOpen : ../ztree/open_tree.png
     * iconClose : ../ztree/close_tree.png
     * isParent : true
     */

    @SerializedName("id")
    public  abstract int getId() ;

    @SerializedName("pId")
    public abstract int getPId() ;

    @SerializedName("name")
    public abstract String getName();

    @Nullable
    @SerializedName("path")
    public abstract String getPath();

    @Nullable
    @SerializedName("iconOpen")
    public abstract String getIconOpen() ;

    @Nullable
    @SerializedName("iconClose")
    public abstract String getIconClose();

    @SerializedName("isParent")
    public abstract Boolean isIsParent() ;

}
