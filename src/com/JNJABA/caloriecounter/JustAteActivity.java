package com.JNJABA.caloriecounter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class JustAteActivity extends Activity {
	private static Button bNewFood, bNewMeal, bSelectFood, bSelectMeal;
	private Day day;
	
	private FoodDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_just_ate);

		db = new FoodDatabase(this.getApplication());
		day = new Day();
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.just_ate, menu);
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_just_ate,container, false);

			bNewFood = (Button) rootView.findViewById(R.id.bNewFood);
			bNewMeal = (Button) rootView.findViewById(R.id.bNewMeal);
			bSelectFood = (Button) rootView.findViewById(R.id.bSelectFood);
			bSelectMeal = (Button) rootView.findViewById(R.id.bSelectMeal);

			bNewFood.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(),NewFoodActivity.class), 0);
				}

			});
			
			bNewMeal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(), NewMealActivity.class), 1);
				}
				
			});
			
			bSelectFood.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(), SelectFoodActivity.class), 0);
				}
				
			});
			
			bSelectMeal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(), SelectMealActivity.class), 1);
				}
				
			});
			
			return rootView;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d("Just Ate", "Entered onActivityResult");
		Log.d("Just Ate", requestCode + " " + resultCode);
		
		if (resultCode == RESULT_OK) {
			// code 0 will return food
			if (requestCode == 0) {
				Food food = (Food) data.getParcelableExtra("food");
				day.addFood(food);
				
				try {
					db.open();
					db.addValue(food);
					db.close();
				} catch (Exception e) {
					Log.d("JustAteActivity", "Database not opened");
				}
			}
			// code 1 will return meal
			else if (requestCode == 1) {
				Meal meal = (Meal) data.getParcelableExtra("meal");
				day.addMeal(meal);
			}
		}
	}
}
