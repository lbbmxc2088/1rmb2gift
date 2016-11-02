package com.muan.takeout.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${Muan} on 2016/6/11.
 * 时间工具
 */
public class TimeTools {
    /**
     * 获取晒单时间
     *
     * @param timestamp
     * @return
     */
    public static String getSDTime(String timestamp) {
        try {
            long time = TimeHelper.getTimeInt(timestamp);
            long currentSeconds = System.currentTimeMillis() / 1000;
            long timeGap = currentSeconds - time;// 与现在时间相差秒数
            String timeStr = null;
            if (timeGap >= 24 * 60 * 60) {// 1天以上
                int days = (int) (timeGap / (24 * 60 * 60));
                if (days >= 30) {
                    if (days >= 365) {
                        timeStr = days / 365 + "年前";
                    } else {
                        timeStr = days / 30 + "个月前";
                    }
                } else {
                    timeStr = days + "天前";
                }
            } else if (timeGap >= 60 * 60) {// 1小时-24小时
                timeStr = timeGap / 3600 + "小时前";
            } else if (timeGap >= 60 && timeGap < 3600) {// 1分钟-59分钟
                timeStr = timeGap / 60 + "分钟前";
            } else if (timeGap >= 0 && timeGap < 60) {// 1秒钟-59秒钟
                timeStr = "刚刚";
            } else {
                return "";
                // throw new Exception();
            }
            return timeStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param -MM-dd HH:mm:ss 时间格式
     * @return
     */
    public static String getTimeString(String str) {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat(str);
        Date date = new Date(time);
        return format.format(date);
    }

    public static String[] WEEKS = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取时和分时间c串
     */
    public static String getTimeHM() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date(time);
        return format.format(date);

    }

    /**
     * 获取月日时间c串
     */
    public static String getTimeMD() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("M月d日");
        Date date = new Date(time);
        return format.format(date);
    }

    /**
     * 获取星期几时间c串
     */
    public static String getTimeWeek() {
        long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return WEEKS[day];
    }

    /**
     * 将毫秒数转化为 DD:SS:ss
     * SS秒 ss毫秒
     *
     * @param time
     * @return
     */
    public static String longSecondToTime(long time) {
        int h = (int) (time / 3600 / 1000);
        int m = (int) ((time / 1000 - h * 60 * 60) / 60);
        int s = (int) (time / 1000 % 60);
        int ms = (int) (time / 10 % 100);

        if (h > 0) {
            return String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s) +  ":" + String.format("%02d", ms);
        } else {
            return String.format(String.format("%02d", m) + ":" + String.format("%02d", s) + ":" + String.format("%02d", ms));
        }
    }
}
