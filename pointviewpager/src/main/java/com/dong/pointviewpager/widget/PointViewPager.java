package com.dong.pointviewpager.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointViewPager extends RelativeLayout implements LoopViewPager.OnPagerCompleteListener {

    protected Context context;
    protected LoopViewPager loopViewPager;
    protected PointView pointView;

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

    protected void init(Context context) {
        this.context = context;
        initViewPager(context);
        initPointView();
    }

    /*
     * 初始化LoopViewPager
     */
    protected void initViewPager(Context context) {
        loopViewPager = new LoopViewPager(context);
        loopViewPager.setOnPagerCompleteListener(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        loopViewPager.setLayoutParams(lp);
        loopViewPager.initialise();
        addView(loopViewPager);
    }

    /*
     * 初始化小圆点
     */
    protected void initPointView() {
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

    @Override
    public void onPagerComplete() {
        if (pointView != null){
            RelativeLayout.LayoutParams layoutParams = (LayoutParams) pointView.getLayoutParams();
            // 获取高-测量规则的模式和大小
            int mWidth = (int) ((pointView.getDistance() + pointView.getRudis() * 2) * pointView.getCount());
            int mHeight = (int) (pointView.getDistance() + pointView.getRudis() * 2 + pointView.getDisbottom());
            layoutParams.width = mWidth;
            layoutParams.height = mHeight;
            pointView.setLayoutParams(layoutParams);
        }
    }
}
