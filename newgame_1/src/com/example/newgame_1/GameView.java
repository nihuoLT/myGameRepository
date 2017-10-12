package com.example.newgame_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
	private SurfaceHolder holder=null;
	private Bitmap GameCacheMap=null;//定义二级缓存bitmap
	public Bitmap konglu1,konglu2,konglu3,road_1,road_2,paodan_1,paodan_2,flyMonster,wugui,wuguidead;//定义资源图片
	public JumpTest jumpTest=null;//定义马里奥对象
	public Road roadb_1=null,roadb_2=null,road1=null,road2=null,road3=null,road4=null;//定义界面所有路对象
	private BgMap GameBg=null;//定义背景
	private float Alltime=0;
	public int ScreenWidth,ScreenHeight,StartY,AllScore=0;
	public int timeA=0,limmitA=200,timeS=0,limmitS=20;
	public int CountStar=0;
	public boolean IsAddStar=true,stopstate=false;
	private Paint paint=null,scorepaint=null;
	public Random random=new Random();
	private Thread thread1,thread2,thread3;
	private Typeface typeface=null;
	private boolean MainThreadFlag=true,IsAddMonsterA=true;;
	public ArrayList<GameInterFace> roads=new ArrayList<GameInterFace>();
	public ArrayList<GameInterFace> mys=new ArrayList<GameInterFace>();
	public ArrayList<GameInterFace> stars=new ArrayList<GameInterFace>();
	public ArrayList<GameInterFace> monsters=new ArrayList<GameInterFace>();
	public ArrayList<GameInterFace> explodes=new ArrayList<GameInterFace>();
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder=getHolder();
		holder.addCallback(this);
		typeface=Typeface.createFromAsset(context.getAssets(),"pty.TTF");//初始化字体
		DisplayMetrics dm = getResources().getDisplayMetrics();
        ScreenWidth=dm.widthPixels;
        ScreenHeight=dm.heightPixels;
        GameCacheMap=Bitmap.createBitmap(ScreenWidth,ScreenHeight,Config.ARGB_8888);//初始化二级缓存bitmap
        GameBg=new BgMap(this);
        //加载资源图片
        road_1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.dilu_1));
        paodan_1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.paodan_1));
        road_2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.dilu_2));
        konglu2=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.lu_2));
        konglu1=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.lu_1));
        konglu3=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.lu_3));
        flyMonster=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.monster_1));
        wugui=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.allwugui));
        wuguidead=BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.deadwg));
        //初始化画笔
        scorepaint=new Paint();
        paint=new Paint();
        init();
        //初始跳跳蘑菇并将它加入绘制容器
        FlayMonster fMonster=new FlayMonster(flyMonster, 850,100,1, this);
        monsters.add(fMonster);
	}
    public void init(){
    	//设置画笔的类型属性等
    	scorepaint.setTypeface(typeface);
        scorepaint.setColor(Color.rgb(180, 100,220));
        scorepaint.setShadowLayer(8,3,3,Color.CYAN);
        scorepaint.setTextSkewX(-0.5f);
        scorepaint.setTextSize(60);
      //初始化各个路对象
        roadb_1=new Road(road_2,0, ScreenHeight-road_2.getHeight(),0,this);
        roadb_2=new Road(road_2,ScreenWidth-road_2.getWidth(), ScreenHeight-road_2.getHeight(),0,this);
        road1=new Road(konglu2,400, 200,1,this);
        road2=new Road(konglu2,350, 400,2,this);
        road3=new Road(konglu1,800, 470,0,this);
        road4=new Road(konglu3,1050, 150,0,this);
      //初始化特殊路-炮塔对象
        Bitmap paota=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.paota));
        Road paotaroad=new Road(paota,500, 400-paota.getHeight(),3,this);
      //初始化马里奥对象
        jumpTest=new JumpTest(100,ScreenHeight*3/4,this);
      //将各个对象放入各绘制容器
        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(roadb_1);
        roads.add(roadb_2);
        roads.add(paotaroad);
        mys.add(jumpTest);
    }
    public void MainDraw(){
    	Alltime+=0.03f;
    	Canvas c=new Canvas(GameCacheMap);//获取缓存bitmap的画布
    	c.drawBitmap(GameBg.getBitmap(), GameBg.getX(),GameBg.getY(), paint);
    	for(GameInterFace gameInterFace:(List<GameInterFace>)stars.clone()){
    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), paint);
        }
    	c.drawText("时间: "+String.format("%.2f", Alltime), 20,60, scorepaint);
    	c.drawText("金币: ", ScreenWidth/2-90,60, scorepaint);
    	c.drawText("分数: "+AllScore, ScreenWidth-300,60, scorepaint);
