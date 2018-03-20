package com.dong.pointviewpager.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.transformer.GalleryTransformer;

/**
 * Created by Dong on 2018/3/19.
 */

public class GalleryViewPager extends RelativeLayout {

    private Context context;
    private LoopViewPager loopViewPager;

    private int pageWidth = (int) getResources().getDimension(R.dimen.x240);//ViewPager的宽度
    private int pageHeight = LayoutParams.MATCH_PARENT;//ViewPager的高度

    private float pageAlpha = (float) 0.5;//隐藏页卡的透明度
    private float pageScale = (float) 0.8; //隐藏页卡的缩放比例

    public GalleryViewPager(@NonNull Context context) {
        super(context);
        init(context);
    }

    public GalleryViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GalleryViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GalleryViewPager setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
        return this;
    }

    public GalleryViewPager setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
        return this;
    }

    public GalleryViewPager setPageAlpha(float pageAlpha) {
        this.pageAlpha = pageAlpha;
        return this;
    }

    public GalleryViewPager setPageScale(float pageScale) {
        this.pageScale = pageScale;
        return this;
    }

    public LoopViewPager getLoopViewPager() {
        return loopViewPager;
    }

    private void init(Context context) {
        this.context = context;
        setClipChildren(false);
        initLoopViewPager();
    }

    private void initLoopViewPager() {
        loopViewPager =  new LoopViewPager(context){
            @Override
            public void initialise() {
                setOffscreenPageLimit(4);
                setPageTransformer(true, new GalleryTransformer(pageAlpha, pageScale));
                super.initialise();
            }
        };
        Log.i("Dong", "width:"+pageWidth+"----height:"+pageHeight);
        RelativeLayout.LayoutParams lp= new RelativeLayout.LayoutParams(pageWidth, pageHeight);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        loopViewPager.setLayoutParams(lp);
        loopViewPager.setClipChildren(false);
        loopViewPager.initialise();//显示默认效果
        addView(loopViewPager);
    }


    public void initialise(){
        if(loopViewPager!=null){
            RelativeLayout.LayoutParams lp = (LayoutParams) loopViewPager.getLayoutParams();
            lp.width = pageWidth;
            lp.height = pageHeight;
            loopViewPager.setLayoutParams(lp);

            loopViewPager.setPageTransformer(true, new GalleryTransformer(pageAlpha, pageScale));
        }
    }
}
