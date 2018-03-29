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

package com.qyh.myblog_android.model.helper;

import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.model.bean.BlogDetailBean;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.model.bean.UserInfoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * 接口名： HttpHelper
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 22:40
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public interface HttpHelper {
    // ##################### 用户账号相关 ######################
    Flowable<MyHttpResponse> regist(String phone, String password, String userName);

    Flowable<UserInfoBean> login(String phone, String password);

    Flowable<UserInfoBean> getUserInfo(String userId);

    // ##################### 博客相关 ######################
    Flowable<MyHttpResponse<List<BlogTypeBean>>> getBlogTypeList();

    Flowable<MyHttpResponse<List<BlogDataBean>>> getBlogList(int type, int page, int pageSize);

    Flowable<MyHttpResponse<List<BlogDataBean>>> getBlogListByid(String userId);

    Flowable<MyHttpResponse> addBlog(Map map);

    Flowable<MyHttpResponse<BlogDetailBean>> getBlogDetail(int id);
}