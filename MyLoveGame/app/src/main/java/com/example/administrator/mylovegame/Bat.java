package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class Bat implements GameImage {
    public GameView gameView;
    public Bitmap batBitmap;
    public int x=0,y=0,width=0,height=0;
    public List<Bitmap> bat=new ArrayList<Bitmap>();
    public int speedX=0;
    public int speedY=0;
    public int index=0;
    public int Xindex=0;
    public int Yindex=0;
    public int intvar=0,Yintvar=0;
    public int count=0,time=0;
    public boolean XBirdiedirection,YBirdiedirection;
    public Random random=new Random();
    public Bat(Bitmap batBitmap, int x, int y,int speedX, int speedY,
               boolean XBirdiedirection, boolean YBirdiedirection, GameView gameView,int model){
        switch (model){
            case 1:
                bat.add(Bitmap.createBitmap(batBitmap, 0, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*1, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*2, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, 0, batBitmap.getHeight()/2, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*1,batBitmap.getHeight()/2, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                break;
            case 2:
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*2, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*1, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, 0, 0, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*2,batBitmap.getHeight()/2, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                bat.add(Bitmap.createBitmap(batBitmap, (batBitmap.getWidth()/3)*1,batBitmap.getHeight()/2, batBitmap.getWidth()/3, batBitmap.getHeight()/2));
                break;
        }
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.speedX=speedX;
        this.speedY=speedY;
        this.XBirdiedirection=XBirdiedirection;
        this.YBirdiedirection=YBirdiedirection;
        this.batBitmap=batBitmap;
        this.width=batBitmap.getWidth()/3;
        this.height=batBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=bat.get(index);
        count++;
        if(count==3){
            index++;
            if(index==bat.size()){
                index=0;
            }
            count=0;
        }
        Xindex++;
        Yindex++;
        if(Xindex>=80+random.nextInt(100)){
            intvar=random.nextInt(200);
            if(intvar>=100){
                gameView.dragons.remove(this);
                int flagint=random.nextInt(200);
                boolean flag;
                if(flagint>=100){
                    flag=true;
                }else {
                    flag=false;
                }
                gameView.dragons.add(new Bat(gameView.BatBitmap,x,y,6,5,true,flag,gameView,1));
            }else{
                gameView.dragons.remove(this);
                int flagint=random.nextInt(200);
                boolean flag;
                if(flagint>=100){
                    flag=true;
                }else {
                    flag=false;
                }
                gameView.dragons.add(new Bat(gameView.OppositeBat,x,y,6,5,false,flag,gameView,2));
            }
            Xindex=0;
        }
        if(Yindex>=80+random.nextInt(100)){
            Yintvar=random.nextInt(200);
            if(Yintvar>=100){
                YBirdiedirection=true;
            }else{
                YBirdiedirection=false;
            }
            Yindex=0;
        }
        if(YBirdiedirection){
            y-=speedY;
            if(y<0){
                YBirdiedirection=false;
            }
        }else{
            y+=speedY;
            if(y> gameView.ScreenHeight-batBitmap.getHeight()/2){
                YBirdiedirection=true;
            }
        }
        if(XBirdiedirection){
            x-=speedX;
            if(x<0){
                gameView.dragons.remove(this);
                int flagint=random.nextInt(200);
                boolean flag;
                if(flagint>=100){
                    flag=true;
                }else {
                    flag=false;
                }
                gameView.dragons.add(new Bat( gameView.OppositeBat,0,y,6,5,false,flag,gameView,2));
            }
        }else{
            x+=speedX;
            if(x>gameView.ScreenWidth-gameView.BatBitmap.getWidth()/3){
                gameView.dragons.remove(this);
                int flagint=random.nextInt(200);
                boolean flag;
                if(flagint>=100){
                    flag=true;
                }else{
                    flag=false;
                }
                gameView.dragons.add(new Bat(gameView.BatBitmap,gameView.ScreenWidth-batBitmap.getWidth()/3,y,6,5,true,flag,gameView,1));
            }
        }
        return bitmap;
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
