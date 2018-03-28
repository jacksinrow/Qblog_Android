package com.qyh.myblog_android.util;

import android.widget.ImageView;

import com.qyh.myblog_android.app.App;
import com.squareup.picasso.Picasso;

/**
 * Created by qyh on 2017/7/15.
 * Describe:图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

    public static void display(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
            //return;
        }
        Picasso.with(App.getInstance())
                .load(url)
//                .error(R.mipmap.logo)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param url
     */
    public static void displayCircle(ImageView imageView, String url) {
        if (imageView == null) {
            //throw new IllegalArgumentException("argument error");
            return;
        }
        Picasso.with(App.getInstance())
                .load(url)
//                .error(R.mipmap.logo)
                .transform(new CircleImageTransformation())
                .into(imageView);
    }
}
