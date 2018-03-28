package com.qyh.myblog_android.widget;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.model.bean.BlogTypeBean;
import com.qyh.myblog_android.ui.adapter.BlogTypeAdapter;

import java.util.List;

/**
 * Created by qyh on 2018/3/7.
 * 用户收益类型pop  RecyclerView 填充
 */

public class BlogTypePop extends PopupWindow {
    private Activity mContext;
    private PopupWindow popupWindow;
    private OnBlogTypePopListener mListener;
    private List<BlogTypeBean.DataBean> mData;
    private BlogTypeAdapter mAdapter;
    private int currentPositon = 0;

    public BlogTypePop(Activity context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_common, null);
            popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);

            RecyclerView rv_view_main = (RecyclerView) view.findViewById(R.id.view_main);
            mAdapter = new BlogTypeAdapter(R.layout.item_blogtype_view, null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            rv_view_main.setLayoutManager(linearLayoutManager);
            rv_view_main.setHasFixedSize(true);
            rv_view_main.addItemDecoration(new RecyclerViewDecoration(
                    mContext, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage));
            rv_view_main.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                    if (mListener != null) {
                        if(currentPositon != position) {
                            currentPositon = position;
                            mListener.BlogType(position);
                            closePopWindow();
                        }
                    }
                }
            });
        }
    }

    public void setPopData(List<BlogTypeBean.DataBean> data) {
        this.mData = data;
        mData.remove(0);
        mAdapter.setNewData(data);
    }

    public void showPopWindow(View view) {
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(view);
        }
    }

    public void closePopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


    public interface OnBlogTypePopListener {
        void BlogType(int type);

    }

    public void setOnBlogTypePopListener(OnBlogTypePopListener listener) {
        this.mListener = listener;
    }
}
