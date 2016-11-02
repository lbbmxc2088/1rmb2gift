package com.muan.takeout.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

import com.muan.takeout.Utils.NetWorkUtils;
import com.muan.takeout.Utils.PreferenceUtils;


/**
 * 类描述：网络环境改版监听
 * 修改备注：
 */
public final class ConnectivityStatusReceiver extends BroadcastReceiver {


    private final String TAG = "ConnectivityStatusReceiver";
    private final String PING_ADDRESS="www.baidu.com";
    private final String WORK_STATUS = "colorful_connectivitywork_status";
    private  String mPingCommand="ping -c 1 "+PING_ADDRESS;// -c 次数 -i间隔时间 -w等待时间
    private ConnectivityChangeListener mListener;

    private boolean mIsWork;

    private boolean mNetCanuse = false;


    @Override
    public void onReceive(Context context, Intent intent) {
        //获取当前是否有在工作的网络监测活动
        mIsWork = PreferenceUtils.getInstance(context).getBoolean(WORK_STATUS, false);
        if (mIsWork) {
            return;
        } else {
            mIsWork = true;
            PreferenceUtils.getInstance(context).saveBoolean(WORK_STATUS, mIsWork);
            NetworkInfo networkInfo = NetWorkUtils.getActiveNetwork(context);//获取 状态,暂不做扩展处理
            if (networkInfo == null) {//没有连接
                mNetCanuse = false;
            } else {//有连接
               Runtime runtime=Runtime.getRuntime();
                Process proc = null;
                try {
                    //运行 ping 命令
                    proc = runtime.exec(mPingCommand);
                    int result = proc.waitFor();
                    if(result==0){
                        mNetCanuse=true;
                    }else{
                        mNetCanuse=false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mNetCanuse=false;
                }
            }

         if (mListener != null) {
                mListener.internetCanUse(mNetCanuse,networkInfo);
            }
            mIsWork=false;
            PreferenceUtils.getInstance(context).saveBoolean(WORK_STATUS, mIsWork);
        }
    }

    public void setConnectivityListener(ConnectivityChangeListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 网络改变回掉
     */
    public interface ConnectivityChangeListener {
        public void internetCanUse(boolean val, NetworkInfo networkInfo);
    }
}
