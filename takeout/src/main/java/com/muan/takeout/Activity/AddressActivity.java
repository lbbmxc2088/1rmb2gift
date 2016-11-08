package com.muan.takeout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.muan.takeout.Adapter.AddressAdapter;
import com.muan.takeout.Model.AddressEntity;
import com.muan.takeout.R;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by ${Muan} on 2016/11/7 14:00
 * 地址列表
 */
public class AddressActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.lv_address)
    ListView lvAddress;
    @BindView(R.id.xrfv_address)
    XRefreshView xrfvAddress;
    List<AddressEntity> mList;
    AddressAdapter mAdapter;

    @Override
    public void initData() {
        mList = new ArrayList<>();
        mAdapter = new AddressAdapter(mList, this);
        lvAddress.setAdapter(mAdapter);
    }

    @Override
    public void initView() {
        titleMiddle.setText("地址管理");
        titleRight.setText("添加");
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_address;
    }

    @OnClick({R.id.title_back, R.id.title_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.title_right:
                startActivity(new Intent(this, AddressUpdataActivity.class));
                break;
        }
    }

    @OnItemClick({R.id.lv_address})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        startActivity(new Intent(this, AddressUpdataActivity.class));
    }
}
