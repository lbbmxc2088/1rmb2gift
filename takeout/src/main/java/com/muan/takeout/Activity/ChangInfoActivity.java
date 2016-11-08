package com.muan.takeout.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.R;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/4 13:45
 * 修改信息: 1-个人签名 2-手机号 3-红包兑换 4-修改昵称
 */
public class ChangInfoActivity extends BaseActivity {
    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.et_editinfo_info)
    EditText etEditinfoInfo;
    @BindView(R.id.bt_editinfo_changered)
    Button btEditinfoChangered;


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        switch (getIntent().getIntExtra("type", 4)) {
            case 1:
                titleMiddle.setText("修改签名");
                btEditinfoChangered.setText("修改签名");
                break;
            case 2:

                break;
            case 3:
                titleMiddle.setText("兑换红包");
                btEditinfoChangered.setText("兑换红包");
                break;
            case 4:
                titleMiddle.setText("修改昵称");
                btEditinfoChangered.setText("修改昵称");
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_changeinfo;
    }


    @OnClick({R.id.title_back, R.id.bt_editinfo_changered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.bt_editinfo_changered:
                if (getIntent().getIntExtra("type", 4) == 3) {
                    exchangeRedgrab();
                } else {
                    changUserInfo();
                }
                break;
        }
    }

    public void changUserInfo() {
        //修改个人信息
    }

    public void exchangeRedgrab() {
        //兑换红包
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
