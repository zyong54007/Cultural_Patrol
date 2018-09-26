package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

public class GetErrorMessageListRequest extends BaseRequest {

    private int page;

    public void GetErrorMessageListRequest(int page){
        this.page = page;
    }

    @Override
    public String toString(){
        super.toString();
        String result = String.format("pager.offset=%d*10",page);
        if(null != AppContext.getJsessionid() ){
            result = String.format(";jsessionid=%s%s",AppContext.getJsessionid(),result);
        }
        return result;
    }

}
