# PointViewPager

### 使用手册
github地址：https://github.com/Eric3023/PointViewPager

### 1.工程的build.gradle文件中添加依赖
```xml
allprojects {
    repositories {  
        maven { url 'https://jitpack.io' }  
    }
}
dependencies {
    compile 'com.github.Eric3023:PointViewPager:v2.4.5'   
}
```

### 2.控件使用
#### 2.1 LoopViewPager

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
        pager.setImageScale(LoopViewPager.CENTER_INSIDE)//修改视图的填充类型
                .setLoop(false)//设置是否循环(图片数量大于3有效)
                .setAuto(true)//设置是否自动播放
                .setAutoTime(5)//设置图片时间间隔
                .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听
                .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
                .setBeans(new DataManager().getUrlBeans())//设置数据源
                .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
                .setCard(true)//设置是否是CardView
                .setCardRadius(getResources().getDimension(R.dimen.x10))//设置CardView的圆角弧度
                .setCardElevation(getResources().getDimension(R.dimen.x5))//设置CardView的阴影宽度
                .setCardPadding((int) getResources().getDimension(R.dimen.x3))//设置CardView的Padding宽度
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

//数据源发生改变时
```java
        loopViewPager.getAdapter().notifyDataSetChanged();
````

//页面关闭是调用destoryViewPager()：
```java
        @Override
        protected void onDestroy() {
            loopViewPager.destoryViewPager();
            super.onDestroy();
        }
```

 ##### 效果：
 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/1.gif)

#### 2.2 PonitViewPager
布局文件中添加PointViewPager控件：

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

//设置LoopViewPager（轮播图）的属性（方法参考2.1,LoopViewPager的设置）
initLoopViewPager(loopViewPager);

//设置PointView（小圆点）的属性
initPointView(pointView);
```
```java
private void initPointView(PointView pointView) {
   pointView.setNfColor(Color.RED)//设置未被选中时小圆点的颜色(默认白色)
            .setfColor(Color.BLUE)//设置选中时小圆点的演的（默认红色）
            .setDisbottom(getResources().getDimension(R.dimen.x10))//设置距离控件底部的距离
            .setDistance(getResources().getDimension(R.dimen.x8))//设置小圆点之间的间隔距离
            .setRudis(getResources().getDimension(R.dimen.x3))//设置小圆点的半径
            .setScrollType(PointView.SMOOTH_SCROLL)//设置小圆点的滑动方式（INSTANT_SCROLL或SMOOTH_SCROLL）
            .initialise();
}
```

//数据源发生改变时
```java
loopViewPager.getAdapter().notifyDataSetChanged();
````
//页面关闭是调用destoryViewPager()：
```java
@Override
protected void onDestroy() {
    loopViewPager.destoryViewPager();
    super.onDestroy();
}
```

##### 效果：

 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/2.gif)
 

#### 2.3 GalleryViewPager

布局文件

```xml
<com.dong.pointviewpager.widget.GalleryViewPager
    android:id="@+id/galleryviewpager"
    android:layout_width="match_parent"
    android:layout_height="@dimen/y120"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent">
</com.dong.pointviewpager.widget.GalleryViewPager>
```
代码文件
```java
GalleryViewPager galleryViewPager = findViewById(R.id.galleryviewpager);
LoopViewPager loopViewPager = galleryViewPager.getLoopViewPager();

//设置Gallery中LoopViewPager的参数(方法同上2.1， LoopViewPager参数设置)
initLoopViewPager(loopViewPager);

//设置Gallery中其他参数
initGalleryViewPager(galleryViewPager);
```
```java
    private void initGalleryViewPager(GalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight((int) (getResources().getDimension(R.dimen.y150)))//设置ViewPager的高度
                .setPageDistance((int) (getResources().getDimension(R.dimen.x50)))//设置两侧页卡的缩进距离
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .setPagerRotation(20)//设置两侧页卡的倾斜角度
                .initialise();
    }
```

//数据源发生改变时
```java
loopViewPager.getAdapter().notifyDataSetChanged();
````
//页面关闭是调用destoryViewPager()：
```java
@Override
protected void onDestroy() {
    loopViewPager.destoryViewPager();
    super.onDestroy();
}
```
效果：

 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/GIF1.gif)
 
 
 #### 2.4 PointGalleryViewPager
 布局文件
```xml
<com.dong.pointviewpager.widget.PointGalleryViewPager
    android:id="@+id/pointGalleryViewPager"
    android:layout_width="match_parent"
    android:layout_height="@dimen/y120"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```
代码文件
```java
//设置PointGallery中LoopViewPager的参数(方法同2.1)
initLoopViewPager(loopViewPager);
//设置PointGallery中PoitView的参数（方法同2.2）
initPointView(pointView);
//设置PointGallery中其他参数
initGalleryViewPager(pointGalleryViewPager);
```
```
    private void initGalleryViewPager(PointGalleryViewPager galleryViewPager) {
        galleryViewPager.setPageWidth((int) getResources().getDimension(R.dimen.x240))//设置ViewPager的宽度，适当小于GalleryViewPager的宽度
                .setPageHeight((int) (getResources().getDimension(R.dimen.y150)))//设置ViewPager的高度，原高度/PageCenterScale
                .setPageDistance((int) (getResources().getDimension(R.dimen.x15))) // 两侧页卡的缩进距离
                .setPageScale((float) 0.8)//设置两侧隐藏页面的缩放比例
                .setPageAlpha((float) 0.5)//设置两侧隐藏页面的透明度
                .setPageRotation(0)
                .initialise();
    }
```
//数据源发生改变时
```java
loopViewPager.initialise();
````
//页面关闭是调用destoryViewPager()：
```java
@Override
protected void onDestroy() {
    loopViewPager.destoryViewPager();
    super.onDestroy();
}
```

效果：

 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/GIF2.gif)
 
#### 2.5 OverCardViewPager

布局文件中添加OverCardViewPager控件：

```xml
    <com.dong.pointviewpager.widget.OverCardViewPager
        android:id="@+id/overCardViewPager"
        android:layout_width="@dimen/x200"
        android:layout_height="@dimen/y200" />
```

java代码中设置控件的属性,根据需要设置部分属性，不用全部设置

```java
//配置OverCardViewPager参数
pager = findViewById(R.id.overCardViewPager);
pager.setImageScale(LoopViewPager.FIT_CENTER)//修改视图的填充类型
        .setLoop(true)//设置是否循环(图片数量大于3有效)
        .setOnLoopPageChangeListener(ListenerManager.onLoopPageChangeListener)//设置选中监听，替代addOnPageChangeListener方法
        .setOnLoopPagerClickListener(ListenerManager.onLoopPagerClickListener)//设置点击监听
        .setBeans(new DataManager().getUrlBeans())//设置数据源
        .setDefaultResouces(new int[]{R.drawable.img1})//设置默认显示的占位图
        .setCard(true)//设置是否是CardView
        .setCardRadius(getResources().getDimension(R.dimen.x1))//设置CardView的圆角弧度
        .setCardElevation(getResources().getDimension(R.dimen.x2))//设置CardView的阴影宽度
        .setCardPadding((int) getResources().getDimension(R.dimen.x2))//设置CardView的Padding宽度
        .setmOffset(getResources().getDimension(R.dimen.y5))//设置重叠卡片的下移距离
        .initialise();//参数配置完成后，执行适配(必须执行，且必须最后一步执行)
```
//数据源发生改变时
```java
loopViewPager.getAdapter().notifyDataSetChanged();
````

效果：

 ![image](https://github.com/Eric3023/PointViewPager/blob/master/app/screenshoot/GIF3.gif)
