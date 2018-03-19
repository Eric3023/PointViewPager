package com.dong.pointviewpager.bean;

/**
 * Created by Dong on 2018/3/19.
 */

public class LoopViewPagerBean {

    private String url;
    private int resourceID;
    private Object object;

    public LoopViewPagerBean() {
    }

    public LoopViewPagerBean(String url, int resourceID) {
        this.url = url;
        this.resourceID = resourceID;
    }

    public LoopViewPagerBean(int resourceID, Object object) {
        this.resourceID = resourceID;
        this.object = object;
    }

    public LoopViewPagerBean(String url, Object object) {
        this.url = url;
        this.object = object;
    }

    public LoopViewPagerBean(String url, int resourceID, Object object) {
        this.url = url;
        this.resourceID = resourceID;
        this.object = object;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
