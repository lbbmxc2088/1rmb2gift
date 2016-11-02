package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.muan.takeout.Adapter.HomeMenuPagerAdapter;
import com.muan.takeout.Fragment.BaseFragment;
import com.muan.takeout.Fragment.CarFragment;
import com.muan.takeout.Fragment.FindFragment;
import com.muan.takeout.Fragment.MainFragment;
import com.muan.takeout.Fragment.MineFragment;
import com.muan.takeout.Fragment.NewsFragment;
import com.muan.takeout.R;
import com.muan.takeout.Utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ${Muan} on 2016/10/28 17:24
 */
public class MainActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private HomeMenuPagerAdapter mHomeMenuPagerAdapter;
    /**
     * viewpager
     */
    @BindView(R.id.vpage_home)
    public ViewPager mContentViewpager;

    /**
     * 菜单容器
     */
    @BindView(R.id.rg_homemenu)
    public RadioGroup mRgHomeMenu;

    /**
     * 菜单选项
     */
    @BindView(R.id.rb_homemenu_lqg)
    public RadioButton mRbHomeMenuLQG;


    @BindView(R.id.rb_homemenu_zxjx)
    public RadioButton mRbHomeMenuZXJX;

    @BindView(R.id.rb_homemenu_fx)
    public RadioButton mRbHomeMenuFX;

    /**
     * 购物车
     */
    @BindView(R.id.rb_homemenu_sd)
    public RadioButton mRbHomeMenuCar;

    /**
     * 我的菜单选项
     */
    @BindView(R.id.rb_homemenu_wd)
    public RadioButton mRbHomeMenuWD;

    /**
     * 首页菜单Fragment 集合
     */
    private List<BaseFragment> mFragments;

    private MainFragment mMainFragment;
    private NewsFragment mNewsFragment;
    private CarFragment mCarFragment;
    private MineFragment mMineFragment;
    private FindFragment mFindFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        mMainFragment = new MainFragment();
        mNewsFragment = new NewsFragment();
        mCarFragment = new CarFragment();
        mMineFragment = new MineFragment();
        mFindFragment = new FindFragment();

        mFragments = new ArrayList<>();
        mFragments.add(mMainFragment);
        mFragments.add(mNewsFragment);
        mFragments.add(mFindFragment);
        mFragments.add(mCarFragment);
        mFragments.add(mMineFragment);


        mHomeMenuPagerAdapter = new HomeMenuPagerAdapter(getSupportFragmentManager(), mFragments);
        mContentViewpager.addOnPageChangeListener(this);
        mContentViewpager.setOffscreenPageLimit(5);
        mContentViewpager.setAdapter(mHomeMenuPagerAdapter);
        mContentViewpager.setCurrentItem(0);
        mRgHomeMenu.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_homemenu_lqg:
                mContentViewpager.setCurrentItem(0);
                break;
            case R.id.rb_homemenu_zxjx:
                mContentViewpager.setCurrentItem(1);
                break;
            case R.id.rb_homemenu_fx:
                mContentViewpager.setCurrentItem(2);
                break;
            case R.id.rb_homemenu_sd:
                mContentViewpager.setCurrentItem(3);
                break;
            case R.id.rb_homemenu_wd:
                mContentViewpager.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRbHomeMenuLQG.setChecked(true);
                break;
            case 1:
                mRbHomeMenuZXJX.setChecked(true);
                break;
            case 2:
                mRbHomeMenuFX.setChecked(true);
                break;
            case 3:
                mRbHomeMenuCar.setChecked(true);
                break;
            case 4:
                mRbHomeMenuWD.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
