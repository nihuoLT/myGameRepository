package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.Matrix;



/**
 * Created by Administrator on 2017-02-25.
 */
public class EnemyBullet_2 implements MyInterface {
    private Bitmap bitmap=null;
    private MyGameView myGameView=null;
    private int x=0,y=0,model=0,witdh=0,height=0;
    public EnemyBullet_2(Bitmap bitmap,int x,int y,int model,MyGameView myGameView){
        this.bitmap=bitmap;
        this.x=x;
        this.y=y;
        this.model=model;
        this.myGameView=myGameView;
        this.witdh=bitmap.getWidth();
        this.height=bitmap.getHeight();
        Matrix matrix = new Matrix();
        switch(model){
            case 4:
                matrix.postRotate(-30);
                this.bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
                matrix.reset();
                break;
            case 5:
                matrix.postRotate(30);
                this.bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
                matrix.reset();
                break;
            case 6:
                matrix.postRotate(-20);
                this.bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
                matrix.reset();
                break;
            case 7:
                matrix.postRotate(20);
                this.bitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
                matrix.reset();
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y+=4;
                x+=3;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 2:
                y+=4;
                x-=3;
                if(y>=myGameView.ScreenHeight||x<=-bitmap.getWidth()){
                    myGameView.bullets.remove(this);
                }
                break;
            case 3:
                y+=8;
                if(y>=myGameView.ScreenHeight){
                    myGameView.bullets.remove(this);
                }
                break;
            case 4:
                y+=8;
                x+=3;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 5:
                y+=8;
                x-=3;
                if(y>=myGameView.ScreenHeight||x<=-bitmap.getWidth()){
                    myGameView.bullets.remove(this);
                }
                break;
            case 6:
                y+=8;
                x+=2;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 7:
                y+=8;
                x-=2;
                if(y>=myGameView.ScreenHeight||x<=-bitmap.getWidth()){
                    myGameView.bullets.remove(this);
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
        return witdh;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
