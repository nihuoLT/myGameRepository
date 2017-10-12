package com.example.newgame_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class StartBgView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

	private SurfaceHolder holder=null;
	public Bitmap GameCacheMap=null,startbg,lan,yue,peach1,peach2,peach3,peach4,peach5,raindrop;
	public int ScreenWidth,ScreenHeight,x,y,r=100,time=0,raintime=0;
	public Random random=new Random();
	private boolean MainFlag=true,FlagR=true;
	private Paint paint1=new Paint();
	private StartActivity startActivity=null;
	//private int []colors={Color.GREEN, Color.YELLOW,Color.CYAN,Color.GRAY};
	//private float[] points={0,200,400,600};
	public ArrayList<GameInterFace> peachs=new ArrayList<GameInterFace>();
	public ArrayList<GameInterFaceTwo> riandrops=new ArrayList<GameInterFaceTwo>();
	public StartBgView(Context context, AttributeSet attrs) {
		super(context, attrs);
		startActivity=(StartActivity)context;
		holder=this.getHolder();
		holder.addCallback(this);
		DisplayMetrics dm = getResources().getDisplayMetrics();
        ScreenWidth=dm.widthPixels;
        ScreenHeight=dm.heightPixels;
        x=ScreenWidth/2;
        y=ScreenHeight/2;
        GameCacheMap=Bitmap.createBitmap(ScreenWidth,ScreenHeight,Config.ARGB_8888);
        startbg=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.startbg));
        startbg=condenseBgMap(startbg);
        init();
//		paint1.setColor(Color.BLUE);
//		paint1.setAntiAlias(true);
//		paint1.setStyle(Style.STROKE);
//		paint1.setStrokeWidth(20);
//		LinearGradient linearGradient = new LinearGradient(0, 0, ScreenWidth, ScreenHeight,colors,points,TileMode.MIRROR);
//		paint1.setShader(linearGradient);
		//paint1.setShadowLayer(r+20,x,y,Color.CYAN);
		//paint1.set
//		paint1.setTextSkewX(-0.5f);
//		paint1.setTextSize(45);
	}
	 private void init() {
		lan=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.lan));
		yue=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.yue));
		peach1=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.peach_1));
		peach2=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.peach_2));
		peach3=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.peach_3));
		peach4=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.peach_4));
		peach5=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.peach_5));
		raindrop=BitmapFactory.decodeStream(getResources()
				.openRawResource(R.drawable.yudi));
	}
	public Bitmap condenseBgMap(Bitmap map){
	        int width = map.getWidth();
	        int height = map.getHeight();
	        float scaleWidth = ((float) this.ScreenWidth) / width;
	        float scaleHeight = ((float) this.ScreenHeight) / height;
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);
	        map = Bitmap.createBitmap(map, 0, 0, width,
	                height, matrix, true);
	        return map;
	    }
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		new Thread(this).start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(MainFlag){
			if(startActivity.IsAdd){
				if(startActivity.IsPeach){
					if(peachs.size()<8){
					    time++;
						if(time>=70){
							Peach peach=new Peach(random.nextInt(6)+4, this);
							peachs.add(peach);
							time=0;
						}
					}
				}else{
					peachs.clear();
				}
				if(startActivity.IsRain){
					raintime++;
		            if(raintime>=20){
		                riandrops.add(new RainDrop(raindrop,this));
		                raintime=0;
		            }
				}
			}
			Canvas c=new Canvas(GameCacheMap);
			c.drawBitmap(startbg, 0, 0,null);
			for(GameInterFaceTwo gameInterFace:(List<GameInterFaceTwo>)riandrops.clone()){
	    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), null);
	        }
			for(GameInterFace gameInterFace:(List<GameInterFace>)peachs.clone()){
	    		c.drawBitmap(gameInterFace.getBitmap(), gameInterFace.getX(),gameInterFace.getY(), null);
	        }
//			if(FlagR){
//				r+=3;
//				if(r>=300){
//					r=300;
//					FlagR=false;
//				}
//			}else{
//				r-=3;
//				if(r<=100){
//					r=100;
//					FlagR=true;
//				}
//			}
//			
//			c.drawCircle(x, y, r, paint1);
			Canvas canvas=null;
	    	try {
	            if(holder!=null){
	                canvas=holder.lockCanvas();
	                canvas.drawBitmap(GameCacheMap,0,0,null);
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            if(canvas!=null)
	                 holder.unlockCanvasAndPost(canvas);
	        }
		}
		
	}

}
