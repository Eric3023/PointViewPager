package com.dong.pointandviewpager.sample.model;

import android.util.Log;

import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;

/**
 * Created by Dong on 2018/3/20.
 */

public class ListenerManager {

    //选中监听
    public static OnLoopPageChangeListener onLoopPageChangeListener = new OnLoopPageChangeListener() {
        @Override
        protected void onViewPageSelected(int position) {
            Log.i("Dong", "选中Position:"+position);
        }

        @Override
        protected void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        protected void onViewPageScrollStateChanged(int state) {

        }
    };

    //点击监听
    public static  OnLoopPagerClickListener onLoopPagerClickListener = new OnLoopPagerClickListener() {
        @Override
        public void onLoopPagerClick(int position, LoopViewPagerBean bean) {
            Log.i("Dong", "点击Position:"+position);
        }
    };
}
