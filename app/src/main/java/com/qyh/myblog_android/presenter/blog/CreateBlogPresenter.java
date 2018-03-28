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
import com.qyh.myblog_android.base.contract.blog.CreateBlogContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.BaseBean;
import com.qyh.myblog_android.model.bean.BlogContentBean;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.util.ToastUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import java.util.Map;

import javax.inject.Inject;

/**
 * 类  名： CreateBlogPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月24日 13:41
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class CreateBlogPresenter extends RxPresenter<CreateBlogContract.View> implements CreateBlogContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public CreateBlogPresenter(DataManager manager) {
        this.mDataManager = manager;
    }

    @Override
    public void getBlogTypeList() {
        addSubscribe(mDataManager.getBlogTypeList()
                .compose(RxUtil.<BlogTypeBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BlogTypeBean>(mView) {
                    @Override
                    public void onNext(BlogTypeBean blogTypeBean) {
                        if(blogTypeBean != null) {
                            if(blogTypeBean.getData() != null) {
                                mView.showBlogTypeListData(blogTypeBean);
                            }
                        }else{
                            ToastUtil.show("获取博客列表失败！");
                        }
                    }
                })
        );
    }

    @Override
    public void addBlog(Map map) {
        addSubscribe(mDataManager.addBlog(map)
                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseBean>(mView) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean != null && baseBean.getStatus_code().equals(Constants.SUCCESS_CODE)) {
                            mView.showSuccessMsg(baseBean.getMsg());
                        } else {
                            mView.showErrorMsg(baseBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                })
        );
    }
}