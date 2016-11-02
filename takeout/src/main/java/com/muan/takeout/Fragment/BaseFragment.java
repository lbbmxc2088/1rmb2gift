package com.muan.takeout.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.RequestQueue;
import com.muan.takeout.TakeoutApplication;
import com.muan.takeout.Utils.RecycleViewItemListener;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by ${Muan} on 2016/10/31 09:22
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected Activity mActivity;

    protected TakeoutApplication mApplication;
    protected Fragment mFragment;
    protected ImageLoader mImageLoader;
    protected RequestQueue mRequestQueue;
    protected ProgressDialog mProgressDialog;
    protected InputMethodManager imm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mFragment = this;
        mActivity = this.getActivity();
        mApplication = (TakeoutApplication) this.getActivity().getApplication();
        mImageLoader = mApplication.getImageLoader();
        mRequestQueue = mApplication.getRequestQueue();
        mProgressDialog = new ProgressDialog(mActivity);
        imm = (InputMethodManager) mActivity.getSystemService(Service.INPUT_METHOD_SERVICE);
    }

}
