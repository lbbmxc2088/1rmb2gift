package com.muan.takeout.Activity;

import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.LuckyAdapter;
import com.muan.takeout.Model.LuckyEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:59
 * 我的-中奖记录
 */
public class LuckyRecodActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.lv_lucky)
    ListView mListView;
    @BindView(R.id.xrfv_lucky)
    XRefreshView mXrefreshview;
    @BindView(R.id.ll_lucky_nodata)
    LinearLayout mLayout_nodata;

    List<LuckyEntity> mList;
    LuckyAdapter mAdapter;


    @Override
    public void initData() {
        mList = new ArrayList<>();
        mAdapter = new LuckyAdapter(mList, this);
        mListView.setAdapter(mAdapter);
        getData();
    }

    @Override
    public void initView() {
        titleMiddle.setText("幸运记录");
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
        return R.layout.activity_luckyrecord;
    }

    @OnClick({R.id.title_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                this.finish();
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
