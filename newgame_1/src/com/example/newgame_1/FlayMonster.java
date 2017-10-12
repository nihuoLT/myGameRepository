package com.example.newgame_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FlayMonster implements GameInterFace{
    private int x,y,model,width,height,speedY=15,speed=0,index=0,count=0;
    private Bitmap bitmap;
    private GameView gameView;
    private boolean directionY=true,directionX=true,IsDo=false,Isdo=false,IsJump=false,IsDead=false,IsAdd=true;
    private List<Bitmap> sprites=new ArrayList<Bitmap>();
	public FlayMonster(Bitmap bitmap,int x,int y,int model,GameView gameView){
    	this.x=x;
		this.y=y;
		this.model=model;
		this.gameView=gameView;
		this.bitmap=bitmap;
		switch (model) {
		case 1:
			this.width=bitmap.getWidth()/5;
			this.height=bitmap.getHeight();	
			sprites.add(Bitmap.createBitmap(bitmap,0,0,width,height));
			sprites.add(Bitmap.createBitmap(bitmap,width,0,width,height));
			sprites.add(Bitmap.createBitmap(bitmap,2*width,0,width,height));
			sprites.add(Bitmap.createBitmap(bitmap,3*width,0,width,height));
			sprites.add(Bitmap.createBitmap(bitmap,4*width,0,width,height));
			break;
		}
		
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
		if(y>=gameView.ScreenHeight||x>=gameView.ScreenWidth||x<=-width)
			gameView.monsters.remove(this);
		bitmap=sprites.get(index);
		if(count==8){
            index++;
            if(index==sprites.size()-1){
                index=0;
            }
            count=0;
        }
		count++;
		if(IsJump){
//			if(directionX){
//				x+=3;
//				if(x>gameView.ScreenWidth-width)
//					directionX=false;
//			}else{
//				x-=3;
//				if(x<0)
//					directionX=true;
//			}	
			if(directionY){
				speedY--;
				y-=speedY;
				if(speedY<0){
					speedY=0;
					directionY=false;
				}
			}else{
				if(speedY<15)
				   speedY++;
				y+=speedY;
				
			}
		}
		if(IsDo){
			if(!IsJump){
				if(speed<15)
					speed++;
				y+=speed;
				if(y>=gameView.ScreenHeight)
				gameView.monsters.remove(this);
			}
			
		}
		if(IsDead){
			if(IsAdd){
				int varB=gameView.random.nextInt(40);
				int model=0;
				if(varB<=10){
					model=1;
				}else if(varB>10&&varB<=20){
					model=2;
				}else if(varB>20&&varB<=30){
					model=3;
				}else{
					model=4;
				}
				Monster monster	=new Monster(x, y+20, model, gameView);
				gameView.monsters.add(monster);
				IsAdd=false;
			}
			  gameView.monsters.remove(this);
		}
		return bitmap;
	}
	 public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
		    if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
		    	return true;
		    }
          return false;
     }
	 public int isCollisionWithRect2(int x1, int y1, int w1, int h1,
	            int x2,int y2, int w2, int h2) {
			if(y1+h1<y2+h2/2){
				Isdo=true;
			}else{
				Isdo=false;
				if( x1+w1>=x2+10&&y1+h1>=y2&&x1<=x2+w2-10&&y1<=y2+h2){
			    	return 2;
			    }
			}
			if(Isdo){
				if( x1+w1>=x2+15&&y1+h1>=y2&&x1<=x2+w2-15&&y1<=y2+h2){
			    	return 1;
			    }
			}
			 return 0;
	    }
	 public void IsImpact(JumpTest jTest){
 		switch (isCollisionWithRect2(jTest.getX(),jTest.getY(),jTest.getWidth(),jTest.getHeight(),x,y,width,height)) {
			case 1:
				if(!jTest.YdirectionFlag){
					if(!IsDead){
						jTest.YdirectionFlag=true;
						jTest.speed=10;
					}
				    IsDead=true;
				}
				break;
			case 2:
				if(!IsDead)
				jTest.state=false;
				break;

			}  
 	
}
	 public void IsImpactRoad(ArrayList<GameInterFace> bitmaps){
	     for(GameInterFace obj:(ArrayList<GameInterFace>)bitmaps.clone()) {
	   		 if(obj instanceof Road){
	   			 Road road=(Road)obj;
	   			 //if(road.model!=3){
	   				if(isCollisionWithRect(x,y,width,height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
	   					IsJump=true;
	   					speedY=15;
	   					directionY=true;
	   				}else{
	   					IsDo=true;
	   				}
	   			// }
	   		 }
	     }
	 }
}
