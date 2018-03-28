package com.qyh.myblog_android.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qyh.myblog_android.R;
import com.qyh.myblog_android.model.bean.BlogTypeBean;

import java.util.List;

/**
 * Created by adminn on 2018/3/27.
 */

public class BlogTypeAdapter extends BaseQuickAdapter {
    public BlogTypeAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        BlogTypeBean.DataBean dataBean = (BlogTypeBean.DataBean) item;
        helper.setText(R.id.tv_popu_blogtype,dataBean.getTypName());
    }
}
