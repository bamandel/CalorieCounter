package com.JNJABA.caloriecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDatabase {
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
	
	private static final String DATABASE_TABLE = "food_table";
	private static final String DATABASE_NAME = "calorie_counter";
	private static final int DATABASE_VERSION = 3;
	
	private DatabaseHelper mHelper;
	private Context mContext;
	private SQLiteDatabase mDatabase;
	
	public FoodDatabase(Context context) {
		mContext = context;
	}
	
	private class DatabaseHelper extends SQLiteOpenHelper{	
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_FOOD_NAME + " TEXT NOT NULL, " +
					KEY_FOOD_CALORIES + " TEXT NOT NULL, " +
					KEY_FOOD_POTASSIUM + " TEXT NOT NULL, " +
					KEY_FOOD_SODIUM + " TEXT NOT NULL, " +
					KEY_FOOD_CHOLESTEROL + " TEXT NOT NULL, " +
					KEY_FOOD_PROTEIN + " TEXT NOT NULL, " +
					KEY_FOOD_TOTAL_FAT + " TEXT NOT NULL, " +
					KEY_FOOD_TOTAL_CARBS + " TEXT NOT NULL, " +
					KEY_FOOD_SERVING_SIZE + " TEXT NOT NULL" +
					");");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
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
		
		return mDatabase.insert(DATABASE_TABLE, "Nothing entered", values);
	}
	
	public ContentValues createContentValues(Food food) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_FOOD_NAME, food.getFoodName());
		values.put(KEY_FOOD_CALORIES, String.valueOf(food.getCalories()));
		values.put(KEY_FOOD_SODIUM, String.valueOf(food.getSodium()));
		values.put(KEY_FOOD_CHOLESTEROL, String.valueOf(food.getCholesterol()));
		values.put(KEY_FOOD_POTASSIUM, String.valueOf(food.getPotassium()));
		values.put(KEY_FOOD_PROTEIN, String.valueOf(food.getProtein()));
		values.put(KEY_FOOD_TOTAL_FAT, String.valueOf(food.getTotalFat()));
		values.put(KEY_FOOD_TOTAL_CARBS, String.valueOf(food.getTotalCarbs()));
		values.put(KEY_FOOD_SERVING_SIZE, String.valueOf(food.getServingSize()));
		
		return values;
	}

	public Cursor getValues() {
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		return mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
	}
	
	public Food getFoodValueAt(long id) {
		Food food = new Food();
		
		String[] columns = new String[] {KEY_ROWID, KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		try {
			Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
			c.moveToFirst();
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if(c.getLong(c.getColumnIndex(KEY_ROWID)) == id) {
					food.setCalories(c.getInt(c.getColumnIndex(KEY_FOOD_CALORIES)));
					food.setCholesterol(c.getInt(c.getColumnIndex(KEY_FOOD_CHOLESTEROL)));
					food.setFoodName(c.getString(c.getColumnIndex(KEY_FOOD_NAME)));
					food.setPotassium(c.getInt(c.getColumnIndex(KEY_FOOD_POTASSIUM)));
					food.setProtein(c.getInt(c.getColumnIndex(KEY_FOOD_PROTEIN)));
					food.setServingSize(c.getDouble(c.getColumnIndex(KEY_FOOD_SERVING_SIZE)));
					food.setSodium(c.getInt(c.getColumnIndex(KEY_FOOD_SODIUM)));
					food.setTotalCarbs(c.getInt(c.getColumnIndex(KEY_FOOD_TOTAL_CARBS)));
					food.setTotalFat(c.getInt(c.getColumnIndex(KEY_FOOD_TOTAL_FAT)));
					break;
				}
			}
			c.close();
		} catch (SQLiteException e) {
			food = null;
		}
		
		return food;
	}
	
	public String getStringValues() {
		String result = "";
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int iFoodName = c.getColumnIndex(KEY_FOOD_NAME);
		int iFoodCalories = c.getColumnIndex(KEY_FOOD_CALORIES);
		int iFoodSodium = c.getColumnIndex(KEY_FOOD_SODIUM);
		int iFoodCholesterol = c.getColumnIndex(KEY_FOOD_CHOLESTEROL);
		int iFoodPotassium = c.getColumnIndex(KEY_FOOD_POTASSIUM);
		int iFoodTotalFat = c.getColumnIndex(KEY_FOOD_TOTAL_FAT);
		int iFoodTotalCarbs = c.getColumnIndex(KEY_FOOD_TOTAL_CARBS);
		int iFoodProtein = c.getColumnIndex(KEY_FOOD_PROTEIN);
		int iFoodServingSize = c.getColumnIndex(KEY_FOOD_SERVING_SIZE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result += c.getString(iFoodName) + " " +
					c.getString(iFoodCalories) + " " +
					c.getString(iFoodSodium) + " " +
					c.getString(iFoodCholesterol) + " " +
					c.getString(iFoodPotassium) + " " +
					c.getString(iFoodTotalFat) + " " +
					c.getString(iFoodTotalCarbs) + " " +
					c.getString(iFoodProtein) + " " +
					c.getString(iFoodServingSize) + "\n";
		}
		
		c.close();
		
		return result;
	}
	
	public boolean updateValue(long id, Food food) {
		ContentValues values = createContentValues(food);
		
		return mDatabase.update(DATABASE_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public long getValueId(String name) {
		long id;
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		try {
			Cursor c = mDatabase.query(true, DATABASE_TABLE, columns, KEY_FOOD_NAME + "='" + name + "'", null, null, null, null, null);
			c.moveToFirst();
			id = c.getLong(c.getColumnIndex(KEY_ROWID));
			c.close();
		} catch (SQLiteException e) {
			id = -1;
		}
		
		return id;
	}
	
	public boolean deleteValue(long id) {
		return mDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public int deleteAllValues() {
		return mDatabase.delete(DATABASE_TABLE, null, null);
	}
	
}
