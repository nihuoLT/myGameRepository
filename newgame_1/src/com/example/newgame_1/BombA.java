package com.example.newgame_1;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BombA implements GameInterFace{
	public int x,y,width,height,model,speed,deadspeed=12;
	private Bitmap bitmap;
	private GameView gameView;
	private boolean IsDoT=false,IsDoB=false,IsDo=false,state=true,deaddirection=true;
	public BombA(int x,int y,int model,GameView gameView){
		this.gameView=gameView;
		switch (model) {
		case 1:
			this.bitmap=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.paodan_1));
			break;
		case 2:
			this.bitmap=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.paodan_2));
			break;
		case 3:
			this.bitmap=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.paodan_3));
			break;
		}
		
		this.x=x;
		this.y=y;
		this.model=model;
		this.width=bitmap.getWidth();
		this.height=bitmap.getHeight();
		//this.speed=random.nextInt(6)+4;
		this.speed=gameView.random.nextInt(5)+1;
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
			if(state){
				x-=speed;
				if(x<=-width)
					gameView.monsters.remove(this);
				
			}else{
				x-=5;
				if(deaddirection){
					deadspeed--;
					y-=deadspeed;
					if(deadspeed<=0){
						deadspeed=0;
						deaddirection=false;
					}
				}else{
					deadspeed++;
					y+=deadspeed;
					if(y>=gameView.ScreenHeight)
						gameView.monsters.remove(this);
				}
				
			}
			break;

		case 2:
			if(state){
				x+=speed;
				if(x>=gameView.ScreenWidth)
					gameView.monsters.remove(this);
			}else{
				x+=5;
				if(deaddirection){
					deadspeed--;
					y-=deadspeed;
					if(deadspeed<=0){
						deadspeed=0;
						deaddirection=false;
					}
				}else{
					deadspeed++;
					y+=deadspeed;
					if(y>=gameView.ScreenHeight)
						gameView.monsters.remove(this);
				}
			}
			
			break;
		case 3:
			if(state){
				x-=speed;
				if(x>=gameView.ScreenWidth)
					gameView.monsters.remove(this);
			}else{
				x-=5;
				if(deaddirection){
					deadspeed--;
					y-=deadspeed;
					if(deadspeed<=0){
						deadspeed=0;
						deaddirection=false;
					}
				}else{
					deadspeed++;
					y+=deadspeed;
					if(y>=gameView.ScreenHeight)
						gameView.monsters.remove(this);
				}
			}
			
			break;
		}
		return bitmap;
	}
	 public int isCollisionWithRect(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
		 if(y1+h1<y2){
			IsDoT=true;
			IsDoB=false;
		 }else if(y1>y2+h2){
			 IsDoB=true;
			 IsDoT=false;
		 } 
		 else if(x1+w1<x2||x1>x2+w2){
			 if(y1+h1!=y2){
				 IsDoB=false;
				 IsDoT=false; 
				 IsDo=true;
			 }
			 
		 }
		 
		 if(IsDoT){
			 if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
			    	return 1;
			   }  
		 }
		 if(IsDoB){
			 if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
			    	return 2;
			   }  
		 }
		 if(IsDo){
			 if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
			    	return 3;
			   }  
		 }
		    return 0;
     }
	 public boolean isCollisionWithRect2(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
	
		  if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
			    	return true;
			   }  
		 return false;
	 }
	public void IsImpact(JumpTest jTest){
		switch (model) {
		case 1:
			switch (isCollisionWithRect(jTest.getX(),jTest.getY(),jTest.getWidth(),jTest.getHeight(),x,y,width,height)) {

			case 0:
				if(jTest.now.size()==1){
					jTest.now.remove(this);
				}
				break;
			case 1:
				if(jTest.now.size()!=0){
					if(jTest.now.get(0) instanceof Road){
						Road road=(Road) jTest.now.get(0);
						if(road.model!=3&&state)
						jTest.state=false;
					}else{
						jTest.now.clear();
						}
					}
					 if(jTest.now.size()==0){
						 if(state)
						 jTest.now.add(this); 
					 }
				break;
			case 2:
				state=false;
				if(jTest.now.size()==1){
					jTest.now.remove(this);
				}
				break;
			case 3:
				jTest.state=false;
				break;
			}
			break;

		case 2:
			switch (isCollisionWithRect(jTest.getX(),jTest.getY(),jTest.getWidth(),jTest.getHeight(),x,y,width,height)) {

			case 0:
				if(jTest.now.size()==1){
					jTest.now.remove(this);
				}
				break;
			case 1:
				if(jTest.now.size()!=0){
					if(jTest.now.get(0) instanceof Road){
						Road road=(Road) jTest.now.get(0);
						if(road.model!=3&&state)
						jTest.state=false;
					}else{
						jTest.now.clear();
						}
					}
					 if(jTest.now.size()==0){
						 if(state)
						 jTest.now.add(this); 
					 }
				break;
			case 2:
				state=false;
				if(jTest.now.size()==1){
					jTest.now.remove(this);
				}
				break;
			case 3:
				jTest.state=false;
				break;
			}
			break;
		case 3:
			if(isCollisionWithRect2(jTest.getX(),jTest.getY(),jTest.getWidth(),jTest.getHeight(),x,y,width,height)){
				jTest.state=false;
			}
			break;
		}
			
    }
}
