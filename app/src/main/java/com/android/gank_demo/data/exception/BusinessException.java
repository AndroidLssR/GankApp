package com.android.gank_demo.data.exception;


public class BusinessException extends Exception {

    private boolean errorCode;

    public BusinessException(String message, boolean errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public boolean getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(boolean errorCode) {
        this.errorCode = errorCode;
    }
}
