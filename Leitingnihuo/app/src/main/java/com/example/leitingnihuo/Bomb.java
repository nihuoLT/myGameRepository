package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;






import java.util.ArrayList;


/**
 * Created by Administrator on 2017-04-13.
 */
public class Bomb implements MyInterface {
    private Bitmap bitmap;
    private MyGameView myGameView;
    private int x,y,model,width,height,time=0;
    public Bomb(Bitmap bitmap,int x,int y,int model,MyGameView myGameView){
        this.x=x;
        this.y=y;
        this.model=model;
        this.myGameView=myGameView;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        switch (model){
            case 1:
                this.bitmap= BitmapFactory.decodeResource(myGameView.getResources(), R.mipmap.baojian);
                break;
            case 2:
                this.bitmap= BitmapFactory.decodeResource(myGameView.getResources(),R.mipmap.baojian2);
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        switch (model){
            case 1:
                y-=8;
                if(y<=-height){
                    myGameView.my.remove(this);
                    //bitmap.recycle();
                }
                break;
            case 2:
                y+=8;
                if(y>=myGameView.ScreenHeight){
                    myGameView.my.remove(this);
                    //bitmap.recycle();
                }
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return bitmap;
    }

    @Override
    public Bitmap addBitmapToCache(int resId) {
        return null;
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
        for(MyInterface myInterface:(ArrayList<MyInterface>)bitmaps.clone()) {
                if(isCollisionWithRect(myInterface.getX(),myInterface.getY(),myInterface.getWidth(),myInterface.getHeight(),x,y,width,height)==true){
                        if(myInterface instanceof Enemy){
                            Enemy enemy=(Enemy) myInterface;
                            if(enemy.resid==R.mipmap.ep_5){
                                myGameView.myScore+=5;
                                myGameView.my.add(new AddScore(R.mipmap.five,enemy.getX(),enemy.getY(),myGameView));
                            }else if(enemy.resid==R.mipmap.ep_2){
                                myGameView.myScore+=4;
                                myGameView.my.add(new AddScore(R.mipmap.four,enemy.getX(),enemy.getY(),myGameView));
                            }else {
                                myGameView.myScore+=3;
                                myGameView.my.add(new AddScore(R.mipmap.three,enemy.getX(),enemy.getY(),myGameView));
                            }
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(enemy);
                        }
                        if(myInterface instanceof Enemy_2){
                            myGameView.myScore+=8;
                            myGameView.my.add(new AddScore(R.mipmap.eight,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof Enemy_3){
                            myGameView.myScore+=6;
                            myGameView.my.add(new AddScore(R.mipmap.six,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof Enemy_4){
                            myGameView.myScore+=10;
                            myGameView.my.add(new AddScore(R.mipmap.ten,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof Enemy_5){
                            myGameView.myScore+=9;
                            myGameView.my.add(new AddScore(R.mipmap.nine,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof Enemy_6){
                            myGameView.myScore+=7;
                            myGameView.my.add(new AddScore(R.mipmap.seven,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof Enemy_7){
                            myGameView.myScore+=8;
                            myGameView.my.add(new AddScore(R.mipmap.eight,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                       if(myInterface instanceof Enemy_8){
                           myGameView.myScore+=7;
                           myGameView.my.add(new AddScore(R.mipmap.seven,myInterface.getX(),myInterface.getY(),myGameView));
                           myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                           myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof EnemyTest){
                            myGameView.myScore+=6;
                            myGameView.my.add(new AddScore(R.mipmap.six,myInterface.getX(),myInterface.getY(),myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,myInterface.getX()+myInterface.getWidth()/2-myGameView.explodeW/2,myInterface.getY()-myGameView.explodeH/2,2,myGameView));
                            myGameView.my.remove(myInterface);
                        }
                        if(myInterface instanceof EnemyBullet_1){
                            myGameView.bullets.remove(myInterface);
                        }
                        if(myInterface instanceof EnemyBullet_2){
                            myGameView.bullets.remove(myInterface);
                        }
                        if(myInterface instanceof EnemyBullet_3){
                            myGameView.bullets.remove(myInterface);
                        }
                        if(myInterface instanceof BossABullet){
                            myGameView.bullets.remove(myInterface);
                        }
                        if(myInterface instanceof BossBBullet){
                            myGameView.bullets.remove(myInterface);
                        }
                        if(myInterface instanceof Bullet_8){
                            myGameView.my.remove(myInterface);
                        }
                }
        }
    }
}
