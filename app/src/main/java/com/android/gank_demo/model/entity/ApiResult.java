package com.android.gank_demo.model.entity;


import com.android.gank_demo.model.annotation.ResultCode;
import com.google.gson.annotations.SerializedName;

/**
 * 通用接口返回数据包装类
 */
public class ApiResult<T> {

    /**
     * 请求状态码
     */
    private boolean error;

    /**
     * 返回值说明
     */
    private String errMsg;

    /**
     * 返回数据
     */
    @SerializedName("results")
    private T data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
