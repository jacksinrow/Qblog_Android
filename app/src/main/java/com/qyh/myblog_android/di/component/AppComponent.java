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

package com.qyh.myblog_android.di.component;



import com.qyh.myblog_android.app.App;
import com.qyh.myblog_android.di.module.AppModule;
import com.qyh.myblog_android.di.module.HttpModule;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.db.GreenDaoHelper;
import com.qyh.myblog_android.model.helper.ImplPreferencesHelper;
import com.qyh.myblog_android.model.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 接口名： AppComponent
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:45
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    GreenDaoHelper realmHelper();   //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}