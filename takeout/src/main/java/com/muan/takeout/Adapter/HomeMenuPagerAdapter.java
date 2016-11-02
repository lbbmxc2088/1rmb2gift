package com.muan.takeout.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.muan.takeout.Fragment.BaseFragment;

import java.util.List;


/**
 * 类描述：首页菜单切换适配器
 * 创建人：Muan
 * 创建时间：16/10/31 16:23
 */
public class HomeMenuPagerAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> mList;

    public HomeMenuPagerAdapter(FragmentManager fm, List<BaseFragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public BaseFragment getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
