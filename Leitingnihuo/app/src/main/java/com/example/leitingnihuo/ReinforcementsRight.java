package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




/**
 * Created by Administrator on 2017-04-06.
 */
public class ReinforcementsRight implements MyInterface{
    private MyGameView myGameView;
    private Bitmap bitmap;
    private MyPlane myPlane;
    private int x,y,width,height,time=0,resid;
    public ReinforcementsRight(int resid, MyGameView myGameView){
        this.resid=resid;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.myPlane=(MyPlane)myGameView.my.get(0);

    }
    @Override
    public Bitmap getBitmap() {
        this.x=myPlane.getX()+width+myPlane.getWidth();
        this.y=myPlane.getY()-myPlane.getHeight()/2;
        time++;
        if(time>=15){
            myGameView.bullets.add(new Bullet(R.mipmap.ying,0,0,3,myGameView));
            time=0;
        }
        return bitmap;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        String imageKey=String.valueOf(resId);
        Bitmap bitmap=(Bitmap)myGameView.lruCache.get(imageKey);
        if(bitmap!=null){
            return bitmap;
        }else {
            Bitmap bitmaptwo= BitmapFactory.decodeResource(myGameView.getResources(),resId);
            myGameView.lruCache.put(imageKey,bitmaptwo);
            return bitmaptwo;
        }
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
