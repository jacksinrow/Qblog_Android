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

package com.qyh.myblog_android.presenter.blog;

import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.blog.MyBlogListContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.BlogContentBean;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.util.ToastUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * 类  名： MyBlogListPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月23日 21:59
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class MyBlogListPresenter extends RxPresenter<MyBlogListContract.View> implements
        MyBlogListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public MyBlogListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getBlogListData(String userId) {
        addSubscribe(mDataManager.getBlogListByid(userId)
                .compose(RxUtil.<BlogContentBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BlogContentBean>(mView) {
                    @Override
                    public void onNext(BlogContentBean blogContentBean) {
                        Logger.e("我的博客===="+blogContentBean.toString());
                        if (blogContentBean.getStatus_code().equals(Constants.SUCCESS_CODE)) {
                            if (blogContentBean.getData() != null && blogContentBean.getData().size() > 0) {
                                mView.showData(blogContentBean);
                            } else {
                                mView.showEmptyView();
                            }
                        } else {
                            mView.showEmptyView();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Logger.e(e.toString());
                    }
                })
        );
    }
}