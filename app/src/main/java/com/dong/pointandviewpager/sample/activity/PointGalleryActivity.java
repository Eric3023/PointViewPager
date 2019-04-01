package com.dong.pointandviewpager.sample.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.widget.GalleryViewPager;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointGalleryViewPager;
import com.dong.pointviewpager.widget.PointView;

import java.util.List;

public class PointGalleryActivity extends AppCompatActivity {

    private LoopViewPager loopViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_gallery);

        PointGalleryViewPager pointGalleryViewPager = findViewById(R.id.pointGalleryViewPager);

        loopViewPager = pointGalleryViewPager.getLoopViewPager();
        PointView pointView = pointGalleryViewPager.getPointView();

        //设置PointGallery中LoopViewPager的参数
        initLoopViewPager(loopViewPager);
        //设置PointGallery中PoitView的参数
        initPointView(pointView);
        //设置PointGallery中其他参数
        initGalleryViewPager(pointGalleryViewPager);

        handler.sendEmptyMessageDelayed(10, 1000*3);

    }

    List<LoopViewPagerBean> beans = new DataManager().getUrlBeans();
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            beans.addAll(beans);
            loopViewPager.initialise();
            return false;
        }
    });

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        loopViewPager.setAuto(false)
                .setImageScale(LoopViewPager.FIT_XY)
                .setBeans(beans)
                .setAutoTime(3)
                .setDefaultResouces(new int[]{R.drawable.img0})
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)
                .setLoop(true)
                .setCard(true)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x1))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x1))//设置CardView的阴影宽度
                .setCardPadding(0)//设置CardView的Padding宽度
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
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight((int) (getResources().getDimension(R.dimen.y150)))//设置ViewPager的高度，原高度/PageCenterScale
                .setPageDistance((int) (getResources().getDimension(R.dimen.x15))) // 两侧页卡的缩进距离
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .setPageRotation(0)
                .initialise();
    }

    @Override
    protected void onDestroy() {
        loopViewPager.destoryViewPager();
        super.onDestroy();
    }
}
