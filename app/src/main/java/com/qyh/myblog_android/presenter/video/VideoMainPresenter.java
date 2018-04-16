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

package com.qyh.myblog_android.presenter.video;

import android.support.annotation.NonNull;

import com.qyh.myblog_android.base.RxPresenter;
import com.qyh.myblog_android.base.contract.video.VideoMainContract;
import com.qyh.myblog_android.model.DataManager;
import com.qyh.myblog_android.model.bean.MyHttpResponse;
import com.qyh.myblog_android.model.bean.VideoTypeBean;
import com.qyh.myblog_android.util.RxUtil;
import com.qyh.myblog_android.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * 类  名： VideoMainPresenter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月30日 13:56
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class VideoMainPresenter extends RxPresenter<VideoMainContract.View> implements
        VideoMainContract.Presenter {

    private DataManager manager;

    @Inject
    public VideoMainPresenter(DataManager dataManager) {
        this.manager = dataManager;
    }

    @Override
    public void getVideoTypeList() {
        addSubscribe(manager.getVideoTypeList()
                .compose(RxUtil.<MyHttpResponse<List<VideoTypeBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<VideoTypeBean>>handleMyResult())
                .subscribeWith(new CommonSubscriber<List<VideoTypeBean>>(mView) {
                    @Override
                    public void onNext(List<VideoTypeBean> videoTypeBeen) {
                        if (videoTypeBeen != null && videoTypeBeen.size() > 0) {
                            mView.showVideoTypeListData(videoTypeBeen);
                        }
                    }
                })
        );
    }
}