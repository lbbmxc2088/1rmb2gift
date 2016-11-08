package com.muan.takeout.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.R;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:58
 * 我的-购买记录
 */
public class BuyRecordActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.tv_record_all)
    TextView mTv_RecordAll;
    @BindView(R.id.tv_record_dfk)
    TextView mTv_RecordDfk;
    @BindView(R.id.tv_record_dfh)
    TextView mTv_RecordDfh;
    @BindView(R.id.tv_record_dsh)
    TextView mTv_RecordDsh;
    @BindView(R.id.view_mywin_move)
    View viewMywinMove;
    @BindView(R.id.layout_move)
    FrameLayout layoutMove;
    @BindView(R.id.layout_sort_head)
    LinearLayout layoutSortHead;
    @BindView(R.id.lv_lucky)
    ListView lvLucky;
    @BindView(R.id.xrfv_lucky)
    XRefreshView xrfvLucky;
    @BindView(R.id.ll_lucky_nodata)
    LinearLayout llLuckyNodata;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
//        mXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
//            @Override
//            public void onRefresh() {
//                super.onRefresh();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mXrefreshview.stopRefresh();
//                    }
//                }, FinalTools.REFRESH_DELAYTIME);
//
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//                super.onLoadMore(isSilence);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mXrefreshview.stopLoadMore();
//                    }
//                }, FinalTools.REFRESH_DELAYTIME);
//            }
//        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_buyrecord;
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


    @OnClick({R.id.title_back, R.id.tv_record_all, R.id.tv_record_dfk, R.id.tv_record_dfh, R.id.tv_record_dsh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.tv_record_all:
                break;
            case R.id.tv_record_dfk:
                break;
            case R.id.tv_record_dfh:
                break;
            case R.id.tv_record_dsh:
                break;
        }
    }
}
