package com.haobin.watermelon_all_summer.http;

import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.model.ArticleTag;
import com.haobin.watermelon_all_summer.model.Tree;
import com.haobin.watermelon_all_summer.model.WanHttpResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * for 网络请求链接汇总
 */

public interface WanAndroidService {
    /**
     * 玩安卓，文章列表
     * @param page 页码，从0开始
     */
    @GET("article/list/{page}/json")
    Flowable<WanHttpResult<Article>> getArticleList(@Path("page") int page);

    /**
     * 玩安卓，标签列表
     */
    @GET("project/tree/json")
    Flowable<WanHttpResult<List<ArticleTag>>> getProjectTagList();

    /**
     * 玩安卓，分类项目列表
     * @param page 页码，从0开始
     */
    @GET("article/list/{page}/json")
    Flowable<WanHttpResult<Article>> getProjectList(@Path("page") int page,
                                                    @Query("cid") int cid);

    /**
     * 玩安卓，体系数据列表
     */
    @GET("tree/json")
    Flowable<WanHttpResult<List<Tree>>> getTreeList();

}
