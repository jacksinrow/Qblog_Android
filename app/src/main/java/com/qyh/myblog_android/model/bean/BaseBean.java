package com.qyh.myblog_android.model.bean;

/**
 * Created by qyh on 2018/3/22.
 */

public class BaseBean {

    /**
     * status_code : 0
     * msg : 该账号已注册
     * data : null
     */

    private String status_code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "status_code='" + status_code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
