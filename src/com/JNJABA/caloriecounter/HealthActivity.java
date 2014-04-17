package com.JNJABA.caloriecounter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HealthActivity extends Activity {
	private static Day day;
	private static LinearLayout llDailyFoods, llDailyNutrition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health);

		day = Day.getInstance();

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.health, menu);
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_health,
					container, false);

			if ( day != null) {
				Log.d("HealthActivity", "Day is not null");
				
				llDailyFoods = (LinearLayout) rootView.findViewById(R.id.llDailyFoods);
				llDailyNutrition = (LinearLayout) rootView.findViewById(R.id.llDailyNutrition);
				
				llDailyFoods.removeAllViews();
				llDailyNutrition.removeAllViews();

				ArrayList<Food> foodsEaten = day.getFoods();
				ArrayList<Meal> mealsEaten = day.getMeals();

				Log.d("HealthActivity", "Lists created");
				
				if (!foodsEaten.isEmpty()) {
					Log.d("HealthActivity", "Foods is not empty");
					for (int i = 0; i < foodsEaten.size(); i++) {
						TextView foodView = new TextView(getActivity());
						Log.d("HealthActivity", "getting food name");
						
						foodView.setText(foodsEaten.get(i).getFoodName());
						Log.d("HealthActivity", "Not adding View???");
						llDailyFoods.addView(foodView);
						Log.d("HealthActivity", foodsEaten.get(i).getFoodName());
					}
				}
				
				if (!mealsEaten.isEmpty()) {
					Log.d("HealthActivity", "Meals is not empty");
					for (Meal meal : mealsEaten) {
						for (Food food : meal.getFoods()) {
							TextView foodView = new TextView(getActivity());

							foodView.setText(food.getFoodName());
							llDailyFoods.addView(foodView);
						}
					}
				}
				
				Log.d("HealthActivity", "Setting up Nutrition");
				
				TextView nutrition = new TextView(getActivity());
				nutrition.setText(day.nutritionToString());

				llDailyNutrition.addView(nutrition);
				
				Log.d("HealthActivity", "Everything is setup");
			}

			return rootView;
		}
	}
	
}
