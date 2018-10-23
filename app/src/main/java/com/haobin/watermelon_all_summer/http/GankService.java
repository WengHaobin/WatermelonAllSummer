package com.haobin.watermelon_all_summer.http;

import com.haobin.watermelon_all_summer.model.GankHttpResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 网络请求链接汇总
 */

public interface GankService {
    // 获取休息视频列表
    @GET("休息视频/10/{page}")
    Flowable<GankHttpResult> getVideoList(@Path("page") int page);

    // 获取福利列表
    @GET("福利/10/{page}")
    Flowable<GankHttpResult> getMeiZiList(@Path("page") int page);
}
