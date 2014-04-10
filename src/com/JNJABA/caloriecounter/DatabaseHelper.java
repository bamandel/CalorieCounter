package com.JNJABA.caloriecounter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FOOD_NAME = "foodName";
	public static final String KEY_FOOD_CALORIES = "foodCalories";
	public static final String KEY_FOOD_SODIUM = "foodSodium";
	public static final String KEY_FOOD_POTASSIUM = "foodPotassium";
	public static final String KEY_FOOD_TOTAL_FAT = "foodTotalFat";
	public static final String KEY_FOOD_TOTAL_CARBS = "foodTotalCarbs";
	public static final String KEY_FOOD_PROTEIN = "foodProtein";
	public static final String KEY_FOOD_CHOLESTEROL = "foodCholesterol";
	public static final String KEY_FOOD_SERVING_SIZE = "foodServingSize";
	
	public static final String FOOD_DATABASE_TABLE = "foodTable";
	
	private static final String DATABASE_NAME = "calorie_counter";
	private static int DATABASE_VERSION = 2;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + FOOD_DATABASE_TABLE + " (" +
				KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_FOOD_NAME + "TEXT NOT NULL, " +
				KEY_FOOD_CALORIES + "TEXT NOT NULL, " +
				KEY_FOOD_POTASSIUM + "TEXT NOT NULL, " +
				KEY_FOOD_SODIUM + "TEXT NOT NULL, " +
				KEY_FOOD_CHOLESTEROL + "TEXT NOT NULL, " +
				KEY_FOOD_PROTEIN + "TEXT NOT NULL, " +
				KEY_FOOD_TOTAL_FAT + "TEXT NOT NULL, " +
				KEY_FOOD_TOTAL_CARBS + "TEXT NOT NULL, " +
				KEY_FOOD_SERVING_SIZE + "TEXT NOT NULL" +
				");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + FOOD_DATABASE_TABLE);
		onCreate(db);
	}
}