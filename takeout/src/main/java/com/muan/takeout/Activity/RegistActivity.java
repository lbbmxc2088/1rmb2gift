package com.muan.takeout.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Model.MessageEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.PatternTools;
import com.muan.takeout.Utils.StringUtil;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/2 09:17
 */
public class RegistActivity extends BaseActivity {
    @BindView(R.id.title_back)
    public TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.ed_regist_phone)
    EditText mEt_RegistPhone;
    @BindView(R.id.tv_regist_vcode)
    TextView mTv_RegistVcode;
    @BindView(R.id.ed_regist_vcode)
    EditText mEt_RegistVcode;
    @BindView(R.id.ed_regist_pswd)
    EditText mEt_RegistPswd;
    @BindView(R.id.bt_regist_in)
    Button mBt_registin;
    @BindView(R.id.cb_regist_user)
    CheckBox mCb_user;
    @BindView(R.id.tv_regist_protocol)
    TextView mTv_RegistProtocol;
    private String phonenumber;
    private String password;
    private String yanzhengma;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        if (getIntent().getBooleanExtra(FinalTools.ISREGIST, true)) {
            titleMiddle.setText("注册");
        } else {
            titleMiddle.setText("找回密码");
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_regist;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_regist_vcode, R.id.bt_regist_in, R.id.tv_regist_protocol, R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.tv_regist_vcode:
                phonenumber = mEt_RegistPhone.getText().toString().trim();
                if (StringUtil.isEmpty(phonenumber)
                        || !PatternTools.isPhoneNumber(phonenumber)) {
                    MessageUtils.alertMessageCENTER("请输入正确手机号码");
                    return;
                }
                mHandler = new TimerHandler();
                //发送手机号获得验证码 ，按钮倒计时
                getYanzhengma();
                break;
            case R.id.bt_regist_in:

                password = mEt_RegistPswd.getText().toString().trim();
                yanzhengma = mEt_RegistVcode.getText().toString().trim();
                phonenumber = mEt_RegistPhone.getText().toString().trim();
                if (StringUtil.isEmpty(phonenumber)
                        || !PatternTools.isPhoneNumber(phonenumber)) {
                    MessageUtils.alertMessageCENTER("请输入正确手机号码");
                    return;
                }
                if (StringUtil.isEmpty(yanzhengma) || !PatternTools.isYanzhengma(yanzhengma)) {
                    MessageUtils.alertMessageCENTER("请输入正确的验证码");
                    return;
                }
                if (StringUtil.isEmpty(password)
                        || !PatternTools.isPassword(password)) {
                    MessageUtils.alertMessageCENTER("密码请勿包含除数字、字母和下划线外的其他字符");
                    return;
                }
                if (!mCb_user.isChecked()) {
                    MessageUtils.alertMessageCENTER("请仔细阅读并同意用户协议");
                    return;
                }
                if (getIntent().getBooleanExtra(FinalTools.ISREGIST, true)) {
                    regist(phonenumber, yanzhengma, password);
                } else {
                    forgetPswd();
                }
                break;
            case R.id.tv_regist_protocol:
                MessageUtils.alertLongMessageCENTER("转协议页面");
                break;
        }
    }

    //注册
    public void regist(final String phonenumber, final String yanzhengma, final String password) {
        //// TODO: 2016/11/2
    }

    //忘记密码
    public void forgetPswd() {
        //// TODO: 2016/11/2
    }

    private int time = 90;
    private Handler mHandler;
    private Timer mTimer;
    private TimerTask mTask;

    /**
     * 开启计时器
     */
    private void startTimer() {
        mTv_RegistVcode.setEnabled(false);
        mTask = new TimerTask() {
            public void run() {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        };
        mTimer = new Timer(true);
        mTimer.schedule(mTask, 1000, 1000); // 延时1000ms后执行，1000ms执行一次
    }

    /**
     * 关闭计时器
     */
    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mTask != null) {
            mTask.cancel();
        }
        time = 90;
        mTv_RegistVcode.setText("获取验证码");
        mTv_RegistVcode.setEnabled(true);
    }

    class TimerHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    time--;
                    mTv_RegistVcode.setText("重新发送(" + time + "s)");
                    if (time <= 0) {
                        stopTimer();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    //获取验证码
    public void getYanzhengma() {
        startTimer();
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
