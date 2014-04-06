package com.JNJABA.caloriecounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable{
	public static final int DAILY_CALORIES = 2000;
	public static final int DAILY_POTASSIUM = 3500; // mg
	public static final int DAILY_TOTAL_FAT = 65; // g
	public static final int DAILY_SATURATED_FAT = 20; // g
	public static final int DAILY_CHOLESTEROL = 300; // mg
	public static final int DAILY_SODIUM = 2400; // mg
	public static final int DAILY_TOTAL_CARBS = 300; // g
	public static final int DAILY_DIETARY_FIBER = 25; // g
	public static final int DAILY_PROTEIN = 50; // g
	
	private String mFoodName;
	private int mCalories, mPotassium, mTotalFat, mCholesterol, mSodium, mTotalCarbs, mProtein;
	private double mServingSize;
	private Bitmap foodPic = null;
	private Map<String, Integer> moreCarbs, moreFat;
	
	public Food() {
		moreCarbs = new HashMap<String, Integer>();
		moreFat = new HashMap<String, Integer>();
	}
	
	public Food(Parcel in) {
		mFoodName = in.readString();
		mCalories = in.readInt();
		mTotalCarbs = in.readInt();
		mTotalFat = in.readInt();
		mCholesterol = in.readInt();
		mPotassium = in.readInt();
		mProtein = in.readInt();
		mSodium = in.readInt();
		mServingSize = in.readDouble();
		
		//Should try to write everything individually to a new map
		moreCarbs = null;
		moreFat = null;
	}
	
	public Food(String foodName, double servingSize) {
		mFoodName = foodName;
		mServingSize = servingSize;
		
		moreCarbs = new HashMap<String, Integer>();
		moreFat = new HashMap<String, Integer>();
	}
	
	public Food(String foodName, int calories, int totalFat, int totalCarbs, int protein, double servingSize) {
		mFoodName = foodName;
		mServingSize = servingSize;
		mCalories = calories;
		mTotalFat = totalFat;
		mTotalCarbs = totalCarbs;
		mProtein = protein;
		
		moreCarbs = new HashMap<String, Integer>();
		moreFat = new HashMap<String, Integer>();
	}
	
	public Food(String foodName, int calories, int potassium, int totalFat, int cholesterol, int sodium, int totalCarbs, int protein, double servingSize) {
		mFoodName = foodName;
		mPotassium = potassium;
		mCalories = calories;
		mTotalFat = totalFat;
		mCholesterol = cholesterol;
		mSodium = sodium;
		mTotalCarbs = totalCarbs;
		mProtein = protein;
		mServingSize = servingSize;
		
		moreCarbs = new HashMap<String, Integer>();
		moreFat = new HashMap<String, Integer>();
	}
	
	public void addFatNutrition(String name, int quantity) {moreFat.put(name, quantity);}
	public int getFatNutritionValue(String name) {return moreFat.get(name);}
	public void addCarbNutrition(String name, int quantity) {moreCarbs.put(name, quantity);}
	public int getCarbNutritionValue(String name) {return moreCarbs.get(name);}
	public void addPicture(Bitmap pic) {foodPic = pic;}
	public Bitmap getPicture() {return foodPic;}
	
	public double getDailyTotalFat() {return mTotalFat / DAILY_TOTAL_FAT;}
	public double getDailyTotalCarbs() {return mTotalCarbs / DAILY_TOTAL_CARBS;}
	public double getDailyPotassium() {return mPotassium / DAILY_POTASSIUM;}
	public double getDailyCholesterol() {return mCholesterol / DAILY_CHOLESTEROL;}
	public double getDailySodium() {return mSodium / DAILY_SODIUM;}
	public double getDailyProtein() {return mProtein / DAILY_PROTEIN;}
	
	@SuppressWarnings("unused")
	private String getSpecialItemsToString(String special) {
		String item = "";
		
		if(special.equals("fat")) {
			if(moreFat.isEmpty())
				return "";
			for(Entry<String, Integer> entry: moreFat.entrySet()) {
				item = "\n" + entry.getKey() + ": "+ entry.getValue().toString();
			}
		} else if (special.equals("carbs")) {
			if(moreCarbs.isEmpty())
				return "";
			for(Entry<String, Integer> entry: moreCarbs.entrySet()) {
				item = "\n" + entry.getKey() + ": " + entry.getValue().toString();
			}
		}
		
		return item;
	}
	
	public String toString() {
		return "Food: " + mFoodName +
				"\nServing Size: " + mServingSize +
				"\nCalories: " + mCalories +
				"\nTotal Fat: " + mTotalFat + "    " + getDailyTotalFat() + "%" +
				//getSpecialItemsToString("fat") +
				"\nTotal Cholesterol: " + mCholesterol + "    " + getDailyCholesterol() + "%" +
				"\nSodium: " + mSodium + "    " + getDailySodium() + "%" +
				"\nTotal Carbs: " + mTotalCarbs + "    " + getDailyTotalCarbs() + "%" +
				//getSpecialItemsToString("carbs") +
				"\nProtein: " + mProtein + "    " + getDailyProtein() + "%";
	}
	
	public String getFoodName() {return mFoodName;}
	public void setFoodName(String mFoodName) {this.mFoodName = mFoodName;}
	public int getCalories() {return mCalories;}
	public void setCalories(int mCalories) {this.mCalories = mCalories;}
	public int getPotassium() {return mPotassium;}
	public void setPotassium(int potassium) {mPotassium = potassium;}
	public int getTotalFat() {return mTotalFat;}
	public void setTotalFat(int mTotalFat) {this.mTotalFat = mTotalFat;}
	public int getCholesterol() {return mCholesterol;}
	public void setCholesterol(int cholesterol) {mCholesterol = cholesterol;}
	public int getSodium() {return mSodium;}
	public void setSodium(int mSodium) {this.mSodium = mSodium;}
	public int getTotalCarbs() {return mTotalCarbs;}
	public void setTotalCarbs(int mTotalCarbs) {this.mTotalCarbs = mTotalCarbs;}
	public int getProtein() {return mProtein;}
	public void setProtein(int mProtein) {this.mProtein = mProtein;}
	public double getServingSize() {return mServingSize;}
	public void setServingSize(double size) {mServingSize = size;}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(mFoodName);
		dest.writeInt(mCalories);
		dest.writeInt(mTotalCarbs);
		dest.writeInt(mTotalFat);
		dest.writeInt(mCholesterol);
		dest.writeInt(mPotassium);
		dest.writeInt(mProtein);
		dest.writeInt(mSodium);
		dest.writeDouble(mServingSize);
		
		dest.writeMap(moreCarbs);
		dest.writeMap(moreFat);
		
		//foodPic.writeToParcel(dest, 0);
	}
	
	public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {

		@Override
		public Food createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Food(source);
		}

		@Override
		public Food[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Food[size];
		}
		
	};
}
