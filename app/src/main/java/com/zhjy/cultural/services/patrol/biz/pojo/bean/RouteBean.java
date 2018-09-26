package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jialg on 2018/1/9.
 */
@Entity(indices = {@Index("id"), @Index("userId"), @Index("dateTime"), @Index("status")},
        primaryKeys = "id")
public class RouteBean {

    @SerializedName("id")
    @NonNull
    public final String id;

    //经度
    @SerializedName("longitude")
    @NonNull
    public final String longitude;

    //纬度
    @SerializedName("latitude")
    @NonNull
    public final String latitude;

    @SerializedName("userId")
    @NonNull
    public final String userId;

    @SerializedName("userName")
    @NonNull
    public final String userName;


    @SerializedName("dateTime")
    @NonNull
    public final String dateTime;

    //0为未上传，1为已上传
    @SerializedName("status")
    @NonNull
    public final String status;

    public RouteBean(String id,String longitude,String latitude,String userId,String userName,String dateTime,String status){
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userId = userId;
        this.userName = userName;
        this.dateTime = dateTime;
        this.status = status;
    }

}
