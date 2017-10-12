package com.example.newgame_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DialogActivity extends Activity implements OnCheckedChangeListener,OnClickListener{

	private Switch wlan,wlan_2,wlan_3,wlan_4;
	private Button queding;
	private boolean IsbgMusic=false,IsPeach=false,IsText=false,IsRain=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
		init();
		SharedPreferences pref = getSharedPreferences("data",
				MODE_PRIVATE);
		wlan.setChecked(pref.getBoolean("music", false));
		wlan_2.setChecked(pref.getBoolean("peach", false));
		wlan_3.setChecked(pref.getBoolean("text", false));
		wlan_4.setChecked(pref.getBoolean("rain", false));
	}
	@Override
	public void onClick(View v) {
		//Toast.makeText(DialogActivity.this, "646", Toast.LENGTH_SHORT).show();
		switch (v.getId()) {
		case R.id.myclose:
			SharedPreferences.Editor editor = getSharedPreferences("data",
					MODE_PRIVATE).edit();
			        //editor.clear().commit();
			        editor.remove("music");
			        editor.remove("peach");
			        editor.remove("text");
			        editor.remove("rain");
					editor.putBoolean("music", IsbgMusic);
					editor.putBoolean("peach", IsPeach);
					editor.putBoolean("text", IsText);
					editor.putBoolean("rain", IsRain);
					editor.commit();
			Intent intenttwo=new Intent(DialogActivity.this,StartActivity.class);
			startActivity(intenttwo);
			DialogActivity.this.finish();
			break;
		}
	}
	public void init(){
		queding=(Button) findViewById(R.id.myclose);
		queding.setOnClickListener(this);
		wlan=(Switch)findViewById(R.id.wlan);
		wlan_2=(Switch)findViewById(R.id.wlan_2);
		wlan_3=(Switch)findViewById(R.id.wlan_3);
		wlan_4=(Switch)findViewById(R.id.wlan_4);
		wlan.setOnCheckedChangeListener(this);
		wlan_2.setOnCheckedChangeListener(this);
		wlan_3.setOnCheckedChangeListener(this);
		wlan_4.setOnCheckedChangeListener(this);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SharedPreferences.Editor editor = getSharedPreferences("data",
					MODE_PRIVATE).edit();
			        //editor.clear().commit();
			        editor.remove("music");
		            editor.remove("peach");
		            editor.remove("text");
		            editor.remove("rain");
					editor.putBoolean("music", IsbgMusic);
					editor.putBoolean("peach", IsPeach);
					editor.putBoolean("text", IsText);
					editor.putBoolean("rain", IsRain);
					editor.commit();
		}
		return super.onKeyDown(keyCode, event);
	}
//	protected void onDestroy() {
//		SharedPreferences.Editor editor = getSharedPreferences("data",
//				MODE_PRIVATE).edit();
//		        editor.clear();
//				editor.putBoolean("music", IsbgMusic);
//				editor.putBoolean("peach", IsPeach);
//				editor.putBoolean("text", IsText);
//				editor.commit();
//		super.onDestroy();
//	}
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.wlan:
			if(buttonView.isChecked()){
				IsbgMusic=true;
			}else{
				IsbgMusic=false;
			}
			break;
		case R.id.wlan_2:
			if(buttonView.isChecked()){
				IsPeach=true;
			}else{
				IsPeach=false;
			}
			break;
		case R.id.wlan_3:
			if(buttonView.isChecked()){
				IsText=true;
			}else{
				IsText=false;
			}
			break;
		case R.id.wlan_4:
			if(buttonView.isChecked()){
				IsRain=true;
			}else{
				IsRain=false;
			}
			break;
		}
		//Toast.makeText(this,"IsbgMusic:"+IsbgMusic+"IsPeach:"+IsPeach+"IsText:"+IsText ,Toast.LENGTH_LONG).show();
	}
	
}
