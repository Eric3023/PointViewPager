package com.dong.pointandviewpager.sample.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointandviewpager.sample.model.UrlConfige;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
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

        //配置LoopViewPager参数
        loopViewPager.setImageScale(LoopViewPager.CENTER_INSIDE)//修改视图的填充类型
                .setLoop(true)//设置是否循环(图片数量大于3有效)
                .setAuto(true)//设置是否自动播放
                .setAutoTime(5)//设置图片时间间隔
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
                .setBeans(new DataManager().getUrlBeans())//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)

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
}
