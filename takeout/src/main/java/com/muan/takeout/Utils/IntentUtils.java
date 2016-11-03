package com.muan.takeout.Utils;

import android.content.Context;
import android.content.Intent;

import com.muan.takeout.Activity.LoginActivity;

/**
 * Created by ${Muan} on 2016/11/3 11:50
 */
public class IntentUtils {
    public static void  Login(Context mActivity){
        MessageUtils.alertMessageCENTER("您还没有登录，请先登录");
        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
    }
}
