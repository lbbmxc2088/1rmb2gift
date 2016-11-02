package com.muan.takeout.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muan.takeout.Activity.LoginActivity;
import com.muan.takeout.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/10/31 17:49
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.login)
    TextView mTv_login;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, mFragmentView);
        return mFragmentView;
    }

    @OnClick({R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
