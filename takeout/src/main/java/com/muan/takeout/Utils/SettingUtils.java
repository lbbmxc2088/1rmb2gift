package com.muan.takeout.Utils;

import android.content.Context;

/**
 * 
 * 设置类
 * 
 * @author Ran
 *
 * 时间: 2015年7月8日
 */
public class SettingUtils {

	//是否是该版本第一次使用
	private  final static String APP_KEY_FIRST_USE="app_key_first_use";
	public static boolean appIsFirstUse(Context context){
		return PreferenceUtils.getInstance(context).getBoolean(APP_KEY_FIRST_USE, true);
	}
	public static  void setAppIsFirstUse(Context context,boolean value){
		PreferenceUtils.getInstance(context).saveBoolean(APP_KEY_FIRST_USE, value);
	}
	//是否是该版本第一次使用
	private final static String KEY_FIRST_USE="key_first_use_";
	public static boolean isFirstUse(Context context){
		return PreferenceUtils.getInstance(context).getBoolean(KEY_FIRST_USE + CommonUtils.getVersonCode(context), true);
	}
	public static  void setFirstUse(Context context,boolean value){
		PreferenceUtils.getInstance(context).saveBoolean(KEY_FIRST_USE + CommonUtils.getVersonCode(context), value);
	}

	private final static String KEY_FIRST_USE_ZFB = "KEY_FIRST_USE_ZFB";

	public static boolean isFirstUseZFB(Context context) {
		return PreferenceUtils.getInstance(context).getBoolean(KEY_FIRST_USE_ZFB, true);
	}

	public static void setFirstUseZFB(Context context, boolean value) {
		PreferenceUtils.getInstance(context).saveBoolean(KEY_FIRST_USE_ZFB, value);
	}
	
	//彩票地址
	private final static String KEY_LOTTERY="key_lottery";
	public static String getLottery(Context context){
		return PreferenceUtils.getInstance(context).getString(KEY_LOTTERY, "");
	}
	public static void setLottery(Context context,String value){
		PreferenceUtils.getInstance(context).saveString(KEY_LOTTERY, value);
	}
	
	//快递地址
	private final static String KEY_URL_EXPRESS="key_url_express";
	public static String getExpress(Context context){
		return PreferenceUtils.getInstance(context).getString(KEY_URL_EXPRESS, "");
	}
	public static void setExpress(Context context,String value){
		PreferenceUtils.getInstance(context).saveString(KEY_URL_EXPRESS, value);
	}

	//当前网络是否可用
	private final static String KEY_NET_WORK_STATUS="key_net_work_status";
	public static boolean isNetCanUse(Context context){
		return PreferenceUtils.getInstance(context).getBoolean(KEY_NET_WORK_STATUS , false);
	}
	public static  void setNetCanUse(Context context,boolean value){
		PreferenceUtils.getInstance(context).saveBoolean(KEY_NET_WORK_STATUS, value);
	}

	//欢迎页图片地址 hashcode 址
	private final static String KEY_WELCOME_URL="key_welcome_url";
	public static String getImageUrl(Context context){
		return PreferenceUtils.getInstance(context).getString(KEY_WELCOME_URL, null);
	}
	public static  void saveImageUrl(Context context,String value){
		PreferenceUtils.getInstance(context).saveString(KEY_WELCOME_URL, value);
	}
	public final static String KEY_WELCOME_URL_LOCAL = "key_welcome_local";

	public static String getImageLocal(Context context) {
		return PreferenceUtils.getInstance(context).getString(KEY_WELCOME_URL_LOCAL, null);
	}

	public static void saveImageLocal(Context context, String value) {
		PreferenceUtils.getInstance(context).saveString(KEY_WELCOME_URL_LOCAL, value);
	}


	/**
	 * 默认下拉刷新背景图
	 */
	private final static String KEY_REFRESH_BG_URL = "KEY_REFRESH_BG_URL";
	public static void saveDEFAULT_REFRESHBGURL(Context context, String str) {
		PreferenceUtils.getInstance(context).saveString(KEY_REFRESH_BG_URL, str);
	}

	public static String getDEFAULT_REFRESHBGURL(Context context) {
		return PreferenceUtils.getInstance(context).getString(KEY_REFRESH_BG_URL, "");
	}

	/**
	 * 默认下拉刷新提示語
	 */
	private final static String KEY_REFRESH_TIP = "KEY_REFRESH_TIP";
	public static void saveDEFAULT_REFRESHTIP(Context context, String str) {
		PreferenceUtils.getInstance(context).saveString(KEY_REFRESH_TIP, str);
	}

	public static String getDEFAULT_REFRESHTIP(Context context) {
		return PreferenceUtils.getInstance(context).getString(KEY_REFRESH_TIP, "");
	}


}
