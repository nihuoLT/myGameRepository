package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-26.
 */
public class Enemy_3 implements MyInterface {
    private Bitmap bitmap=null,cache=null;
    private MyGameView myGameView=null;
    private Paint paint=new Paint();
    private int x,y,width,height,blood,resid;
    private boolean PaintFlag;
    public Enemy_3(int resid,int x,int y,MyGameView myGameView){
        this.resid=resid;
        this.x=x;
        this.y=y;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.blood=10;
        //this.cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
    }
    @Override
    public Bitmap getBitmap() {
        y+=2;
        if(y>myGameView.ScreenHeight){
            myGameView.my.remove(this);
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
    public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
                                       int x2,int y2, int w2, int h2) {
        if( x1+w1>x2&&y1+h1>y2&&x1<x2+w2&&y1<y2+h2){
            return true;
        }
        return false;
    }
    public void IsBeAttacked(ArrayList<MyInterface> bitmaps){
        for(MyInterface zidan:(ArrayList<MyInterface>)bitmaps.clone()) {
            if(zidan instanceof Bullet){
                 if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                //if(isCollisionWithRect(x,y,width,height,zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight())==true){
                    myGameView.bullets.remove(zidan);
                    blood--;
                    if(blood<=0){
                        myGameView.my.remove(this);
                        myGameView.myScore+=6;
                        myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                        myGameView.my.add(new AddScore(R.mipmap.six,x,y,myGameView));
                        myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                    }
                }
            }
        }

    }
}
