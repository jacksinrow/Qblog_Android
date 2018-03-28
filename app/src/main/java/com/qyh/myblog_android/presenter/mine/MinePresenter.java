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

import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.mine.MineContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.UserInfoBean;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * 类  名： MinePresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月21日 10:16
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MinePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getUserInfo(final String userId) {
        addSubscribe(mDataManager.getUserInfo(userId)
                .compose(RxUtil.<UserInfoBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if (userInfoBean.getStatus_code().equals(Constants.SUCCESS_CODE)) {
                            if (userInfoBean.getData() != null) {
                                mView.showUserInfoData(userInfoBean);
                            } else {
                                mView.showErrorMsg(userInfoBean.getMsg());
                            }
                        } else {
                            mView.showErrorMsg(userInfoBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showErrorMsg(e.getMessage());
                    }
                })
        );
    }
}