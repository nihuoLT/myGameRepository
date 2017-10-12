package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Blood implements GameImage{
    public Bitmap blood=null;
    public int width=0;
    public int height=0;
    public int x=0;
    public int y=0;
    public Blood(Bitmap blood,int x,int y){
        this.blood=blood;
        this.x=x;
        this.y=y;
        this.width=blood.getWidth();
        this.height=blood.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        return blood;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
