package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetPointListRequest extends BaseRequest {

    private String recordId;

    public GetPointListRequest(String recordId){
        this.recordId = recordId;
    }

    @Override
    public String toString(){
        super.toString();
        String result = String.format("?recordId=%s",recordId);
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }
}
