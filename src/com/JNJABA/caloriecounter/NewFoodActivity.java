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
import android.widget.EditText;

public class NewFoodActivity extends Activity {
	private static String mFoodName = null;
	private static double mCalories, mPotassium, mTotalFat, mCholesterol, mSodium, mTotalCarbs, mProtein = -1;
	private static double mServingSize = -1;
	
	private static EditText etFoodName, etCalories, etPotassium, etTotalFat, etCholesterol, etSodium, etTotalCarbs, etProtein, etServingSize;
	//private static Bitmap foodPic = null;
	
	private static Food food;
	
	private static Button bCancel, bSubmit;
	
	private static Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_food);
		
		db = new Database(this);
		food = new Food();
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_food, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_new_food, container, false);
			
			Log.d("NewFoodActivity", "New Food Activity ready");
			
			etFoodName = (EditText) rootView.findViewById(R.id.etFoodName);
			etCalories = (EditText) rootView.findViewById(R.id.etCalories);
			//etPotassium = (EditText) rootView.findViewById(R.id.etPotassium);
			etTotalFat = (EditText) rootView.findViewById(R.id.etTotalFat);
			//etCholesterol = (EditText) rootView.findViewById(R.id.etCholesterol);
			//etSodium = (EditText) rootView.findViewById(R.id.etSodium);
			etTotalCarbs = (EditText) rootView.findViewById(R.id.etTotalCarbs);
			etProtein = (EditText) rootView.findViewById(R.id.etProtein);
			etServingSize = (EditText) rootView.findViewById(R.id.etServingSize);
			
			bSubmit = (Button) rootView.findViewById(R.id.bSubmitFood);
			bCancel = (Button) rootView.findViewById(R.id.bCancelFood);
			
			bCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getActivity().setResult(Activity.RESULT_CANCELED);
					getActivity().finish();
				}
				
			});
			bSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Log.d("NewFoodActivity", "OnClick entered");
					
					if((mFoodName = etFoodName.getText().toString()).equals(""))
						mFoodName = "No name";
					
					if(isDouble(etCalories.getText().toString()))
						mCalories = Double.parseDouble(etCalories.getText().toString());
					else
						mCalories = 0;
					
					/*if(isDouble(etPotassium.getText().toString()))
						mPotassium = Double.parseDouble(etPotassium.getText().toString());
					else
						mPotassium = -1;*/
					
					if(isDouble(etTotalFat.getText().toString()))
						mTotalFat = Double.parseDouble(etTotalFat.getText().toString());
					else
						mTotalFat = 0;
					
					/*if(isDouble(etCholesterol.getText().toString()))
						mCholesterol = Double.parseDouble(etCholesterol.getText().toString());
					else mCholesterol = -1;
					
					if(isDouble(etSodium.getText().toString()))
						mSodium = Double.parseDouble(etSodium.getText().toString());
					else
						mSodium = -1;*/
					
					if(isDouble(etTotalCarbs.getText().toString()))
						mTotalCarbs = Double.parseDouble(etTotalCarbs.getText().toString());
					else
						mTotalCarbs = 0;
					
					if(isDouble(etProtein.getText().toString()))
						mProtein = Double.parseDouble(etProtein.getText().toString());
					else
						mProtein = 0;
					
					if(isDouble(etServingSize.getText().toString()))
						mServingSize = Double.parseDouble(etServingSize.getText().toString());
					else
						mServingSize = 0;
					
					Log.d("NewFoodActivity", "Values set");
					
					food.setFoodName(mFoodName);
					food.setCalories(mCalories);
					food.setPotassium(mPotassium);
					food.setTotalFat(mTotalFat);
					food.setCholesterol(mCholesterol);
					food.setSodium(mSodium);
					food.setTotalCarbs(mTotalCarbs);
					food.setProtein(mProtein);
					food.setServingSize(mServingSize);
					
					Log.d("NewFoodActivity", "Creating food item");
					
					try {
						db.open();
						db.addFoodValue(food);
						db.close();
					} catch (Exception e) {
						Log.d("NewFoodActivity", "Database not opened");
					}
					
					getActivity().setResult(Activity.RESULT_OK, new Intent().putExtra("food", food));
					getActivity().finish();
				}
				
			});
			return rootView;
		}
	}
	
	private static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
