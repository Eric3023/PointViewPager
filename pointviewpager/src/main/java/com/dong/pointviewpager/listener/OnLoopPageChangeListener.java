package com.dong.pointviewpager.listener;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.dong.pointviewpager.bean.Message;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by Dong on 2018/3/15.
 */

public abstract class OnLoopPageChangeListener implements ViewPager.OnPageChangeListener {

    private Observer stateObserver;
    private Observer scrollObserver;

    private int scroll_state;

    public void setStateObserver(Observer observer) {
        this.stateObserver = observer;
    }

    public void setScrollObserver(Observer observer) {
        this.scrollObserver = observer;
    }

    @Override
    public void onPageSelected(int position) {
        onViewPageSelected(position);
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, int positionOffsetPixels) {
        if (scrollObserver != null) {
            Observable.create(new ObservableOnSubscribe<Message>() {
                @Override
                public void subscribe(ObservableEmitter<Message> e) throws Exception {
                    Message message = new Message(position, positionOffset);
                    e.onNext(message);
                }
            }).subscribe(scrollObserver);
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

        if (stateObserver != null) {
            Observable.create(new ObservableOnSubscribe<Integer>() {
                @Override
                public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    e.onNext(scroll_state);
                }
            }).subscribe(stateObserver);
        }

        onViewPageScrollStateChanged(state);
    }

    protected abstract void onViewPageSelected(int position);

    protected abstract void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    protected abstract void onViewPageScrollStateChanged(int state);
}
