package com.haobin.watermelon_all_summer.module.home.presenter;

import com.haobin.watermelon_all_summer.http.Api;
import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.model.WanHttpResult;
import com.haobin.watermelon_all_summer.module.home.view.ArticleFragment;
import com.haobin.watermelon_all_summer.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for
 */
public class ArticlePresenter extends XPresent<ArticleFragment> {

    public void loadData(final int page){
        Api.getWanAndroidService().getArticleList(page)
                .compose(XApi.<WanHttpResult<Article>>getApiTransformer())
                .compose(XApi.<WanHttpResult<Article>> getScheduler())
                .compose(getV().<WanHttpResult<Article>> bindToLifecycle())
                .subscribe(new ApiSubscriber<WanHttpResult<Article>>() {
                    @Override
                    protected void onFail(NetError error) {
                        if (page == 0){
                            getV().erlArticle.refreshComplete();
                        }else {
                            getV().erlArticle.loadMoreComplete();
                        }
                        ToastUtils.showToast("服务器出错，请稍后再试");
                    }
                    @Override
                    public void onNext(WanHttpResult<Article> result) {
                        if (result.getErrorCode()==0){
                            getV().setData(result.getData());
                        }else {
                            if (page == 0){
                                getV().erlArticle.refreshComplete();
                            }else {
                                getV().erlArticle.loadMoreComplete();
                            }
                            ToastUtils.showToast(result.getErrorMsg());
                        }
                    }
                });
    }
}
