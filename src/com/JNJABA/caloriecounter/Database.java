package com.JNJABA.caloriecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
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
	
	//Row that the meal is stored at
	public static final String KEY_MEAL_ROWID = "meal_id";
	//Name of the specific meal
	public static final String KEY_MEAL_NAME = "mealName";
	//Points to the id of the food database it is holding
	public static final String KEY_MEAL_FOOD_ID = "mealId";
	
	//Row that the food database is stored at
	public static final String KEY_FOOD_ROWID = "food_id";
	//Points to the id of the actual food data
	public static final String KEY_FOOD_ID = "foodId";
	
	private static final String DATABASE_FOOD_TABLE = "food_table";
	private static final String DATABASE_MEAL_TABLE = "meal_table";
	private static final String DATABASE_MEAL_FOOD_TABLE = "meal_food_table";
	private static final String DATABASE_NAME = "calorie_counter";
	private static final int DATABASE_VERSION = 4;
	
	private DatabaseHelper mHelper;
	private Context mContext;
	private SQLiteDatabase mDatabase;
	
	public Database(Context context) {
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
			db.execSQL("CREATE TABLE " + DATABASE_FOOD_TABLE + " (" +
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
			
			db.execSQL("CREATE TABLE " + DATABASE_MEAL_TABLE + " (" +
					KEY_MEAL_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_MEAL_NAME + " TEXT NOT NULL, " + 
					KEY_MEAL_FOOD_ID + " TEXT NOT NULL" +
					");");
			
			db.execSQL("CREATE TABLE " + DATABASE_MEAL_FOOD_TABLE + " (" +
					KEY_FOOD_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_FOOD_ID + " TEXT NOT NULL" + 
					");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_FOOD_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MEAL_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MEAL_FOOD_TABLE);
			
			onCreate(db);
		}
	}
	
	public Database open() throws SQLiteException{
		mHelper = new DatabaseHelper(mContext);
		mDatabase = mHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		mHelper.close();
	}
	
	public long addFoodValue(Food food) {
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
		return mDatabase.insert(DATABASE_FOOD_TABLE, "Nothing entered", values);
	}
	
	public Cursor getFoodValues() {
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		return mDatabase.query(DATABASE_FOOD_TABLE, columns, null, null, null, null, null);
	}
	
	public Food getFoodValueAt(long id) {
		Food food = new Food();
		
		String[] columns = new String[] {KEY_ROWID, KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		try {
			Cursor c = mDatabase.query(DATABASE_FOOD_TABLE, columns, null, null, null, null, null);
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
	
	public String getFoodStringValues() {
		String result = "";
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		Cursor c = mDatabase.query(DATABASE_FOOD_TABLE, columns, null, null, null, null, null);
		
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
	
	public boolean updateFoodValue(long id, Food food) {
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
		
		return mDatabase.update(DATABASE_FOOD_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public long getFoodValueId(String name) {
		long id;
		String[] columns = new String[] {KEY_FOOD_NAME, KEY_FOOD_CALORIES,
				KEY_FOOD_SODIUM, KEY_FOOD_CHOLESTEROL,
				KEY_FOOD_POTASSIUM, KEY_FOOD_TOTAL_FAT,
				KEY_FOOD_TOTAL_CARBS, KEY_FOOD_PROTEIN,
				KEY_FOOD_SERVING_SIZE};
		
		try {
			Cursor c = mDatabase.query(true, DATABASE_FOOD_TABLE, columns, KEY_FOOD_NAME + "='" + name + "'", null, null, null, null, null);
			c.moveToFirst();
			id = c.getLong(c.getColumnIndex(KEY_ROWID));
			c.close();
		} catch (SQLiteException e) {
			id = -1;
		}
		
		return id;
	}
	
	public boolean deleteFoodValue(long id) {
		return mDatabase.delete(DATABASE_FOOD_TABLE, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public int deleteAllFoodValues() {
		return mDatabase.delete(DATABASE_FOOD_TABLE, null, null);
	}
	
	public long addMealValue(Meal meal) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_MEAL_NAME, meal.getMealName());
		values.put(KEY_MEAL_FOOD_ID, KEY_FOOD_ROWID);
		
		return mDatabase.insert(DATABASE_MEAL_TABLE, "Nothing entered", values);
	}
	
	public Meal getMealValueAt(long id) {
		Meal meal = new Meal();
		
		String[] columns = new String[] {KEY_MEAL_ROWID, KEY_MEAL_NAME};
		
		try {
			Cursor c = mDatabase.query(DATABASE_MEAL_TABLE, columns, null, null, null, null, null);
			c.moveToFirst();
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if(c.getLong(c.getColumnIndex(KEY_MEAL_ROWID)) == id) {
					meal.setMealName(c.getString(c.getColumnIndex(KEY_MEAL_NAME)));
					break;
				}
			}
			c.close();
		} catch (SQLiteException e) {
			meal = null;
		}
		
		return meal;
	}
	
	public String getMealStringValues() {
		String result = "";
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		Cursor c = mDatabase.query(DATABASE_MEAL_TABLE, columns, null, null, null, null, null);
		
		int iMealName = c.getColumnIndex(KEY_MEAL_NAME);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result += c.getString(iMealName) + "\n";
		}
		
		c.close();
		
		return result;
	}
	
	public boolean updateMealValue(long id, Meal meal) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_MEAL_NAME, meal.getMealName());
		values.put(KEY_MEAL_FOOD_ID, KEY_MEAL_ROWID);
		
		return mDatabase.update(DATABASE_MEAL_TABLE, values, KEY_MEAL_ROWID + "=" + id, null) > 0;
	}
	
	public Cursor getMealValues() {
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		return mDatabase.query(DATABASE_MEAL_TABLE, columns, null, null, null, null, null);
	}
	
	public long getMealValueId(String name) {
		long id;
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		try {
			Cursor c = mDatabase.query(true, DATABASE_MEAL_TABLE, columns, KEY_MEAL_NAME + "='" + name + "'", null, null, null, null, null);
			c.moveToFirst();
			id = c.getLong(c.getColumnIndex(KEY_MEAL_ROWID));
			c.close();
		} catch (SQLiteException e) {
			id = -1;
		}
		
		return id;
	}
	
	public boolean deleteMealValue(long id) {
		return mDatabase.delete(DATABASE_MEAL_TABLE, KEY_MEAL_ROWID + "=" + id, null) > 0;
	}
	
	public int deleteAllMealValues() {
		return mDatabase.delete(DATABASE_MEAL_TABLE, null, null);
	}
	
	public long addMealFoodValue(Food food) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_FOOD_ID, getFoodValueId(food.getFoodName()));
		
		return mDatabase.insert(DATABASE_FOOD_TABLE, "Nothing entered for food", values);
	}
}
