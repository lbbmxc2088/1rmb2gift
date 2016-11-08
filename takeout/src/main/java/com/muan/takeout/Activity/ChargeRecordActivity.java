package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.ChargeAdapter;
import com.muan.takeout.Model.ChargeEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:55
 * 我的-充值记录
 */
public class ChargeRecordActivity extends BaseActivity {
    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.lv_charge)
    ListView mListView;
    @BindView(R.id.xrfv_charge)
    XRefreshView mXrefreshview;
    @BindView(R.id.tv_nodata)
    TextView mTv_nodata;

    List<ChargeEntity> mList;
    ChargeAdapter mAdapter;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mAdapter = new ChargeAdapter(mList, this);
        mListView.setAdapter(mAdapter);
        for (int i = 0; i < 10; i++) {
            ChargeEntity entity = new ChargeEntity();
            entity.setCharge_num(i + 10 + "");
            entity.setCharge_time("2016-11-8 16:05:22");
            entity.setCharge_way("微信");
            mList.add(entity);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        titleMiddle.setText("充值记录");
        titleRight.setText("充值");
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
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_chargerecord;
    }

    @OnClick({R.id.title_back, R.id.title_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.title_right:
                startActivity(new Intent(this, ChargeActivity.class));
                break;
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
