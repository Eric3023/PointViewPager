package com.dong.pointviewpager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dong.pointviewpager.R;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.utils.ImageLoadUtil;
import com.dong.pointviewpager.widget.RoundImageView;

import java.util.List;

import me.panpf.sketch.SketchImageView;

/**
 * Created by Dong on 2018/3/13.
 */

public class LoopPagerAdapter extends PagerAdapter {

    private Context context;
    private List<LoopViewPagerBean> beans;
    private ImageView.ScaleType imageScale;
    private int defaultResource;
    private OnLoopPagerClickListener onLoopPagerClickListener;
    private boolean isLoop;

    private boolean isCard;
    private float radius;
    private float elevation;
    private int padding;

    private int count;
    public onDataChangedListener onDataChangedListener;

    public LoopPagerAdapter(Context context, List<LoopViewPagerBean> list, ImageView.ScaleType imageScale, int defaultResource, OnLoopPagerClickListener onLoopPagerClickListener,
                            boolean isLoop, boolean isAuto, int mAutoTime, boolean isCard, float radius, float elevation, int padding) {
        this.context = context;
        this.beans = list;
        this.imageScale = imageScale;
        this.defaultResource = defaultResource;
        this.onLoopPagerClickListener = onLoopPagerClickListener;
        this.isLoop = isLoop;
        this.isCard = isCard;
        this.radius = radius;
        this.elevation = elevation;
        this.padding = padding;
    }

    public void setOnDataChangedListener(LoopPagerAdapter.onDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }

    @Override
    public int getCount() {
        if (beans != null)
            if (isLoop)
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
        if (isCard) {
            CardView cardView = (CardView) View.inflate(context, R.layout.item_pager, null);
            cardView.setBackgroundColor(Color.TRANSPARENT);
            cardView.setCardElevation(elevation);
            cardView.setContentPadding(padding, padding, padding, padding);
            RoundImageView imageView = cardView.findViewById(R.id.item_page_imageview);
            imageView.setScaleType(imageScale);
            imageView.setRadius(radius);
            if (beans != null && beans.size() != 0)
                ImageLoadUtil.loadImage(context, beans.get(position % beans.size()), imageView, defaultResource);
            imageView.setOnClickListener(onLoopPagerClickListener);
            container.addView(cardView);
            return cardView;
        } else {
            RoundImageView imageView = new RoundImageView(context);
            imageView.setScaleType(imageScale);
            imageView.setRadius(radius);
            if (beans != null && beans.size() != 0)
                ImageLoadUtil.loadImage(context, beans.get(position % beans.size()), imageView, defaultResource);
            imageView.setOnClickListener(onLoopPagerClickListener);
            container.addView(imageView);
            return imageView;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        count = getCount();
        if (onDataChangedListener != null)
            onDataChangedListener.onDataChanged();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (count > 0) {
            count--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public interface onDataChangedListener {
        void onDataChanged();
    }
}
