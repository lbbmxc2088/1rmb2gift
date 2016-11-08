package com.muan.takeout.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.R;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:53
 * 我的-个人信息
 */
public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.iv_userinfo_head)
    ImageView mIv_UserinfoHead;
    @BindView(R.id.iv_userinfo_arrow)
    ImageView mIv_UserinfoArrow;
    @BindView(R.id.rl_userinfo_head)
    RelativeLayout mRl_UserinfoHead;
    @BindView(R.id.tv_userinfo_id)
    TextView mTv_UserinfoId;
    @BindView(R.id.rl_userinfo_id)
    RelativeLayout mRl_UserinfoId;
    @BindView(R.id.tv_userinfo_account)
    TextView mTv_UserinfoAccount;
    @BindView(R.id.rl_userinfo_account)
    RelativeLayout mRl_UserinfoAccount;
    @BindView(R.id.tv_userinfo_phone)
    TextView mTv_UserinfoPhone;
    @BindView(R.id.iv_userinfo_arrow2)
    ImageView mIv_UserinfoArrow2;
    @BindView(R.id.rl_userinfo_phone)
    RelativeLayout mRl_UserinfoPhone;
    @BindView(R.id.rl_userinfo_address)
    RelativeLayout mRl_UserinfoAddress;
    @BindView(R.id.rl_userinfo_nickname)
    RelativeLayout mRl_UserinfoNickname;
    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.tv_userinfo_nickname)
    TextView tvUserinfoNickname;
    @BindView(R.id.iv_userinfo_arrow3)
    ImageView ivUserinfoArrow3;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        titleMiddle.setText("个人信息");

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_userinfo;
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


    @OnClick({R.id.rl_userinfo_head, R.id.rl_userinfo_phone, R.id.rl_userinfo_address, R.id.rl_userinfo_nickname, R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_userinfo_head:
                break;
            case R.id.rl_userinfo_phone:
                break;
            case R.id.rl_userinfo_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
            case R.id.rl_userinfo_nickname:
                break;
            case R.id.title_back:
                this.finish();
                break;
        }
    }
}

