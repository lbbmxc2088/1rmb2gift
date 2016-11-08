package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.HongBaoAdapter;
import com.muan.takeout.Model.RedGrabEntity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:57
 * 我的-红包
 */
public class RedgrabActivity extends BaseActivity {
    private final String[] TYPE = {"used", "unused"};
    @BindView(R.id.lv_redgrab)
    ListView mListView;
    @BindView(R.id.xrfv_redgrab)
    XRefreshView mXrefreshview;
    @BindView(R.id.ll_redgrab_nodata)
    LinearLayout layout_nodata;

    @BindView(R.id.bt_redgrab_gobuy)
    Button mBt_gobuy;
    @BindView(R.id.rg_redgrab)
    RadioGroup mRadioGroup;
    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;

    private List<RedGrabEntity> mList;
    private HongBaoAdapter mAdapter_can;

    private List<RedGrabEntity> mList_cant;
    private HongBaoAdapter mAdapter_cant;
    /**
     * 0不可用 1可用
     */
    private int type = 1;

    @Override
    public void initData() {
        mList = new ArrayList<RedGrabEntity>();
        mAdapter_can = new HongBaoAdapter(mList, mActivity);
        mList_cant = new ArrayList<RedGrabEntity>();
        mAdapter_cant = new HongBaoAdapter(mList_cant, mActivity);
        mListView.setAdapter(mAdapter_can);
        getHongBaoListCanused();
        getHongBaoListHasused();
    }

    @Override
    public void initView() {
        titleMiddle.setText("红包");
        titleRight.setText("兑换红包");
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_redgrab_can:
                        type = 1;
                        mListView.setAdapter(mAdapter_can);
                        if (mList == null || mList.size() == 0) {
                            layout_nodata.setVisibility(View.VISIBLE);
                        } else {
                            layout_nodata.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.rb_redgrab_cant:
                        type = 0;
                        mListView.setAdapter(mAdapter_cant);
                        if (mList_cant == null || mList_cant.size() == 0) {
                            layout_nodata.setVisibility(View.VISIBLE);
                        } else {
                            layout_nodata.setVisibility(View.GONE);
                        }
                        break;

                }
            }
        });
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
        return R.layout.activity_redgrab;
    }

    @OnClick({R.id.bt_redgrab_gobuy, R.id.title_back, R.id.title_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.title_right:
                startActivity(new Intent());
                break;
            case R.id.bt_redgrab_gobuy:
                break;
        }
    }

    public void getHongBaoListCanused() {

    }

    public void getHongBaoListHasused() {

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
