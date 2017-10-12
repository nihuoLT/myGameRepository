package com.example.newgame_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
//import android.app.AlertDialog;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
//import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StartActivity extends Activity implements OnClickListener,AnimationListener,AnimatorListener,Spinner.OnItemSelectedListener{
	private Typeface typeface=null;
	private TextView mytext=null,wendu,tianqi,actiontext;
	private Button start,jianjie,wenda;
	private ImageButton shezhi;
	private Spinner city;
	public Rotate3dAnimation animation3d;
	private AnimatorSet set=null;
	private int time=0,count=2,hour;
	private Calendar calendar;
	private Timer timer=new Timer();
	private List<String> citys=new ArrayList<String>();
	private StringBuffer url=new StringBuffer();
	private String last=".shtml";
	private String[] citylist={"成都","自贡","绵阳","南充","达州","遂宁","广安","巴中","泸州",
			"宜宾","内江","资阳","乐山","眉山","凉山","雅安","甘孜","阿坝","德阳","广元","攀枝花"};
	private String[] daimas={"101270101","101270301","101270401","101270501","101270601","101270701","101270801","101270901","101271001",
	"101271101","101271201","101271301","101271401","101271501","101271601","101271701","101271801","101271901","101272001","101272101","101270201"};
	private String[] ss={"早","上","中","下","晚","午","好","主","人"};
	private String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};//定义特效是否开启标志
	public StringBuffer buffer=new StringBuffer();
	public static StartActivity instance; 
	public boolean IsPeach,IsRain,IsAdd=false;//定义特效是否开启标志
	private TimerTask task=new TimerTask() {
		@Override
		public void run() {
            Message message=Message.obtain();
            message.what=1;
            handler.sendMessage(message);
		}
	};
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				time++;
					switch (time) {//每隔50ms加入ss数组的一个字
					case 50:
						if(hour>=6&&hour<=7){
							buffer.append(ss[0]);
						}else if(hour>7&&hour<11){
							buffer.append(ss[1]);
						}else if(hour>=11&&hour<13){
							buffer.append(ss[2]);
						}else if(hour>=13&&hour<18){
							buffer.append(ss[3]);
						}else{
							buffer.append(ss[4]);
						}
						mytext.setText(buffer.toString().trim());
						break;
					case 100:
						if(hour>=6&&hour<=7){
							buffer.append(ss[1]);
						}else if(hour>7&&hour<11){
							buffer.append(ss[5]);
						}else if(hour>=11&&hour<13){
							buffer.append(ss[5]);
						}else if(hour>=13&&hour<18){
							buffer.append(ss[5]);
						}else{
							buffer.append(ss[1]);
						}
						mytext.setText(buffer.toString().trim());
						break;
					case 150:
						buffer.append(ss[6]);
						mytext.setText(buffer.toString().trim());
						
						break;
					case 200:
						buffer.append(",");
						mytext.setText(buffer.toString().trim());
						break;
					case 250:
						buffer.append(ss[7]);
						mytext.setText(buffer.toString().trim());
						
						break;
					case 300:
						buffer.append(ss[8]);
						mytext.setText(buffer.toString().trim());
						break;
					case 350://最后一个字过后，执行动画
						mytext.startAnimation(animation3d);
						break;
					
				}
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//设置屏幕常亮
		instance=StartActivity.this;
		IsPeach=true;
		IsRain=true;
		setContentView(R.layout.start);
		//getLocaltion();
		SharedPreferences.Editor editor = getSharedPreferences("data",
				MODE_PRIVATE).edit();//将特效标志默认为true，并放入SharedPreferences
				editor.putBoolean("music", true);
				editor.putBoolean("peach", true);
				editor.putBoolean("text", true);
				editor.putBoolean("rain", true);
				editor.commit();
		init();
		animation3d=new Rotate3dAnimation(0,90,0,0,0,false);
        animation3d.setStartOffset(100);
        animation3d.setFillAfter(true);
        animation3d.setDuration(1000);
        set=new AnimatorSet();//执行动画
        set.playTogether(ObjectAnimator.ofFloat(start, "Alpha", 0,0.7f),
        		ObjectAnimator.ofFloat(actiontext, "Alpha", 0,0.7f),
        		ObjectAnimator.ofFloat(jianjie, "Alpha", 0,0.7f),
        		ObjectAnimator.ofFloat(wenda, "Alpha", 0,0.7f));
        set.setDuration(1500);
        set.addListener(this);
        animation3d.setAnimationListener(this);
		timer.schedule(task, 100, 10);
		hour=calendar.get(Calendar.HOUR_OF_DAY);
	}
	@Override
	protected void onResume() {
		SharedPreferences pref = getSharedPreferences("data",
				MODE_PRIVATE);//在onResume方法里获取特效标志
		IsPeach=pref.getBoolean("peach", true);
		IsRain=pref.getBoolean("rain", true);
		if(pref.getBoolean("text", false)){
			actiontext.setVisibility(View.VISIBLE);
		}else{
			actiontext.setVisibility(View.INVISIBLE);
		}
		super.onResume();
	}
	public boolean isNetworkAvailable(Activity activity){//判断手机是否联网
        Context context = activity.getApplicationContext();// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
        		getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            return false;
        }else{
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();// 获取NetworkInfo对象
            if (networkInfo != null && networkInfo.length > 0){
                for (int i = 0; i < networkInfo.length; i++){
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){ // 判断当前网络状态是否为连接状态
                        return true;
                    }
                }
            }
        }
        return false;
    }
	/*public void getLocaltion(){
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				// 获取所有可用的位置提供器
	    List<String> providerList = locationManager.getProviders(true);
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		}else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
				// 当没有可用的位置提供器时，弹出Toast提示用户
			Toast.makeText(this, "No location provider to use",Toast.LENGTH_SHORT).show();
			return;
		}
		Criteria criteria = new Criteria();    
		criteria.setAccuracy(Criteria.ACCURACY_FINE);    
		criteria.setAltitudeRequired(false);  
		criteria.setBearingRequired(false);  
		criteria.setCostAllowed(false);    
		criteria.setPowerRequirement(Criteria.POWER_LOW);  
		provider = locationManager.getBestProvider(criteria, true); 
		location = locationManager.getLastKnownLocation(provider);
				if (location != null) {
				// 显示当前设备的位置信息
					showLocal(location);
				}
		locationManager.requestLocationUpdates(provider,  2000, 10,locationListener);			
	}
	LocationListener locationListener = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle
		extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
		@Override
		public void onLocationChanged(Location location) {
			//StartActivity.this.location=location;
			StartActivity.this.location=location;
		}
		};*/
	public void init(){
		for(int i=0;i<citylist.length;i++){
			citys.add(citylist[i]);
		}
		typeface=Typeface.createFromAsset(getAssets(), "pty.TTF");
		mytext=(TextView) findViewById(R.id.mytext);
		actiontext=(TextView) findViewById(R.id.actiontext);
		start=(Button) findViewById(R.id.gamestart);
		jianjie=(Button) findViewById(R.id.jianjie);
		wenda=(Button) findViewById(R.id.wenda);
		shezhi=(ImageButton) findViewById(R.id.shezhi);
		city=(Spinner) findViewById(R.id.spinner_city);
		mytext.setTypeface(typeface);
		actiontext.setTypeface(typeface);
		start.setTypeface(typeface);
		start.setOnClickListener(this);
		jianjie.setTypeface(typeface);
		jianjie.setOnClickListener(this);
		wenda.setTypeface(typeface);
		wenda.setOnClickListener(this);
		shezhi.setOnClickListener(this);
		TextView data=(TextView) findViewById(R.id.time);
		TextView test=(TextView)findViewById(R.id.test);
		wendu=(TextView)findViewById(R.id.wendu);
		tianqi=(TextView)findViewById(R.id.tianqi);
		calendar=Calendar.getInstance();
        data.setTypeface(typeface);
        test.setTypeface(typeface);
		data.setText(calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.DAY_OF_MONTH));
		test.setText(weeks[calendar.get(Calendar.DAY_OF_WEEK)-1]);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.adapter_mytopactionbar_spinner,citys){  
            @Override  
            public View getDropDownView(int position, View convertView, ViewGroup parent) {  
                if (convertView == null){  
                    convertView = getLayoutInflater().inflate(R.layout.adapter_mytopactionbar_spinner_item, parent, false);   
                }  
                TextView spinnerText=(TextView)convertView.findViewById(R.id.spinner_textView);  
                spinnerText.setText(getItem(position));  
              return convertView;  
            }  
        };  
		city.setAdapter(adapter); 
		city.setSelection(2,true);
		city.setOnItemSelectedListener(this);
		city.setEnabled(false);
		if(isNetworkAvailable(this)){
			GetData getData=new GetData();
			//String url="http://www.weather.com.cn/data/sk/101270401.html";
			String url="http://m.weather.com.cn/mweather/101270401.shtml";
			//String url="http://api.map.baidu.com/geocoder?output=json&location=";
			//String url="http://maps.google.com/maps/api/geocode/json?latlng=";
			getData.execute(url.toString());
		}else{
			wendu.setText("null");
			tianqi.setText("null");
			Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gamestart:
			Intent intent=new Intent(StartActivity.this,MainActivity.class);
			startActivity(intent);
			StartActivity.this.finish();
			break;
		case R.id.shezhi:
			Intent intenttwo=new Intent(StartActivity.this,DialogActivity.class);
			startActivity(intenttwo);
			//StartActivity.this.finish();
			break;
		case R.id.wenda:
            FindQQ findQQTask=new FindQQ();
            String path= Environment.getExternalStorageDirectory().getPath().toString().trim() +"/Tencent/MobileQQ";
            findQQTask.execute(path);
			break;
		}
	}
	@Override
	protected void onStop() {
		super.onStop();
	}
	@Override
	public void onAnimationEnd(Animation anim) {
		if(anim.hashCode()==animation3d.hashCode()){
			mytext.setVisibility(View.GONE);
			buffer.reverse();
			set.start();
		}
			
	}
	@Override
	public void onAnimationRepeat(Animation anim) {
		
	}
	@Override
	public void onAnimationStart(Animation anim) {
		
	}
	@Override
	public void onAnimationCancel(Animator anim) {
		
	}
	@Override
	public void onAnimationEnd(Animator anim) {
		start.setEnabled(true);
		jianjie.setEnabled(true);
		wenda.setEnabled(true);
		IsAdd=true;
	}
	@Override
	public void onAnimationRepeat(Animator anim) {
		
	}
	@Override
	public void onAnimationStart(Animator anim) {
		
	}
	class GetData extends AsyncTask<String,Integer, String>{

		private Document doc;
		private String tempmin,tempmax,weather;
		protected String doInBackground(String... params) {
			
//			HttpResponse httpResponse;
//			String response;
//			try {
//				/*StringBuilder url = new StringBuilder();
//				url.append(params[0]);
//				url.append(location.getLatitude()).append(",");
//				url.append(location.getLongitude());
//				url.append("&key=37492c0ee6f924cb5e934fa08c6b1676");
//				HttpClient httpClient = new DefaultHttpClient();
//				HttpGet httpGet = new HttpGet(url.toString());
//			    httpResponse = httpClient.execute(httpGet);
//				if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = httpResponse.getEntity();
//				response = EntityUtils.toString(entity,"utf-8");
//				JSONObject jsonObject = new JSONObject(response);
//				JSONArray resultArray = jsonObject.getJSONArray("results");
//				if (resultArray.length() > 0) {
//				JSONObject subObject = resultArray.
//				getJSONObject(0);
//			    String address = subObject.getString("formatted_address");*/
//				HttpClient httpClient = new DefaultHttpClient();
//				HttpGet httpGet = new HttpGet(params[0]);
//				httpResponse = httpClient.execute(httpGet);
//				if (httpResponse.getStatusLine().getStatusCode() == 200) {
//					HttpEntity entity = httpResponse.getEntity();
//					response= EntityUtils.toString(entity,"utf-8");
//					JSONObject jsonObject = new JSONObject(response);
//					JSONArray resultArray = jsonObject.getJSONArray("weatherinfo");
//					if (resultArray.length() > 0) {
//					JSONObject subObject = resultArray.getJSONObject(0);
//				    //String address = subObject.getString("formatted_address");
//				    }
//				}
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			try {
				doc=Jsoup.connect(params[0]).timeout(5000).get();
				Element min=doc.getElementById("tempmin");
				Element max=doc.getElementById("tempmax");
				if(min!=null)
				tempmin=min.text();
				if(max!=null)
				tempmax=max.text();
				Element days7 = doc.select("div.days7").first();
				if(days7!=null)
				weather=days7.getElementsByTag("img").attr("alt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(tempmax!=null){
				wendu.setText(tempmin+"~"+tempmax);
			}else{
				wendu.setText("最低温度："+tempmin);
			}
			tianqi.setText(weather);
			city.setEnabled(true);
			url.delete(0, url.length());
		}
	}
//  Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);//手机振动
//  long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
//  vibrator.vibrate(pattern,2);
	class FindQQ extends AsyncTask<String ,Integer,String> {
        public List<String> qqarray=new ArrayList<String>();
        public String qqnumber,code="0";
        public Intent intent=null;
        @Override
        protected void onPreExecute() {
        	intent=new Intent(StartActivity.this,ExitActivity.class);
        }
        @Override
        protected String doInBackground(String... params) {
            File[] files = new File(params[0]).listFiles();
            if(files!=null){
                for (File file : files) {
                    if(file.getName().trim().length()>=5&&file.getName().trim().length()<=11){
                        if (isNumeric(file.getName().trim())) {
                        	qqnumber=file.getName().trim();
                            qqarray.add(qqnumber);
                        }
                    }
                }
            }
            for (String ss : qqarray) {
                if(ss.trim().equals("1657004367")){
                	code="1";
                }else if(ss.trim().equals("2453090797")){
                	code="2";
                }else{
                	if(code.equals("0")){
                		code="3";
                	}
                	
                }
            }
            return code;
        }

        @Override
        protected void onPostExecute(String code) {
            super.onPostExecute(code);
            intent.putExtra("code", code);
            startActivity(intent);
        }
        public  boolean isNumeric(String str){
            for (int i = 0; i < str.length(); i++){
                if (!Character.isDigit(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intenttwo=new Intent(StartActivity.this,ExitActivity.class);
			intenttwo.putExtra("TagText", "start");
			startActivity(intenttwo);
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		if (locationManager != null) {
//			locationManager.removeUpdates(locationListener);// 关闭程序时将监听器移除
//		}
	}
	@Override
	public void onItemSelected(AdapterView<?> adapter, View view, int index,
			long arg3) {
		url=new StringBuffer();
		count=index;
		if(isNetworkAvailable(StartActivity.this)){
			GetData getData=new GetData();
			url.append("http://m.weather.com.cn/mweather/");
			url.append(daimas[index]);
			url.append(last);
			getData.execute(url.toString());
		}else{
			wendu.setText("无网络");
			tianqi.setText("无网络");
			Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
		}
		city.setEnabled(false);
		adapter.setVisibility(View.VISIBLE);
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> adapter) {
		adapter.setVisibility(View.VISIBLE);
	}
}
