package com.haobin.watermelon_all_summer.module.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by Wenghaobin
 * on 2018/11/12
 * for 设置页
 */
public class SettingActivity extends XActivity{

    private static final String TAG = "SettingActivity";

    @BindView(R.id.tb_setting)
    Toolbar tbSetting;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void bindEvent() {
        tbSetting.setTitle(getString(R.string.about));
        setSupportActionBar(tbSetting);
        tbSetting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        XLog.d(TAG, "SettingActivity.initData--" + Kits.File.getFolderSize(Constants.CACHE_PATH));
        tvSettingClear.setText(Kits.File.getFolderSize(Constants.CACHE_PATH));
    }

    @OnClick(R.id.tv_setting_clear)
    public void onViewClicked() {
        Kits.File.deleteFile(Constants.CACHE_PATH);
        tvSettingClear.setText(Kits.File.getFolderSize(Constants.CACHE_PATH));
        ToastUtils.showToast("清除干净啦>.<");
    }

    @Override
    public Object newP() {
        return null;
    }
}
