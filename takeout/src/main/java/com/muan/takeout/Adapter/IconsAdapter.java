package com.muan.takeout.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.muan.takeout.Model.IconEntity;
import com.muan.takeout.R;
import com.muan.takeout.Utils.ImageLoaderTools;
import com.muan.takeout.Utils.RecycleViewItemListener;
import com.muan.takeout.Utils.WindowUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${Muan} on 2016/6/7.
 */
public class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.LQGIconsViewHolder> implements View.OnClickListener {
    private List<IconEntity> mList;
    private ImageLoader mloader;
    private RecycleViewItemListener mListener;

    public void addRecycleViewItemListener(RecycleViewItemListener mListener) {
        this.mListener = mListener;
    }

    public IconsAdapter(List<IconEntity> mList, ImageLoader mloader) {
        this.mList = mList;
        this.mloader = mloader;

    }

    @Override
    public LQGIconsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewRoot = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icons, parent, false);
        viewRoot.setOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = viewRoot.getLayoutParams();
        try {
            layoutParams.width =  WindowUtils.getWIndowWidth(parent.getContext())/mList.size();
            layoutParams.height = WindowUtils.getWIndowWidth(parent.getContext()) / 5;
        } catch (Exception e) {
            layoutParams.width = WindowUtils.getWIndowWidth(parent.getContext()) / 5;
            layoutParams.height = WindowUtils.getWIndowWidth(parent.getContext()) / 5;
        }

        return new LQGIconsViewHolder(viewRoot);
    }

    @Override
    public void onBindViewHolder(IconsAdapter.LQGIconsViewHolder holder, int position) {
        holder.mTvIconName.setText(mList.get(position).getTitle());
        holder.itemView.setTag(position);
        mloader.displayImage(mList.get(position).getImg(), holder.mIvIcon, ImageLoaderTools.getDisplayImageOptions(0, R.mipmap.icon_green_notice));

    }

    class LQGIconsViewHolder extends RecyclerView.ViewHolder {
        @BindView( R.id.tv_icon)
        public TextView mTvIconName;
        @BindView( R.id.iv_icon)
        public ImageView mIvIcon;

        public LQGIconsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 将Icons的点击事件放调用处处理，
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onRecycleItemClickListener(view, (Integer) view.getTag());
        }
    }

}
