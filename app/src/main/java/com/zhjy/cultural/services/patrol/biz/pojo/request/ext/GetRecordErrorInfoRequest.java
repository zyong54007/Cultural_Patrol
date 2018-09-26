package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

public class GetRecordErrorInfoRequest extends BaseRequest {

    private int id;

    public GetRecordErrorInfoRequest(int id){
        this.id = id;
    };

    @Override
    public String toString(){
        String result = String.format("?id=%d",id);
        if(AppContext.getJsessionid() != null){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }

}
