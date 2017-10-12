package com.example.leitingnihuo;




/**
 * Created by Administrator on 2017-02-20.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class AddScore implements MyInterface {
    public Bitmap score;
    public MyGameView myGameView;
    public int x;
    public int y;
    public int startY,resid;
    public AddScore(int resid,int x,int y,MyGameView myGameView){
        this.myGameView=myGameView;
        this.resid=resid;
        this.score=addBitmapToCache(resid);
        this.x=x;
        this.y=y;
        this.startY=y;
    }

    @Override
    public Bitmap getBitmap() {
        y-=10;
        if(y<=startY-200){
            //score=null;
            myGameView.my.remove(this);
            System.gc();
        }
        return score;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        String imageKey=String.valueOf(resId);
        Bitmap bitmap=(Bitmap)myGameView.lruCache.get(imageKey);
        if(bitmap!=null){
            return bitmap;
        }else {
            Bitmap bitmaptwo= BitmapFactory.decodeResource(myGameView.getResources(),resId);
            myGameView.lruCache.put(imageKey,bitmaptwo);
            return bitmaptwo;
        }
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
