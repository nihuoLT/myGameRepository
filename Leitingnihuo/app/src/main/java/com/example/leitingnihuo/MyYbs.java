package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



/**
 * Created by Administrator on 2017-04-12.
 */
public class MyYbs implements MyInterface{
    private Bitmap bitmap=null;
    private MyGameView myGameView=null;
    private int x,y,width,height,model=1,time=0,modely;
    public MyYbs(int x,int y,int modely,MyGameView myGameView){
        this.bitmap= BitmapFactory.decodeResource(myGameView.getResources(), R.mipmap.my_2);
        this.x=x;
        this.y=y;
        this.modely=modely;
        this.myGameView=myGameView;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y-=10;
                switch (modely){
                    case 1:
                        if(y<=7*myGameView.ScreenHeight/8){
                            model=2;
                        }
                        break;
                    case 2:
                        if(y<=7*myGameView.ScreenHeight/8+myGameView.my_2.getHeight()){
                            model=2;
                        }
                        break;
                }
                break;
            case 2:
                time++;
                if(time>=10){
                    myGameView.bullets.add(new Bullet(R.mipmap.ybzidan,x,y-myGameView.ybzidan.getHeight(),6,myGameView));
                    myGameView.bullets.add(new Bullet(R.mipmap.ybzidan,x+width-myGameView.ybzidan.getWidth()
                            ,y-myGameView.ybzidan.getHeight(),6,myGameView));
                    time=0;
                }
                y-=3;
                if(y<=-width){
                    myGameView.ybs.remove(this);
                    myGameView.YbBIsexist=false;
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
