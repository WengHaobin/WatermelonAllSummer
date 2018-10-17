package com.haobin.watermelon_all_summer.module.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.module.home.view.ArticleFragment;
import com.haobin.watermelon_all_summer.module.home.view.ProjectFragment;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 首页
 */
public class HomeFragment extends XFragment {

    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initFragments();
    }

    private void initFragments() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new ArticleFragment());
        mFragmentList.add(new ProjectFragment());

        String[] strings = new String[2];
        strings[0] = "文章";
        strings[1] = "项目";
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getChildFragmentManager(), mFragmentList, strings);
        vpHome.setAdapter(xFragmentAdapter);
        vpHome.setOffscreenPageLimit(2);

        tabHome.setTabMode(TabLayout.MODE_FIXED);
        tabHome.setupWithViewPager(vpHome);
    }

    @Override
    public Object newP() {
        return null;
    }
}
