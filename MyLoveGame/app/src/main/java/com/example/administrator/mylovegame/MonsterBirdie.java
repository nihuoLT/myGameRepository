package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class MonsterBirdie implements GameImage{
    public GameView gameView;
    public Bitmap BirdieBitmap=null;
    public Bitmap DropBitmap=null;
    public Bitmap ReturnBitmap=null;
    public int width=0;
    public int height=0;
    public int x=0;
    public int y=0;
    public int speedX=0;
    public int speedY=0;
    public int index=0;
    public int Xindex=0;
    public int Yindex=0;
    public int intvar=0,Yintvar=0;
    public int count=0;
    public boolean Isdrop=false,XBirdiedirection,YBirdiedirection,Iscantouch=true,Iscanattack=true,IsSound=true;
    public Random random=new Random();
    public List<Bitmap> birdie=new ArrayList<Bitmap>();
    public MonsterBirdie(Bitmap BirdieBitmap, Bitmap ReturnBitmap, int x, int y, int speedX, int speedY,
                         boolean XBirdiedirection, boolean YBirdiedirection, GameView gameView){
        this.gameView=gameView;
        this.BirdieBitmap=BirdieBitmap;
        this.DropBitmap=ReturnBitmap;
        birdie.add(Bitmap.createBitmap(BirdieBitmap, 0, 0, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*1, 0, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*2, 0, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*3, 0, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, 0,BirdieBitmap.getHeight()/2, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*1,BirdieBitmap.getHeight()/2, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*2,BirdieBitmap.getHeight()/2, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        birdie.add(Bitmap.createBitmap(BirdieBitmap, (BirdieBitmap.getWidth()/4)*3,BirdieBitmap.getHeight()/2, BirdieBitmap.getWidth()/4, BirdieBitmap.getHeight()/2));
        this.x=x;
        this.y=y;
        this.speedX=speedX;
        this.speedY=speedY;
        this.XBirdiedirection=XBirdiedirection;
        this.YBirdiedirection=YBirdiedirection;
        this.width=BirdieBitmap.getWidth()/4;
        this.height=BirdieBitmap.getHeight()/2;
    }
    @Override
    public Bitmap getBitmap() {
        if(Isdrop==false){
            ReturnBitmap=birdie.get(index);
            if(count==3){
                index++;
                if(index==birdie.size()){
                    index=0;
                }
                count=0;
            }
            count++;
            Xindex++;
            Yindex++;
            if(Xindex>=80+random.nextInt(100)){
                intvar=random.nextInt(200);
                if(intvar>=100){
                    gameView.birdies.remove(this);
                    int flagint=random.nextInt(200);
                    boolean flag;
                    if(flagint>=100){
                        flag=true;
                    }else {
                        flag=false;
                    }
                    gameView.birdies.add(new MonsterBirdie( gameView.Birdie, gameView.BirdieDrop,x,y,6,5,true,flag,gameView));
                }else{
                    gameView.birdies.remove(this);
                    int flagint=random.nextInt(200);
                    boolean flag;
                    if(flagint>=100){
                        flag=true;
                    }else {
                        flag=false;
                    }
                    gameView.birdies.add(new MonsterBirdie( gameView.OppositeBirdie, gameView.BirdieDrop,x,y,6,5,false,flag,gameView));
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
                if(gameView.IsStart==true)
                y-=speedY;
                if(y<0){
                    YBirdiedirection=false;
                }
            }else{
                if(gameView.IsStart==true)
                y+=speedY;
                if(y> gameView.ScreenHeight-BirdieBitmap.getHeight()/2){
                    YBirdiedirection=true;
                }
            }
            if(XBirdiedirection){
                if(gameView.IsStart==true)
                x-=speedX;
                if(x<0){
                    gameView.birdies.remove(this);
                    int flagint=random.nextInt(200);
                    boolean flag;
                    if(flagint>=100){
                        flag=true;
                    }else {
                        flag=false;
                    }
                    gameView.birdies.add(new MonsterBirdie( gameView.OppositeBirdie, gameView.BirdieDrop,0,y,6,5,false,flag,gameView));
                }
            }else{
                if(gameView.IsStart==true)
                x+=speedX;
                if(x>gameView.ScreenWidth-gameView.Birdie.getWidth()/4){
                    gameView.birdies.remove(this);
                    int flagint=random.nextInt(200);
                    boolean flag;
                    if(flagint>=100){
                        flag=true;
                    }else{
                        flag=false;
                    }
                    gameView.birdies.add(new MonsterBirdie(gameView.Birdie,gameView.BirdieDrop,gameView.ScreenWidth-BirdieBitmap.getWidth()/4,y,6,5,true,flag,gameView));
                }
            }
        }else{
            ReturnBitmap=DropBitmap;
            y+=8;
            if(IsSound==true){
                IsSound=false;
            }
            if(y>=gameView.ScreenHeight-ReturnBitmap.getHeight()){
                gameView.scores.add(new AddScore(gameView.TenScore,x,y,gameView));
                gameView.pool.play(gameView.musicId.get(1),1,1,0,0,1);
                gameView.explodes.add(new Explode(gameView.Explodemap,x,y-width/2,1,gameView));
                gameView.AllScore+=10;
                gameView. birdies.remove(this);
            }
        }
        return ReturnBitmap;
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
