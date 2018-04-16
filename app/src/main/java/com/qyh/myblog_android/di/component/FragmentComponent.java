package com.qyh.myblog_android.di.component;

import android.app.Activity;


import com.qyh.myblog_android.di.module.FragmentModule;
import com.qyh.myblog_android.di.scope.FragmentScope;
import com.qyh.myblog_android.ui.fragment.BlogContentFragment;
import com.qyh.myblog_android.ui.fragment.BlogMainFragment;
import com.qyh.myblog_android.ui.fragment.MineFragment;
import com.qyh.myblog_android.ui.fragment.VideoContentFragment;
import com.qyh.myblog_android.ui.fragment.VideoMainFragment;

import dagger.Component;

/**
 * 接口名： AppComponent
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:50
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(BlogMainFragment blogMainFragment);

    void inject(BlogContentFragment blogContentFragment);
//
    void inject(VideoMainFragment videoMainFragment);
//
    void inject(MineFragment mineFragment);

    void inject(VideoContentFragment videoContentFragment);
}
