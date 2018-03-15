package com.dong.pointandviewpager.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.dong.pointandviewpager.R;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.widget.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoopViewPager pager = findViewById(R.id.pointViewPager);

        //设置是否循环(图片数量大于3有效)
        pager.setLoop(true);

        //设置默认显示的图片数量（未设置setImageResources、setImageString时有效）
        pager.setDefaultCount(10);

        //设置默认显示的背景图片(在setImageResources、setImageString前调用)
        int[] resources ={R.drawable.img1};
        pager.setDefaultResouces(resources);

        //以url的形式修改图片数据源（与setImageResources互斥，只有一个有效）
        List<String> imageString = new ArrayList<String>();
        imageString.add("http://img.zcool.cn/community/01561959967047a80121560336a49c.jpg@2o.jpg");
        imageString.add("http://img5.duitang.com/uploads/item/201403/24/20140324103016_h8TGu.jpeg");
        pager.setImageString(imageString);

        //以resource的形式修改展示图片的数据源（与setImageString互斥，只有一个有效）
        List<Integer> images = new ArrayList<Integer>();
        images.add(R.drawable.img0);
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        images.add(R.drawable.img0);
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        pager.setImageResources(images);

        //修改图片的填充类型
        pager.setImageType(ImageView.ScaleType.CENTER_INSIDE);

        //设置自动播放
        pager.setAuto(true);

        //设置图片时间间隔
        pager.setAutoTime(5);

        //设置选中监听，替代addOnPageChangeListener方法
        pager.setOnLoopPageChangeListener(new OnLoopPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("Dong","选中Item:"+position);
            }

            @Override
            protected void onViewPageScrollStateChanged(int state) {

            }
        });

    }
}
