package com.dong.pointandviewpager.sample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.transformer.GalleryTransformer;
import com.dong.pointviewpager.widget.GalleryViewPager;
import com.dong.pointviewpager.widget.LoopViewPager;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryViewPager galleryViewPager = findViewById(R.id.galleryviewpager);
        LoopViewPager loopViewPager = galleryViewPager.getLoopViewPager();

        //设置Gallery中LoopViewPager的参数
        initLoopViewPager(loopViewPager);
        //设置Gallery中其他参数
        initGalleryViewPager(galleryViewPager);
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
                .setCard(true)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x10))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x5))//设置CardView的阴影宽度
                .setCardPadding(0)//设置CardView的Padding宽度
                .initialise();
    }

    private void initGalleryViewPager(GalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight(RelativeLayout.LayoutParams.MATCH_PARENT)//设置ViewPager的高度
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .initialise();
    }

}
