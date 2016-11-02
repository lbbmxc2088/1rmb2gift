package com.muan.takeout.zxing;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class ZXingUtils {
	/**生成二维码*/
	public static Bitmap encodeAsBitmap(String contents,int dimension,BarcodeStyle style) throws WriterException {
		String contentsToEncode = contents;
		if (contentsToEncode == null) {
			return null;
		}
		Map<EncodeHintType, Object> hints = null;
		hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "gbk");
		hints.put(EncodeHintType.MARGIN, 1);//外层背景宽度
		
		String encoding = guessAppropriateEncoding(contentsToEncode);
		if (encoding != null) {
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		
		BitMatrix result = null;
		try {
			result = new MultiFormatWriter().encode(contentsToEncode,  BarcodeFormat.QR_CODE,
					dimension, dimension, hints);
		} catch (IllegalArgumentException iae) {
			return null;
		}
		
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			int offset = y * width;
			for (int x = 0; x < width; x++) {
				pixels[offset + x] = result.get(x, y) ? style.lineColr : style.bgColor;
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	private static String guessAppropriateEncoding(CharSequence contents) {
		for (int i = 0; i < contents.length(); i++) {
			if (contents.charAt(i) > 0xFF) {
				return "UTF-8";
			}
		}
		return null;
	}
}
