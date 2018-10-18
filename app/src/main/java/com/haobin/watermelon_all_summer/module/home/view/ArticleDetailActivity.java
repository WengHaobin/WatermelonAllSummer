package com.haobin.watermelon_all_summer.module.home.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.app.Constants;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * Created by Wenghaobin
 * on 2018/10/18
 * for 文章详情
 */
public class ArticleDetailActivity extends XActivity {

    @BindView(R.id.tb_article_detail)
    Toolbar tbArticleDetail;
    @BindView(R.id.fl_article_detail)
    FrameLayout flArticleDetail;

    private AgentWeb mAgentWeb;

    @Override
    public void initData(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra(Constants.WAN_ANDROID_URL);
        tbArticleDetail.setTitle(getIntent().getStringExtra(Constants.WAN_ANDROID_NAME));
        setSupportActionBar(tbArticleDetail);
        tbArticleDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setWebView(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView(String url) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(flArticleDetail, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(ContextCompat.getColor(context,R.color.colorPrimary))
                .setMainFrameErrorView(R.layout.webview_error_view, -1)
                .createAgentWeb()
                .ready()
                .go(url);

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
