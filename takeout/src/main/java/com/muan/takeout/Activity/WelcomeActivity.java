package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.MyLog;
import com.muan.takeout.Utils.PreferenceUtils;

/**
 * Created by ${Muan} on 2016/10/28 16:54
 */
public class WelcomeActivity extends BaseActivity {
    private static int SPLASH_HOLDTIME = 2000;

    @Override
    public int getContentLayout() {
        return R.layout.activity_welcome;
    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //判断是否是第一次开启应用
        boolean isFirstOpen = PreferenceUtils.getInstance(this).getBoolean(FinalTools.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, SPLASH_HOLDTIME);
    }


    @Override
    public void onClick(View v) {

    }
}
