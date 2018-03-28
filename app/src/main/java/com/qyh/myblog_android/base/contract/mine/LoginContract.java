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

package com.qyh.myblog_android.base.contract.mine;

import com.qyh.myblog_android.base.BasePresenter;
import com.qyh.myblog_android.base.BaseView;
import com.qyh.myblog_android.model.bean.UserInfoBean;

/**
 * 接口名： LoginContract
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月21日 22:03
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public interface LoginContract {
    interface View extends BaseView {

        void loginSuccess(UserInfoBean userInfoBean);

        void loginError(String msg);

    }

    interface Presenter extends BasePresenter<LoginContract.View> {

        void login(String account, String password);

    }
}