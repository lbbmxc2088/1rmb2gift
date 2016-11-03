package com.muan.takeout.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.muan.takeout.R;


/**
 * 圆角ImageView
 */

public class CircleImageView extends ImageView {


    private static final Xfermode MASK_DST;

    private static final Xfermode MASK_CIRCLE;

    private Bitmap dstBitmap;

    private Bitmap circleBitmap;

    private Drawable drawable;

    private Paint paintSrc;

    private Paint paintDst;

    private Paint paintCircle;

    /**
     * 边框宽度
     */
    private int width;

    /**
     * 边框颜色
     */
    private int color;
    /**
     * 背景颜色
     */
    private int bgColor;

    /**
     * 是否有边框
     */
    private boolean isFrame;

    /**
     * 图像width
     */
    private int w;

    /**
     * 图像width
     */
    private int h;

    private int flag;

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        PorterDuff.Mode circleMode = PorterDuff.Mode.DST_ATOP;
        MASK_DST = new PorterDuffXfermode(localMode);
        MASK_CIRCLE = new PorterDuffXfermode(circleMode);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        width = (int) array.getDimension(R.styleable.CircleImageView_border_width, 0);
        color = array.getColor(R.styleable.CircleImageView_border_color, Color.WHITE);
        bgColor = array.getColor(R.styleable.CircleImageView_border_bg_color, Color.WHITE);
        isFrame = array.getBoolean(R.styleable.CircleImageView_circle_IsFrame, false);
        array.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        w = getWidth();
        h = getHeight();

        drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (paintSrc == null) {
            paintSrc = new Paint();
            paintSrc.setFilterBitmap(false);
            paintSrc.setColor(color);
            paintSrc.setAntiAlias(true);
            paintSrc.setXfermode(MASK_DST);
        }

        flag = canvas.saveLayer(0.0F, 0.0F, w, h, null, 31);

        drawable.setBounds(width, width, w - width, h - width);
        drawable.draw(canvas);

        if ((this.dstBitmap == null) || (this.dstBitmap.isRecycled())) {
            dstBitmap = getDstBitMap();
        }
        canvas.drawBitmap(dstBitmap, 0, 0, paintSrc);

        if (isFrame) { // 假如有边框

            paintSrc.setXfermode(MASK_CIRCLE);
            if ((this.circleBitmap == null) || (this.circleBitmap.isRecycled())) {
                circleBitmap = getBitMap(w, h);
            }
            canvas.drawBitmap(circleBitmap, 0f, 0f, paintSrc);
        }
        if (dstBitmap != null) {
            //   dstBitmap.recycle();
            dstBitmap = null;
        }
        if (circleBitmap != null) {
            //	circleBitmap.recycle();
            circleBitmap = null;
        }
        if (!isInEditMode()) {
            canvas.restoreToCount(flag);
        }
    }


    /**
     * 获取dst圆圈
     */
    private Bitmap getDstBitMap() {

        int w = getWidth() - width;
        int h = getHeight() - width;
//        try {
        Bitmap.Config DstConfig = Bitmap.Config.ARGB_8888;
        Bitmap dstBitmap = Bitmap.createBitmap(w, h, DstConfig);

        Canvas dstCanvas = new Canvas(dstBitmap);
        Paint paintDst = new Paint(1);
        paintDst.setColor(bgColor);
        RectF dstRectF = new RectF(width, width, w, h);
        dstCanvas.drawOval(dstRectF, paintDst);
//    	} catch (OutOfMemoryError e) {
//
//		}
        return dstBitmap;
    }

    /**
     * 获取外部框
     */
    private Bitmap getBitMap(int w, int h) {
//    	try {
        Bitmap.Config DstConfig = Bitmap.Config.ARGB_8888;
        Bitmap dstBitmap = Bitmap.createBitmap(w, h, DstConfig);
        Canvas dstCanvas = new Canvas(dstBitmap);
        Paint paintDst = new Paint(1);
        if (isFrame) {
            paintDst.setColor(bgColor);
        }
        RectF rectF = new RectF(0, 0, w, h);
        dstCanvas.drawOval(rectF, paintDst);
//		} catch (OutOfMemoryError e) {
//
//		}
        return dstBitmap;
    }


}
