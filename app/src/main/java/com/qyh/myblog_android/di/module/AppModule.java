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

package com.qyh.myblog_android.di.module;



import com.qyh.myblog_android.app.App;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.db.DBHelper;
import com.qyh.myblog_android.model.db.GreenDaoHelper;
import com.qyh.myblog_android.model.helper.HttpHelper;
import com.qyh.myblog_android.model.helper.ImplPreferencesHelper;
import com.qyh.myblog_android.model.helper.PreferencesHelper;
import com.qyh.myblog_android.model.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 类  名： AppModule
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:42
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */

@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(GreenDaoHelper realmHelper){
        return realmHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper , DBHelper dbHelper) {
        return new DataManager(httpHelper, preferencesHelper,dbHelper);
    }
}