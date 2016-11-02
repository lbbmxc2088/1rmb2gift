package com.muan.takeout.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import java.util.Calendar;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;


/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JpuchReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String title = bundle.getString(JPushInterface.EXTRA_TITLE);
		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			// TODO: 2016/6/20  保留待用
		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			// TODO: 2016/6/20  保留待用
		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

		} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
			//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
		} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
		} else {
		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("key:" + key + ", value:" + bundle.getInt(key));
			} else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("key:" + key + ", value:" + bundle.getBoolean(key));
			} else {
				sb.append("key:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

}
