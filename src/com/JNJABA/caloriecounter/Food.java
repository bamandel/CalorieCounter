package com.JNJABA.caloriecounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable{
	
	/*
	 * Everything is being stored in Milligrams and will
	 * be converted when inserting and accesing the data
	 */
	
	public static final int DAILY_CALORIES = 2000000;
	public static final int DAILY_POTASSIUM = 3500;
	public static final int DAILY_TOTAL_FAT = 65000;
	public static final int DAILY_SATURATED_FAT = 20000;
	public static final int DAILY_CHOLESTEROL = 300;
	public static final int DAILY_SODIUM = 2400;
	public static final int DAILY_TOTAL_CARBS = 300000;
	public static final int DAILY_DIETARY_FIBER = 25000;
	public static final int DAILY_PROTEIN = 50000;
	
	private String mFoodName;
	private int mCalories, mPotassium, mTotalFat, mCholesterol, mSodium, mTotalCarbs, mProtein;
	private int amountEaten;
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
	
	public Food(String foodName, double calories, double potassium, double totalFat, double cholesterol, double sodium, double totalCarbs, double protein, double servingSize) {
		mFoodName = foodName;
		setPotassium(potassium);
		setCalories(calories);
		setTotalFat(totalFat);
		setCholesterol(cholesterol);
		setSodium(sodium);
		setTotalCarbs(totalCarbs);
		setProtein(protein);
		mServingSize = servingSize;
		
		moreCarbs = new HashMap<String, Integer>();
		moreFat = new HashMap<String, Integer>();
	}
	
	public void addAmountEaten(double amount) {amountEaten = (int) amount * 1000;}
	public int getAmountEaten() {return amountEaten;}
	
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
	public void setCalories(double mCalories) {this.mCalories = (int) mCalories * 1000;}
	public int getPotassium() {return mPotassium;}
	public void setPotassium(double potassium) {mPotassium = (int) potassium * 1000;}
	public int getTotalFat() {return mTotalFat;}
	public void setTotalFat(double mTotalFat) {this.mTotalFat = (int) mTotalFat * 1000;}
	public int getCholesterol() {return mCholesterol;}
	public void setCholesterol(double cholesterol) {mCholesterol = (int) cholesterol * 1000;}
	public int getSodium() {return mSodium;}
	public void setSodium(double mSodium) {this.mSodium = (int) mSodium * 1000;}
	public int getTotalCarbs() {return mTotalCarbs;}
	public void setTotalCarbs(double mTotalCarbs) {this.mTotalCarbs = (int) (mTotalCarbs * 1000);}
	public int getProtein() {return mProtein;}
	public void setProtein(double mProtein) {this.mProtein = (int) mProtein * 1000;}
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
