package com.example.newgame_1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollHorizontalViewPager extends ViewPager{
    private boolean DISABLE=false;
    public NoScrollHorizontalViewPager(Context context){
       super(context);
    }
    public NoScrollHorizontalViewPager(Context context, AttributeSet attrs){
       super(context,attrs);
    }
   
   @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return DISABLE&&super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return DISABLE&&super.onTouchEvent(arg0);
    }
}
