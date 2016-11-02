package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ${Muan} on 2016/10/31.
 * Adapter基类
 */
public abstract class BasisAdapter<E> extends BaseAdapter {
    protected List<E> mList;
    protected Context mContext;
    protected int mViewId;

    public BasisAdapter(List<E> mList, Context mContext, int mViewId) {
        this.mList = mList;
        this.mContext = mContext;
        this.mViewId = mViewId;
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public E getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BasisViewHolder t = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, mViewId, null);
            t = onCreateViewHolder(convertView);
            convertView.setTag(t);
        } else {
            t = (BasisViewHolder) convertView.getTag();
        }
        bingHolder(t, position);
        return convertView;
    }

    /**
     * 实现数据和View绑定
     */
    public abstract void bingHolder(BasisViewHolder basisViewHolder, int position);

    /**
     * 需要的时候创建ViewHolder
     */
    public abstract BasisViewHolder onCreateViewHolder(View view);
}
