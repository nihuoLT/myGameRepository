package com.example.administrator.mylovegame;

/**
 * Created by Administrator on 2017-01-10.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-01-07.
 */
public class MainActivityBgView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder surfaceHolder;
    public boolean state=true,stopstate=false,isadd=false;
    private Paint paint,mpaint;
    private Thread thread;
    private Bitmap Cache,bg,raindrop,leaf;
    public int DisplayWidth,DisplayHeight,x=10,y=10,countL=0,countR=0,angleW=0,angleS=-90,alpha=255;
    public ArrayList<GameImage> riandrops=new ArrayList<GameImage>() ;
    public MainActivityBgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayWidth=wm.getDefaultDisplay().getWidth();
        DisplayHeight=wm.getDefaultDisplay().getHeight();
        Cache=Bitmap.createBitmap(DisplayWidth,DisplayHeight,Bitmap.Config.ARGB_4444);
        paint=new Paint();
        bg= BitmapFactory.decodeResource(getResources(),R.mipmap.startbg);
        raindrop= BitmapFactory.decodeResource(getResources(),R.mipmap.raindrop);
        leaf= BitmapFactory.decodeResource(getResources(),R.mipmap.leaf);
    }
    private void draw(){
            if(isadd=true){
                countR++;
                if(countR>=10){
                    riandrops.add(new RainDrop(raindrop,this));
                    countR=0;
                }
                countL++;
                if(countL>=30){
                    riandrops.add(new Leaf(leaf,this));
                    countL=0;
                }
            }
            Canvas catchcanvas=new Canvas(Cache);
            catchcanvas.drawBitmap(bg,new Rect(0,0,bg.getWidth(),bg.getHeight()),new Rect(0,0,DisplayWidth,DisplayHeight),paint);
            for(GameImage p:(List<GameImage>)riandrops.clone()){
                catchcanvas.drawBitmap(p.getBitmap(),p.getX(),p.getY(),paint);
            }
            Canvas canvas=surfaceHolder.lockCanvas();
            canvas.drawBitmap(Cache,0,0,paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
    }
    @Override
    public void run() {
        while (state==true){
            try {
                draw();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread= new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        state=false;
        surfaceHolder.removeCallback(this);
    }

}
