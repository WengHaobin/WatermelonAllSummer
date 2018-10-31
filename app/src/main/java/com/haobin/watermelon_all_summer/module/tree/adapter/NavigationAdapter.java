package com.haobin.watermelon_all_summer.module.tree.adapter;

import android.view.View;
import android.widget.TextView;

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
public class NavigationAdapter extends BaseQuickAdapter<Tree, BaseViewHolder> {

    public NavigationAdapter(List<Tree> data) {
        super(R.layout.adapter_navigation, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Tree item) {
        final TextView tvNavigationTitle = helper.getView(R.id.tv_navigation_title);
        tvNavigationTitle.setSelected(item.isSelected());
        tvNavigationTitle.setText(item.getName());
        tvNavigationTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNavigationTitle.setSelected(true);
                setSelected(helper.getLayoutPosition());
                if (listener != null) {
                    listener.onSelected(helper.getLayoutPosition());
                }
            }
        });
    }

    public void setSelected(int position) {
        List<Tree> data = getData();
        for (int i = 0; i < data.size(); i++) {
            if (i == position) {
                data.get(i).setSelected(true);
            } else {
                data.get(i).setSelected(false);
            }
        }
        notifyDataSetChanged();
    }

    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }
}
