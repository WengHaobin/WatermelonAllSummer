package com.haobin.watermelon_all_summer.module.tree.adapter;

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
public class NavigationDetailContentAdapter extends BaseQuickAdapter<Tree.Children, BaseViewHolder> {

    public NavigationDetailContentAdapter(List<Tree.Children> data) {
        super(R.layout.adapter_navigation_detail_content,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Tree.Children item) {
        helper.setText(R.id.tv_navigation_detail_content, item.getName());
    }
}
