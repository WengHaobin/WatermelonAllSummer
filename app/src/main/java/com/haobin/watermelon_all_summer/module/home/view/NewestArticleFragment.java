package com.haobin.watermelon_all_summer.module.home.view;

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
import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.module.main.WebDetailActivity;
import com.haobin.watermelon_all_summer.module.home.adapter.ArticleAdapter;
import com.haobin.watermelon_all_summer.module.home.presenter.NewestArticlePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 文章
 */
public class NewestArticleFragment extends XFragment<NewestArticlePresenter> {

    @BindView(R.id.rv_article)
    RecyclerView rvArticle;
    @BindView(R.id.erl_article)
    public EasyRefreshLayout erlArticle;

    private ArticleAdapter articleAdapter;
    private List<Article.DatasBean> datas = new ArrayList<>();

    public int page = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(0);
    }

    @Override
    public void bindEvent() {
        erlArticle.autoRefresh();
        erlArticle.setRefreshHeadView(new SimpleRefreshHeaderView(context));
        erlArticle.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getP().loadData(page);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlArticle != null) {
                            erlArticle.closeLoadView();
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
                        if (erlArticle != null) {
                            erlArticle.refreshComplete();
                        }
                    }
                }, 1000);
            }
        });

        rvArticle.setLayoutManager(new LinearLayoutManager(context));
        articleAdapter = new ArticleAdapter(datas);
        rvArticle.setAdapter(articleAdapter);
        articleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context).to(WebDetailActivity.class)
                        .putString(Constants.WEB_URL, datas.get(position).getLink())
                        .putString(Constants.WEB_NAME, datas.get(position).getTitle())
                        .launch();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    public NewestArticlePresenter newP() {
        return new NewestArticlePresenter();
    }

    public void setData(Article article) {
        datas.addAll(article.getDatas());
        articleAdapter.notifyDataSetChanged();

        page ++;
    }
}
