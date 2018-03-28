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

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.app.Constants;
import com.qyh.myblog_android.base.BaseActivity;
import com.qyh.myblog_android.base.contract.mine.RegistContract;
import com.qyh.myblog_android.presenter.mine.RegistPresenter;
import com.qyh.myblog_android.util.AnimatorUtils;
import com.qyh.myblog_android.util.ToastUtil;
import com.youkb.mylibrary.Keyboard.DrawableTextView;
import com.youkb.mylibrary.Keyboard.KeyboardWatcher;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类  名： RegistActivity
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月22日 21:41
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class RegistActivity extends BaseActivity<RegistPresenter> implements RegistContract.View,
        KeyboardWatcher.SoftKeyboardStateListener {


    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.dt_logo)
    DrawableTextView dtLogo;
    @BindView(R.id.et_regist_phone)
    EditText etRegistPhone;
    @BindView(R.id.iv_clean_username)
    ImageView ivCleanUsername;
    @BindView(R.id.et_regist_password)
    EditText etRegistPassword;
    @BindView(R.id.iv_clean_password)
    ImageView ivCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;
    @BindView(R.id.et_regist_usename)
    EditText etRegistUsename;
    @BindView(R.id.iv_clean_phone)
    ImageView ivCleanPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.body)
    LinearLayout body;
    @BindView(R.id.root)
    LinearLayout root;

    private KeyboardWatcher keyboardWatcher;
    private int screenHeight = 0;//屏幕高度
    private float scale = 0.8f; //logo缩放比例
    private String password;
    private String phone;
    private String userName;

    @Override
    protected int getLayout() {
        return R.layout.activity_regist_view;
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
        etRegistPhone.addTextChangedListener(new phoneWatcher());
        etRegistPassword.addTextChangedListener(new passwordWatcher());
        etRegistUsename.addTextChangedListener(new userNameWatcher());
    }

    @OnClick(R.id.btn_login)
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.regist(phone, password, userName);
                break;
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void registSuccess(String msg) {
        ToastUtil.show(msg);
        setResult(Constants.LOGIN_SUCCESS_FLAG);
        finish();
    }

    @Override
    public void registError(String msg) {
        ToastUtil.show(msg);
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

    private class userNameWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            userName = s.toString().trim();
            inNextGo();
        }
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
            inNextGo();
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
            inNextGo();
        }
    }

    private void inNextGo() {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(userName)) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }
}