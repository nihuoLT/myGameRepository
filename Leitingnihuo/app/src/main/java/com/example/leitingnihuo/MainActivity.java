package com.example.leitingnihuo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements DialogInterface.OnClickListener ,View.OnClickListener,Animator.AnimatorListener,Animation.AnimationListener {

    private MyGameView myGameView;
    private ProgressBar progressBar;
    private RelativeLayout main;
    private Button button,pause,bgstyle;
    private Typeface typeface;
    private View contentView;
    private TextView t1,t2,t3;
    private PopupWindow popupWindow;
    private boolean state=true,flag=true;
    private GridView gview;
    private int times=0;
    private Animator gridview_IN,gridview_OUT;
    //private Animation gridview_IN,gridview_OUT;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private String[] iconName = {"默认图","白云飘","机器猫","小阿狸"};
    private int[] icon = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
    private Runnable endrunnable=new Runnable() {

        @Override
        public void run() {
            timer.cancel();
            task.cancel();
            handler.removeCallbacks(endrunnable);
            Intent intent=new Intent(MainActivity.this,endActivity.class);
            intent.putExtra("score", Long.toString(myGameView.myScore));
            main.destroyDrawingCache();
            startActivity(intent);
            finish();
        }
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    progressBar.setProgress(myGameView.myplaneblood);
                    if(myGameView.IsEnd){
                        handler.post(endrunnable);
                    }
                    break;
            }
        }
    };
    private Timer timer=new Timer();
    private TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Message message=Message.obtain();
            message.what=1;
            handler.sendMessage(message);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Intent musicintent=new Intent(MainActivity.this,BgMusic.class);
        //startService(musicintent);
        init();
        timer.schedule(task,100,10);
    }
    public void init(){
        main=(RelativeLayout)findViewById(R.id.mainlayout);
        myGameView=(MyGameView)findViewById(R.id.mygame);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        button=(Button)findViewById(R.id.show);
        pause=(Button)findViewById(R.id.zanting);
        bgstyle=(Button)findViewById(R.id.bgstyle);
        button.setOnClickListener(this);
        pause.setOnClickListener(this);
        bgstyle.setOnClickListener(this);
        typeface=Typeface.createFromAsset(getAssets(),"pty.TTF");
        gview = (GridView) findViewById(R.id.gview);
        gridview_IN= ObjectAnimator.ofFloat(gview, "ScaleX", 0,1);
        gridview_OUT=ObjectAnimator.ofFloat(gview, "ScaleX", 1,0);
//        gridview_IN= AnimationUtils.loadAnimation(MainActivity.this,R.anim.in);
//        gridview_OUT=AnimationUtils.loadAnimation(MainActivity.this,R.anim.out);
        //gridview_IN.setFillAfter(true);
        //gridview_OUT.setFillAfter(true);
        gridview_OUT.addListener(this);
        //gridview_OUT.setAnimationListener(this);
        data_list = new ArrayList<>();
        getData();
        String [] from ={"image","text"};
        int [] to = {R.id.imagetest,R.id.texttest};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        gview.setAdapter(sim_adapter);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:
                        gridview_OUT.setDuration(500).start();
                        myGameView.index=arg2;
                        //myGameView.BgAdd=true;
                        flag=true;
                        break;
                    case 1:
                        gridview_OUT.setDuration(500).start();
                        myGameView.index=arg2;
                       // myGameView.BgAdd=true;
                        flag=true;
                        break;
                    case 2:
                        gridview_OUT.setDuration(500).start();
                        myGameView.index=arg2;
                        //myGameView.BgAdd=true;
                        flag=true;
                        break;
                    case 3:
                        gridview_OUT.setDuration(500).start();
                        Toast.makeText(MainActivity.this,"暂未开通此背景",Toast.LENGTH_SHORT).show();
                        //myGameView.index=arg2;
                        //myGameView.BgAdd=true;
                        flag=true;
                        break;
                }
            }
        });
        contentView = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.layout, null);
        ImageButton button1 = (ImageButton) contentView.findViewById(R.id.pause);
        ImageButton button2 = (ImageButton) contentView.findViewById(R.id.sos);
        ImageButton button3 = (ImageButton) contentView.findViewById(R.id.bomb);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        t1=(TextView)contentView.findViewById(R.id.text_x);
        t2=(TextView)contentView.findViewById(R.id.text_y);
        t3=(TextView)contentView.findViewById(R.id.text_z);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        t3.setTypeface(typeface);
    }
    public List<Map<String, Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            myGameView.stop();
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("提示").setMessage("是否退出游戏？").setNeutralButton("退出", this)
                    .setNegativeButton("继续", this).create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void onClick(DialogInterface dialog, int which) {
        if(which==-2){
            myGameView.start();
            dialog.dismiss();
        }else{
            MainActivity.this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            dialog.dismiss();
        }
    }
    protected void onDestroy() {
        Intent intent = new Intent(MainActivity.this,BgMusic.class);
        stopService(intent);
        if(myGameView!=null){
            myGameView.destroyDrawingCache();
            myGameView=null;
        }
        main.removeAllViewsInLayout();
        main.destroyDrawingCache();
        setContentView(R.layout.nulllayot);
        System.gc();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent(MainActivity.this,BgMusic.class);
        stopService(intent);
//        if(myGameView!=null){
//            myGameView.destroyDrawingCache();
//            myGameView=null;
//        }
        main.removeAllViewsInLayout();
        main.destroyDrawingCache();
        setContentView(R.layout.nulllayot);
        System.gc();
        super.onStop();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_UI_HIDDEN:
                myGameView.destroyDrawingCache();
                myGameView.getHolder().removeCallback(null);
                myGameView=null;
                main.destroyDrawingCache();
                System.gc();
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show:
                ShowWindow(v);
                if(button.getVisibility()==View.VISIBLE){
                    button.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.zanting:
                if(state==true){
                   // handler.removeCallbacks(runnable);
                    myGameView.stop();
                    button.setEnabled(false);
                    myGameView.setEnabled(false);
                    bgstyle.setEnabled(false);
                    pause.setBackgroundResource(R.drawable.pause_p);
                    state=false;
                }else{
                    //handler.post(runnable);
                    myGameView.start();
                    button.setEnabled(true);
                    myGameView.setEnabled(true);
                    bgstyle.setEnabled(true);
                    pause.setBackgroundResource(R.drawable.pause_s);
                    state=true;
                }
                break;
            case R.id.pause:
                if(button.getVisibility()==View.INVISIBLE){
                    button.setVisibility(View.VISIBLE);
                    if(myGameView.indexyb>0){
                        if(myGameView.YbLIsexist==false&&myGameView.YbRIsexist==false){
                            myGameView.IsAddsos=true;
                            t1.setText("x"+myGameView.indexyb);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"上一次的大招还消失",Toast.LENGTH_SHORT).show();
                        }
                    }
                    popupWindow.dismiss();
                }
                break;
            case R.id.sos:
                if(button.getVisibility()==View.INVISIBLE){
                    button.setVisibility(View.VISIBLE);
                    if(myGameView.indexmyyb>0){
                        if(myGameView.YbBIsexist==false){
                            myGameView.IsAddyb = true;
                            t2.setText("x"+myGameView.indexmyyb);
                        }else {
                            Toast.makeText(MainActivity.this,"上一次的大招还消失",Toast.LENGTH_SHORT).show();
                        }
                    }
                    popupWindow.dismiss();
                }
                break;
            case R.id.bgstyle:
                if(flag){
                    gview.setVisibility(View.VISIBLE);
                    //gview.startAnimation(gridview_IN);
                    gridview_IN.setDuration(500).start();
                    flag=false;
                }else{
                    //gview.startAnimation(gridview_OUT);
                    gridview_OUT.setDuration(500).start();
                    Toast.makeText(MainActivity.this,Boolean.toString(flag),Toast.LENGTH_SHORT).show();
                    flag=true;
                }
                break;
            case R.id.bomb:
                if(button.getVisibility()==View.INVISIBLE){
                    button.setVisibility(View.VISIBLE);
                    if(myGameView.indexbomb>0){
                            myGameView.IsAddbomb = true;
                            t3.setText("x"+myGameView.indexbomb);
                    }
                    popupWindow.dismiss();
                }
                break;
        }
    }
//    public void Location(View view,int i_1,int i_2){
//        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)view.getLayoutParams();
//        params.setMargins(i_1, i_2, 0, 0);
//        view.requestLayout();
//    }
    private void ShowWindow(View view) {
        t1.setText("x"+myGameView.indexyb);
        t2.setText("x"+myGameView.indexmyyb);
        t3.setText("x"+myGameView.indexbomb);
        popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(button.getVisibility()==View.INVISIBLE){
                    button.setVisibility(View.VISIBLE);
                }
            }
        });
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popbg));
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if(animation.hashCode()==gridview_OUT.hashCode()){
           gview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation.hashCode()==gridview_OUT.hashCode()){
            gview.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
