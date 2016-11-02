package com.muan.takeout.Widget.BannerView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.muan.takeout.R;
import com.muan.takeout.Utils.ImageLoaderTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Bannerview Manager
 *
 * @author Muan
 *         <p/>
 *         时间: 2015年6月17日
 */
public class BannerManager implements OnPageChangeListener, OnClickListener {
    private ViewPager mViewPager = null;
    private RelativeLayout layout_out;
    private View mRootView = null;
    private BannerRound mBannerRound = null;
    private List<ImageView> mImageViews = null;
    private List<String> mUrls = null;
    BannerAdapter mBannerAdapter = null;
    private ImageLoader mImageloader;
    private int mCount = 0;
    private Context mContext;
    private Timer timer;
    private int nowSelect;
    private View mRefreshview;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    };
    public static final int HOME = 100;
    public static final int SHOP_DETAIL = 101;
    DepthPageTransformer mPageTansfrome;
    AlphaAnimation mAnimation;
    private int drawableId = R.mipmap.icon_buyall;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.startAnimation(mAnimation);
            nowSelect++;
            mViewPager.setPageTransformer(true, mPageTansfrome);
            mViewPager.setCurrentItem(nowSelect);

        }
    };

    public void setDefaulDrawable(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getHeight() {
        return layout_out.getHeight();
    }

    public int getWidth() {
        return layout_out.getWidth();
    }

    public interface BannerClickLintener {
        public void bannerClick(int position);
    }

    BannerClickLintener bannerClickLintener;

    public void setRefreshLayout(View view) {
        this.mRefreshview = view;
    }

    public BannerManager(View rootView, List<String> mUrls, ImageLoader mImageloader) {
        this.mRootView = rootView;
        this.mUrls = mUrls;
        this.mImageloader = mImageloader;
        mPageTansfrome = new DepthPageTransformer();
        mAnimation = new AlphaAnimation(0.1f, 1.0f);
        mAnimation.setDuration(500);
        init();
        initData();
        if (mRootView != null) {
            mRootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_UP:
                            mRefreshview.setEnabled(true);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            mRefreshview.setEnabled(true);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            mRefreshview.setEnabled(false);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            mRefreshview.setEnabled(false);
                            break;
                    }
                    return true;
                }
            });
        }

    }
    public BannerClickLintener getBannerClickLintener() {
        return bannerClickLintener;
    }


    public void setBannerClickLintener(BannerClickLintener bannerClickLintener) {
        this.bannerClickLintener = bannerClickLintener;
    }

    public void setUrls(List<String> url) {
        this.mUrls = url;
    }

    /**
     * 更新 Banner 数据
     */
    public void update() {
        if (mUrls != null) {
            mCount = mUrls.size();
        }
        if (mCount == 0) {
            layout_out.setVisibility(View.GONE);
        } else {
            layout_out.setVisibility(View.VISIBLE);
        }
        try {
            mBannerRound.setShowNum(mCount);
            if (mCount != 0) {
                mBannerRound.setCircleOn(0);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initData();
    }

    //首页或者商品详情
    public void setLayoutParams(int width, int height) {
        LayoutParams layoutParams = (LayoutParams) layout_out.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        layout_out.setLayoutParams(layoutParams);
    }



    public void init() {
        mContext = mRootView.getContext();
        layout_out = (RelativeLayout) mRootView.findViewById(R.id.rl_banner);
        mBannerRound = (BannerRound) mRootView.findViewById(R.id.bannerround);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpaper_banner);
        try {
            mBannerRound.setShowNum(mCount);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mImageViews = new ArrayList<ImageView>();
        mBannerAdapter = new BannerAdapter();
        mViewPager.setAdapter(mBannerAdapter);
        // mViewPager.setOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(mCount * 100);
        nowSelect = mCount * 100;
        mViewPager.setOffscreenPageLimit(1);
        try {
            mBannerRound.setCircleOn(0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        update();
    }

    public void initData() {
        ViewGroup.LayoutParams layoutParams = mViewPager
                .getLayoutParams();
        mImageViews.clear();
        for (int i = 0; i < (mCount * 4); i++) {
            ImageView imageView = new ImageView(mRootView.getContext());
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = LayoutParams.MATCH_PARENT;
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ScaleType.FIT_XY);
            mImageloader.displayImage(mUrls.get(i % mCount), imageView, ImageLoaderTools.getDisplayImageOptions(0, drawableId));
            imageView.setOnClickListener(this);
            imageView.setTag(i % mCount);
            mImageViews.add(imageView);
        }
        mBannerAdapter.notifyDataSetChanged();
        if (mUrls.size() > 1) {
            if (timer == null) {
                timer = new Timer();
                timer.schedule(timerTask, 4000, 4000);
            }
        }
    }

    class BannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mCount == 0 ? mCount : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(mImageViews.get(position % (mCount * 4)));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            try {
                view.addView(mImageViews.get(position % (mCount * 4)), 0);
            } catch (Exception ex) {
            }
            return mImageViews.get(position % (mCount * 4));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mRefreshview != null) {
            mRefreshview.setEnabled(state == ViewPager.SCROLL_STATE_IDLE);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {
        if (mRefreshview != null) {
            mRefreshview.setEnabled(true);
        }
        nowSelect = arg0;
        try {
            mBannerRound.setCircleOn(arg0 % mCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (bannerClickLintener != null) {
            bannerClickLintener.bannerClick((int) v.getTag());
        }
    }
}
