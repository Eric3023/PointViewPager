package com.dong.pointviewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointPagerAdapter extends PagerAdapter {

    private List<ImageView> mList;
    private boolean isLoop;
    private boolean isAuto;
    private int mAutoTime;

    public PointPagerAdapter(List<ImageView> list, boolean isLoop, boolean isAuto, int mAutoTime) {
        this.mList = list;
        this.isLoop = isLoop;
        this.isAuto = isAuto;
        this.mAutoTime = mAutoTime;
    }

    @Override
    public int getCount() {
        if(mList!=null)
            if(isLoop)
                return Integer.MAX_VALUE;
            else
                return mList.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position%mList.size()));
        return mList.get(position%mList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
