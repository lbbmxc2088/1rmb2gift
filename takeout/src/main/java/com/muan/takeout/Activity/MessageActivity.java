package com.muan.takeout.Activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.muan.takeout.Adapter.MessageAdapter;
import com.muan.takeout.Model.MessageEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.IpConfig;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.volley.MyJsonRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by ${Muan} on 2016/11/1 13:08
 */
public class MessageActivity extends BaseActivity {
    @BindView(R.id.title_back)
    public TextView mTv_back;
    @BindView(R.id.title_middle)
    public TextView mTv_middle;

    @BindView(R.id.rcv_msg)
    public ListView mListView;
    MessageAdapter mAdatper;
    List mList;

    @Override
    public void initData() {
        initTestData();
    }

    private void initTestData() {
        for (int i = 0; i < 10; i++) {
            MessageEntity msg = new MessageEntity();
            msg.setId(i);
            msg.setContent("消息:" + i);
            msg.setTitle("消息标题:" + i);
            msg.setImgurl("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%B6%88%E6%81%AF%E5%9B%BE%E6%A0%87&step_word=&hs=0&pn=0&spn=0&di=146665571690&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2697120922%2C4103427738&os=3323025906%2C2583689869&simid=3447496627%2C304371536&adpicid=0&ln=1983&fr=&fmq=1477977769868_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.ui.cn%2Fdata%2Ffile%2F6%2F5%2F8%2F1856.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B7t_z%26e3BvgAzdH3Ftg1jx_z%26e3Brir%3Fpw2%3D4wtgAzdH3Fr%3Dd%26r%3D0ln%26vr%3Dc&gsm=0&rpstart=0&rpnum=0");
            mList.add(msg);
        }
        mAdatper.notifyDataSetChanged();
    }

    @Override
    public void initView() {
        mTv_middle.setText("消息中心");
        mList = new ArrayList();
        mAdatper = new MessageAdapter(mList, this, mImageLoader);
        mListView.setAdapter(mAdatper);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_messages;
    }

    @OnClick({R.id.title_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                this.finish();
                break;
        }

    }

    @OnItemClick({R.id.rcv_msg})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        MessageUtils.alertLongMessageCENTER("进入消息详情" + position);
    }
    private void getData() {
        HashMap map = new HashMap();
        new MyJsonRequestListener(this, Request.Method.GET, IpConfig.HTTP, map) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }

            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
            }
        };
    }
}
