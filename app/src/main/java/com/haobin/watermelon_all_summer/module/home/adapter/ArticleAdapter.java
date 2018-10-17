package com.haobin.watermelon_all_summer.module.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.Article;

import java.util.List;

/**
 * Created by Wenghaobin
 * on 2018/10/17
 * for 文章适配器
 */
public class ArticleAdapter extends BaseQuickAdapter<Article.DatasBean, BaseViewHolder> {

    public ArticleAdapter(@Nullable List<Article.DatasBean> data) {
        super(R.layout.adapter_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DatasBean item) {
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_type, item.getChapterName());
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_time, item.getNiceDate());
    }
}
