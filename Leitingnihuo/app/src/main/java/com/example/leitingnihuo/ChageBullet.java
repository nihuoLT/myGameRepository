package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017-04-01.
 */
public class ChageBullet implements MyInterface{
    private int resid,x,y,width,height,time,xtag,ytag;
    private boolean xflag=true,yflag=true;
    private Bitmap bitmap;
    private MyGameView myGameView;
    private Random random=new Random();
    public  ChageBullet(int resid ,int x,int y,MyGameView myGameView){
        this.resid=resid;
        this.x=x;
        this.y=y;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        //this.bitmap=BitmapFactory.decodeResource(myGameView.getResources(),R.mipmap.ic_launcher);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        if(x>myGameView.ScreenWidth/2){
            xflag=true;
        }else {
            xflag=false;
        }
        if(y>myGameView.ScreenHeight/2){
            xflag=true;
        }else {
            xflag=false;
        }
    }
    @Override
    public Bitmap getBitmap(){
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
            if(y>=myGameView.ScreenHeight)
                myGameView.bullets.remove(this);
        }
        if(time>=100+random.nextInt(200)){
            xtag=random.nextInt(100);
            ytag=random.nextInt(100);
            if(xtag<=50){
                xflag=false;
            }else {
                xflag=true;
            }
            if(ytag<=33){
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
            if(zidan instanceof MyPlane){
                if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                    myGameView.bullets.remove(this);
                    switch (myGameView.bulletIndex){
                        case 0:
                            myGameView.bulletId= R.mipmap.myb_2;
                            myGameView.bulletIndex=1;
                            break;
                        case 1:
                            myGameView.bulletId=R.mipmap.myb_3;
                            myGameView.bulletIndex=2;
                            break;
                        case 2:
                            myGameView.Isying=true;
                            myGameView.bulletIndex=3;
                            break;
                        case 3:
                            myGameView.my.add(new ReinforcementsLeft(R.mipmap.reinforcements,myGameView));
                            myGameView.my.add(new ReinforcementsRight(R.mipmap.reinforcements,myGameView));
                            myGameView.bulletIndex=4;
                            break;
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
