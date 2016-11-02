package com.muan.takeout.Utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * 
 * 提供bitmap异步操作
 * 
 * @author Muan
 *
 * 时间: 2015年10月31日
 */
public class BitmapOperationUtil {



	public interface OnFinishCompressListener{
		public void finishCompress(String imagepath);
	}

	OnFinishCompressListener compressListener;

	public String imageDirector;
	public BitmapOperationUtil(OnFinishCompressListener compressListener, String imagePath) {
		super();
		this.compressListener = compressListener;
		this.imageDirector=imagePath;
	}

	public void saveBitMap(String path){
		new BitmapAsyncTask().execute(path);
	}
	
	class BitmapAsyncTask extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String imagePath;
			if(params!=null&&params.length>0){
				imagePath=params[0];
				if(imagePath.startsWith("file")){
					imagePath = imagePath.toString().substring(7,imagePath.toString().length());
				}
				//确保文件夹存在
				FileUtils.createDirectory(imageDirector);
				String imageNowPath=imageDirector+imagePath.hashCode()+".jpg";
				//假如已经存在改文件，删除之前的文件
				FileUtils.deleteFile(imageNowPath);

				Bitmap bitmap= BitmapUtils.getimage(imagePath);
				BitmapUtils.saveBitmap(bitmap, imageNowPath, true);
				return imageNowPath;
			}else{
				return "";
			}
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(compressListener!=null){
				compressListener.finishCompress(result);
			}
		}
	}
	
	
}
