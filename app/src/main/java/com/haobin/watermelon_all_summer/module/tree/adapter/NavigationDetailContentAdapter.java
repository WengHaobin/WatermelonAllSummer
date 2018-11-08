package com.haobin.watermelon_all_summer.module.tree.adapter;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.model.Tree;
import com.haobin.watermelon_all_summer.module.home.view.ArticleListActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/30
 * for
 */
public class NavigationDetailContentAdapter extends BaseQuickAdapter<Tree.Children, BaseViewHolder> {

    private Activity context;

    public NavigationDetailContentAdapter(Activity mContext, List<Tree.Children> data) {
        super(R.layout.adapter_navigation_detail_content,data);
        context = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Tree.Children item) {
        helper.setText(R.id.tv_navigation_detail_content, item.getName());

        helper.getView(R.id.tv_navigation_detail_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(context).to(ArticleListActivity.class)
                        .putInt(Constants.WAN_ANDROID_CID, item.getId())
                        .putString(Constants.WAN_ANDROID_TITLE, item.getName()).launch();
            }
        });

    }
}
