package com.JNJABA.caloriecounter;

import java.util.ArrayList;

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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewMealActivity extends Activity {
	private static String mMealName = null;

	private static Button bSubmit, bCancel, bNewFood, bSelectFood;
	private static EditText etMealName;
	private static LinearLayout llMealFoodText;

	private static ArrayList<Food> foods;
	private static ArrayList<TextView> views;

	private static Meal meal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_meal);

		foods = new ArrayList<Food>();
		views = new ArrayList<TextView>();

		meal = new Meal(mMealName);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_meal, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_new_meal,
					container, false);

			bSubmit = (Button) rootView.findViewById(R.id.bSubmitMeal);
			bCancel = (Button) rootView.findViewById(R.id.bCancelMeal);
			bNewFood = (Button) rootView.findViewById(R.id.bAddNewFood);
			bSelectFood = (Button) rootView.findViewById(R.id.bSelectNewFood);

			etMealName = (EditText) rootView.findViewById(R.id.etMealName);
			mMealName = etMealName.getText().toString();

			llMealFoodText = (LinearLayout) rootView
					.findViewById(R.id.llMealFoodText);

			bNewFood.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(),NewFoodActivity.class), 0);
				}
			});
			bSelectFood.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().startActivityForResult(new Intent(getActivity(),SelectFoodActivity.class), 0);
				}
			});
			
			bSubmit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					meal.setMealName(mMealName);

					for (Food food : foods)
						meal.addFood(food);

					getActivity().setResult(Activity.RESULT_OK,new Intent().putExtra("meal", meal));
					getActivity().finish();
				}
			});
			bCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().setResult(Activity.RESULT_CANCELED);
					getActivity().finish();
				}
			});
			return rootView;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d("NewMealActivity", "onActivityResult being called");
		
		if (resultCode == RESULT_OK) {
			if (requestCode == 0) {
				Log.d("NewMealActivity", "request met");

				final Food food = (Food) data.getParcelableExtra("food");
				foods.add(food);

				final TextView temp = new TextView(this);

				temp.setTag(true);

				temp.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Object obj = v.getTag();

						if (obj instanceof Boolean) {
							if (Boolean.TRUE.equals(obj)) {
								temp.setText(food.toString());
								v.setTag(false);
							} else {
								temp.setText(food.getFoodName());
								v.setTag(true);
							}
						}
					}
				});
				
				views.add(temp);

				temp.setText(food.getFoodName());
				temp.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

				llMealFoodText.addView(temp);
			}
		}
	}

}
