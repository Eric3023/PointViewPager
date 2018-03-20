package com.dong.pointviewpager.transformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Dong on 2018/3/19.
 */

public class GalleryTransformer implements ViewPager.PageTransformer  {


    private  float pageAlpha;
    private  float pageScale;

    public GalleryTransformer() {
    }

    public GalleryTransformer(float pageAlpha, float pageScale) {
        this.pageAlpha = pageAlpha;
        this.pageScale = pageScale;
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
        }else{
            float scaleFactor = Math.max(pageScale, 1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            float alpha=1-Math.abs(position);
            view.setAlpha(Math.max(pageAlpha, alpha));
        }
    }
}

