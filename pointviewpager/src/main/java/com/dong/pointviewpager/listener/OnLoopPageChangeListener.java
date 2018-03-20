package com.dong.pointviewpager.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.dong.pointviewpager.bean.ScrollBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by Dong on 2018/3/15.
 */

public abstract class OnLoopPageChangeListener implements ViewPager.OnPageChangeListener {

    private Observer observer;

    private int scroll_state;

    private int position;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onPageSelected(final int position) {
        if (observer != null) {
            Observable.create(new ObservableOnSubscribe<ScrollBean>() {
                @Override
                public void subscribe(ObservableEmitter<ScrollBean> e) throws Exception {
                    ScrollBean bean =new ScrollBean();
                    bean.setSelect_position(position);
                    e.onNext(bean);
                }
            }).subscribe(observer);
        }
        onViewPageSelected(this.position);
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, int positionOffsetPixels) {
        if (observer != null) {
            Observable.create(new ObservableOnSubscribe<ScrollBean>() {
                @Override
                public void subscribe(ObservableEmitter<ScrollBean> e) throws Exception {
                    ScrollBean bean = new ScrollBean();
                    bean.setScroll_positon(position);
                    bean.setScroll_percent(positionOffset);
                    e.onNext(bean);
                }
            }).subscribe(observer);
        }

        onViewPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                scroll_state = ViewPager.SCROLL_STATE_IDLE;
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                scroll_state = ViewPager.SCROLL_STATE_DRAGGING;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                scroll_state = ViewPager.SCROLL_STATE_SETTLING;
                break;
        }

        if (observer != null) {
            Observable.create(new ObservableOnSubscribe<ScrollBean>() {
                @Override
                public void subscribe(ObservableEmitter<ScrollBean> e) throws Exception {
                    ScrollBean bean =new ScrollBean();
                    bean.setScroll_state(scroll_state);
                    e.onNext(bean);
                }
            }).subscribe(observer);
        }

        onViewPageScrollStateChanged(state);
    }

    protected abstract void onViewPageSelected(int position);

    protected abstract void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    protected abstract void onViewPageScrollStateChanged(int state);
}
