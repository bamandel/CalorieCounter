package com.JNJABA.caloriecounter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

public class NewFoodActivity extends Activity {
	private static String mFoodName = null;
	private static int mCalories, mPotassium, mTotalFat, mCholesterol, mSodium, mTotalCarbs, mProtein = -1;
	private static double mServingSize = -1;
	
	private static EditText etFoodName, etCalories, etPotassium, etTotalFat, etCholesterol, etSodium, etTotalCarbs, etProtein, etServingSize;
	private static Bitmap foodPic = null;
	
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
			etFoodName.setText("No name");
			etCalories = (EditText) rootView.findViewById(R.id.etCalories);
			etCalories.setText("0");
			etPotassium = (EditText) rootView.findViewById(R.id.etPotassium);
			etPotassium.setText("0");
			etTotalFat = (EditText) rootView.findViewById(R.id.etTotalFat);
			etTotalFat.setText("0");
			etCholesterol = (EditText) rootView.findViewById(R.id.etCholesterol);
			etCholesterol.setText("0");
			etSodium = (EditText) rootView.findViewById(R.id.etSodium);
			etSodium.setText("0");
			etTotalCarbs = (EditText) rootView.findViewById(R.id.etTotalCarbs);
			etTotalCarbs.setText("0");
			etProtein = (EditText) rootView.findViewById(R.id.etProtein);
			etProtein.setText("0");
			etServingSize = (EditText) rootView.findViewById(R.id.etServingSize);
			etServingSize.setText("0");
			
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
					
					try{
						mFoodName = etFoodName.getText().toString();
						mCalories = Integer.parseInt(etCalories.getText().toString());
						mPotassium = Integer.parseInt(etPotassium.getText().toString());
						mTotalFat = Integer.parseInt(etTotalFat.getText().toString());
						mCholesterol = Integer.parseInt(etCholesterol.getText().toString());
						mSodium = Integer.parseInt(etSodium.getText().toString());
						mTotalCarbs = Integer.parseInt(etTotalCarbs.getText().toString());
						mProtein = Integer.parseInt(etProtein.getText().toString());
						mServingSize = Double.parseDouble(etServingSize.getText().toString());
					} catch (Exception e) {
						Toast.makeText(getActivity().getBaseContext(), "Must fill out all data", Toast.LENGTH_LONG).show();
						getActivity().finish();
					}
					
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

}
