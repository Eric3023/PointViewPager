package com.dong.pointandviewpager.sample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.transformer.GalleryTransformer;
import com.dong.pointviewpager.widget.GalleryViewPager;
import com.dong.pointviewpager.widget.LoopViewPager;

public class GalleryActivity extends AppCompatActivity {

    private LoopViewPager loopViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryViewPager galleryViewPager = findViewById(R.id.galleryviewpager);
        loopViewPager = galleryViewPager.getLoopViewPager();

        //设置Gallery中LoopViewPager的参数
        initLoopViewPager(loopViewPager);
        //设置Gallery中其他参数
        initGalleryViewPager(galleryViewPager);
    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        loopViewPager.setAuto(false)
                .setImageScale(ImageView.ScaleType.FIT_XY)
                .setBeans(new DataManager().getUrlBeans())
                .setAutoTime(3)
                .setDefaultResouces(new int[]{R.drawable.img0})
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)
                .setLoop(true)
                .setCard(false)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x1))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x1))//设置CardView的阴影宽度
                .setCardPadding(0)//设置CardView的Padding宽度
                .initialise();
    }

    private void initGalleryViewPager(GalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight((int) (getResources().getDimension(R.dimen.y150)))//设置ViewPager的高度
                .setPageDistance((int) (getResources().getDimension(R.dimen.x50)))//设置两侧页卡的缩进距离
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .setPagerRotation(20)//设置两侧页卡的倾斜角度
                .initialise();
    }

    @Override
    protected void onDestroy() {
        loopViewPager.destoryViewPager();
        super.onDestroy();
    }

}
