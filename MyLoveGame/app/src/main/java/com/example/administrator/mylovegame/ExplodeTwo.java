package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class ExplodeTwo implements GameImage{
    public GameView gameView;
    public Bitmap explodeBitmap;
    public int x=0,y=0,width=0,height=0;
    public List<Bitmap> explode=new ArrayList<Bitmap>();
    public int index=0,count=0;
    public ExplodeTwo(Bitmap explodeBitmap, int x, int y, GameView gameView ){
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.explodeBitmap=explodeBitmap;
        this.width=explodeBitmap.getWidth()/3;
        this.height=explodeBitmap.getHeight()/3;
        explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*2, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        explode.add(Bitmap.createBitmap(explodeBitmap, 0, explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*2,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/2));
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.explodeBitmap=explodeBitmap;
        this.width=explodeBitmap.getWidth()/3;
        this.height=explodeBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=explode.get(index);
        count++;
        if(count==3){
            index++;
            if(index==explode.size()){
                gameView.explodes.remove(this);
            }
            count=0;
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
