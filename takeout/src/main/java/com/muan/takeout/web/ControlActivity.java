package com.muan.takeout.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.muan.takeout.Activity.BaseActivity;
import com.muan.takeout.TakeoutApplication;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


/**
 * Created by ${Muan} on 2016/6/19.
 */
public class ControlActivity extends Activity {

    Intent intent;
    Uri uri;
    Bitmap shareBitmap;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onResume() {
        super.onResume();

        intent = getIntent();
        uri = getIntent().getData();

    }

    public void share_act() {
        ImageView imageView = new ImageView(this);
        ((TakeoutApplication) getApplicationContext()).getImageLoader().displayImage(uri.getQueryParameter("img"), imageView, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                finish();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                finish();
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shareBitmap != null) {
            shareBitmap.recycle();
        }
    }
}
