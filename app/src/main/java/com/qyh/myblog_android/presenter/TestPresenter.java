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

package com.qyh.myblog_android.presenter;

import android.util.Log;

import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.TestContract;
import com.qyh.myblog_android.model.DataManager;

import javax.inject.Inject;

/**
 * 类  名： TestPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年01月16日 17:31
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class TestPresenter extends RxPresenter<TestContract.View> implements TestContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public TestPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public void test() {
        Log.e("qiao", "test: ===");
    }

    public void getYZM(String type, String moblie) {

    }

}