package com.example.newgame_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Peach implements GameInterFace{

	private int x,y,startY,width,height,time=0,limmit=200,maxspeed=0,speed=0,speedtime=0;
	private Bitmap bitmap=null;
	private StartBgView startBgView;
	public Peach(int maxspeed,StartBgView startBgView){
		this.startBgView=startBgView;
		this.maxspeed=maxspeed;
		this.startY=startBgView.ScreenHeight;
		if(startBgView.random.nextInt(70)<=10){
			bitmap=startBgView.lan;
		}else if(startBgView.random.nextInt(70)>10&&startBgView.random.nextInt(70)<=20){
			bitmap=startBgView.yue;
		}else if(startBgView.random.nextInt(70)>20&&startBgView.random.nextInt(70)<=30){
			bitmap=startBgView.peach1;
		}else if(startBgView.random.nextInt(70)>30&&startBgView.random.nextInt(70)<=40){
			bitmap=startBgView.peach2;
		}else if(startBgView.random.nextInt(70)>40&&startBgView.random.nextInt(70)<=50){
			bitmap=startBgView.peach3;
		}else if(startBgView.random.nextInt(70)>50&&startBgView.random.nextInt(70)<=60){
			bitmap=startBgView.peach4;
		}else{
			bitmap=startBgView.peach5;
		}
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
		this.x=startBgView.random.nextInt(startBgView.ScreenWidth-width);
		this.y=startY;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Bitmap getBitmap() {
		speedtime++;
		if(speedtime>=50){
			if(speed<maxspeed)
				speed++;
		}
		y-=speed;
		if(y<=-height){
			time++;
			if(time>=limmit){
				limmit=startBgView.random.nextInt(150)+150;
				if(startBgView.random.nextInt(70)<=10){
					bitmap=startBgView.lan;
				}else if(startBgView.random.nextInt(70)>10&&startBgView.random.nextInt(70)<=20){
					bitmap=startBgView.yue;
				}else if(startBgView.random.nextInt(70)>20&&startBgView.random.nextInt(70)<=30){
					bitmap=startBgView.peach1;
				}else if(startBgView.random.nextInt(70)>30&&startBgView.random.nextInt(70)<=40){
					bitmap=startBgView.peach2;
				}else if(startBgView.random.nextInt(70)>40&&startBgView.random.nextInt(70)<=50){
					bitmap=startBgView.peach3;
				}else if(startBgView.random.nextInt(70)>50&&startBgView.random.nextInt(70)<=60){
					bitmap=startBgView.peach4;
				}else{
					bitmap=startBgView.peach5;
				}
				this.x=startBgView.random.nextInt(startBgView.ScreenWidth-width);
				this.y=startY;
				time=0;
			}
		}
		return bitmap;
	}

}
