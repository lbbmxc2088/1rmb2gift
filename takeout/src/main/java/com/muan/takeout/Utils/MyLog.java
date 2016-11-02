package com.muan.takeout.Utils;

import android.util.Log;

/**
 *
 * @author Muan
 *
 * 时间: 2016年10月
 */
public class MyLog {

    private MyLog() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //正式服记得关
    public static boolean isDebug = true;
    private static final String TAG = "Try :";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            msg =  getCaller() + msg;
            Log.i(tag, msg);
        }
    }


    // 内部方法，取得调用方的类名和行数
    private static String getCaller() {
        StackTraceElement stacks[] = (new Throwable()).getStackTrace();
        StackTraceElement stack = stacks[2];
        return "[" + stack.getClassName() + " " + stack.getLineNumber() + "]";
    }


}
