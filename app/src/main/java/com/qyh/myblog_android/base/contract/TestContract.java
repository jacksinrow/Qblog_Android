package com.qyh.myblog_android.base.contract;


import com.qyh.myblog_android.base.BasePresenter;
import com.qyh.myblog_android.base.BaseView;

/**
 * 描述：
 * Created by qyh on 2018/1/16.
 */

public interface TestContract {

    interface View extends BaseView {
        void showContent();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
