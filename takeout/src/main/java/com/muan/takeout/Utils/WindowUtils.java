package com.muan.takeout.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

/**
 * 项目名称：newzeroshop
 * 类描述：
 * 创建人：colorful
 * 创建时间：15/10/13 18:59
 * 修改人：colorful
 * 修改时间：15/10/13 18:59
 * 修改备注：
 */
public class WindowUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getWIndowWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (SDKUtils.getVersonCode(mContext) > 12) {
            Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            return point.x;
        } else {
            return wm.getDefaultDisplay().getWidth();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getWindowHeigh(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (SDKUtils.getVersonCode(mContext) > 12) {
            Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            return point.y;
        } else {
            return wm.getDefaultDisplay().getHeight();
        }
    }
}
