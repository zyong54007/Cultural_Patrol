package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jialg on 2018/1/12.
 */
@AutoValue
public abstract class CultureListBean implements Serializable {
    /**
     * contentid : 5161
     * title : 苏家坨镇前沙涧地区电影放映活动
     * thumb : uploadfile/2017/1113/20171113025414222.jpg
     * url : 2017/1113/5161.html
     * type : 7
     * address : 苏家坨镇麓景嘉园北里15号楼
     * date : 2017-11-19
     * end_date : 2017-11-19
     * integral : 0
     * issign : 2
     * cost : 免费
     * ftitle : 苏家坨镇前沙涧地区文化活动中心
     * totalvotes : 90
     * votes : 91
     */

    public static TypeAdapter<CultureListBean> typeAdapter(Gson gson) {
        return new AutoValue_CultureListBean.GsonTypeAdapter(gson);
    }

    @SerializedName("contentid")
    public abstract String getContentid();

    @Nullable
    @SerializedName("title")
    public abstract String getTitle();

    @Nullable
    @SerializedName("thumb")
    public abstract String getThumb();

    public String getImageThumb(){
        return  "http://www.hdggwh.com/" + getThumb();
    }

    @Nullable
    @SerializedName("url")
    public abstract String getUrl();

    @Nullable
    @SerializedName("type")
    public abstract String getType();

    @Nullable
    @SerializedName("address")
    public abstract String getAddress();

    @Nullable
    @SerializedName("date")
    public abstract String getDate();

    @Nullable
    @SerializedName("end_date")
    public abstract String getEnd_date() ;

    @Nullable
    @SerializedName("integral")
    public abstract String getIntegral();

    @Nullable
    @SerializedName("issign")
    public abstract String getIssign();

    @Nullable
    @SerializedName("cost")
    public abstract String getCost();

    @Nullable
    @SerializedName("ftitle")
    public abstract String getFtitle() ;

    @Nullable
    @SerializedName("totalvotes")
    public abstract String getTotalvotes();

    @Nullable
    @SerializedName("votes")
    public abstract String getVotes();

}

