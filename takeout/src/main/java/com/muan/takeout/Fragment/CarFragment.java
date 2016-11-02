package com.muan.takeout.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muan.takeout.R;

import butterknife.ButterKnife;

/**
 * Created by ${Muan} on 2016/10/31 17:46
 */
public class CarFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_car, container, false);
        ButterKnife.bind(this,mFragmentView);
        return mFragmentView;
    }

    @Override
    public void onClick(View v) {

    }
}
