package com.haobin.watermelon_all_summer.module.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.haobin.watermelon_all_summer.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by Wenghaobin
 * on 2018/10/11
 * for
 */
public class MainActivity extends XActivity {

//    @BindView(R.id.fl_title_menu)
//    FrameLayout flTitleMenu;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        initToolBar();
        initNavigationView();
    }

    private void initToolBar(){
        // 设置titleBar
        setSupportActionBar(toolbarMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initNavigationView(){
        // 联系titleBar 和 navigationView
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlMain, toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        dlMain.addDrawerListener(toggle);
    }
}
