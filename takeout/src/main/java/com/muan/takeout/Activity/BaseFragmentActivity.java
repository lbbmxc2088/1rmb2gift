package com.muan.takeout.Activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.RequestQueue;
import com.muan.takeout.TakeoutApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * User: Muan
 * Date: 2016/10/31
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    protected TakeoutApplication mApplication;
    protected FragmentActivity mActivity;
    protected ImageLoader mImageLoader;
    protected RequestQueue mRequestQueue;
    protected ProgressDialog mProgressDialog;

    public InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        mApplication= (TakeoutApplication) getApplicationContext();
        mImageLoader=mApplication.getImageLoader();
        mRequestQueue=mApplication.getRequestQueue();
        mProgressDialog=new ProgressDialog(mActivity);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setTranslucentStatus(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置沉侵栏
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}