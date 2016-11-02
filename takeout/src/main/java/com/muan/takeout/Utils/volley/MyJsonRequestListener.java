package com.muan.takeout.Utils.volley;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Muan on 16/10/31.
 */
public class MyJsonRequestListener extends MyRequestListener<JSONObject> {
    public MyJsonRequestListener(Context mContext, int method, String url, HashMap<String, String> params) {
        super(mContext, method, url, params);
    }

    @Override
    protected MyRequest createRequest(Context mContext, int method, String url, HashMap<String, String> commonParams, HashMap<String, String> mParams, Response.Listener<JSONObject> mResponseListener, Response.ErrorListener listener) {
        return new MyJsonRequest(mContext, method, url, commonParams, mParams, mResponseListener, listener);
    }

}