//    	c.drawText("IsJump: "+jumpTest.IsJump, ScreenWidth-300,80, scorepaint);
//    	c.drawText("size: "+jumpTest.now.size(), ScreenWidth-300,120, scorepaint);
    	for(GameInterFace gameInterFace:(List<GameInterFace>)roads.clone()){
    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), paint);
        }
    	for(GameInterFace gameInterFace:(List<GameInterFace>)mys.clone()){
    		if( gameInterFace instanceof JumpTest){
    			JumpTest my=(JumpTest)gameInterFace;
    			if(my.state)
    			my.IsImpact(roads);
    		}
    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), paint);
        }
    	for(GameInterFace gameInterFace:(List<GameInterFace>)explodes.clone()){
    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), paint);
        }
    	for(GameInterFace gameInterFace:(List<GameInterFace>)monsters.clone()){
    		if( gameInterFace instanceof Monster){
    			Monster monster=(Monster)gameInterFace;
    			if(jumpTest.state)
    			monster.IsImpact(jumpTest);
    			monster.IsImpactRoad(roads);
    			monster.IsImpactTortoise(monsters);
    		}
    		if(gameInterFace instanceof BombA){
    			BombA bombA=(BombA)gameInterFace;
    			if(jumpTest.state)
    			bombA.IsImpact(jumpTest);
    		}
    		if(gameInterFace instanceof FlayMonster){
    			FlayMonster flayMonster=(FlayMonster)gameInterFace;
    			if(jumpTest.state)
    			    flayMonster.IsImpact(jumpTest);
    			flayMonster.IsImpactRoad(roads);
    		}
    		if(gameInterFace instanceof Tortoise){
    			Tortoise tortoise=(Tortoise)gameInterFace;
    			if(jumpTest.state)
    				tortoise.IsImpact(jumpTest);
    			tortoise.IsImpactRoad(roads);
    			tortoise.IsImpactTortoise(monsters);
    		}
    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), paint);
        }
    	Canvas canvas=null;
    	try {
            if(holder!=null){
                canvas=holder.lockCanvas();
                canvas.drawBitmap(GameCacheMap,0,0,paint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas!=null)
                 holder.unlockCanvasAndPost(canvas);
        }
    }
    public void stop(){
        stopstate=true;
    }
    public void start(){
        stopstate=false;
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    }
	public void run(){
		while(MainThreadFlag){
			while(stopstate){
                try {
                    Thread.sleep(100000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    		try {
    			MainDraw();
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
	}
	public void surfaceCreated(SurfaceHolder holder) {
		thread1=new Thread(this);
		thread1.start();
		thread2=new Thread(new DetectionRunnable());
		thread2.start();
		thread3=new Thread(new AddSpriteThread(this));
		thread3.start();
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}
	public void surfaceDestroyed(SurfaceHolder holder) {
	   MainThreadFlag=false;
	   this.holder.removeCallback(this);
    }
	class DetectionRunnable implements Runnable{

		
		@Override
		public void run() {
			 while (MainThreadFlag){
				 while(stopstate){
		                try {
		                    Thread.sleep(100000);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
				 try {
					Detection(jumpTest);
					Detection2(monsters);
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
		}
		public void Detection(GameInterFace face){
			 JumpTest my=null;
			 ArrayList<GameInterFace> list=null;
			 Road road=null;
			 if(face instanceof JumpTest){
				 my =(JumpTest)face;
				 list=(ArrayList<GameInterFace>)my.now;
			 }
			 if(list!=null){
				 if(list.size()==1){
					 for(GameInterFace gFace:(ArrayList<GameInterFace>)list.clone()){
						 if(gFace instanceof Road){
							 road=(Road)gFace;
						 }else{
							 road=null;
						 }
					 }
					 if(road!=null){
						 if(isCollisionWithRect2(my.x,my.y,my.width,my.height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
		    				 switch (road.model) {
		 					   case 1:
		 						  if(my.now.size()==1){
		 							 my.now.remove(road);
		        				   }
		 						break;
		    				 }
		    				 my.Isdo=true;
						 }
					 }
				 }
			 }
			 
		}
		 public boolean isCollisionWithRect(int x1, int y1, int w1, int h1,
	             int x2,int y2, int w2, int h2) {
			    if( x1+w1>=x2&&y1+h1>=y2&&x1<=x2+w2&&y1<=y2+h2){
			    	return true;
			    }
	          return false;
	     }
		public void Detection2(ArrayList<GameInterFace> bitmaps){
			Monster monster=null;
			Tortoise tortoise=null;
			ArrayList<GameInterFace> list=null;
			Road road=null;
			for(GameInterFace face:(ArrayList<GameInterFace>)bitmaps.clone()){
				if(face instanceof Monster){
					monster =(Monster)face;
					list=(ArrayList<GameInterFace>)monster.now;
				}
				else if(face instanceof Tortoise){
					tortoise =(Tortoise)face;
					list=(ArrayList<GameInterFace>)tortoise.now;
				}
				if(list!=null){
					if(list.size()==1){
						 for(GameInterFace gFace:(ArrayList<GameInterFace>)list.clone()){
							 if(gFace instanceof Road){
								 road=(Road)gFace;
							 }else{
								 road=null;
							 }
						 }
						 if(road!=null){
							 if(monster!=null){
								 if(isCollisionWithRect2(monster.x,monster.y,monster.width,monster.height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
					   				 switch (road.model) {
										   case 1:
											  if(monster.now.size()==1){
												  monster.now.remove(road);
					       				   }
											break;
					   				 }
					   				monster.IsOnRoad=false;
								 }
							 }
							 if(tortoise!=null){
								 if(isCollisionWithRect2(tortoise.x,tortoise.y,tortoise.width,tortoise.height,road.getX(),road.getY(),road.getWidth(),road.getHeight())){
					   				 switch (road.model) {
										   case 1:
											  if(tortoise.now.size()==1){
												  tortoise.now.remove(road);
					       				   }
											break;
					   				 }
					   				tortoise.IsOnRoad=false;
								 }
							 }
							 
						 }
					 }
				}
				 
			}
			 
		}
		public boolean isCollisionWithRect2(int x1, int y1, int w1, int h1,
		            int x2,int y2, int w2, int h2) {
		    if( y1+h1==y2){
				 if(x1+w1<x2+10||x1>x2+w2-10){
				     return true;
				 }
			}
		    return false;
		}
	}
	class AddSpriteThread implements Runnable{
        private GameView gameView;
		public AddSpriteThread(GameView gameView){
			this.gameView=gameView;
		}
		@Override
		public void run() {
			 while (MainThreadFlag){
				 while(stopstate){
		                try {
		                    Thread.sleep(100000);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
				 try {
					 addSprite();
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
		}
		public void addSprite(){
			timeA++;
			if(timeA>=limmitA){
				if(IsAddMonsterA){
				    Tortoise tortoise=new Tortoise(wugui, wuguidead,600, 60,1,2, gameView);
				    monsters.add(tortoise);
					Monster monster=null;
					limmitA=random.nextInt(220)+280;
					//int varA=random.nextInt(30);
					int varB=random.nextInt(40);
					int x=0,y=0,model=0;
					x=random.nextInt(konglu2.getWidth())+380;
					y=200-random.nextInt(200);
					if(varB<=10){
						model=1;
					}else if(varB>10&&varB<=20){
						model=2;
					}else if(varB>20&&varB<=30){
						model=3;
					}else{
						model=4;
					}
					monster	=new Monster(x, y, model, gameView);
					monsters.add(monster);
					timeA=0;
				}
			}
			if(IsAddStar){
				timeS++;
				if(timeS>=limmitS){
					 limmitS=random.nextInt(100)+100;
					 int x=random.nextInt(ScreenWidth-200)+100;
					 Star star=new Star(x, -100, 1, gameView);
					 stars.add(star);
					 CountStar++;
					 if(CountStar>=10){
						 IsAddStar=false;
					 }
				     timeS=0;
				}
			}
			
		}
	}
}
