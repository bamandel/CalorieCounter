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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectMealActivity extends Activity {
	private static Database db;
	
	private static LinearLayout llMeals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_meal);
		
		db = new Database(this);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_meal, menu);
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
			
			llMeals = (LinearLayout) rootView.findViewById(R.id.llMeals);
			final Meal tempMeal = new Meal();
			
			try {
				db.open();
				
				Cursor c = db.getMealValues();
				
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					tempMeal.setMealName(c.getString(c.getColumnIndex(Database.KEY_MEAL_NAME)));
					
					final TextView temp = new TextView(getActivity());
					temp.setText(tempMeal.getMealName());
					temp.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							getActivity().setResult(Activity.RESULT_OK, new Intent().putExtra("meal", tempMeal));
							getActivity().finish();
						}
					});
					
					llMeals.addView(temp);
				}
				
				c.close();

				db.close();
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
