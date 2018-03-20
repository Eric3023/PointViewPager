package com.dong.pointandviewpager.sample.model;

import com.dong.pointandviewpager.R;
import com.dong.pointviewpager.bean.LoopViewPagerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2018/3/20.
 */

public class DataManager {

    private List<LoopViewPagerBean> urlBeans;
    private List<LoopViewPagerBean> resourceBeans;

    public DataManager() {
        init();
    }

    private void init() {
        initUrlBeans();
        initResourceBeans();
    }

    public List<LoopViewPagerBean> getUrlBeans() {
        return urlBeans;
    }

    public List<LoopViewPagerBean> getResourceBeans() {
        return resourceBeans;
    }

    private void initUrlBeans() {
        urlBeans = new ArrayList<LoopViewPagerBean>();
        LoopViewPagerBean urlBean0 = new LoopViewPagerBean(UrlConfige.URL0, null);
        LoopViewPagerBean urlBean1 = new LoopViewPagerBean(UrlConfige.URL1, null);
        LoopViewPagerBean urlBean2 = new LoopViewPagerBean(UrlConfige.URL2, null);
        LoopViewPagerBean urlBean3 = new LoopViewPagerBean(UrlConfige.URL3, null);
        LoopViewPagerBean urlBean4 = new LoopViewPagerBean(UrlConfige.URL4, null);

        urlBeans.add(urlBean0);
        urlBeans.add(urlBean1);
        urlBeans.add(urlBean2);
        urlBeans.add(urlBean3);
        urlBeans.add(urlBean4);
    }

    private void initResourceBeans() {
        resourceBeans = new ArrayList<LoopViewPagerBean>();
        LoopViewPagerBean resourceBean0 = new LoopViewPagerBean(ResourceConfige.resourceID0, null);
        LoopViewPagerBean resourceBean1 = new LoopViewPagerBean(ResourceConfige.resourceID1, null);
        LoopViewPagerBean resourceBean2 = new LoopViewPagerBean(ResourceConfige.resourceID2, null);
        LoopViewPagerBean resourceBean3 = new LoopViewPagerBean(ResourceConfige.resourceID3, null);
        LoopViewPagerBean resourceBean4 = new LoopViewPagerBean(ResourceConfige.resourceID4, null);
        LoopViewPagerBean resourceBean5 = new LoopViewPagerBean(ResourceConfige.resourceID5, null);
        resourceBeans.add(resourceBean0);
        resourceBeans.add(resourceBean1);
        resourceBeans.add(resourceBean2);
        resourceBeans.add(resourceBean3);
        resourceBeans.add(resourceBean4);
        resourceBeans.add(resourceBean5);
    }
}
