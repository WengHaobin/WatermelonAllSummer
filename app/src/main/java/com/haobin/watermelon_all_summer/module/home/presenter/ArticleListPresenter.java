package com.haobin.watermelon_all_summer.module.home.presenter;

import com.haobin.watermelon_all_summer.http.Api;
import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.model.WanHttpResult;
import com.haobin.watermelon_all_summer.module.home.view.ArticleListActivity;
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
public class ArticleListPresenter extends XPresent<ArticleListActivity> {

    public void loadData(final int page, int cid){
        Api.getWanAndroidService().getProjectList(page, cid)
                .compose(XApi.<WanHttpResult<Article>>getApiTransformer())
                .compose(XApi.<WanHttpResult<Article>> getScheduler())
                .compose(getV().<WanHttpResult<Article>> bindToLifecycle())
                .subscribe(new ApiSubscriber<WanHttpResult<Article>>() {
                    @Override
                    protected void onFail(NetError error) {
                        if (page == 0){
                            getV().erlArticleList.refreshComplete();
                        }else {
                            getV().erlArticleList.loadMoreComplete();
                        }
                        ToastUtils.showToast("服务器出错，请稍后再试");
                    }
                    @Override
                    public void onNext(WanHttpResult<Article> result) {
                        if (result.getErrorCode()==0){
                            getV().setData(result.getData());
                        }else {
                            if (page == 0){
                                getV().erlArticleList.refreshComplete();
                            }else {
                                getV().erlArticleList.loadMoreComplete();
                            }
                            ToastUtils.showToast(result.getErrorMsg());
                        }
                    }
                });
    }
}
