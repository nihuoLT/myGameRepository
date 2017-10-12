package com.example.administrator.mylovegame;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ant.liao.GifView;


/**
 * Created by Administrator on 2016/12/24 0024.
 */
public class FourActivity extends Activity implements View.OnClickListener{
    public Button gameexplain,play,explain_bee,explain_birdie,explain_demon,explain_dragon,explain_doctor,explain_big,explain_small,explain_bat;
    public TextView tishitext,explaintext;
    public ScrollView scrollView;
    public LinearLayout linearLayout;
    public Animator scaley;
    public Typeface typeface;
    public GifView jieshuo;
    public StringBuffer buffer;
    public int display_w,display_h;
    public boolean IsStart=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_four);
        WindowManager manager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics=new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		display_w=displayMetrics.widthPixels;
		display_h=displayMetrics.heightPixels;
        init();
        gameexplain.setOnClickListener(this);
        play.setOnClickListener(this);
        explain_bee.setOnClickListener(this);
        explain_birdie.setOnClickListener(this);
        explain_demon.setOnClickListener(this);
        gameexplain.setTypeface(typeface);
        play.setTypeface(typeface);
        explain_bee.setTypeface(typeface);
        explain_birdie.setTypeface(typeface);
        explain_demon.setTypeface(typeface);
        explaintext.setTypeface(typeface);
        explain_dragon.setTypeface(typeface);
        explain_doctor.setTypeface(typeface);
        explain_big.setTypeface(typeface);
        explain_small.setTypeface(typeface);
        explain_bat.setTypeface(typeface);
    }
    public void init(){
        gameexplain=(Button)findViewById(R.id.gameexplain);
        explaintext=(TextView)findViewById(R.id.explaintext);
        play=(Button)findViewById(R.id.play);
        scrollView=(ScrollView)findViewById(R.id.myScroll);
        linearLayout=(LinearLayout)scrollView.findViewById(R.id.mylin);
        explain_bee=(Button)linearLayout.findViewById(R.id.explain_bee);
        explain_birdie=(Button)linearLayout.findViewById(R.id.explain_birdie);
        explain_demon=(Button)linearLayout.findViewById(R.id.explain_demon);
        explain_dragon=(Button)linearLayout.findViewById(R.id.explain_dragon);
        explain_doctor=(Button)linearLayout.findViewById(R.id.explain_doctor);
        explain_big=(Button)linearLayout.findViewById(R.id.explain_big);
        explain_small=(Button)linearLayout.findViewById(R.id.explain_small);
        explain_bat=(Button)linearLayout.findViewById(R.id.explain_bat);
        jieshuo=(GifView) findViewById(R.id.jieshuo);
        scaley= ObjectAnimator.ofFloat(explaintext,"ScaleY",0,1);
        scaley.setDuration(500);
        jieshuo.setGifImageType(GifView.GifImageType.COVER);
        jieshuo.setGifImage(R.drawable.ali);
        typeface=Typeface.createFromAsset(getAssets(),"pty.TTF");
        buffer=new StringBuffer();
    }
    private void showWindow(View v) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("游戏中的小蜜蜂会自动移动，你的职责是点击怪物以保护它.右上角的蓝色桃心是小蜜蜂的血条，游戏初始时小蜜蜂有3滴血。" +
                "再次点击按钮隐藏提示框将。");
        View popwindowview= LayoutInflater.from(FourActivity.this).inflate(R.layout.popwindow, null);
        tishitext=(TextView)popwindowview.findViewById(R.id.tishitext);
        tishitext.setTypeface(typeface);
        tishitext.setText(buffer, TextView.BufferType.EDITABLE);
        final PopupWindow popupWindow=new PopupWindow(popwindowview,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.boxbg));
        popupWindow.showAtLocation(v, Gravity.RIGHT,0,0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gameexplain:
                showWindow(v);
                break;
            case R.id.play:
                Intent toSecond=new Intent(FourActivity.this,SecondActivity.class);
                startActivity(toSecond);
                finish();
                break;
            case R.id.explain_bee:
                buffer.delete(0,buffer.length());
                buffer.append("小蜜蜂是游戏中需要你保护的对象。它的初始生命值为3，受到怪物攻击后生命值减1。");
                explaintext.setText(buffer, TextView.BufferType.EDITABLE);
                if(explaintext.getVisibility()==View.INVISIBLE)
                explaintext.setVisibility(View.VISIBLE);
                if(IsStart==true){
                    scaley.start();
                    IsStart=false;
                }
                break;
            case R.id.explain_birdie:
                buffer.delete(0,buffer.length());
                buffer.append("怪物乌鸦会在手机屏幕的左，上，右三个方向随机出现，它的血量值为1，消灭后得10分。如果你不消灭它，它会一直在屏幕中随机飞行，不会退出屏幕。");
                explaintext.setText(buffer, TextView.BufferType.EDITABLE);
                if(explaintext.getVisibility()==View.INVISIBLE)
                explaintext.setVisibility(View.VISIBLE);
                if(IsStart==true){
                    scaley.start();
                    IsStart=false;
                }
                break;
            case R.id.explain_demon:
                buffer.delete(0,buffer.length());
                buffer.append("小恶魔只会在屏幕的左侧出现，它的血量值为1，消灭后得5分。如果你不消灭它，它沿直线飞行，直到飞出屏幕.");
                explaintext.setText(buffer, TextView.BufferType.EDITABLE);
                if(explaintext.getVisibility()==View.INVISIBLE)
                explaintext.setVisibility(View.VISIBLE);
                if(IsStart==true){
                    scaley.start();
                    IsStart=false;
                }
                break;
        }
    }
}
