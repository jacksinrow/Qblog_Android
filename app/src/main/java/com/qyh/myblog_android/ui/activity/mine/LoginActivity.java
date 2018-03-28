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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.BaseActivity;
import com.qyh.myblog_android.base.contract.mine.LoginContract;
import com.qyh.myblog_android.model.bean.UserInfoBean;
import com.qyh.myblog_android.presenter.mine.LoginPresenter;
import com.qyh.myblog_android.util.AnimatorUtils;
import com.qyh.myblog_android.util.Logger;
import com.qyh.myblog_android.util.SharedPreferencesUtils;
import com.qyh.myblog_android.util.ToastUtil;
import com.youkb.mylibrary.Keyboard.DrawableTextView;
import com.youkb.mylibrary.Keyboard.KeyboardWatcher;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类  名： LoginActivity
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月21日 22:06
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.iv_clean_password)
    ImageView ivCleanPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.body)
    LinearLayout body;
    @BindView(R.id.dt_logo)
    DrawableTextView dtLogo;
    private KeyboardWatcher keyboardWatcher;
    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例
    private String phone;
    private String password;

    @Override
    protected int getLayout() {
        return R.layout.activity_login_view;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initView() {
        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        etLoginPhone.addTextChangedListener(new phoneWatcher());
        etLoginPassword.addTextChangedListener(new passwordWatcher());
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void loginSuccess(UserInfoBean userInfoBean) {
        String s = userInfoBean.getData().toString();
        ToastUtil.show("登录成功");
        SharedPreferencesUtils.saveData(mContext, Constants.USERID, String.valueOf(userInfoBean.getData().getUserId()));
        setResult(Constants.LOGIN_SUCCESS_FLAG);
        finish();
    }

    @Override
    public void loginError(String msg) {
        ToastUtil.show(msg);
    }

    @OnClick({R.id.btn_login, R.id.tv_regist})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.login(phone, password);
                break;
            case R.id.tv_regist:
                startActivity(RegistActivity.class);
                break;
        }
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int y = location[1];
        int bottom = screenHeight - (y + body.getHeight());
        if (keyboardSize > bottom) {
            //先平移根布局
            AnimatorUtils.rootViewTrans(body, 0.0f, -(keyboardSize - bottom));
            AnimatorUtils.zoomIn(dtLogo, keyboardSize - bottom, scale);
        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        AnimatorUtils.rootViewTrans(body, body.getTranslationY(), 0);
        AnimatorUtils.zoomOut(dtLogo, scale);
    }

    private class phoneWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            phone = s.toString().trim();
            if (!TextUtils.isEmpty(phone)) {
                ivCleanPhone.setVisibility(View.VISIBLE);
            } else {
                ivCleanPhone.setVisibility(View.GONE);
            }
            isGoNext();
        }
    }

    private class passwordWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            password = s.toString().trim();
            if (!TextUtils.isEmpty(password)) {
                ivCleanPassword.setVisibility(View.VISIBLE);
            } else {
                ivCleanPassword.setVisibility(View.GONE);
            }
            isGoNext();
        }
    }

    private void isGoNext() {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }
}