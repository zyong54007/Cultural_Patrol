package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

public class GetTotalMessageRequest extends BaseRequest {

    public void GetTotalMessageRequest(){};

    @Override
    public String toString(){
        super.toString();
        String result = "";
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }

}
