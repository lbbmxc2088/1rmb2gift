package com.muan.takeout.Utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast信息提示
 *
 * @author Muan
 * @time 2015年10月31日
 */
public final class MessageUtils {
    private static Toast mToast;

    private MessageUtils() {
    }

    public static void init(Context context) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }


    public static void alertMessageBottom(String msg) {
        if (StringUtil.isEmpty(msg)) {
            return;
        }
        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void alertMessageTop(String msg) {
        if (StringUtil.isEmpty(msg)) {
            return;
        }
        mToast.setGravity(Gravity.TOP, 0, 0);
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void alertMessage(String msg, int gravity) {
        if (StringUtil.isEmpty(msg)) {
            return;
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void alertMessageCENTER(String msg) {
        if (StringUtil.isEmpty(msg)) {
            return;
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void alertLongMessageCENTER(String msg) {
        if (StringUtil.isEmpty(msg)) {
            return;
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }
}
