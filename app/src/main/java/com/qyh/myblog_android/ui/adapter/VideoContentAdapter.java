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

package com.qyh.myblog_android.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.model.bean.VideoDataBean;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * 类  名： VideoContentAdapter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月31日 21:35
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class VideoContentAdapter extends BaseQuickAdapter {
    public VideoContentAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        VideoDataBean videoDataBean = (VideoDataBean) item;

        helper.setText(R.id.tv_videocontentitem_titel,videoDataBean.getTitle());

        JZVideoPlayerStandard videoPlayer = helper.getView(R.id.videoplayer);
        videoPlayer.setUp(videoDataBean.getUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                videoDataBean.getTitle());

    }
}