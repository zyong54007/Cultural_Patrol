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
public abstract class UserBean implements Serializable {

    public static TypeAdapter<UserBean> typeAdapter(Gson gson) {
        return new AutoValue_UserBean.GsonTypeAdapter(gson);
    }
    /**
     * id : 1
     * username : admin
     * pwd : e10adc3949ba59abbe56e057f20f883e
     * truename : 管理员
     * mobile :
     * status : 1
     * organization : {"id":1,"pId":0,"name":"文化单位","path":"/1/","iconOpen":"../ztree/open_tree.png","iconClose":"../ztree/close_tree.png","isParent":true}
     * type : 99
     * deleted : false
     * createTime : 2018-03-09 19:08:40
     * updateTime : null
     */

    @SerializedName("id")
    public abstract int getId() ;

    @Nullable
    @SerializedName("username")
    public abstract String getUsername();

    @Nullable
    @SerializedName("pwd")
    public abstract String getPwd();

    @Nullable
    @SerializedName("truename")
    public abstract String getTruename();

    @Nullable
    @SerializedName("mobile")
    public abstract String getMobile() ;

    @SerializedName("status")
    public abstract int getStatus() ;


    @Nullable
    @SerializedName("organization")
    public abstract OrganizationBean getOrganization() ;

    @SerializedName("type")
    public abstract int getType() ;

    @SerializedName("deleted")
    public abstract Boolean isDeleted();

    @Nullable
    @SerializedName("createTime")
    public abstract String getCreateTime();

    @Nullable
    @SerializedName("updateTime")
    public abstract String getUpdateTime() ;


}
