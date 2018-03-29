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

package com.qyh.myblog_android.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.github.moduth.blockcanary.BlockCanary;
import com.qyh.myblog_android.app.App;
import com.qyh.myblog_android.util.CrashHandler;
import com.qyh.myblog_android.widget.AppBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

/**
 * 类  名： InitializeService
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:35
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            initAppcation();
        }
    }

    private void initAppcation() {

        try {
            // 初始化异常收集
            CrashHandler.getInstance().init(this);
            //初始化内存泄漏检测
            LeakCanary.install(App.getInstance());

            //初始化过度绘制检测
            BlockCanary.install(getApplicationContext(), new AppBlockCanaryContext()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}