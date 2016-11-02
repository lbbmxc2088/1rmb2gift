package com.muan.takeout.Utils.volley;

import android.content.Context;
import android.content.pm.PackageManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.muan.takeout.TakeoutApplication;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoweiguo on 16/6/8.
 */
public abstract class MyRequestListener<T> implements Response.Listener<T>, Response.ErrorListener {

    private TakeoutApplication mApplication;
    private Context mContext;

    public MyRequestListener(Context mContext, int method, String url, HashMap<String, String> params) {
        this.mContext = mContext;
        this.mApplication = (TakeoutApplication) mContext.getApplicationContext();
        StringBuffer urlParams = new StringBuffer();
        urlParams.append(url);

        HashMap<String, String> commonParams = new HashMap<>();

        if (method == Request.Method.GET || method == Request.Method.DELETE) {
            boolean flag = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    flag = false;
                    try {
                        urlParams.append("?").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        urlParams.append("?").append(entry.getKey()).append("=").append(entry.getValue());   // @todo
                        e.printStackTrace();
                    }
                } else {
                    try {
                        urlParams.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        urlParams.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                        e.printStackTrace();
                    }
                }
            }
        }

            MyRequest<JSONObject> myRequest = createRequest(mContext, method, urlParams.toString(), commonParams, params, this, this);
            mApplication.getRequestQueue().add(myRequest);
            onStart();

    }

    protected abstract MyRequest createRequest(Context mContext, int method, String url, HashMap<String, String> commonParams, HashMap<String, String> mParams, Response.Listener<T> mResponseListener, Response.ErrorListener listener);


    /**
     * 开始请求网络
     */
    public void onStart() {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(T response) {
    }
}
