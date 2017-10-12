package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-06.
 */
public class Ybtwo implements MyInterface{
    private MyGameView myGameView;
    private Bitmap bitmap;
    private MyPlane myPlane;
    private int x,y,width,height,time=0,resid,blood=10;
    public Ybtwo(int resid, MyGameView myGameView){
        this.resid=resid;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.myPlane=(MyPlane)myGameView.my.get(0);

    }
    @Override
    public Bitmap getBitmap() {
        this.x=myPlane.getX()+myPlane.getWidth();
        this.y=myPlane.getY()+myPlane.getHeight();
        time++;
        if(time>=10){
           // myGameView.pool.play(myGameView.musicId.get(2),1,1,0,0,1);
            myGameView.bullets.add(new Bullet(R.mipmap.newbullet_2,0,0,5,myGameView));
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
        if( x1+w1-15>x2&&y1+h1>y2+10&&x1+15<x2+w2&&y1<y2+h2-10){
            return true;
        }
        return false;
    }
    public void IsBeAttacked(ArrayList<MyInterface> bitmaps){
        for(MyInterface zidan:(ArrayList<MyInterface>)bitmaps.clone()) {
            if((zidan instanceof Enemy)||(zidan instanceof Enemy_2)||(zidan instanceof Enemy_3)||(zidan instanceof Enemy_4)||(zidan instanceof Enemy_5)||(zidan instanceof EnemyBullet_1)||(zidan instanceof EnemyBullet_2)
                    ||(zidan instanceof EnemyBullet_3)||(zidan instanceof EnemyTest)||(zidan instanceof BossABullet)||(zidan instanceof BossBBullet)||(zidan instanceof Enemy_6)||(zidan instanceof Enemy_7)) {
                if(isCollisionWithRect(x,y,width,height,zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight())==true){
                    bitmaps.remove(zidan);
                    blood-=1;
                    if(blood<=0){
                        myGameView.YbRIsexist=false;
                        myGameView.ybs.remove(this);
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
