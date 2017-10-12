package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;



import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017-02-15.
 */
public class Enemy implements MyInterface{
    private MyGameView myGameView=null;
    private Bitmap bitmap=null;
    private int x,y,width,height,model,time=0,xtime=0,addBulletTime=0;
    public int resid;
    private Random random=new Random();
    private boolean direction=false,flag=true;
    public Enemy(int resid, MyGameView myGameView, int model){
        this.resid=resid;
        this.myGameView=myGameView;
        this.bitmap=addBitmapToCache(resid);
        this.model=model;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        switch (model){
            case 1:
                this.x=-width;
                this.y=myGameView.ScreenHeight/6;
                break;
            case 2:
                this.x=myGameView.ScreenWidth;
                this.y=myGameView.ScreenHeight/6;
                break;
            case 3:
                this.x=myGameView.ScreenWidth/6;
                this.y=-bitmap.getHeight();
                break;
            case 4:
                this.x=5*myGameView.ScreenWidth/6-bitmap.getWidth();
                this.y=-bitmap.getHeight();
                break;
            case 5:
                this.x=-width;
                this.y=myGameView.ScreenHeight/10;
                Matrix matrix2 = new Matrix();
                matrix2.postRotate(-45);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0,width,
                       height, matrix2, true);
                break;
            case 6:
                this.x=myGameView.ScreenWidth;
                this.y=myGameView.ScreenHeight/10;
                Matrix matrix1 = new Matrix();
                matrix1.postRotate(45);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0,width,
                        height, matrix1, true);
                break;
            case 7:
                this.x=myGameView.ScreenWidth/2-bitmap.getWidth()/2;
                this.y=-bitmap.getHeight();
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        time++;
        if(time>=60){
            if(direction==false){
                direction=true;
                time=0;
            }else{
                direction=false;
                time=0;
            }
        }
        switch (model){
            case 1:
                x+=2;
                if(x>=myGameView.ScreenWidth){
                    myGameView.my.remove(this);
                }
                if(direction==true){
                    y-=4;
                }else {
                    y+=4;
                }
                break;
            case 2:
                x-=2;
                if(x<=-width){
                    myGameView.my.remove(this);
                }
                if(direction==true){
                    y-=4;
                }else {
                    y+=4;
                }
                break;
            case 3:
                xtime++;
                if(xtime>=30){
                    if(flag==true){
                        flag=false;
                    }else {
                        flag=true;
                    }
                    xtime=0;
                }
                y+=4;
                if(flag){
                    x-=1;
                }else {
                    x+=1;
                }
                if(y>=myGameView.ScreenHeight){
                    myGameView.my.remove(this);
                }
                break;
            case 4:
                xtime++;
                if(xtime>=30){
                    if(flag==true){
                        flag=false;
                    }else {
                        flag=true;
                    }
                    xtime=0;
                }
                if(flag){
                    x-=1;
                }else {
                    x+=1;
                }
                y+=4;
                if(y>=myGameView.ScreenHeight){
                    myGameView.my.remove(this);
                }
                break;
            case 5:
                y+=5;
                x+=5;
//                if(x>=10&&x<=14){
//                    myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_2,x+width/2-myGameView.epb_2.getWidth()/2,y+height,1,myGameView));
//                }
                if(y>=myGameView.ScreenHeight||x>=myGameView.ScreenWidth){
                    myGameView.my.remove(this);
                }
                break;
            case 6:
                y+=5;
                x-=5;
//                if(x<=myGameView.ScreenWidth-width-10&&x>=myGameView.ScreenWidth-width-14){
//                    myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_2,x+width/2-myGameView.epb_2.getWidth()/2,y+height,2,myGameView));
//                }
                if(y>=myGameView.ScreenHeight||x<=-bitmap.getWidth()){
                    myGameView.my.remove(this);
                }
                break;
            case 7:
                if(y<=myGameView.ScreenHeight/6){
                    y+=4;
                }else{
                    addBulletTime++;
                    if(addBulletTime>=30){
                        myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_2,x+width/2-myGameView.epb_2.getWidth()/2,y+height,3,myGameView));
                        myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_2,x+width/2-myGameView.epb_2.getWidth()/2,y+height,4,myGameView));
                        myGameView.bullets.add(new EnemyBullet_2(myGameView.epb_2,x+width/2-3*myGameView.epb_2.getWidth()/2,y+height,5,myGameView));
                        addBulletTime=0;
                    }
                }
                break;
        }
            return bitmap;
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
    public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
                                       int x2,int y2, int w2, int h2) {
        if( x1+w1>x2&&y1+h1>y2&&x1<x2+w2&&y1<y2+h2){
            return true;
        }
        return false;
    }
    public void IsBeAttacked(ArrayList<MyInterface> bitmaps){
        for(MyInterface zidan:(ArrayList<MyInterface>)bitmaps.clone()) {
            if(zidan instanceof Bullet){
                if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                    if(resid== R.mipmap.ep_5){
                        myGameView.myScore+=5;
                        myGameView.my.add(new AddScore(R.mipmap.five,x,y,myGameView));
                        myGameView.bullets.add(new ChageBullet(R.mipmap.jls,x,y,myGameView));
                    }else if(resid==R.mipmap.ep_2){
                        myGameView.myScore+=4;
                        myGameView.my.add(new AddScore(R.mipmap.four,x,y,myGameView));
                    }else {
                        myGameView.myScore+=3;
                        myGameView.my.add(new AddScore(R.mipmap.three,x,y,myGameView));
                    }
                    myGameView.my.remove(this);
                    myGameView.pool.play(myGameView.musicId.get(3),1,1,0,0,1);
                    myGameView.bullets.remove(zidan);
                    myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                }
            }
        }
    }
}
