package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-15.
 */
public class MyPlane implements MyInterface{
    private Bitmap myplane=null;
    private MyGameView myGameView;
    private int x=0,y=0,width=0,height=0,resid;
    private boolean flag=false;
    public MyPlane(int resid,MyGameView myGameView){
        this.resid=resid;
        this.myGameView=myGameView;
        this.myplane=addBitmapToCache(resid);
        this.x=myGameView.ScreenWidth/2-myplane.getWidth()/2;
        this.y=myGameView.ScreenHeight-myplane.getHeight()-150;

        this.width=myplane.getWidth();
        this.height=myplane.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        return myplane;
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
        if( x1+w1-15>x2&&y1+h1>y2+10&&x1+15<x2+w2&&y1<y2+h2-10){
            return true;
        }
        return false;
    }
    public void IsBeAttacked(ArrayList<MyInterface> bitmaps){
        for(MyInterface zidan:(ArrayList<MyInterface>)bitmaps.clone()) {
            if((zidan instanceof Enemy)||(zidan instanceof Enemy_2)||(zidan instanceof Enemy_3)||(zidan instanceof Enemy_4)||(zidan instanceof Enemy_5)||(zidan instanceof EnemyBullet_1)||(zidan instanceof EnemyBullet_2)
                    ||(zidan instanceof EnemyBullet_3)||(zidan instanceof EnemyTest)||(zidan instanceof BossABullet)||(zidan instanceof BossBBullet)||(zidan instanceof Enemy_6)||(zidan instanceof Enemy_7)||(zidan instanceof Enemy_8)
                    ||(zidan instanceof Bullet_8)) {
                if(isCollisionWithRect(x,y,width,height,zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight())==true){
                    bitmaps.remove(zidan);
                    myGameView.myplaneblood-=10;
                    if(myGameView.myplaneblood<=0){
                        //myGameView.my.remove(this);
                        myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                    }
                }
            }
        }

    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
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
