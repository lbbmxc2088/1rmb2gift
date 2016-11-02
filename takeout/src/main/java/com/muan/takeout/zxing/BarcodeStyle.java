package com.muan.takeout.zxing;
/**
 * 二维码style
 * /背景色在前面，线条颜色在后面
 * @author Ran
 *
 * 时间: 2015年8月11日
 */
public enum BarcodeStyle {
	WhiteAndBalck(0xFFFFFFFF,0xFF000000),TransAndBalck(0x00000000,0xFF000000),D9A172And656565(0xFFD9A172,0xFF656565),
	A4AE6AAndTrans(0xFF84AE6A, 0x00000000), WhiteAndcd8f09(0xFFFFFFFF, 0xFFcd8f09), WhiteAndBlue(0xFFFFFFFF, 0xFF3680df);
	public int lineColr;
	public int bgColor;
	private BarcodeStyle(int bgColor, int lineColr) {
		this.bgColor = bgColor;
		this.lineColr = lineColr;
	}
}
