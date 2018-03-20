package com.dong.pointandviewpager.sample.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.widget.GalleryViewPager;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointGalleryViewPager;
import com.dong.pointviewpager.widget.PointView;

public class PointGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_gallery);

        PointGalleryViewPager pointGalleryViewPager = findViewById(R.id.pointGalleryViewPager);

        LoopViewPager loopViewPager = pointGalleryViewPager.getLoopViewPager();
        PointView pointView = pointGalleryViewPager.getPointView();

        //设置PointGallery中LoopViewPager的参数
        initLoopViewPager(loopViewPager);
        //设置PointGallery中PoitView的参数
        initPointView(pointView);
        //设置PointGallery中其他参数
        initGalleryViewPager(pointGalleryViewPager);

    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        loopViewPager.setAuto(false)
                .setImageScale(LoopViewPager.FIT_XY)
                .setBeans(new DataManager().getUrlBeans())
                .setAutoTime(3)
                .setDefaultResouces(new int[]{R.drawable.img0})
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)
                .setLoop(true)
                .initialise();
    }

    private void initPointView(PointView pointView) {
        pointView.setNfColor(Color.RED)//设置未被选中时小圆点的颜色(默认白色)
                .setfColor(Color.BLUE)//设置选中时小圆点的演的（默认红色）
                .setDisbottom(getResources().getDimension(R.dimen.x10))//设置距离控件底部的距离
                .setDistance(getResources().getDimension(R.dimen.x8))//设置小圆点之间的间隔距离
                .setRudis(getResources().getDimension(R.dimen.x3))//设置小圆点的半径
                .setScrollType(PointView.SMOOTH_SCROLL)//设置小圆点的滑动方式（INSTANT_SCROLL或SMOOTH_SCROLL）
                .initialise();
    }

    private void initGalleryViewPager(PointGalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x280))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight(RelativeLayout.LayoutParams.MATCH_PARENT)//设置ViewPager的高度
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .initialise();
    }
}
