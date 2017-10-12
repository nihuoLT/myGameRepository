package com.example.newgame_1;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Administrator on 2017-01-09.
 */
public class RainDrop implements GameInterFaceTwo{
    public Bitmap raindrop=null;
    public Random random=new Random();
    public StartBgView startBgView;
    public float x,y,speedx,speedy;
    public RainDrop(Bitmap raindrop,StartBgView startBgView){
        speedx=random.nextInt(4)+4;
        speedy=(float) Math.sqrt(speedx*speedx*4-speedx*speedx);
        DecimalFormat df = new DecimalFormat("0.0");
        speedy=Float.parseFloat(df.format(speedy));
        float scale=0;
        int varscale=random.nextInt(100);
        if(varscale<=40){
            scale=0.5f;
        }else if(varscale>40&&varscale<=80){
            scale=0.6f;
        } else{
            scale=0.7f;
        }
        Matrix matrix = new Matrix();
        //matrix.postRotate(-45);
        matrix.postScale(scale,scale);
        raindrop = Bitmap.createBitmap(raindrop, 0, 0,raindrop.getWidth(),
                raindrop.getHeight(), matrix, true);
        this.startBgView=startBgView;
        this.raindrop=raindrop;
        int var=random.nextInt(100);
        if(var>=30){
            this.x=random.nextInt(startBgView.ScreenWidth-raindrop.getWidth());
            this.y=-raindrop.getHeight();
        }else{
            this.x=-raindrop.getWidth();
            this.y=random.nextInt(startBgView.ScreenHeight-raindrop.getHeight());
        }
    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public Bitmap getBitmap() {
        x+=speedx;
        y+=speedy;
        if(x>=startBgView.ScreenWidth-raindrop.getWidth()||y>=startBgView.ScreenHeight){
//            raindrop.recycle();
//            raindrop=null;
        	startBgView.riandrops.remove(this);
            System.gc();
        }
        return raindrop;
    }
}
