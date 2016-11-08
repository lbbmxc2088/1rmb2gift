package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.muan.takeout.Model.RecomEntity;
import com.muan.takeout.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/4 09:27
 * 猜你喜欢适配器
 */
public class RecomAdapter extends BaseRecyclerView<RecomEntity, RecomAdapter.RecomViewHolder> {
    public RecomAdapter(List<RecomEntity> mList, Context mContext) {
        super(mList, mContext, R.layout.item_recom);
    }

    @Override
    public RecomViewHolder createViewHolder(View v) {
        return new RecomViewHolder(v);
    }

    @Override
    public void baseOnBingViewHolder(RecomViewHolder holder, int position, RecomEntity recomEntity) {
        holder.mIv_recom.setImageResource(R.mipmap.icon_app);
        holder.mTv_recom.setText(mList.get(position).getGoodsName());
        holder.mProgressbar.setMax(mList.get(position).getMaxtimes());
        holder.mProgressbar.setProgress(mList.get(position).getCurrenttimes());

    }


    class RecomViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_recom_img)
        ImageView mIv_recom;
        @BindView(R.id.tv_recom_name)
        TextView mTv_recom;
        @BindView(R.id.progress_shop_status)
        ProgressBar mProgressbar;

        public RecomViewHolder(View view) {
            super(view);
        }
    }
}
