package com.haobin.watermelon_all_summer.module.tree.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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

    private Context context;

    public NavigationDetailAdapter(Context mContext, List<Tree> data) {
        super(R.layout.adapter_navigation_detail,data);
        context = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, Tree item) {
        helper.setText(R.id.tv_navigation_detail_title, item.getName());

        RecyclerView rvNavigationDetailContent = helper.getView(R.id.rv_navigation_detail_content);
        rvNavigationDetailContent.setLayoutManager(new GridLayoutManager(context,2));
        NavigationDetailContentAdapter navigationDetailContentAdapter = new NavigationDetailContentAdapter(item.getChildren());
        rvNavigationDetailContent.setAdapter(navigationDetailContentAdapter);
    }
}
