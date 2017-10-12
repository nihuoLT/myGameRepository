package com.example.administrator.mylovegame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyGamedb extends SQLiteOpenHelper {

	public final static String Scoredb="create table myscoretable("
	                                  +"id integer primary key autoincrement,"
			                          +"name text,"
	                                  +"score integer)";
	private Context context;
	public MyGamedb(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Scoredb);
		Toast.makeText(context, "deleteDatabase succeed", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists myscoretable");

	}

	public boolean deleteDatabase(String name) {
		Toast.makeText(context, "重置成功", Toast.LENGTH_SHORT).show();
		return context.deleteDatabase(name);
		}
}
