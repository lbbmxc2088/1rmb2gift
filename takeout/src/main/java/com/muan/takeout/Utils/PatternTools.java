package com.muan.takeout.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${Muan} on 2016/6/13.
 * 正则式判断工具类
 */
public class PatternTools {
    public static boolean isPhoneNumber(String number) {
        try {
            Pattern p = Pattern
                    .compile("1[3|4|5|7|8]\\d{9}");
            Matcher m = p.matcher(number);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isQQ(String qq) {
        try {
            Pattern p = Pattern
                    .compile("[1-9][0-9]{4,12}");
            Matcher m = p.matcher(qq);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPassword(String pw) {
        try {
            Pattern p = Pattern
                    .compile("[A-Za-z0-9_]{6,16}");
            Matcher m = p.matcher(pw);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isYanzhengma(String yzm) {
        try {
            Pattern p = Pattern
                    .compile("\\d{6}");
            Matcher m = p.matcher(yzm);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }
}