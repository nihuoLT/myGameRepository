package com.example.administrator.mylovegame;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.text.InputType;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener,Animation.AnimationListener{
    public MainActivityBgView bgView;
    public ViewFlipper flipper=null;
    public RelativeLayout layout,mainlayout;
    public Rotate3dAnimation animation3d;
    public AnimatorSet alphaSet=null;
    public View landview;
    public Typeface typeface;
    public Button Strat,Explain,Synopsis,loginbutton;
    private EditText accountEdittext,passwordEdittext;
    private TextView account,password;
    public Animation animator_in=null,animator_out=null;
    public int winW,winH;
    public  String hint="Please enter the correct password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent musicintent=new Intent(MainActivity.this,BgMusic.class);
        setContentView(R.layout.activity_main);
        Intent intent=this.getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        winW = metric.widthPixels;
        winH = metric.heightPixels;
        //startService(musicintent);
        init();
        loginbutton.setOnClickListener(this);
        animation3d.setAnimationListener(this);
        animator_in.setAnimationListener(this);
        passwordEdittext.setOnClickListener(this);
        Strat.setOnClickListener(this);
        Explain.setOnClickListener(this);
        Synopsis.setOnClickListener(this);
    }
    public void setDateValue(int y,int m,int d){passwordEdittext.setText(y+"-"+(m+1)+"-"+d);}
    public void init(){
        bgView=(MainActivityBgView) findViewById(R.id.mainbg);
        flipper=(ViewFlipper)findViewById(R.id.flipper_1);
        mainlayout=(RelativeLayout) findViewById(R.id.mainlayout);
        layout=(RelativeLayout)flipper.findViewById(R.id.children_2);
        Strat=(Button) layout.findViewById(R.id.start);
        Explain=(Button) layout.findViewById(R.id.explain);
        Synopsis=(Button) layout.findViewById(R.id.synopsis);
        landview=View.inflate(this,R.layout.landlayout,null);
        account=(TextView) landview.findViewById(R.id.account);
        password=(TextView) landview.findViewById(R.id.password);
        passwordEdittext=(EditText)landview.findViewById(R.id.passwordEdittext);
        accountEdittext=(EditText)landview.findViewById(R.id.accountEdittext);
        loginbutton=(Button)landview.findViewById(R.id.login_button);
        alphaSet=new AnimatorSet();
        alphaSet.playTogether(
                ObjectAnimator.ofFloat(Strat,"Alpha",0,1),
                ObjectAnimator.ofFloat(Explain,"Alpha",0,1),
                ObjectAnimator.ofFloat(Synopsis,"Alpha",0,1)
                );
        alphaSet.setDuration(800);
        animation3d=new Rotate3dAnimation(0,90,0,0,0,false);
        animation3d.setStartOffset(100);
        animation3d.setFillAfter(true);
        animation3d.setDuration(1000);
        typeface=Typeface.createFromAsset(getAssets(),"cy.TTF");
        loginbutton.setTypeface(typeface);
        account.setTypeface(typeface);
        password.setTypeface(typeface);
        accountEdittext.setTypeface(typeface);
        passwordEdittext.setTypeface(typeface);
        passwordEdittext.setInputType(InputType.TYPE_NULL);
        Strat.setTypeface(typeface);
        Explain.setTypeface(typeface);
        Synopsis.setTypeface(typeface);
        animator_in=new AlphaAnimation(0,1);
        setParameter(animator_in);
        animator_out= new AlphaAnimation(1,0);
        setParameter(animator_out);
        flipper.setInAnimation(animator_in);
        flipper.setOutAnimation(animator_out);
        flipper.showNext();
        landview.setLayoutParams(new LayoutParams(winW/2, winH/2+20));
        layout.addView(landview);
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)landview.getLayoutParams();
        params.setMargins(winW/4,winH/4-20, 0, 0);
        landview.requestLayout();
    }
    public void setParameter(Animation anim){
        anim.setFillAfter(true);
        anim.setDuration(3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                Intent toSecond=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(toSecond);
                bgView.state=false;
                finish();
                break;
            case R.id.passwordEdittext:
                DataPickerFragment dialog=new DataPickerFragment();
                dialog.show(getFragmentManager(),"Dialog");
                break;
            case R.id.login_button:
                if(passwordEdittext.getText().toString().trim().equals("2017-11-9")){
                    landview.startAnimation(animation3d);
                }else {
                    Toast.makeText(MainActivity.this,hint,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.explain:
                Intent toFour=new Intent(MainActivity.this,FourActivity.class);
                startActivity(toFour);
                finish();
                break;
            case R.id.synopsis:
                Intent toFive=new Intent(MainActivity.this,FiveActivity.class);
                startActivity(toFive);
                finish();
                break;

        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation.hashCode()==animation3d.hashCode()){
            layout.removeView(landview);
            layout.destroyDrawingCache();
            layout.postInvalidate();
            System.gc();
            Strat.setEnabled(true);
            Explain.setEnabled(true);
            Synopsis.setEnabled(true);
            alphaSet.start();
//            FindQQ findQQTask=new FindQQ();
//            String path= Environment.getExternalStorageDirectory().getPath().toString().trim() +"/Tencent/MobileQQ";
//            findQQTask.execute(path);
        }
        if(animation.hashCode()==animator_in.hashCode()){
            bgView.isadd=true;
            //Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onStop() {
        if(bgView!=null){
            bgView.state=false;
            bgView=null;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(MainActivity.this,BgMusic.class);
        stopService(intent);
        if(bgView!=null){
            bgView.state=false;
            bgView.destroyDrawingCache();
            bgView=null;
        }
        mainlayout.removeAllViewsInLayout();
        mainlayout.destroyDrawingCache();
        setContentView(R.layout.nulllayout);
        System.gc();
        super.onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_UI_HIDDEN:
                layout.destroyDrawingCache();
                System.gc();
                break;
        }
    }
    class FindQQ extends AsyncTask<String ,Integer,String> implements DialogInterface.OnClickListener {

        public List<String> qqaccount=new ArrayList<String>();
        public String myString;
        public boolean Isnext=false;
        public AlertDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("warning")
                    .setMessage("Please use XunYang's phone to install this program")
                    .setNegativeButton("Quit", this)
                    .create();
            dialog.setCanceledOnTouchOutside(false);

        }
        @Override
        protected String doInBackground(String... params) {
            File[] files = new File(params[0]).listFiles();
            if(files!=null){
                for (File file : files) {
                    if(file.getName().trim().length()>=5&&file.getName().trim().length()<=11){
                        if (isNumeric(file.getName().trim())) {
                            myString=file.getName().trim();
                            qqaccount.add(myString);
                        }
                    }
                }
            }
            return myString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            for (String ss : qqaccount) {
                if(ss.equals("1657004367")||ss.equals("1358462610")||ss.equals("1319196420")){
                    Isnext=true;
                }
            }
            if(Isnext==true){
                Toast.makeText(MainActivity.this,"Verify pass",Toast.LENGTH_SHORT).show();
//                Strat.setVisibility(View.VISIBLE);
//                Explain.setVisibility(View.VISIBLE);
//                Synopsis.setVisibility(View.VISIBLE);
                Strat.setEnabled(true);
                Explain.setEnabled(true);
                Synopsis.setEnabled(true);
                alphaSet.start();
            }else {
                dialog.show();
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern,2);
                Window window = dialog.getWindow();
                View view = window.getDecorView();
                setViewFontStyle(view,typeface);
            }
        }
        private void setViewFontStyle(View view,Typeface typeface)
        {
            if(view instanceof ViewGroup)
            {
                ViewGroup parent = (ViewGroup)view;
                int count = parent.getChildCount();
                for (int i = 0; i < count; i++)
                {
                    setViewFontStyle(parent.getChildAt(i),typeface);
                }
            }
            else if(view instanceof TextView){
                TextView textview = (TextView)view;
                textview.setTypeface(typeface);
            }
        }
        public  boolean isNumeric(String str){
            for (int i = 0; i < str.length(); i++){
                if (!Character.isDigit(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==-2){
                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }
}
