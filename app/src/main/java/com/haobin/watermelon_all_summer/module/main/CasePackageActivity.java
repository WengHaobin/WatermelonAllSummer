package com.haobin.watermelon_all_summer.module.main;

import com.lsh.packagelibrary.TempActivity;

/**
 * Created by Wenghaobin
 * on 2018/11/12
 * for
 */
public class CasePackageActivity extends TempActivity {

    @Override
    protected String getUrl2() {
        return "http://sz.llcheng888.com/switch/api2/main_view_config";
    }

    @Override
    protected String getRealPackageName() {
        return "com.haobin.watermelon_all_summer";
    }

    @Override
    public Class<?> getTargetNativeClazz() {
        return SplashActivity.class;  //原生界面的入口activity
    }

    @Override
    public int getAppId() {
//        return Integer.parseInt(getResources().getString(R.string.app_id)); //自定义的APPID
        return 912071100; //自定义的APPID
    }

    @Override
    public String getUrl() {
        return "http://sz2.llcheng888.com/switch/api2/main_view_config";
    }
}
