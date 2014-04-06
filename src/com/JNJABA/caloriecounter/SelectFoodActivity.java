package com.JNJABA.caloriecounter;

import java.sql.SQLException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectFoodActivity extends Activity {
	private static FoodDatabase foodDB;
	private static LinearLayout llFoods;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_food);
		
		foodDB = new FoodDatabase(this);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_food, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_select_food, container, false);
			
			final Food food = new Food();
			
			try {
				foodDB.open();
				final Cursor c = foodDB.getAllValues();
				
				for (c.moveToFirst(); c.isAfterLast(); c.moveToNext()) {
					TextView temp = new TextView(getActivity());
					temp.setText(c.getColumnIndex(FoodDatabase.KEY_FOOD_NAME));
					temp.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							food.setFoodName(c.getString(c.getColumnIndex(FoodDatabase.KEY_FOOD_NAME)));
							food.setCalories(c.getInt(c.getColumnIndex(FoodDatabase.KEY_CALORIES)));
							food.setPotassium(c.getInt(c.getColumnIndex(FoodDatabase.KEY_POTASSIUM)));
							food.setTotalFat(c.getInt(c.getColumnIndex(FoodDatabase.KEY_TOTALFAT)));
							food.setCholesterol(c.getInt(c.getColumnIndex(FoodDatabase.KEY_CHOLESTEROL)));
							food.setSodium(c.getInt(c.getColumnIndex(FoodDatabase.KEY_SODIUM)));
							food.setTotalCarbs(c.getInt(c.getColumnIndex(FoodDatabase.KEY_TOTALCARBS)));
							food.setProtein(c.getInt(c.getColumnIndex(FoodDatabase.KEY_PROTEIN)));
							food.setServingSize(c.getDouble(c.getColumnIndex(FoodDatabase.KEY_SERVING_SIZE)));
						}

					});
					llFoods.addView(temp);
				}
				
				foodDB.close();
			} catch (SQLiteException e) {

			}
			
			getActivity().setResult(0, new Intent().putExtra("food", food));
			
			return rootView;
		}
	}

}
