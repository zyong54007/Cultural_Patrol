package com.zhjy.cultural.services.patrol.network.exception;

/**
 * Created by wpy on 2017/7/23.
 */

public class APIStatusErrorException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public APIStatusErrorException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
