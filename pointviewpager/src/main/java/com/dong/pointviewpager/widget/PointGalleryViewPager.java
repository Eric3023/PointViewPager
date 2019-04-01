package com.dong.pointviewpager.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.transformer.GalleryTransformer;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointGalleryViewPager extends PointViewPager {

    private int pageWidth = (int) getResources().getDimension(R.dimen.x240);//ViewPager的宽度
    private int pageHeight = LayoutParams.MATCH_PARENT;//ViewPager的高度

    private float pageAlpha;//隐藏页卡的透明度
    private float pageScale; //隐藏页卡的缩放比例
    private float pageDistance;//两侧页卡的缩紧距离
    private float pageRotation;//两侧页卡的倾斜角度

    public PointGalleryViewPager(@NonNull Context context) {
        super(context);
    }

    public PointGalleryViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PointGalleryViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public PointGalleryViewPager setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
        return  this;
    }

    public PointGalleryViewPager setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
        return  this;
    }

    public PointGalleryViewPager setPageAlpha(float pageAlpha) {
        this.pageAlpha = pageAlpha;
        return  this;
    }

    public PointGalleryViewPager setPageScale(float pageScale) {
        this.pageScale = pageScale;
        return  this;
    }

    public PointGalleryViewPager setPageDistance(float pageDistance) {
        this.pageDistance = pageDistance;
        return this;
    }

    public PointGalleryViewPager setPageRotation(float pageRotation) {
        this.pageRotation = pageRotation;
        return this;
    }

    /*
     * 初始化LoopViewPager
     */

    @Override
    protected void initViewPager(final Context context) {

        pageAlpha = (float) 0.5;
        pageScale = (float) 0.8;
        pageWidth = (int) getResources().getDimension(R.dimen.x240);
        pageHeight = LayoutParams.MATCH_PARENT;

        setClipChildren(false);

        loopViewPager =  new LoopViewPager(context){
            @Override
            public void initialise() {
                setOffscreenPageLimit(4);
                setPageTransformer(true, new GalleryTransformer(pageAlpha, pageScale, pageDistance,pageRotation));
                super.initialise();
            }
        };
        loopViewPager.setOnPagerCompleteListener(this);
        RelativeLayout.LayoutParams lp= new RelativeLayout.LayoutParams(pageWidth, (int) (getMeasuredHeight()));
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        loopViewPager.setLayoutParams(lp);
        loopViewPager.setClipChildren(false);
        loopViewPager.initialise();
        addView(loopViewPager);
    }

    public void initialise(){
        if(loopViewPager!=null){
            RelativeLayout.LayoutParams lp = (LayoutParams) loopViewPager.getLayoutParams();
            lp.width = pageWidth;
            lp.height = pageHeight;
            loopViewPager.setLayoutParams(lp);

            Log.i("Dong", "pageCenterScale:" + pageDistance);
            loopViewPager.setPageTransformer(true, new GalleryTransformer(pageAlpha, pageScale, pageDistance, pageRotation));
        }
    }
}
