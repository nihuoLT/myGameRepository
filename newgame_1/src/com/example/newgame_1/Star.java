package com.example.newgame_1;



import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;

public class Star implements GameInterFace{
	private int x,y,model,width,height,degrees=0,speedX=0,speedY=0,time=0,limmittime=50;
	private GameView gameView;
	private Bitmap cache,bitmap;
	private Canvas canvas;
	private boolean directionX=true,IsStop=false;
    public Star(int x,int y,int model,GameView gameView){
    	this.x=x;
    	this.y=y;
    	this.model=model;
    	this.gameView=gameView;
    	switch (model) {
		case 1:
			if(gameView.random.nextInt(30)<=10){
				bitmap=BitmapFactory.decodeStream(gameView.getResources()
						.openRawResource(R.drawable.start_b));
			}else if(gameView.random.nextInt(30)>10&&gameView.random.nextInt(30)<=20){
				bitmap=BitmapFactory.decodeStream(gameView.getResources()
						.openRawResource(R.drawable.start_g));
			}else{
				bitmap=BitmapFactory.decodeStream(gameView.getResources()
						.openRawResource(R.drawable.start_my));
			}
			break;
		case 2:
			bitmap=BitmapFactory.decodeStream(gameView.getResources()
					.openRawResource(R.drawable.start_r));
			break;
		}
    	this.width=bitmap.getWidth();
    	this.height=bitmap.getHeight();
    	if(gameView.random.nextInt(10)>=5){
    		directionX=true;
    	}else{
    		directionX=false;
    	}
    	speedX=gameView.random.nextInt(4);
    	cache=Bitmap.createBitmap(width*3/2,height*3/2,Config.ARGB_8888);
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
			if(!IsStop){
				if(speedY<10)
					speedY++;
					y+=speedY;
					if(directionX){
						x-=speedX;
					}else{
						x+=speedX;
					}
					
					degrees+=3;
					if(degrees>=360){
						degrees=0;
					}
					canvas=new Canvas(cache);
					canvas.save();
					canvas.rotate(degrees, width*3/4, height*3/4);
					canvas.translate(width/4, height/4);
					canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
					canvas.drawBitmap(bitmap, 0, 0,null);
					canvas.restore();
			}
			if(x>=gameView.ScreenWidth||x<=-width||y>=gameView.ScreenHeight){
				IsStop=true;
				time++;
				if(time>=limmittime){
					if(gameView.random.nextInt(30)<=10){
						bitmap=BitmapFactory.decodeStream(gameView.getResources()
								.openRawResource(R.drawable.start_b));
					}else if(gameView.random.nextInt(30)>10&&gameView.random.nextInt(30)<=20){
						bitmap=BitmapFactory.decodeStream(gameView.getResources()
								.openRawResource(R.drawable.start_g));
					}else{
						bitmap=BitmapFactory.decodeStream(gameView.getResources()
								.openRawResource(R.drawable.start_my));
					}
					limmittime=gameView.random.nextInt(80)+80;
					x=gameView.random.nextInt(gameView.ScreenWidth-200)+100;
					y=-100;
					IsStop=false;
					time=0;
				}
				
			}
			break;
		}
		return cache;
	}

}
