package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.muan.takeout.Model.GoodsEntity;
import com.muan.takeout.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/6/6.
 */
public class GoodsAdapter extends BasisAdapter<GoodsEntity> implements View.OnClickListener {
    private ImageLoader mLoader;

    public GoodsAdapter(List<GoodsEntity> mList, Context mContext, ImageLoader mLoader) {
        super(mList, mContext, R.layout.item_goods_view);
        this.mLoader = mLoader;
    }

    @Override
    public void bingHolder(BasisViewHolder fastViewHolder, int position) {
        ViewHolder viewHolder = (ViewHolder) fastViewHolder;
        viewHolder.mTvName.setText(mList.get(position).getGoods_name());
        int mTotal = 100;
        int mRemain = 50;
        try {
            mTotal = Integer.parseInt(mList.get(position).getCopies());
            mRemain = Integer.parseInt(mList.get(position).getSell_copies());
        } catch (Exception e) {

        }
        viewHolder.mTvPrize.setText(mTotal + "");
        viewHolder.mProgressShopStatus.setMax(mTotal);
        viewHolder.mProgressShopStatus.setProgress(mRemain);
        viewHolder.mTvStatus.setText((int) ((mRemain + 0f) / mTotal * 100) + "%");

    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onClick(View v) {
    }

    class ViewHolder extends BasisViewHolder {


        @BindView(R.id.iv_goods)
        public ImageView mIvGoods;

        @BindView(R.id.tv_add_shopcart)
        public ImageView mIvAddShopCart;

        @BindView(R.id.tv_status)
        public TextView mTvStatus;

        @BindView(R.id.tv_prize)
        public TextView mTvPrize;

        @BindView(R.id.tv_name)
        public TextView mTvName;

        @BindView(R.id.progress_shop_statis)
        public ProgressBar mProgressShopStatus;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
