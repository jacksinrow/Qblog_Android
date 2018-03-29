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

package com.qyh.myblog_android.model.api;


import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.model.bean.BlogDetailBean;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.model.bean.UserInfoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口名： MyApis
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年01月16日 17:43
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public interface MyApis {

//    String HOST = "http://192.168.1.188:8081/";
    String HOST = "http://101.132.165.105:8082/";

    // 用户注册
    @GET("/user/regist")
    Flowable<MyHttpResponse> regist(@Query("phone") String phone, @Query("password") String password,
                                    @Query("userName") String userName);

    // 用户登录
    @GET("/user/login")
    Flowable<UserInfoBean> login(@Query("phone") String phone, @Query("password") String password);

    // 获取用户信息
    @GET("/user/getuserinfo")
    Flowable<UserInfoBean> getUserInfo(@Query("userid") String userId);

    // 获取博客分类
    @GET("/blog/getblogtype")
    Flowable<MyHttpResponse<List<BlogTypeBean>>> getBlogType();

    // 获取博客列表
    @GET("/blog/getbloglist")
    Flowable<MyHttpResponse<List<BlogDataBean>>> getBlogList(@Query("type") int type, @Query("page") int page
            , @Query("pageSize") int pageSize);

    // 通过用户id 获取博客
    @GET("/blog/getblogsbyid")
    Flowable<MyHttpResponse<List<BlogDataBean>>> getBlogListByUserid(@Query("userId") String userId);

    // 上传博客
    @FormUrlEncoded
    @POST("/blog/addblog")
    Flowable<MyHttpResponse> addBlog(@FieldMap Map<String, String> map);

    // 通过id获取博客详情
    @GET("/blog/getblogdetail")
    Flowable<MyHttpResponse<BlogDetailBean>> getBlogDetail(@Query("id") int id);
}