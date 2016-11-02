package com.muan.takeout.Utils.volley;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by Muan on 16/10/31.
 */
public class MyJsonRequest extends MyRequest<JSONObject> {
    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Creates a new request.
     * @param method GET/POST
     * @param url url地址
     * @param commonParams 通用参数，主要用于放到http请求的header里面
     * @param mParams 个性参数，每个接口自已独特的参数
     * @param listener 接收JSON response的监听
     * @param errorListener 错误监听，如果为null,听不监听错误.
     */
    public MyJsonRequest(Context mContext, int method, String url, HashMap<String, String> commonParams, HashMap<String, String> mParams, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(mContext, method, url, commonParams, mParams, listener, errorListener);
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }


}
