package com.haobin.watermelon_all_summer.module.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.model.ArticleTag;
import com.haobin.watermelon_all_summer.module.home.presenter.ArticleTagPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 项目
 */
public class ArticleTagFragment extends XFragment<ArticleTagPresenter> {

    @BindView(R.id.tfl_article_tag)
    TagFlowLayout tflArticleTag;

    private List<ArticleTag> articleTags = new ArrayList<>();
    private TagAdapter tagAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        tagAdapter = new TagAdapter(articleTags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) mInflater.inflate(R.layout.adapter_article_tag, tflArticleTag, false);
                tv.setText(articleTags.get(position).getName());
                return tv;
            }
        };
        tflArticleTag.setAdapter(tagAdapter);
        tflArticleTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Router.newIntent(context).to(ArticleListActivity.class)
                        .putInt(Constants.WAN_ANDROID_CID, articleTags.get(position).getId())
                        .putString(Constants.WAN_ANDROID_TITLE, articleTags.get(position).getName()).launch();
                return true;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getP().loadData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_article_tag;
    }

    @Override
    public ArticleTagPresenter newP() {
        return new ArticleTagPresenter();
    }

    public void setData(List<ArticleTag> datas){
        articleTags.addAll(datas);
        tagAdapter.notifyDataChanged();
    }
}
