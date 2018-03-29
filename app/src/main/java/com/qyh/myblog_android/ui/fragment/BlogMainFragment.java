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

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.BaseFragment;
import com.qyh.myblog_android.base.BaseFragmentAdapter;
import com.qyh.myblog_android.base.contract.blog.BlogMainContract;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.presenter.blog.BlogMainPresenter;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.MyUtils;
import com.qyh.myblog_android.widget.tablayout.ColorTrackTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 类  名： BlogMainFragment
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月20日 15:48
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class BlogMainFragment extends BaseFragment<BlogMainPresenter> implements BlogMainContract.View {

    @BindView(R.id.tab_blog_type)
    ColorTrackTabLayout tabs;
    @BindView(R.id.vp_blog_main)
    ViewPager vpBlogMain;
    private BaseFragmentAdapter baseFragmentAdapter;
    private List<BlogTypeBean> blogTypeList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blogmain_view;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getBlogTypeList();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        tabs.setTabPaddingLeftAndRight(25, 25);
        //隐藏指示器
        tabs.setSelectedTabIndicatorHeight(0);
        Logger.e("initView===========");
    }


    @Override
    public void showBlogTypeListData(List<BlogTypeBean> blogTypeBean) {
        blogTypeList = blogTypeBean;
        initFragmenAdapter();
    }

    private void initFragmenAdapter() {
        ArrayList<String> channelNames = new ArrayList<>();
        List<Fragment> mNewsFragmentList = new ArrayList<>();
        for (int i = 0; i < blogTypeList.size(); i++) {
            channelNames.add(blogTypeList.get(i).getTypName());
            mNewsFragmentList.add(createListFragments(blogTypeList.get(i)));
        }

        if (baseFragmentAdapter == null) {
            baseFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, channelNames);
        } else {
            baseFragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, channelNames);
        }
        if (vpBlogMain != null) {
            vpBlogMain.setAdapter(baseFragmentAdapter);

            tabs.setupWithViewPager(vpBlogMain);
            MyUtils.dynamicSetTabLayoutMode(tabs);
        }
//        setPageChangeListener();
    }

    private BlogContentFragment createListFragments(BlogTypeBean typeBean) {

        BlogContentFragment blogContentFragment = new BlogContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BLOG_TYPE, typeBean.getTypeId());
        blogContentFragment.setArguments(bundle);
        return blogContentFragment;
    }

    @Override
    public void showNotData() {

    }

}