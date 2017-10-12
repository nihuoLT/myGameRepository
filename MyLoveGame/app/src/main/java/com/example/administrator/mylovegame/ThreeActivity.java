package com.example.administrator.mylovegame;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/12/23 0023.
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class ThreeActivity extends Activity implements OnClickListener,DialogInterface.OnClickListener{
    public Typeface typeface;
    public ListView listView;
    public RelativeLayout layout=null;
    public BaseAdapter adapter;
    public List<String> contactsList=new ArrayList<String>();
    public MyGamedb gamedb=null;
    public SQLiteDatabase db=null;
    public ContentValues values=null;
    public String string;
    public Random random;
    private AlertDialog.Builder exitdialog=null;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            setDataTodb(Integer.parseInt(string));
            showdata();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_three);
        init();
    }
    public void init(){
        typeface=Typeface.createFromAsset(getAssets(),"pty.TTF");
        random=new Random();
        layout = (RelativeLayout) findViewById(R.id.mylayout);
        gamedb=new MyGamedb(ThreeActivity.this, "Scoredb.db", null, 1);
        db=gamedb.getWritableDatabase();
        values=new ContentValues();
        listView=(ListView)findViewById(R.id.scorelist);
        TextView view=(TextView)findViewById(R.id.myscore);
        TextView textview=(TextView)findViewById(R.id.mytext);
       final Button restart=(Button)findViewById(R.id.restart);
        view.setTypeface(typeface);
        textview.setTypeface(typeface);
        restart.setTypeface(typeface);
        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        restart.setTextSize(25);
                        restart.setTextColor(Color.parseColor("#ffff00"));
                        break;
                    case MotionEvent.ACTION_UP:
                        restart.setTextSize(30);
                        restart.setTextColor(Color.parseColor("#660099"));
                        Intent intent=new Intent(ThreeActivity.this,SecondActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
        ImageButton buttonexit=(ImageButton)findViewById(R.id.exit);
        buttonexit.setOnClickListener(this);
        getMyScore(view);
        handler.post(runnable);
    }
    public void getMyScore(TextView view){
        Intent intent=getIntent();
        string=intent.getStringExtra("score");
        view.setText("本局得分："+string);
    }
    public void setDataTodb(int myscore){
        Cursor firstcursor=db.rawQuery("select * from myscoretable",null);
        switch (firstcursor.getCount()) {
            case 0:
                values.put("name", "第一名：");
                values.put("score", myscore);
                db.insert("myscoretable", null, values);
                values.clear();
                try {
                    firstcursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                firstcursor=db.query("myscoretable", null, null, null, null, null, null);
                if(firstcursor.moveToFirst()){
                    do {
                        int temporaryscore=firstcursor.getInt(firstcursor.getColumnIndex("score"));
                        if(myscore>temporaryscore){
                            values.put("score", myscore);
                            db.update("myscoretable", values, "name=?", new String[]{"第一名："});
                            values.clear();
                            values.put("name", "第二名：");
                        }else if(myscore<temporaryscore){
                            values.put("name", "第二名：");
                            values.put("score", myscore);
                            db.insert("myscoretable", null, values);
                            values.clear();
                        }else{
                            values.clear();
                        }
                    } while (firstcursor.moveToNext());
                }
                try {
                    firstcursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Cursor secondcursor=db.query("myscoretable", null, null, null, null, null, null);
                int temporaryscore[]=new int[2];
                int i=0;
                if(secondcursor.moveToFirst()){
                    do {
                        temporaryscore[i]=secondcursor.getInt(secondcursor.getColumnIndex("score"));
                        i++;
                    } while (secondcursor.moveToNext());
                }
                if(myscore>temporaryscore[1]){
                    values.put("score", myscore);
                    db.update("myscoretable", values, "name=?", new String[]{"第一名："});
                    values.clear();
                    values.put("score", temporaryscore[0]);
                    db.update("myscoretable", values, "name=?", new String[]{"第二名："});
                    values.clear();
                    values.put("name","第三名：");
                    values.put("score", temporaryscore[1]);
                    db.insert("myscoretable", null, values);
                    values.clear();
                }if(myscore<temporaryscore[0]&&myscore>temporaryscore[1]){
                values.put("score", myscore);
                db.update("myscoretable", values, "name=?", new String[]{"第二名："});
                values.clear();
                values.put("name","第三名：");
                values.put("score", temporaryscore[1]);
                db.insert("myscoretable", null, values);
                values.clear();
            }if(myscore<temporaryscore[1]){
                values.put("name","第三名：");
                values.put("score", myscore);
                db.insert("myscoretable", null, values);
                //cursor.requery();
                values.clear();
            }
                try {
                    secondcursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                Cursor thirdcursor=db.query("myscoretable", null, null, null, null, null, null);
                int temporaryscore_next[]=new int[3];
                int j=0;
                if(thirdcursor.moveToFirst()){
                    do {
                        temporaryscore_next[j]=thirdcursor.getInt(thirdcursor.getColumnIndex("score"));
                        j++;
                    }while(thirdcursor.moveToNext());
                }

                if(myscore>temporaryscore_next[0]){
                    values.put("score", myscore);
                    db.update("myscoretable", values, "name=?", new String[]{"第一名："});
                    values.clear();
                    values.put("score", temporaryscore_next[0]);
                    db.update("myscoretable", values, "name=?", new String[]{"第二名："});
                    values.clear();
                    values.put("score", temporaryscore_next[1]);
                    db.update("myscoretable", values, "name=?", new String[]{"第三名："});
                    //cursor.requery();
                    values.clear();
                }
                if(myscore<temporaryscore_next[0]&&myscore>temporaryscore_next[1]){
                    values.put("score", myscore);
                    db.update("myscoretable", values, "name=?", new String[]{"第二名："});
                    values.clear();
                    values.put("score", temporaryscore_next[1]);
                    db.update("myscoretable", values, "name=?", new String[]{"第三名："});
                    //cursor.requery();
                    values.clear();
                }
                if(myscore<temporaryscore_next[1]&&myscore>temporaryscore_next[2]){
                    values.put("score", myscore);
                    db.update("myscoretable", values, "name=?", new String[]{"第三名："});
                    //cursor.requery();
                    values.clear();
                }
                thirdcursor.close();
                break;
            default:
                break;
        }
    }
    public void showdata(){
        Cursor cursor=null;
        contactsList.clear();
        try {
            cursor=db.query("myscoretable", null, null, null, null, null, null);
            if(cursor.moveToFirst()){
                do {
                    String displayorder=cursor.getString(cursor.getColumnIndex("name"));
                    int displayscore=cursor.getInt(cursor.getColumnIndex("score"));
                    String displaySscore=Integer.toString(displayscore);
                    contactsList.add(displayorder+displaySscore);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(cursor!=null){
                cursor.close();
            }
        }
        adapter=new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final TextView indextext;
                LayoutInflater mInflater= LayoutInflater.from(ThreeActivity.this);
                if(convertView==null){
                    convertView=mInflater.inflate(R.layout.listlayout, null);
                    convertView.invalidate();
                }
                indextext=(TextView)convertView.findViewById(R.id.myscore);
                indextext.setTypeface(typeface);
                indextext.setText(contactsList.get(position).toString());
                convertView.invalidate();
                return convertView;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public Object getItem(int arg0) {
                // TODO Auto-generated method stub
                return contactsList.get(arg0);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return contactsList.size();
            }
        };
        listView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.exit:
                exitdialog= new AlertDialog.Builder(this);
                exitdialog.setCancelable(false);
                exitdialog.setTitle("是否退出游戏？");
                exitdialog.setNeutralButton("取消", this);
                exitdialog.setNegativeButton("退出", this);
                exitdialog.create().show();
                break;
        }
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==-2){
            System.exit(0);
            dialog.dismiss();
        }else{
            Toast.makeText(ThreeActivity.this, "已取消", Toast.LENGTH_SHORT).show();
        }
    }
}

