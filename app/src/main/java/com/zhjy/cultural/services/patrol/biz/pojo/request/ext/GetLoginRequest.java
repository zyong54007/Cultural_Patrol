package com.zhjy.cultural.services.patrol.biz.pojo.request.ext;

import com.zhjy.cultural.services.patrol.biz.pojo.request.BaseRequest;

/**
 * Created by jialg on 2018/4/3.
 */

public class GetLoginRequest extends BaseRequest {

    private String username;

    private String pwd;

    public GetLoginRequest(String username,String pwd){
        this.username = username;
        this.pwd = pwd;
    }

    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sbu.append((int)chars[i] + 20).append(",");
        }
        return sbu.toString();
    }

    @Override
    public String toString(){
        super.toString();
        return String.format("?username=%s&pwd=%s",username,stringToAscii(pwd));
    }
}
