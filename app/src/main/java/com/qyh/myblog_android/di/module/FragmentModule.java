package com.qyh.myblog_android.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.qyh.myblog_android.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * 类  名： FragmentModule
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 20:38
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
