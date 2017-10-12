package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



/**
 * Created by Administrator on 2017-04-01.
 */
public class BossBBullet implements MyInterface {
    private int resid,x,y,width,height,model;
    private MyGameView myGameView;
    private Bitmap bitmap;
    public BossBBullet(int resid, int x, int y, int model, MyGameView myGameView){
        this.resid=resid;
        this.x=x;
        this.y=y;
        this.model=model;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y+=6;
                x+=6;
                if(y>=myGameView.ScreenHeight){
                    myGameView.bullets.remove(this);
                }
                break;
            case 2:
                y+=6;
                x+=5;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 3:
                y+=6;
                x+=4;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 4:
                y+=6;
                x+=3;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 5:
                y+=6;
                x+=2;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 6:
                y+=6;
                x+=1;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 7:
                y+=6;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 8:
                y+=6;
                x-=1;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 9:
                y+=6;
                x-=2;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 10:
                y+=6;
                x-=3;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 11:
                y+=6;
                x-=4;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 12:
                y+=6;
                x-=5;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
            case 13:
                y+=6;
                x-=6;
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.bullets.remove(this);
                }
                break;
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
