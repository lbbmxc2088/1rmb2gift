package com.muan.takeout.Utils;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 微信工具类
 *
 * @author Muan
 *         时间: 2016年11月2日
 */
public class WXUtils {
    /**
     * 注册
     */
    public static void regist(Context context, String appId) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, appId, true);
        if (!api.isWXAppInstalled()) {
            MessageUtils.alertMessageCENTER("您还没有安装微信");
            return;
        }
        api.registerApp(appId);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";//获取用户信息填写snsapi_userinfo
        req.state = "com_muan_yydb";//自定义
        api.sendReq(req);
    }

    public static boolean isExitsWx(IWXAPI api) {
        if (!api.isWXAppInstalled()) {
            MessageUtils.alertMessageCENTER("您还没有安装微信");
            return false;
        }
        return true;
    }

}
