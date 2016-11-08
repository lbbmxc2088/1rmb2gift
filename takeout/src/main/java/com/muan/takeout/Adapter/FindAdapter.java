package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.muan.takeout.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/4 16:31
 */
public class FindAdapter extends BasisAdapter {

    public FindAdapter(List mList, Context mContext) {
        super(mList, mContext, R.layout.item_find);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder holder = (ViewHolder) basisViewHolder;
        if (position == 0) {
            holder.mTv_find_seat.setVisibility(View.VISIBLE);
        } else {
            holder.mTv_find_seat.setVisibility(View.GONE);
        }

    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BasisViewHolder {
        @BindView(R.id.tv_find_seat)
        TextView mTv_find_seat;
        @BindView(R.id.iv_find_img)
        ImageView mIv_find_img;
        @BindView(R.id.tv_find_title)
        TextView mTv_find_title;
        @BindView(R.id.tv_find_content)
        TextView mTv_find_content;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
