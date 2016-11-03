package com.muan.takeout.Activity;

import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.muan.takeout.Model.HistoryEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.MoveBtnAinmation;
import com.muan.takeout.Utils.WindowUtils;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import java.util.ArrayList;
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
    XRefreshView mXrfvMyhistory;
    @BindView(R.id.bt_myhistory_gobuy)
    Button mBt_MyhistoryGobuy;
    @BindView(R.id.ll_myhistory_nodata)
    LinearLayout mLl_MyhistoryNodata;

    private View mLastView;

    List<HistoryEntity> mList_all = new ArrayList<>();
    List<HistoryEntity> mList_end = new ArrayList<>();
    List<HistoryEntity> mList_ing = new ArrayList<>();

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mMoveView.getLayoutParams();
        layoutParams1.width = WindowUtils.getWIndowWidth(mActivity) / 3;
        layoutParams1.height = FrameLayout.LayoutParams.MATCH_PARENT;
        mMoveView.setLayoutParams(layoutParams1);

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


    @OnClick({R.id.tv_record_all, R.id.tv_myhistory_ing, R.id.tv_myhitory_end, R.id.bt_myhistory_gobuy, R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_record_all:
                getAllData();
                mLastView = mTv_RecordAll;
                break;
            case R.id.tv_myhistory_ing:
                getIngData();
                mLastView = mTv_MyhistoryIng;
                break;
            case R.id.tv_myhitory_end:
                getEndData();
                mLastView = mTv_MyhitoryEnd;
                break;
            case R.id.bt_myhistory_gobuy:
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

    }

    //获取进行中记录
    private void getIngData() {
        MoveBtnAinmation.cursorMoveBtnItemAnimation(mLastView, mTv_MyhistoryIng, mMoveView);
    }

    //获取已揭晓
    private void getEndData() {
        MoveBtnAinmation.cursorMoveBtnItemAnimation(mLastView, mTv_MyhitoryEnd, mMoveView);
    }
}
