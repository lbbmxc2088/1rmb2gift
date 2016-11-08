package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;

import com.muan.takeout.Model.AddressEntity;
import com.muan.takeout.R;

import java.util.List;

/**
 * Created by ${Muan} on 2016/11/7 14:09
 */
public class AddressAdapter extends BasisAdapter<AddressEntity> {
    public AddressAdapter(List<AddressEntity> mList, Context mContext) {
        super(mList, mContext, R.layout.item_address);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {

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
