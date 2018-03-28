/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.qyh.myblog_android.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类  名： BlogContentBean
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月20日 14:04
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class BlogContentBean implements Serializable{

    /**
     * status_code : 1
     * msg : 请求成功
     * data : [{"id":7,"type":2,"createTime":"2018-03-22 04:14:08 下午","title":"的点点滴滴多多顶顶顶顶 ","content":"的点点滴滴多多顶顶顶顶多多多多顶顶顶顶","userBean":{"userId":"91d43c10-edbc-4427-a431-6c5852ad9967","phone":null,"userName":null},"userId":"91d43c10-edbc-4427-a431-6c5852ad9967"},{"id":6,"type":3,"createTime":"2018-03-22 04:14:04 下午","title":"测试数据","content":"呵呵呵呵呵呵呵呵呵呵呵呵呵呵哈哈哈哈哈","userBean":{"userId":"91d43c10-edbc-4427-a431-6c5852ad9967","phone":null,"userName":null},"userId":"91d43c10-edbc-4427-a431-6c5852ad9967"},{"id":5,"type":1,"createTime":"2018-03-22 04:14:03 下午","title":"哈哈哈哈","content":"加快递费了咖啡机阿里发了房间啊","userBean":{"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee","phone":null,"userName":null},"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee"},{"id":4,"type":2,"createTime":"2018-03-22 04:13:53 下午","title":"傻笑啥","content":"发打发打发","userBean":{"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee","phone":null,"userName":null},"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee"},{"id":3,"type":1,"createTime":"2018-03-22 04:13:51 下午","title":"黑乎乎黑乎乎","content":"对方的发奥奥奥","userBean":{"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee","phone":null,"userName":null},"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee"},{"id":2,"type":1,"createTime":"2018-03-22 04:13:48 下午","title":"呵呵呵呵呵呵呵","content":"人头狗若拖若若若若若若若若若若若若若若若若若若若若若若若若若","userBean":{"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee","phone":null,"userName":null},"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee"},{"id":1,"type":0,"createTime":"2018-03-22 04:13:46 下午","title":"哈哈哈哈哈","content":"记得发发发浪费大家了的看法啦方法方法方法方法方法方法方法方法方法方法方法方法方法方法方法","userBean":{"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee","phone":null,"userName":null},"userId":"3b5c8e94-86b8-4077-b2fb-2a196f8e2dee"}]
     */

    private String status_code;
    private String msg;
    private List<BlogDataBean> data;

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

    public List<BlogDataBean> getData() {
        return data;
    }

    public void setData(List<BlogDataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BlogContentBean{" +
                "status_code='" + status_code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

//    public static class DataBean implements Serializable {

}