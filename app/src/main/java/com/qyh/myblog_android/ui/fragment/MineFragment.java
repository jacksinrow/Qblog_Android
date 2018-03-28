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
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.BaseFragment;
import com.qyh.myblog_android.base.contract.mine.MineContract;
import com.qyh.myblog_android.model.bean.UserInfoBean;
import com.qyh.myblog_android.presenter.mine.MinePresenter;
import com.qyh.myblog_android.ui.activity.mine.CreateBlogActivity;
import com.qyh.myblog_android.ui.activity.mine.LoginActivity;
import com.qyh.myblog_android.ui.activity.mine.MyBlogActivity;
import com.qyh.myblog_android.util.ImageLoaderUtils;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.SharedPreferencesUtils;
import com.qyh.myblog_android.widget.WaveView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 类  名： MineFragment
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月20日 11:44
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {


    @BindView(R.id.wave_view)
    WaveView waveView;
    @BindView(R.id.iv_minefragment_icon)
    ImageView ivMinefragmentIcon;
    @BindView(R.id.tv_minefragment_myblog)
    TextView tvMinefragmentMyblog;
    @BindView(R.id.tv_minefragment_username)
    TextView tvMinefragmentUsername;
    @BindView(R.id.tv_minefragment_userinfo)
    TextView tvMinefragmentUserinfo;
    private String userId;
    private UserInfoBean.DataBean userInfoData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_view;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        ImageLoaderUtils.displayCircle(ivMinefragmentIcon, "http://c1.hoopchina.com.cn/uploads/star/event/images/160825/2634bc97229158f4da46bca88018317e1e6181df.jpg");
    }


    @Override
    protected void initEventAndData() {
        userId = SharedPreferencesUtils.getData(mContext, Constants.USERID, "");
        if (userId != null) {
            mPresenter.getUserInfo(userId);
        }
    }


    @OnClick({R.id.tv_minefragment_myblog, R.id.tv_minefragment_userinfo, R.id.tv_minefragment_createblog})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_minefragment_userinfo:
                startActivityForResult(LoginActivity.class, Constants.LOGIN_SUCCESS_FLAG);
                break;
            case R.id.tv_minefragment_myblog:
                startActivity(MyBlogActivity.class);
                break;
            case R.id.tv_minefragment_createblog:
                startActivity(CreateBlogActivity.class);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.LOGIN_SUCCESS_FLAG:
                initEventAndData();
                break;
        }
    }

    @Override
    public void showUserInfoData(UserInfoBean userInfoBean) {
        userInfoData = userInfoBean.getData();
        setUserInfo();
    }

    private void setUserInfo() {
        if (!TextUtils.isEmpty(userInfoData.getUserName())) {
            tvMinefragmentUsername.setText(userInfoData.getUserName());
        }
    }

    @Override
    public void getUserInfoError(String msg) {

    }
}