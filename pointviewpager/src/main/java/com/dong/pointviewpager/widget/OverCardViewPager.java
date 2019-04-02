package com.dong.pointviewpager.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.adapter.LoopPagerAdapter;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.bean.ScrollBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.model.ResourceConfige;
import com.dong.pointviewpager.transformer.OverCardTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dong on 2018/3/15.
 */

public class OverCardViewPager extends ViewPager implements LoopPagerAdapter.onDataChangedListener {

    private Context context;
    private boolean isLoop;//是否可以循环滑动
    private int scroll_state;//滑动状态
    private LoopPagerAdapter loopPagerAdapter;//ViewPager适配器
    private ImageView.ScaleType imageScale = ImageView.ScaleType.FIT_XY;//默认图片的伸缩模式

    private OnPagerCompleteListener onPagerCompleteListener;

    private int defaultCount = 5;//默认显示的数量
    private int[] defaultResouces = {ResourceConfige.resourceID, ResourceConfige.resourceID, ResourceConfige.resourceID};//默认显示占位图片

    private List<LoopViewPagerBean> beans = new ArrayList<LoopViewPagerBean>();
    private List<ImageView> imageViews = new ArrayList<ImageView>();

    private boolean isCard;
    private float radius;
    private float elevation;
    private int padding;
    private float mOffset;

    private OnLoopPageChangeListener onLoopPageChangeListener = new OnLoopPageChangeListener() {
        @Override
        protected void onViewPageSelected(int position) {

        }

        @Override
        protected void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        protected void onViewPageScrollStateChanged(int state) {

        }
    };

    private OnLoopPagerClickListener onLoopPagerClickListener;

    private PointView pointView;

    private int position;

    private Observer selectObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object value) {
            ScrollBean bean = (ScrollBean) value;
            if (bean.getScroll_state() != -1) {
                scroll_state = bean.getScroll_state();
            }

            if (bean.getScroll_positon() != -1 && bean.getScroll_percent() != -1) {
                if (pointView != null && getCount() != 0) {
                    int position = bean.getScroll_positon() % getCount();
                    float positionOffset = bean.getScroll_percent();
                    if (position == getCount() - 1) {
                        if (positionOffset > 0.5)
                            position = 0;
                        positionOffset = 0;
                    }
                    pointView.setPercent(position + positionOffset);
                    pointView.invalidate();

                }
            }

            if (bean.getSelect_position() != -1) {
                if(getCount()!=0){
                    position = bean.getSelect_position()%getCount();
                    if(onLoopPageChangeListener!=null)
                        onLoopPageChangeListener.setPosition(position);
                }
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    private Observer clickObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object value) {
            if (onLoopPagerClickListener != null) {
                onLoopPagerClickListener.setPosition(position);
                if (beans != null && position < beans.size())
                    onLoopPagerClickListener.setBean(beans.get(position));
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    public void setOnPagerCompleteListener(OnPagerCompleteListener onPagerCompleteListener) {
        this.onPagerCompleteListener = onPagerCompleteListener;
    }

    public OverCardViewPager(Context context) {
        super(context);
        init(context);
    }

    public OverCardViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public float getmOffset() {
        return mOffset;
    }

    public OverCardViewPager setmOffset(float mOffset) {
        this.mOffset = mOffset;
        setPadding(0, 0 ,0, (int) mOffset);
        return this;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public OverCardViewPager setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public int getCount() {
        if (beans != null)
            return beans.size();
        return 0;
    }

    public OverCardViewPager setCard(boolean card) {
        isCard = card;
        return this;
    }

    public OverCardViewPager setCardRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public OverCardViewPager setCardElevation(float elevation) {
        this.elevation = elevation;
        return this;
    }

    public OverCardViewPager setCardPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageView.ScaleType getImageScale() {
        return imageScale;
    }

    public OverCardViewPager setImageScale(ImageView.ScaleType imageScale) {
        this.imageScale = imageScale;
        return this;
    }

    public List<LoopViewPagerBean> getBeans() {
        return beans;
    }

    public OverCardViewPager setBeans(List<LoopViewPagerBean> beans) {
        if(beans!=null){
            this.beans = beans;
        }else{
            this.beans.clear();
            this.beans.add(new LoopViewPagerBean(defaultResouces[0], null));
        }
        return this;
    }

    public OverCardViewPager setDefaultResouces(int[] defaultResouces) {
        if (defaultResouces != null && defaultResouces.length != 0) {
            this.defaultResouces = defaultResouces;
        }
        return this;
    }

    public OnLoopPageChangeListener getPageChangeListener() {
        return onLoopPageChangeListener;
    }

    public LoopPagerAdapter getLoopPagerAdapter() {
        return loopPagerAdapter;
    }

    public OverCardViewPager setOnLoopPageChangeListener(OnLoopPageChangeListener onLoopPageChangeListener) {
        this.onLoopPageChangeListener = onLoopPageChangeListener;
        onLoopPageChangeListener.setObserver(selectObserver);
        return this;
    }

    public OverCardViewPager setOnLoopPagerClickListener(OnLoopPagerClickListener onLoopPagerClickListener) {
        this.onLoopPagerClickListener = onLoopPagerClickListener;
        this.onLoopPagerClickListener.setObserver(clickObserver);
        return this;
    }

    public PointView getPointView() {
        return pointView;
    }

    public void setPointView(PointView pointView) {
        this.pointView = pointView;
    }

    private ArrayList<Integer> childCenterXAbs = new ArrayList<>();
    private SparseArray<Integer> childIndex = new SparseArray<>();

    /*
     * 初始化
     */
    private void init(Context context) {
        this.context = context;

        this.radius = context.getResources().getDimension(R.dimen.x5);
        this.elevation = context.getResources().getDimension(R.dimen.x10);
        this.mOffset = context.getResources().getDimension(R.dimen.y5);

        setPadding(0, 0 ,0, (int) mOffset);
        setClipToPadding(false);

        //设置占位图片
        for (int i = 0; i < defaultCount; i++) {
            LoopViewPagerBean bean = new LoopViewPagerBean();
            bean.setResourceID(defaultResouces[i % defaultResouces.length]);
            beans.add(bean);
        }

        initialise();//显示默认效果
    }

    public void initialise() {

        setPageTransformer(true, new OverCardTransformer(mOffset));

        setOffscreenPageLimit(4);

        imageViews.clear();

        loopPagerAdapter = new LoopPagerAdapter(context, beans, imageScale, defaultResouces[0], onLoopPagerClickListener,
                isLoop, false, 0, isCard, radius, elevation, padding);
        loopPagerAdapter.setOnDataChangedListener(this);
        setAdapter(loopPagerAdapter);
        loopCheck();

        if (pointView != null)
            pointView.setCount(getCount());

        //设置监听
        onLoopPageChangeListener.setObserver(selectObserver);
        addOnPageChangeListener(onLoopPageChangeListener);

        if(onPagerCompleteListener != null)
            onPagerCompleteListener.onPagerComplete();
    }

    public void loopCheck() {
        if (isLoop) {
            setCurrentItem(getCount()*5, false);
        } else {
            setCurrentItem(0, false);
        }
    }

    @Override
    public void onDataChanged() {
        if(pointView!=null){
            pointView.setCount(getCount());
        }
        if(onPagerCompleteListener!=null){
            onPagerCompleteListener.onPagerComplete();
        }
    }

    public interface OnPagerCompleteListener{
        void onPagerComplete();
    }

}
