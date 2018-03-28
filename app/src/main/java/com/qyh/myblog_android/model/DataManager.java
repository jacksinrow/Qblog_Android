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

package com.qyh.myblog_android.model;


import com.qyh.myblog_android.model.bean.BaseBean;
import com.qyh.myblog_android.model.bean.BlogContentBean;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.model.bean.UserInfoBean;
import com.qyh.myblog_android.model.db.DBHelper;
import com.qyh.myblog_android.model.helper.HttpHelper;
import com.qyh.myblog_android.model.helper.PreferencesHelper;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * 类  名： DataManager
 * 描  述：所有跟数据相关操作的管理类 （门面模式）
 * 创建人： qyh
 * 日  期：2018年03月17日 22:58
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class DataManager implements HttpHelper, PreferencesHelper, DBHelper {

    private HttpHelper mHttpHelper;
    private PreferencesHelper mPreferencesHelper;
    private DBHelper mDbHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper, DBHelper dbHelper) {
        this.mHttpHelper = httpHelper;
        this.mPreferencesHelper = preferencesHelper;
        this.mDbHelper = dbHelper;
    }

    @Override
    public Flowable<BaseBean> regist(String phone, String password, String userName) {
        return mHttpHelper.regist(phone, password, userName);
    }

    @Override
    public Flowable<UserInfoBean> login(String account, String password) {
        return mHttpHelper.login(account, password);
    }

    @Override
    public Flowable<UserInfoBean> getUserInfo(String userId) {
        return mHttpHelper.getUserInfo(userId);
    }

    @Override
    public Flowable<BlogTypeBean> getBlogTypeList() {
        return mHttpHelper.getBlogTypeList();
    }

    @Override
    public Flowable<BlogContentBean> getBlogList(int type, int page, int pageSize) {
        return mHttpHelper.getBlogList(type, page, pageSize);
    }

    @Override
    public Flowable<BlogContentBean> getBlogListByid(String userId) {
        return mHttpHelper.getBlogListByid(userId);
    }

    @Override
    public Flowable<BaseBean> addBlog(Map map) {
        return mHttpHelper.addBlog(map);
    }
}