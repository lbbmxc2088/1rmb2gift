package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.SDAdapter;
import com.muan.takeout.Model.SDEntity;
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
 * Created by ${Muan} on 2016/11/3 10:56
 * 我的-晒单
 */
public class SDActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.lv_sd)
    ListView mListview_sd;
    @BindView(R.id.xrfv_sd)
    XRefreshView mXrefreshview;
    @BindView(R.id.bt_redgrab_gobuy)
    Button btRedgrabGobuy;
    @BindView(R.id.ll_redgrab_nodata)
    LinearLayout llRedgrabNodata;

    List<SDEntity> mList;
    SDAdapter mAdapter;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mAdapter = new SDAdapter(mList, this);
        mListview_sd.setAdapter(mAdapter);

    }

    @Override
    public void initView() {
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
        return R.layout.activity_sd;
    }


    @OnClick({R.id.title_back, R.id.bt_redgrab_gobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.bt_redgrab_gobuy:
                startActivity(new Intent(this, MainActivity.class).putExtra("type", FinalTools.TO_SHOPPING));
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
