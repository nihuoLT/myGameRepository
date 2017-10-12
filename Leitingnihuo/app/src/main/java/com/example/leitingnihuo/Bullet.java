package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



import java.util.List;


/**
 * Created by Administrator on 2017-02-15.
 */
public class Bullet implements MyInterface {
    private MyGameView myGameView=null;
    private Bitmap bitmap=null;
    private MyPlane myPlane;
    private int x,y,width,height,resid,model;
    public int speed=20;
    public Bullet(int resid,int x,int y,int model,MyGameView myGameView){
        this.resid=resid;
        this.model=model;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        myPlane=(MyPlane)myGameView.my.get(0);
        switch (model){
            case 1:
                this.x=myPlane.getX()+myPlane.getWidth()/2-bitmap.getWidth()/2;
                this.y=myPlane.getY()-bitmap.getHeight();
                break;
            case 2:
                for(MyInterface myInterface: (List<MyInterface>)myGameView.my.clone()){
                    if(myInterface instanceof ReinforcementsLeft){
                        ReinforcementsLeft enemy=(ReinforcementsLeft)myInterface;
                        this.x=enemy.getX()+enemy.getWidth()/2-width/2;
                        this.y=enemy.getY();
                    }
                }
                break;
            case 3:
                for(MyInterface myInterface: (List<MyInterface>)myGameView.my.clone()){
                    if(myInterface instanceof ReinforcementsRight){
                        ReinforcementsRight enemy=(ReinforcementsRight)myInterface;
                        this.x=enemy.getX()+enemy.getWidth()/2-width/2;
                        this.y=enemy.getY();
                    }
                }
                break;
            case 4:
                for(MyInterface myInterface: (List<MyInterface>)myGameView.ybs.clone()){
                    if(myInterface instanceof Ybone){
                        Ybone enemy=(Ybone)myInterface;
                        this.x=enemy.getX()+enemy.getWidth()/2-width/2;
                        this.y=enemy.getY()-height;
                    }
                }
                break;
            case 5:
                for(MyInterface myInterface: (List<MyInterface>)myGameView.ybs.clone()){
                    if(myInterface instanceof Ybtwo){
                        Ybtwo enemy=(Ybtwo) myInterface;
                        this.x=enemy.getX()+enemy.getWidth()/2-width/2;
                        this.y=enemy.getY()-height;
                    }
                }
                break;
            case 6:
                this.x=x;
                this.y=y;
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        y-=speed;
        if(y<=-bitmap.getHeight()){
            myGameView.bullets.remove(this);
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
