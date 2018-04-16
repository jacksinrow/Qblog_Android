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

package com.qyh.myblog_android.presenter.mine;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.App;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.mine.LoginContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.UserInfoBean;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * 类  名： LoginPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月21日 22:05
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }



    @Override
    public void login(String phone, String password) {
        addSubscribe(mDataManager.login(phone, password)
                .compose(RxUtil.<UserInfoBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
                    @Override
                    public void onNext(UserInfoBean userLoginBean) {
                        Logger.e("login==="+userLoginBean.toString());
                        if (userLoginBean.getStatus_code().equals(Constants.SUCCESS_CODE)) {
                            if (userLoginBean.getData() != null) {
                                mView.loginSuccess(userLoginBean);
                            } else {
                                mView.loginError(userLoginBean.getMsg());
                            }
                        } else {
                            mView.loginError(userLoginBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.loginError(App.getInstance().getString(R.string.neterrormsg));
                    }
                })
        );
    }
}