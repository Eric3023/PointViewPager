package com.dong.pointviewpager.transformer;

import android.graphics.PixelFormat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Dong on 2018/3/19.
 */

public class GalleryTransformer implements ViewPager.PageTransformer  {


    private float pageAlpha;
    private float pageScale;
    private float pageDistance;
    private float pageRotation;

    public GalleryTransformer() {
    }

    public GalleryTransformer(float pageAlpha, float pageScale, float pageCenterScale, float pageRotation) {
        this.pageAlpha = pageAlpha;
        this.pageScale = pageScale;
        this.pageDistance = pageCenterScale;
        this.pageRotation = pageRotation;
    }

    public float getPageAlpha() {
        return pageAlpha;
    }

    public void setPageAlpha(float pageAlpha) {
        this.pageAlpha = pageAlpha;
    }

    public float getPageScale() {
        return pageScale;
    }

    public void setPageScale(float pageScale) {
        this.pageScale = pageScale;
    }

    public void transformPage(View view, float position) {
        view.setAlpha(pageAlpha);

        if(position > 1 && position <-1){
            view.setScaleX(pageScale);
            view.setScaleY(pageScale);
            view.setAlpha(pageAlpha);
            view.setTranslationX(0);
            view.setRotationY(0);
        }else{
            float scaleFactor = Math.max(pageScale, 1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            float alpha=1-Math.abs(position);
            view.setAlpha(Math.max(pageAlpha, alpha));

            view.setTranslationX(- pageDistance* position);

            view.setRotationY( - pageRotation * position);
        }
    }
}

