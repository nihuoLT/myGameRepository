package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017-04-01.
 */
public class Enemy_6 implements MyInterface{
    private int resid,x,y,width,height,time=0,xtag,ytag,blood;
    private MyGameView myGameView;
    private boolean xflag=true,yflag=true;
    private Random random=new Random();
    private Bitmap bitmap;
    public Enemy_6(int resid ,int x,int y,int blood,MyGameView myGameView){
        this.resid=resid;
        this.blood=blood;
        this.x=x;
        this.y=y;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        time++;
        if(xflag==true) {
            x -= 3;
            if(x<=0){
                xflag=false;
            }
        }else {
            x += 3;
            if(x>=myGameView.ScreenWidth-width){
                xflag=true;
            }
        }
        if(yflag==true){
            y -= 5;
            if(y<=0)
                yflag=false;
        }else {
            y += 5;
            if(y>=myGameView.ScreenHeight-height)
                yflag=true;
        }
        if(time>=100+random.nextInt(200)){
            xtag=random.nextInt(100);
            ytag=random.nextInt(100);
            if(xtag<=50){
                xflag=false;
            }else {
                xflag=true;
            }
            if(ytag<=65){
                yflag=false;
            }else{
                yflag=true;
            }
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
                    myGameView.bullets.remove(zidan);
                    blood--;
                    if(blood<=0){
                        myGameView.my.remove(this);
                        myGameView.myScore+=7;
                        myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                        myGameView.my.add(new AddScore(R.mipmap.seven,x,y,myGameView));
                        myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                    }
                }
            }
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
