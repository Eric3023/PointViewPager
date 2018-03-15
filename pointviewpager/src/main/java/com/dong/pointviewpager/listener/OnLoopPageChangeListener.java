package com.dong.pointviewpager.listener;

import android.support.v4.view.ViewPager;

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

    public void setObserver(Observer observer) {
        this.observer = observer;
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
            Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    e.onNext(scroll_state);
                }
            }).subscribe(observer);
        }

        onViewPageScrollStateChanged(state);
    }

    protected abstract void onViewPageScrollStateChanged(int state);
}