package com.haobin.watermelon_all_summer.module.home;

import android.os.Bundle;

import com.haobin.watermelon_all_summer.R;

import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 首页
 */
public class HomeFragment extends XFragment {

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public Object newP() {
        return null;
    }
}
