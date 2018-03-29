package com.qyh.myblog_android.model.bean;

/**
 * Created by qyh on 2018/3/22.
 */

public class MyHttpResponse<T> {

    private String status_code;
    private String msg;
    private T data;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
