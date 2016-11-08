package com.muan.takeout.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.muan.takeout.Activity.MessageActivity;
import com.muan.takeout.Activity.SearchActivity;
import com.muan.takeout.Adapter.GoodsAdapter;
import com.muan.takeout.Adapter.IconsAdapter;
import com.muan.takeout.Model.BannerEntity;
import com.muan.takeout.Model.GoodsEntity;
import com.muan.takeout.Model.IconEntity;
import com.muan.takeout.Model.MarqueeEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Utils.MessageUtils;
import com.muan.takeout.Utils.RecycleViewItemListener;
import com.muan.takeout.Utils.WindowUtils;
import com.muan.takeout.Widget.BannerView.BannerManager;
import com.muan.takeout.Widget.HeaderGridView;
import com.muan.takeout.Widget.HeaderView;
import com.muan.takeout.Widget.xRefresh.XRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by ${Muan} on 2016/10/31 17:41
 */
public class MainFragment extends BaseFragment implements RecycleViewItemListener, BannerManager.BannerClickLintener, HeaderView.HeadViewClickListener, View.OnClickListener, GridView.OnItemClickListener {
    @BindView(R.id.iv_titlesearch)
    public ImageView mIv_back;
    @BindView(R.id.tv_titlemiddle)
    public TextView mTv_middle;
    @BindView(R.id.tv_titlemsg)
    public TextView mTv_right;

    @BindView(R.id.xrfv_main)
    public XRefreshView mXrefreshview;
    @BindView(R.id.hgv_main)
    public HeaderGridView mGridview;

    HeaderView mTopView;
    private List<GoodsEntity> mGoodsList = new ArrayList<>();
    private GoodsAdapter mGoodsAdapter;
    private List<BannerEntity> mBannerList = new ArrayList<>();
    private BannerManager mBannerManager;
    private List<String> mBannerImageUrls;
    private List<IconEntity> mIconList = new ArrayList<>();
    private IconsAdapter mIconAdapter;
    private List<MarqueeEntity> mMarquesList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mFragmentView);


        initView();
        initTestData();//加载测试数据,请求服务器数据请在initData()方法里处理
        //initData();
        return mFragmentView;
    }

    private void initView() {

        mTopView = new HeaderView(mActivity);
        mTopView.setHeadViewListener(this);
        mGridview.addHeaderView(mTopView);
        //设置商品适配器
        mGoodsAdapter = new GoodsAdapter(mGoodsList, mActivity, mImageLoader);
        mGridview.setAdapter(mGoodsAdapter);
        mXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXrefreshview.stopRefresh();
                    }
                }, FinalTools.REFRESH_DELAYTIME);

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXrefreshview.stopLoadMore();
                    }
                }, FinalTools.REFRESH_DELAYTIME);
            }
        });
        //设置Banner适配
        mBannerImageUrls = new ArrayList<>();
        mBannerManager = new BannerManager(mTopView.mBannerLayout, mBannerImageUrls, mImageLoader);
        mBannerManager.setRefreshLayout(mXrefreshview);
        mBannerManager.setBannerClickLintener(this);
        mBannerManager.setLayoutParams(-1, WindowUtils.getWIndowWidth(mActivity) / 3);
        //设置导航适配
        mIconAdapter = new IconsAdapter(mIconList, mImageLoader);
        mIconAdapter.addRecycleViewItemListener(this);
        mTopView.mRecycleview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTopView.mRecycleview.setLayoutManager(linearLayoutManager);
        mTopView.mRecycleview.setItemAnimator(new DefaultItemAnimator());
        mTopView.mRecycleview.setAdapter(mIconAdapter);


    }

    private void initTestData() {
        //模拟banner数据
        mBannerImageUrls.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=banner%E5%9B%BE&step_word=&hs=0&pn=13&spn=0&di=134462334270&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=888918404%2C3272705416&os=842630369%2C2890051930&simid=4020045712%2C594001995&adpicid=0&ln=1936&fr=&fmq=1477967758286_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01774856486feb32f87512f66f8898.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bzv55s_z%26e3Bv54_z%26e3BvgAzdH3Fo56hAzdH3FZMTQoMzYxOTY%3D_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
        mBannerImageUrls.add("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=banner%E5%9B%BE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3462653056,3740313519&os=4026506294,3823843881&simid=0,0&pn=4&rn=1&di=82011613640&ln=1936&fr=&fmq=1477967758286_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&adpicid=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01156e56d56e2b32f875520fc87c00.jpg&rpstart=0&rpnum=0&adpicid=0&ctd=1477968125440^3_1920X944%1");
        mBannerManager.update();
        //跑马灯数据
        for (int i = 0; i < 10; i++) {
            TextView tv = new TextView(mActivity);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setSingleLine();
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#AA000000"));
            tv.setText("中奖消息：" + i);
            mTopView.mMessageLayout.addView(tv);
        }
        mTopView.mMessageLayout.startFlipping();
        //商品数据
        for (int i = 0; i < 20; i++) {
            GoodsEntity good = new GoodsEntity();
            good.setGoods_thumb("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=ipone7%E5%9B%BE%E7%89%87&step_word=&hs=0&pn=0&spn=0&di=35616351210&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3256437698%2C2740429284&os=2904359539%2C2437687918&simid=3449594756%2C359644484&adpicid=0&ln=1850&fr=&fmq=1477968599095_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fi2.qihuiwang.com%2Fweb%2F2015-02-28%2F0149075f13ca32b6b4.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fgjof_z%26e3Bqti7towg2_z%26e3Bv54AzdH3FEq7tr4jgpAzdH3Fda8caddblmccl_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
            good.setCopies(7000 + "");
            good.setSell_copies(i * 30 + "");
            good.setGoods_name("iphone7测试" + i);
            mGoodsList.add(good);
        }
        mGoodsAdapter.notifyDataSetChanged();
        //4个分类相关图标 可写死 现在为可变
        for (int i = 0; i < 4; i++) {
            IconEntity icon = new IconEntity();
            icon.setTitle("分类" + i);
            icon.setImg("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%88%86%E7%B1%BB%E5%9B%BE%E6%A0%87%E5%8D%95%E4%B8%AA&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3676370926,1604803210&os=4188891535,88005062&simid=0,0&pn=0&rn=1&di=166197405550&ln=1984&fr=&fmq=1477969325868_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&adpicid=0&pi=0&gsm=0&objurl=http%3A%2F%2Fww2.sinaimg.cn%2Fbmiddle%2Fa31f5addjw1en5t8dhkxdj20a00a0q35.jpg&rpstart=0&rpnum=0&adpicid=0&ctd=1477969521014^3_1920X944%1");
            mIconList.add(icon);
        }
        mIconAdapter.notifyDataSetChanged();


    }


    @OnClick({R.id.iv_titlesearch, R.id.tv_titlemsg})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_titlesearch:
                //去搜索页面
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.tv_titlemsg:
                //去消息页面
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
        }
    }


    @OnItemClick({R.id.hgv_main})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void bannerClick(int position) {
        MessageUtils.alertLongMessageCENTER("跳转到web还是商品详情 自定义");
    }

    @Override
    public void onRecycleItemClickListener(View view, int position) {
        switch (position) {
            case 0:
                MessageUtils.alertLongMessageCENTER("分类");
                break;
            case 1:
                MessageUtils.alertLongMessageCENTER("优选商品");
                break;
            case 2:
                MessageUtils.alertLongMessageCENTER("晒单");
                break;
            case 3:
                MessageUtils.alertLongMessageCENTER("常见问题");
                break;
        }
    }

    @Override
    public void headViewClickListener(View v) {

    }
}
