package com.dong.pointviewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Dong on 2018/3/16.
 */

public class PointView extends View {

    private int count=7;//小圆点数量
    private int nfColor;//未被选中时的颜色
    private int fColor;//被选中时的颜色
    private float distance;//圆点之间的间距
    private float rudis;//圆点的半径
    private float disbottom;//距离底部边缘的距离

    private float percent;//选中圆点的位置
    private int scrollType;
    public static final int SMOOTH_SCROLL = 0;
    public static final int INSTANT_SCROLL = 1;

    public PointView(Context context) {
        super(context);
        init();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        nfColor = Color.WHITE;
        fColor = Color.RED;
        distance = 20;
        rudis= 10;
        disbottom=30;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint nfPaint = new Paint();
        nfPaint.setAntiAlias(false);
        nfPaint.setColor(nfColor);
        for (int i = 0; i < count; i++) {
            canvas.drawCircle((distance+rudis*2)*i+distance/2+rudis, distance/2+rudis, rudis, nfPaint);
        }

        Paint fPaint = new Paint();
        fPaint.setAntiAlias(false);
        fPaint.setColor(fColor);
        if(scrollType == PointView.INSTANT_SCROLL)
            percent = Math.round(percent);
        canvas.drawCircle((distance+rudis*2)*percent+distance/2+rudis, distance/2+rudis, rudis, fPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 设置wrap_content的默认宽 / 高值
        int mWidth = (int) ((distance + rudis*2)*count);
        int mHeight = (int) (distance + rudis * 2 + disbottom);

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNfColor(int nfColor) {
        this.nfColor = nfColor;
    }

    public void setfColor(int fColor) {
        this.fColor = fColor;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setRudis(float rudis) {
        this.rudis = rudis;
    }

    public void setDisbottom(float disbottom) {
        this.disbottom = disbottom;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public int getScrollType() {
        return scrollType;
    }

    public void setScrollType(int scrollType) {
        this.scrollType = scrollType;
    }
}
