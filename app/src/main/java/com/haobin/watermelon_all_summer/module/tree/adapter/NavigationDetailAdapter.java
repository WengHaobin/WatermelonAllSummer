package com.haobin.watermelon_all_summer.module.tree.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.Tree;

import java.util.List;

/**
 * Created by Wenghaobin
 * on 2018/10/30
 * for
 */
public class NavigationDetailAdapter extends BaseQuickAdapter<Tree, BaseViewHolder> {

    private Activity context;
    private RecyclerView recyclerView;

    public NavigationDetailAdapter(Activity mContext, List<Tree> data, RecyclerView mRecyclerView) {
        super(R.layout.adapter_navigation_detail, data);
        context = mContext;
        recyclerView = mRecyclerView;
    }

    @Override
    protected void convert(BaseViewHolder helper, Tree item) {
        helper.setText(R.id.tv_navigation_detail_title, item.getName());

        RecyclerView rvNavigationDetailContent = helper.getView(R.id.rv_navigation_detail_content);
        rvNavigationDetailContent.setLayoutManager(new GridLayoutManager(context, 2));
        NavigationDetailContentAdapter navigationDetailContentAdapter = new NavigationDetailContentAdapter(context, item.getChildren());
        rvNavigationDetailContent.setAdapter(navigationDetailContentAdapter);
    }

    /**
     * 获取被选中的位置，将选中项移动到顶部，并刷新
     */
    public void getSelectedPosition(int selectedPosition) {
        //调用移动位置的方法,直接移动到顶部第一个位置
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(selectedPosition, 0);
        //刷新
        notifyDataSetChanged();
    }

}
