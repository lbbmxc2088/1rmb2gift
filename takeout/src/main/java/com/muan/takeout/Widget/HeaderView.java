package com.muan.takeout.Widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.muan.takeout.R;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 零钱购首页 商标列表上方类别的布局
 */
public class HeaderView extends LinearLayout implements View.OnClickListener {

    //udpate
    @BindView(R.id.rl_banner)
    public RelativeLayout mBannerLayout;
    @BindView(R.id.layout_message_out)
    public LinearLayout mMessageOutLayout;

    @BindView(R.id.layout_message)
    public ViewFlipper mMessageLayout;


    @BindView(R.id.layout_sort_head)
    public LinearLayout sortHeadLayout;

    @BindView(R.id.tv_hot)
    public TextView mTvHot;

    @BindView(R.id.tv_remain)
    public TextView mTvRemain;

    @BindView(R.id.tv_new)
    public TextView mTvNew;

    @BindView(R.id.tv_all_asc)
    public TextView mTvAllAsc;

    @BindView(R.id.tv_all_desc)
    public TextView mTvAllDesc;


    @BindView(R.id.recycleview_icons)
    public RecyclerView mRecycleview;

    public HeadViewClickListener mListener;

    public TextView mLastChooseView;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_headview, HeaderView.this);
        ButterKnife.bind(this, view);
        ViewGroup.LayoutParams layoutParamsRecyclerView = mRecycleview.getLayoutParams();
        layoutParamsRecyclerView.height = WindowUtils.getWIndowWidth(getContext()) / 4;
        layoutParamsRecyclerView.width = LayoutParams.MATCH_PARENT;
        mRecycleview.setLayoutParams(layoutParamsRecyclerView);
        mLastChooseView = mTvHot;

    }

    @OnClick({R.id.layout_message_out, R.id.tv_hot, R.id.tv_remain, R.id.tv_new, R.id.tv_all_asc, R.id.tv_all_desc,})
    public void onClick(View v) {
        if (v == mTvAllAsc) {
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.theme_text_gray));
            mLastChooseView = mTvAllAsc;
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.main_red));
        } else if (v == mTvAllDesc) {
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.theme_text_gray));
            mLastChooseView = mTvAllDesc;
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.main_red));
        } else if (v == mTvHot) {
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.theme_text_gray));
            mLastChooseView = mTvHot;
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.main_red));
        } else if (v == mTvNew) {
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.theme_text_gray));
            mLastChooseView = mTvNew;
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.main_red));
        } else if (v == mTvRemain) {
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.theme_text_gray));
            mLastChooseView = mTvRemain;
            mLastChooseView.setTextColor(CommonUtils.getColor(getContext(), R.color.main_red));
        }
        if (mListener != null) {
            mListener.headViewClickListener(v);
        }
    }

    public void setHeadViewListener(HeadViewClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 本界面点击事件
     */
    public interface HeadViewClickListener {
        public void headViewClickListener(View v);
    }
}