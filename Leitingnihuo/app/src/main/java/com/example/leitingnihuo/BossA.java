package com.example.leitingnihuo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;





import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017-04-01.
 */
public class BossA implements MyInterface {
    private int x,y,width,height,time=0,xtag,ytag,model=1,index=0,count=0,blood=300,PaintTime=0,alpha=255,twotime=0,bulletTime=0,bulletTimeTwo=0,bulletTimeThree=0;
    private MyGameView myGameView;
    private boolean xflag=true,yflag=true;
    private Paint paint=new Paint();
    private Random random=new Random();
    public List<Bitmap> bosss=new ArrayList<Bitmap>();
    private Bitmap Cache;
    private boolean PaintFlag,alphaflag=false,timeflag=false,IsAlpha=false,Ischange=true;
    public BossA(Bitmap bitmap,int x,int y,MyGameView myGameView){
        this.x=x;
        this.y=y;
        this.myGameView=myGameView;
        this.width=bitmap.getWidth()/3;
        this.height=bitmap.getHeight()/3;
        this.Cache=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        bosss.add(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap, (bitmap.getWidth()/3)*1, 0, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap, (bitmap.getWidth()/3)*2, 0, bitmap.getWidth()/3, bitmap.getHeight()/3));

        bosss.add(Bitmap.createBitmap(bitmap, 0,bitmap.getHeight()/3, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap, (bitmap.getWidth()/3)*1, bitmap.getHeight()/3, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap,(bitmap.getWidth()/3)*2,bitmap.getHeight()/3, bitmap.getWidth()/3, bitmap.getHeight()/3));

        bosss.add(Bitmap.createBitmap(bitmap, 0,(bitmap.getHeight()/3)*2, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap,(bitmap.getWidth()/3)*1,(bitmap.getHeight()/3)*2, bitmap.getWidth()/3, bitmap.getHeight()/3));
        bosss.add(Bitmap.createBitmap(bitmap,(bitmap.getWidth()/3)*2,(bitmap.getHeight()/3)*2, bitmap.getWidth()/3, bitmap.getHeight()/3));
    }
    @Override
    public Bitmap getBitmap(){
        Bitmap bitmap=bosss.get(index);
        count++;
        if(count==3){
            index++;
            if(index==bosss.size()){
                index=0;
            }
            count=0;
        }
        if(alpha>=100){
            bulletTimeTwo++;
            if(bulletTimeTwo>=40+random.nextInt(100)){
                myGameView.bullets.add(new BossABullet(R.mipmap.bosszidan,x+width/2-myGameView.bulletW/2,y+height,8,myGameView));
                bulletTimeTwo=0;
            }
        }
        if(alpha>=100){
            bulletTimeThree++;
            if(bulletTimeThree>=150+random.nextInt(100)){
                myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,10,myGameView));
                myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,10,myGameView));
                myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,10,myGameView));
                myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,10,myGameView));
                myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,10,myGameView));
                bulletTimeThree=0;
            }
        }
        if(alpha==255){
            bulletTime++;
            if(bulletTime>=40){
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,1,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,2,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,3,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,4,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,5,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,6,myGameView));
                myGameView.bullets.add(new BossABullet(R.mipmap.feibiao,x+width/2-myGameView.bulletW/2,y+height,7,myGameView));
                bulletTime=0;
            }
        }
        if(IsAlpha){
            if(alphaflag==true){
                if(alpha<255){
                    alpha+=5;
                    if(alpha>=255){
                        timeflag=true;
                    }
                }
            }else {
                if(alpha<=0){
                    if(Ischange){
                        this.x=random.nextInt(myGameView.ScreenWidth-width);
                        this.y=random.nextInt(myGameView.ScreenHeight/2-height);
                        Ischange=false;
                    }
                    timeflag=true;
                }else {
                    alpha-=5;
                }
            }
            if(timeflag==true){
                twotime++;
                if(twotime>=300+random.nextInt(200)){
                    Ischange=true;
                    if(alpha>=255){
                        alphaflag=false;
                    }else{
                        alphaflag=true;
                    }
                    timeflag=false;
                    twotime=0;
                }
            }
        }
        if(PaintFlag==true){
            PaintTime++;
            if(PaintTime>=5) {
                paint.reset();
                PaintFlag = false;
                PaintTime = 0;
            }
        }
        switch (model){
            case 1:
                y+=15;
                if(y>=myGameView.ScreenHeight/2-height){
                    IsAlpha=true;
                    model=2;
                }
                break;
            case 2:
                time++;
                if(xflag==true) {
                    x -= 3;
                    if(x<=0){
                        xflag=false;
                    }
                }else {
                    x += 3;
                    if(x>=myGameView.ScreenWidth-width){
                        xflag=true;
                    }
                }
                if(yflag==true){
                    y -=10;
                    if(y<=0)
                        yflag=false;
                }else {
                    y += 10;
                    if(y>=myGameView.ScreenHeight/2)
                        yflag=true;
                }
                if(time>=100+random.nextInt(200)){
                    xtag=random.nextInt(100);
                    ytag=random.nextInt(100);
                    if(xtag<=50){
                        xflag=false;
                    }else {
                        xflag=true;
                    }
                    if(ytag<=33){
                        yflag=false;
                    }else{
                        yflag=true;
                    }
                    time=0;
                }
                break;
        }
        Canvas canvas=new Canvas(Cache);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        if(IsAlpha)
        paint.setAlpha(alpha);
        canvas.drawBitmap(bitmap,0,0,paint);
        return Cache;
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
                if(alpha>0){
                    if(isCollisionWithRect(zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight(),x,y,width,height)==true){
                        //if(isCollisionWithRect(x,y,width,height,zidan.getX(),zidan.getY(),zidan.getWidth(),zidan.getHeight())==true){
                        myGameView.bullets.remove(zidan);
                        PaintFlag=true;
                        ColorMatrix cm=new ColorMatrix();
                        cm.set(new float[] { 160 / 128f, 0, 0, 0, 0,// 红色值
                                0, 32 / 128f, 0, 0, 0,// 绿色值
                                0, 0, 240 / 128f, 0, 0,// 蓝色值
                                0, 0, 0, 1, 0 // 透明度
                        });
                        paint.setColorFilter(new ColorMatrixColorFilter(cm));
                        blood--;
                        if(blood<=0){
                            myGameView.my.remove(this);
                            myGameView.myScore+=600;
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new Enemy_6(R.mipmap.bosssmall,x,y,6,myGameView));
                            myGameView.my.add(new AddScore(R.mipmap.hundred6,x,y,myGameView));
                            myGameView.my.add(new Explode(R.mipmap.baozha,x+width/2-myGameView.explodeW/2,y-myGameView.explodeH/2,2,myGameView));
                        }
                    }
                }
            }
        }

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
}
