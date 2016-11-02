package com.muan.takeout.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.muan.takeout.Model.MessageEntity;
import com.muan.takeout.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ${Muan} on 2016/11/1 13:13
 */
public class MessageAdapter extends BasisAdapter<MessageEntity> {
    private ImageLoader mLoader;

    public MessageAdapter(List mList, Context mContext, ImageLoader mLoader) {
        super(mList, mContext, R.layout.item_messages);
        this.mLoader = mLoader;
    }

    @Override
    public void bingHolder(BasisViewHolder basisViewHolder, int position) {
        ViewHolder holder = (ViewHolder) basisViewHolder;
        holder.mIv_pic.setImageResource(R.mipmap.msg);
        //加载网络图片
        //mLoader.displayImage(mList.get(position).getImgurl(),holder.mIv_pic);
        holder.mTv_title.setText(mList.get(position).getTitle());
        holder.mTv_content.setText(mList.get(position).getContent());

    }

    @Override
    public BasisViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BasisViewHolder {
        @BindView( R.id.tv_msgtitle)
        public TextView mTv_title;
        @BindView(R.id.tv_msgcontent)
        public TextView mTv_content;
        @BindView( R.id.iv_msgpic)
        public ImageView mIv_pic;

        public ViewHolder(View view) {
            super(view);

        }
    }
}
