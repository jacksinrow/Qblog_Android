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

package com.qyh.myblog_android.ui.activity.mine;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RootActivity;
import com.qyh.myblog_android.base.contract.blog.MyBlogListContract;
import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.presenter.blog.MyBlogListPresenter;
import com.qyh.myblog_android.ui.activity.blog.BlogDetailActivity;
import com.qyh.myblog_android.ui.adapter.BlogContentAdapter;
import com.qyh.myblog_android.util.AppUtils;
import com.qyh.myblog_android.widget.RecyclerViewDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 类  名： MyBlogActivity
 * 描  述： 我的博客列表页面
 * 创建人： qyh
 * 日  期： 2018年03月23日 21:33
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class MyBlogActivity extends RootActivity<MyBlogListPresenter> implements MyBlogListContract.View {

    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private BlogContentAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;
    private String userId;
    private List<BlogDataBean> mData;

    @Override
    protected int getLayout() {
        return R.layout.activity_mybloglist;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initView() {
        setToolBar(toolBar, "我的博客");
        mAdapter = new BlogContentAdapter(R.layout.item_blogcontent_view, null);
        mLayoutManger = new LinearLayoutManager(mContext);
        viewMain.setLayoutManager(mLayoutManger);
        viewMain.setHasFixedSize(true);
        viewMain.addItemDecoration(new RecyclerViewDecoration(
                mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage));
        viewMain.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, BlogDetailActivity.class);
                intent.putExtra(Constants.BLOGCONTENT_TYPE, mData.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        userId = AppUtils.getUserId();
        if (!TextUtils.isEmpty(userId)) {
            mPresenter.getBlogListData(userId);
        } else {
            startActivityForResult(LoginActivity.class, Constants.LOGIN_SUCCESS_FLAG);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.LOGIN_SUCCESS_FLAG:
                userId = AppUtils.getUserId();
                mPresenter.getBlogListData(userId);
                break;
        }
    }

    @Override
    public void showData(List<BlogDataBean> blogDataBeen) {
        stateMain();
        mData = blogDataBeen;
        mAdapter.setNewData(blogDataBeen);
    }

    @Override
    public void showEmptyView() {
        stateEmpty();
    }
}