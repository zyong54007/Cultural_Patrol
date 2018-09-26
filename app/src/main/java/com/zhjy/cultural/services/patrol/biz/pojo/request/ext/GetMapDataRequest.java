package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

public class GetMapDataRequest extends BaseRequest {

    public String title;

    public GetMapDataRequest(){
    }

    public GetMapDataRequest(String title){
        this.title = title;
    }

    @Override
    public String toString(){
        super.toString();
        String result = "";
        if(null !=title){
            result = String.format("?title=%s",title);
        }
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }

}
