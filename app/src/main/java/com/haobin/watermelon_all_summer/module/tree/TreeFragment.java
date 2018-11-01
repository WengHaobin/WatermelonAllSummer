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
        rvNavigation.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        navigationAdapter = new NavigationAdapter(trees, rvNavigation);
        rvNavigation.setAdapter(navigationAdapter);

        rvNavigationDetail.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        navigationDetailAdapter = new NavigationDetailAdapter(context, trees, rvNavigationDetail);
        rvNavigationDetail.setAdapter(navigationDetailAdapter);

        navigationAdapter.setOnSelectListener(new NavigationAdapter.OnSelectListener() {
            @Override
            public void onSelected(int position) {
                navigationAdapter.getSelectedPosition(position);
                navigationDetailAdapter.getSelectedPosition(position);
            }
        });

        rvNavigationDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取右侧列表的第一个可见Item的position
                int TopPosition = ((LinearLayoutManager) rvNavigationDetail.getLayoutManager()).findFirstVisibleItemPosition();
                //左侧得到这个position
                navigationAdapter.getSelectedPosition(TopPosition);
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
