package com.muan.takeout.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 类描述：
 * 创建人：Muan
 * 创建时间：15/10/13 19:02
 */
public class SDKUtils {
    /**获取当前版本号*/
    public static int getVersonCode(Context mContext){
        int versonCode=0;
        try {
            PackageManager mPackageManager=mContext.getPackageManager();
            PackageInfo mPackageInfo=mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            versonCode=mPackageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versonCode;
    }

    /**获取当前版本名*/
    public static String getVersonName(Context mContext){
        String versonCode="";
        try {
            PackageManager mPackageManager=mContext.getPackageManager();
            PackageInfo mPackageInfo=mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            versonCode=mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versonCode;
    }
}
