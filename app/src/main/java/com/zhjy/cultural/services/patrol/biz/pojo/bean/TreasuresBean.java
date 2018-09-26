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
public abstract class TreasuresBean implements Serializable{
    /**
     * id : 1
     * title : 摩诃庵
     * wwType : 1
     * wwTypeStr : 国家级
     * address : 北京市海淀区八里庄南玲珑巷
     * fxType : 0
     * fxTypeStr :
     * opentime : 全天
     * content : <p style="text-align: justify;">　　摩诃庵，属国家级文保单位，位于海淀区八里庄，明嘉靖二十五年(1546)由太监赵政集资修建。现存有正殿、东西配殿、廊庑及方丈院等建筑，庵墙四隅各设角楼一座，尤为珍贵的是正殿内有明代壁画和重临集篆三十二体金刚经刻石。</p><p><br/></p>
     * wwStatus : 2
     * wwStatusStr : 保存完好
     * point : 116.306914,39.939423
     * picturePath : uploadfile/2017/0702/20170702051608624.png
     * managementUnit : null
     * deleted : false
     * totalRecord : 1
     * totalStatus0 : 0
     * totalStatus1 : 0
     * totalStatus2 : 1
     * totalStatus3 : 0
     * showDetail : false
     */

    public static TypeAdapter<TreasuresBean> typeAdapter(Gson gson) {
        return new AutoValue_TreasuresBean.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract int getId() ;

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("wwType")
    public abstract int getWwType();

    @Nullable
    @SerializedName("wwTypeStr")
    public abstract String getWwTypeStr() ;

    @Nullable
    @SerializedName("address")
    public abstract String getAddress() ;

    @SerializedName("fxType")
    public abstract int getFxType();

    @Nullable
    @SerializedName("fxTypeStr")
    public abstract String getFxTypeStr() ;

    @Nullable
    @SerializedName("opentime")
    public abstract String getOpentime();

    @Nullable
    @SerializedName("content")
    public abstract String getContent() ;

    @SerializedName("wwStatus")
    public abstract int getWwStatus();

    @Nullable
    @SerializedName("wwStatusStr")
    public abstract String getWwStatusStr();

    @Nullable
    @SerializedName("point")
    public abstract String getPoint();

    @Nullable
    @SerializedName("picturePath")
    public abstract String getPicturePath();

   /* @Nullable
    @SerializedName("managementUnit")
    public abstract Object getManagementUnit();
*/
    @SerializedName("deleted")
    public abstract Boolean isDeleted();

    @SerializedName("totalRecord")
    public abstract int getTotalRecord();

    @SerializedName("totalStatus0")
    public abstract int getTotalStatus0();

    @SerializedName("totalStatus1")
    public abstract int getTotalStatus1();

    @SerializedName("totalStatus2")
    public abstract int getTotalStatus2();

    @SerializedName("totalStatus3")
    public abstract int getTotalStatus3();

    /*@Nullable
    @SerializedName("showDetail")
    public abstract Boolean isShowDetail();*/

    @Nullable
    @SerializedName("createTime")
    public abstract String getCreateTime();

    @Nullable
    @SerializedName("updateTime")
    public abstract String getUpdateTime();


}
