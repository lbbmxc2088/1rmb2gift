package com.muan.takeout.Utils.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muan on 16/10/31.
 */

public abstract class MyRequest<T> extends com.android.volley.Request<T> {
    MyRequest myRequest;
    private Context mContext;

    protected HashMap<String, String> mParams;
    private HashMap<String, String> mheader;


    protected Response.Listener<T> mResponseListener = null;


    public MyRequest(Context mContext, int method, String url,  HashMap<String, String> commonParams, HashMap<String, String> params, Response.Listener<T> mResponseListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.mheader = commonParams;
        this.mParams = params;
        this.mResponseListener = mResponseListener;
        this.mContext = mContext;
        setRetryPolicy(new DefaultRetryPolicy(5000, 0, 0));
    }


    @Override
    protected void deliverResponse(T response) {
        if (this.mResponseListener != null) {
            this.mResponseListener.onResponse(response);
        }
    }
    /*
     *  通信结束后调用的
     *  可以对返回的reponse做处理， NetworkResponse里面包括状态码，头信息，内容数据，是否缓存在本地，花费的时间（ms）等内容
     */
    @Override
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);



    // http请求post参数
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.mParams;
    }

    // http请求头参数
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        return this.getHeaders();
    }


    @Override
    public String getParamsEncoding() {
        return "UTF-8";
    }

}
