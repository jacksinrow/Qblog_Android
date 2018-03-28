package com.qyh.myblog_android;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.qyh.myblog_android.app.AppManager;
import com.qyh.myblog_android.base.SimpleActivity;
import com.qyh.myblog_android.ui.fragment.BlogMainFragment;
import com.qyh.myblog_android.ui.fragment.MineFragment;
import com.qyh.myblog_android.ui.fragment.RestsFragment;
import com.qyh.myblog_android.util.ToastUtil;
import com.youkb.mylibrary.BottomNavigationView.BottomNavigationItem;
import com.youkb.mylibrary.BottomNavigationView.BottomNavigationView;
import com.youkb.mylibrary.BottomNavigationView.OnBottomNavigationItemClickListener;

import butterknife.BindView;

public class MainActivity extends SimpleActivity{
    @BindView(R.id.fl_body)
    FrameLayout flBody;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    private BlogMainFragment blogMainFragment;
    private MineFragment mineFragment;
    private RestsFragment restsFragment;

    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        int[] image = {R.mipmap.icon_blog, R.mipmap.icon_rests, R.mipmap.icon_mine};
        int[] color = {ContextCompat.getColor(this, R.color.main_color), ContextCompat.getColor(this, R.color.main_color),
                ContextCompat.getColor(this, R.color.main_color)};

        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(true);
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.main_color));
            bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
        }

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("博客", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("其他", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("我的", color[2], image[2]);


        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                SwitchTo(index);
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
    }

    @Override
    protected void initEventAndData() {

    }


    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            blogMainFragment = (BlogMainFragment) getSupportFragmentManager().findFragmentByTag("blogMainFragment");
            mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag("mineFragment");
            restsFragment = (RestsFragment) getSupportFragmentManager().findFragmentByTag("restsFragment");
        } else {
            blogMainFragment = new BlogMainFragment();
            mineFragment = new MineFragment();
            restsFragment = new RestsFragment();

            transaction.add(R.id.fl_body, blogMainFragment, "blogMainFragment");
            transaction.add(R.id.fl_body, mineFragment, "mineFragment");
            transaction.add(R.id.fl_body, restsFragment, "restsFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
//        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.hide(mineFragment);
                transaction.hide(restsFragment);
                transaction.show(blogMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(blogMainFragment);
                transaction.hide(mineFragment);
                transaction.show(restsFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(blogMainFragment);
                transaction.hide(restsFragment);
                transaction.show(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }

    //双击退出应用
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis();
                ToastUtil.show("再按一次退出程序");
                return true;
            } else {
//                finish(); // 退出
                AppManager.getAppManager().addActivity(this);
            }
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
