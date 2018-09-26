package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;
import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

import okhttp3.FormBody;

public class PostHandleSaveRequest extends BaseRequest {

    private String handleDescription;

    private int recordId;

    private String image_ids;

    public PostHandleSaveRequest(int recordId,String handleDescription,String image_ids){
        this.recordId = recordId;
        this.handleDescription = handleDescription;
        this.image_ids = image_ids;
    }

    public FormBody.Builder BuilderOf(){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("recordId",String.valueOf(recordId));
        builder.add("handleDescription",handleDescription);
        builder.add("image_ids",image_ids);
        return builder;
    }

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
