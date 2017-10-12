package com.example.newgame_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Monster implements GameInterFace{
	public int x,y,startX=0,mode,width,height,count=0,index=0,limmitW=0,time=0,speed=0,state=1,dropSpeed=10;
	private Bitmap bitmap;
	private GameView gameView;
	public boolean direction=true,Isdo=false,IsOnRoad=false,IsTest=false,dropFlag,dropYdirection=true;
	private List<Bitmap> sprites=new ArrayList<Bitmap>();
	public List<GameInterFace> now=new ArrayList<GameInterFace>();
	public Monster(int x,int y,int mode,GameView gameView){
		this.x=x;
		this.y=y;
		this.mode=mode;
		this.gameView=gameView;
		this.bitmap=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.monster_2));
		this.width=bitmap.getWidth()/3;
		this.height=bitmap.getHeight();
		if(gameView.random.nextInt(10)>=5){
			direction=true;
		}else{
			direction=false;
		}
		sprites.add(Bitmap.createBitmap(bitmap,0,0,width,height));
		sprites.add(Bitmap.createBitmap(bitmap,width,0,width,height));
		sprites.add(Bitmap.createBitmap(bitmap,2*width,0,width,height));
		
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public void setY(int y){
		this.y=y;
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
		if(!IsOnRoad&&state!=3){
			if(speed<10){
				speed++;
			}
			y+=speed;
			if(y>=gameView.ScreenHeight)
			gameView.monsters.remove(this);
		}
		if(now.size()==1){
			Road road=(Road)now.get(0);
			if(state!=3);
			setY(road.getY()-height);
			if(mode==1){
				startX=road.getX();
				limmitW=road.getWidth();
			}
		}
		if(state==1){
		switch (mode) {
		case 1:
			if(direction){
				x+=1;
				if(x+width>=startX+limmitW){
					direction=false;
				}
			}else{
				x-=1;
				if(x<=startX){
					direction=true;
				}
			}
			break;
		case 2:
			x+=2;
			if(x>=gameView.ScreenWidth)
				gameView.monsters.remove(this);
			break;
		case 3:
			x-=2;
			if(x<=-width)
				gameView.monsters.remove(this);
			break;
		case 4:
			if(direction){
				x-=3;
				if(x<=0){
					direction=false;
				}
			}else{
				x+=3;
				if(x>=gameView.ScreenWidth-width){
					direction=true;
				}
			}
			
			break;
			
		}
		
			bitmap=sprites.get(index);
			if(count==8){
	            index++;
	            if(index==sprites.size()-1){
	                index=0;
	            }
	            count=0;
	        }
			count++;
		}else if(state==2){
			bitmap=sprites.get(sprites.size()-1);
			time++;
			if(time>=20){
				gameView.monsters.remove(this);
				gameView.explodes.add(new Explode(x, y, 2, gameView));
				AddScore score=new AddScore(x, y, mode, gameView);
				gameView.mys.add(score);
				switch (mode) {
				case 1:
					gameView.AllScore+=5;
					break;
				case 2:
					gameView.AllScore+=3;
					break;
				case 3:
					gameView.AllScore+=3;
					break;
				case 4:
					gameView.AllScore+=4;
					break;
				}
				
			}
		}else{
			if(dropFlag){
				x+=5;
			}else{
				x-=5;
			}
			if(dropYdirection){
				if(dropSpeed>0){
					dropSpeed--;
				}else{
					dropYdirection=false;	
				}
				y-=dropSpeed;
			}else{
				if(dropSpeed<20)
					dropSpeed++;
				y+=dropSpeed;
			}
			if(x<=-width||x>=gameView.ScreenWidth||y>=gameView.ScreenHeight)
				gameView.monsters.remove(this);
		}
		return bitmap;
	}
	 public boolean isCollisionWithRect3(int x1, int y1, int w1, int h1,
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
	 public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
		    if( x1+w1>=x2+10&&y1+h1>=y2&&x1<=x2+w2-10&&y1+h1*3/4<=y2){
		    	return true;
		    }
          return false;
     }
    public void IsImpact(JumpTest jTest){
    		switch (isCollisionWithRect2(jTest.getX(),jTest.getY(),jTest.getWidth(),jTest.getHeight(),x,y,width,height)) {
			case 1:
				if(state==1){
					//if(IsOnRoad){
						jTest.YdirectionFlag=true;
						jTest.speed=10;
						state=2;
					//}
				}
				break;
			case 2:
				if(state==1)
				jTest.state=false;
				break;

			}  
    	
   }
    public void IsImpactTortoise(ArrayList<GameInterFace> bitmaps){
    	for(GameInterFace obj:(ArrayList<GameInterFace>)bitmaps.clone()) {
    		if(obj instanceof Tortoise){
    			Tortoise tortoise=(Tortoise)obj;
    			if(tortoise.state==2&&tortoise.IsMove){

    				if(isCollisionWithRect3(x,y,width,height,tortoise.getX(),tortoise.getY(),tortoise.getWidth(),tortoise.getHeight())){
    					state=3;
    				}else{
    					if(state==1){
    						if(tortoise.getX()+tortoise.width/2<x+width/2){
            					dropFlag=true;
            				}else{
            					dropFlag=false;
            				}
    					}
    				}
    			}
    		}
    	}
	}
    public void IsImpactRoad(ArrayList<GameInterFace> bitmaps){
    	for(GameInterFace obj:(ArrayList<GameInterFace>)bitmaps.clone()) {
   		 if(obj instanceof Road){
   			 Road road=(Road)obj;
   			 if(road.model!=3){
   				if(isCollisionWithRect(x,y,width,height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
   					 if(now.size()!=0)
       				    now.clear();
       				 if(now.size()==0){
       					 if(state!=3)
       					 now.add(road);
       				 }
 
                   }else{
                  	 switch (road.model) {
   					case 0:
   						if(now.size()==1){
   	       					 now.remove(road);
   	       				  }
   						break;
   					case 2:
   						if(now.size()==1){
   	       					 now.remove(road);
   	       				  }
   						IsOnRoad=false;
   						break;
   					}
                  	 
                   }
   			 }
   			 
   				
   		 }
   	 }
    if(now.size()==1){
   		 if(isCollisionWithRect3(x,y,width,height,now.get(0).getX(),now.get(0).getY(),now.get(0).getWidth(),now.get(0).getHeight())){
       		 IsOnRoad=true;	
          }
   	 }
    }
}
