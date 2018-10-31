package com.haobin.watermelon_all_summer.module.tree.presenter;

import com.haobin.watermelon_all_summer.http.Api;
import com.haobin.watermelon_all_summer.model.Tree;
import com.haobin.watermelon_all_summer.model.WanHttpResult;
import com.haobin.watermelon_all_summer.module.tree.TreeFragment;
import com.haobin.watermelon_all_summer.utils.ToastUtils;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/30
 * for
 */
public class TreePresenter extends XPresent<TreeFragment> {

    public void loadData(){
        Api.getWanAndroidService().getTreeList()
                .compose(XApi.<WanHttpResult<List<Tree>>>getApiTransformer())
                .compose(XApi.<WanHttpResult<List<Tree>>> getScheduler())
                .compose(getV().<WanHttpResult<List<Tree>>> bindToLifecycle())
                .subscribe(new ApiSubscriber<WanHttpResult<List<Tree>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showToast("服务器出错，请稍后再试");
                    }
                    @Override
                    public void onNext(WanHttpResult<List<Tree>> result) {
                        if (result.getErrorCode()==0){
                            getV().setData(result.getData());
                        }else {
                            ToastUtils.showToast(result.getErrorMsg());
                        }
                    }
                });
    }
}
