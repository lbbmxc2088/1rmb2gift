package com.muan.takeout.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muan.takeout.Model.UserInfoEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.WXUtils;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/2 09:04
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.ed_login_phone)
    EditText mEt_phone;
    @BindView(R.id.ed_login_password)
    EditText mEd_pswd;
    @BindView(R.id.bt_login_in)
    Button mBt_login;
    @BindView(R.id.bt_regist)
    Button mBt_toregist;
    @BindView(R.id.tv_login_findpswd)
    TextView mTv_findpswd;
    @BindView(R.id.iv_wx_login)
    ImageView mIv_wx;
    @BindView(R.id.ll_login_wx)
    LinearLayout mLl_wx;
    @BindView(R.id.iv_qq_login)
    ImageView mIv_qq;
    @BindView(R.id.ll_login_qq)
    LinearLayout mLl_qq;
    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;

    private Tencent mTencent;
    IUiListener iUiListener = new IUiListener() {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public void onError(UiError arg0) {
            MessageUtils.alertMessageCENTER(arg0.errorMessage);
        }

        @Override
        public void onComplete(Object arg0) {
            try {
                JSONObject json = new JSONObject(arg0.toString());
                long expires_in = json.optLong("expires_in");
                final String accessToken = json.optString("access_token");
                final String openId = json.optString("openid");
                mTencent.setOpenId(openId);
                mTencent.setAccessToken(accessToken, expires_in / 1000 + "");
                UserInfo info = new UserInfo(mActivity, mTencent.getQQToken());
                info.getUserInfo(new IUiListener() {
                    @Override
                    public void onError(UiError arg0) {
                        //取消进度条
                        //登录失败
                        MessageUtils.alertMessageCENTER(arg0.errorMessage);
                    }

                    @Override
                    public void onComplete(Object arg0) {
                        //取消进度条
                        //登录成功
                        try {
                            Login();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onCancel() {
        }
    };

    @Override
    public void initData() {

    }

    //调用登陆接口
    public void Login() {
        UserInfoEntity user = new UserInfoEntity();
        user.nickname = "Demo1";
        user.signature = "sign2";
        user.id = 123;
        user.token = "456";
        user.saveInfo(this);
        this.finish();

    }

    @Override
    public void initView() {
        titleMiddle.setText("登陆");

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.bt_login_in, R.id.tv_login_findpswd, R.id.title_back, R.id.bt_regist, R.id.iv_wx_login, R.id.ll_login_wx, R.id.iv_qq_login, R.id.ll_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login_in:
                Login();
                break;
            case R.id.tv_login_findpswd:
                startActivity(new Intent(this, RegistActivity.class).putExtra(FinalTools.ISREGIST, false));
                this.finish();
                break;
            case R.id.iv_wx_login:
            case R.id.ll_login_wx:
                MessageUtils.alertMessageCENTER("正在唤起微信登录");
                setButtonsEnable(false);
                //   WXUtils.regist(this, "写入微信appid");
                break;
            case R.id.iv_qq_login:
            case R.id.ll_login_qq:
                MessageUtils.alertMessageCENTER("正在唤起QQ登陆");
                // LoginQQ();
                //setButtonsEnable(false);
                break;
            case R.id.title_back:
                this.finish();
                break;
            case R.id.bt_regist:
                startActivity(new Intent(this, RegistActivity.class).putExtra(FinalTools.ISREGIST, true));
                this.finish();
                break;
        }
    }

    //qq登录
    //这里是调用QQ登录的关键代码
    public void LoginQQ() {
        //这里的APP_ID请换成你应用申请的APP_ID，我这里使用的是DEMO中官方提供的测试APP_ID 222222
        String mqqAppid = "";
        //第一个参数就是上面所说的申请的APPID，第二个是全局的Context上下文，这句话实现了调用QQ登录
        mTencent = Tencent.createInstance(mqqAppid, getApplicationContext());
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mTencent.login(this, "all", iUiListener);
    }


    private void setButtonsEnable(boolean enable) {
        mBt_login.setEnabled(enable);
        mIv_qq.setEnabled(enable);
        mIv_wx.setEnabled(enable);
        mLl_qq.setEnabled(enable);
        mLl_wx.setEnabled(enable);
    }
}
