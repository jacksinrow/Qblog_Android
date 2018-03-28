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
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.model.bean.BlogContentBean;
import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.util.Logger;

import java.util.List;

/**
 * 类  名： BlogContentAdapter
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月22日 17:16
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class BlogContentAdapter extends BaseQuickAdapter {
    public BlogContentAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object data) {
        BlogDataBean blogContentBean = (BlogDataBean) data;
        helper.setText(R.id.tv_blogcontentitem_titel, blogContentBean.getTitle());
        if(blogContentBean.getUserBean() != null) {
            helper.setText(R.id.tv_blogcontentitem_author, blogContentBean.getUserBean().getUserName());
        }
        helper.setText(R.id.tv_blogcontentitem_createtime,blogContentBean.getCreateTime());
    }
}