package com.qyh.myblog_android.model.bean;

/**
 * 博客详情
 * Created by qyh on 2018/3/29.
 */

public class BlogDetailBean {

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "BlogDetailBean{" +
                "detail='" + detail + '\'' +
                '}';
    }
}
