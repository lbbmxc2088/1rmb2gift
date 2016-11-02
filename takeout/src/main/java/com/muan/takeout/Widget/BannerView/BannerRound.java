package com.muan.takeout.Widget.BannerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;


/**banner圆点Veiw*/
public class BannerRound extends View {
	private final String COLOR_ON = "#df3031";
	private final String COLOR_OFF = "#55FFFFFF";
	private final String COLOR_BORDER = "#55bbbbbb";
	private final int CIRCLE_SIZE = 10;
	private int showNum;
	private Circle[] mCircles;
	private Paint mPaintOn;
	private Paint mPaintOff;
	private Paint mPaintBorder;
	private int mWidth;
	private int mHeight;
	private float heightStup;
	private float radius;
	private int circleOn = -1;

	public int getCircleOn() {
		return circleOn;
	}

	public void setCircleOn(int circleOn) throws Exception {
		if (circleOn > showNum) {
			throw new Exception("NullPointerException");
		}
		this.circleOn =showNum- circleOn-1;
		invalidate();
	}

	@SuppressLint("NewApi")
	public BannerRound(Context context, AttributeSet attrs, int defStyleAttr,
					   int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public BannerRound(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public BannerRound(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BannerRound(Context context) {
		super(context);
		init();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mHeight = getHeight();
		radius = heightStup = mHeight / 2f;
		mWidth = getWidth();
		for (int count = 0; count < CIRCLE_SIZE; count++) {
			Circle circle = new Circle();
			circle.radius = radius;
			circle.windowX = mWidth - (count + 1) * radius * 3;
			circle.windowY = heightStup;
			mCircles[count] = circle;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int count = 0; count < showNum; count++) {
			// int index = CIRCLE_SIZE - count - 1;
			int index = count;
			if (count == circleOn) {
				canvas.drawCircle(mCircles[index].windowX,
						mCircles[index].windowY, mCircles[index].radius + 2,
						mPaintBorder);
				canvas.drawCircle(mCircles[index].windowX,
						mCircles[index].windowY, mCircles[index].radius,
						mPaintOn);


			} else {
				canvas.drawCircle(mCircles[index].windowX,
						mCircles[index].windowY, mCircles[index].radius + 2,
						mPaintBorder);
				canvas.drawCircle(mCircles[index].windowX,
						mCircles[index].windowY, mCircles[index].radius,
						mPaintOff);

			}
		}
	}

	public void init() {
		mCircles = new Circle[CIRCLE_SIZE];
		mPaintOn = new Paint();
		mPaintOn.setColor(Color.parseColor(COLOR_ON));
		mPaintOn.setAntiAlias(true);
		mPaintOn.setStyle(Style.FILL);

		mPaintOff = new Paint();
		mPaintOff.setColor(Color.parseColor(COLOR_OFF));
		mPaintOff.setAntiAlias(true);
		mPaintOff.setStyle(Style.FILL);

		mPaintBorder = new Paint();
		mPaintBorder.setColor(Color.parseColor(COLOR_BORDER));
		mPaintOff.setAntiAlias(true);
		mPaintOff.setStyle(Style.STROKE);
	}

	/**动画小圆点基本属性*/
	class Circle {
		private float windowX;
		private float windowY;
		private float radius;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) throws Exception {
		if (showNum > CIRCLE_SIZE) {
			throw new Exception("ShowNum is too large");
		}
		this.showNum = showNum;
		invalidate();
	}

}
