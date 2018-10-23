package com.haobin.watermelon_all_summer.module.welfare.presenter;

import com.haobin.watermelon_all_summer.http.Api;
import com.haobin.watermelon_all_summer.model.GankHttpResult;
import com.haobin.watermelon_all_summer.module.welfare.view.PhotoListFragment;
import com.haobin.watermelon_all_summer.utils.ToastUtils;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/23
 * for
 */
public class PhotoListPresenter extends XPresent<PhotoListFragment> {
    public void loadData(final int page){
        Api.getGankService().getMeiZiList(page)
                .compose(XApi.<GankHttpResult>getApiTransformer())
                .compose(XApi.<GankHttpResult> getScheduler())
                .compose(getV().<GankHttpResult> bindToLifecycle())
                .subscribe(new ApiSubscriber<GankHttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        if (page == 0){
                            getV().erlPhotoList.refreshComplete();
                        }else {
                            getV().erlPhotoList.loadMoreComplete();
                        }
                        ToastUtils.showToast("服务器出错，请稍后再试");
                    }
                    @Override
                    public void onNext(GankHttpResult result) {
                        if (!result.isError()){
                            getV().setData(result);
                        }else {
                            if (page == 0){
                                getV().erlPhotoList.refreshComplete();
                            }else {
                                getV().erlPhotoList.loadMoreComplete();
                            }
                            ToastUtils.showToast(result.getErrorMsg());
                        }
                    }
                });
    }
}
