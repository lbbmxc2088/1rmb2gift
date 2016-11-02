package com.muan.takeout.Utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * 字符串工具
 */

public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     */
    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }

        if ("".equals(str) || "null".equals(str) || "NULL".equals(str)) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static int calculatePlaces(String str) {
        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if ((c >= 0x0391 && c <= 0xFFE5))  //中文字符
            {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) //英文字符
            {
                m = m + 1;
            }
        }
        return m;
    }

    public static String getAssetsFile(Context context, String fileName) {
        // 将json文件读取到buffer数组中
        InputStream is;
        String strJson = null;
        try {
            is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            // 将字节数组转换为以utf-8编码的字符串
            strJson = new String(buffer, "utf-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strJson;
    }

    /**
     * 将数字数组 转化为List
     *
     * @param mArrays
     * @param maxNums List的数量上限
     * @return
     */
    public static ArrayList array2List(String[] mArrays, int maxNums) {
        ArrayList mList = new ArrayList();
        if (mArrays.length <= 0) {
            return mList;
        } else {
            for (int i = 0; i < mArrays.length; i++) {
                if (i >= maxNums) {
                    break;
                }
                mList.add(mArrays[i]);
            }
            return mList;
        }

    }
}
