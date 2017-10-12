package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class Dragon implements GameImage {
    public GameView gameView;
    public Bitmap dragonBitmap;
    public int x=0,y=0,width=0,height=0,speed;
    public List<Bitmap> dragon=new ArrayList<Bitmap>();
    public List<Bitmap> maindragon=new ArrayList<Bitmap>();
    public int index=0,count=0,i=0,blood=3;
    public boolean Ischage=false;
    public Dragon(Bitmap dragonBitmap,int x,int y,GameView gameView){
        dragon.add(Bitmap.createBitmap(dragonBitmap, 0, 0, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        dragon.add(Bitmap.createBitmap(dragonBitmap, (dragonBitmap.getWidth()/3)*1, 0, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        dragon.add(Bitmap.createBitmap(dragonBitmap, (dragonBitmap.getWidth()/3)*2, 0, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        dragon.add(Bitmap.createBitmap(dragonBitmap, 0, dragonBitmap.getHeight()/2, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        dragon.add(Bitmap.createBitmap(dragonBitmap, (dragonBitmap.getWidth()/3)*1,dragonBitmap.getHeight()/2, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        dragon.add(Bitmap.createBitmap(dragonBitmap, (dragonBitmap.getWidth()/3)*2,dragonBitmap.getHeight()/2, dragonBitmap.getWidth()/3, dragonBitmap.getHeight()/2));
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.dragonBitmap=dragonBitmap;
        this.width=dragonBitmap.getWidth()/3;
        this.height=dragonBitmap.getHeight()/2;
        maindragon=dragon;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=maindragon.get(index);
        count++;
        if(count==3){
            index++;
            if(index==dragon.size()){
                index=0;
            }
            count=0;
        }
        if(blood<=0){
            gameView.pool.play(gameView.musicId.get(1),1,1,0,0,1);
            gameView.scores.add(new AddScore(gameView.ThirtyScore,x,y,gameView));
            gameView.explodes.add(new Explode(gameView.Explodemap,x,y-width/2,1,gameView));
            gameView.AllScore+=30;
            gameView.dragons.remove(this);
        }
        if(Ischage==true){
            i++;
            if(i>=25){
                maindragon=dragon;
                Ischage=false;
                i=0;
            }
        }
        if(gameView.IsStart==true)
        x-=2;
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
