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

package com.qyh.myblog_android.app;

import android.support.multidex.MultiDexApplication;

import com.qyh.myblog_android.service.InitializeService;
import com.qyh.myblog_android.di.component.AppComponent;
import com.qyh.myblog_android.di.component.DaggerAppComponent;
import com.qyh.myblog_android.di.module.AppModule;
import com.qyh.myblog_android.di.module.HttpModule;


/**
 * 类  名： App
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:30
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class App extends MultiDexApplication {

    private static App instance;

    public static AppComponent appComponent;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);


    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}