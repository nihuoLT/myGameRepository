package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-05.
 */
public class Enemy_7 implements MyInterface{
    private int x,y,width,height,alpha=0,bulletTime,model=1,blood=100;
    private MyGameView myGameView;
    private Paint paint=new Paint();
    private Bitmap bitmap,cache;
    private boolean modelflag=true;
    public Enemy_7(int resid,int x,int y,MyGameView myGameView){
        this.x=x;
        this.y=y;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
    }
    @Override
    public Bitmap getBitmap(){
        if(alpha<=255){
            alpha+=10;
        }else {
            bulletTime++;
            if(bulletTime>=26){
                myGameView.bullets.add(new BossBBullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,model,myGameView));
                if(modelflag){
                    model++;
                    if(model>=13){
                        modelflag=false;
                    }
                }else {
                    model--;
                    if(model<=1){
                        modelflag=true;
                    }
                }

                bulletTime=0;
            }
        }
        Canvas canvas=new Canvas(cache);
        paint.setAlpha(alpha);
        canvas.drawBitmap(bitmap,0,0,paint);
       return cache;
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
                // if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                    myGameView.bullets.remove(zidan);
                    blood--;
                    if(blood<=0){
                        myGameView.my.remove(this);
                        myGameView.myScore+=8;
                        myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                        myGameView.my.add(new AddScore(R.mipmap.eight,x,y,myGameView));
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
