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
import com.qyh.myblog_android.base.contract.blog.BlogContentContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * 类  名： BlogContentPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月20日 15:57
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class BlogContentPresenter extends RxPresenter<BlogContentContract.View> implements BlogContentContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public BlogContentPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getBlogListData(int type, int page, int pageSize, final int requestType) {
        addSubscribe(mDataManager.getBlogList(type, page, pageSize)
                .compose(RxUtil.<MyHttpResponse<List<BlogDataBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<BlogDataBean>>handleMyResult())
                .subscribeWith(new CommonSubscriber<List<BlogDataBean>>(mView) {
                    @Override
                    public void onNext(List<BlogDataBean> blogDataBeen) {
                        if (blogDataBeen != null && blogDataBeen.size() > 0) {
                            // TODO: 2018/3/29  此处处理的有点low，后期优化 id为-1 说明没有数据。。。为了避过rxjava返回空数据问题
                            if (blogDataBeen.get(0).getId() != -1) {
                                mView.showData(requestType, blogDataBeen);
                            } else {
                                if (requestType == Constants.TYPE_LOADMORE) {
                                    mView.showNoMoreData();
                                } else {
                                    mView.showEmptyView();
                                }

                            }
                        }
                    }

                })
        );
    }
}