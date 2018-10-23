package com.haobin.watermelon_all_summer.module.welfare.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.GankHttpResult;

import java.util.List;

/**
 * Created by Wenghaobin
 * on 2018/10/23
 * for
 */
public class VideoListAdapter extends BaseQuickAdapter<GankHttpResult.ResultsBean, BaseViewHolder> {

    public VideoListAdapter(@Nullable List<GankHttpResult.ResultsBean> data) {
        super(R.layout.adapter_video_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankHttpResult.ResultsBean item) {
        helper.setText(R.id.tv_video_name, item.getDesc());
        helper.setText(R.id.tv_video_suggester, item.getWho());
    }
}
