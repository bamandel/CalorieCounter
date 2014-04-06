package com.JNJABA.caloriecounter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "foodList";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE_FOOD = "CREATE TABLE food (" +
			"_id integer PRIMARY KEY AUTOINCREMENT," +
			"foodName text NOT NULL," +
			"foodCalories integer NOT NULL," +
			"foodPotassium integer NOT NULL," +
			"foodTotalFat integer NOT NULL," +
			"foodCholesterol integer NOT NULL," +
			"foodSodium integer NOT NULL," +
			"foodTotalCarbs integer NOT NULL," +
			"foodProtein integer NOT NULL," +
			"foodServingSize double NOT NULL" +
			");";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//can change out DATABASE_NAME with whichever database I want to
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE_FOOD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS food");
		onCreate(db);
	}
}