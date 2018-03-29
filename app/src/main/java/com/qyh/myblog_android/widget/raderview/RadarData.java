package com.qyh.myblog_android.widget.raderview;

/**
 * Created by qyh on 2016/10/31.
 */

public class RadarData {

    private String title;
    private double percentage;

    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public double getPercentage() {
        return percentage;
    }
}
