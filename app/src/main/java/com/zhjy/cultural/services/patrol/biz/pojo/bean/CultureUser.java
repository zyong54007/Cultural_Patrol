package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jialg on 2017/12/26.
 */

@Entity(indices = {@Index("id"), @Index("username"), @Index("mobile")},
        primaryKeys = "id")
public class CultureUser {
    @SerializedName("id")
    @NonNull
    public final String id;

    @SerializedName("username")
    public final String username;

    @SerializedName("password")
    public final String password;

    @SerializedName("mobile")
    public final String mobile;

    @SerializedName("img")
    public final String img;

    @SerializedName("jsessionid")
    public final String jsessionid;

    public CultureUser(String id, String username, String mobile, String password, String jsessionid, String img){
        this.id =id;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.jsessionid = jsessionid;
        this.img = img;
    }
}
