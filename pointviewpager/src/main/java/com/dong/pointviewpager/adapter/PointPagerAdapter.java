package com.dong.pointviewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImageStrings;
    private List<Integer> mImageResources;
    private boolean isLoop;
    private boolean isAuto;
    private int mAutoTime;
    private ImageView imageView;

    public PointPagerAdapter(Context context, List<String> imageStrings, List<Integer> imageResources, boolean isLoop, boolean isAuto, int mAutoTime) {
        this.mContext = context;
        this.mImageStrings = imageStrings;
        this.mImageResources = imageResources;
        this.isLoop = isLoop;
        this.isAuto = isAuto;
        this.mAutoTime = mAutoTime;

        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getCount() {
        if(mImageStrings!=null){
            if(isLoop)
                return Integer.MAX_VALUE;
            else
                return mImageStrings.size();
        }else{
            if(isLoop)
                return Integer.MAX_VALUE;
            else
                return mImageResources.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(mImageStrings!=null){
//            imageView.setImageResource(mImageResources.get(position));
        }else{
            if(isLoop)
            imageView.setImageResource(mImageResources.get(position));
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
