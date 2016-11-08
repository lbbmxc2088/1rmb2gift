package com.muan.takeout.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muan.takeout.R;
import com.muan.takeout.Widget.ListViewForScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/8 13:56
 * 充值
 */
public class ChargeActivity extends BaseActivity {

    @BindView(R.id.title_back)
    TextView titleBack;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.tv_charge_10)
    TextView tvCharge10;
    @BindView(R.id.tv_charge_20)
    TextView tvCharge20;
    @BindView(R.id.tv_charge_30)
    TextView tvCharge30;
    @BindView(R.id.tv_charge_50)
    TextView tvCharge50;
    @BindView(R.id.tv_charge_100)
    TextView tvCharge100;
    @BindView(R.id.et_charge_num)
    EditText etChargeNum;
    @BindView(R.id.lv_charge_channel)
    ListViewForScrollView lvChargeChannel;
    @BindView(R.id.btn_charge_submit)
    Button btnChargeSubmit;

    private String mCharNum;
    private View mLastSelectView;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //初始化默认选中10元
        mLastSelectView = tvCharge10;
        tvCharge10.setSelected(true);
        mCharNum = "10";
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_charge;
    }


    @OnClick({R.id.title_back, R.id.tv_charge_10, R.id.tv_charge_20, R.id.tv_charge_30, R.id.tv_charge_50, R.id.tv_charge_100, R.id.et_charge_num, R.id.btn_charge_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.tv_charge_10:
            case R.id.tv_charge_20:
            case R.id.tv_charge_30:
            case R.id.tv_charge_50:
            case R.id.tv_charge_100:
            case R.id.et_charge_num:
                setChargeMoney(view);
                break;
            case R.id.btn_charge_submit:
                break;
        }
    }

    private void setChargeMoney(View v) {
        if (!v.isSelected()) {
            v.setSelected(true);
            mLastSelectView.setSelected(false);
            mLastSelectView = v;
        }
        if (v == etChargeNum) {
            etChargeNum.setCursorVisible(true);
            //切换后将EditText光标置于末尾
            CharSequence charSequence = etChargeNum.getText();
            if (charSequence instanceof Spannable) {
                Spannable spanText = (Spannable) charSequence;
                Selection.setSelection(spanText, charSequence.length());
            }
        } else {
            etChargeNum.setCursorVisible(false);
            if (mActivity.getWindow().getDecorView().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(etChargeNum.getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER || event.getKeyCode() == KeyEvent.KEYCODE_SEARCH) {
            /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
