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

import android.app.Activity;


import com.qyh.myblog_android.MainActivity;
import com.qyh.myblog_android.di.module.ActivityModule;
import com.qyh.myblog_android.di.scope.ActivityScope;
import com.qyh.myblog_android.ui.activity.blog.BlogDetailActivity;
import com.qyh.myblog_android.ui.activity.mine.CreateBlogActivity;
import com.qyh.myblog_android.ui.activity.mine.LoginActivity;
import com.qyh.myblog_android.ui.activity.mine.MyBlogActivity;
import com.qyh.myblog_android.ui.activity.mine.RegistActivity;

import dagger.Component;

/**
 * 接口名： ActivityComponent
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:41
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(RegistActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(MyBlogActivity myBlogActivity);

    void inject(CreateBlogActivity createBlogActivity);

    void inject(BlogDetailActivity blogDetailActivity);

}