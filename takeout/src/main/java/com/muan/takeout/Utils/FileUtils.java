package com.muan.takeout.Utils;

import java.io.File;

/**
 * 
 * @author Muan
 *
 * 时间: 2015年10月31日
 */
public class FileUtils {
	/**判断文件是否存在*/
	public static boolean isExits(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**创建目录*/
	public static boolean createDirectory(String path){
		File file=new File(path);
		try{
			if(!file.exists()){
				file.mkdirs();
			}
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	
	
	public  static void deleteFile(String filePath) {
		File file=new File(filePath);
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.getAbsoluteFile().delete(); 
			}
		}
	}
}
