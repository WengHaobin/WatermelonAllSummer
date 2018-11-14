package com.haobin.watermelon_all_summer.module.welfare.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.view.SimpleRefreshHeaderView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.model.GankHttpResult;
import com.haobin.watermelon_all_summer.module.main.WebDetailActivity;
import com.haobin.watermelon_all_summer.module.welfare.adapter.PhotoListAdapter;
import com.haobin.watermelon_all_summer.module.welfare.presenter.PhotoListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/23
 * for 妹纸图片列表
 */
public class PhotoListFragment extends XFragment<PhotoListPresenter> {

    @BindView(R.id.rv_photo_list)
    RecyclerView rvPhotoList;
    @BindView(R.id.erl_photo_list)
    public EasyRefreshLayout erlPhotoList;

    private PhotoListAdapter photoListAdapter;
    private List<GankHttpResult.ResultsBean> datas = new ArrayList<>();

    public int page = 0;

    @Override
    public void bindEvent() {
        erlPhotoList.autoRefresh();
        erlPhotoList.setRefreshHeadView(new SimpleRefreshHeaderView(context));
        erlPhotoList.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getP().loadData(page);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlPhotoList != null) {
                            erlPhotoList.closeLoadView();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onRefreshing() {
                datas.clear();
                getP().loadData(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlPhotoList != null) {
                            erlPhotoList.refreshComplete();
                        }
                    }
                }, 1000);
            }
        });

        rvPhotoList.setLayoutManager(new GridLayoutManager(context, 2));
        photoListAdapter = new PhotoListAdapter(datas);
        rvPhotoList.setAdapter(photoListAdapter);
        photoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context).to(WebDetailActivity.class)
                        .putString(Constants.WEB_NAME, datas.get(position).getDesc())
                        .putString(Constants.WEB_URL, datas.get(position).getUrl())
                        .launch();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo_list;
    }

    @Override
    public PhotoListPresenter newP() {
        return new PhotoListPresenter();
    }

    public void setData(GankHttpResult result){
        datas.addAll(result.getResults());
        photoListAdapter.notifyDataSetChanged();

        page ++;
    }

}
