package com.dong.pointviewpager.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.adapter.LoopPagerAdapter;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.squareup.picasso.Picasso;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private int defaultCount = 5;//默认显示的数量
    private ImageView.ScaleType imageType = ImageView.ScaleType.FIT_XY;//默认图片的伸缩模式
    private List<Integer> imageResources = new ArrayList<Integer>();//图片格式资源
    private List<String> imageString = new ArrayList<String>();//图片格式url(优先使用)
    private List<ImageView> imageViews = new ArrayList<ImageView>();
    private int[] defaultResouces = {R.drawable.img_default0, R.drawable.img_default1, R.drawable.img_default2};//默认显示占位图片

    private OnLoopPageChangeListener onLoopPageChangeListener;
    //滑动状态观察者
    private Observer stateObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object value) {
            scroll_state = (int)value;
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

    public void setLoop(boolean loop) {
        isLoop = loop;
        loopCheck();
        loopPagerAdapter = new LoopPagerAdapter(imageViews, isLoop, isAuto, autoTime);
        setAdapter(loopPagerAdapter);
        loopCheck();
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
        autoPlay();
    }

    public int getAutoTime() {
        return autoTime;
    }

    public void setAutoTime(int autoTime) {
        this.autoTime = autoTime;
        autoPlay();
    }

    public int getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(int defaultCount) {
        if (defaultCount > 0) {
            this.defaultCount = defaultCount;
            imageResources.clear();
            this.imageViews.clear();
            for (int i = 0; i < defaultCount; i++)
                imageResources.add(defaultResouces[i % defaultResouces.length]);
            this.imageResources.addAll(imageResources);
            for (int i = 0; i < imageResources.size(); i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(imageType);
                imageView.setImageResource(imageResources.get(i));
                imageViews.add(imageView);
            }

            loopPagerAdapter.notifyDataSetChanged();
            loopCheck();
        }
    }

    public ImageView.ScaleType getImageType() {
        return imageType;
    }

    public void setImageType(ImageView.ScaleType imageType) {
        this.imageType = imageType;
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setScaleType(this.imageType);
        }

        loopPagerAdapter.notifyDataSetChanged();
        loopCheck();
    }

    public List<Integer> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<Integer> imageResources) {
        this.imageViews.clear();
        if (imageResources != null && imageResources.size() != 0) {
            this.imageResources.clear();
            this.imageResources.addAll(imageResources);
            for (int i = 0; i < imageResources.size(); i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(imageType);
                imageView.setImageResource(imageResources.get(i));
                imageViews.add(imageView);
            }
        } else {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(imageType);
            imageView.setImageResource(this.imageResources.get(0));
            imageViews.add(imageView);
        }

        loopPagerAdapter.notifyDataSetChanged();
        loopCheck();
    }

    public List<String> getImageString() {
        return imageString;
    }

    public void setImageString(List<String> imageString) {
        this.imageString = imageString;
        imageViews.clear();
        if (imageString != null && imageString.size() != 0) {
            for (int i = 0; i < imageString.size(); i++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(imageType);
                Picasso.get().load(imageString.get(i)).placeholder(imageResources.get(i % imageResources.size())).error(imageResources.get(i % imageResources.size())).into(imageView);
                imageViews.add(imageView);
            }
        } else {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(imageType);
            imageView.setImageResource(imageResources.get(0));
            imageViews.add(imageView);
        }

        loopPagerAdapter.notifyDataSetChanged();
        loopCheck();
    }

    public int[] getDefaultResouces() {
        return defaultResouces;
    }

    public void setDefaultResouces(int[] defaultResouces) {
        if (defaultResouces.length != 0) {
            this.defaultResouces = defaultResouces;
            imageResources.clear();
            for (int i = 0; i < defaultResouces.length; i++) {
                imageResources.add(defaultResouces[i]);
            }
        }

    }

    public OnLoopPageChangeListener getPageChangeListener() {
        return onLoopPageChangeListener;
    }

    public void setOnLoopPageChangeListener(OnLoopPageChangeListener onLoopPageChangeListener) {
        this.onLoopPageChangeListener = onLoopPageChangeListener;
        onLoopPageChangeListener.setObserver(stateObserver);
        addOnPageChangeListener(this.onLoopPageChangeListener);
    }

    /*
     * 初始化
     */
    private void init(Context context) {
        this.context = context;

        //设置占位图片
        for (int i = 0; i < defaultCount; i++)
            imageResources.add(defaultResouces[i % defaultResouces.length]);

        for (int i = 0; i < imageResources.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(imageType);
            imageView.setImageResource(imageResources.get(i));
            imageViews.add(imageView);
        }

        loopPagerAdapter = new LoopPagerAdapter(imageViews, isLoop, isAuto, autoTime);
        setAdapter(loopPagerAdapter);

        //设置监听
        onLoopPageChangeListener = new OnLoopPageChangeListener() {
            @Override
            protected void onViewPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }
        };
        addOnPageChangeListener(onLoopPageChangeListener);
    }

    public void loopCheck(){
        if(isLoop&&loopPagerAdapter.getCount() <=3){
            setCurrentItem(0, false);
        }else{
            setCurrentItem(loopPagerAdapter.getCount() / 2, false);
        }
    }

    public void autoPlay(){

        if(disposable!=null&&!disposable.isDisposed())
            disposable.dispose();

        if(isAuto){

            Observable.interval(autoTime,TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onNext(Long value) {
                            int current = getCurrentItem();
                            if(current+1 < loopPagerAdapter.getCount() && scroll_state == SCROLL_STATE_IDLE){
                                setCurrentItem(current+1, true);
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

}
