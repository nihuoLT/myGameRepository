package com.example.leitingnihuo;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;



/**
 * Created by Administrator on 2017-02-15.
 */
public class BgMap implements MyInterface {
    private MyGameView myGameView;
    private Bitmap bg_1=null,bg_2=null,Cache=null;
    private int y1=0,y2,width,height;
    public int Speed=2;
    private Paint paint=new Paint();
    public BgMap(Bitmap bg_1,Bitmap bg_2,MyGameView myGameView){
        this.myGameView=myGameView;
        this.Cache=Bitmap.createBitmap(myGameView.ScreenWidth,myGameView.ScreenHeight, Bitmap.Config.ARGB_8888);
        this.width=myGameView.ScreenWidth;
        this.height=myGameView.ScreenHeight;
        this.bg_1=condenseBgMap(bg_1);
        this.bg_2=condenseBgMap(bg_2);
        this.y2=-myGameView.ScreenHeight;
    }
    public Bitmap condenseBgMap(Bitmap map){
        int width = map.getWidth();
        int height = map.getHeight();
        float scaleWidth = ((float) this.width) / width;
        float scaleHeight = ((float) this.height) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        map = Bitmap.createBitmap(map, 0, 0, width,
                height, matrix, true);
        return map;
    }
    public Bitmap getBitmap() {
        Canvas canvas=new Canvas(Cache);
        canvas.drawBitmap(bg_1,0,y1,paint);
        canvas.drawBitmap(bg_2,0,y2,paint);
        y1+=Speed;
        y2+=Speed;
        if(y1>=myGameView.ScreenHeight){
            y1=-myGameView.ScreenHeight;
        }
        if(y2>=myGameView.ScreenHeight){
            y2=-myGameView.ScreenHeight;
        }
        return Cache;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        return null;
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
