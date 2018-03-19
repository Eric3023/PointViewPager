package com.dong.pointviewpager.listener;

import android.app.FragmentManager;
import android.view.View;

import com.dong.pointviewpager.bean.LoopViewPagerBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by Dong on 2018/3/19.
 */

public abstract class OnLoopPagerClickListener implements View.OnClickListener{

    private int position;
    private LoopViewPagerBean bean;
    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setBean(LoopViewPagerBean bean) {
        this.bean = bean;
    }

    @Override
    public void onClick(View view) {

        if(observer!=null){
            Observable.create(new ObservableOnSubscribe<Object>() {
                @Override
                public void subscribe(ObservableEmitter<Object> e) throws Exception {
                    e.onNext("Onclick");
                }
            }).subscribe(observer);
        }

        onLoopPagerClick(position, bean);
    }

    public abstract void onLoopPagerClick(int position, LoopViewPagerBean bean);
}
