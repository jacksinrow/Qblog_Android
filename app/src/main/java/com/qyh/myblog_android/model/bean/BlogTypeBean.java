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