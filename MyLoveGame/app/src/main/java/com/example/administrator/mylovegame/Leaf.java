package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import java.util.Random;

/**
 * Created by Administrator on 2017-01-12.
 */
public class Leaf implements GameImage{
    public Bitmap raindrop=null,Catch=null;
    public Random random=new Random();
    public Matrix matrix;
    public MainActivityBgView mainActivityBgView;
    public Paint paint=new Paint();
    public Camera camera;
    public Canvas canvas;
    public int x,y,speedX,speedY,anglex=0,angley=0,anglez=0,count=0;
    public float scale=0;
    public boolean IsX=false,IsY=false,IsZ=false;
    public Leaf(Bitmap raindrop,MainActivityBgView mainActivityBgView){
        //speedX=random.nextInt(4)+3;
        speedX=1;
        speedY=random.nextInt(3)+4;
        int varrotate=random.nextInt(100);
        if(varrotate<=20){
            IsX=true;
            IsZ=true;
        }else if(varrotate>20&&varrotate<=70){
            IsZ=true;
        }else{
            IsY=true;
            IsZ=true;
        }
        int varscale=random.nextInt(100);
        if(varscale<=30){
            scale=0.1f;
        }else if(varscale>30&&varscale<=85){
            scale=0.2f;
        } else{
            scale=0.1f;
        }
        Catch=Bitmap.createBitmap(raindrop.getWidth(),raindrop.getHeight(), Bitmap.Config.ARGB_4444);
        matrix=new Matrix();
        camera=new Camera();
        //matrix.preTranslate(0, raindrop.getHeight()/2);
        this.mainActivityBgView=mainActivityBgView;
        this.raindrop=raindrop;
        this.x=30+random.nextInt(mainActivityBgView.DisplayWidth-raindrop.getWidth()-30);
        this.y=-raindrop.getHeight();
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Bitmap getBitmap() {
        if(IsZ==true){
            anglez+=3;
            if(anglez>=360){
                anglez=0;
            }
        }
        if(IsY==true){
            angley+=3;
            if(angley>=360){
                angley=0;
            }
        }
        if(IsX==true){
            anglex+=3;
            if(anglex>=360){
                anglex=0;
            }
        }
        camera.save();
        camera.rotate(anglex,0,anglez);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.postTranslate(raindrop.getWidth()+80,raindrop.getHeight()+50);
        matrix.postScale(scale,scale);
        canvas=new Canvas(Catch);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(raindrop,matrix,null);
        count=0;
        x+=speedX;
        y+=speedY;
        if(x>=mainActivityBgView.DisplayWidth||y>=mainActivityBgView.DisplayHeight){
            camera=null;
//            Catch.recycle();
//            raindrop.recycle();
//            Catch=null;
//            raindrop=null;
            mainActivityBgView.riandrops.remove(this);
            System.gc();
        }
        return Catch;
    }
}
