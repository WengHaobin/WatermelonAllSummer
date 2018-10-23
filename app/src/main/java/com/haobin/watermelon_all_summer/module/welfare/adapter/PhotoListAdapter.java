package com.haobin.watermelon_all_summer.module.welfare.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haobin.watermelon_all_summer.R;
import com.haobin.watermelon_all_summer.model.GankHttpResult;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * Created by Wenghaobin
 * on 2018/10/23
 * for
 */
public class PhotoListAdapter extends BaseQuickAdapter<GankHttpResult.ResultsBean, BaseViewHolder> {

    public PhotoListAdapter(@Nullable List<GankHttpResult.ResultsBean> data) {
        super(R.layout.adapter_photo_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankHttpResult.ResultsBean item) {
        ImageView imageView = helper.getView(R.id.img_meizhi);
        ILFactory.getLoader().loadNet(imageView, item.getUrl(), null);
    }
}
