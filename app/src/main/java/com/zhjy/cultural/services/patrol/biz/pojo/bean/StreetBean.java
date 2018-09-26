package com.zhjy.cultural.services.patrol.biz.pojo.bean;

import java.io.Serializable;

/**
 * Created by jialg on 2018/1/29.
 */

public class StreetBean implements Serializable {

    public String name;

    public Integer id;

    public StreetBean(String name , Integer id){
        this.name = name;
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

}
