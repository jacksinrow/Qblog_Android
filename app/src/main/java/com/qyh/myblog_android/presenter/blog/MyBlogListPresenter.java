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

import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.blog.MyBlogListContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * 类  名： MyBlogListPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月23日 21:59
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
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
                .compose(RxUtil.<MyHttpResponse<List<BlogDataBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<BlogDataBean>>handleMyResult())
                .subscribeWith(new CommonSubscriber<List<BlogDataBean>>(mView) {
                    @Override
                    public void onNext(List<BlogDataBean> blogDataBeen) {
                        if (blogDataBeen != null && blogDataBeen.size() > 0) {
                            if (blogDataBeen.get(0).getId() != -1) {
                                mView.showData(blogDataBeen);
                            } else {
                                mView.showEmptyView();
                            }
                        } else {
                            mView.showEmptyView();
                        }
                    }
                })
        );
    }
}