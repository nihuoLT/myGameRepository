package com.example.administrator.mylovegame;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017-02-03.
 */
public class MonsterA implements GameImage{
    private Bitmap monsterabitmap;
    private GameView gameView;
    public int x,y,width,height,count=0,index=0,time=0,alpha=0;
    public boolean alphaflag=true,timeflag=false;
    private Random random=new Random();
    public List<Bitmap> monstera=new ArrayList<Bitmap>();
    public MonsterA(Bitmap monsterabitmap,int x,int y,GameView gameView){
        this.monsterabitmap=monsterabitmap;
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.width=monsterabitmap.getWidth()/2;
        this.height=monsterabitmap.getHeight();
        monstera.add(Bitmap.createBitmap(monsterabitmap, 0, 0, monsterabitmap.getWidth()/2, monsterabitmap.getHeight()));
        monstera.add(Bitmap.createBitmap(monsterabitmap,monsterabitmap.getWidth()/2 ,0, monsterabitmap.getWidth()/2, monsterabitmap.getHeight()));
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=monstera.get(index);
        if(count==3){
            index++;
            if(index==monstera.size()){
                index=0;
            }
            count=0;
        }
        if(alphaflag==true){
            if(alpha<255){
                alpha+=5;
                if(alpha>=255){
                    timeflag=true;
                }
            }
        }else {
            alpha-=5;
            if(alpha<=0){
                this.x=random.nextInt(gameView.ScreenWidth-width);
                this.y=random.nextInt(gameView.ScreenHeight-height);
                gameView.explodes.add(new ExplodeTwo(gameView.ExplodemapTwo,x,y,gameView));
                alphaflag=true;
            }
        }
        if(timeflag==true){
            time++;
            if(time>=60){
                alphaflag=false;
                timeflag=false;
                time=0;
            }
        }
        count++;
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
