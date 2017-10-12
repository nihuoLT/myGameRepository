package com.example.leitingnihuo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ant.liao.GifView;



import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2017-03-11.
 */
public class StartActivity extends Activity implements View.OnClickListener{
    private ViewFlipper flipper=null,myflipper;
    private RelativeLayout relativeLayout_1=null,relativeLayout_2=null;
    private Animation in,out;
    private GifView gif_1=null,xie_1=null,xie_2=null,sun=null,hua,zuo,you;
    private TextView actiontext;
    private Typeface typeface;
    private int ScreenWidth,ScreenHeight,x,filpperX=0,count=0;
    private boolean flag=true;
    private Timer timer=new Timer();
    private TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Message message=Message.obtain();
            message.what=1;
            handler.sendMessage(message);
        }
    };
    private Timer timer2=new Timer();
    private TimerTask task2=new TimerTask() {
        @Override
        public void run() {
            Message message=Message.obtain();
            message.what=2;
            handler.sendMessage(message);
        }
    };
    private Timer timer3=new Timer();
    private TimerTask task3=new TimerTask() {
        @Override
        public void run() {
            Message message=Message.obtain();
            message.what=3;
            handler.sendMessage(message);
        }
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    flipper.setInAnimation(in);
                    flipper.setOutAnimation(out);
                    actiontext.setVisibility(View.VISIBLE);
                    flipper.showNext();
                    break;
                case 2:
                    x-=2;
                    if(x<=0)
                        x=ScreenWidth;
                    if(gif_1.getVisibility()==View.GONE)
                        gif_1.setVisibility(View.VISIBLE);
                    Location(gif_1, x, 0);
//                    RelativeLayout.LayoutParams params=( RelativeLayout.LayoutParams)gif_1.getLayoutParams();
//                    params.setMargins(x,20,0,0);
//                    gif_1.requestLayout();
                    break;
                case 3:
                    if(filpperX<getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth()&&flag){
                        if(filpperX!=(getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth())/2){
                            filpperX++;
                            if(filpperX==getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth()){
                                myflipper.setDisplayedChild(1);
                                flag=false;
                            }
                        }else if(filpperX==(getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth())/2){
                            myflipper.setDisplayedChild(0);
                            count++;
                            if(count==200){
                                myflipper.setDisplayedChild(2);
                                filpperX++;
                                count=0;
                            }
                        }

                    }
                    if(filpperX>0&&!flag){
                        if(filpperX!=(getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth())/2){
                            filpperX--;
                            if(filpperX==0){
                                myflipper.setDisplayedChild(2);
                                flag=true;
                            }
                        }else if(filpperX==(getWindowManager().getDefaultDisplay().getWidth()-myflipper.getWidth())/2){
                            myflipper.setDisplayedChild(0);
                            count++;
                            if(count==200){
                                myflipper.setDisplayedChild(1);
                                filpperX--;
                                count=0;
                            }
                        }

                    }
                    Location(myflipper, filpperX, 0);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.startlayout);
        init();
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenWidth=displayMetrics.widthPixels;
        ScreenHeight=displayMetrics.heightPixels;
        x=ScreenWidth;
        timer.schedule(task,1000);
        timer2.schedule(task2,2000,10);
        timer3.schedule(task3,100,10);
    }
    public void init(){
        flipper=(ViewFlipper)findViewById(R.id.myflipper);
        relativeLayout_1=(RelativeLayout)flipper.findViewById(R.id.chird_1);
        relativeLayout_2=(RelativeLayout)flipper.findViewById(R.id.chird_2);
        gif_1=(GifView)relativeLayout_2.findViewById(R.id.gif_1);
        xie_1=(GifView)relativeLayout_2.findViewById(R.id.xie_1);
        xie_2=(GifView)relativeLayout_2.findViewById(R.id.xie_2);
        sun=(GifView)relativeLayout_2.findViewById(R.id.sun);
        actiontext=(TextView)findViewById(R.id.actiontext);
        myflipper=(ViewFlipper)relativeLayout_2.findViewById(R.id.viewfilpper);
        hua=(GifView)myflipper.findViewById(R.id.gif_hua);
        zuo=(GifView)myflipper.findViewById(R.id.gif_zuo);
        you=(GifView)myflipper.findViewById(R.id.gif_you);
        myflipper.setDisplayedChild(2);
        hua.setGifImage(R.drawable.zoux);
        hua.setGifImageType(GifView.GifImageType.COVER);
        zuo.setGifImage(R.drawable.zouzuo);
        zuo.setGifImageType(GifView.GifImageType.COVER);
        you.setGifImage(R.drawable.zouyou);
        you.setGifImageType(GifView.GifImageType.COVER);
        gif_1.setGifImageType(GifView.GifImageType.COVER);
        gif_1.setGifImage(R.drawable.xianzi);
        xie_1.setGifImageType(GifView.GifImageType.COVER);
        xie_1.setGifImage(R.drawable.xie_1);
        xie_2.setGifImageType(GifView.GifImageType.COVER);
        xie_2.setGifImage(R.drawable.xie_2);
        sun.setGifImageType(GifView.GifImageType.COVER);
        sun.setGifImage(R.drawable.sun);
        typeface=Typeface.createFromAsset(getAssets(),"pty.TTF");
        Button start=(Button)relativeLayout_2.findViewById(R.id.start);
        start.setOnClickListener(this);
        start.setTypeface(typeface);
        actiontext.setTypeface(typeface);
        in=new AlphaAnimation(0,1);
        in.setDuration(1500);
        in.setFillAfter(true);
        out= AnimationUtils.loadAnimation(StartActivity.this,R.anim.out);
    }
    public void Location(View view,int i_1,int i_2){
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)view.getLayoutParams();
        params.setMargins(i_1, i_2, 0, 0);
        view.requestLayout();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                Intent intentone=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intentone);
                finish();
                break;
        }
    }
}
