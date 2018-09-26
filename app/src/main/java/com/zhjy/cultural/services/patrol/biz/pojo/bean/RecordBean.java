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
public abstract class RecordBean implements Serializable{

    public static TypeAdapter<RecordBean> typeAdapter(Gson gson) {
        return new AutoValue_RecordBean.GsonTypeAdapter(gson);
    }

    /**
     * id : 17
     * removal : false
     * destroy : false
     * fireComplete : true
     * insideExplain : 文物完整
     * manage : false
     * construction : false
     * outsideExplain : 文物安全
     * ww : {"id":2,"title":"慈寿寺塔","wwType":1,"wwTypeStr":"国家级","address":"海淀区蓝靛厂南路","fxType":0,"fxTypeStr":"","opentime":"","content":"<p>　　慈寿寺塔，属国家级文保单位，位于海淀区八里庄，塔建于明万历四年(1576)，须弥座四壁雕塑金刚像，做工精细，为明代单层多檐式塔的极重要的范例。<\/p><p><br/><\/p>","wwStatus":2,"wwStatusStr":"保存完好","point":"116.301671,39.934309","picturePath":"uploadfile/2017/0702/20170702051345954.png","managementUnit":null,"deleted":false,"createTime":null,"updateTime":null}
     * user : {"id":1,"username":"admin","pwd":"e10adc3949ba59abbe56e057f20f883e","truename":"管理员","mobile":"","status":1,"organization":{"id":1,"pId":0,"name":"文化单位","path":"/1/","iconOpen":"../ztree/open_tree.png","iconClose":"../ztree/close_tree.png","isParent":true},"type":99,"deleted":false,"createTime":"2018-03-09 19:08:40","updateTime":null}
     * status : 1
     * statusDescription :
     * flag : 2
     * flow : 0
     * flowPath :
     * writeTime : 2018-03-27 18:52
     * createTime : null
     * updateTime : null
     * pointNum : 0
     */


    @SerializedName("id")
    public abstract int getId() ;

    @SerializedName("removal")
    public abstract Boolean isRemoval();

    @SerializedName("destroy")
    public abstract Boolean isDestroy() ;

    @SerializedName("fireComplete")
    public abstract Boolean isFireComplete() ;

    @Nullable
    @SerializedName("insideExplain")
    public abstract String getInsideExplain();

    @SerializedName("manage")
    public abstract Boolean isManage() ;

    @SerializedName("construction")
    public abstract Boolean isConstruction();

    @Nullable
    @SerializedName("outsideExplain")
    public abstract String getOutsideExplain();

    @Nullable
    @SerializedName("ww")
    public abstract TreasuresBean getWw();

    @Nullable
    @SerializedName("user")
    public abstract UserBean getUser() ;

    @SerializedName("status")
    public abstract int getStatus();

    @Nullable
    @SerializedName("statusDescription")
    public abstract String getStatusDescription() ;

    @SerializedName("flag")
    public abstract int getFlag();

    @SerializedName("flow")
    public abstract int getFlow() ;

    @Nullable
    @SerializedName("flowPath")
    public abstract String getFlowPath() ;

    @Nullable
    @SerializedName("writeTime")
    public abstract String getWriteTime();

    @Nullable
    @SerializedName("createTime")
    public abstract String getCreateTime();

    @Nullable
    @SerializedName("updateTime")
    public abstract String getUpdateTime();

    @SerializedName("pointNum")
    public abstract int getPointNum() ;

    @Nullable
    @SerializedName("daysBetween")
    public abstract String getDaysBetween();
}
