package com.dong.pointviewpager.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointViewPager extends RelativeLayout {

    private Context context;
    private LoopViewPager loopViewPager;
    private PointView pointView;

    public PointViewPager(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PointViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PointViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        this.context = context;
        initViewPager(context);
        initPointView();
    }

    /*
     * 初始化LoopViewPager
     */
    private void initViewPager(Context context) {
        loopViewPager =  new LoopViewPager(context);
        RelativeLayout.LayoutParams lp= new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        loopViewPager.setLayoutParams(lp);
        addView(loopViewPager);
    }

    /*
     * 初始化小圆点
     */
    private void initPointView() {
        pointView = new PointView(context);
        loopViewPager.setPointView(pointView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pointView.setLayoutParams(layoutParams);
        pointView.setCount(loopViewPager.getCount());
        addView(pointView, layoutParams);
    }

    public LoopViewPager getLoopViewPager() {
        return loopViewPager;
    }

    public PointView getPointView() {
        return pointView;
    }
}
