package com.dong.pointandviewpager.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.widget.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class LoopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //配置LoopViewPager参数
        LoopViewPager pager = findViewById(R.id.loopViewPager);
        pager.setImageScale(LoopViewPager.CENTER_INSIDE)//修改视图的填充类型
                .setLoop(false)//设置是否循环(图片数量大于3有效)
                .setAuto(true)//设置是否自动播放
                .setAutoTime(5)//设置图片时间间隔
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
                .setBeans(new DataManager().getUrlBeans())//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .setCard(true)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x10))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x5))//设置CardView的阴影宽度
                .setCardPadding((int) getResources().getDimension(R.dimen.x3))//设置CardView的Padding宽度
                .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)
    }
}
