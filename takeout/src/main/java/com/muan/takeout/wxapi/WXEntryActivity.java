package com.muan.takeout.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by ${Muan} on 2016/10/31.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private String mWXAPPId;
    private String mWXAPPScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mWXAPPId = this.getString(R.string.WX_APP_ID);
        //mWXAPPScreen = this.getString(R.string.WX_APPSECRET);
        api = WXAPIFactory.createWXAPI(this, mWXAPPId, false);
        api.registerApp(mWXAPPId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 微信登陆获取access_token
     */
    public void getAccessToken(String code) {
        HashMap ap = new HashMap();
        ap.put("appid", mWXAPPId);
        ap.put("secret", mWXAPPScreen);
        ap.put("code", code);
        ap.put("grant_type", "authorization_code");//固定填
        new MyJsonRequestListener(this, Request.Method.GET, "https://api.weixin.qq.com/sns/oauth2/access_token", ap) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                WXEntryActivity.this.finish();
            }

            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                try {
                    JSONObject json = response;
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageUtils.alertMessageCENTER("授权失败");
                }
            }
        };

    }



    @Override
    public void onResp(BaseResp baseResp) {

    }

}
