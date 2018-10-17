package com.haobin.watermelon_all_summer.http;

import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.model.WanHttpResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 网络请求链接汇总
 */

public interface WanAndroidService {
    /**
     * 玩安卓，文章列表
     *
     * @param page 页码，从0开始
     */
    @GET("article/list/{page}/json")
    Flowable<WanHttpResult<Article>> getArticleList(@Path("page") int page);
}
