package com.dong.pointviewpager.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.adapter.LoopPagerAdapter;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.bean.ScrollBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by Dong on 2018/3/15.
 */

public class LoopViewPager extends ViewPager {

    private Context context;
    private boolean isLoop;//是否可以循环滑动
    private boolean isAuto;//是否可以自动滑动
    private int scroll_state;//滑动状态
    private int autoTime = 3;//自动播放间隔，单位为秒
    private Disposable disposable;
    private LoopPagerAdapter loopPagerAdapter;//ViewPager适配器

    public static final int FIT_XY = 0;
    public static final int CENTER_INSIDE = 1;
    public static final int CENTER_CROP = 2;
    private int imageScale = FIT_XY;//默认图片的伸缩模式

    private int defaultCount = 5;//默认显示的数量
    private int[] defaultResouces = {R.drawable.bigimg1, R.drawable.bigimg2, R.drawable.bigimg3};//默认显示占位图片

    private List<LoopViewPagerBean> beans = new ArrayList<LoopViewPagerBean>();
    private List<ImageView> imageViews = new ArrayList<ImageView>();

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

    public LoopViewPager(Context context) {
        super(context);
        init(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public boolean isLoop() {
        return isLoop;
    }

    public LoopViewPager setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public LoopViewPager setAuto(boolean auto) {
        isAuto = auto;
        return this;
    }

    public int getAutoTime() {
        return autoTime;
    }

    public LoopViewPager setAutoTime(int autoTime) {
        this.autoTime = autoTime;
        return this;
    }

    public int getCount() {
        if (imageViews != null)
            return imageViews.size();
        return 0;
    }

    public int getImageScale() {
        return imageScale;
    }

    public LoopViewPager setImageScale(int imageScale) {
        this.imageScale = imageScale;
        return this;
    }

    public List<LoopViewPagerBean> getBeans() {
        return beans;
    }

    public LoopViewPager setBeans(List<LoopViewPagerBean> beans) {
        this.beans = beans;
        return this;
    }

    public LoopViewPager setDefaultResouces(int[] defaultResouces) {
        if (defaultResouces != null && defaultResouces.length != 0) {
            this.defaultResouces = defaultResouces;
        }
        return this;
    }

    public OnLoopPageChangeListener getPageChangeListener() {
        return onLoopPageChangeListener;
    }

    public LoopViewPager setOnLoopPageChangeListener(OnLoopPageChangeListener onLoopPageChangeListener) {
        this.onLoopPageChangeListener = onLoopPageChangeListener;
        onLoopPageChangeListener.setObserver(selectObserver);
        addOnPageChangeListener(this.onLoopPageChangeListener);
        return this;
    }

    public LoopViewPager setOnLoopPagerClickListener(OnLoopPagerClickListener onLoopPagerClickListener) {
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

    /*
     * 初始化
     */
    private void init(Context context) {
        this.context = context;

        //设置占位图片
        for (int i = 0; i < defaultCount; i++) {
            LoopViewPagerBean bean = new LoopViewPagerBean();
            bean.setResourceID(defaultResouces[i % defaultResouces.length]);
            beans.add(bean);
        }
    }

    public void initialise() {

        imageViews.clear();

        if (beans != null && beans.size() != 0) {
            for (int i = 0; i < beans.size(); i++) {
                ImageView imageView = new ImageView(context);
                loadImage(beans.get(i), imageView);
                if(onLoopPagerClickListener!=null)
                    imageView.setOnClickListener(onLoopPagerClickListener);
                imageViews.add(imageView);
            }
        } else {
            ImageView imageView = new ImageView(context);
            LoopViewPagerBean bean = new LoopViewPagerBean();
            bean.setResourceID(defaultResouces[0]);
            loadImage(bean, imageView);
            imageViews.add(imageView);
        }

        loopPagerAdapter = new LoopPagerAdapter(imageViews, isLoop, isAuto, autoTime);
        setAdapter(loopPagerAdapter);
        loopCheck();
        autoPlay();

        if (pointView != null)
            pointView.setCount(getCount());

        //设置监听
        setOnLoopPageChangeListener(onLoopPageChangeListener);

    }

    public void loopCheck() {
        if (isLoop && loopPagerAdapter.getCount() > 3) {
            setCurrentItem(loopPagerAdapter.getCount() / 2, false);
        } else {
            setCurrentItem(0, false);
        }
    }

    public void autoPlay() {

        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();

        if (isAuto) {

            Observable.interval(autoTime, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onNext(Long value) {
                            int current = getCurrentItem();
                            if (current + 1 < loopPagerAdapter.getCount() && scroll_state == SCROLL_STATE_IDLE) {
                                setCurrentItem(current + 1, true);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void loadImage(LoopViewPagerBean bean, ImageView imageView) {
        if (bean == null || imageView == null)
            return;
        String url = bean.getUrl();
        int resourceID = bean.getResourceID();
        if (!TextUtils.isEmpty(url)) {
            switch (imageScale) {
                case FIT_XY:
                    Picasso.get().load(url).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                case CENTER_INSIDE:
                    Picasso.get().load(url).centerInside().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                case CENTER_CROP:
                    Picasso.get().load(url).centerCrop().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                default:
                    Picasso.get().load(url).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
            }
        } else {
            switch (imageScale) {
                case FIT_XY:
                    Picasso.get().load(resourceID).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                case CENTER_INSIDE:
                    Picasso.get().load(resourceID).centerInside().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                case CENTER_CROP:
                    Picasso.get().load(resourceID).centerCrop().fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
                    break;
                default:
                    Picasso.get().load(resourceID).fit().config(Bitmap.Config.RGB_565).placeholder(defaultResouces[0]).error(defaultResouces[0]).into(imageView);
            }
        }
    }
}
