package com.muan.takeout.Utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 红线移动动画
 */
public class MoveBtnAinmation {

    /**
     * 移动控件水平动画
     *
     * @param lastView
     * @param currView
     * @param move
     */
    public static void cursorMoveBtnItemAnimation(View lastView, View currView, View move) {
        int currViewLeft = currView.getLeft() - move.getLeft();
        int lastViewLeft = lastView.getLeft() - move.getLeft();
        Animation anim = new TranslateAnimation(lastViewLeft, currViewLeft, 0,
                0);
        anim.setFillAfter(true);
        anim.setDuration(300);
        move.startAnimation(anim);
    }
}
