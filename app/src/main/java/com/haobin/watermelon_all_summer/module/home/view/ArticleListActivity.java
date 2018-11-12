package com.haobin.watermelon_all_summer.module.home.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.view.SimpleRefreshHeaderView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.haobin.watermelon_all_summer.model.Article;
import com.haobin.watermelon_all_summer.module.main.view.WebDetailActivity;
import com.haobin.watermelon_all_summer.module.home.adapter.ArticleAdapter;
import com.haobin.watermelon_all_summer.module.home.presenter.ArticleListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by Wenghaobin
 * on 2018/10/18
 * for 分类文章列表
 */
public class ArticleListActivity extends XActivity<ArticleListPresenter> {

    @BindView(R.id.rv_article_list)
    RecyclerView rvArticleList;
    @BindView(R.id.erl_article_list)
    public EasyRefreshLayout erlArticleList;
    @BindView(R.id.tb_article_list)
    Toolbar tbArticleList;

    private int cid;

    private ArticleAdapter articleAdapter;
    private List<Article.DatasBean> datas = new ArrayList<>();

    public int page = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        cid = getIntent().getIntExtra(Constants.WAN_ANDROID_CID, 0);

        tbArticleList.setTitle(getIntent().getStringExtra(Constants.WAN_ANDROID_TITLE));
        setSupportActionBar(tbArticleList);
        tbArticleList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        erlArticleList.autoRefresh();
        erlArticleList.setRefreshHeadView(new SimpleRefreshHeaderView(context));
        erlArticleList.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getP().loadData(page, cid);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlArticleList != null) {
                            erlArticleList.closeLoadView();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onRefreshing() {
                datas.clear();
                getP().loadData(0, cid);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (erlArticleList != null) {
                            erlArticleList.refreshComplete();
                        }

                    }
                }, 1000);
            }
        });

        rvArticleList.setLayoutManager(new LinearLayoutManager(context));
        articleAdapter = new ArticleAdapter(datas);
        rvArticleList.setAdapter(articleAdapter);
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
        return R.layout.activity_article_list;
    }

    @Override
    public ArticleListPresenter newP() {
        return new ArticleListPresenter();
    }

    public void setData(Article article) {
        datas.addAll(article.getDatas());
        articleAdapter.notifyDataSetChanged();

        page++;
    }
}
