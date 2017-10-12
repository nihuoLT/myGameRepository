package com.example.newgame_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnTouchListener{

	private ImageButton left=null,right=null,jump=null;
	public GameView gameView=null;
	public static MainActivity instance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		instance=this;
		setContentView(R.layout.activity_main);
		init();
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			gameView.stop();
			Intent intenttwo=new Intent(MainActivity.this,ExitActivity.class);
			intenttwo.putExtra("TagText", "main");
			startActivity(intenttwo);
		}
		return super.onKeyDown(keyCode, event);
	}
    public void init(){
    	gameView=(GameView) findViewById(R.id.test);
    	left=(ImageButton) findViewById(R.id.left);
    	right=(ImageButton) findViewById(R.id.right);
    	jump=(ImageButton) findViewById(R.id.jump);
    	left.setOnTouchListener(this);
    	right.setOnTouchListener(this);
    	jump.setOnTouchListener(this);
    }
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			switch (v.getId()) {
			case R.id.left:
				gameView.jumpTest.IsMove=true;
				gameView.jumpTest.XdirectionFlag=false;
				gameView.jumpTest.first=2;
            	gameView.jumpTest.index=gameView.jumpTest.first;
            	gameView.jumpTest.size=4;
            	left.setBackgroundResource(R.drawable.zuo_r);
				break;
            case R.id.right:
            	gameView.jumpTest.IsMove=true;
            	gameView.jumpTest.XdirectionFlag=true;
            	gameView.jumpTest.first=0;
            	gameView.jumpTest.index=gameView.jumpTest.first;
            	gameView.jumpTest.size=2;
            	right.setBackgroundResource(R.drawable.you_r);
				break;
            case R.id.jump:
            	if(!gameView.jumpTest.IsJump&&gameView.jumpTest!=null&&gameView.jumpTest.state&&!gameView.jumpTest.Isdo){
            		gameView.jumpTest.Isdo=false;
            		gameView.jumpTest.IsStop=false;
                	gameView.jumpTest.IsJump=true;
                	jump.setBackgroundResource(R.drawable.tiao_r);
            	}
				break;
			}
			
			break;
		case MotionEvent.ACTION_UP:
			switch (v.getId()) {
			case R.id.left:
				left.setBackgroundResource(R.drawable.zuo_b);
				gameView.jumpTest.IsMove=false;
				break;
            case R.id.right:
            	right.setBackgroundResource(R.drawable.you_b);
            	gameView.jumpTest.IsMove=false;
				break;
            case R.id.jump:
            	jump.setBackgroundResource(R.drawable.tiao_b);
				break;
			}
			
			break;
		}
		return true;
	}
}
