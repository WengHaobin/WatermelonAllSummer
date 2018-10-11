package com.haobin.watermelon_all_summer.http;

import com.haobin.watermelon_all_summer.app.Constants;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 网络请求
 */

public class Api {
    private static NetService netService;

    public static NetService getNetService() {
        if (netService == null) {
            synchronized (Api.class) {
                if (netService == null) {
                    netService = XApi.getInstance().getRetrofit(Constants.API_GANK_BASE_URL, true).create(NetService.class);
                }
            }
        }
        return netService;
    }
}
