package com.example.newgame_1;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class WenDaActivity extends FragmentActivity implements OnClickListener,AnimationListener,DialogInterface.OnClickListener{
	private ViewPager mViewPager;
	private RatingBar ratingBar;
	private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
	private FragmentManager mFragmentManager;
	private FragmentOne fragment1,fragment2;
	public Button previous, next,queren,jieshu,back,lookjieguo;
	private TextView jieguo,allnumber,result;
	private Animation in_1,in_2,out_1,out_2;
	private ViewFlipper flipper;
	private AlertDialog dialog=null,dialog2=null;
	private String s="";
	private int count=0,index=0,k=1,number=0,oder=0;
	private float f1,f2,progress=0;
	private boolean isdo=true,IsUp=false,IsNext=false;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	public static WenDaActivity instance; 
	private Timer timer=new Timer();
	private TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			Message message=Message.obtain();
			message.what=k;
			//message.setTarget(handler);
			//message.sendToTarget();
			handler.sendMessage(message);
		}
	};
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String ss="";
			    for(int i=0;i<chooses.length;i++){
			    	if(chooses[i]!=null){
			    		ss+=(i+1)+", "+chooses[i]+"  ";
			    	}	
			    }
				jieguo.setText(ss);
				
				break;
			case 2:
				if(isdo){
					for(int j=0;j<chooses.length;j++){
						if(chooses[j]!=null){
							number++;
							if(j<answers.length){
								if(chooses[j]==answers[j]){
									index++;
								}else{
									cuowus[oder]=j+1;
									oder++;
								}
							}
						}
					}
				    for(int i=0;i<cuowus.length;i++){
				    	if(cuowus[i]!=0){
				    		s+=(cuowus[i])+", "+" ";
				    	}	
				    }
					isdo=false;
				}
				f1=index*0.1f;
				f2=number*0.1f;
				float totalPrice=getDecimal(f1/f2);
				allnumber.setText("你回答了"+number+"道题");
			    result.setText("正确： "+index+"      "+"错误： "+(number-index));
				progress+=0.05f;
				ratingBar.setRating(progress);
				if(progress>(totalPrice*5)){
					k=1;
				}
				break;
			}
		};
	};
	private String[] problems={"","天地五行金,木,水,火,土中，哪个的腿最长？","三国演义中张飞的妈姓什么？","历史上哪个人跑的最快？","以“无字碑”名扬天下的是：",
    "甲骨文是写在什么上面的？","世界上最长的蟒蛇是？","“匈奴未灭，何以家为”是谁的豪言？","“紫土地”是指我国哪个地形区域的土地？","最早的扇子是用什么做的？",
	"中国的尼姑最早是何时出现的：","沉鱼落雁，闭月羞花”中“闭月”是指：",	"有一句关于三国时期的名言是这样说的”某某月下斩貂蝉“，这里的某某是谁？","世博会的持续时间通常为",
	"历史上第一次两个国家联合举办世界杯的是那两个国家？","橄榄球起源于","牛、马的年轮长在：","你知道蚊子吸血是为了什么吗？","中国最早设置的民族自治区是？",
	"中国陆地最低点在？","中国最浅的海","五岳中的中岳是","俗称“四不象”的动物是：","歌曲“亲爱的那不是爱情”是谁编的曲","李白笔下的“飞流直下三千尺，疑是银河落九天”指的是哪个风景区？",
	"新郎官”最早用来指：","六弦琴是什么乐器的别称：","卫星最多的行星是什么？","下列哪种方式是JavaScript声明整形变量的方式","android手机的系统是？","苹果系统目前用的哪种语言",
	"Java是哪个公司开发出来的？","茉莉花、向日葵、玫瑰、牡丹哪一种花最没劲？","大象皮、老虎皮、野牛皮、狮子皮哪一种最不好？","铅笔姓什么？","123456789哪个数字最勤劳，哪个数字最懒惰？",
	"怎样能让麻雀安静下来？"};
    private String[] answers={"A","D","B","D","D","C","A","A","B","D","A","A","C","C","A","B","D","A","B","A","C","C","B","D","C","A","B","C","D","C","B","A","A","B","D","B"};
    private String[] chooses=new String[100];
    private int[] cuowus=new int[100];
    private String[] option={"","A  火","B  木","C  金和木","D  水和土","A  李","B  马","C  张","D  吴","A  田忌","B  曹操","C  李自成","D  罗成","A 成吉思汗","B  康熙","C  汉武大帝","D  武则天",
    "A  鱼骨","B  石壁","C  象牙","D  龟骨","A  巨森蚺","B  紫金蟒","C  网纹蟒","D  非洲岩莽","A  霍去病","B  卫青","C  李广","D  岳云","A  四川盆地","B  青藏高原","C  塔里木盆地","D  呼伦贝尔草原",
    "A  草","B  羽毛","C  芭蕉","D  树叶","A  宋朝","B  汉朝","C  五代十国","D  南北朝","A  貂蝉","B  西施","C  妲己","D  杨贵妃","A  关羽","B  张飞","C  赵云","D  马超","A  一个月到五个月","B  半年到一年",
    "C  几个月甚至半年","D  半年或一年","A  中国和俄罗斯","B  中国和朝鲜","C  日本和韩国","D  英国和德国","A  英国","B  美国","C  加拿大","D  西班牙","A  耳朵上","B  牙齿上","C  蹄子上","D  鼻子上",
    "A  交配","B  补充能量","C  发育","D  产卵","A  内蒙古自治区","B  新疆维吾尔自治区","C  藏族自治区","D  宁夏回族自治区","A  四川盆地","B  吐鲁番盆地","C  塔里木盆地","D  准噶尔盆地",
    "A  渤海","B  黄海","C  东海","D  南海","A  恒山","B  华山","C  嵩山","D  衡山","A  大象","B  麒麟","C  麋鹿","D  狮鹫","A  陈奕迅","B  周杰伦","C  张杰","D  林俊杰","A  黄山","B  泰山","C  恒山","D  庐山",
    "A  新科状元","B  新科院士","C  新科进士","D  新科秀才","A  吉他","B  二胡","C  小提琴","D  琵琶","A 木星","B 土星","C  天王星","D  海王星","A  int a=10","B  int * p=*10","C  var a=10","D  int? p = 10;",
    "A  Windows","B  UNIX","C  Chrome OS","D  Linux","A  C++","B  C#","C  Object-c","D  汇编语言","A  甲骨文公司","B  sun公司","C  谷歌","D  IBM","A  茉莉花","B  向日癸","C  牡丹","D  玫瑰",
    "A  大象皮","B  老虎皮","C  狮子皮","D  野牛皮","A  铁","B  萧","C  王","D  钱","A  1和9","B  9和1","C  1和2","D  2和1","A  打它","B  压它","C  顶它","D  吓它"};
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		instance=WenDaActivity.this;
		setContentView(R.layout.wenda);
		initFragmetList();
		mFragmentManager = getSupportFragmentManager();
		mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);
		init();
		SharedPreferences pref = getSharedPreferences("data",
				MODE_PRIVATE);
		if(pref.getInt("order", 0)>0){
			count=pref.getInt("order", 0);
		    dialog2=new AlertDialog.Builder(this)
		    .setTitle("小提示")
		    .setMessage("是否回到上次答题的位置？")
		    .setNegativeButton("是", this)
		    .setNeutralButton("否", this)
		    .show();
		    dialog2.setCanceledOnTouchOutside(false);
		}
		//Toast.makeText(WenDaActivity.this, "order"+pref.getInt("order", 1), Toast.LENGTH_SHORT).show();
		timer.schedule(task, 100, 25);
	}
	public float  getDecimal(float decimal) {
		String str=Float.toString(decimal).trim();
		if(str.length()>=6){
			String s1=str.substring(0, 4);
			String s2=str.substring(4, 5);
			String s3=str.substring(5, 6);
			int a1=Integer.valueOf(s2);
			int a2=Integer.valueOf(s3);
			if(a2>=5){
				a1++;
			}
			String last=s1+a1;
			return Float.parseFloat(last);
		}else{
			return decimal;
		}
		
	}
	public void init(){
		in_1=AnimationUtils.loadAnimation(WenDaActivity.this, R.anim.trans1);
		in_1.setAnimationListener(this);
		in_2=AnimationUtils.loadAnimation(WenDaActivity.this, R.anim.trans3);
		in_2.setAnimationListener(this);
		out_1=AnimationUtils.loadAnimation(WenDaActivity.this, R.anim.trans2);
		out_2=AnimationUtils.loadAnimation(WenDaActivity.this, R.anim.trans4);
		mViewPager=(ViewPager)findViewById(R.id.ViewPagerLayout);
		ratingBar=(RatingBar) findViewById(R.id.ratingBar);
		flipper=(ViewFlipper)findViewById(R.id.myfliper);
		previous=(Button)findViewById(R.id.theprevious);
		next=(Button)findViewById(R.id.thenext);
		jieguo=(TextView)findViewById(R.id.jieguo);
		allnumber=(TextView)findViewById(R.id.allnumber);
		result=(TextView)findViewById(R.id.result);
		queren=(Button)findViewById(R.id.queren);
		jieshu=(Button)findViewById(R.id.jieshu);
		lookjieguo=(Button)findViewById(R.id.lookjiehuo);
		back=(Button)findViewById(R.id.fliperback);
		dialog=new AlertDialog.Builder(this)
		.setTitle("答错题目题号：")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		}).create();
		dialog.setCanceledOnTouchOutside(false);
		previous.setOnClickListener(this);
		next.setOnClickListener(this);
		queren.setOnClickListener(this);
		jieshu.setOnClickListener(this);
		back.setOnClickListener(this);
		lookjieguo.setOnClickListener(this);
		initViewPager();
	}
	public void initViewPager() {
		mViewPager.setOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
		mViewPager.setAdapter(mViewPagerFragmentAdapter);
		mViewPager.setCurrentItem(0);
	}

	public void initFragmetList() {
		fragment1 = new FragmentOne();
		fragment2 = new FragmentOne();
		mFragmentList.add(fragment1);
		mFragmentList.add(fragment2);
	}
    public void onClick(View v) {
		switch (v.getId()) {
		case R.id.theprevious:
			if(count>0){
				if(chooses[count]==null||chooses[count]==""){
					if(isOdd(count)){
						if(fragment2.flag){
							Toast.makeText(WenDaActivity.this, "请点击确认按钮", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "第"+(count+1)+"题还没选择答案", Toast.LENGTH_SHORT).show();
						}
					}else{
						if(fragment1.flag){
							Toast.makeText(WenDaActivity.this, "请点击确认按钮", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "第"+(count+1)+"题还没选择答案", Toast.LENGTH_SHORT).show();
						}
					}
				}else{
					count--;
					mViewPager.setCurrentItem(count%2);
					IsNext=false;
					if(count<problems.length-1){
						if(isOdd(count)){
							fragment2.text.setText("问题"+(count+1));
							fragment2.youanser.setText(fragment2.xinxi);
							fragment2.anser="";
							fragment2.timu.setText(problems[count+1]);
			    			fragment2.texta.setText(option[(count+1)*4-3]);
			    			fragment2.textb.setText(option[(count+1)*4-2]);
			    			fragment2.textc.setText(option[(count+1)*4-1]);
			    			fragment2.textd.setText(option[(count+1)*4]);
						}else{
							fragment1.text.setText("问题"+(count+1));
							fragment1.youanser.setText(fragment2.xinxi);
							fragment1.anser="";
							fragment1.timu.setText(problems[count+1]);
			    			fragment1.texta.setText(option[(count+1)*4-3]);
			    			fragment1.textb.setText(option[(count+1)*4-2]);
			    			fragment1.textc.setText(option[(count+1)*4-1]);
			    			fragment1.textd.setText(option[(count+1)*4]);
						}
					}
				}
				
			}else{
				Toast.makeText(WenDaActivity.this, "已经是第一题了！！！", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.thenext:
				if(chooses[count]==null||chooses[count]==""){
					if(isOdd(count)){
						if(fragment2.flag){
							Toast.makeText(WenDaActivity.this, "请点击确定按钮", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "第"+(count+1)+"题还没选择答案", Toast.LENGTH_SHORT).show();
						}
					}else{
						if(fragment1.flag){
							Toast.makeText(WenDaActivity.this, "请点击确定按钮", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "第"+(count+1)+"题还没选择答案", Toast.LENGTH_SHORT).show();
						}
					}
				}else{
						if(count<problems.length-2){
							count++;
							mViewPager.setCurrentItem(count%2);
							IsNext=false;
						if(isOdd(count)){
							fragment2.text.setText("问题"+(count+1));
							fragment2.youanser.setText(fragment2.xinxi);
							fragment2.anser="";
							fragment2.timu.setText(problems[count+1]);
			    			fragment2.texta.setText(option[(count+1)*4-3]);
			    			fragment2.textb.setText(option[(count+1)*4-2]);
			    			fragment2.textc.setText(option[(count+1)*4-1]);
			    			fragment2.textd.setText(option[(count+1)*4]);
						}else{
							fragment1.text.setText("问题"+(count+1));
							fragment1.youanser.setText(fragment2.xinxi);
							fragment1.anser="";
							fragment1.timu.setText(problems[count+1]);
			    			fragment1.texta.setText(option[(count+1)*4-3]);
			    			fragment1.textb.setText(option[(count+1)*4-2]);
			    			fragment1.textc.setText(option[(count+1)*4-1]);
			    			fragment1.textd.setText(option[(count+1)*4]);
						}
					}else{
						Toast.makeText(WenDaActivity.this, "已经是最后一题了！！！", Toast.LENGTH_SHORT).show();
					}
    		}
			break;
		case R.id.queren:
		    if(isOdd(count)){
		    	if(fragment2.anser.equals("")){
		    		Toast.makeText(WenDaActivity.this, "请选择答案", Toast.LENGTH_SHORT).show();
		    	}else{
		    		IsNext=true;
		    		chooses[count]=fragment2.anser;
		    	}
		    }else{
		    	if(fragment1.anser.equals("")){
		    		Toast.makeText(WenDaActivity.this, "请选择答案", Toast.LENGTH_SHORT).show();
		    	}else{
		    		IsNext=true;
		    		chooses[count]=fragment1.anser;
		    	}
		    } 
			break;
		case R.id.jieshu:
			if(jieguo.getText().toString().length()>=30){
			    k=2;
			    isdo=true;
				flipper.setInAnimation(in_1);
				flipper.setOutAnimation(out_1);
				flipper.showNext();
				previous.setEnabled(false);
				next.setEnabled(false);
				queren.setEnabled(false);
				jieshu.setEnabled(false);
				back.setEnabled(false);
			}else{
				Toast.makeText(WenDaActivity.this, "至少回答5个题，才能查看结果！！！", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.fliperback:
			k=1;
			progress=0;
			number=0;
			index=0;
			s="";
			cuowus=null;
			cuowus=new int[100];
			flipper.setInAnimation(in_2);
			flipper.setOutAnimation(out_2);
			flipper.showPrevious();
			previous.setEnabled(false);
			next.setEnabled(false);
			queren.setEnabled(false);
			jieshu.setEnabled(false);
			back.setEnabled(false);
			break;
		case R.id.lookjiehuo:
			if(s==""){
				dialog.setMessage("恭喜您全答对了！");
			}else{
				dialog.setMessage(s);
			}
			
			dialog.show();
			break;
		}	
	}
    public boolean isOdd(int a){   
	    if(a%2 == 1){      
	        return true;   
	    }   
	    return false;   
	}  
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intenttwo=new Intent(WenDaActivity.this,ExitActivity.class);
			intenttwo.putExtra("TagText", "wenda");
			startActivity(intenttwo);
		}
    	return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onStop() {
    	SharedPreferences.Editor editor = getSharedPreferences("data",
				MODE_PRIVATE).edit();
    	editor.remove("order");
    	if(count<answers.length-1){
    		if(IsNext){
        		count++;
        	}
    	}
    	
		editor.putInt("order", count);
		editor.commit();
    	super.onStop();
    }
	class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
		public void onPageSelected(int position) {}
		public void onPageScrollStateChanged(int state) {
		  switch (state) {
		     case 2:
		    	if(isOdd(count)){
		    		fragment2.group.clearCheck();
		    		fragment2.flag=false;
				}else{
					fragment1.group.clearCheck();
					fragment1.flag=false;
				}
			    break;

		  }
	    }
	}
	@Override
	public void onAnimationEnd(Animation anim) {
		if(anim.hashCode()==in_1.hashCode()){
			previous.setEnabled(true);
			next.setEnabled(true);
			queren.setEnabled(true);
			jieshu.setEnabled(true);
			back.setEnabled(true);
		}else if(anim.hashCode()==in_2.hashCode()){
			previous.setEnabled(true);
			next.setEnabled(true);
			queren.setEnabled(true);
			jieshu.setEnabled(true);
			back.setEnabled(true);
		}
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {}
	@Override
	public void onAnimationStart(Animation arg0) {}
	public void onClick(DialogInterface dialog, int which) {
	     if(dialog.hashCode()==dialog2.hashCode()){
	    	 if(which==-2){
	    		 mViewPager.setCurrentItem(count%2);
				 if(isOdd(count)){
						fragment2.text.setText("问题"+(count+1));
						fragment2.youanser.setText(fragment2.xinxi);
						fragment2.anser="";
						fragment2.timu.setText(problems[count+1]);
		    			fragment2.texta.setText(option[(count+1)*4-3]);
		    			fragment2.textb.setText(option[(count+1)*4-2]);
		    			fragment2.textc.setText(option[(count+1)*4-1]);
		    			fragment2.textd.setText(option[(count+1)*4]);
					}else{
						fragment1.text.setText("问题"+(count+1));
						fragment1.youanser.setText(fragment2.xinxi);
						fragment1.anser="";
						fragment1.timu.setText(problems[count+1]);
		    			fragment1.texta.setText(option[(count+1)*4-3]);
		    			fragment1.textb.setText(option[(count+1)*4-2]);
		    			fragment1.textc.setText(option[(count+1)*4-1]);
		    			fragment1.textd.setText(option[(count+1)*4]);
					}
		        }else if(which==-3){
		            dialog.dismiss();
		            count=0;
		        }
	     }
	}
	
}
