package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.app.AppContext;

public class GetRecordListRequest {

    private int wwId;

    private String recordType;

    private String startDate;

    private String endDate;

    private int offset;


    public GetRecordListRequest(int wwId,int offset){
        this.wwId = wwId;
        this.offset = offset;
    }

    public GetRecordListRequest(int wwId,String recordType,int offset){
        this.wwId = wwId;
        this.recordType = recordType;
        this.offset = offset;
    }

    public GetRecordListRequest(int wwId,String recordType,String startDate,String endDate,int offset){
        this.wwId = wwId;
        this.recordType = recordType;
        this.startDate = startDate;
        this.endDate= endDate;
        this.offset = offset;
    }

    @Override
    public String toString(){
        super.toString();
        String result = "?";
        if(AppContext.getJsessionid() != null){
            result = String.format(";jsessionid=%s?",AppContext.getJsessionid());
        }
        if(wwId != 0)
            result += String.format("wwId=%d&",wwId);
        if(recordType != null)
            result += String.format("recordType=%s&",recordType);
        if(startDate != null)
            result += String.format("startDate=%s&",startDate);
        if(endDate != null)
            result += String.format("endDate=%s&",endDate);
        if(offset >= 0)
            result += String.format("pager.offset=%d*10",offset);
        return result;
    }


}
