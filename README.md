# PointViewPager
### 使用手册
#### 1.工程的build.gradle文件中添加依赖  
allprojects {

    repositories {  
        maven { url 'https://jitpack.io' }  
    }
}


dependencies {

    compile 'com.github.Eric3023:PointViewPager:v1.2.0'
    
}

#### 2.LoopViewPager的使用

布局文件中添加LoopViewPager控件：

```xml
<com.dong.pointviewpager.widget.LoopViewPager
    android:id="@+id/loopViewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

java代码中设置控件的属性

```java
LoopViewPager pager = findViewById(R.id.loopViewPager);

//设置是否循环(图片数量大于3有效)
pager.setLoop(true);

//设置默认显示的图片数量（未设置setImageResources、setImageString时有效）
pager.setDefaultCount(10);

//设置默认占位图片(在setImageResources、setImageString前调用)
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

//设置是否自动播放
pager.setAuto(true);

//设置自动播放时图片时间间隔
pager.setAutoTime(5);

//设置监听，替代addOnPageChangeListener方法
pager.setOnLoopPageChangeListener(new OnLoopPageChangeListener() {

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
```

#### 2.PonitViewPager的使用
布局文件中添加LoopViewPager控件：

```xml
<com.dong.pointviewpager.widget.PointViewPager
   android:id="@+id/pointViewPager"
   android:layout_width="match_parent"
   android:layout_height="match_parent"/>
```

java代码中设置控件的属性

```java
PointViewPager pointViewPager = findViewById(R.id.pointViewPager);

LoopViewPager loopViewPager = pointViewPager.getLoopViewPager();

PointView pointView = pointViewPager.getPointView();
//设置LoopViewPager（轮播图）的属性（方法参考上面）
initLoopViewPager(loopViewPager);
//设置PointView（小圆点）的属性
initPointView(pointView);
```
```java
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
   pointView.setScrollType(PointView.INSTANT_SCROLL);
   pointView.setScrollType(PointView.SMOOTH_SCROLL);
}
```
