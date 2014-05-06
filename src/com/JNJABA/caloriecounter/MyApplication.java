package com.JNJABA.caloriecounter;

import android.app.Application;

public class MyApplication extends Application {
	
	public void onCreate() {
		super.onCreate();
		
		Database db = new Database(this);
		db.open();
		
		db.init();
		
		db.close();
	}
	
}
