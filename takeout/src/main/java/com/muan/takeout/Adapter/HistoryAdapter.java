package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muan.takeout.Model.HistoryEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.MessageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${Muan} on 2016/11/3 16:57
 */
public class HistoryAdapter extends BasisAdapter<HistoryEntity> {


    public HistoryAdapter(List mList, Context mContext) {
        super(mList, mContext, R.layout.item_history);
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder viewholder = (ViewHolder) basisViewHolder;
        viewholder.tvHistoryName.setText(mList.get(position).getGoodsName());
        viewholder.tvHistoryTimes.setText("我已参与:" + mList.get(position).getGoodsTimes());
        viewholder.tvHistoryQihao.setText("参与期数:" + mList.get(position).getGoodsQihao());
        viewholder.tvHistoryWinner.setText(mList.get(position).getWinnerName());
        viewholder.tvHistoryWintimes.setText(mList.get(position).getWinTimes());

    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    @OnClick({R.id.tv_history_buyagain, R.id.ll_history_root})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_history_buyagain:
                MessageUtils.alertLongMessageCENTER("购买");
                break;
            case R.id.ll_history_root:
                MessageUtils.alertLongMessageCENTER("去详情");
                break;
        }
    }

    class ViewHolder extends BasisViewHolder {
        @BindView(R.id.iv_history_pic)
        ImageView ivHistoryPic;
        @BindView(R.id.tv_history_name)
        TextView tvHistoryName;
        @BindView(R.id.tv_history_qihao)
        TextView tvHistoryQihao;
        @BindView(R.id.tv_history_times)
        TextView tvHistoryTimes;
        @BindView(R.id.tv_history_winner)
        TextView tvHistoryWinner;
        @BindView(R.id.tv_history_winnername)
        TextView tvHistoryWinnername;
        @BindView(R.id.tv_history_wintimes)
        TextView tvHistoryWintimes;
        @BindView(R.id.tv_history_buyagain)
        TextView tvHistoryBuyagain;
        @BindView(R.id.ll_history_root)
        LinearLayout llHistoryRoot;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
