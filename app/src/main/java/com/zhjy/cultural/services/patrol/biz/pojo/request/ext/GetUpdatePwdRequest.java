package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetUpdatePwdRequest extends BaseRequest {

    private String oldPwd ;

    private String newPwd ;

    public GetUpdatePwdRequest(String oldPwd,String newPwd){
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
    }

    @Override
    public String toString(){
        super.toString();
        return String.format("?oldPwd=%s&newPwd=%s",oldPwd,newPwd);
    }
}
