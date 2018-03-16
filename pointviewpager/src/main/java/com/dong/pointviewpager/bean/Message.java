package com.dong.pointviewpager.bean;

/**
 * Created by Dong on 2018/3/16.
 */

public class Message{
    private int position;
    private float positionOffset;

    public Message() {
    }

    public Message(int position, float positionOffset) {
        this.position = position;
        this.positionOffset = positionOffset;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public float getPositionOffset() {
        return positionOffset;
    }

    public void setPositionOffset(float positionOffset) {
        this.positionOffset = positionOffset;
    }
}