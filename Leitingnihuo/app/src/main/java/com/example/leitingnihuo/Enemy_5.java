package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;




import java.util.ArrayList;

/**
 * Created by Administrator on 2017-02-26.
 */
public class Enemy_5 implements MyInterface {
    private MyGameView myGameView=null;
    private Paint paint=new Paint();
    private Bitmap bitmap=null,cache=null;
    private int x,y,width,height,blood,addBulletTime=0,startX=0,startY=0,endX=0,endY=0,PaintTime;
    private boolean PaintFlag=false,IsCan=false;
    public Enemy_5(Bitmap bitmap,int x,int y,int blood,MyGameView myGameView){
        this.bitmap=bitmap;
        this.x=x;
        this.y=y;
        this.blood=blood;
        this.myGameView=myGameView;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        this.startX=width/2;
        this.startY=height/2;
        this.endX=startX;
        this.endY=startY;
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
        if(startX>0){
            startX--;
            endX++;
        }else {
            IsCan=true;
        }
        if(startY>0){
            startY--;
            endY++;
        }else {
            addBulletTime++;
            if(addBulletTime>=50){
                myGameView.bullets.add(new EnemyBullet_3(myGameView.epb_4,x+width/2-myGameView.epb_4.getWidth()/2,y+height,1,myGameView));
                myGameView.bullets.add(new EnemyBullet_3(myGameView.epb_4,x+width/2-myGameView.epb_4.getWidth()/2,y+height,2,myGameView));
                myGameView.bullets.add(new EnemyBullet_3(myGameView.epb_4,x+width/2-myGameView.epb_4.getWidth()/2,y+height,3,myGameView));
                addBulletTime=0;
            }
        }
        Canvas canvas=new Canvas(cache);
        canvas.save();
        canvas.clipRect(startX,startY,endX,endY);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();
        return cache;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        return null;
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
                            myGameView.myScore+=9;
                            myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                            myGameView.my.add(new AddScore(R.mipmap.nine,x,y,myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                        }
                    }
                }
            }
        }

    }
    public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
                                       int x2,int y2, int w2, int h2) {
        if( x1+w1>x2&&y1+h1>y2&&x1<x2+w2&&y1<y2+h2){
            return true;
        }
        return false;
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
