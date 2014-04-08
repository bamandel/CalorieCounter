package com.JNJABA.caloriecounter;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabase {
	public static final String KEY_ROWID = "_id";
	
	public static final String KEY_FOOD_NAME = "foodName";
	public static final String KEY_CALORIES = "foodCalories";
	public static final String KEY_POTASSIUM = "foodPotassium";
	public static final String KEY_TOTALFAT = "foodTotalFat";
	public static final String KEY_CHOLESTEROL = "foodCholesterol";
	public static final String KEY_SODIUM = "foodSodium";
	public static final String KEY_TOTALCARBS = "foodTotalCarbs";
	public static final String KEY_PROTEIN = "foodProtein";
	public static final String KEY_SERVING_SIZE = "foodServingSize";
	
	private static final String DATABASE_TABLE = "food";
	
	private DatabaseHelper mHelper;
	private Context mContext;
	private SQLiteDatabase mDatabase;
	
	public FoodDatabase(Context context) {
		mContext = context;
	}
	
	public FoodDatabase open() throws SQLiteException{
		mHelper = new DatabaseHelper(mContext);
		mDatabase = mHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		mHelper.close();
	}

	public long addValue(Food food) {
		ContentValues values = createContentValues(food);
		
		return mDatabase.insert(DATABASE_TABLE, null, values);
	}
	
	public boolean updateValue(long id, Food food) {
		ContentValues values = createContentValues(food);
		
		return mDatabase.update(DATABASE_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public long getValueId(String type) {
		//This column string shows the only data that you are getting
		String[] columns = new String[] {KEY_ROWID, KEY_FOOD_NAME, KEY_CALORIES, KEY_POTASSIUM, KEY_TOTALFAT,
				KEY_CHOLESTEROL, KEY_SODIUM, KEY_TOTALCARBS, KEY_PROTEIN, KEY_SERVING_SIZE};
		
		long id;
		
		try {
			Cursor c = mDatabase.query(true, DATABASE_TABLE, null, KEY_FOOD_NAME + "='" + type + "'", null, null, null, null, null);
			//c.moveToFirst();
			id = c.getLong(c.getColumnIndex(KEY_ROWID));
		} catch (SQLiteException e) {
			id = -1;
		}
		
		return id;
	}
	
	public Cursor getAllValues() {
		//Again string for acquiring specific columns, but we want all
		String[] columns = new String[] {KEY_ROWID, KEY_FOOD_NAME, KEY_CALORIES, KEY_POTASSIUM, KEY_TOTALFAT,
				KEY_CHOLESTEROL, KEY_SODIUM, KEY_TOTALCARBS, KEY_PROTEIN, KEY_SERVING_SIZE};
		
		Cursor c;
		
		try {
			c =  mDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
		} catch (Exception e) {
			c = null;
		}
		/*
		 * String allData = "";
		 * int iRow = c.getColumnIndex(KEY_ROWID);
		 * int iFoodName = c.getColumnIndex(KEY_FOOD_NAME);
		 * ...
		 * for(c.moveToFirst(); c.isAfterLast(); c.moveToNext()) {
		 * 		allData += " " + c.getString(iRow) + " " + c.getString(iFoodName) ...
		 * }
		 */
		
		return c;
	}
	
	public boolean deleteValue(long id) {
		return mDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public ContentValues createContentValues(Food food) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_FOOD_NAME, food.getFoodName());
		values.put(KEY_CALORIES, food.getFoodName());
		values.put(KEY_POTASSIUM, food.getFoodName());
		values.put(KEY_TOTALFAT, food.getFoodName());
		values.put(KEY_CHOLESTEROL, food.getFoodName());
		values.put(KEY_SODIUM, food.getFoodName());
		values.put(KEY_TOTALCARBS, food.getFoodName());
		values.put(KEY_PROTEIN, food.getFoodName());
		values.put(KEY_SERVING_SIZE, food.getFoodName());
		
		return values;
	}
}
