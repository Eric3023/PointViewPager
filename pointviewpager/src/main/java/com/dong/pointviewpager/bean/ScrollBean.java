package com.dong.pointviewpager.bean;

/**
 * Created by Dong on 2018/3/19.
 */

public class ScrollBean {

    private int scroll_state = -1;//滑动状态

    private int scroll_positon = -1;//滑动位置

    private float scroll_percent = -1;//滑动比例

    private int select_position = -1;//选中位置

    public int getScroll_state() {
        return scroll_state;
    }

    public void setScroll_state(int scroll_state) {
        this.scroll_state = scroll_state;
    }

    public int getScroll_positon() {
        return scroll_positon;
    }

    public void setScroll_positon(int scroll_positon) {
        this.scroll_positon = scroll_positon;
    }

    public float getScroll_percent() {
        return scroll_percent;
    }

    public void setScroll_percent(float scroll_percent) {
        this.scroll_percent = scroll_percent;
    }

    public int getSelect_position() {
        return select_position;
    }

    public void setSelect_position(int select_position) {
        this.select_position = select_position;
    }
}
