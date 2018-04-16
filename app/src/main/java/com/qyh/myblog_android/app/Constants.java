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

package com.qyh.myblog_android.app;

import java.io.File;

/**
 * 类  名： Constants
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月20日 21:51
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.g7.com.cn Inc. All rights reserved
 */
public class Constants {


    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String BLOG_TYPE = "blog_type";
    public static final String VIDEO_TYPE = "video_type";
    public static final String SUCCESS_CODE = "1"; // 成功状态码
    public static final int LOGIN_SUCCESS_FLAG = 100; //
    public static final String USERID = "userid"; //

    public static final int TYPE_REFRESH = 0x0;
    public static final int TYPE_LOADMORE = 0x1;

    public static final String BLOGCONTENT_TYPE="blogcontent_type";

}