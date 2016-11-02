package com.muan.takeout.Model;

import android.content.Context;

import com.muan.takeout.Utils.PreferenceUtils;
import com.muan.takeout.Utils.StringUtil;

/**
 * Created by ${Muan} on 2016/11/2 11:39
 */
public class UserInfoEntity {
    public long id;
    public String nickname;
    public String headpic;
    public String signature;
    public String tel;
    public String token;
    public int isfirst;
    public int vip;
    public String qq;
    public String alipay;
    public String weixin;

    /**
     * 加载本地缓存的用户数据
     *
     * @param context
     * @return
     */
    public static UserInfoEntity init(Context context) {
        long id = PreferenceUtils.getInstance(context).getLong("user_id", -1L);
        String token = PreferenceUtils.getInstance(context).getString("user_token", "");
        if (id == -1 || StringUtil.isEmpty(token)) {
            return null;
        } else {
            UserInfoEntity entity = new UserInfoEntity();
            entity.id = id;
            entity.token = token;
            return entity;
        }
    }

    public void saveInfo(Context context) {
        PreferenceUtils.getInstance(context).saveLong("user_id", this.id);
        PreferenceUtils.getInstance(context).saveString("user_token", this.token);
    }

    public static void clearUserInfo(Context context) {
        PreferenceUtils.getInstance(context).saveLong("user_id", -1L);
        PreferenceUtils.getInstance(context).saveString("user_token", "null");
    }

}
