package com.haobin.watermelon_all_summer.module.welfare;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.module.welfare.view.PhotoListFragment;
import com.haobin.watermelon_all_summer.module.welfare.view.VideoListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for
 */
public class WelfareFragment extends XFragment {

    @BindView(R.id.tab_welfare)
    TabLayout tabWelfare;
    @BindView(R.id.vp_welfare)
    ViewPager vpWelfare;

    @Override
    public void initData(Bundle savedInstanceState) {
        initFragments();
    }

    private void initFragments() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new VideoListFragment());
        mFragmentList.add(new PhotoListFragment());

        String[] strings = new String[2];
        strings[0] = "休息视频";
        strings[1] = "精美图片";
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getChildFragmentManager(), mFragmentList, strings);
        vpWelfare.setAdapter(xFragmentAdapter);
        vpWelfare.setOffscreenPageLimit(2);

        tabWelfare.setTabMode(TabLayout.MODE_FIXED);
        tabWelfare.setupWithViewPager(vpWelfare);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welfare;
    }

    @Override
    public Object newP() {
        return null;
    }
}
