package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Explode implements GameImage{
    public GameView gameView;
    public Bitmap explodeBitmap;
    public int x=0,y=0,width=0,height=0;
    public List<Bitmap> explode=new ArrayList<Bitmap>();
    public int index=0,count=0;
    public Explode(Bitmap explodeBitmap,int x,int y,int model,GameView gameView ){
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.explodeBitmap=explodeBitmap;
        switch (model){
            case 1:
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*2, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));

                explode.add(Bitmap.createBitmap(explodeBitmap, 0,explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1, explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*2,explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));

                explode.add(Bitmap.createBitmap(explodeBitmap, 0,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*1,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*2,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                this.width=explodeBitmap.getWidth()/3;
                this.height=explodeBitmap.getHeight()/3;
                break;
            case 2:
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/4, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 3*(explodeBitmap.getWidth())/4,0,  explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,explodeBitmap.getWidth()/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,3*(explodeBitmap.getWidth())/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                this.width=explodeBitmap.getWidth()/4;
                this.height=explodeBitmap.getHeight()/2;
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=explode.get(index);
        count++;
        if(count==3){
            index++;
            if(index==explode.size()){
                //index=0;
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
