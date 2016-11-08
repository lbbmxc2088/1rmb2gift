package com.muan.takeout.Widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.muan.takeout.Adapter.RecomAdapter;
import com.muan.takeout.Model.RecomEntity;
import com.muan.takeout.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/4 09:15
 * 推荐商品
 */
public class RecomView extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.rcv_recom)
    RecyclerView mrcv_recom;
    @BindView(R.id.bt_recom_tobuy)
    Button mBt_tobuy;
    List<RecomEntity> mList;
    RecomAdapter mRecomAdapter;

    public RecomView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_recom, this);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        mRecomAdapter = new RecomAdapter(mList, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mrcv_recom.setLayoutManager(manager);
        mrcv_recom.setAdapter(mRecomAdapter);
        initData();
    }


    //猜你喜欢数据
    private void initData() {
        for (int i = 0; i < 10; i++) {
            RecomEntity entity = new RecomEntity();
            entity.setGoodsName("iphone7");
            entity.setMaxtimes(7000);
            entity.setCurrenttimes(i * 100);
            mList.add(entity);
        }
        mRecomAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.bt_recom_tobuy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recom_tobuy:
                break;
        }
    }
}
