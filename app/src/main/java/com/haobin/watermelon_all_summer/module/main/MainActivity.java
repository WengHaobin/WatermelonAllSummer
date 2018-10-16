package com.haobin.watermelon_all_summer.module.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.module.home.HomeFragment;
import com.haobin.watermelon_all_summer.module.tree.TreeFragment;
import com.haobin.watermelon_all_summer.module.welfare.WelfareFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by Wenghaobin
 * on 2018/10/11
 * for 首页
 */
public class MainActivity extends XActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.iv_title_one)
    ImageView ivTitleOne;
    @BindView(R.id.iv_title_two)
    ImageView ivTitleTwo;
    @BindView(R.id.iv_title_three)
    ImageView ivTitleThree;

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
        initFragments();
    }

    private void initToolBar() {
        // 设置titleBar
        setSupportActionBar(toolbarMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initNavigationView() {
        // 联系titleBar 和 navigationView
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dlMain, toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        dlMain.addDrawerListener(toggle);
    }

    private void initFragments() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new TreeFragment());
        mFragmentList.add(new WelfareFragment());

        String[] titles = new String[3];
        titles[0] = "首页";
        titles[1] = "体系";
        titles[2] = "福利";
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(), mFragmentList, titles);
        vpContent.setAdapter(xFragmentAdapter);

        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCurrentItem(int position) {
        boolean isOne = false;
        boolean isTwo = false;
        boolean isThree = false;
        switch (position) {
            case 0:
                isOne = true;
                break;
            case 1:
                isTwo = true;
                break;
            case 2:
                isThree = true;
                break;
            default:
                isTwo = true;
                break;
        }
        vpContent.setCurrentItem(position);
        ivTitleOne.setSelected(isOne);
        ivTitleTwo.setSelected(isTwo);
        ivTitleThree.setSelected(isThree);
    }

    @OnClick({R.id.iv_title_one, R.id.iv_title_two, R.id.iv_title_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_one:
                if (vpContent.getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
                break;
            case R.id.iv_title_two:
                if (vpContent.getCurrentItem() != 1) {
                    setCurrentItem(1);
                }
                break;
            case R.id.iv_title_three:
                if (vpContent.getCurrentItem() != 2) {
                    setCurrentItem(2);
                }
                break;
        }
    }
}
