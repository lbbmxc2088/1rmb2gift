package com.muan.takeout.Adapter;

import android.view.View;


import butterknife.ButterKnife;


/**
 * Created by ${Muan} on 2016/6/1.
 */
public class BasisViewHolder {
    public View mRootView;

    public BasisViewHolder(View view) {
        mRootView = view;
        ButterKnife.bind(this, view);
    }
}
