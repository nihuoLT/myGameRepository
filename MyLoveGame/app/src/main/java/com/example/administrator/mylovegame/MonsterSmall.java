package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class MonsterSmall implements GameImage {
    public GameView gameView;
    public Bitmap smallBitmap;
    public int x=0,y=0,width=0,height=0,model;
    public List<Bitmap> monstersmall=new ArrayList<Bitmap>();
    public int index=0,count=0;
    public MonsterSmall(Bitmap smallBitmap, int x, int y, GameView gameView,int model){
        switch (model){
            case 1:
                monstersmall.add(Bitmap.createBitmap(smallBitmap, 0, 0, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                monstersmall.add(Bitmap.createBitmap(smallBitmap, smallBitmap.getWidth()/2, 0, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                monstersmall.add(Bitmap.createBitmap(smallBitmap,0,smallBitmap.getHeight()/2, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                break;
            case 2:
                monstersmall.add(Bitmap.createBitmap(smallBitmap, smallBitmap.getWidth()/2, 0, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                monstersmall.add(Bitmap.createBitmap(smallBitmap, 0, 0, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                monstersmall.add(Bitmap.createBitmap(smallBitmap ,smallBitmap.getWidth()/2,smallBitmap.getHeight()/2, smallBitmap.getWidth()/2, smallBitmap.getHeight()/2));
                break;
        }
        this.model=model;
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.smallBitmap=smallBitmap;
        this.width=smallBitmap.getWidth()/2;
        this.height=smallBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=monstersmall.get(index);
        count++;
        if(count==3){
            index++;
            if(index==monstersmall.size()){
                index=0;
            }
            count=0;
        }
        if(model==1){
            x-=8;
            if(x<=-width){
                gameView.dragons.remove(this);
            }
        }else {
            x+=8;
            if(x>=gameView.ScreenWidth){
                gameView.dragons.remove(this);
            }
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
