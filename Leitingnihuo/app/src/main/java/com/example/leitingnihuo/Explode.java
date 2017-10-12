package com.example.leitingnihuo;

/**
 * Created by Administrator on 2017-02-20.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;




import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Explode implements MyInterface{
    public MyGameView myGameView;
    public Bitmap explodeBitmap;
    public int x=0,y=0,width=0,height=0,resid,model;
    public List<Bitmap> explode=new ArrayList<>();
    public int index=0,count=0;
    public Explode(int resid,int x,int y,int model,MyGameView myGameView ){
        this.resid=resid;
        this.myGameView=myGameView;
        this.explodeBitmap=addBitmapToCache(resid);
        this.x=x;
        this.y=y;
        this.model=model;
        switch (model){
            case 1:
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*2, 0, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));

                explode.add(Bitmap.createBitmap(explodeBitmap, 0,explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap, (explodeBitmap.getWidth()/3)*1, explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*2,explodeBitmap.getHeight()/3, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));

                explode.add(Bitmap.createBitmap(explodeBitmap, 0,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*1,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                explode.add(Bitmap.createBitmap(explodeBitmap,(explodeBitmap.getWidth()/3)*2,(explodeBitmap.getHeight()/3)*2, explodeBitmap.getWidth()/3, explodeBitmap.getHeight()/3));
                this.width=explodeBitmap.getWidth()/3;
                this.height=explodeBitmap.getHeight()/3;
                break;
            case 2:
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/4, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 3*(explodeBitmap.getWidth())/4,0,  explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,explodeBitmap.getWidth()/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,3*(explodeBitmap.getWidth())/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                this.width=explodeBitmap.getWidth()/4;
                this.height=explodeBitmap.getHeight()/2;
                break;
            case 3:
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/4, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2, 0, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 3*(explodeBitmap.getWidth())/4,0,  explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, 0, explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,explodeBitmap.getWidth()/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap, explodeBitmap.getWidth()/2,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                explode.add(Bitmap.createBitmap(explodeBitmap,3*(explodeBitmap.getWidth())/4,explodeBitmap.getHeight()/2, explodeBitmap.getWidth()/4, explodeBitmap.getHeight()/2));
                this.width=explodeBitmap.getWidth()/4;
                this.height=explodeBitmap.getHeight()/2;
                break;
        }
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=explode.get(index);
        switch (model){
            case 1:
                count++;
                if(count==3){
                    index++;
                    if(index==explode.size()){
                        //index=0;
                        explodeBitmap=null;
                        myGameView.my.remove(this);
                        System.gc();
                    }
                    count=0;
                }
                break;
            case 2:
                count++;
                if(count==3){
                    index++;
                    if(index==explode.size()){
                        //index=0;
                        explodeBitmap=null;
                        myGameView.my.remove(this);
                        System.gc();
                    }
                    count=0;
                }
                break;
            case 3:
                for (MyInterface myInterface:(List<MyInterface>)myGameView.my.clone()){
                    if(myInterface instanceof Enemy){
                        Enemy enemy=(Enemy) myInterface;
                        if(enemy.resid== R.mipmap.ep_5){
                            myGameView.myScore+=5;
                            myGameView.my.add(new AddScore(R.mipmap.five,enemy.getX(),enemy.getY(),myGameView));
                        }else if(enemy.resid==R.mipmap.ep_2){
                            myGameView.myScore+=4;
                            myGameView.my.add(new AddScore(R.mipmap.four,enemy.getX(),enemy.getY(),myGameView));
                        }else {
                            myGameView.myScore+=3;
                            myGameView.my.add(new AddScore(R.mipmap.three,enemy.getX(),enemy.getY(),myGameView));
                        }
                        myGameView.my.remove(enemy);
                    }
                    if(myInterface instanceof Enemy_2){
                        myGameView.myScore+=8;
                        myGameView.my.add(new AddScore(R.mipmap.eight,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof Enemy_3){
                        myGameView.myScore+=6;
                        myGameView.my.add(new AddScore(R.mipmap.six,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof Enemy_4){
                        myGameView.myScore+=10;
                        myGameView.my.add(new AddScore(R.mipmap.ten,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof Enemy_5){
                        myGameView.myScore+=9;
                        myGameView.my.add(new AddScore(R.mipmap.nine,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof Enemy_6){
                        myGameView.myScore+=7;
                        myGameView.my.add(new AddScore(R.mipmap.seven,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof Enemy_7){
                        myGameView.myScore+=8;
                        myGameView.my.add(new AddScore(R.mipmap.eight,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                    if(myInterface instanceof EnemyTest){
                        myGameView.myScore+=6;
                        myGameView.my.add(new AddScore(R.mipmap.six,myInterface.getX(),myInterface.getY(),myGameView));
                        myGameView.my.remove(myInterface);
                    }
                }
                for (MyInterface myInterface:(List<MyInterface>)myGameView.bullets.clone()){
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
                }
                count++;
                if(count==3){
                    index++;
                    if(index==explode.size()){
                        explodeBitmap=null;
                        myGameView.my.remove(this);
                        System.gc();
                    }
                    count=0;
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
}
