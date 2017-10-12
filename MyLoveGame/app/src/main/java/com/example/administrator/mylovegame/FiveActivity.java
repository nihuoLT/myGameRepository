package com.example.administrator.mylovegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
public class FiveActivity extends Activity{
    private Typeface typeface;
    private TextView texts,textexplain,texte;
    private Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_five);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FiveActivity.this,SecondActivity.class);
                startActivity(intent);
                FiveActivity.this.finish();
            }
        });
    }
    public void init(){
        texts=(TextView)findViewById(R.id.texts);
        textexplain=(TextView)findViewById(R.id.textexplain);
        texte=(TextView)findViewById(R.id.texte);
        button=(Button) findViewById(R.id.fivestart);
        typeface=Typeface.createFromAsset(getAssets(),"cy.TTF");
        texts.setTypeface(typeface);
        textexplain.setTypeface(typeface);
        texte.setTypeface(typeface);
        button.setTypeface(typeface);
    }
}
