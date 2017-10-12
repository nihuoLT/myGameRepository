package com.example.newgame_1;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

public class JumpTest implements GameInterFace{
	public int x,y,width,height,speed=20,speedTime=0,count=0,stop=0,time=0,speedDrop=0;
	public int index=0,size=2,first=0;
	private Bitmap source,bitmap,dead,cache;
	private GameView gameView;
	public boolean IsJump=false,IsMove=false,IsStop=true,XdirectionFlag=true,IsAdd=true,
			YdirectionFlag=true,Isdo=false,state=true,speedflag=true,test=false,stateYflag=true;
	private List<Bitmap> sprites=new ArrayList<Bitmap>();
	public List<GameInterFace> now=new ArrayList<GameInterFace>();
	public JumpTest(int x,int y,GameView gameView){
		this.gameView=gameView;
		this.source=BitmapFactory.decodeStream(gameView.getResources()
				.openRawResource(R.drawable.allmali));
		this.dead=BitmapFactory.decodeStream(gameView.getResources()
				.openRawResource(R.drawable.mario13));
		this.x=x;
		this.width=source.getWidth()/6;
		this.height=source.getHeight();
		this.cache=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		this.y=y-height;
		this.speed=20;
		sprites.add(Bitmap.createBitmap(source,0,0,width-2,height));
		sprites.add(Bitmap.createBitmap(source,width,0,width,height));
		sprites.add(Bitmap.createBitmap(source,width*2,0,width,height));
		sprites.add(Bitmap.createBitmap(source,width*3+1,0,width-1,height));
		sprites.add(Bitmap.createBitmap(source,width*4,0,width,height));
		sprites.add(Bitmap.createBitmap(source,width*5,0,width,height));
	}
	public int getX() {
		return x;
	}

	public void setY(int y){
		this.y=y;
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

	@Override
	public Bitmap getBitmap(){
		if(!state){
			bitmap=dead;
			time++;
			if(time>=20){
				if(speedflag){
					speed=20;
					speedflag=false;
				}
				if(stateYflag){
	 				y-=speed;
	 				speed--;
	 				if(speed<=0){
	 					speed=0;
	 					stateYflag=false;
	 				}
	 			}else{
	 				speed++;
	 				y+=speed;
	 				if(y>gameView.ScreenHeight)
	 					gameView.mys.remove(this);
	 			}
			}
			
		}else{
			if(IsMove){
				if(!IsJump){
					bitmap=sprites.get(index);
					if(count==5){
			            index++;
			            if(index==size){
			                index=first;
			            }
			            count=0;
			        }
					count++;
					
					
					
				}
				stop++;
				if(stop==1){
					if(XdirectionFlag){
						if(x<=gameView.ScreenWidth-width)
						x+=5;
					}else{
						if(x>0)
						x-=5;
					}
					stop=0;
				}
				
			}else{
				if(XdirectionFlag){
					bitmap=sprites.get(0);
				}else{
					bitmap=sprites.get(3);
				}
			}
			if(Isdo){
				if(!IsJump){
					//if(speed>0){
//						if(speedTime>=4){
//							speed--;
//							speedTime=0;
//						}
//						speedTime++;
//				    	y+=speed;
					if(speedDrop<20)
						//if(speedTime>=2){
							speedDrop++;
							//speedTime=0;
						//}
						speedTime++;
				    	y+=speedDrop;
				    	if(y>gameView.ScreenHeight)
							gameView.mys.remove(this);
				    //} 
			    } 
		    }
		if(!IsJump){
			if(now.size()==1){
				//setY(now.get(0).getY()-height);
				if(now.get(0) instanceof BombA){
					
					BombA bombA=(BombA) now.get(0);
					switch (bombA.model) {
					case 1:
						setY(now.get(0).getY()-height);
						if(x>0)
						x-=bombA.speed;
						break;
					case 2:
						setY(now.get(0).getY()-height);
						if(x<gameView.ScreenWidth-width)
						x+=bombA.speed;
						break;
					}
				}else{
					Road road=(Road) now.get(0);
					if(road.model==1){
						Isdo=false;
						if(!IsJump)
							setY(now.get(0).getY()-height);
					}else{
						setY(now.get(0).getY()-height);
					}
				}
			}
		}
			if(IsStop){
	    		IsJump=false;
	    		speed=20;
				YdirectionFlag=true;
	    	}
		}
		return bitmap;
	}
	 public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
		    if( x1+w1>=x2+10&&y1+h1>=y2&&x1<=x2+w2-10&&y1+h1/2<=y2){
		    	return true;
		    }
          return false;
     }
	 public boolean isCollisionWithRect2(int x1, int y1, int w1, int h1,
             int x2,int y2, int w2, int h2) {
		    if( x1+w1>=x2&&y1+h1>=y2&&x1<=x2+w2&&y1<=y2+h2){
		    	return true;
		    }
          return false;
     }
     public void IsImpact(ArrayList<GameInterFace> bitmaps){
    	 if(state){
    		 if(IsJump){
    	 	    	if(XdirectionFlag){
    	 				bitmap=sprites.get(4);
    	 			}else{
    	 				bitmap=sprites.get(5);
    	 			}
    	 			if(YdirectionFlag){
    	 				y-=speed;
    	 				speed--;
    	 				if(speed<=0){
    	 					speed=0;
    	 					YdirectionFlag=false;
    	 				}
    	 			}else{
    	 				if(speed<=15){
    	 					speed++;
    	 				}
    	 				y+=speed;
    	 				if(y>gameView.ScreenHeight)
    	 					gameView.mys.remove(this);
    	 			}
    	 		}   
    		 
    	 for(GameInterFace obj:(ArrayList<GameInterFace>)bitmaps.clone()) {
    		 if(obj instanceof Road){
    			 Road road=(Road)obj;
    					 if(isCollisionWithRect(x,y,width,height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
            				 if(now.size()!=0)
            				    now.clear();
            				 if(now.size()==0){
            					  now.add(road); 
            				 }
                         }else{
                        	 switch (road.model) {
        					case 0:
        						if(now.size()==1){
        	       					 now.remove(road);
        	       				  }
        					case 2:
        						if(now.size()==1){
        	       					 now.remove(road);
        	       				  }
        						if(!IsJump)
        						Isdo=true;
        					case 3:
        						if(now.size()==1){
        	       					 now.remove(road);
        	       				  }
        						break;
        					}
                        	 
                         }  
    			 
    				
    		}
    	 }
    	 if(now.size()==1){
    		 if(isCollisionWithRect2(x,y,width,height,now.get(0).getX(),now.get(0).getY(),now.get(0).getWidth(),now.get(0).getHeight())){
        		 Isdo=false;
        		 speedDrop=0;
         		 IsStop=true;
             }
    	 }
    	}
    }
}
