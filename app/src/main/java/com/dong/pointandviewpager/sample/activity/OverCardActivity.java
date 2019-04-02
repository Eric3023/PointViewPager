package com.dong.pointandviewpager.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dong.pointandviewpager.R;
import com.dong.pointandviewpager.sample.model.DataManager;
import com.dong.pointandviewpager.sample.model.ListenerManager;
import com.dong.pointviewpager.widget.LoopViewPager;
import com.dong.pointviewpager.widget.OverCardViewPager;

public class OverCardActivity extends AppCompatActivity {

    private OverCardViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_card);

        //配置OverCardViewPager参数
        pager = findViewById(R.id.overCardViewPager);
        pager.setImageScale(ImageView.ScaleType.FIT_XY)//修改视图的填充类型
                .setLoop(true)//设置是否循环(图片数量大于3有效)
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
                .setBeans(new DataManager().getUrlBeans())//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .setCard(true)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x3))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x2))//设置CardView的阴影宽度
                .setCardPadding((int) getResources().getDimension(R.dimen.x2))//设置CardView的Padding宽度
                .setmOffset(getResources().getDimension(R.dimen.y10))//设置重叠卡片的下移距离
                .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
