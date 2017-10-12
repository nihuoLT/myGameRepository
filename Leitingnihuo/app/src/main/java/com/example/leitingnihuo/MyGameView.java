package com.example.leitingnihuo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017-02-15.
 */
public class MyGameView extends SurfaceView implements SurfaceHolder.Callback,Runnable,View.OnTouchListener {
    private Bitmap CacheBitmap=null,bg_1=null,bg_2=null,bg_3=null,bg_4=null,bg_5=null,bg_6=null,ep_10=null,blood=null,ep_3=null,ep_4=null;
    private Bitmap ep_9=null,bomb;
    public Bitmap explode=null,Ten=null,epb_1=null,epb_2=null,epb_3=null,epb_4=null,bossA=null,bullety,ybzidan=null,my_2=null,bombbaozha=null;
    private Bitmap ep_6=null,ep_7=null,ep_8=null;
    private Random random=new Random();
    private SurfaceHolder holder=null;
    private Typeface typeface;
    private Thread thread,threadtwo;
    private Paint paint=new Paint();
    private Canvas canvas;
    private Paint scorepaint=new Paint();
    private int level=0,alltime=0,MyBulletTime=0,SosBulletTime=0,TimeEnemy_8=0;
    public int explodeW=0,explodeH=0,myScore=0,myplaneblood=100,bulletId,bulletIndex=0,yingtime=0,index=0;
    private boolean MainState=true,IsAddBullet=false,EnemyFlag_2=false,EnemyFlag_3=false,EnemyFlag_4=false,EnemyFlag_5=false,EnemyFlag_6=false,EnemyFlag_7=false;
    private boolean EnemyFlag_8=false,EnemyFlag_9=false,IsShrotPool=false,EnemyFlag_10=false;
    public boolean Isying=false,IsAddsos=false,IsAddyb=false,YbLIsexist=false,YbRIsexist=false,YbBIsexist=false,IsAddbomb=false,stopstate=false,IsEnd=false;
    public int ScreenWidth=0,ScreenHeight=0,BulletTime=0,EnemyTime_1=0,Enemy_1=0,EnemyTime_2=0,Enemy_2=0,Enemy_4=0,EnemyTime_4=0,EnemyTime_3=0;
    public int bulletW,bulletH,indexyb=2,indexmyyb=100,indexbomb=5,endtime=0;
    public LruCache lruCache=null;
    public SoundPool pool=null;
    public Map<Integer, Integer> musicId=new HashMap<>();
    public ArrayList<MyInterface> bggroup_1=new ArrayList<>();
    public ArrayList<MyInterface> bggroup_2=new ArrayList<>();
    public ArrayList<MyInterface> bggroup_3=new ArrayList<>();
    public ArrayList<MyInterface> my=new ArrayList<>();
    public ArrayList<MyInterface> bullets=new ArrayList<>();
    public ArrayList<MyInterface> ybs=new ArrayList<>();
    public MyGameView(Context context, AttributeSet attrs){
        super(context, attrs);
        holder=getHolder();
        holder.addCallback(this);
        typeface=Typeface.createFromAsset(context.getAssets(),"pty.TTF");
        setOnTouchListener(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScreenWidth=dm.widthPixels;
        ScreenHeight=dm.heightPixels;
        init();
        CacheBitmap=Bitmap.createBitmap(ScreenWidth,ScreenHeight, Bitmap.Config.ARGB_8888);
        pool=new SoundPool(5, AudioManager.STREAM_SYSTEM, 0);//实例化soundpool类
        musicId.put(1, pool.load(getContext(), R.raw.destroyer_lazer2, 1));//将音乐文件放入HashMap中
        musicId.put(2, pool.load(getContext(), R.raw.destroyer_lazer3, 1));
        musicId.put(3, pool.load(getContext(), R.raw.bz, 1));
    }
    private void init(){
        int maxMemory =(int) Runtime.getRuntime().maxMemory()/1024;
        int cacheSize=maxMemory/4;
        lruCache=new LruCache<String,Bitmap>(cacheSize){
            protected int sizeOf(String key,Bitmap bitmap){
                return bitmap.getByteCount()/1024;
            }
        };
        bulletId=R.mipmap.myb;
        bg_1=BitmapFactory.decodeResource(getResources(),R.mipmap.bg);
        bg_2=BitmapFactory.decodeResource(getResources(),R.mipmap.bg);
        bg_3=BitmapFactory.decodeResource(getResources(),R.mipmap.bgstyle2_x);
        bg_4=BitmapFactory.decodeResource(getResources(),R.mipmap.bgstyle2_y);
        bg_5=BitmapFactory.decodeResource(getResources(),R.mipmap.bgstyle3_x);
        bg_6=BitmapFactory.decodeResource(getResources(),R.mipmap.bgstyle3_y);
        ep_3=BitmapFactory.decodeResource(getResources(),R.mipmap.ep_15);
        ep_4=addBitmapToCache(R.mipmap.ep_13);
        ep_6=addBitmapToCache(R.mipmap.ep_20);
        ep_7=addBitmapToCache(R.mipmap.big_1);
        ep_8=addBitmapToCache(R.mipmap.boos_1);
        ep_9=addBitmapToCache(R.mipmap.ep_9);
        epb_4=addBitmapToCache(R.mipmap.zidan_r);
        ybzidan=addBitmapToCache(R.mipmap.ybzidan);
        my_2=BitmapFactory.decodeResource(getResources(),R.mipmap.my_2);
        ep_10=BitmapFactory.decodeResource(getResources(),R.mipmap.ep_10);
        epb_1=addBitmapToCache(R.mipmap.epb_1);
        epb_2=BitmapFactory.decodeResource(getResources(),R.mipmap.dijizidan);
        epb_3=BitmapFactory.decodeResource(getResources(),R.mipmap.epb_3);
        bombbaozha=BitmapFactory.decodeResource(getResources(),R.mipmap.bombbaozha);
        explode=addBitmapToCache(R.mipmap.baozha);
        this.explodeW=explode.getWidth()/4;
        this.explodeH=explode.getHeight()/4;
        bullety=BitmapFactory.decodeResource(getResources(),R.mipmap.feibiao);
        bulletW=bullety.getWidth();
        bulletH=bullety.getHeight();
        Ten=BitmapFactory.decodeResource(getResources(),R.mipmap.ten);
        bossA=BitmapFactory.decodeResource(getResources(),R.mipmap.bossa);
        bomb=BitmapFactory.decodeResource(getResources(),R.mipmap.baojian);
        scorepaint.setTypeface(typeface);
        scorepaint.setColor(Color.YELLOW);
        scorepaint.setShadowLayer(5,2,2,Color.CYAN);
        scorepaint.setTextSkewX(-0.5f);
        scorepaint.setTextSize(45);
        bggroup_1.add(new BgMap(bg_1,bg_2,this));
        bggroup_2.add(new BgMap(bg_3,bg_4,this));
        bggroup_3.add(new BgMap(bg_5,bg_6,this));
        my.add(new MyPlane(R.mipmap.my_1,this));
    }
    private void MyDraw(){
        Canvas CacheCanvas=new Canvas(CacheBitmap);
        switch (index) {
            case 0:
                for(MyInterface image:(List<MyInterface>)bggroup_1.clone()){
                    CacheCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),paint);
                }
                break;
            case 1:
                for(MyInterface image:(List<MyInterface>)bggroup_2.clone()){
                    CacheCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),paint);
                }
                break;
            case 2:
                for(MyInterface image:(List<MyInterface>)bggroup_3.clone()){
                    CacheCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),paint);
                }
                break;
        }
        CacheCanvas.drawText("分数："+myScore,ScreenWidth/2,40,scorepaint);
        //CacheCanvas.drawText("等级："+level,10,40,scorepaint);
        CacheCanvas.drawText("时间："+alltime,10,40,scorepaint);
        for(MyInterface myInterface: (List<MyInterface>)my.clone()){
            if(myInterface instanceof Enemy){
                Enemy enemy=(Enemy)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof EnemyTest){
                EnemyTest enemy=(EnemyTest)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_2){
                Enemy_2 enemy=(Enemy_2)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_3){
                Enemy_3 enemy=(Enemy_3)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_4){
                Enemy_4 enemy=(Enemy_4)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_5){
                Enemy_5 enemy=(Enemy_5)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof MyPlane){
                MyPlane enemy=(MyPlane)myInterface;
                enemy.IsBeAttacked(bullets);
                enemy.IsBeAttacked(my);
            }
            if(myInterface instanceof BossA){
                BossA enemy=(BossA)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_6){
                Enemy_6 enemy=(Enemy_6)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_7){
                Enemy_7 enemy=(Enemy_7)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Enemy_8){
                Enemy_8 enemy=(Enemy_8)myInterface;
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Bomb){
                Bomb enemy=(Bomb)myInterface;
                enemy.IsBeAttacked(my);
                enemy.IsBeAttacked(bullets);
            }
            CacheCanvas.drawBitmap(myInterface.getBitmap(),myInterface.getX(),myInterface.getY(),paint);
        }
        for(MyInterface myInterface: (List<MyInterface>)bullets.clone()){
            if(myInterface instanceof ChageBullet){
                ChageBullet enemy=(ChageBullet)myInterface;
                enemy.IsBeAttacked(my);
            }
            CacheCanvas.drawBitmap(myInterface.getBitmap(),myInterface.getX(),myInterface.getY(),paint);
        }
        for(MyInterface myInterface: (List<MyInterface>)ybs.clone()){
            if(myInterface instanceof Ybone){
                Ybone enemy=(Ybone)myInterface;
                enemy.IsBeAttacked(my);
                enemy.IsBeAttacked(bullets);
            }
            if(myInterface instanceof Ybtwo){
                Ybtwo enemy=(Ybtwo)myInterface;
                enemy.IsBeAttacked(my);
                enemy.IsBeAttacked(bullets);
            }
            CacheCanvas.drawBitmap(myInterface.getBitmap(),myInterface.getX(),myInterface.getY(),paint);
        }
        try {
            if(holder!=null){
                canvas=holder.lockCanvas();
                canvas.drawBitmap(CacheBitmap,0,0,paint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas!=null)
                holder.unlockCanvasAndPost(canvas);
        }
    }
    @Override
    public void run() {
        while (MainState==true){
            while(stopstate){
                try {
                    Thread.sleep(100000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                MyDraw();
               // playPool();
                if(myplaneblood<=0){
                    endtime++;
                    if(endtime>=30){
                        IsEnd=true;
                        stop();
                        endtime=0;
                    }
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void playPool(){
        MyBulletTime++;
        if(MyBulletTime>=10&&IsShrotPool){
            pool.play(musicId.get(1),1,1,0,0,1);
            MyBulletTime=0;
        }
        SosBulletTime++;
        if(YbLIsexist||YbRIsexist){
            if(SosBulletTime>=12){
                pool.play(musicId.get(2),1,1,0,0,1);
                SosBulletTime=0;
            }
        }
    }
    public void stop(){
        stopstate=true;
    }
    public void start(){
        stopstate=false;
        thread.interrupt();
        threadtwo.interrupt();
    }
    public Bitmap addBitmapToCache(int resId) {
        String imageKey=String.valueOf(resId);
        Bitmap bitmap=(Bitmap)lruCache.get(imageKey);
        if(bitmap!=null){
            return bitmap;
        }else {
            Bitmap bitmaptwo= BitmapFactory.decodeResource(getResources(),resId);
            lruCache.put(imageKey,bitmaptwo);
            return bitmaptwo;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        threadtwo=new Thread(new AddRunnable(this));
        thread=new Thread(this);
        thread.start();
        threadtwo .start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        MainState=false;
        holder.removeCallback(this);
    }
public MyPlane selectfeiji=null;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(MyInterface myInterface: (List<MyInterface>)my.clone()){
                    if(myInterface instanceof MyPlane){
                        MyPlane feiji=(MyPlane)myInterface;
                        if(event.getX()>=feiji.getX()&&event.getX()<=feiji.getX()+feiji.getWidth()
                                &&event.getY()>feiji.getY()&&event.getY()<=event.getY()+feiji.getHeight()){
                            IsAddBullet=true;
                            IsShrotPool=true;
                            selectfeiji=feiji;
                        }else{
                            selectfeiji=null;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(selectfeiji!=null){
                    if((int)event.getX()>=selectfeiji.getWidth()/2&&(int)event.getX()<=ScreenWidth-selectfeiji.getWidth()/2
                            &&(int)event.getY()>=selectfeiji.getHeight()/2&&(int)event.getY()<=ScreenHeight-selectfeiji.getHeight()/2){
                        selectfeiji.setX((int)event.getX()-selectfeiji.getWidth()/2);
                        selectfeiji.setY((int)event.getY()-selectfeiji.getHeight()/2);
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                IsAddBullet=false;
                IsShrotPool=false;
                selectfeiji=null;
                break;
            default:
                break;
        }
        return true;
    }
    class AddRunnable implements Runnable{
        private  MyGameView myGameView;
        public AddRunnable(MyGameView myGameView){
            this.myGameView=myGameView;
        }
        @Override
        public void run() {
            while (MainState==true){
                if(Isying==true){
                    yingtime++;
                    if(yingtime>=15){
                        bullets.add(new Bullet(R.mipmap.newbullet_1,0,0,1,myGameView));
                        yingtime=0;
                    }
                }
                if(IsAddsos==true){
                    YbLIsexist=true;
                    YbRIsexist=true;
                    ybs.add(new Ybone(R.mipmap.ep_12,myGameView));
                    ybs.add(new Ybtwo(R.mipmap.ep_12,myGameView));
                    indexyb--;
                    IsAddsos=false;
                }
                if(IsAddyb==true){
                        YbBIsexist=true;
                        ybs.add(new MyYbs(0,ScreenHeight,1,myGameView));
                        ybs.add(new MyYbs(ScreenWidth*1/4-my_2.getWidth()/2+10,ScreenHeight+my_2.getHeight(),2,myGameView));
                        ybs.add(new MyYbs(ScreenWidth/2-my_2.getWidth()/2,ScreenHeight,1,myGameView));
                        ybs.add(new MyYbs(ScreenWidth*3/4-my_2.getWidth()/2-10,ScreenHeight+my_2.getHeight(),2,myGameView));
                        ybs.add(new MyYbs(ScreenWidth-my_2.getWidth(),ScreenHeight,1,myGameView));
                        indexmyyb--;
                        IsAddyb=false;
                }
                if(IsAddbomb==true){
                    my.add(new Bomb(bomb,0,ScreenHeight,1,myGameView));
                    my.add(new Bomb(bomb,ScreenWidth/4-bomb.getWidth()/2,-bomb.getHeight(),2,myGameView));
                    my.add(new Bomb(bomb,ScreenWidth/2-bomb.getWidth()/2,ScreenHeight,1,myGameView));
                    my.add(new Bomb(bomb,ScreenWidth*3/4-bomb.getWidth()/2,-bomb.getHeight(),2,myGameView));
                    my.add(new Bomb(bomb,ScreenWidth-bomb.getWidth(),ScreenHeight,1,myGameView));
                    indexbomb--;
                    IsAddbomb=false;
                }
                if(IsAddBullet==true){
                    BulletTime++;
                    if(BulletTime>=15){
                        bullets.add(new Bullet(bulletId,0,0,1,myGameView));
                        BulletTime=0;
                    }
                }
                if(alltime<=8000)
                alltime++;
                if(alltime>=600){
                    EnemyFlag_2=true;
                }
                if(alltime==800){
                    EnemyFlag_3=true;
                }
                if(alltime>=1050){
                    EnemyFlag_4=true;
                }
                if(alltime==1400){
                    my.add(new Enemy_8(-ep_3.getWidth(),ScreenHeight/8,2,10,myGameView));
                    my.add(new Enemy_8(ScreenWidth,ScreenHeight/8,2,10,myGameView));
                }
                if(alltime==1700){
                    my.add(new Enemy_2(R.mipmap.ep_13,10,-ep_4.getHeight(),0,ScreenHeight/4,myGameView));
                    my.add(new Enemy_2(R.mipmap.ep_13,ScreenWidth-10-ep_4.getWidth(),-ep_4.getHeight(),0,ScreenHeight/4,myGameView));
                    my.add(new Enemy_2(R.mipmap.ep_13,ScreenWidth/2-ep_4.getWidth()/2,-ep_4.getHeight(),0,ScreenHeight/12,myGameView));
                }
                if(alltime==2000){
                    EnemyFlag_5=true;
                }
                if(alltime==2300){
                    EnemyFlag_5=true;
                }
                if(alltime==2500){
                    EnemyFlag_5=true;
                }
                if(alltime==2800){
                    EnemyFlag_5=true;
                }
                if(alltime==3100){
                    Enemy_1=0;
                }
                if(alltime==3300){
                    EnemyFlag_6=true;
                }
                if(alltime==3500){
                    EnemyFlag_7=true;
                }
                if(alltime==3800){
                    EnemyFlag_6=true;
                    EnemyFlag_8=true;
                }
                if(alltime==4050){
                    EnemyFlag_7=true;
                }
                if(alltime==4600){
                    EnemyFlag_9=true;
                }
                if(alltime==5000){
                    my.add(new Enemy_7(R.mipmap.ep_9,ScreenWidth/2-ep_9.getWidth()/2,ScreenHeight/4-ep_9.getHeight(),myGameView));
                }
                if(alltime==5600){
                    EnemyFlag_10=true;
                }
                if(alltime==6000){
                    my.add(new BossA(bossA,ScreenWidth/2-bossA.getWidth()/6,0,myGameView));
                }
                if(Enemy_1<6){
                    EnemyTime_1++;
                    if(EnemyTime_1>=80){
                        if(Enemy_1==5){
                            my.add(new Enemy(R.mipmap.ep_5,myGameView,1));
                            my.add(new Enemy(R.mipmap.ep_5,myGameView,2));
                        }else {
                            my.add(new Enemy(R.mipmap.ep_1,myGameView,1));
                            my.add(new Enemy(R.mipmap.ep_1,myGameView,2));
                        }
                        Enemy_1++;
                        EnemyTime_1=0;
                    }
                }
                if(EnemyFlag_2==true){
                    if(Enemy_2<8){
                        EnemyTime_2++;
                        if(EnemyTime_2>=80) {
                            my.add(new Enemy(R.mipmap.ep_1, myGameView, 3));
                            my.add(new Enemy(R.mipmap.ep_1, myGameView, 4));
                            Enemy_2++;
                            EnemyTime_2 = 0;
                        }
                    }
                }
                if(EnemyFlag_4==true){
                    if(Enemy_4<5){
                        EnemyTime_4++;
                        if(EnemyTime_4>=30) {
                            my.add(new Enemy(R.mipmap.ep_2, myGameView, 5));
                            my.add(new Enemy(R.mipmap.ep_2, myGameView, 6));
                            Enemy_4++;
                            EnemyTime_4 = 0;
                        }
                    }
                }
                if(EnemyFlag_3==true){
                    my.add(new EnemyTest(ep_10,myGameView,1));
                    my.add(new EnemyTest(ep_10,myGameView,2));
                    EnemyFlag_3=false;
                }
                if(EnemyFlag_5==true){
                    my.add(new Enemy_3(R.mipmap.ep_20,0*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,1*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,2*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,3*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,4*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,5*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    EnemyFlag_5=false;
                }
                if(EnemyFlag_6==true){
                    my.add(new Enemy_3(R.mipmap.ep_20,1*ep_6.getWidth(),-3*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,2*ep_6.getWidth(),-2*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,3*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,4*ep_6.getWidth(),-2*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,5*ep_6.getWidth(),-3*ep_6.getHeight(),myGameView));
                    EnemyFlag_6=false;
                }
                if(EnemyFlag_7==true){
                    my.add(new Enemy_3(R.mipmap.ep_20,1*ep_6.getWidth(),-3*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,2*ep_6.getWidth(),-2*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,3*ep_6.getWidth(),-ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,4*ep_6.getWidth(),-2*ep_6.getHeight(),myGameView));
                    my.add(new Enemy_3(R.mipmap.ep_20,5*ep_6.getWidth(),-3*ep_6.getHeight(),myGameView));
                    EnemyFlag_7=false;
                }
                if(EnemyFlag_8==true){
                    EnemyTime_3++;
                    if(EnemyTime_3>=600+random.nextInt(600)){
                        if(alltime>=5000){
                            my.add(new Enemy_4(ep_7,10,ScreenHeight/8,15,5,myGameView));
                            my.add(new Enemy_4(ep_7,ScreenWidth-ep_7.getWidth()-10,ScreenHeight/8,15,5,myGameView));
                            my.add(new Enemy_4(ep_7,ScreenWidth/2-ep_7.getWidth()/2,ScreenHeight/4,15,7,myGameView));
                        }else {
                            my.add(new Enemy_4(ep_7,10,ScreenHeight/8,5,4,myGameView));
                            my.add(new Enemy_4(ep_7,ScreenWidth-ep_7.getWidth()-10,ScreenHeight/8,5,4,myGameView));
                            my.add(new Enemy_4(ep_7,ScreenWidth/2-ep_7.getWidth()/2,ScreenHeight/4,5,4,myGameView));
                        }
                        EnemyTime_3=0;
                    }
                }
                if(EnemyFlag_9==true){
                    my.add(new Enemy_5(ep_8,ScreenWidth/6,ScreenHeight/8,16,myGameView));
                    my.add(new Enemy_5(ep_8,5*ScreenWidth/6-ep_8.getWidth(),ScreenHeight/8,16,myGameView));
                    EnemyFlag_9=false;
                }
                if(EnemyFlag_10==true){
                    TimeEnemy_8++;
                    if(TimeEnemy_8>=100+random.nextInt(200)){
                        my.add(new Enemy_8(random.nextInt(ScreenWidth-70),-40,3,12,myGameView));
                        TimeEnemy_8=0;
                    }
                }
                try {
                    while(stopstate){
                        try {
                            Thread.sleep(100000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
