package com.zhjy.cultural.services.patrol.core.net;

/**
 * AppException <br/>
 */
public class AppException extends Throwable {

    private final Throwable detailThrowable;

    public AppException(Throwable throwable) {
        super(throwable);
        this.detailThrowable = throwable;
    }

    public Throwable getDetailThrowable() {
        return detailThrowable;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "detailThrowable=" + detailThrowable +
                '}';
    }
}