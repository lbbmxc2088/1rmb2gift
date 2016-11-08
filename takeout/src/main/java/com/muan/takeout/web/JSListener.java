package com.muan.takeout.web;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.R;
import com.muan.takeout.TakeoutApplication;
import com.muan.takeout.Utils.CommonUtils;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.PreferenceUtils;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 类描述：
 * 创建时间：16/3/14 10:20
 * 修改人：colorful
 * 修改时间：16/3/14 10:20
 * 修改备注：
 */
public class JSListener {
    public static final String NativeCallHtmlMentodBroadcastReceiverString = "com.native.callhtml";
    public static final String API_ACTION = "com.colorful.api.action";
    protected Context mContext;
    public WebView mWebView;
    /**
     * the name used to expose the object in JavaScript
     */
    public static final String EXPOSE_NAME = "colorful";
    ShareSucessfulBroadCastReceiver shareSucessfulBroadCastReceiver;

    public String callHtmlMethod;
    public Bitmap shareBitmap;
    public String mShareImageUrl;

    private NativeCallHtmlMentodBroadcastReceiver NativeCallHtmlreceiver;

    public JSListener(Context mContext, WebView mWebView) {
        this.mContext = mContext;
        this.mWebView = mWebView;
        shareBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.icon_app);
        shareSucessfulBroadCastReceiver = new ShareSucessfulBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ShareSucessfulBroadCastReceiver.ACTION);
        mContext.registerReceiver(shareSucessfulBroadCastReceiver, filter);


        NativeCallHtmlreceiver = new NativeCallHtmlMentodBroadcastReceiver();
        IntentFilter filterNativeCallHtmlreceiver = new IntentFilter();
        filterNativeCallHtmlreceiver.addAction(NativeCallHtmlMentodBroadcastReceiverString);
        mContext.registerReceiver(NativeCallHtmlreceiver, filterNativeCallHtmlreceiver);
    }

    @JavascriptInterface
    public void alert(String str) {
        MessageUtils.alertMessageCENTER("");
    }

    /**
     * html调用本地复制文本
     *
     * @param copyString 带复制文字
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @JavascriptInterface
    public void copy(String copyString) {
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (CommonUtils.getSDK() > 10) {
            cm.setPrimaryClip(ClipData.newPlainText(null, copyString));
        } else {
            cm.setText(copyString);
        }
        MessageUtils.alertMessageCENTER("已成功为您复制:" + copyString);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @JavascriptInterface
    public void turntosafari(String copyString) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(copyString));
        mContext.startActivity(webIntent);
    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    @JavascriptInterface
//    public void turntocharge() {
//        Intent webIntent = new Intent(mContext, ChongZhiActivity.class);
//        mContext.startActivity(webIntent);
//    }


    /**
     * @param type           1:QQ空间 2:QQ好友 ,3微信朋友圈 4.微信好友
     * @param shareTitle     分享标题
     * @param shareUrl       分享链接
     * @param shareText      分享内容
     * @param image          缩略图链接
     * @param callHtmlMethod 回调js方法名
     */
    @JavascriptInterface
    public void htmlCallNativeShare(final int type, final String shareTitle, final String shareUrl, final String shareText, String image, String callHtmlMethod) {
        mShareImageUrl = image;
        final IUiListener qZoneShareListener = new IUiListener() {
            @Override
            public void onCancel() {
                MessageUtils.alertMessageCENTER("分享已取消");
            }

            @Override
            public void onError(UiError e) {
            }

            @Override
            public void onComplete(Object response) {
                MessageUtils.alertMessageCENTER("分享成功");
                nativeCallWebView(JSListener.this.callHtmlMethod);
            }
        };

        this.callHtmlMethod = callHtmlMethod;
        if (TextUtils.isEmpty(mShareImageUrl)) {
            if (!new File(mShareImageUrl).exists()) {

            }
        } else {
            if (mShareImageUrl.startsWith("http")) {
                ImageView imageView = new ImageView(mContext);
                ((TakeoutApplication) mContext.getApplicationContext()).getImageLoader().displayImage(mShareImageUrl, imageView, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        shareBitmap = Bitmap.createScaledBitmap(loadedImage, 100, 100, true);

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });
            } else {
                shareBitmap = BitmapFactory.decodeFile(mShareImageUrl);

            }
        }
    }

    @JavascriptInterface
    public void nativeCallWebView(String methodName) {
        mWebView.loadUrl("javascript:" + methodName);
    }

    public void onDestory() {
        if (shareSucessfulBroadCastReceiver != null) {
            mContext.unregisterReceiver(shareSucessfulBroadCastReceiver);
        }

        if (NativeCallHtmlreceiver != null) {
            mContext.unregisterReceiver(NativeCallHtmlreceiver);
        }
    }

    class ShareSucessfulBroadCastReceiver extends BroadcastReceiver {
        public final static String ACTION = "com.share.action";

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onReceive(final Context context, Intent intent) {
            String apiUrl = PreferenceUtils.getInstance(context).getString("cburl", "cburl");
            if (TextUtils.isEmpty(apiUrl)) {
                nativeCallWebView(PreferenceUtils.getInstance(context).getString("cb", "cb") + "()");
                apiUrl = apiUrl + "!uid_" + ((TakeoutApplication) context.getApplicationContext()).getUserInfo().id;
            } else {
                HashMap<String, String> map = new HashMap<>();
                apiUrl = apiUrl + "!uid_" + ((TakeoutApplication) context.getApplicationContext()).getUserInfo().id;
                Uri uriApi = Uri.parse(apiUrl.replaceAll("_", "=").replaceAll("!", "&"));
                Set<String> set = uriApi.getQueryParameterNames();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    map.put(key, uriApi.getQueryParameter(key));
                }
                map.put("uid", ((TakeoutApplication) context.getApplicationContext()).getUserInfo().id + "");
                new MyJsonRequestListener(context, Request.Method.POST, uriApi.toString().split("[?]")[0], map) {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                        nativeCallWebView(PreferenceUtils.getInstance(context).getString("cb", "cb") + "('error')");
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        super.onResponse(response);
                        nativeCallWebView(PreferenceUtils.getInstance(context).getString("cb", "cb") + "('" + response.toString() + "')");
                    }

                };

            }
        }
    }

    class NativeCallHtmlMentodBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


        }
    }

}
