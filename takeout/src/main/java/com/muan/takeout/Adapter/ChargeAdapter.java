package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.muan.takeout.Model.ChargeEntity;
import com.muan.takeout.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/7 15:05
 */
public class ChargeAdapter extends BasisAdapter<ChargeEntity> {


    public ChargeAdapter(List mList, Context mContext) {
        super(mList, mContext, R.layout.item_charge);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder holder = (ViewHolder) basisViewHolder;
        holder.tvChargenum.setText("¥" + mList.get(position).getCharge_num());
        holder.tvChargetime.setText(mList.get(position).getCharge_time());
        holder.tvChargeway.setText(mList.get(position).getCharge_way());
    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BasisViewHolder {
        @BindView(R.id.tv_chargeway)
        TextView tvChargeway;
        @BindView(R.id.tv_chargetime)
        TextView tvChargetime;
        @BindView(R.id.tv_chargenum)
        TextView tvChargenum;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
