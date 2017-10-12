package com.example.newgame_1;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;



/**
 * Created by Administrator on 2017-02-15.
 */
public class BgMap implements GameInterFace {
    private GameView myGameView;
    private Bitmap bg=null;
    private int x=0,y=0,width,height;
    public BgMap(GameView myGameView){
        this.myGameView=myGameView;
        this.bg=BitmapFactory.decodeStream(myGameView.getResources().openRawResource(R.drawable.mainbg));
        this.bg=condenseBgMap(bg);
        this.width=bg.getWidth();
        this.height=bg.getHeight();
    }
    public Bitmap condenseBgMap(Bitmap map){
        int width = map.getWidth();
        int height = map.getHeight();
        float scaleWidth = ((float) this.myGameView.ScreenWidth) / width;
        float scaleHeight = ((float) this.myGameView.ScreenHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        map = Bitmap.createBitmap(map, 0, 0, width,
                height, matrix, true);
        return map;
    }
    public Bitmap getBitmap() {
        return bg;
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
