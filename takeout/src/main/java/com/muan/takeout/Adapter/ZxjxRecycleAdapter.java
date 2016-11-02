package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muan.takeout.Model.ZXJXGoodsItemEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.TimeTools;
import com.muan.takeout.Widget.MyCountDownTimer;
import com.muan.takeout.Widget.xRefresh.BaseRecyclerAdapter;
import com.muan.takeout.Widget.xRefresh.RecyclerViewPositionHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/1 15:30
 */
public class ZxjxRecycleAdapter extends BaseRecyclerAdapter<ZXJXGoodsItemEntity, ZxjxRecycleAdapter.ZxjxViewHolder> {
    private ImageLoader mImageLoader;
    private long mSecond = -1;
    private String mTime;
    private TimeEndListener mListener;
    private RecyclerViewPositionHelper mPositionHelper;
    private Map<Integer, MyTimer> timerList;
    private List<ZXJXGoodsItemEntity> mList;
    private Context mContext;

    public ZxjxRecycleAdapter(List<ZXJXGoodsItemEntity> list, Context context, ImageLoader loader, RecyclerViewPositionHelper helper) {
        super(list);
        this.mList = list;
        this.mContext = context;
        this.mImageLoader = loader;
        this.mPositionHelper = helper;
        this.timerList = new HashMap<>();
    }

    @Override
    public ZxjxViewHolder getViewHolder(View view) {
        return new ZxjxViewHolder(view, false);
    }

    @Override
    public ZxjxViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_zxjx, null, false);
        return new ZxjxViewHolder(v, true);
    }

    @Override
    public void onBindViewHolder(ZxjxViewHolder viewHolder, final int position, boolean isItem) {
        viewHolder.mLayoutFinish.setVisibility(View.GONE);
        viewHolder.mLayoutIng.setVisibility(View.GONE);
        viewHolder.mLayout_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ShopDetailActivity.class);
//                intent.putExtra("id", mList.get(position).ghid);
//                mContext.startActivity(intent);
            }
        });

        viewHolder.mTvShopName.setText(mList.get(position).title);
        viewHolder.mIvShop.setImageResource(R.mipmap.icon_app);
        // mImageLoader.displayImage(mList.get(position).img, viewHolder.mIvShop, ImageLoaderTools.getDisplayImageOptions(0, R.mipmap.ic_launcher));
        if (mList.get(position).status == 1) {//已经开奖
            viewHolder.mTv_qihao.setText("期        号：" + mList.get(position).issue);
            viewHolder.mLayoutFinish.setVisibility(View.VISIBLE);
            viewHolder.mTvUser.setText(mList.get(position).nickname);
            viewHolder.mTvJoinNum.setText("参与人次：" + mList.get(position).joinintimes);
            viewHolder.mTvLuckNum.setText(mList.get(position).luckyno + "");
            viewHolder.mTvOpenTime.setText("揭晓时间：" + mList.get(position).opened_at);
        } else if (mList.get(position).status == 2) {//开奖中或者时时彩通讯错误
            viewHolder.mTv_qihao.setText("期号：" + mList.get(position).issue);
            if (mList.get(position).seconds < 0) {//时时彩通讯错误
            } else {//倒计时中
                viewHolder.mLayoutIng.setVisibility(View.VISIBLE);
                //有待优化
                if (mSecond != mList.get(position).seconds) {
                    mSecond = mList.get(position).seconds;
                    mTime = TimeTools.longSecondToTime(mSecond);
                }
                viewHolder.mTving.setText(mTime);

                MyTimer timer = timerList.get((int) mList.get(position).ghid);
                if (timer == null) {
                    timer = new MyTimer(mSecond, 1, position, viewHolder.mTving);
                    timerList.put((int) mList.get(position).ghid, timer);
                } else {
                    timer.pos = position;
                    timer.setmMillisInFuture(mSecond * 1000);
                    timer.setmCountdownInterval(1);
                    timer.textView = viewHolder.mTving;
                }
                timer.start();
            }
        }

        viewHolder.mTvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, OthersInfoActivity.class);
//                intent.putExtra("uid", mList.get(position).uid);
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return mList.size();
    }

    public void setTimeEndListener(TimeEndListener listener) {
        this.mListener = listener;
    }

    public void cancelAllTimer() {
        Iterator it = timerList.keySet().iterator();
        while (it.hasNext()) {
            int key = (int) it.next();
            timerList.get(key).cancel();
        }
        timerList.clear();
    }

    @Override
    public void onClick(View view) {

    }


    class ZxjxViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.ll_zxjx_root)
        public LinearLayout mLayout_root;
        @BindView(R.id.iv_shop)
        public ImageView mIvShop;

        @BindView(R.id.tv_name)
        public TextView mTvShopName;

        //正在揭晓
        @BindView(R.id.layout_zxjx_ing)
        public LinearLayout mLayoutIng;

        @BindView(R.id.tv_timing)
        public TextView mTving;

        //已经揭晓
        @BindView(R.id.layout_zxjx_finish)
        public LinearLayout mLayoutFinish;

        @BindView(R.id.tv_user)
        public TextView mTvUser;

        @BindView(R.id.tv_join_num)
        public TextView mTvJoinNum;

        @BindView(R.id.tv_lucky_num)
        public TextView mTvLuckNum;

        @BindView(R.id.tv_time)
        public TextView mTvOpenTime;


        @BindView(R.id.tv_qihao)
        public TextView mTv_qihao;

        public ZxjxViewHolder(View itemView, boolean isItem) {
            super(itemView, isItem);
        }
    }

    public interface TimeEndListener {
        void onTimeEnd();
    }

    class MyTimer extends MyCountDownTimer {
        int pos;
        TextView textView;

        public MyTimer(long millisInFuture, long countDownInterval, int position, TextView textView) {
            super(millisInFuture, countDownInterval);
            this.textView = textView;
            this.pos = position;
        }

        @Override
        public void onFinish() {

            int firstVisiblePosition = mPositionHelper.findFirstVisibleItemPosition();
            int i = pos - firstVisiblePosition;
            if (i >= 0) {
                mListener.onTimeEnd();
            }
            mList.set(pos, mList.get(pos));
            this.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (pos >= mList.size()) {
                this.cancel();
                return;
            }
            int firstVisiblePosition = mPositionHelper.findFirstVisibleItemPosition();
            int i = pos - firstVisiblePosition;
            if (i >= 0) {
                textView.setText(TimeTools.longSecondToTime(millisUntilFinished) + "");
            }
            ZXJXGoodsItemEntity entity = mList.get(pos);
            entity.seconds = (int) (millisUntilFinished / 1000);
            mList.set(pos, entity);
        }
    }
}
