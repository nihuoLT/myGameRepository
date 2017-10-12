package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-22.
 */
public class EnemyTest implements MyInterface {
    private MyGameView myGameView=null;
    private Bitmap bitmap=null,cache=null;
    private int x,y,width,height,model,time=0,blood=0,PaintTime=0;
    private Paint paint=new Paint();
    private Canvas canvas;
    private float scale=0.1f;
    private boolean IsAddBillet=false,PaintFlag,IsCan=false;
    public EnemyTest(Bitmap bitmap,MyGameView myGameView,int model){
        this.myGameView=myGameView;
        this.bitmap=bitmap;
        this.model=model;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.blood=3;
        this.cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        this.y=myGameView.ScreenHeight/6;
        switch (model){
            case 1:
                this.x=myGameView.ScreenWidth/8;
                break;
            case 2:
                this.x=7*myGameView.ScreenWidth/8-width;
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        if(scale<1){
            scale+=0.01f;
            if(scale>=1){
                IsAddBillet=true;
                IsCan=true;
            }
        }
        if(PaintFlag==true){
            PaintTime++;
            if(PaintTime>=5) {
                paint.reset();
                PaintFlag = false;
                PaintTime = 0;
            }
        }
        if(IsAddBillet==true){
            time++;
            if(time>=30){
                myGameView.bullets.add(new EnemyBullet_1(myGameView.epb_1,x+width/2-myGameView.epb_1.getWidth()/2,y+height,myGameView));
                time=0;
            }
        }
        canvas=new Canvas(cache);
        canvas.scale(scale,scale);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap,0,0,paint);
        return cache;
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
                    if(IsCan==true){
                        myGameView.bullets.remove(zidan);
                        PaintFlag=true;
                        ColorMatrix cm=new ColorMatrix();
                        cm.set(new float[] { 160 / 128f, 0, 0, 0, 0,// 红色值
                                0, 32 / 128f, 0, 0, 0,// 绿色值
                                0, 0, 240 / 128f, 0, 0,// 蓝色值
                                0, 0, 0, 1, 0 // 透明度
                        });
                        paint.setColorFilter(new ColorMatrixColorFilter(cm));
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
}
