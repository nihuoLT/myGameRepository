package com.example.leitingnihuo;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017-02-15.
 */
public interface MyInterface {
    Bitmap getBitmap();
    Bitmap addBitmapToCache(int resId);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
}
