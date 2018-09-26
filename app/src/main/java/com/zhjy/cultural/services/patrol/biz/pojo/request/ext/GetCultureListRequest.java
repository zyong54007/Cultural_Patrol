package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;


import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/1/12.
 */

public class GetCultureListRequest extends BaseRequest {

    public int typeid ;

    public int p;

    public GetCultureListRequest(int typeid,int p){
        this.typeid = typeid;
        this.p = p;
    }

    @Override
    public String toString() {
        super.toString();
        return "/typeid/" + typeid + "/p/" + p ;
    }
}
