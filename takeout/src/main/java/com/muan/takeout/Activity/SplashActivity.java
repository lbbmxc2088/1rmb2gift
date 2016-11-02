package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.muan.takeout.Adapter.SplashAdapter;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.PreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by admin on 2016/10/28.
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.vp_guide)
    ViewPager mViewPager;
    @BindView(R.id.btn_enter)
    Button mBt_enter;
    // 定义ViewPager对象
//    @ViewInject(id = R.id.vp_guide)
//    private ViewPager mViewPager;
//    @ViewInject(id = R.id.btn_enter, click = true)
//    private Button mBt_enter;

    // 定义ViewPager适配器
    private SplashAdapter mAdapter;
    // 定义一个ArrayList来存放View
    private ArrayList<View> views;
    // 引导图片资源
    private static final int[] pics = {R.mipmap.daotu1, R.mipmap.daotu2, R.mipmap.daotu3, R.mipmap.daotu4};
    // 底部小点的图片
    private ImageView[] mPoints;
    // 记录当前选中位置
    private int currentIndex;

    @Override
    public int getContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        views = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            // View view = LayoutInflater.from(this).inflate(pics[i], null);
            ImageView view = new ImageView(this);
            view.setImageResource(pics[i]);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            views.add(view);
        }
        // 初始化adapter
        mAdapter = new SplashAdapter(views);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        initDots();
    }


    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        PreferenceUtils.getInstance(this).saveBoolean(FinalTools.FIRST_OPEN, false);
        finish();
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        mPoints = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            mPoints[i] = (ImageView) ll.getChildAt(i);
            mPoints[i].setEnabled(false);// 都设为灰色
            mPoints[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        mPoints[currentIndex].setPressed(true); // 设置为白色，即选中状态

    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        mPoints[position].setPressed(true);
        mPoints[currentIndex].setPressed(false);
        currentIndex = position;
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
        PreferenceUtils.getInstance(this).saveBoolean(FinalTools.FIRST_OPEN, false);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vp_guide:
                break;
            case R.id.btn_enter:
                startActivity(new Intent(this, MainActivity.class));
                this.finish();
                break;
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置
            if (pics.length - 1 >= 0) {
                if (position == pics.length - 1) {
                    mBt_enter.setVisibility(View.VISIBLE);
                } else {
                    mBt_enter.setVisibility(View.GONE);
                }
            }

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
        }

    }
}
