package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class BgMap implements GameImage{
    public GameView gameView;
    public Bitmap bg_1=null,bg_2=null;
    public Bitmap NewBitmap=null;
    public int NewX_1=0,NewX_2=0;
    public boolean bgdirection=true;
    public BgMap(Bitmap bg_1,Bitmap bg_2,GameView gameView){
        this.gameView=gameView;
        this.bg_1=bg_1;
        this.bg_2=bg_2;
        NewBitmap=Bitmap.createBitmap(gameView.ScreenWidth,gameView.ScreenHeight, Bitmap.Config.ARGB_8888);
        this.NewX_2=gameView.ScreenWidth;
    }
    @Override
    public Bitmap getBitmap() {
        Canvas canvas=new Canvas(NewBitmap);
        canvas.drawBitmap(bg_1,NewX_1,0,gameView.spirit);
        canvas.drawBitmap(bg_2,NewX_2,0,gameView.spirit);
        if(bgdirection){
            NewX_1-=4;
            NewX_2-=4;
            if(NewX_1<=-gameView.ScreenWidth){
                bgdirection=false;
            }
        }else{
            NewX_1+=2;
            NewX_2+=2;
            if(NewX_1>=0){
                bgdirection=true;
            }
        }
        return NewBitmap;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
