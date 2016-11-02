package com.muan.takeout.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muan.takeout.Model.ZXJXGoodsItemEntity;
import com.muan.takeout.Adapter.ZxjxRecycleAdapter;
import com.muan.takeout.R;
import com.muan.takeout.Utils.FinalTools;
import com.muan.takeout.Widget.xRefresh.RecyclerViewPositionHelper;
import com.muan.takeout.Widget.xRefresh.XRefreshView;
import com.muan.takeout.Widget.xRefresh.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${Muan} on 2016/10/31 17:45
 */
public class NewsFragment extends BaseFragment implements ZxjxRecycleAdapter.TimeEndListener {
    @BindView(R.id.title_middle)
    public TextView mTv_title;
    @BindView(R.id.title_back)
    public TextView mTv_back;
    @BindView(R.id.title_right)
    public TextView mTv_right;
    @BindView(R.id.xrfv_zxjx)
    public XRefreshView mXrefresh;
    @BindView(R.id.recv_zxjx)
    public RecyclerView mRecycleView;
    private List<ZXJXGoodsItemEntity> mList;
    private ZxjxRecycleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_zxjx, container, false);
        ButterKnife.bind(this,mFragmentView);
        initView();
        initData();
        return mFragmentView;
    }

    private void initView() {
        mTv_title.setText("最新揭晓");
        mTv_back.setVisibility(View.INVISIBLE);
        mTv_right.setVisibility(View.INVISIBLE);
        mList = new ArrayList<>();
        mRecycleView.setHasFixedSize(true);
        mXrefresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mXrefresh.stopRefresh();
                    }
                }, FinalTools.REFRESH_DELAYTIME);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        mXrefresh.stopLoadMore();
                    }
                }, FinalTools.REFRESH_DELAYTIME);
            }
        });
        GridLayoutManager mManager = new GridLayoutManager(mActivity, 2);
        mRecycleView.setLayoutManager(mManager);
        final RecyclerViewPositionHelper mPositionHelper = RecyclerViewPositionHelper.createHelper(mRecycleView);
        mAdapter = new ZxjxRecycleAdapter(mList, mActivity, mImageLoader, mPositionHelper);
        mAdapter.setTimeEndListener(this);
        mAdapter.setCustomLoadMoreView(new XRefreshViewFooter(mActivity));
        mRecycleView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            ZXJXGoodsItemEntity entity = new ZXJXGoodsItemEntity();
            entity.title = "待开奖商品" + i;
            entity.issue = i * (i * 3 - 2);
            entity.ghid = i;
            if (i > 10) {
                entity.status = 1;
                entity.opened_at = "2016.10.31 15:15";
                entity.nickname = "中奖用户" + i;
                entity.joinintimes = 100;
                entity.luckyno = i * (i + 2);
            } else {
                entity.status = 2;
                entity.seconds = 1000 * 60 * 15;
            }
            mList.add(entity);
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTimeEnd() {
        //倒计时结束
    }
}
