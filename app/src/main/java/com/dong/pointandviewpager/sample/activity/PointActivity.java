package com.dong.pointandviewpager.sample.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
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

    private LoopViewPager loopViewPager;
    private PointView pointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        PointViewPager pointViewPager = findViewById(R.id.pointViewPager);

        loopViewPager = pointViewPager.getLoopViewPager();

        PointView pointView = pointViewPager.getPointView();

        initLoopViewPager(loopViewPager);

        initPointView(pointView);

        handler.sendEmptyMessageDelayed(10 , 1000*3);
    }

    List<LoopViewPagerBean> beans = new DataManager().getUrlBeans();

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            beans.addAll(beans);
            loopViewPager.getAdapter().notifyDataSetChanged();
            return false;
        }
    });

    private void initLoopViewPager(LoopViewPager loopViewPager) {
        //配置LoopViewPager参数
        loopViewPager.setImageScale(ImageView.ScaleType.FIT_CENTER)//修改视图的填充类型
                .setLoop(true)//设置是否循环(图片数量大于3有效)
                .setAuto(false)//设置是否自动播放
                .setAutoTime(5)//设置图片时间间隔
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
                .setBeans(beans)//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .setCard(false)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x10))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x5))//设置CardView的阴影宽度
                .setCardPadding((int) getResources().getDimension(R.dimen.x3))//设置CardView的Padding宽度
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

    @Override
    protected void onDestroy() {
        loopViewPager.destoryViewPager();
        super.onDestroy();
    }
}
