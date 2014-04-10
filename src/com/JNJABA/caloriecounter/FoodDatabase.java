package com.JNJABA.caloriecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabase {
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
		
		return mDatabase.insert(DatabaseHelper.FOOD_DATABASE_TABLE, "Nothing entered", values);
	}
	
	public ContentValues createContentValues(Food food) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper.KEY_FOOD_NAME, food.getFoodName());
		values.put(DatabaseHelper.KEY_FOOD_CALORIES, String.valueOf(food.getCalories()));
		values.put(DatabaseHelper.KEY_FOOD_SODIUM, String.valueOf(food.getSodium()));
		values.put(DatabaseHelper.KEY_FOOD_CHOLESTEROL, String.valueOf(food.getCholesterol()));
		values.put(DatabaseHelper.KEY_FOOD_POTASSIUM, String.valueOf(food.getPotassium()));
		values.put(DatabaseHelper.KEY_FOOD_PROTEIN, String.valueOf(food.getProtein()));
		values.put(DatabaseHelper.KEY_FOOD_TOTAL_FAT, String.valueOf(food.getTotalFat()));
		values.put(DatabaseHelper.KEY_FOOD_TOTAL_CARBS, String.valueOf(food.getTotalCarbs()));
		values.put(DatabaseHelper.KEY_FOOD_SERVING_SIZE, String.valueOf(food.getServingSize()));
		
		return values;
	}

	public Cursor getValues() {
		// TODO Auto-generated method stub
		String[] columns = new String[] {DatabaseHelper.KEY_FOOD_NAME, DatabaseHelper.KEY_FOOD_CALORIES,
				DatabaseHelper.KEY_FOOD_SODIUM, DatabaseHelper.KEY_FOOD_CHOLESTEROL,
				DatabaseHelper.KEY_FOOD_POTASSIUM, DatabaseHelper.KEY_FOOD_TOTAL_FAT,
				DatabaseHelper.KEY_FOOD_TOTAL_CARBS, DatabaseHelper.KEY_FOOD_PROTEIN,
				DatabaseHelper.KEY_FOOD_SERVING_SIZE};
		
		return mDatabase.query(DatabaseHelper.FOOD_DATABASE_TABLE, columns, null, null, null, null, null);
	}
	
	public boolean updateValue(long id, Food food) {
		ContentValues values = createContentValues(food);
		
		return mDatabase.update(DatabaseHelper.FOOD_DATABASE_TABLE, values, DatabaseHelper.KEY_ROWID + "=" + id, null) > 0;
	}
	
	public long getValueId(String type) {
		long id;
		String[] columns = new String[] {DatabaseHelper.KEY_FOOD_NAME, DatabaseHelper.KEY_FOOD_CALORIES,
				DatabaseHelper.KEY_FOOD_SODIUM, DatabaseHelper.KEY_FOOD_CHOLESTEROL,
				DatabaseHelper.KEY_FOOD_POTASSIUM, DatabaseHelper.KEY_FOOD_TOTAL_FAT,
				DatabaseHelper.KEY_FOOD_TOTAL_CARBS, DatabaseHelper.KEY_FOOD_PROTEIN,
				DatabaseHelper.KEY_FOOD_SERVING_SIZE};
		
		try {
			Cursor c = mDatabase.query(true, DatabaseHelper.FOOD_DATABASE_TABLE, columns, DatabaseHelper.KEY_FOOD_NAME + "='" + type + "'", null, null, null, null, null);
			c.moveToFirst();
			id = c.getLong(c.getColumnIndex(DatabaseHelper.KEY_ROWID));
		} catch (SQLiteException e) {
			id = -1;
		}
		
		return id;
	}
	
	public boolean deleteValue(long id) {
		return mDatabase.delete(DatabaseHelper.FOOD_DATABASE_TABLE, DatabaseHelper.KEY_ROWID + "=" + id, null) > 0;
	}
	
	public int deleteAllValues() {
		return mDatabase.delete(DatabaseHelper.FOOD_DATABASE_TABLE, null, null);
	}
	
}
