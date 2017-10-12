package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;





import java.util.Random;


/**
 * Created by Administrator on 2017-04-30.
 */
public class Bullet_8 implements MyInterface {
    private int width,height,x,y,model,speed,tag=0;
    private Bitmap bitmap;
    private Random random=new Random();
    private MyGameView myGameView;
    public Bullet_8(int x,int y,int model,int speed,MyGameView myGameView){
        this.x=x;
        this.y=y;
        this.model=model;
        if(model==1){
            bitmap=BitmapFactory.decodeResource(myGameView.getResources(), R.mipmap.feibiao);
        }else {
            bitmap=BitmapFactory.decodeResource(myGameView.getResources(),R.mipmap.zidan_g);
        }
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.myGameView=myGameView;
        if(x>myGameView.ScreenWidth/2+3*width){
            tag=2;
        }else if(x<myGameView.ScreenWidth/2-3*width){
            tag=1;
        }else {
            tag=0;
            switch (model){
                case 2:
                    this.x=x-width*7/2;
                    break;
                case 3:
                    this.x=x-width*3/2;
                    break;
                case 4:
                    this.x=x+width*3/2;
                    break;
                case 5:
                    this.x=x+width*7/2;
                    break;
            }
        }
        this.speed=speed;
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y+=5;
                if(x<=-width||x>=myGameView.ScreenWidth||y>=myGameView.ScreenHeight||y<=-height){
                    myGameView.my.remove(this);
                }
                break;
            case 2:
                switch (tag){
                    case 1:
                        x+=1;
                        break;
                    case 2:
                        x-=1;
                        break;
                }
                y+=speed;
                if(x<=-width||x>=myGameView.ScreenWidth||y>=myGameView.ScreenHeight||y<=-height){
                    myGameView.my.remove(this);
                }
                break;
            case 3:
                switch (tag){
                    case 1:
                        x+=2;
                        break;
                    case 2:
                        x-=2;
                        break;
                }
                y+=speed;
                if(x<=-width||x>=myGameView.ScreenWidth||y>=myGameView.ScreenHeight||y<=-height){
                    myGameView.my.remove(this);
                }
                break;
            case 4:
                switch (tag){
                    case 1:
                        x+=3;
                        break;
                    case 2:
                        x-=3;
                        break;
                }
                y+=speed;
                if(x<=-width||x>=myGameView.ScreenWidth||y>=myGameView.ScreenHeight||y<=-height){
                    myGameView.my.remove(this);
                }
                break;
            case 5:
                switch (tag){
                    case 1:
                        x+=4;
                        break;
                    case 2:
                        x-=4;
                        break;
                }
                y+=speed;
                if(x<=-width||x>=myGameView.ScreenWidth||y>=myGameView.ScreenHeight||y<=-height){
                    myGameView.my.remove(this);
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
