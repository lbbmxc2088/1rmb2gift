package com.muan.takeout.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.muan.takeout.R;
import com.muan.takeout.Utils.MessageUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 10:52
 * 我的-设置
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.tv_notifaction_open)
    TextView mTv_NotifactionOpen;
    @BindView(R.id.rl_setting_notifaction)
    RelativeLayout mRl_SettingNotifaction;
    @BindView(R.id.rl_setting_trouble)
    RelativeLayout mRl_SettingTrouble;
    @BindView(R.id.rl_setting_updata)
    RelativeLayout mRl_SettingUpdata;
    @BindView(R.id.rl_setting_about)
    RelativeLayout mRl_SettingAbout;
    @BindView(R.id.rl_setting_clear)
    RelativeLayout mRl_SettingClear;
    @BindView(R.id.bt_setting_logout)
    Button mBt_logout;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        if (mApplication.getUserInfo() == null) {
            mBt_logout.setVisibility(View.GONE);
        } else {
            mBt_logout.setVisibility(View.VISIBLE);
        }
        titleMiddle.setText("设置");

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.title_back, R.id.rl_setting_notifaction, R.id.rl_setting_trouble, R.id.rl_setting_updata, R.id.rl_setting_about, R.id.rl_setting_clear, R.id.bt_setting_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.rl_setting_notifaction:
                MessageUtils.alertLongMessageCENTER("根据需求制定");
                break;
            case R.id.rl_setting_trouble:
                MessageUtils.alertLongMessageCENTER("根据需求制定");
                break;
            case R.id.rl_setting_updata:
                proveVersion();
                break;
            case R.id.rl_setting_about:
                MessageUtils.alertLongMessageCENTER("根据需求制定");
                break;
            case R.id.rl_setting_clear:
                clearCache();
                break;
            case R.id.bt_setting_logout:
                logOut();
                break;
        }
    }

    private void proveVersion() {
        //检查最新版本，更新
    }

    private void clearCache() {

    }

    private void logOut() {
        //账户登出
    }
}
