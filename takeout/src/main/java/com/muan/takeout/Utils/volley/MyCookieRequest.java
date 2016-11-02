package com.muan.takeout.Utils.volley;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import java.util.HashMap;

/**
 * Created by Muan on 16/10/31.
 */
public class MyCookieRequest extends MyRequest<String> {

    public MyCookieRequest(Context mContext, int method, String url, HashMap<String, String> commonParams, HashMap<String, String> mParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(mContext, method, url, commonParams, mParams, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // 下面的例子是取头信息里的cookie数据
        String mCookie;
        for (String s : response.headers.keySet()) {
            if (s.contains("Set-Cookie")) {
                mCookie = response.headers.get(s); break;
            }
        }
        return null;
    }
}
