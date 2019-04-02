package com.dong.pointviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Dong on 2018/3/19.
 */

public class OverCardTransformer implements ViewPager.PageTransformer {


    private float mOffset;

    public OverCardTransformer(float mOffset) {
        this.mOffset = mOffset;
    }

    public void transformPage(View view, float position) {

        if (position <= 0) {
            view.setRotation((45 * position));
            if(view.getWidth() > view.getHeight())
                view.setTranslationX((view.getWidth() / 3 * position));
            else
                view.setTranslationX((view.getHeight() / 3 * position));
        }else {
            view.setRotation((0));
            //横向偏移量
            view.setTranslationX(-view.getWidth() * position);
            //缩放比例
            float scale = (view.getWidth() - mOffset * position) / (float) (view.getWidth());
            view.setScaleX(scale);
            view.setScaleY(scale);
            //纵向偏移量
            view.setTranslationY(mOffset * position);
        }
    }
}

