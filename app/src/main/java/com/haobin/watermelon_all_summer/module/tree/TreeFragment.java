package com.haobin.watermelon_all_summer.module.tree;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.Tree;
import com.haobin.watermelon_all_summer.module.tree.adapter.NavigationAdapter;
import com.haobin.watermelon_all_summer.module.tree.adapter.NavigationDetailAdapter;
import com.haobin.watermelon_all_summer.module.tree.presenter.TreePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 体系
 */
public class TreeFragment extends XFragment<TreePresenter>{

    @BindView(R.id.rv_navigation)
    RecyclerView rvNavigation;
    @BindView(R.id.rv_navigation_detail)
    RecyclerView rvNavigationDetail;

    private NavigationAdapter navigationAdapter;
    private NavigationDetailAdapter navigationDetailAdapter;
    private List<Tree> trees = new ArrayList<>();

    private int oldPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    public TreePresenter newP() {
        return new TreePresenter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData();
    }

    @Override
    public void bindEvent() {
        LinearLayoutManager lmNavigation = new LinearLayoutManager(context);
        rvNavigation.setLayoutManager(lmNavigation);
        navigationAdapter = new NavigationAdapter(trees);
        rvNavigation.setAdapter(navigationAdapter);

        final LinearLayoutManager lmNavigationDetail = new LinearLayoutManager(context);
        rvNavigationDetail.setLayoutManager(lmNavigationDetail);
        navigationDetailAdapter = new NavigationDetailAdapter(context, trees);
        rvNavigationDetail.setAdapter(navigationDetailAdapter);

        navigationAdapter.setOnSelectListener(new NavigationAdapter.OnSelectListener() {
            @Override
            public void onSelected(int position) {
                if (position <= oldPosition) {
                    rvNavigationDetail.smoothScrollToPosition(position);
                } else {
                    lmNavigationDetail.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        rvNavigationDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentPosition = lmNavigationDetail.findFirstVisibleItemPosition();
                //TODO item错位
                if (oldPosition != currentPosition) {
                    rvNavigation.smoothScrollToPosition(currentPosition);
                    navigationAdapter.setSelected(currentPosition);
                    oldPosition = currentPosition;
                }
            }
        });
    }

    public void setData(List<Tree> data){
        trees.addAll(data);
        navigationAdapter.notifyDataSetChanged();
        navigationAdapter.setSelected(0);

        navigationDetailAdapter.notifyDataSetChanged();
    }
}
