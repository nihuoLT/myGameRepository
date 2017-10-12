package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-26.
 */
public class Enemy_4 implements MyInterface {
    private Bitmap bitmap=null,cathe=null;
    private int x,y,width,height,blood,newY,PaintTime=0,speed;
    private MyGameView myGameView=null;
    private Paint paint=new Paint();
    private boolean PaintFlag,IsCan=false;
    public Enemy_4(Bitmap bitmap,int x,int y,int blood,int speed,MyGameView myGameView){
        this.x=x;
        this.y=y;
        this.blood=blood;
        this.speed=speed;
        this.myGameView=myGameView;
        this.bitmap=bitmap;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.newY=-height;
        this.cathe=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
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
        if(newY<0){
            newY+=3;
            if(newY>=0)
                IsCan=true;
        }else {
            y+=speed;
        }
        Canvas canvas=new Canvas(cathe);
       // canvas.save();
       // canvas.clipRect(0,0,width,newY);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap,0,newY ,paint);
        //canvas.restore();
        return cathe;
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
                if(IsCan==true){
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
                            myGameView.myScore+=10;
                            myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                            myGameView.my.add(new AddScore(R.mipmap.ten,x,y,myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                        }
                    }
                }
            }
        }

    }
}
