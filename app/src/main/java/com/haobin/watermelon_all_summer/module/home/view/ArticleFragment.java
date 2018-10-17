package com.haobin.watermelon_all_summer.module.home.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.view.SimpleRefreshHeaderView;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.module.home.adapter.ArticleAdapter;
import com.haobin.watermelon_all_summer.module.home.presenter.ArticlePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 文章
 */
public class ArticleFragment extends XFragment<ArticlePresenter> {

    @BindView(R.id.rv_article)
    RecyclerView rvArticle;
    @BindView(R.id.erl_article)
    public EasyRefreshLayout erlArticle;

    private ArticleAdapter articleAdapter;
    private List<Article.DatasBean> datas = new ArrayList<>();

    public int page = 0;

    @Override
    public void bindEvent() {
        super.bindEvent();
        erlArticle.autoRefresh();
        erlArticle.setRefreshHeadView(new SimpleRefreshHeaderView(context));
        erlArticle.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getP().loadData(page);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        erlArticle.closeLoadView();
                    }
                }, 1000);
            }

            @Override
            public void onRefreshing() {
                getP().loadData(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        erlArticle.refreshComplete();
                    }
                }, 1000);
            }
        });

        rvArticle.setLayoutManager(new LinearLayoutManager(context));
        articleAdapter = new ArticleAdapter(datas);
        rvArticle.setAdapter(articleAdapter);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    public ArticlePresenter newP() {
        return new ArticlePresenter();
    }

    public void setData(Article article) {
        articleAdapter.addData(article.getDatas());
        articleAdapter.notifyDataSetChanged();

        page ++;
    }
}
