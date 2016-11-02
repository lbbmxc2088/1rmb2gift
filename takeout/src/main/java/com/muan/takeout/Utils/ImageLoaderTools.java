package com.muan.takeout.Utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by ${Muan} on 2016/10/31.
 * ImageLoader的自带选项的封装，画圆。 可自再定义其它
 */
public class ImageLoaderTools {
    public static DisplayImageOptions getDisplayImageOptions(int radius,int loadImage){
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(loadImage)
                .showImageForEmptyUri(loadImage)
                .showImageOnFail(loadImage)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(radius))
                .build();
    }
}
