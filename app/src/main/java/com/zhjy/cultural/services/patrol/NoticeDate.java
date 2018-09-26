package com.zhjy.cultural.services.patrol;

import java.io.Serializable;

public class NoticeDate implements Serializable {


    private String jsessionid;
    private String status;
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

}