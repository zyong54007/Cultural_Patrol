package com.zhjy.cultural.services.patrol.biz.pojo.response;

/**
 * BaseResponse <br/>
 */
public class BaseResponse implements IResponse {

    public Boolean failure = false;


    public void setFailure(Boolean failure){
        this.failure = failure;
    }

    public boolean getFailure(){
        return failure;
    }

}