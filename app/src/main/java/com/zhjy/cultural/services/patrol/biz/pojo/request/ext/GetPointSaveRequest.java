package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetPointSaveRequest extends BaseRequest {

    private String point;

    public GetPointSaveRequest(String point){
        this.point = point;
    }

    @Override
    public String toString(){
        super.toString();
        String result = String.format("?point=%s",point);
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }
}
