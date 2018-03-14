package com.dong.pointviewpager.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.adapter.PointPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2018/3/13.
 */

public class PointViewPager extends FrameLayout {

    private Context mContext;
    private ViewPager mViewPager;
    private List<Integer> mImageResources = new ArrayList<>();//图片格式资源
    private List<String> mImageString = new ArrayList<>();//图片格式url
    private List<ImageView> mImageViews= new ArrayList<ImageView>();
    private int defaultResouce = R.drawable.img_default;//默认显示的图片
    private int defaultCount = 5;//默认显示的数量
    private ImageView.ScaleType imageType = ImageView.ScaleType.FIT_XY;//默认图片的伸缩模式
    private boolean isLoop = false;//是否循环播放
    private boolean isAuto = false;//是否自动播放
    private int mAutoTime= 1000*3;//自动播放间隔


    public PointViewPager(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PointViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PointViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        initViewPager(context);
    }

    /*
     * 初始化
     */
    private void initViewPager(Context context) {
        mContext=context;
        mViewPager=new ViewPager(mContext);
        addView(mViewPager);

        LayoutParams layoutParams = (LayoutParams) mViewPager.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;
        mViewPager.setLayoutParams(layoutParams);

        if(mImageResources.size()==0){
            for (int i = 0; i < 5; i++) {
                mImageResources.add(defaultResouce);
            }
        }

        if(mImageString.size()!=0){
            for (int i = 0; i < mImageString.size(); i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(imageType);
//                Picasso.get().load(mImageString.get(i)).placeholder(defaultResouce).error(defaultResouce).into(imageView);
                mImageViews.add(imageView);
            }
        }else{
            for (int i = 0; i < mImageResources.size(); i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(imageType);
                imageView.setImageResource(mImageResources.get(i));
                mImageViews.add(imageView);
            }
        }

        PointPagerAdapter pagerAdapter = new PointPagerAdapter(mImageViews,isLoop, isAuto, mAutoTime);
        mViewPager.setAdapter(pagerAdapter);

        if(isLoop)
            mViewPager.setCurrentItem(pagerAdapter.getCount()/2);
    }

    public List<Integer> getImageResources() {
        return mImageResources;
    }

    public void setImageResources(List<Integer> mResources) {
        this.mImageResources = mResources;
    }

    public List<String> getmImageString() {
        return mImageString;
    }

    public void setImageString(List<String> mImages) {
        this.mImageString = mImages;
    }

    public ImageView.ScaleType getImageType() {
        return imageType;
    }

    public void setImageType(ImageView.ScaleType imageType) {
        this.imageType = imageType;
    }

    public int getAutoTime() {
        return mAutoTime;
    }

    public void setAutoTime(int mAutoTime) {
        this.mAutoTime = mAutoTime;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public void setOnPagerChangeListener(ViewPager.OnPageChangeListener listener){
        if(mViewPager!=null)
            mViewPager.addOnPageChangeListener(listener);
    }


}
