package com.zhjy.cultural.services.patrol.network.response;

import java.io.Serializable;

public class StatusResponse<T> implements IResponse, Serializable {


    private String jsessionid;
    private String status;
    private T data;

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public boolean isSuccess() {
        return true;
    }


//    private int status;
//
//    private String msg;
//
//    private T data;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public T getData() {
//
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    @Override
//    public boolean isSuccess() {
//        return status == 0;
//    }


}
