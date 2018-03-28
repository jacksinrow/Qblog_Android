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

import java.util.List;

/**
 * 类  名： BlogTypeBean
 * 描  述：博客类型实体类
 * 创建人： qyh
 * 日  期： 2018年03月20日 15:03
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class BlogTypeBean {

    /**
     * status_code : 1
     * msg : 请求成功
     * data : [{"type_name":"全部","type_id":0},{"type_name":"设计模式","type_id":1},{"type_name":"Android基础","type_id":2},{"type_name":"Java基础","type_id":3},{"type_name":"源码原理","type_id":4},{"type_name":"View相关","type_id":5}]
     */

    private String status_code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type_name : 全部
         * type_id : 0
         */

        private String type_name;
        private int type_id;

        public String getTypName() {
            return type_name;
        }

        public void setTypeName(String type_name) {
            this.type_name = type_name;
        }

        public int getTypeId() {
            return type_id;
        }

        public void setTypeId(int type_id) {
            this.type_id = type_id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "type_name='" + type_name + '\'' +
                    ", type_id=" + type_id +
                    '}';
        }
    }
}