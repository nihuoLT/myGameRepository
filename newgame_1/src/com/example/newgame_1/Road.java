package com.example.newgame_1;

import java.util.Random;
import android.graphics.Bitmap;

public class Road implements GameInterFace{

	public int x,y,width,height,model,speed=7,startY,
			startX,time=0,addtime=0,limittime=200;
    private Bitmap bitmap=null;
    private GameView gameView=null;
    public boolean Isdo=false,direction=true,state=true;
	public Road(Bitmap bitmap,int x,int y,int model,GameView gameView){
		this.bitmap=bitmap;
		this.x=x;
		this.y=y;
		this.startX=x;
		this.startY=y;
		this.model=model;
		this.gameView=gameView;
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
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
		switch (model) {
		case 1:
			
			if(direction){
				time++;
				if(time>=5){
					speed--;
					time=0;
				}
				y-=speed;
				if(speed<=0){
 					speed=0;
 					direction=false;
 				}
			}else{
				time++;
				if(time>=5){
					if(speed<7)
					speed++;
					time=0;
				}
				y+=speed;
				if(y>=startY){
					speed=7;
 					direction=true;
				}
			}
			break;

		case 2:
			if(direction){
				time++;
				if(time>=10){
					speed--;
					time=0;
				}
				x-=speed;
				if(speed<=0){
 					speed=7;
 					direction=false;
 				}
			}else{
				time++;
				if(time>=10){
					if(speed<7)
					speed++;
					time=0;
				}
				x+=speed;
				if(x>=startX){
					speed=7;
 					direction=true;
				}
			}
			break;
		case 3:
			if(direction){
				time++;
				if(time>=10){
					speed--;
					time=0;
				}
				x-=speed;
				if(speed<=0){
 					speed=7;
 					direction=false;
 				}
			}else{
				time++;
				if(time>=10){
					if(speed<7)
					speed++;
					time=0;
				}
				x+=speed;
				if(x>=startX){
					speed=7;
 					direction=true;
				}
			}
			BombA bombA=null;
			addtime++;
			if(addtime>=limittime){
				int var=gameView.random.nextInt(30);
				int nowmodel=0;
				int nowx=0;
				if(var<=10){
					nowmodel=1;
					nowx=x-gameView.paodan_1.getWidth();
				}else if(var>10&&var<=20){
					nowmodel=2;
					nowx=x+width;
				}else{
					nowmodel=3;
					nowx=x-gameView.paodan_1.getWidth();
				}
				bombA=new BombA(nowx, y+5, nowmodel, gameView);
				gameView.monsters.add(bombA);
				limittime=gameView.random.nextInt(280)+320;
				addtime=0;
			}
			break;
		}
		return bitmap;
	}
}
