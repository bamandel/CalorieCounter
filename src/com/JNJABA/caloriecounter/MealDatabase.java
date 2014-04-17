package com.JNJABA.caloriecounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MealDatabase {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_MEAL_NAME = "mealName";
	
	private static final String DATABASE_TABLE = "meal_table";
	private static final String DATABASE_NAME = "calorie_counter";
	private static final int DATABASE_VERSION = 1;
	
	private DatabaseHelper mHelper;
	private Context mContext;
	private SQLiteDatabase mDatabase;
	
	public MealDatabase(Context context) {
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
					KEY_MEAL_NAME + " TEXT NOT NULL, " +
					");");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public MealDatabase open() throws SQLiteException{
		mHelper = new DatabaseHelper(mContext);
		mDatabase = mHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		mHelper.close();
	}
	
	public long addValue(Meal meal) {
		ContentValues values = createContentValues(meal);
		
		return mDatabase.insert(DATABASE_TABLE, "Nothing entered", values);
	}
	
	public ContentValues createContentValues(Meal meal) {
		ContentValues values = new ContentValues();
		
		values.put(KEY_MEAL_NAME, meal.getMealName());
		
		return values;
	}

	public Cursor getValues() {
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		return mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
	}
	
	public Meal getMealValueAt(long id) {
		Meal meal = new Meal();
		
		String[] columns = new String[] {KEY_ROWID, KEY_MEAL_NAME};
		
		try {
			Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
			c.moveToFirst();
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if(c.getLong(c.getColumnIndex(KEY_ROWID)) == id) {
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
	
	public String getStringValues() {
		String result = "";
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		int iMealName = c.getColumnIndex(KEY_MEAL_NAME);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result += c.getString(iMealName) + "\n";
		}
		
		c.close();
		
		return result;
	}
	
	public boolean updateValue(long id, Meal meal) {
		ContentValues values = createContentValues(meal);
		
		return mDatabase.update(DATABASE_TABLE, values, KEY_ROWID + "=" + id, null) > 0;
	}
	
	public long getValueId(String name) {
		long id;
		String[] columns = new String[] {KEY_MEAL_NAME};
		
		try {
			Cursor c = mDatabase.query(true, DATABASE_TABLE, columns, KEY_MEAL_NAME + "='" + name + "'", null, null, null, null, null);
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
