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

package com.qyh.myblog_android.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RootFragment;
import com.qyh.myblog_android.base.contract.blog.BlogContentContract;
import com.qyh.myblog_android.model.bean.BlogDataBean;
import com.qyh.myblog_android.presenter.blog.BlogContentPresenter;
import com.qyh.myblog_android.ui.activity.blog.BlogDetailActivity;
import com.qyh.myblog_android.ui.adapter.BlogContentAdapter;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.widget.RecyclerViewDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 类  名： BlogContentFragment
 * 描  述：博客展示页面
 * 创建人： qyh
 * 日  期： 2018年03月20日 11:44
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net/ Inc. All rights reserved
 */
public class BlogContentFragment extends RootFragment<BlogContentPresenter> implements BlogContentContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    private int blogType;
    private BlogContentAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;
    private int page = 1;
    private int pageSize = 6;
    private List<BlogDataBean> mData = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blogcontent_view;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            blogType = getArguments().getInt(Constants.BLOG_TYPE);
        }
        mAdapter = new BlogContentAdapter(R.layout.item_blogcontent_view, null);
        mLayoutManger = new LinearLayoutManager(mContext);
        viewMain.setLayoutManager(mLayoutManger);
        viewMain.setHasFixedSize(true);
        viewMain.addItemDecoration(new RecyclerViewDecoration(
                mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage));
        viewMain.setAdapter(mAdapter);


        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue_light)
                , getResources().getColor(R.color.green_light)
                , getResources().getColor(R.color.orange_light)
                , getResources().getColor(R.color.red_light));
        refreshLayout.setOnRefreshListener(this);
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getBlogListData(page, Constants.TYPE_LOADMORE);
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,BlogDetailActivity.class);
                intent.putExtra(Constants.BLOGCONTENT_TYPE,  mData.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        stateLoading();
        getBlogListData(1, Constants.TYPE_REFRESH);
    }

    @Override
    public void showData(int requestType, List<BlogDataBean> blogDataBean) {
         mData.addAll(blogDataBean);
        stateMain();
        switch (requestType) {
            case Constants.TYPE_REFRESH:
                refreshLayout.setRefreshing(false);
                mAdapter.setNewData(blogDataBean);
                mAdapter.disableLoadMoreIfNotFullPage(viewMain);
                break;
            case Constants.TYPE_LOADMORE:
                mAdapter.addData(blogDataBean);
                mAdapter.loadMoreComplete();
                break;
        }
    }

    @Override
    public void showEmptyView() {
        stateEmpty();
    }

    @Override
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoMoreData() {
        stateMain();
        mAdapter.loadMoreEnd(); // 数据全部加载完毕
    }

    private void getBlogListData(int page, int requestType) {
        mPresenter.getBlogListData(blogType, page, pageSize, requestType);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getBlogListData(page, Constants.TYPE_REFRESH);
    }
}