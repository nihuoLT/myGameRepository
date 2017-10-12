package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.Random;

/**
 * Created by Administrator on 2017-01-09.
 */
public class RainDrop implements GameImage{
    public Bitmap raindrop=null;
    public Random random=new Random();
    public MainActivityBgView mainActivityBgView;
    public int x,y,speed;
    public RainDrop(Bitmap raindrop,MainActivityBgView mainActivityBgView){
        speed=random.nextInt(6)+4;
        float scale=0;
        int varscale=random.nextInt(100);
        if(varscale<=40){
            scale=0.1f;
        }else if(varscale>40&&varscale<=80){
            scale=0.2f;
        } else{
            scale=0.3f;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(-45);
        matrix.postScale(scale,scale);
        raindrop = Bitmap.createBitmap(raindrop, 0, 0,raindrop.getWidth(),
                raindrop.getHeight(), matrix, true);
        this.mainActivityBgView=mainActivityBgView;
        this.raindrop=raindrop;
        int var=random.nextInt(100);
        if(var>=30){
            this.x=random.nextInt(mainActivityBgView.DisplayWidth-raindrop.getWidth());
            this.y=-raindrop.getHeight();
        }else{
            this.x=-raindrop.getWidth();
            this.y=random.nextInt(mainActivityBgView.DisplayHeight-raindrop.getHeight());
        }
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
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Bitmap getBitmap() {
        x+=speed;
        y+=speed;
        if(x>=mainActivityBgView.DisplayWidth-raindrop.getWidth()||y>=mainActivityBgView.DisplayHeight){
//            raindrop.recycle();
//            raindrop=null;
            mainActivityBgView.riandrops.remove(this);
            System.gc();
        }
        return raindrop;
    }
}
