package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-25.
 */
public class Enemy_2 implements MyInterface {
    private Bitmap bitmap=null,cache=null;
    private Paint paint=new Paint();
    private MyGameView myGameView=null;
    private int x,y,width,height,blood,model,limitY,addBulletTime=0,PaintTime=0,resid;
    private boolean PaintFlag;
    public Enemy_2(int resid,int x ,int y,int model,int limitY,MyGameView myGameView){
        this.resid=resid;
        this.x=x;
        this.y=y;
        this.model=model;
        this.limitY=limitY;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.blood=6;
        this.cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
    }
    @Override
    public Bitmap getBitmap() {
        if(PaintFlag==true){
            PaintTime++;
            if(PaintTime>=5) {
                paint.reset();
                PaintFlag = false;
                PaintTime = 0;
            }
        }
        if(y<=limitY){
            y+=3;
        }else{
            addBulletTime++;
            if(addBulletTime>=20){
                myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_3,x+width/2-myGameView.epb_2.getWidth()/2,y+height,3,myGameView));
                myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_3,x+width/2-myGameView.epb_2.getWidth()/2,y+height,4,myGameView));
                myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_3,x+width/2-3*myGameView.epb_2.getWidth()/2,y+height,5,myGameView));
                myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_3,x+width/2-myGameView.epb_2.getWidth()/2,y+height,6,myGameView));
                myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_3,x+width/2-3*myGameView.epb_2.getWidth()/2,y+height,7,myGameView));
                addBulletTime=0;
            }
        }
        Canvas canvas=new Canvas(cache);
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
                // if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                    myGameView.bullets.remove(zidan);
                    PaintFlag=true;
                    ColorMatrix cm=new ColorMatrix();
                    cm.set(new float[] { 100 / 128f, 0, 0, 0, 0,// 红色值
                            0, 64 / 128f, 0, 0, 0,// 绿色值
                            0, 0, 100 / 128f, 0, 0,// 蓝色值
                            0, 0, 0, 1, 0 // 透明度
                    });
                    paint.setColorFilter(new ColorMatrixColorFilter(cm));
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
}
