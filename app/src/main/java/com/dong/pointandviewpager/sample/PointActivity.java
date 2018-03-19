package com.dong.pointandviewpager.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.dong.pointandviewpager.R;
import com.dong.pointviewpager.bean.LoopViewPagerBean;
import com.dong.pointviewpager.listener.OnLoopPageChangeListener;
import com.dong.pointviewpager.listener.OnLoopPagerClickListener;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.PointView;
import com.dong.pointviewpager.widget.PointViewPager;

import java.util.ArrayList;
import java.util.List;

public class PointActivity extends AppCompatActivity {

    private final String URL1="http://img.zcool.cn/community/01561959967047a80121560336a49c.jpg@2o.jpg";
    private final String URL2="http://img5.duitang.com/uploads/item/201403/24/20140324103016_h8TGu.jpeg";
    private OnLoopPageChangeListener onLoopPageChangeListener = new OnLoopPageChangeListener() {
        @Override
        protected void onViewPageSelected(int position) {
            Log.i("Dong", "选中Position:"+position);
        }

        @Override
        protected void onViewPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        protected void onViewPageScrollStateChanged(int state) {

        }
    };

    private OnLoopPagerClickListener onLoopPagerClickListener = new OnLoopPagerClickListener() {
        @Override
        public void onLoopPagerClick(int position, LoopViewPagerBean bean) {
            Log.i("Dong", "点击Position:"+position);
        }
    };


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

        //数据源
        List<LoopViewPagerBean> urlBeans = new ArrayList<LoopViewPagerBean>();
        LoopViewPagerBean urlBean1 = new LoopViewPagerBean(URL1, null);
        LoopViewPagerBean urlBean2 = new LoopViewPagerBean(URL2, null);
        urlBeans.add(urlBean1);
        urlBeans.add(urlBean2);

        List<LoopViewPagerBean> resourceBeans = new ArrayList<LoopViewPagerBean>();
        LoopViewPagerBean resourceBean1 = new LoopViewPagerBean(R.drawable.img0, null);
        LoopViewPagerBean resourceBean2 = new LoopViewPagerBean(R.drawable.img1, null);
        LoopViewPagerBean resourceBean3 = new LoopViewPagerBean(R.drawable.img2, null);
        LoopViewPagerBean resourceBean4 = new LoopViewPagerBean(R.drawable.img0, null);
        LoopViewPagerBean resourceBean5 = new LoopViewPagerBean(R.drawable.img1, null);
        LoopViewPagerBean resourceBean6 = new LoopViewPagerBean(R.drawable.img2, null);
        resourceBeans.add(resourceBean1);
        resourceBeans.add(resourceBean2);
        resourceBeans.add(resourceBean3);
        resourceBeans.add(resourceBean4);
        resourceBeans.add(resourceBean5);
        resourceBeans.add(resourceBean6);

        //配置LoopViewPager参数
        loopViewPager.setImageScale(LoopViewPager.CENTER_INSIDE)//修改视图的填充类型
                .setLoop(true)//设置是否循环(图片数量大于3有效)
                .setAuto(true)//设置是否自动播放
                .setAutoTime(5)//设置图片时间间隔
                .setOnLoopPageChangeListener(onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
                .setOnLoopPagerClickListener(onLoopPagerClickListener)//设置点击监听
                .setBeans(resourceBeans)//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)

    }

    private void initPointView(PointView pointView) {
        //设置未被选中时小圆点的颜色(默认白色)
        pointView.setNfColor(Color.RED);
        //设置选中时小圆点的演的（默认红色）
        pointView.setfColor(Color.BLUE);
        //设置距离控件底部的距离
        pointView.setDisbottom(getResources().getDimension(R.dimen.x10));
        //设置小圆点之间的间隔距离
        pointView.setDistance(getResources().getDimension(R.dimen.x8));
        //设置小圆点的半径
        pointView.setRudis(getResources().getDimension(R.dimen.x3));
        //设置小圆点的滑动方式（INSTANT_SCROLL或SMOOTH_SCROLL）
//        pointView.setScrollType(PointView.INSTANT_SCROLL);
        pointView.setScrollType(PointView.SMOOTH_SCROLL);
    }
}
