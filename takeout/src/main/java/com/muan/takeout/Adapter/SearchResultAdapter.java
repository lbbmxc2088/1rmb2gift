package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.muan.takeout.Model.GoodsEntity;
import com.muan.takeout.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/1 15:01
 */
public class SearchResultAdapter extends BasisAdapter<GoodsEntity> {

    public SearchResultAdapter(List<GoodsEntity> mList, Context mContext) {
        super(mList, mContext, R.layout.item_goods_view);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder viewHolder = (ViewHolder) basisViewHolder;
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
