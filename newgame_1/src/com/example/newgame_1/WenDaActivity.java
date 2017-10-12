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
				allnumber.setText("��ش���"+number+"����");
			    result.setText("��ȷ�� "+index+"      "+"���� "+(number-index));
				progress+=0.05f;
				ratingBar.setRating(progress);
				if(progress>(totalPrice*5)){
					k=1;
				}
				break;
			}
		};
	};
	private String[] problems={"","������н�,ľ,ˮ,��,���У��ĸ��������","�����������ŷɵ�����ʲô��","��ʷ���ĸ����ܵ���죿","�ԡ����ֱ����������µ��ǣ�",
    "�׹�����д��ʲô����ģ�","��������������ǣ�","����ūδ�𣬺��Լ�Ϊ����˭�ĺ��ԣ�","�������ء���ָ�ҹ��ĸ�������������أ�","�������������ʲô���ģ�",
	"�й�����������Ǻ�ʱ���ֵģ�","�������㣬�����߻����С����¡���ָ��",	"��һ���������ʱ�ڵ�����������˵�ġ�ĳĳ����ն�������������ĳĳ��˭��","������ĳ���ʱ��ͨ��Ϊ",
	"��ʷ�ϵ�һ�������������Ͼٰ����籭�������������ң�","�������Դ��","ţ��������ֳ��ڣ�","��֪��������Ѫ��Ϊ��ʲô��","�й��������õ������������ǣ�",
	"�й�½����͵��ڣ�","�й���ǳ�ĺ�","�����е�������","�׳ơ��Ĳ��󡱵Ķ����ǣ�","�������װ����ǲ��ǰ��顱��˭�����","��ױ��µġ�����ֱ����ǧ�ߣ�������������족ָ�����ĸ��羰����",
	"���ɹ١���������ָ��","��������ʲô�����ı�ƣ�","��������������ʲô��","�������ַ�ʽ��JavaScript�������α����ķ�ʽ","android�ֻ���ϵͳ�ǣ�","ƻ��ϵͳĿǰ�õ���������",
	"Java���ĸ���˾���������ģ�","���򻨡����տ���õ�塢ĵ����һ�ֻ���û����","����Ƥ���ϻ�Ƥ��ҰţƤ��ʨ��Ƥ��һ����ã�","Ǧ����ʲô��","123456789�ĸ����������ͣ��ĸ����������裿",
	"����������ȸ����������"};
    private String[] answers={"A","D","B","D","D","C","A","A","B","D","A","A","C","C","A","B","D","A","B","A","C","C","B","D","C","A","B","C","D","C","B","A","A","B","D","B"};
    private String[] chooses=new String[100];
    private int[] cuowus=new int[100];
    private String[] option={"","A  ��","B  ľ","C  ���ľ","D  ˮ����","A  ��","B  ��","C  ��","D  ��","A  ���","B  �ܲ�","C  ���Գ�","D  �޳�","A �ɼ�˼��","B  ����","C  ������","D  ������",
    "A  ���","B  ʯ��","C  ����","D  ���","A  ��ɭ��","B  �Ͻ���","C  ������","D  ������ç","A  ��ȥ��","B  ����","C  ���","D  ����","A  �Ĵ����","B  ��ظ�ԭ","C  ����ľ���","D  ���ױ�����ԭ",
    "A  ��","B  ��ë","C  �Ž�","D  ��Ҷ","A  �γ�","B  ����","C  ���ʮ��","D  �ϱ���","A  ����","B  ��ʩ","C  槼�","D  �����","A  ����","B  �ŷ�","C  ����","D  ��","A  һ���µ������","B  ���굽һ��",
    "C  ��������������","D  �����һ��","A  �й��Ͷ���˹","B  �й��ͳ���","C  �ձ��ͺ���","D  Ӣ���͵¹�","A  Ӣ��","B  ����","C  ���ô�","D  ������","A  ������","B  ������","C  ������","D  ������",
    "A  ����","B  ��������","C  ����","D  ����","A  ���ɹ�������","B  �½�ά���������","C  ����������","D  ���Ļ���������","A  �Ĵ����","B  ��³�����","C  ����ľ���","D  ׼�������",
    "A  ����","B  �ƺ�","C  ����","D  �Ϻ�","A  ��ɽ","B  ��ɽ","C  ��ɽ","D  ��ɽ","A  ����","B  ����","C  ��¹","D  ʨ��","A  ����Ѹ","B  �ܽ���","C  �Ž�","D  �ֿ���","A  ��ɽ","B  ̩ɽ","C  ��ɽ","D  ®ɽ",
    "A  �¿�״Ԫ","B  �¿�Ժʿ","C  �¿ƽ�ʿ","D  �¿����","A  ����","B  ����","C  С����","D  ����","A ľ��","B ����","C  ������","D  ������","A  int a=10","B  int * p=*10","C  var a=10","D  int? p = 10;",
    "A  Windows","B  UNIX","C  Chrome OS","D  Linux","A  C++","B  C#","C  Object-c","D  �������","A  �׹��Ĺ�˾","B  sun��˾","C  �ȸ�","D  IBM","A  ����","B  ���չ�","C  ĵ��","D  õ��",
    "A  ����Ƥ","B  �ϻ�Ƥ","C  ʨ��Ƥ","D  ҰţƤ","A  ��","B  ��","C  ��","D  Ǯ","A  1��9","B  9��1","C  1��2","D  2��1","A  ����","B  ѹ��","C  ����","D  ����"};
    
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
		    .setTitle("С��ʾ")
		    .setMessage("�Ƿ�ص��ϴδ����λ�ã�")
		    .setNegativeButton("��", this)
		    .setNeutralButton("��", this)
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
		.setTitle("�����Ŀ��ţ�")
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
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
							Toast.makeText(WenDaActivity.this, "����ȷ�ϰ�ť", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "��"+(count+1)+"�⻹ûѡ���", Toast.LENGTH_SHORT).show();
						}
					}else{
						if(fragment1.flag){
							Toast.makeText(WenDaActivity.this, "����ȷ�ϰ�ť", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "��"+(count+1)+"�⻹ûѡ���", Toast.LENGTH_SHORT).show();
						}
					}
				}else{
					count--;
					mViewPager.setCurrentItem(count%2);
					IsNext=false;
					if(count<problems.length-1){
						if(isOdd(count)){
							fragment2.text.setText("����"+(count+1));
							fragment2.youanser.setText(fragment2.xinxi);
							fragment2.anser="";
							fragment2.timu.setText(problems[count+1]);
			    			fragment2.texta.setText(option[(count+1)*4-3]);
			    			fragment2.textb.setText(option[(count+1)*4-2]);
			    			fragment2.textc.setText(option[(count+1)*4-1]);
			    			fragment2.textd.setText(option[(count+1)*4]);
						}else{
							fragment1.text.setText("����"+(count+1));
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
				Toast.makeText(WenDaActivity.this, "�Ѿ��ǵ�һ���ˣ�����", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.thenext:
				if(chooses[count]==null||chooses[count]==""){
					if(isOdd(count)){
						if(fragment2.flag){
							Toast.makeText(WenDaActivity.this, "����ȷ����ť", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "��"+(count+1)+"�⻹ûѡ���", Toast.LENGTH_SHORT).show();
						}
					}else{
						if(fragment1.flag){
							Toast.makeText(WenDaActivity.this, "����ȷ����ť", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(WenDaActivity.this, "��"+(count+1)+"�⻹ûѡ���", Toast.LENGTH_SHORT).show();
						}
					}
				}else{
						if(count<problems.length-2){
							count++;
							mViewPager.setCurrentItem(count%2);
							IsNext=false;
						if(isOdd(count)){
							fragment2.text.setText("����"+(count+1));
							fragment2.youanser.setText(fragment2.xinxi);
							fragment2.anser="";
							fragment2.timu.setText(problems[count+1]);
			    			fragment2.texta.setText(option[(count+1)*4-3]);
			    			fragment2.textb.setText(option[(count+1)*4-2]);
			    			fragment2.textc.setText(option[(count+1)*4-1]);
			    			fragment2.textd.setText(option[(count+1)*4]);
						}else{
							fragment1.text.setText("����"+(count+1));
							fragment1.youanser.setText(fragment2.xinxi);
							fragment1.anser="";
							fragment1.timu.setText(problems[count+1]);
			    			fragment1.texta.setText(option[(count+1)*4-3]);
			    			fragment1.textb.setText(option[(count+1)*4-2]);
			    			fragment1.textc.setText(option[(count+1)*4-1]);
			    			fragment1.textd.setText(option[(count+1)*4]);
						}
					}else{
						Toast.makeText(WenDaActivity.this, "�Ѿ������һ���ˣ�����", Toast.LENGTH_SHORT).show();
					}
    		}
			break;
		case R.id.queren:
		    if(isOdd(count)){
		    	if(fragment2.anser.equals("")){
		    		Toast.makeText(WenDaActivity.this, "��ѡ���", Toast.LENGTH_SHORT).show();
		    	}else{
		    		IsNext=true;
		    		chooses[count]=fragment2.anser;
		    	}
		    }else{
		    	if(fragment1.anser.equals("")){
		    		Toast.makeText(WenDaActivity.this, "��ѡ���", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(WenDaActivity.this, "���ٻش�5���⣬���ܲ鿴���������", Toast.LENGTH_SHORT).show();
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
				dialog.setMessage("��ϲ��ȫ����ˣ�");
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
						fragment2.text.setText("����"+(count+1));
						fragment2.youanser.setText(fragment2.xinxi);
						fragment2.anser="";
						fragment2.timu.setText(problems[count+1]);
		    			fragment2.texta.setText(option[(count+1)*4-3]);
		    			fragment2.textb.setText(option[(count+1)*4-2]);
		    			fragment2.textc.setText(option[(count+1)*4-1]);
		    			fragment2.textd.setText(option[(count+1)*4]);
					}else{
						fragment1.text.setText("����"+(count+1));
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
