package com.dong.pointandviewpager.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.dong.pointandviewpager.R;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.dong.pointviewpager.widget.PointViewPager;

import java.util.ArrayList;
import java.util.List;

public class PointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        PointViewPager pointViewPager = findViewById(R.id.pointViewPager);

        LoopViewPager loopViewPager = pointViewPager.getLoopViewPager();

        PointView pointView = pointViewPager.getPointView();

        initLoopViewPager(loopViewPager);

        initPointView(pointView);
    }

    private void initLoopViewPager(LoopViewPager loopViewPager) {

        //设置是否循环(图片数量大于3有效)
        loopViewPager.setLoop(true);

        //设置默认显示的图片数量（未设置setImageResources、setImageString时有效）
        loopViewPager.setDefaultCount(10);

        //设置默认显示的背景图片(在setImageResources、setImageString前调用)
        int[] resources = {R.drawable.img1};
        loopViewPager.setDefaultResouces(resources);

        //以url的形式修改图片数据源（与setImageResources互斥，只有一个有效）
        List<String> imageString = new ArrayList<String>();
        imageString.add("http://img.zcool.cn/community/01561959967047a80121560336a49c.jpg@2o.jpg");
        imageString.add("http://img5.duitang.com/uploads/item/201403/24/20140324103016_h8TGu.jpeg");
        loopViewPager.setImageString(imageString);

        //以resource的形式修改展示图片的数据源（与setImageString互斥，只有一个有效）
        List<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.img0);
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        images.add(R.drawable.img0);
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        loopViewPager.setImageResources(images);

        //修改图片的填充类型
        loopViewPager.setImageType(ImageView.ScaleType.CENTER_INSIDE);

        //设置自动播放
        loopViewPager.setAuto(true);

        //设置图片时间间隔
        loopViewPager.setAutoTime(5);

        //设置选中监听，替代addOnPageChangeListener方法
        loopViewPager.setOnLoopPageChangeListener(new OnLoopPageChangeListener() {

            @Override
            protected void onViewPageSelected(int position) {
                Log.i("Dong","选中Item:"+position);
            }

            @Override
            protected void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            protected void onViewPageScrollStateChanged(int state) {

            }
        });

    }

    private void initPointView(PointView pointView) {
        pointView.setNfColor(Color.RED);
        pointView.setfColor(Color.BLUE);
//        pointView.setDisbottom(20);
//        pointView.setDistance(20);
//        pointView.setRudis(20);
        pointView.setScrollType(PointView.INSTANT_SCROLL);
        pointView.setScrollType(PointView.SMOOTH_SCROLL);
    }
}
