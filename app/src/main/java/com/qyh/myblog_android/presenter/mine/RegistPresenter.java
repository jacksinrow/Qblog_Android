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
import com.qyh.myblog_android.base.contract.mine.RegistContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * 类  名： RegistPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月22日 21:39
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class RegistPresenter extends RxPresenter<RegistContract.View> implements RegistContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RegistPresenter(DataManager dm) {
        this.mDataManager = dm;
    }

    @Override
    public void regist(String phone, String password, String userName) {
        addSubscribe(mDataManager.regist(phone, password, userName)
                .compose(RxUtil.<MyHttpResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<MyHttpResponse>(mView) {
                    @Override
                    public void onNext(MyHttpResponse baseBean) {
                        if (baseBean.getStatus_code().equals(Constants.SUCCESS_CODE)) {
                            Logger.e("註冊==="+baseBean.toString());
                            mView.registSuccess(baseBean.getMsg());
                        } else {
                            mView.registError(baseBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.registError(App.getInstance().getString(R.string.neterrormsg));
                    }
                })
        );
    }
}
