package com.muan.takeout.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.muan.takeout.Activity.BaseActivity;
import com.muan.takeout.Model.UserInfoEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.StringUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class WebViewOnlineActivity extends BaseActivity implements DownloadListener {
    @BindView(R.id.mIvBack)
    ImageView mIv_back;
    @BindView(R.id.mIvRefresh)
    ImageView mIv_refresh;
    @BindView(R.id.mIvHead)
    ImageView mIv_next;
    @BindView(R.id.webview)
    WebView mWeb_view;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    HashMap mHeader = new HashMap();
    private WebSettings mWebSettings;
    private JSListener mJsListener;


    /**
     * 1、LoadUrl            直接加载网页、图片并显示.（本地或是网络上的网页、图片、gif）
     * 2、LoadData           显示文字与图片内容 （模拟器1.5、1.6）
     * 3、LoadDataWithBase   显示文字与图片内容（支持多个模拟器版本）
     */
    private int loadType;
    private String loadUrl;

    public String share_icon;

    public String share_title;

    public String share_content;

    public String share_url;


    @Override
    public void initData() {

    }

    @SuppressLint("JavascriptInterface")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void initView() {
//        if (getIntent().hasExtra("title")) {
//            getTitleBar().setTitle(getIntent().getStringExtra("title"));
//        } else {
//            getTitleBar().setTitle("");
//        }
//        getTitleBar().setRightText("分享");
//        getTitleBar().getRightText().setOnClickListener(this);
//        getTitleBar().getLeftText().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mWeb_view.canGoBack()) {
//                    mWeb_view.goBack();
//                } else {
//                    finish();
//                }
//
//            }
//        });
        mWebSettings = mWeb_view.getSettings();
        //webView的一些设置
        mWebSettings.setJavaScriptEnabled(true);//允许javascrpt
        mJsListener = new JSListener(mActivity, mWeb_view);
        mWeb_view.addJavascriptInterface(mJsListener, mJsListener.EXPOSE_NAME);//绑定监听
        mWebSettings.setBuiltInZoomControls(true);//缩放
        mWebSettings.setDisplayZoomControls(false);//不显示缩放按钮
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadsImagesAutomatically(true);//图片自动加载
        mWeb_view.requestFocusFromTouch();//允许点击获得焦点
        //    mWeb_view.setWebChromeClient(new MyWebChromeClient(mProgressBar, getTitleBar().getTitle()));//处理进度条
        mWeb_view.setWebViewClient(new MyWebViewClient());
        mWeb_view.setDownloadListener(this);
        mWeb_view.getSettings().setUserAgentString(mWeb_view.getSettings().getUserAgentString() +
                "7color" + getString(R.string.APP_NAME) + "/" +
                CommonUtils.getVersonName(mActivity) + "");
        loadUrl = getIntent().getStringExtra(FinalTools.LOADURL);
        if (StringUtil.isEmpty(loadUrl)) {
            MessageUtils.alertMessageCENTER("加载网页地址不能为空");
            this.finish();
        }
        if (getIntent().hasExtra(FinalTools.SHARE_ICON)) {
            share_icon = getIntent().getStringExtra(FinalTools.SHARE_ICON);
        }
        if (getIntent().hasExtra(FinalTools.SHARE_CONTENT)) {
            share_content = getIntent().getStringExtra(FinalTools.SHARE_CONTENT);
        }
        if (getIntent().hasExtra(FinalTools.SHARE_TITLE)) {
            share_title = getIntent().getStringExtra(FinalTools.SHARE_TITLE);
        }
        if (getIntent().hasExtra(FinalTools.SHARE_URL)) {
            share_url = getIntent().getStringExtra(FinalTools.SHARE_URL);
        }
        if (StringUtil.isEmpty(share_title)) {
            if (!StringUtil.isEmpty(loadUrl) && loadUrl.contains("share_title")) {
                Uri uri = Uri.parse(loadUrl);
                share_icon = uri.getQueryParameter("share_icon");
                share_icon = uri.getQueryParameter("share_content");
                share_title = uri.getQueryParameter("share_title");
                share_url = uri.getQueryParameter("share_url");
            }
        }
//        if (StringUtil.isEmpty(share_title)) {
//            getTitleBar().getRightText().setVisibility(View.GONE);
//        } else {
//            getTitleBar().getRightText().setVisibility(View.VISIBLE);
//            getTitleBar().setRightText("分享");
//        }
        //京东支付跳转页面 需要http头。(注意页面重载时的坑)
        if (getIntent().hasExtra("forJD")) {
            UserInfoEntity infoEntity = mApplication.getUserInfo();
            if (infoEntity != null) {
                mHeader.put("uid", infoEntity.id + "");
                mHeader.put("token", infoEntity.token);
            } else {
                MessageUtils.alertMessageCENTER("请先登录");
                return;
            }
            mHeader.put("os", "android");
            mHeader.put("timestamp", getIntent().getStringExtra("stamp"));
            mHeader.put("app", this.getString(R.string.APP_NAME));
            mHeader.put("version", this.getString(R.string.app_version));
            mHeader.put("zgid", CommonUtils.getIMEI(this));
            try {
                mHeader.put("channel", this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("UMENG_CHANNEL"));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            mWeb_view.loadUrl(loadUrl, mHeader);
        } else {
            mWeb_view.loadUrl(loadUrl);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_web;
    }

    @OnClick({R.id.mIvBack, R.id.mIvHead, R.id.mIvRefresh})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.mIvBack:
                if (mWeb_view.canGoBack()) {
                    mWeb_view.goBack();
                }
                break;
            case R.id.mIvHead:
                if (mWeb_view.canGoForward()) {
                    mWeb_view.goForward();
                }
                break;
            case R.id.mIvRefresh:
                mWeb_view.reload();
                break;
        }

    }

    @Override
    public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //对京东支付做的单独处理
//            if (url.contains("http://m.uqian.me/zerogo://uqian.me")) {
//                url = url.replace("http://m.uqian.me/", "");
//                //  url = "zerogo://uqian.me/?type=jdpay?status=1";
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)).
//                        putExtra("forJD", getIntent().getIntExtra("forJD", FinalTools.PAY_JD_CHARG)));
//                WebViewOnlineActivity.this.finish();
//            return true;
//            }

            if (url.startsWith("http")) {
                view.loadUrl(url, mHeader);
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
            return true;
        }

        /**
         * 设置返回 前进按钮
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mWeb_view.canGoBack()) {
                mIv_back.setImageDrawable(CommonUtils.getDrawable(mActivity, R.mipmap.icon_webview_goback));
            } else {
                mIv_back.setImageDrawable(CommonUtils.getDrawable(mActivity, R.mipmap.icon_webview_goback_default));
            }

            if (mWeb_view.canGoForward()) {
                mIv_next.setImageDrawable(CommonUtils.getDrawable(mActivity, R.mipmap.icon_webview_go_ahead));
            } else {
                mIv_next.setImageDrawable(CommonUtils.getDrawable(mActivity, R.mipmap.icon_webview_go_ahead_default));
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb_view.canGoBack()) {
            mWeb_view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mJsListener != null) {
            mJsListener.onDestory();
        }
        mWeb_view.setWebViewClient(null);
        mWeb_view.removeJavascriptInterface(mJsListener.EXPOSE_NAME);
        mWeb_view.removeAllViewsInLayout();
        mWeb_view.removeAllViews();
        super.onDestroy();
    }


}
