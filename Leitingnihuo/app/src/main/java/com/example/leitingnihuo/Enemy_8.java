package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017-04-30.
 */
public class Enemy_8 implements MyInterface{
    private int width,height,x,y,model,blood,time=0,index=0,speed;
    private Bitmap bitmap;
    private Random random=new Random();
    private MyGameView myGameView;
    private boolean dirict1=true,dirict2=true,IsAdd=true;
    public Enemy_8(int x,int y,int model,int blood,MyGameView myGameView){
        if(model==3){
            bitmap= BitmapFactory.decodeResource(myGameView.getResources(), R.mipmap.ep_3);
        }else {
            bitmap= BitmapFactory.decodeResource(myGameView.getResources(),R.mipmap.ep_15);
        }
        this.x=x;
        this.y=y;
        this.model=model;
        this.blood=blood;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.myGameView=myGameView;
        this.index=15+random.nextInt(25);
        this.speed=2+random.nextInt(8);
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                time++;
                if(time>=40){
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,1,0,myGameView));
                    time=0;
                }
                if(dirict1){
                    x-=4;
                    if(x<=0){
                        dirict1=false;
                    }
                }else {
                    x+=4;
                    if(x>=myGameView.ScreenWidth-width){
                        dirict1=true;
                    }
                }
                break;
            case 2:
                time++;
                if(time>=40){
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,1,0,myGameView));
                    time=0;
                }
                if(dirict2){
                    x+=4;
                    if(x>=myGameView.ScreenWidth-width){
                        dirict2=false;
                    }
                }else {
                    x-=4;
                    if(x<=0){
                        dirict2=true;
                    }
                }
                break;
            case 3:
                y+=speed;
                if(IsAdd)
                    time++;
                if(time>=index){
                    int s=5+random.nextInt(7);
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,2,s,myGameView));
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,3,s,myGameView));
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,4,s,myGameView));
                    myGameView.my.add(new Bullet_8(x+width/2-myGameView.bulletW/2,y+height,5,s,myGameView));
                    IsAdd=false;
                    time=0;
                }
                break;
        }
        return bitmap;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        return null;
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
}
