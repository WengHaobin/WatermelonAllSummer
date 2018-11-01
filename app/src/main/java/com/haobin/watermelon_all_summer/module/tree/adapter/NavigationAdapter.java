package com.haobin.watermelon_all_summer.module.tree.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;

    public NavigationAdapter(List<Tree> data, RecyclerView mRecyclerView) {
        super(R.layout.adapter_navigation, data);
        recyclerView = mRecyclerView;
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

    /**
     * 获取被选中的位置，将选中项移动到中间，并刷新
     */
    public void getSelectedPosition(int selectedPosition) {
        moveToMiddle(selectedPosition);
        setSelected(selectedPosition);
        notifyDataSetChanged();
    }

    /**
     * 将选中项移动到中间位置的方法
     */
    private void moveToMiddle(int position) {
        //先从RecyclerView的LayoutManager中获取当前第一项和最后一项的Position
        int firstItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        //中间位置
        int middle = (firstItem + lastItem)/2;
        // 取绝对值，index下标是当前的位置和中间位置的差，下标为index的view的top就是需要滑动的距离
        int index = (position - middle) >= 0 ? position - middle : -(position - middle);
        //左侧列表一共有12个Item，如果>这个值会返回null，程序崩溃，如果>12直接滑到指定位置,或者getChildCount,都一样啦
        if (index >= recyclerView.getChildCount()) {
            recyclerView.scrollToPosition(position);
        } else {
            //如果当前位置在中间位置上面，往下移动，这里为了防止越界
            if(position < middle) {
                recyclerView.scrollBy(0, - recyclerView.getChildAt(index).getTop());
                // 在中间位置的下面，往上移动
            } else {
                recyclerView.scrollBy(0, recyclerView.getChildAt(index).getTop());
            }
        }
    }
}
