package com.example.administrator.mylovegame;

import android.graphics.Bitmap;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Doctor implements GameImage{
    public GameView gameView;
    public Bitmap doctorBitmap;
    public int x=0,y=0,width=0,height=0,speedX=0,speedY=0;
    public int index=0,count=0,Ycount=0;
    public boolean Xdirection,Ydirection=true,Isremove=false;
    public Random random=new Random();
    public List<Bitmap> doctor=new ArrayList<Bitmap>();
    public Doctor(Bitmap doctorBitmap,int x, int y, int speedX, int speedY,GameView gameView){
        this.gameView=gameView;
        this.x=x;
        this.y=y;
        this.doctorBitmap=doctorBitmap;
        this.width=doctorBitmap.getWidth()/5;
        this.height=doctorBitmap.getHeight()/2;
        this.speedX=speedX;
        this.speedY=speedY;
        doctor.add(Bitmap.createBitmap(doctorBitmap, 0, 0, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*1, 0, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*2, 0, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*3, 0, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*4, 0, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, 0,doctorBitmap.getHeight()/2, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*1,doctorBitmap.getHeight()/2, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*2,doctorBitmap.getHeight()/2, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*3,doctorBitmap.getHeight()/2, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
        doctor.add(Bitmap.createBitmap(doctorBitmap, (doctorBitmap.getWidth()/5)*4,doctorBitmap.getHeight()/2, doctorBitmap.getWidth()/5, doctorBitmap.getHeight()/2));
    }
    @Override
    public Bitmap getBitmap() {
        Bitmap bitmap=doctor.get(index);
        if(Isremove==true){
            gameView.doctors.remove(this);
        }else {
            if(count==3){
                index++;
                if(index==doctor.size()){
                    index=0;
                }
                count=0;
            }
            if (Ycount>=40+random.nextInt(50)){
                if (Ydirection==true){
                    Ydirection=false;
                    Ycount=0;
                }else {
                    Ydirection=true;
                    Ycount=0;
                }
            }
            count++;
            Ycount++;
            if(Xdirection==true){
                x-=speedX;
                if(x<=-width){
                    gameView.doctors.remove(this);
                }
                if(Ydirection==true){
                    y-=speedY;
                }else {
                    y+=speedY;
                }
            }else {
                x+=speedX;
                if(x>=gameView.ScreenWidth){
                    gameView.doctors.remove(this);
                }
                if(Ydirection==true){
                    y-=speedY;
                }else {
                    y+=speedY;
                }
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
