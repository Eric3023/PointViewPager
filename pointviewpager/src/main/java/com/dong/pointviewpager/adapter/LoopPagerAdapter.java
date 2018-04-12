package com.dong.pointviewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.utils.ImageLoadUtil;

import java.util.List;

/**
 * Created by Dong on 2018/3/13.
 */

public class LoopPagerAdapter extends PagerAdapter {

    private Context context;
    private List<LoopViewPagerBean> beans;
    private int imageScale;
    private int defaultResource;
    private OnLoopPagerClickListener onLoopPagerClickListener;
    private boolean isLoop;
    private boolean isAuto;
    private int mAutoTime;

    private int count;
    public onDataChangedListener onDataChangedListener;

    public LoopPagerAdapter(Context context, List<LoopViewPagerBean> list, int imageScale, int defaultResource, OnLoopPagerClickListener onLoopPagerClickListener, boolean isLoop, boolean isAuto, int mAutoTime) {
        this.context = context;
        this.beans = list;
        this.imageScale = imageScale;
        this.defaultResource = defaultResource;
        this.onLoopPagerClickListener = onLoopPagerClickListener;
        this.isLoop = isLoop;
        this.isAuto = isAuto;
        this.mAutoTime = mAutoTime;
    }

    public void setOnDataChangedListener(LoopPagerAdapter.onDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }

    @Override
    public int getCount() {
        if(beans!=null)
            if(isLoop)
                return Integer.MAX_VALUE;
            else
                return beans.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        if(beans!=null && beans.size()!=0)
            ImageLoadUtil.loadImage(beans.get(position%beans.size()), imageView, imageScale, defaultResource);
        imageView.setOnClickListener(onLoopPagerClickListener);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        count = getCount();
        onDataChangedListener.onDataChanged();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if(count>0){
            count--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public interface onDataChangedListener{
        void onDataChanged();
    }
}
