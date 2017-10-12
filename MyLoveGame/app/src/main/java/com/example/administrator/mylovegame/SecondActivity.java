package com.example.administrator.mylovegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class SecondActivity extends Activity implements DialogInterface.OnClickListener,View.OnClickListener{
    public GameView view;
    public ProgressBar progressBar;
    public RelativeLayout layout;
    public ImageButton pause,bomb;
    public Bitmap missile;
    public Handler handler;
    public Runnable finish;
    public boolean state=true;
    public int progress=5000;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if(progress<progressBar.getMax()){
                progress+=1;
            }
            progressBar.setProgress(progress);
            handler.postDelayed(runnable, 10);
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent gamemusic=new Intent(SecondActivity.this,GameMusic.class);
        setContentView(R.layout.activity_two);
        startService(gamemusic);
        init();
        handler=new Handler();
        finish=new Runnable() {
            @Override
            public void run() {
                if(view.Isend==true){
                    Intent intent = new Intent(SecondActivity.this,GameMusic.class);
                    stopService(intent);
                    layout.destroyDrawingCache();
                    SecondActivity.this.finish();
                    System.gc();
                }
                handler.postDelayed(finish,10);
            }
        };
        handler.post(finish);
        handler.post(runnable);
    }
    public void init(){
        view=(GameView)findViewById(R.id.gameview);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        layout=(RelativeLayout) findViewById(R.id.twolayout);
        pause=(ImageButton) findViewById(R.id.pause);
        bomb=(ImageButton) findViewById(R.id.bomb);
        missile= BitmapFactory.decodeResource(getResources(),R.drawable.missile);
        pause.setOnClickListener(this);
        bomb.setOnClickListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            view.stop();
            AlertDialog dialog = new AlertDialog.Builder(SecondActivity.this)
                    .setTitle("提示").setMessage("是否退出游戏？").setNeutralButton("退出", this)
                    .setNegativeButton("继续", this).create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==-2){
            view.start();
            dialog.dismiss();
        }else{
            SecondActivity.this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            dialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(finish);
        view.destroyDrawingCache();
        layout=null;
        view=null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_UI_HIDDEN:
                Intent intent = new Intent(SecondActivity.this,GameMusic.class);
                stopService(intent);
                layout.destroyDrawingCache();
                view.getHolder().removeCallback(null);
                System.gc();
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pause:
                if(state==true){
                    handler.removeCallbacks(runnable);
                    view.stop();
                    view.setEnabled(false);
                    pause.setBackgroundResource(R.mipmap.pause_s);
                    state=false;
                }else{
                    handler.post(runnable);
                    view.start();
                    view.setEnabled(true);
                    pause.setBackgroundResource(R.mipmap.pause_p);
                    state=true;
                }
                break;
            case R.id.bomb:
                if(view.bombs.size()<1&&progress>=progressBar.getMax()){
                    progress=0;
                    view.bombs.add(new Missile(missile,view));
                }
                break;
        }
    }
}
