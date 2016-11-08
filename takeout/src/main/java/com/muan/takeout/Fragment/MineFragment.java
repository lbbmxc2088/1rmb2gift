package com.muan.takeout.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.muan.takeout.Activity.AllRecordActivity;
import com.muan.takeout.Activity.BuyRecordActivity;
import com.muan.takeout.Activity.ChargeRecordActivity;
import com.muan.takeout.Activity.LoginActivity;
import com.muan.takeout.Activity.LuckyRecodActivity;
import com.muan.takeout.Activity.MessageActivity;
import com.muan.takeout.Activity.MultiActivity;
import com.muan.takeout.Activity.RedgrabActivity;
import com.muan.takeout.Activity.RegistActivity;
import com.muan.takeout.Activity.SDActivity;
import com.muan.takeout.Activity.SettingActivity;
import com.muan.takeout.Activity.UserInfoActivity;
import com.muan.takeout.Model.UserInfoEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.IntentUtils;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Widget.CircleImageView;
import com.muan.takeout.Widget.xRefresh.XRefreshView;
import com.muan.takeout.Widget.xRefresh.XScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/10/31 17:49
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_mine_setting)
    ImageView mIv_MineSetting;
    @BindView(R.id.iv_mine_message)
    ImageView mIv_MineMessage;
    @BindView(R.id.criv_mine_head)
    CircleImageView mCriv_MineHead;
    @BindView(R.id.tv_mine_name)
    TextView mTv_MineName;
    @BindView(R.id.tv_mine_sign)
    TextView mTv_MineSign;
    @BindView(R.id.ll_mine_login)
    RelativeLayout mLl_MineLogin;
    @BindView(R.id.tv_mine_regist)
    TextView mTv_MineRegist;
    @BindView(R.id.tv_mine_login)
    TextView mTv_MineLogin;
    @BindView(R.id.rl_mine_regist)
    RelativeLayout mRl_MineRegist;
    @BindView(R.id.tv_mine_ing)
    TextView mTv_MineIng;
    @BindView(R.id.tv_mine_end)
    TextView mTv_MineEnd;
    @BindView(R.id.tv_mine_multi)
    TextView mTv_MineMulti;
    @BindView(R.id.rl_mine_allrecord)
    RelativeLayout mRl_MineAllrecord;
    @BindView(R.id.rl_mine_luckyrecord)
    RelativeLayout mRl_MineLuckyrecord;
    @BindView(R.id.rl_mine_buyrecord)
    RelativeLayout mRl_MineBuyrecord;
    @BindView(R.id.rl_mine_redgrab)
    RelativeLayout mRl_MineRedgrab;
    @BindView(R.id.rl_mine_sd)
    RelativeLayout mRl_MineSd;
    @BindView(R.id.rl_mine_chargerecord)
    RelativeLayout mRl_MineChargerecord;
    @BindView(R.id.rl_mine_service)
    RelativeLayout mRl_MineService;
    @BindView(R.id.temp)
    XScrollView temp;
    @BindView(R.id.xrfv_mine)
    XRefreshView xrfvMine;

    UserInfoEntity mUserinfo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, mFragmentView);
        initView();
        return mFragmentView;
    }


    @OnClick({R.id.iv_mine_setting, R.id.iv_mine_message, R.id.criv_mine_head, R.id.tv_mine_regist, R.id.tv_mine_login, R.id.tv_mine_ing, R.id.tv_mine_end, R.id.tv_mine_multi, R.id.rl_mine_allrecord, R.id.rl_mine_luckyrecord, R.id.rl_mine_buyrecord, R.id.rl_mine_redgrab, R.id.rl_mine_sd, R.id.rl_mine_chargerecord, R.id.rl_mine_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.iv_mine_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
            case R.id.criv_mine_head:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, UserInfoActivity.class));
                }
                break;
            case R.id.tv_mine_regist:
                startActivity(new Intent(mActivity, RegistActivity.class));
                break;
            case R.id.tv_mine_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.tv_mine_ing:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, AllRecordActivity.class).putExtra("type", FinalTools.RECORD_TYPE_ING));
                }
                break;
            case R.id.tv_mine_end:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, AllRecordActivity.class).putExtra("type", FinalTools.RECORD_TYPE_END));
                }
                break;
            case R.id.tv_mine_multi:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, MultiActivity.class));
                }
                break;
            case R.id.rl_mine_allrecord:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, AllRecordActivity.class).putExtra("type", FinalTools.RECORD_TYPE_ALL));
                }
                break;
            case R.id.rl_mine_luckyrecord:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, LuckyRecodActivity.class));
                }
                break;
            case R.id.rl_mine_buyrecord:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, BuyRecordActivity.class));
                }
                break;
            case R.id.rl_mine_redgrab:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, RedgrabActivity.class));
                }
                break;
            case R.id.rl_mine_sd:
                startActivity(new Intent(mActivity, SDActivity.class));
                break;
            case R.id.rl_mine_chargerecord:
                if (mApplication.getUserInfo() == null) {
                    IntentUtils.Login(mActivity);
                } else {
                    startActivity(new Intent(mActivity, ChargeRecordActivity.class));
                }
                break;
            case R.id.rl_mine_service:
                MessageUtils.alertLongMessageCENTER("客服，根据需求定制");
                break;
        }
    }

    public void initView() {
        mUserinfo = mApplication.getUserInfo();
        if (mUserinfo == null) {
            isLogin(false);
        }
    }

    //是否登陆界面
    public void isLogin(boolean islogin) {
        if (islogin) {
            mLl_MineLogin.setVisibility(View.VISIBLE);
            mRl_MineRegist.setVisibility(View.GONE);
            mTv_MineName.setText(mUserinfo.nickname);
            mTv_MineSign.setText(mUserinfo.signature);
            //  mCriv_MineHead.setImageResource();

        } else {
            mLl_MineLogin.setVisibility(View.GONE);
            mRl_MineRegist.setVisibility(View.VISIBLE);
            mCriv_MineHead.setImageDrawable(CommonUtils.getDrawable(mActivity, R.mipmap.icon_app));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    private void getUserInfo() {
        if (mApplication.getUserInfo() == null) {
            isLogin(false);
            return;
        }
        //获得用户最新信息
        isLogin(true);
    }

}
