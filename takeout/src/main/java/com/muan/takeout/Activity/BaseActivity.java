package com.muan.takeout.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.android.volley.RequestQueue;
import com.muan.takeout.Receiver.ConnectivityStatusReceiver;
import com.muan.takeout.TakeoutApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by Muan on 2016/10/28.
 */
public abstract class BaseActivity extends Activity implements ConnectivityStatusReceiver.ConnectivityChangeListener, View.OnClickListener, AdapterView.OnItemClickListener {

    protected TakeoutApplication mApplication;
    protected Activity mActivity;
    protected ImageLoader mImageLoader;
    protected RequestQueue mRequestQueue;

    protected ProgressDialog mProgressDialog;

    /**
     * 软键盘管理类 隐藏软键盘 imm.hideSoftInputFromWindow(view.getWindowToken(), 0); 显示软键盘
     * imm.showSoftInput(view, 0);
     */
    public InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mApplication = (TakeoutApplication) getApplicationContext();
        mImageLoader = mApplication.getImageLoader();
        mRequestQueue = mApplication.getRequestQueue();
        mProgressDialog = new ProgressDialog(mActivity);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setTranslucentStatus(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        initView();
        initData();

    }

    public abstract void initData();

    public abstract void initView();

    public abstract int getContentLayout();


    /**
     * 设置沉侵栏
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4以上
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 网络改变回掉方法
     */
    public void internetCanUse(boolean val, NetworkInfo networkInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    private Map<Integer, Runnable> disallowablePermissionRunnables = new HashMap<>();

    /**
     * 请求权限
     *
     * @param id                   请求授权的id 唯一标识即可
     * @param permission           请求的权限
     * @param allowableRunnable    同意授权后的操作
     * @param disallowableRunnable 禁止权限后的操作
     */
    public void requestPermission(int id, String[] permission, Runnable allowableRunnable, Runnable disallowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }

        allowablePermissionRunnables.put(id, allowableRunnable);
        if (disallowableRunnable != null) {
            disallowablePermissionRunnables.put(id, disallowableRunnable);
        }

        ActivityCompat.requestPermissions(BaseActivity.this, permission, id);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Runnable allowRun = allowablePermissionRunnables.get(requestCode);
            allowRun.run();
        } else {
            Runnable disallowRun = disallowablePermissionRunnables.get(requestCode);
            disallowRun.run();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}