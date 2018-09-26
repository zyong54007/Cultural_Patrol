package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetImageListRequest extends BaseRequest {

    private int id;

    private int type;

    public GetImageListRequest(int id,int type){
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString(){
        super.toString();
        String result = String.format("?id=%d&type=%d",id,type);
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }
}
