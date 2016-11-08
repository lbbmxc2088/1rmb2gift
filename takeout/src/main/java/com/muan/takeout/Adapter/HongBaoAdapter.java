package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;

import com.muan.takeout.R;

import java.util.List;

/**
 * Created by ${Muan} on 2016/11/4 11:15
 */
public class HongBaoAdapter extends BasisAdapter {
    public HongBaoAdapter(List mList, Context mContext) {
        super(mList, mContext, R.layout.item_hongbao);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder holder = (ViewHolder) basisViewHolder;


    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BasisViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }
}
