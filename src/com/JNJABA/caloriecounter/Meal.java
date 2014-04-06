package com.JNJABA.caloriecounter;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable{
	private String mMealName;
	private ArrayList<Food> myMeal;
	private int totalCalories, totalFat, totalCholesterol, totalSodium, totalCarbs, totalProtein = 0;
	
	public Meal() {
		myMeal = new ArrayList<Food>();
	}
	
	public Meal(Parcel in) {
		mMealName = in.readString();
		in.readList(myMeal, null);
	}
	
	public Meal(String mealName) {
		mMealName = mealName;
		
		myMeal = new ArrayList<Food>();
	}
	
	public void addFood(Food food) {
		myMeal.add(food);
		updateMeal();
	}
	public void getFoodItem(int index) {myMeal.get(index);}
	
	public void updateMeal() {
		for(int i = 0; i < myMeal.size(); i++) {
			totalCalories += myMeal.get(i).getCalories();
			totalFat += myMeal.get(i).getTotalFat();
			totalCholesterol += myMeal.get(i).getCholesterol();
			totalSodium += myMeal.get(i).getSodium();
			totalCarbs += myMeal.get(i).getTotalCarbs();
			totalProtein += myMeal.get(i).getProtein();
		}
	}

	public String getMealName() {return mMealName;}
	public void setMealName(String name) {mMealName = name;}
	public ArrayList<Food> getFoods() {return myMeal;}
	public void setFoods(ArrayList<Food> foods) {myMeal = foods;}
	public int getTotalCalories() {return totalCalories;}
	public int getTotalFat() {return totalFat;}
	public int getTotalCholesterol() {return totalCholesterol;}
	public int getTotalSodium() {return totalSodium;}
	public int getTotalCarbs() {return totalCarbs;}
	public int getTotalProtein() {return totalProtein;}
	
	@SuppressWarnings("unused")
	private String getFoodToStrings() {
		String foods = "";
		
		for(int i = 0; i < myMeal.size(); i++) {
			foods += "\n" + myMeal.get(i).getFoodName();
		}
		
		return foods;
	}
	
	public String toString() {
		return "Meal: " + mMealName +
				//getFoodToStrings() +
				"\n\nTotal Calories: " + totalCalories +
				"\nTotal Fat: " + totalFat +
				"\nTotal Cholesterol: " + totalCholesterol +
				"\nTotal Sodium: " + totalSodium +
				"\nTotal Carbs: " + totalCarbs +
				"\nTotal Protein: " + totalProtein;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(mMealName);
		dest.writeTypedList(myMeal);
	}
	
	public static final Parcelable.Creator<Meal> CREATOR = new Parcelable.Creator<Meal>() {

		@Override
		public Meal createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Meal(source);
		}

		@Override
		public Meal[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Meal[size];
		}
		
	};
}