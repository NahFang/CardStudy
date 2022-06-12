package com.nahfang.studycard.fragment.container;

import static java.lang.Math.abs;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

class ScaleInTransformer implements ViewPager2.PageTransformer {
    static final float DEFAULT_MIN_SCALE = 0.85f;
    static final float DEFAULT_CENTER = 0.5f;

    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setElevation(-abs(position));
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        view.setPivotY((pageHeight / 2));
        view.setPivotX((pageWidth / 2));
        if (position < -1) {
            view.setScaleX(DEFAULT_MIN_SCALE);
            view.setScaleY(DEFAULT_MIN_SCALE);
            view.setPivotX(pageWidth);
        } else if (position <= 1) {
            if (position < 0) {
                float scaleFactor = (1 + position) * (1 - DEFAULT_MIN_SCALE) + DEFAULT_MIN_SCALE;
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setPivotX(pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position));
            } else {
                float scaleFactor = (1 - position) * (1 - DEFAULT_MIN_SCALE) + DEFAULT_MIN_SCALE;
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }
        } else {
            view.setPivotX(0f);
            view.setScaleX(DEFAULT_MIN_SCALE);
            view.setScaleY(DEFAULT_MIN_SCALE);
        }
    }
}
