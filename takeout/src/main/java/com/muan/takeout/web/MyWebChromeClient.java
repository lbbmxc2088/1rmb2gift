package com.muan.takeout.web;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by ${Muan} on 2016/6/19.
 * 辅助WebView处理JavaScript的对话框、网站图标、网站title、加载进度等
 */
public class MyWebChromeClient extends WebChromeClient {
    private ProgressBar mProgressBar;
    private TextView mTitle;

    public MyWebChromeClient(ProgressBar progressBar,TextView textView) {
        this.mProgressBar = progressBar;
        this.mTitle = textView;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (newProgress == 100) {
            mProgressBar.setProgress(newProgress);
            mProgressBar.setVisibility(View.GONE);
        } else {
            mProgressBar.setProgress(newProgress);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }
}
