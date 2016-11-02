package com.muan.takeout.Widget.xRefresh;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.muan.takeout.R;
import com.muan.takeout.TakeoutApplication;
import com.muan.takeout.Utils.SettingUtils;
import com.muan.takeout.Utils.StringUtil;

import java.util.Calendar;
import java.util.Random;

public class XRefreshViewHeader extends LinearLayout implements IHeaderCallBack {
    private ViewGroup mContent;
    private ImageView mArrowImageView;
    private ImageView mOkImageView;
    private ProgressBar mProgressBar;
    private TextView mHintTextView;
    private TextView mHeaderTimeTextView;
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    private final int ROTATE_ANIM_DURATION = 180;
    private RelativeLayout mLayout_bg;
    private ImageView mIv_bg;
    private ImageView mIv_anim;
    //下拉刷新动画 根据需求开启
//    AnimationDrawable animation_up;
//    AnimationDrawable animation_down;
    public XRefreshViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XRefreshViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContent = (ViewGroup) LayoutInflater.from(context).inflate(
                R.layout.xrefreshview_header, this);
        mArrowImageView = (ImageView) findViewById(R.id.xrefreshview_header_arrow);
        mOkImageView = (ImageView) findViewById(R.id.xrefreshview_header_ok);
        mHintTextView = (TextView) findViewById(R.id.xrefreshview_header_hint_textview);
        mHeaderTimeTextView = (TextView) findViewById(R.id.xrefreshview_header_time);
        mProgressBar = (ProgressBar) findViewById(R.id.xrefreshview_header_progressbar);
        mLayout_bg = (RelativeLayout) findViewById(R.id.xrefreshview_header_text);
        mIv_bg = (ImageView) findViewById(R.id.xrefreshview_headbg);
        mIv_anim = (ImageView) findViewById(R.id.iv_head_anim);
        if (StringUtil.isEmpty(SettingUtils.getDEFAULT_REFRESHBGURL(getContext()))) {
            mIv_bg.setImageResource(R.mipmap.icon_pulltorefresh);
        } else {
            ((TakeoutApplication) (getContext().getApplicationContext())).getImageLoader().displayImage(SettingUtils.getDEFAULT_REFRESHBGURL(getContext()), mIv_bg);
        }

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    public void setRefreshTime(long lastRefreshTime) {
        // 获取当前时间
        Calendar mCalendar = Calendar.getInstance();
        long refreshTime = mCalendar.getTimeInMillis();
        long howLong = refreshTime - lastRefreshTime;
        int minutes = (int) (howLong / 1000 / 60);
        String refreshTimeText = null;
        Resources resources = getContext().getResources();
        if (minutes < 1) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_justnow);
        } else if (minutes < 60) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_minutes_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes);
        } else if (minutes < 60 * 24) {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_hours_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes / 60);
        } else {
            refreshTimeText = resources
                    .getString(R.string.xrefreshview_refresh_days_ago);
            refreshTimeText = Utils.format(refreshTimeText, minutes / 60 / 24);
        }
        // mHeaderTimeTextView.setText(refreshTimeText);
    }

    /**
     * hide footer when disable pull load more
     */
    public void hide() {
        setVisibility(View.GONE);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void onStateNormal() {
        mProgressBar.setVisibility(View.GONE);
        mArrowImageView.setVisibility(View.VISIBLE);
        mOkImageView.setVisibility(View.GONE);
        mArrowImageView.startAnimation(mRotateDownAnim);
        mHintTextView.setText(R.string.xrefreshview_header_hint_normal);
//        animation_down = (AnimationDrawable) mIv_anim.getBackground();
//        animation_down.setOneShot(true);
//        animation_down.start();
    }

    @Override
    public void onStateReady() {
        mProgressBar.setVisibility(View.GONE);
        mOkImageView.setVisibility(View.GONE);
        mArrowImageView.setVisibility(View.VISIBLE);
        mArrowImageView.clearAnimation();
        mArrowImageView.startAnimation(mRotateUpAnim);
        if (StringUtil.isEmpty(SettingUtils.getDEFAULT_REFRESHTIP(getContext()))) {
            mHintTextView.setText(R.string.xrefreshview_header_hint_ready);
        } else {
            try {
                String[] tips = SettingUtils.getDEFAULT_REFRESHTIP(getContext()).split("#");
                if (tips.length > 1) {
                    mHintTextView.setText(tips[new Random().nextInt(tips.length - 1)]);
                } else {
                    mHintTextView.setText(tips[0]);
                }
            } catch (Exception e) {
                mHintTextView.setText(R.string.xrefreshview_header_hint_ready);
            }
        }
        mHeaderTimeTextView.setVisibility(View.GONE);
    }

    @Override
    public void onStateRefreshing() {
        mArrowImageView.clearAnimation();
        mArrowImageView.setVisibility(View.GONE);
        mOkImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        if ((AnimationDrawable) mIv_anim.getBackground() != null && ((AnimationDrawable) ((AnimationDrawable) mIv_anim.getBackground())).isRunning()) {
            ((AnimationDrawable) mIv_anim.getBackground()).stop();
        }
        if (StringUtil.isEmpty(SettingUtils.getDEFAULT_REFRESHTIP(getContext()))) {

        } else {
            mHintTextView.setText(R.string.xrefreshview_header_hint_loading);
        }
//        animation_up = (AnimationDrawable) mIv_anim.getBackground();
//        animation_up.setOneShot(false);
//        animation_up.start();

    }

    @Override
    public void onStateFinish() {
        mArrowImageView.setVisibility(View.GONE);
        mOkImageView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        if (StringUtil.isEmpty(SettingUtils.getDEFAULT_REFRESHTIP(getContext()))) {

        } else {
            mHintTextView.setText(R.string.xrefreshview_header_hint_loaded);
        }
//        if (animation_up != null && animation_up.isRunning()) {
//            animation_up.stop();
//            mIv_anim.setBackgroundResource(R.drawable.refresh_anim);
//        }
        mHeaderTimeTextView.setVisibility(View.GONE);
    }

    @Override
    public void onHeaderMove(double offset, int offsetY, int deltaY) {

    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
