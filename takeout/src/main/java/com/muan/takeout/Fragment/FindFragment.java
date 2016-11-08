package com.muan.takeout.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.muan.takeout.Adapter.FindAdapter;
import com.muan.takeout.Model.FindEntity;
import com.muan.takeout.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${Muan} on 2016/10/31 17:52
 */
public class FindFragment extends BaseFragment {

    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.lv_actives)
    ListView mLv_Actives;

    List<FindEntity> mList;
    FindAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, mFragmentView);
        initView();
        return mFragmentView;
    }

    public void initView() {
        titleMiddle.setText("发现");
        mList = new ArrayList<>();
        mAdapter = new FindAdapter(mList, mActivity);
        mLv_Actives.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
