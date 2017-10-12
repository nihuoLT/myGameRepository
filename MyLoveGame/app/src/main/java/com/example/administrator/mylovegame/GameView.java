package com.example.administrator.mylovegame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable,View.OnTouchListener{
    public BgMap bgMap=null;
    public Context context;
    public Typeface typeface=null;
    public SurfaceHolder holder=null;
    public Bitmap CacheBitmap=null,BgBitmap=null,BgTwoBitmap=null,Bee=null,OppositeBee=null;
    public Bitmap Birdie=null,OppositeBirdie=null,BirdieDrop=null,Blood=null,TenScore=null,Doctormap=null;
    public Bitmap BeeCry=null,AddBlood=null,HundredScore=null,Explodemap=null,DragonBitmap=null,DragonChange=null;
    public Bitmap ThirtyScore=null,DemonBitmap=null,FiveScore=null,MonsterBig=null,MonsterSmall=null;
    public Bitmap EightScore=null,ThreeScore=null,BatBitmap=null,OppositeBat=null,ExplodemapTwo=null;
    public Bitmap OppositeMonsterSmall=null,SixScore=null,Monstera=null,SevenScore=null;
    public Canvas NewCanvas=null;
    public Paint  spirit=new Paint();
    public Paint alphapaint=new Paint();
    public Paint mpaint=new Paint();
    public Paint  font=new Paint();
    public Paint  numberfont=new Paint();
    public int ScreenWidth=0,ScreenHeight=0,AllScore=0,MainCount=0,Addbloodcout=0,Addbloodvar=0,Big=0,varX=0,varY=0;
    public int AddDragon=0,Adddemon=0,Allmonster=0,CarnivalX=0,time=0,adddoctors=0,carnivaltime=0,addbat=0,birdienumber=0;
    public int alphatime=0,var=0;
    public Random MainRandom=new Random();
    public  boolean MainThreadState=true,Xbeedirection=false,Ybeedirection=false,Iscry=false,Iscarnival=false;
    public boolean carnivalflag=true,allflag=true,stopstate=false,Isend=false,IsaddBat=false,IsStart=true;
    public boolean Isalpha=false;
    public Thread thread;
    public SoundPool pool=null;
    public Path path=new Path();
    public HashMap<Integer, Integer> musicId=new HashMap<Integer, Integer>();
    private List<Bitmap> beecrys=new ArrayList<Bitmap>();
    private List<Bitmap> dragonchanges=new ArrayList<Bitmap>();
    public ArrayList<GameImage> bees=new ArrayList<GameImage>();
    public ArrayList<GameImage> birdies=new ArrayList<GameImage>();
    public ArrayList<GameImage> bloods=new ArrayList<GameImage>();
    public ArrayList<GameImage> scores=new ArrayList<GameImage>();
    public ArrayList<GameImage> doctors=new ArrayList<GameImage>();
    public ArrayList<GameImage> explodes=new ArrayList<GameImage>();
    public ArrayList<GameImage> dragons=new ArrayList<GameImage>();
    public ArrayList<GameImage> bombs=new ArrayList<GameImage>();
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        holder=this.getHolder();//获取holder
        holder.addCallback(this);//加载callback
        typeface=Typeface.createFromAsset(context.getAssets(),"pty.TTF");//加载外部字体
        font.setTypeface(typeface);//画笔设置为外部字体类型
        font.setColor(Color.BLUE);//画笔设置为蓝色
        font.setShadowLayer(5,2,2,Color.CYAN);
        font.setTextSkewX(-1);
        font.setTextSize(60);//设置字体大小为60
        numberfont.setTypeface(typeface);
        numberfont.setColor(Color.YELLOW);
        numberfont.setShadowLayer(5,2,2,Color.CYAN);
        numberfont.setTextSkewX(-0.5f);
        numberfont.setTextSize(45);
        mpaint.setStyle(Paint.Style.STROKE);
        // mpaint.setTextSize(40);
        mpaint.setStrokeWidth(10);
        mpaint.setColor(Color.BLUE);
        mpaint.setAntiAlias(true);
        pool=new SoundPool(3, AudioManager.STREAM_SYSTEM, 2);//实例化soundpool类
        musicId.put(1, pool.load(getContext(),R.raw.bz, 1));//将音乐文件放入HashMap中
        musicId.put(2, pool.load(getContext(), R.raw.jj, 1));
        musicId.put(3, pool.load(getContext(), R.raw.kl, 1));
        musicId.put(4, pool.load(getContext(), R.raw.ys, 1));
        setOnTouchListener(this);//为view添加触点事件
    }
    public void init(){
        CacheBitmap=Bitmap.createBitmap(ScreenWidth,ScreenHeight, Bitmap.Config.ARGB_8888);//创建二级缓存bitmap
        BgBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.bg_1);//获取第一张背景图片
        Bee=BitmapFactory.decodeResource(getResources(),R.mipmap.bee);//获取蜜蜂资源图片
        Monstera=BitmapFactory.decodeResource(getResources(),R.drawable.monster_a);
        BeeCry=BitmapFactory.decodeResource(getResources(),R.mipmap.beecry);//获取蜜蜂资源图片
        Birdie=BitmapFactory.decodeResource(getResources(),R.mipmap.birdie);//获取怪物鸟资源图片
        Doctormap=BitmapFactory.decodeResource(getResources(),R.mipmap.doctor);//加载医生猫头鹰资源图片
        BirdieDrop=BitmapFactory.decodeResource(getResources(),R.mipmap.drop);//加载怪物鸟坠落资源图片
        MonsterBig=BitmapFactory.decodeResource(getResources(),R.mipmap.monsterbig);
        MonsterSmall=BitmapFactory.decodeResource(getResources(),R.mipmap.monstersmall);
        Blood=BitmapFactory.decodeResource(getResources(),R.mipmap.blood);
        TenScore=BitmapFactory.decodeResource(getResources(),R.mipmap.ten);
        DemonBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.demon);
        ThirtyScore=BitmapFactory.decodeResource(getResources(),R.mipmap.thirty);
        EightScore=BitmapFactory.decodeResource(getResources(),R.mipmap.eight);
        ThreeScore=BitmapFactory.decodeResource(getResources(),R.mipmap.three);
        SevenScore=BitmapFactory.decodeResource(getResources(),R.drawable.seven);
        FiveScore=BitmapFactory.decodeResource(getResources(),R.mipmap.five);
        SixScore=BitmapFactory.decodeResource(getResources(),R.mipmap.six);
        HundredScore=BitmapFactory.decodeResource(getResources(),R.mipmap.hundred);
        DragonBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.dragon);
        DragonChange=BitmapFactory.decodeResource(getResources(),R.mipmap.dragonchange);
        Explodemap=BitmapFactory.decodeResource(getResources(),R.mipmap.explode);
        AddBlood=BitmapFactory.decodeResource(getResources(),R.mipmap.addblood);
        BatBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.bat);
        ExplodemapTwo=BitmapFactory.decodeResource(getResources(),R.drawable.magic);
        OppositeMonsterSmall=OppositeBitmap(MonsterSmall);
        OppositeBat=OppositeBitmap(BatBitmap);
        OppositeBee=OppositeBitmap(Bee);
        OppositeBirdie=OppositeBitmap(Birdie);//得到水平翻转后的图片
        BgBitmap=condenseBgMap(BgBitmap);//调用自定义方法把图片压缩到手机屏幕大小
        BgTwoBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.bg_2);//获取第二张背景图片
        BgTwoBitmap=condenseBgMap(BgTwoBitmap);//第二个背景bitmap调用自定义方法把图片压缩到手机屏幕大小
        beecrys.add(Bitmap.createBitmap(BeeCry, 0, 0, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*1, 0, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*2, 0, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*3, 0, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, 0, BeeCry.getHeight()/2, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*1,BeeCry.getHeight()/2, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*2,BeeCry.getHeight()/2, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        beecrys.add(Bitmap.createBitmap(BeeCry, (BeeCry.getWidth()/4)*3,BeeCry.getHeight()/2, BeeCry.getWidth()/4, BeeCry.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, 0, 0, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, (DragonChange.getWidth()/3)*1, 0, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, (DragonChange.getWidth()/3)*2, 0, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, 0, DragonChange.getHeight()/2, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, (DragonChange.getWidth()/3)*1,DragonChange.getHeight()/2, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        dragonchanges.add(Bitmap.createBitmap(DragonChange, (DragonChange.getWidth()/3)*2,DragonChange.getHeight()/2, DragonChange.getWidth()/3, DragonChange.getHeight()/2));
        bgMap=new BgMap(BgBitmap,BgTwoBitmap,this);//初始化背景
        bees.add(new Bee(OppositeBee,ScreenWidth/2-Bee.getWidth()/8,ScreenHeight/2-Bee.getHeight()/4));//加入蜜蜂
        bloods.add(new Blood(Blood,10,20));
        bloods.add(new Blood(Blood,10+Blood.getWidth(),20));
        bloods.add(new Blood(Blood,10+2*Blood.getWidth(),20));
        bloods.add(new Blood(Blood,10,20+Blood.getHeight()));
        dragons.add(new Dragon(DragonBitmap,ScreenWidth,MainRandom.nextInt(ScreenHeight-DragonBitmap.getHeight()/2),this));
        //dragons.add(new MonsterA(Monstera,MainRandom.nextInt(ScreenHeight-Monstera.getWidth()/2),MainRandom.nextInt(ScreenHeight-Monstera.getHeight()),this));
    }
    public Bitmap OppositeBitmap(Bitmap map){
        Matrix orig = new Matrix();
        orig.setScale(-1, 1);//翻转X
        orig.postTranslate(map.getWidth(), 0);//平移
        map=Bitmap.createBitmap(map, 0, 0, map.getWidth(),
                map.getHeight(), orig, true);
        return map;
    }
    public Bitmap condenseBgMap(Bitmap map){
        int width = map.getWidth();
        int height = map.getHeight();
        float scaleWidth = ((float) ScreenWidth) / width;
        float scaleHeight = ((float) ScreenHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(45);
        map = Bitmap.createBitmap(map, 0, 0, width,
                height, matrix, true);
        return map;
    }
    public void draw(){
        Addbloodcout++;
        if(Iscarnival==false){
            adddoctors=500+MainRandom.nextInt(500)  ;
        }
        if(Addbloodcout>=adddoctors){
            Addbloodvar=MainRandom.nextInt(200);
            if(Addbloodvar>100){
                Doctor doctor=new Doctor(Doctormap,ScreenWidth,ScreenHeight/2,5,4,this);
                doctor.Xdirection=true;
                doctors.add(doctor);
                Addbloodcout=0;
            }else {
                Doctor doctor=new Doctor(Doctormap,-Doctormap.getWidth()/4,ScreenHeight/2,5,4,this);
                doctor.Xdirection=false;
                doctors.add(doctor);
                Addbloodcout=0;
            }
        }
        if(allflag==true){
            if(birdies.size()<40){
                MainCount++;
                int MainIndex=100+MainRandom.nextInt(100);
                if(MainCount>=MainIndex){
                    int AddMonster=MainRandom.nextInt(800);
                    if(AddMonster<=200){
                        MonsterBirdie monsterBirdie=new MonsterBirdie(Birdie,BirdieDrop,ScreenWidth-Birdie.getWidth()/4,MainRandom.nextInt(ScreenHeight-Birdie.getHeight()/2),6,5,true,true,this);
                        birdienumber++;
                        birdies.add(monsterBirdie);
                        MainCount=0;
                    }else if(AddMonster>200&&AddMonster<=400){
                        MonsterBirdie monsterBirdie=new MonsterBirdie(OppositeBirdie,BirdieDrop,0,MainRandom.nextInt(ScreenHeight-Birdie.getHeight()/2),6,5,false,true,this);
                        birdienumber++;
                        birdies.add(monsterBirdie);
                        MainCount=0;
                    }else{
                        MonsterBirdie monsterBirdie=new MonsterBirdie(OppositeBirdie,BirdieDrop,MainRandom.nextInt(ScreenWidth-Birdie.getWidth()/4),10,6,5,false,true,this);
                        birdienumber++;
                        birdies.add(monsterBirdie);
                        MainCount=0;
                    }
                }
            }
            AddDragon++;
            if(AddDragon>=200+MainRandom.nextInt(300)){
                dragons.add(new Dragon(DragonBitmap,ScreenWidth,DragonBitmap.getHeight()+MainRandom.nextInt(ScreenHeight-2*DragonBitmap.getHeight()),this));
                AddDragon=0;
            }
            Adddemon++;
            if(Adddemon>=100+MainRandom.nextInt(300)){
                dragons.add(new Demon(DemonBitmap,0,40+MainRandom.nextInt(400),this));
                Adddemon=0;
            }
            if(AllScore>=6000){
                Big++;
                if(Big>=100+MainRandom.nextInt(300)){
                    dragons.add(new MonsterBig(MonsterBig,ScreenWidth,100+MainRandom.nextInt(ScreenHeight-300),this));
                    Big=0;
                }
            }
            if(AllScore>=6300){
                Isalpha=true;
            }
            if(AllScore>=6500){
                IsaddBat=true;
            }
            if(IsaddBat==true){
                addbat++;
                if(addbat>=200+MainRandom.nextInt(400)){
                    dragons.add(new Bat(BatBitmap,MainRandom.nextInt(ScreenWidth-BatBitmap.getWidth()/3),MainRandom.nextInt(ScreenHeight-BatBitmap.getHeight()/3),1+MainRandom.nextInt(6),MainRandom.nextInt(7),true,true,this,1));
                }
                if(addbat>=250){
                    addbat=0;
                }
            }
            if(Isalpha==true){
                alphatime++;
                if(alphatime>=200+MainRandom.nextInt(200)){
                    dragons.add(new MonsterA(Monstera,MainRandom.nextInt(ScreenHeight-Monstera.getWidth()/2),MainRandom.nextInt(ScreenHeight-Monstera.getHeight()),this));
                    alphatime=0;
                }
            }
        }

//        if(varY<=ScreenHeight){
//            varY+=2;
//            if(varY>=ScreenHeight){
//                IsStart=true;
//            }
//        }
        Allmonster=birdies.size()+dragons.size();
        NewCanvas=new Canvas(CacheBitmap);
//        NewCanvas.save();
//        NewCanvas.clipRect(0,0,varX,varY);
        NewCanvas.drawBitmap(bgMap.getBitmap(),0,0,spirit);
        NewCanvas.drawText("Score: "+AllScore,ScreenWidth/6+60,60,font);
        NewCanvas.drawText("MonsterNumber: "+Allmonster,ScreenWidth/2+110,60,numberfont);
            for(GameImage image:(List<GameImage>)bloods.clone()){
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
            for(GameImage image:(List<GameImage>)bees.clone()){
                ((Bee)image).IsBeAttacked(birdies);
                ((Bee)image).IsBeAttacked(dragons);
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
            for(GameImage image:(List<GameImage>)birdies.clone()){
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
            for(GameImage image:(List<GameImage>)doctors.clone()){
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
            for(GameImage image:(List<GameImage>)explodes.clone()){
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
            for(GameImage image:(List<GameImage>)dragons.clone()){
                if(image instanceof MonsterA){
                    MonsterA monster=(MonsterA)image;
                    alphapaint.setAlpha(monster.alpha);
                    NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),alphapaint);
                }else {
                    NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
                }
            }
            for(GameImage image:(List<GameImage>)scores.clone()){
                NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
            }
        for(GameImage image:(List<GameImage>)bombs.clone()){
            NewCanvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(),spirit);
        }
        if(AllScore>=200&&carnivalflag==true){
            Iscarnival=true;
        }
        if(Iscarnival==true){
            NewCanvas.drawText("6 sec carnival",CarnivalX,ScreenHeight/2-80,font);
            if(CarnivalX>=ScreenWidth/2-100){
                CarnivalX-=12;
            }else {
                time++;
                if(time>=100){
                    CarnivalX-=12;
                    if(CarnivalX<=-300){
                        allflag=false;
                        birdies.clear();
                        dragons.clear();
                        adddoctors=10;
                        carnivaltime++;
                        if(carnivaltime>=600){
                            time=0;
                            CarnivalX=ScreenWidth;
                            carnivaltime=0;
                            Iscarnival=false;
                            carnivalflag=false;
                            allflag=true;
                        }
                    }
                }
            }
        }
        if(bloods.size()<=0){
            Intent intent=new Intent(context,ThreeActivity.class);
            intent.putExtra("score", Long.toString(AllScore));
            context.startActivity(intent);
            Isend=true;
        }
        Canvas canvas=holder.lockCanvas();
        canvas.drawBitmap(CacheBitmap,0,0,spirit);
        holder.unlockCanvasAndPost(canvas);
    }
    public void stop(){
        stopstate=true;
    }
    public void start(){
        stopstate=false;
        thread.interrupt();
    }
    @Override
    public void run() {
        try {
            while (MainThreadState){
                while(stopstate){
                    try {
                        Thread.sleep(100000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                draw();
                Thread.sleep(10);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(GameImage image:(List<GameImage>)doctors.clone()){
                    if(image instanceof Doctor){
                        Doctor doctor=(Doctor) image;
                            if(event.getX()>=doctor.getX()&&event.getX()<=doctor.getX()+doctor.getWidth()
                                    &&event.getY()>=doctor.getY()&&event.getY()<=doctor.getY()+doctor.getHeight()){
                                doctor.Isremove=true;
                                if(bloods.size()<3&&bloods.size()>0){
                                    if(event.getX()>ScreenWidth/2){
                                        scores.add(new AddScore(AddBlood,(int)event.getX()-100,(int)event.getY(),this));
                                    }else {
                                        scores.add(new AddScore(AddBlood,(int)event.getX(),(int)event.getY(),this));
                                    }
                                    pool.play(musicId.get(4),1,1,0,0,1);
                                    bloods.add(new Blood(Blood,10+bloods.size()*Blood.getWidth(),20));
                                }else if(bloods.size()==0){
                                    if(event.getX()>ScreenWidth/2){
                                        scores.add(new AddScore(AddBlood,(int)event.getX()-100,(int)event.getY(),this));
                                    }else {
                                        scores.add(new AddScore(AddBlood,(int)event.getX(),(int)event.getY(),this));
                                    }
                                    pool.play(musicId.get(4),1,1,0,0,1);
                                    bloods.add(new Blood(Blood,10,20));
                                }else if(bloods.size()<6&&bloods.size()>=3){
                                    if(event.getX()>ScreenWidth/2){
                                        scores.add(new AddScore(AddBlood,(int)event.getX()-100,(int)event.getY(),this));
                                    }else {
                                        scores.add(new AddScore(AddBlood,(int)event.getX(),(int)event.getY(),this));
                                    }
                                    pool.play(musicId.get(4),1,1,0,0,1);
                                    bloods.add(new Blood(Blood,10+(bloods.size()-3)*Blood.getWidth(),Blood.getHeight()+20));
                                }else{
                                    if(event.getX()>ScreenWidth/2){
                                        scores.add(new AddScore(HundredScore,(int)event.getX()-100,(int)event.getY(),this));
                                    }else {
                                        scores.add(new AddScore(HundredScore,(int)event.getX(),(int)event.getY(),this));
                                    }
                                    AllScore+=100;
                                    pool.play(musicId.get(1),1,1,0,0,1);
                                    explodes.add(new Explode(Explodemap,(int)event.getX()-doctor.getWidth()/2,(int)event.getY()-doctor.getHeight()/2,1,this));
                                }
                            }
                    }
                }
                for(GameImage image:(List<GameImage>)dragons.clone()){
                    if(image instanceof Demon){
                        Demon monster=(Demon)image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                            dragons.remove(monster);
                            scores.add(new AddScore(FiveScore,(int)event.getX(),(int)event.getY(),this));
                            AllScore+=5;
                            pool.play(musicId.get(1),1,1,0,0,1);
                            explodes.add(new Explode(Explodemap,(int)event.getX()-monster.getWidth()/2,(int)event.getY()-monster.getHeight()/2,1,this));
                        }
                    }
                    if(image instanceof Dragon){
                        Dragon monster=(Dragon)image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                                    monster.maindragon=dragonchanges;
                                    monster.Ischage=true;
                                    monster.blood--;
                                    if(monster.blood>0)
                                    pool.play(musicId.get(3),1,1,0,0,1);
                        }
                    }
                    if(image instanceof MonsterBig){
                        MonsterBig monster=(MonsterBig)image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                            dragons.remove(monster);
                            scores.add(new AddScore(EightScore,(int)event.getX(),(int)event.getY(),this));
                            AllScore+=8;
                            if(monster.getX()>=ScreenWidth/2){
                                dragons.add(new MonsterSmall(MonsterSmall,monster.getX(),monster.getY()-100,this,1));
                                dragons.add(new MonsterSmall(MonsterSmall,monster.getX()+50,monster.getY()+100,this,1));
                            }else {
                                dragons.add(new MonsterSmall(OppositeMonsterSmall,monster.getX(),monster.getY()-100,this,2));
                                dragons.add(new MonsterSmall(OppositeMonsterSmall,monster.getX()+50,monster.getY()+100,this,2));
                            }
                        }
                    }
                    if(image instanceof MonsterSmall){
                        MonsterSmall monster=(MonsterSmall)image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                            dragons.remove(monster);
                            AllScore+=3;
                            scores.add(new AddScore(ThreeScore,(int)event.getX(),(int)event.getY(),this));
                            pool.play(musicId.get(1),1,1,0,0,1);
                            explodes.add(new Explode(Explodemap,(int)event.getX()-monster.getWidth(),(int)event.getY()-monster.getHeight(),1,this));
                        }
                    }
                    if(image instanceof Bat){
                        Bat monster=(Bat) image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                            dragons.remove(monster);
                            AllScore+=6;
                            scores.add(new AddScore(ThreeScore,(int)event.getX(),(int)event.getY(),this));
                            pool.play(musicId.get(1),1,1,0,0,1);
                            explodes.add(new ExplodeTwo(ExplodemapTwo,(int)event.getX()-20,(int)event.getY()-monster.getHeight()/2,this));
                        }
                    }
                    if(image instanceof MonsterA){
                        MonsterA monster=(MonsterA) image;
                        if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()&&monster.alpha>=255){
                            dragons.remove(monster);
                            AllScore+=7;
                            scores.add(new AddScore(SevenScore,(int)event.getX(),(int)event.getY(),this));
                            pool.play(musicId.get(1),1,1,0,0,1);
                            explodes.add(new ExplodeTwo(ExplodemapTwo,(int)event.getX()-20,(int)event.getY()-monster.getHeight()/2,this));
                        }
                    }
                }
                for(GameImage image:(List<GameImage>)birdies.clone()){
                    if(image instanceof MonsterBirdie){
                        MonsterBirdie monster=(MonsterBirdie)image;
                        if(monster.Iscantouch==true){
                            if(event.getX()>=monster.getX()&&event.getX()<=monster.getX()+monster.getWidth()
                                    &&event.getY()>=monster.getY()&&event.getY()<=monster.getY()+monster.getHeight()){
                                monster.Isdrop=true;
                                monster.Iscantouch=false;
                                monster.Iscanattack=false;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    private class Bee implements GameImage{
        private  Bitmap BeeCacheBitmap;
        private int width=0;
        private int height=0;
        private int x=0,x1=0,x2=0,r1=0;
        private int y=0,y1=0,y2=0,r2=0;
        private int index=0,i=0;
        private int Xindex=0;
        private int Yindex=0;
        private int intvar=0,Yintvar=0;
        private int count=0;
        private Random random=new Random();
        private List<Bitmap> mifengs=new ArrayList<Bitmap>();
        private List<Bitmap> mainmifengs=new ArrayList<Bitmap>();
        public Bee(Bitmap MiFengBitmap,int x,int y){
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, 0, 0, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*1, 0, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*2, 0, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*3, 0, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, 0, MiFengBitmap.getHeight()/2, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*1,MiFengBitmap.getHeight()/2, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*2,MiFengBitmap.getHeight()/2, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            mifengs.add(Bitmap.createBitmap(MiFengBitmap, (MiFengBitmap.getWidth()/4)*3,MiFengBitmap.getHeight()/2, MiFengBitmap.getWidth()/4, MiFengBitmap.getHeight()/2));
            BeeCacheBitmap=MiFengBitmap;
            this.width=MiFengBitmap.getWidth()/4;
            this.height=MiFengBitmap.getHeight()/2;
            this.x=x;
            this.y=y;
            this.r1=width/2;
            this.mainmifengs=mifengs;
        }
        @Override
        public Bitmap getBitmap() {
           Bitmap bitmap=mainmifengs.get(index);
            if(Iscry==true){
                i++;
                if(i>=25){
                    mainmifengs=mifengs;
                    Iscry=false;
                    i=0;
                }
            }
                if(count==3){
                    index++;
                    if(index==mainmifengs.size()){
                        index=0;
                    }
                    count=0;
                }
                count++;
                Xindex++;
                Yindex++;
                if(Xindex>=50+random.nextInt(120)){
                    intvar=random.nextInt(500);
                    if(intvar>=200){
                        Xbeedirection=true;
                        if(Iscry==false){
                            bees.clear();
                            bees.add(new Bee(Bee,x,y));
                        }
                    }else{
                        Xbeedirection=false;
                        if(Iscry==false){
                            bees.clear();
                            bees.add(new Bee(OppositeBee,x,y));
                        }
                    }
                    Xindex=0;
                }
                if(Yindex>=60+random.nextInt(80)){
                    Yintvar=random.nextInt(300);
                    if(Yintvar>=130){
                        Ybeedirection=true;
                    }else{
                        Ybeedirection=false;
                    }
                    Yindex=0;
                }
                if(Ybeedirection==true){
                    if(IsStart==true)
                    y-=3;
                    if(y<0){
                        Ybeedirection=false;
                    }
                }else{
                    if(IsStart==true)
                    y+=3;
                    if(y>ScreenHeight-BeeCacheBitmap.getHeight()/2){
                        Ybeedirection=true;
                    }
                }
                if(Xbeedirection==true){
                    if(IsStart==true)
                    x-=3;
                    if(x<0){
                        Xbeedirection=false;
                        if(Iscry==false){
                            bees.clear();
                            bees.add(new Bee(OppositeBee,x,y));
                        }
                    }
                }else{
                    if(IsStart==true)
                    x+=3;
                    if(x>ScreenWidth-BeeCacheBitmap.getWidth()/4){
                        Xbeedirection=true;
                        if(Iscry==false){
                            bees.clear();
                            bees.add(new Bee(Bee,x,y));
                        }
                    }
                }
            return bitmap;
        }

        @Override
        public int getX() {
            return x;
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
        public void IsBeAttacked(ArrayList<GameImage> object){
                for(GameImage monster:(ArrayList<GameImage>)object.clone()){
                   if(monster instanceof MonsterBirdie){
                        MonsterBirdie monsterbirdie=(MonsterBirdie)monster;
                        this.x1=x+width/2;
                        this.x2=monster.getX()+ monster.getWidth()/2;
                        this.y1=y+height/2;
                        this.y2=monster.getY()+ monster.getHeight()/2;
                        this.r2=monster.getHeight()/2+10;
                        if(monsterbirdie.Iscanattack==true){
                            if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2))<=r1+r2){
                                mainmifengs=beecrys;
                                Iscry=true;
                                pool.play(musicId.get(2),1,1,0,0,1);
                                object.remove(monster);
                                if(bloods.size()>0)
                                    bloods.remove(bloods.size()-1);
                            }
                        }
                    }else if(monster instanceof Bat){
                       this.x1=x+width/2;
                       this.x2=monster.getX()+ monster.getWidth()/2;
                       this.y1=y+height/2;
                       this.y2=monster.getY()+ monster.getHeight()/2;
                       this.r2=monster.getHeight()/3+10;
                       if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2))<=r1+r2){
                           mainmifengs=beecrys;
                           Iscry=true;
                           pool.play(musicId.get(2),1,1,0,0,1);
                           object.remove(monster);
                           if(bloods.size()>0)
                               bloods.remove(bloods.size()-1);
                       }
                   } else {
                       this.x1=x+width/2;
                       this.x2=monster.getX()+ monster.getWidth()/2;
                       this.y1=y+height/2;
                       this.y2=monster.getY()+ monster.getHeight()/2;
                       this.r2=monster.getHeight()/2+10;
                       if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2))<=r1+r2){
                           mainmifengs=beecrys;
                           Iscry=true;
                           pool.play(musicId.get(2),1,1,0,0,1);
                           object.remove(monster);
                           if(bloods.size()>0)
                               bloods.remove(bloods.size()-1);
                       }
                   }
                }
            }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ScreenWidth=width;
        ScreenHeight=height;
        init();
        CarnivalX=ScreenWidth;
        thread= new Thread(this);
        thread .start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        MainThreadState=false;
        getHolder().removeCallback(this);
    }
}
