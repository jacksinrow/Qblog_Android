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

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.RootFragment;
import com.qyh.myblog_android.base.contract.video.VideoContentContract;
import com.qyh.myblog_android.model.bean.VideoDataBean;
import com.qyh.myblog_android.presenter.video.VideoContentPresenter;
import com.qyh.myblog_android.ui.adapter.VideoContentAdapter;
import com.qyh.myblog_android.widget.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

/**
 * 类  名： VideoContentFragment
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月31日 21:30
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class VideoContentFragment extends RootFragment<VideoContentPresenter> implements VideoContentContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private VideoContentAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;
    private int page = 1;
    private int pageSize = 5;
    private List<VideoDataBean> mData = new ArrayList<>();
    private int videoType; // 1 是源码解析视频类型，请求自己的服务器

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog_datalist;
    }

    @Override
    protected void initView() {
        mAdapter = new VideoContentAdapter(R.layout.item_videocontent_view, null);
        mLayoutManger = new LinearLayoutManager(mContext);
        viewMain.setLayoutManager(mLayoutManger);
        viewMain.setHasFixedSize(true);
        viewMain.addItemDecoration(new RecyclerViewDecoration(
                mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage));
        viewMain.setAdapter(mAdapter);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_light)
                , getResources().getColor(R.color.green_light)
                , getResources().getColor(R.color.orange_light)
                , getResources().getColor(R.color.red_light));
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getVideoList(page, Constants.TYPE_LOADMORE);
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        if (getArguments() != null) {
            videoType = getArguments().getInt(Constants.VIDEO_TYPE);
        }
        getVideoList(page, Constants.TYPE_REFRESH);
    }

    @Override
    public void showData(int requestType, List<VideoDataBean> videoDataBeen) {
        mData.addAll(videoDataBeen);
        stateMain();
        switch (requestType) {
            case Constants.TYPE_REFRESH:
                swipeRefresh.setRefreshing(false);
                mAdapter.setNewData(videoDataBeen);
                mAdapter.disableLoadMoreIfNotFullPage(viewMain);
                break;
            case Constants.TYPE_LOADMORE:
                mAdapter.addData(videoDataBeen);
                mAdapter.loadMoreComplete();
                break;
        }
    }

    @Override
    public void showEmptyView() {
        stateEmpty();
    }

    @Override
    public void showNoMoreData() {
        stateMain();
        mAdapter.loadMoreEnd(); // 数据全部加载完毕
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void getVideoList(int page, int requestType) {
        if (videoType == 1) {
            mPresenter.getVideoListData(page, pageSize, requestType);
        }else{
            stateEmpty();
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getVideoList(page, Constants.TYPE_REFRESH);
    }
}