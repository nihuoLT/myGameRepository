package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.view.SurfaceHolder;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class AddScore implements GameImage{
    public Bitmap score;
    public GameView gameView;
    public int x;
    public int y;
    public int startY;
    public AddScore(Bitmap score,int x,int y,GameView gameView){
        this.gameView=gameView;
        this.score=score;
        this.x=x;
        this.y=y;
        this.startY=y;
    }

    @Override
    public Bitmap getBitmap() {
        y-=10;
        if(y<=startY-200){
            gameView.scores.remove(this);
        }
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
