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

package com.qyh.myblog_android.model.db;


import javax.inject.Inject;

/**
 * 类  名： GreenDaoHelper
 * 描  述：
 * 创建人： qyh
 * 日  期： 2018年03月17日 22:47
 * 版本号： 1.0
 * <p>
 * Copyright (c) 2018 www.youkb.net Inc. All rights reserved
 */
public class GreenDaoHelper implements DBHelper {

//    private static final String DB_NAME = "youkb.realm";
//    private final DaoSession daoSession;
//
    @Inject
    public GreenDaoHelper() {
//        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getInstance(), DB_NAME, null);
//        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
//        daoSession = daoMaster.newSession();
    }

    /**
//     * 增加新闻分类数据
//     * @param dataBean
//     */
//    @Override
//    public void insertNewsClassData(VideoNewsClassListBean dataBean) {
//        NewsClassBeanDao newsClassBeanDao = daoSession.getNewsClassBeanDao();
//        List<NewsClassBean> news = dataBean.getNews();
//        newsClassBean = new NewsClassBean();
//        for (int i = 0; i <news.size() ; i++) {
//            String name = news.get(i).getName();
//            String type = news.get(i).getType();
//            newsClassBean = new NewsClassBean(name,type);
//            newsClassBeanDao.insert(newsClassBean);
//        }
//    }
//
//    @Override
//    public void insertVideoClassData(VideoNewsClassListBean dataBean) {
//        VideoClassBeanDao videoClassBeanDao = daoSession.getVideoClassBeanDao();
//        List<VideoClassBean> videos = dataBean.getVideos();
//        for (int i = 0; i <videos.size() ; i++) {
//            String name = videos.get(i).getName();
//            String type = videos.get(i).getType();
//            videoClassBean = new VideoClassBean(name,type);
//            videoClassBeanDao.insert(videoClassBean);
//        }
//    }
//
//    @Override
//    public List<NewsClassBean> queryNewsClassData() {
//        NewsClassBeanDao newsClassBeanDao = daoSession.getNewsClassBeanDao();
//        List<NewsClassBean> newsClassBeans = newsClassBeanDao.loadAll();
//        return newsClassBeans;
//    }
//
//    @Override
//    public List<VideoClassBean> queryVideoClassData() {
//        VideoClassBeanDao videoClassBeanDao = daoSession.getVideoClassBeanDao();
//        List<VideoClassBean> videoClassBeans = videoClassBeanDao.loadAll();
//        return videoClassBeans;
//    }
}