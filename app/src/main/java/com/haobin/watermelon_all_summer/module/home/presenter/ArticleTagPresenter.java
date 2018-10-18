package com.haobin.watermelon_all_summer.module.home.presenter;

import com.haobin.watermelon_all_summer.http.Api;
import com.haobin.watermelon_all_summer.model.ArticleTag;
import com.haobin.watermelon_all_summer.model.WanHttpResult;
import com.haobin.watermelon_all_summer.module.home.view.ArticleTagFragment;
import com.haobin.watermelon_all_summer.utils.ToastUtils;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/18
 * for
 */
public class ArticleTagPresenter extends XPresent<ArticleTagFragment> {
    public void loadData(){
        Api.getWanAndroidService().getProjectTagList()
                .compose(XApi.<WanHttpResult<List<ArticleTag>>>getApiTransformer())
                .compose(XApi.<WanHttpResult<List<ArticleTag>>> getScheduler())
                .compose(getV().<WanHttpResult<List<ArticleTag>>> bindToLifecycle())
                .subscribe(new ApiSubscriber<WanHttpResult<List<ArticleTag>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("服务器出错，请稍后再试");
                    }
                    @Override
                    public void onNext(WanHttpResult<List<ArticleTag>> result) {
                        if (result.getErrorCode()==0){
                            getV().setData(result.getData());
                        }else {
                            ToastUtils.showToast(result.getErrorMsg());
                        }
                    }
                });
    }
}
