package com.muan.takeout.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeHelper {



    private static final String TAG = "TimeHelper";
    private static SimpleDateFormat sdfday = new SimpleDateFormat("MM/dd");
    public static final int BEFORE_TODAY = -1;
    public static final int TODAY = 0;
    public static final int AFTER_TODAY = 1;

    public static String friendlyTime(String timestamp) {
        try {
            return friendlyTime(Integer.valueOf(timestamp));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "刚刚";

    }

    public static boolean isBeforeToday(String date) {
        String[] str = date.split("-");
        try {
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            Calendar cal = Calendar.getInstance();

            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            if (year < currentYear)
                return true;
            else if (month < currentMonth)
                return true;
            else if (day < currentDay)
                return true;
            else
                return false;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static int isBeforeToday(long ctime) {
        String date = getStandardTimeWithYeay(ctime);
        String[] str = date.split("-");
        try {
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            Calendar cal = Calendar.getInstance();

            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            int currentDay = cal.get(Calendar.DAY_OF_MONTH);
            if (year < currentYear) {
                return BEFORE_TODAY;
            } else if (year > currentYear) {
                return AFTER_TODAY;
            } else {
                if (month < currentMonth) {
                    return BEFORE_TODAY;
                } else if (month > currentMonth) {
                    return AFTER_TODAY;
                } else if (day < currentDay) {
                    return BEFORE_TODAY;
                } else if (day > currentDay) {
                    return AFTER_TODAY;
                } else
                    return TODAY;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return TODAY;
    }

    public static String friendlyTime(int timestamp) throws Exception {

        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数

        long toZero = currentSeconds / (24 * 60 * 60) * (24 * 60 * 60);
        long todayGap = currentSeconds - toZero;

        String timeStr = null;
        Log.d(TAG, "timeGap=" + timeGap);
        if (timeGap >= 24 * 60 * 60 || timeGap > todayGap) {// 1天以上
            // timeStr = timeGap/(24*60*60)+"天前";
            timeStr = getStandardTimeWithDate(timestamp);
        } else if (timeGap >= 60 * 60 && timeGap < todayGap) {// 1小时-24小时
            timeStr = "今天  " + getStandardTimeWithHour(timestamp);
        } else if (timeGap >= 60 && timeGap < 3600) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else if (timeGap >= 0 && timeGap < 60) {// 1秒钟-59秒钟
            timeStr = "刚刚";
        } else {
            throw new Exception();
        }
        return timeStr;
    }

    public static String friendlyTimeFromeStringTime(String timeTemp)
            throws Exception {

        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - getTimeInt(timeTemp);// 与现在时间相差秒数

        long toZero = currentSeconds / (24 * 60 * 60) * (24 * 60 * 60);
        long todayGap = currentSeconds - toZero;

        String timeStr = null;
        if (timeGap > 24 * 60 * 60 || timeGap > todayGap) {// 1天以上
            // timeStr = timeGap/(24*60*60)+"天前";
            timeStr = getStandardTimeWithDate(getTimeInt(timeTemp));
        } else if (timeGap > 60 * 60 && timeGap < todayGap) {// 1小时-24小时
            timeStr = "今天  " + getStandardTimeWithHour(getTimeInt(timeTemp));
        } else if (timeGap > 60 && timeGap < 3600) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else if (timeGap > 0 && timeGap < 60) {// 1秒钟-59秒钟
            timeStr = "刚刚";
        } else {
            throw new Exception();
        }
        return timeStr;
    }

    public static String getStandardTimeWithYeay(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getStandardTimeWithYeay2(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getStandardTimeWithYeaybyPoint(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getStandardTimeWithDay(long timestamp) {
        Date date = new Date(timestamp * 1000);
        try {
            return sdfday.format(date);

        } catch (Exception e) {
            // TODO: handle exception
            return "没有时间";
        }
    }

    public static String getStandardTimeWithDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getDateWithTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getStandardTimeWithHour(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static long getTimeInt(String timeTemp) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(timeTemp);
        return date.getTime() / 1000;

    }

    public static String getStandardTimeWithSen(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }


    public static int getDate(Date date){

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK)-1;
        if(week == 0)
            return week=7;
        else
            return week;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }



}
