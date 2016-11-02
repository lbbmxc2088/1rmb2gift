package com.muan.takeout.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *网络状态工具
 * 修改备注：
 */
public class NetWorkUtils {
    public static NetworkInfo getActiveNetwork(Context context){
        if (context == null)
            return null;
        ConnectivityManager mConnMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null)
            return null;
        NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); //
        return aActiveInfo;
    }
}
