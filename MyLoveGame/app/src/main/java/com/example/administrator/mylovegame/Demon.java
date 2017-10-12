package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class Demon implements GameImage {
    public GameView gameView;
    public Bitmap demonBitmap;
    public int x=0,y=0,width=0,height=0,speed=8;
    public List<Bitmap> demon=new ArrayList<Bitmap>();
    public int index=0,count=0;
    public Demon(Bitmap demonBitmap,int x,int y,GameView gameView){
        demon.add(Bitmap.createBitmap(demonBitmap, 0, 0, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        demon.add(Bitmap.createBitmap(demonBitmap, (demonBitmap.getWidth()/3)*1, 0, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        demon.add(Bitmap.createBitmap(demonBitmap, (demonBitmap.getWidth()/3)*2, 0, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        demon.add(Bitmap.createBitmap(demonBitmap, 0, demonBitmap.getHeight()/2, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        demon.add(Bitmap.createBitmap(demonBitmap, (demonBitmap.getWidth()/3)*1,demonBitmap.getHeight()/2, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        demon.add(Bitmap.createBitmap(demonBitmap, (demonBitmap.getWidth()/3)*2,demonBitmap.getHeight()/2, demonBitmap.getWidth()/3, demonBitmap.getHeight()/2));
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.demonBitmap=demonBitmap;
        this.width=demonBitmap.getWidth()/3;
        this.height=demonBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=demon.get(index);
        count++;
        if(count==3){
            index++;
            if(index==demon.size()){
                index=0;
            }
            count=0;
        }
        if(gameView.IsStart==true)
        x+=speed;
        if(x>=gameView.ScreenWidth){
            gameView.dragons.remove(this);
        }
        return bitmap;
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
