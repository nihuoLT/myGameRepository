package com.example.newgame_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentOne extends Fragment implements OnCheckedChangeListener{
   public View mView=null;
   public TextView text=null,timu=null,texta=null,textb=null,textc=null,textd=null,youanser=null;
   public RadioGroup group=null;
   public WenDaActivity wenDaActivity=null;
   public String xinxi="你选择的答案是：  ",anser="";
   public RadioButton itemA=null,itemB=null,itemC=null,itemD=null;
   public boolean flag=false;
   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	   wenDaActivity=(WenDaActivity)getActivity();
       if (mView == null) {
           mView = inflater.inflate(R.layout.fragmentone,null);
       }
       text=(TextView)mView.findViewById(R.id.mTextView);
       timu=(TextView)mView.findViewById(R.id.timu);
       youanser=(TextView)mView.findViewById(R.id.youanser);
       group=(RadioGroup)mView.findViewById(R.id.radioGroup);
       group.setOnCheckedChangeListener(this);
       itemA=(RadioButton)mView.findViewById(R.id.itema);
       itemB=(RadioButton)mView.findViewById(R.id.itemb);
       itemC=(RadioButton)mView.findViewById(R.id.itemc);
       itemD=(RadioButton)mView.findViewById(R.id.itemd);
       texta=(TextView)mView.findViewById(R.id.texta);
       textb=(TextView)mView.findViewById(R.id.textb);
       textc=(TextView)mView.findViewById(R.id.textc);
       textd=(TextView)mView.findViewById(R.id.textd);
       youanser.setText(xinxi);
       text.setText("问题1");
       timu.setText("天地五行金,木,水,火,土中，哪个的腿最长？");
       texta.setText("A  火");
       textb.setText("B  木");
       textc.setText("C  金和木");
       textd.setText("D  水和土");
       return mView;
   }
   public void onCheckedChanged(RadioGroup group, int checkedId) {
	   flag=true;
	   switch (checkedId) {
           case R.id.itema:
        	   anser="A";
              break;
           case R.id.itemb:
        	   anser="B";
              break;
           case R.id.itemc:
        	   anser="C";
    	      break;
           case R.id.itemd:
        	   anser="D";
              break;
       }
	   youanser.setText(xinxi+anser);
   } 
}
