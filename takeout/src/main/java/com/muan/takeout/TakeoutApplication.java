package com.muan.takeout;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.muan.takeout.Model.UserInfoEntity;
import com.muan.takeout.Utils.MessageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by admin on 2016/10/28.
 */
public class TakeoutApplication extends Application {
    /**
     * 图片加载器
     */
    private ImageLoader mImageLoader;
    /**
     * 网络请求队列
     */
    private RequestQueue mRequestQueue;

    /**
     * 用户信息，用于自动登录等
     */
    private UserInfoEntity mUserInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        MessageUtils.init(this);
        //极光
//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(this);
        UserInfoEntity entity = UserInfoEntity.init(this);
        if (entity != null) {
            mUserInfo = entity;
        }
    }
    /**
     * 这里获得的对象有两种情况 1：所有属性都有 2：只有Id Token
     *
     * @return
     */
    public UserInfoEntity getUserInfo() {
        if (mUserInfo == null) {
            mUserInfo = UserInfoEntity.init(this);
            return mUserInfo;
        }
        return mUserInfo;
    }

    public void setUserInfo(UserInfoEntity mUserInfo) {
        this.mUserInfo = mUserInfo;
    }


    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            //创建默认的ImageLoader配置参数
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                    .createDefault(this);
            mImageLoader = ImageLoader.getInstance();
            mImageLoader.init(configuration);
        }
        return mImageLoader;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);

        }
        return mRequestQueue;
    }

}
