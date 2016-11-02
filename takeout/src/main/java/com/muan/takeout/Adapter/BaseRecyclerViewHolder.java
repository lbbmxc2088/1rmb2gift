package com.muan.takeout.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import butterknife.ButterKnife;


/**
 *
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    //是否是itemView,其他的为自定义的头部或者尾部的刷新时的页面。
    public BaseRecyclerViewHolder(View itemView, boolean isItem) {
        super(itemView);
        if (isItem) {
            ButterKnife.bind(this, itemView);
        }
    }

    //不使用下拉刷新的recycleview使用的
    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
