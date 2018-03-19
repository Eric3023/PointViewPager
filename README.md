# PointViewPager
### 使用手册
V2.0.0版本，修复了Out of Memory异常，添加点击监听，参数修改为链式调用，优化数据源的类型  

github地址：https://github.com/Eric3023/PointViewPager
#### 1.工程的build.gradle文件中添加依赖  
allprojects {

    repositories {  
        maven { url 'https://jitpack.io' }  
    }
}


dependencies {

    compile 'com.github.Eric3023:PointViewPager:v2.0.0'
    
}

#### 2.LoopViewPager的使用

布局文件中添加LoopViewPager控件：

```xml
<com.dong.pointviewpager.widget.LoopViewPager
    android:id="@+id/loopViewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

java代码中设置控件的属性,根据需要设置部分属性，不用全部设置

```java
//配置LoopViewPager参数
LoopViewPager pager = findViewById(R.id.loopViewPager);
pager.setImageScale(LoopViewPager.CENTER_INSIDE)//修改视图的填充类型 LoopViewPager.CENTER_INSIDE、CENTER_CROP、FIT_XY三种
     .setLoop(false)//设置是否循环(图片数量大于3有效)
     .setAuto(true)//设置是否自动播放
     .setAutoTime(5)//设置图片自动播放的时间间隔
     .setOnLoopPageChangeListener(onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
     .setOnLoopPagerClickListener(onLoopPagerClickListener)//设置点击监听
     .setBeans(resourceBeans)//设置数据源
     .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
     .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)
```

选中监听（OnLoopPageChangeListener）和点击监听（OnLoopPagerClickListener）


```java
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

```


数据源beans是个集合（List<LoopViewPagerBean>），LoopViewPagerBean包含Object object、String url、int resourceID三个参数，Object根据项目自行设定类型，url为图片的url路径，一般由object的getxxx方法获得，也可自行设置，resourceID为图片的资源路径（url和resourceID，优先选择URL作为数据源）


举例说明数据源的初始化：
```java
//数据源

List<LoopViewPagerBean> resourceBeans = new ArrayList<LoopViewPagerBean>();
LoopViewPagerBean resourceBean1 = new LoopViewPagerBean(R.drawable.img0, null);
LoopViewPagerBean resourceBean2 = new LoopViewPagerBean(R.drawable.img1, null);
LoopViewPagerBean resourceBean3 = new LoopViewPagerBean(R.drawable.img2, null);
LoopViewPagerBean resourceBean4 = new LoopViewPagerBean(R.drawable.img0, null);
resourceBeans.add(resourceBean1);
resourceBeans.add(resourceBean2);
resourceBeans.add(resourceBean3);
resourceBeans.add(resourceBean4);
```


##### 效果：
 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/1.gif)

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
##### 效果：
SMOOTH_SCROLL


 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/2.gif)
 
 INSTANT_SCROLL
 
 
 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/3.gif)

