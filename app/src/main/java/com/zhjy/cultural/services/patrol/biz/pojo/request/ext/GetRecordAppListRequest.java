package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;

public class GetRecordAppListRequest {

    private int flag;

    private String recordType;

    private int offset;

    private String title;


    public GetRecordAppListRequest(String recordType, String title,int offset){
        this.recordType = recordType;
        this.title = title;
        this.offset = offset;
    }

    public GetRecordAppListRequest(String recordType, int flag,String title, int offset){
        this.recordType = recordType;
        this.flag = flag;
        this.title = title;
        this.offset = offset;
    }

    @Override
    public String toString(){
        super.toString();
        String result = "?";
        if(AppContext.getJsessionid() != null){
            result = String.format(";jsessionid=%s?",AppContext.getJsessionid());
        }
        if(recordType != null)
            result += String.format("recordType=%s&",recordType);
        if(title != null){
            result += String.format("title=%s&",title);
        }
        result += String.format("flag=%d&",flag);
        result += String.format("pager.offset=%d*10",offset);
        return result;
    }


}
