package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class MonsterBig implements GameImage {
    public GameView gameView;
    public Bitmap bigBitmap;
    public int x=0,y=0,width=0,height=0,speed;
    public List<Bitmap> monsterbig=new ArrayList<Bitmap>();
    public int index=0,count=0;
    public MonsterBig(Bitmap bigBitmap, int x, int y, GameView gameView){
        monsterbig.add(Bitmap.createBitmap(bigBitmap, 0, 0, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, (bigBitmap.getWidth()/4)*1, 0, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, (bigBitmap.getWidth()/4)*2, 0, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, (bigBitmap.getWidth()/4)*3, 0, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, 0,bigBitmap.getHeight()/2, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, (bigBitmap.getWidth()/4)*1,bigBitmap.getHeight()/2, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        monsterbig.add(Bitmap.createBitmap(bigBitmap, (bigBitmap.getWidth()/4)*2,bigBitmap.getHeight()/2, bigBitmap.getWidth()/4, bigBitmap.getHeight()/2));
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.bigBitmap=bigBitmap;
        this.width=bigBitmap.getWidth()/3;
        this.height=bigBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=monsterbig.get(index);
        count++;
        if(count==3){
            index++;
            if(index==monsterbig.size()){
                index=0;
            }
            count=0;
        }
        x-=4;
        if(x<=-width){
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
