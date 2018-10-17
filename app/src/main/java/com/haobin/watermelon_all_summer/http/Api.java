package com.haobin.watermelon_all_summer.http;

import com.haobin.watermelon_all_summer.app.Constants;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 网络请求
 */

public class Api {
    private static WanAndroidService wanAndroidService;
    private static GankService gankService;

    public static WanAndroidService getWanAndroidService() {
        if (wanAndroidService == null) {
            synchronized (Api.class) {
                if (wanAndroidService == null) {
                    wanAndroidService = XApi.getInstance().getRetrofit(Constants.API_WAN_ANDROID, true).create(WanAndroidService.class);
                }
            }
        }
        return wanAndroidService;
    }

    public static GankService getGankService() {
        if (gankService == null) {
            synchronized (Api.class) {
                if (gankService == null) {
                    gankService = XApi.getInstance().getRetrofit(Constants.API_GANK, true).create(GankService.class);
                }
            }
        }
        return gankService;
    }
}
