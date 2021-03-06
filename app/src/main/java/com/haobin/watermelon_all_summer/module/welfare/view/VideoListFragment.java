package com.haobin.watermelon_all_summer.module.welfare.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.view.SimpleRefreshHeaderView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.model.GankHttpResult;
import com.haobin.watermelon_all_summer.module.main.WebDetailActivity;
import com.haobin.watermelon_all_summer.module.welfare.adapter.VideoListAdapter;
import com.haobin.watermelon_all_summer.module.welfare.presenter.VideoListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/23
 * for 休息视频列表
 */
public class VideoListFragment extends XFragment<VideoListPresenter> {

    @BindView(R.id.rv_video_list)
    RecyclerView rvVideoList;
    @BindView(R.id.erl_video_list)
    public EasyRefreshLayout erlVideoList;

    private VideoListAdapter videoListAdapter;
    private List<GankHttpResult.ResultsBean> datas = new ArrayList<>();

    public int page = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(0);
    }

    @Override
    public void bindEvent() {
        erlVideoList.autoRefresh();
        erlVideoList.setRefreshHeadView(new SimpleRefreshHeaderView(context));
        erlVideoList.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getP().loadData(page);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlVideoList != null) {
                            erlVideoList.closeLoadView();
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
                        if (erlVideoList != null) {
                            erlVideoList.refreshComplete();
                        }
                    }
                }, 1000);
            }
        });

        rvVideoList.setLayoutManager(new LinearLayoutManager(context));
        videoListAdapter = new VideoListAdapter(datas);
        rvVideoList.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
    public int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    public VideoListPresenter newP() {
        return new VideoListPresenter();
    }

    public void setData(GankHttpResult result){
        datas.addAll(result.getResults());
        videoListAdapter.notifyDataSetChanged();

        page ++;
    }
}
