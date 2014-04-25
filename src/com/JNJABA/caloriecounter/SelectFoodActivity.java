package com.JNJABA.caloriecounter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectFoodActivity extends Activity {
	private static Database foodDB;
	private static LinearLayout llFoods;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_food);
		
		foodDB = new Database(this);
		
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
			
			llFoods = (LinearLayout) rootView.findViewById(R.id.llFoods);
			final Food tempFood = new Food();
			
			try {
				foodDB.open();
				
				Cursor c = foodDB.getFoodValues();
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					tempFood.setFoodName(c.getString(c.getColumnIndex(Database.KEY_FOOD_NAME)));
					tempFood.setCalories(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_CALORIES)))));
					tempFood.setCholesterol(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_CHOLESTEROL)))));
					tempFood.setPotassium(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_POTASSIUM)))));
					tempFood.setProtein(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_PROTEIN)))));
					tempFood.setSodium(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_SODIUM)))));
					tempFood.setTotalCarbs(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_TOTAL_CARBS)))));
					tempFood.setTotalFat(Integer.parseInt((c.getString(c.getColumnIndex(Database.KEY_FOOD_TOTAL_FAT)))));
					tempFood.setServingSize(Double.parseDouble((c.getString(c.getColumnIndex(Database.KEY_FOOD_SERVING_SIZE)))));
					
					final TextView temp = new TextView(getActivity());
					temp.setText(tempFood.getFoodName());
					temp.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							getActivity().setResult(Activity.RESULT_OK, new Intent().putExtra("food", tempFood));
							getActivity().finish();
						}
					});
					
					llFoods.addView(temp);
				}
				
				c.close();

				foodDB.close();
			} catch (SQLiteException e) {
				Log.d("SelectFoodActivity", "Error starting DB");
			}
			
			return rootView;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_CANCELED);
	}
}
