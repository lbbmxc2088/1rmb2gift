package com.muan.takeout.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.HistoryAdapter;
import com.muan.takeout.Model.HistoryEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.MoveBtnAinmation;
import com.muan.takeout.Utils.WindowUtils;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.muan.takeout.Utils.volley.ParseUtils;
import com.muan.takeout.Widget.RecomView;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:59
 * 我的—全部夺宝记录
 */
public class AllRecordActivity extends BaseActivity {
    @BindView(R.id.title_back)
    TextView title_back;
    @BindView(R.id.title_middle)
    TextView title_middle;

    @BindView(R.id.tv_record_all)
    TextView mTv_RecordAll;
    @BindView(R.id.tv_myhistory_ing)
    TextView mTv_MyhistoryIng;
    @BindView(R.id.tv_myhitory_end)
    TextView mTv_MyhitoryEnd;
    @BindView(R.id.view_myhistory_move)
    View mMoveView;
    @BindView(R.id.lv_myhistory)
    ListView mLvMyhistory;
    @BindView(R.id.xrfv_myhistory)
    XRefreshView mXrefreshview;

    @BindView(R.id.ll_allrecord_nodata)
    LinearLayout mLl_historyNodata;

    private TextView mLastView;
    private RecomView mRecomView;

    List<HistoryEntity> mList_all = new ArrayList<>();
    List<HistoryEntity> mList_end = new ArrayList<>();
    List<HistoryEntity> mList_ing = new ArrayList<>();
    HistoryAdapter mAdapter_all;
    HistoryAdapter mAdapter_end;
    HistoryAdapter mAdapter_ing;

    @Override
    public void initData() {
        mAdapter_all = new HistoryAdapter(mList_all, this);
        mAdapter_end = new HistoryAdapter(mList_all, this);
        mAdapter_ing = new HistoryAdapter(mList_all, this);
        for (int i = 0; i < 10; i++) {
            HistoryEntity entity = new HistoryEntity();
            entity.setGoodsName("iphone7");
            entity.setGoodsQihao("137");
            entity.setGoodsTimes("12");
            entity.setWinnerName("参与者" + i);
            entity.setWinTimes("3次");
            mList_all.add(entity);
        }
        mLvMyhistory.setAdapter(mAdapter_all);
    }

    @Override
    public void initView() {
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mMoveView.getLayoutParams();
        layoutParams1.width = WindowUtils.getWIndowWidth(mActivity) / 3;
        layoutParams1.height = FrameLayout.LayoutParams.MATCH_PARENT;
        mMoveView.setLayoutParams(layoutParams1);
        mRecomView = new RecomView(this);
        mLl_historyNodata.addView(mRecomView);
        mXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXrefreshview.stopRefresh();
                    }
                }, FinalTools.REFRESH_DELAYTIME);

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXrefreshview.stopLoadMore();
                    }
                }, FinalTools.REFRESH_DELAYTIME);
            }
        });
        switch (getIntent().getIntExtra("type", FinalTools.RECORD_TYPE_ALL)) {
            case FinalTools.RECORD_TYPE_ALL:
                mLastView = mTv_RecordAll;
                title_middle.setText("全部购买记录");
                getAllData();
                break;
            case FinalTools.RECORD_TYPE_ING:
                mLastView = mTv_MyhistoryIng;
                title_middle.setText("进行中");
                getIngData();
                break;
            case FinalTools.RECORD_TYPE_END:
                mLastView = mTv_MyhitoryEnd;
                title_middle.setText("已揭晓");
                getEndData();
                break;
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_allrecord;
    }


    @OnClick({R.id.tv_record_all, R.id.tv_myhistory_ing, R.id.tv_myhitory_end, R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_record_all:
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.theme_text_gray));
                getAllData();
                mLastView = mTv_RecordAll;
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.main_red));
                break;
            case R.id.tv_myhistory_ing:
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.theme_text_gray));
                getIngData();
                mLastView = mTv_MyhistoryIng;
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.main_red));
                break;
            case R.id.tv_myhitory_end:
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.theme_text_gray));
                getEndData();
                mLastView = mTv_MyhitoryEnd;
                mLastView.setTextColor(CommonUtils.getColor(this, R.color.main_red));
                break;
            case R.id.title_back:
                this.finish();
                break;
        }
    }

    //获取所有记录
    private void getAllData() {
        mTv_RecordAll.setText("全部（88）");
        MoveBtnAinmation.cursorMoveBtnItemAnimation(mLastView, mTv_RecordAll, mMoveView);
        mLvMyhistory.setAdapter(mAdapter_all);
        if (mList_all.size() <= 0) {
            showNoData(true);
        } else {
            showNoData(false);
        }

    }

    //获取进行中记录
    private void getIngData() {
        MoveBtnAinmation.cursorMoveBtnItemAnimation(mLastView, mTv_MyhistoryIng, mMoveView);
        mLvMyhistory.setAdapter(mAdapter_ing);
        if (mList_ing.size() <= 0) {
            showNoData(true);
        } else {
            showNoData(false);
        }
    }

    //获取已揭晓
    private void getEndData() {
        MoveBtnAinmation.cursorMoveBtnItemAnimation(mLastView, mTv_MyhitoryEnd, mMoveView);
        mLvMyhistory.setAdapter(mAdapter_end);
        if (mList_end.size() <= 0) {
            showNoData(true);
        } else {
            showNoData(false);
        }
    }

    private void showNoData(boolean isShow) {
        if (isShow) {
            mXrefreshview.setVisibility(View.GONE);
            mLl_historyNodata.setVisibility(View.VISIBLE);
        } else {
            mXrefreshview.setVisibility(View.VISIBLE);
            mLl_historyNodata.setVisibility(View.GONE);
        }
    }

    private void getData() {
        HashMap map = new HashMap();
        new MyJsonRequestListener(this, Request.Method.GET, IpConfig.HTTP, map) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);

            }

            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);

            }
        };
    }
}
