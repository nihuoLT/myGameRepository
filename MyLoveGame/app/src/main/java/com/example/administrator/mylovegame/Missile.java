package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017-02-07.
 */
public class Missile implements GameImage{
    private Bitmap missile=null,bitmap=null;
    private GameView gameView=null;
    private int x,y,time=0,width,height,winW,winH;
    public Missile(Bitmap missile,GameView gameView){
        this.gameView=gameView;
        this.missile=missile;
        this.x=gameView.ScreenWidth/2-missile.getWidth()/2;
        this.y=gameView.ScreenHeight;
        this.bitmap= BitmapFactory.decodeResource(gameView.getResources(),R.drawable.bombbaozha);
        this.width=bitmap.getWidth()/4;
        this.height=bitmap.getHeight()/2;
        this.winW=gameView.ScreenWidth;
        this.winH=gameView.ScreenHeight;
    }
    @Override
    public Bitmap getBitmap() {
        y-=8;
        if(y<=gameView.getHeight()/3){
            gameView.bombs.remove(this);
            gameView.allflag=false;
           for(GameImage image:gameView.dragons){
               if(image.getX()>=0&&image.getY()<=gameView.ScreenWidth-image.getWidth()&&image.getY()>=0&&image.getY()<=gameView.ScreenHeight-image.getHeight()){
                   if(image instanceof Dragon){
                       gameView.AllScore+=30;
                   }
                   if(image instanceof Demon){
                       gameView.AllScore+=5;
                   }
                   if(image instanceof MonsterBig){
                       gameView.AllScore+=8;
                   }
                   if(image instanceof MonsterSmall){
                       gameView.AllScore+=3;
                   }
                   if(image instanceof Bat){
                       gameView.AllScore+=6;
                   }
                   if(image instanceof MonsterA){
                       gameView.AllScore+=7;
                   }
               }
           }
            gameView.AllScore+=gameView.birdies.size()*10;
            gameView.birdies.clear();
            gameView.dragons.clear();
            gameView.explodes.add(new Explode(bitmap,winW/2-width/2,winH/2-height/2,2,gameView));
            gameView.allflag=true;
            bitmap.recycle();
        }
        return missile;
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
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
