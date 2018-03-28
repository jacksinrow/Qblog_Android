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

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.qyh.myblog_android.R;
import com.qyh.myblog_android.base.BaseActivity;
import com.qyh.myblog_android.base.contract.blog.CreateBlogContract;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.presenter.blog.CreateBlogPresenter;
import com.qyh.myblog_android.util.AppUtils;
import com.qyh.myblog_android.util.ToastUtil;
import com.qyh.myblog_android.widget.BlogTypePop;

import java.util.HashMap;

import butterknife.BindView;

/**
 * 类  名： CreateBlogActivity
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月24日 13:38
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class CreateBlogActivity extends BaseActivity<CreateBlogPresenter> implements CreateBlogContract.View {

    @BindView(R.id.et_new_title)
    EditText etNewTitle;
    @BindView(R.id.et_new_content)
    EditText etNewContent;
    @BindView(R.id.content_new)
    LinearLayout contentNew;
    @BindView(R.id.bt_create_blog)
    Button btCreateBlog;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private HashMap<String, String> map;

    private String title;
    private String content;
    private BlogTypePop blogTypePop;
    private int blogType;

    @Override
    protected int getLayout() {

        return R.layout.activity_mine_createblog;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void initView() {
        setToolBar(toolBar, "创建博客");
        etNewTitle.addTextChangedListener(new titleListener());
        etNewContent.addTextChangedListener(new contentListener());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_upload_blog:
                uploadBlog();
                break;
            case R.id.action_blogtype:
                mPresenter.getBlogTypeList();
                if (blogTypePop == null) {
                    blogTypePop = new BlogTypePop(mContext);
                }
                blogTypePop.setOnBlogTypePopListener(new mListener());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadBlog() {

        if (!TextUtils.isEmpty(AppUtils.getUserId())) {
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                map = new HashMap<>();
                map.put("userId", AppUtils.getUserId());
                map.put("title", title);
                map.put("content", content);
                map.put("type", String.valueOf(blogType));
                String currentDate = AppUtils.dateFormat();
                map.put("createTime", currentDate);
                mPresenter.addBlog(map);
            } else {
                ToastUtil.show("请输入完整后提交!");
            }
        } else {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showSuccessMsg(String msg) {
        ToastUtil.show(msg);
        finish();
    }

    @Override
    public void showErrorssMsg(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showBlogTypeListData(BlogTypeBean bean) {
        blogTypePop.setPopData(bean.getData());
        blogTypePop.showPopWindow(toolBar);
    }

    private class titleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            title = s.toString().trim();
        }
    }

    private class contentListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            content = s.toString().trim();
        }
    }

    private class mListener implements BlogTypePop.OnBlogTypePopListener {
        @Override
        public void BlogType(int type) {
            blogType = type + 1;
        }
    }
}
