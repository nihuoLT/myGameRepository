package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




/**
 * Created by Administrator on 2017-02-22.
 */
public class EnemyBullet_1 implements MyInterface {
    private MyGameView myGameView=null;
    private Bitmap bitmap=null,cache=null;
    private int x,y,width,height,speed=5;
    public EnemyBullet_1(Bitmap bitmap,int x,int y,MyGameView myGameView){
        this.myGameView=myGameView;
        this.bitmap=bitmap;
        this.x=x;
        this.y=y;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        y+=speed;
        if(y>=myGameView.ScreenHeight){
            myGameView.bullets.remove(this);
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
