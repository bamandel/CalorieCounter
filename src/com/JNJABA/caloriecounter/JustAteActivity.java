package com.JNJABA.caloriecounter;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class JustAteActivity extends Activity {
	private static Button bNewFood, bNewMeal, bSelectFood, bSelectMeal;
	private Day day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_just_ate);

		day = Day.getInstance();
		
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
		
		if (resultCode == RESULT_OK && data != null) {
			// code 0 will return food
			if (requestCode == 0) {
				Log.d("Just Ate", "getting food");
				final Food food = (Food) data.getParcelableExtra("food");
				Log.d("Just Ate", "storing food");
				
				final EditText input = new EditText(JustAteActivity.this);
				input.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
				input.setInputType(InputType.TYPE_CLASS_NUMBER |
						InputType.TYPE_NUMBER_FLAG_DECIMAL);
				
				final AlertDialog alert = new AlertDialog.Builder(JustAteActivity.this).create();
				alert.setTitle("How much eaten?");
				alert.setMessage("Please enter how much food you ate in grams");
				alert.setView(input);
				alert.setCancelable(true);
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "Submit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						double temp = Double.parseDouble(input.getText().toString());
						day.addFood(food, temp);
					}
				});
				alert.show();
				
			}
			// code 1 will return meal
			else if (requestCode == 1) {
				Log.d("Just Ate", "storing meal");
				Meal meal = (Meal) data.getParcelableExtra("meal");
				day.addMeal(meal);
			}
		}
	}
	
}
