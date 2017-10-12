package com.example.newgame_1;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ExitActivity extends Activity implements OnClickListener{

	private Button yes=null,no=null;
	private TextView hint=null;
	private String tag="",code="";
	private Timer timer=new Timer();
	private TimerTask task=new TimerTask() {
		@Override
		public void run() {
			Intent intenttwo=new Intent(ExitActivity.this,StartActivity.class);
			startActivity(intenttwo);
			ExitActivity.this.finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.exitlayout);
		Intent intent=getIntent();
		tag=intent.getStringExtra("TagText");
		code=intent.getStringExtra("code");
		hint=(TextView) findViewById(R.id.myhiti);
		yes=(Button) findViewById(R.id.yes);
		no=(Button) findViewById(R.id.no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		
        if(tag!=null&&tag!=""){
        	if(tag.trim().equals("wenda")){
        		hint.setText("退出游戏或者返回主界面？");
        		yes.setText("退出");
        		no.setText("返回");
        	}
		}
		if(code!=""&&code!=null){
			hint.setLines(1);
			if(code.trim().equals("1")){
				hint.setText("欢迎作者进入趣味问答模块");
				hint.setTextSize(18);
				hint.setLines(2);
				yes.setText("进入");
			}else if(code.trim().equals("2")){
				hint.setText("欢迎兰悦师妹进入趣味问答模块");
				hint.setTextSize(18);
				hint.setLines(2);
				yes.setText("进入");
			}else if(code.trim().equals("3")){
				hint.setText("未获取到指定qq号,5秒后退出");
				hint.setTextSize(18);
				hint.setTextColor(Color.RED);
				hint.setLines(2);
				yes.setVisibility(View.GONE);
				no.setVisibility(View.GONE);
				timer.schedule(task, 5000);
			}else{
				hint.setText("未获取到任何qq号,5秒后退出");
				hint.setTextSize(18);
				hint.setTextColor(Color.YELLOW);
				hint.setLines(2);
				yes.setVisibility(View.GONE);
				no.setVisibility(View.GONE);
				timer.schedule(task, 5000);
			}
		}
		
	}
	@Override
	protected void onStop() {
		if(timer!=null)
			timer.cancel();
		if(task!=null)
			task.cancel();
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		if(timer!=null)
			timer.cancel();
		if(task!=null)
			task.cancel();
		super.onDestroy();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yes:
			ExitActivity.this.finish();
			if(tag!=""&&tag!=null){
				if(tag.trim().equals("start")){
					 StartActivity.instance.finish();
				}else if(tag.trim().equals("main")){
					MainActivity.instance.finish();
				}else if(tag.trim().equals("wenda")){
					WenDaActivity.instance.finish();
				}
			}
			if(code!=""&&code!=null){
				if(code.trim().equals("1")||code.trim().equals("2")){
					StartActivity.instance.finish();
					Intent intenttwo=new Intent(ExitActivity.this,WenDaActivity.class);
					startActivity(intenttwo);
					ExitActivity.this.finish();
				}
			}
			break;

		case R.id.no:
			if(tag!=""&&tag!=null){
				if(tag.trim().equals("start")){
					Intent intenttwo=new Intent(ExitActivity.this,StartActivity.class);
					startActivity(intenttwo);
					ExitActivity.this.finish();
				}else if(tag.trim().equals("main")){
					MainActivity.instance.gameView.start();
					Intent intenttwo=new Intent(ExitActivity.this,MainActivity.class);
					startActivity(intenttwo);
					ExitActivity.this.finish();
				}else if(tag.trim().equals("wenda")){
					Intent intenttwo=new Intent(ExitActivity.this,StartActivity.class);
					startActivity(intenttwo);
					WenDaActivity.instance.finish();
					ExitActivity.this.finish();
				}
			}
			if(code!=""&&code!=null){
				if(code.trim().equals("1")||code.trim().equals("2")){
					Intent intenttwo=new Intent(ExitActivity.this,StartActivity.class);
					startActivity(intenttwo);
					ExitActivity.this.finish();
				}
			}
			break;
		}
		
	}

}
