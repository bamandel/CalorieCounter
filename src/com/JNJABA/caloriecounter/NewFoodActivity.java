package com.JNJABA.caloriecounter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
	private static int mCalories, mPotassium, mTotalFat, mCholesterol, mSodium, mTotalCarbs, mProtein = -1;
	private static double mServingSize = -1;
	
	private static EditText etFoodName, etCalories, etPotassium, etTotalFat, etCholesterol, etSodium, etTotalCarbs, etProtein, etServingSize;
	//private static Bitmap foodPic = null;
	
	private static Food food;
	
	private static Button bCancel, bSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_food);
		
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
			
			etFoodName = (EditText) rootView.findViewById(R.id.etFoodName);
			etCalories = (EditText) rootView.findViewById(R.id.etCalories);
			etPotassium = (EditText) rootView.findViewById(R.id.etPotassium);
			etTotalFat = (EditText) rootView.findViewById(R.id.etTotalFat);
			etCholesterol = (EditText) rootView.findViewById(R.id.etCholesterol);
			etSodium = (EditText) rootView.findViewById(R.id.etSodium);
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
					
					if((mFoodName = etFoodName.getText().toString()).equals(""))
						mFoodName = "null";
					
					if(isInteger(etCalories.getText().toString()))
						mCalories = Integer.parseInt(etCalories.getText().toString());
					else
						mCalories = -1;
					
					if(isInteger(etPotassium.getText().toString()))
						mPotassium = Integer.parseInt(etPotassium.getText().toString());
					else
						mPotassium = -1;
					
					if(isInteger(etTotalFat.getText().toString()))
						mTotalFat = Integer.parseInt(etTotalFat.getText().toString());
					else
						mTotalFat = -1;
					
					if(isInteger(etCholesterol.getText().toString()))
						mCholesterol = Integer.parseInt(etCholesterol.getText().toString());
					else mCholesterol = -1;
					
					if(isInteger(etSodium.getText().toString()))
						mSodium = Integer.parseInt(etSodium.getText().toString());
					else
						mSodium = -1;
					
					if(isInteger(etTotalCarbs.getText().toString()))
						mTotalCarbs = Integer.parseInt(etTotalCarbs.getText().toString());
					else
						mTotalCarbs = -1;
					
					if(isInteger(etProtein.getText().toString()))
						mProtein = Integer.parseInt(etProtein.getText().toString());
					else
						mProtein = -1;
					
					if(isDouble(etServingSize.getText().toString()))
						mServingSize = Double.parseDouble(etServingSize.getText().toString());
					else
						mServingSize = -1;
					
					food.setFoodName(mFoodName);
					food.setCalories(mCalories);
					food.setPotassium(mPotassium);
					food.setTotalFat(mTotalFat);
					food.setCholesterol(mCholesterol);
					food.setSodium(mSodium);
					food.setTotalCarbs(mTotalCarbs);
					food.setProtein(mProtein);
					food.setServingSize(mServingSize);
					
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
