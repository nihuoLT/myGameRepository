package com.example.leitingnihuo;

import android.graphics.Bitmap;



/**
 * Created by Administrator on 2017-02-26.
 */
public class EnemyBullet_3 implements MyInterface {
    private Bitmap bitmap=null;
    private MyGameView myGameView;
    private int x,y,width,height,model;
    private boolean diriction_1=true,diriction_2=true;
    public EnemyBullet_3(Bitmap bitmap,int x,int y,int model,MyGameView myGameView){
        this.bitmap=bitmap;
        this.x=x;
        this.y=y;
        this.model=model;
        this.myGameView=myGameView;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y+=4;
                if(diriction_1==true){
                    x+=3;
                    if(x>=myGameView.ScreenWidth-width){
                        diriction_1=false;
                    }
                }else {
                    x-=3;
                    if(x<=0){
                        diriction_1=true;
                    }
                }
                if(y>=myGameView.ScreenHeight){
                    myGameView.bullets.remove(this);
                }
                break;
            case 2:
                y+=4;
                if(diriction_2==true){
                    x-=3;
                    if(x<=0){
                        diriction_2=false;
                    }
                }else {
                    x+=3;
                    if(x>=myGameView.ScreenWidth-width){
                        diriction_1=true;
                    }
                }
                if(x<=0){
                    x+=3;
                }
                if(y>=myGameView.ScreenHeight){
                    myGameView.bullets.remove(this);
                }
                break;
            case 3:
                y+=5;
                if(y>=myGameView.ScreenHeight){
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
