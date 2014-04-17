package com.JNJABA.caloriecounter;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

public class Day {
	private static String mDay;
	private static final ArrayList<Food> foodsEaten = new ArrayList<Food>();
	private static final ArrayList<Meal> mealsEaten = new ArrayList<Meal>();
	private static int totalCalories, totalFat, totalCholesterol, totalSodium, totalCarbs, totalProtein, totalPotassium = 0;
	
	public Day() {
		mDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		
		resetValues();
	}
	
	public static class Holder {
		private static final Day INSTANCE = new Day();
	}
	
	public static Day getInstance() {
		return Holder.INSTANCE;
	}
	
	public boolean isEmpty() {
		if(foodsEaten.isEmpty())
			if(mealsEaten.isEmpty())
				return true;
		return false;
	}
	
	public String getDayName() {return mDay;}
	public void setDay(String day) {mDay = day;}
	
	public void addFood(Food food) {
		Log.d("Day", "WHY ISNT THIS WORKING??");
		foodsEaten.add(food);
		Log.d("Day", "Food added");
		updateDay();
	}
	public Food getFoodItem(int item) {return foodsEaten.get(item);}
	public void addMeal(Meal meal) {
		mealsEaten.add(meal);
		updateDay();
	}
	public Meal getMealItem(int item) {return mealsEaten.get(item);}
	
	public ArrayList<Food> getFoods() {
		return foodsEaten;
	}
	
	public ArrayList<Meal> getMeals() {
		return mealsEaten;
	}
	
	public void updateDay() {
		
		for(int i = 0; i < foodsEaten.size(); i++) {
			totalCalories += foodsEaten.get(i).getCalories();
			totalFat += foodsEaten.get(i).getTotalFat();
			totalCholesterol += foodsEaten.get(i).getCholesterol();
			totalSodium += foodsEaten.get(i).getSodium();
			totalCarbs += foodsEaten.get(i).getTotalCarbs();
			totalProtein += foodsEaten.get(i).getProtein();
			totalPotassium += foodsEaten.get(i).getPotassium();
		}
		for(int i = 0; i < mealsEaten.size(); i++) {
			totalCalories += mealsEaten.get(i).getTotalCalories();
			totalFat += mealsEaten.get(i).getTotalFat();
			totalCholesterol += mealsEaten.get(i).getTotalCholesterol();
			totalSodium += mealsEaten.get(i).getTotalSodium();
			totalCarbs += mealsEaten.get(i).getTotalCarbs();
			totalProtein += mealsEaten.get(i).getTotalProtein();
			totalPotassium += mealsEaten.get(i).getTotalPotassium();
		}
	}
	
	private void resetValues() {
		totalCalories = 0;
		totalFat = 0;
		totalCholesterol = 0;
		totalSodium = 0;
		totalCarbs = 0;
		totalProtein = 0;
		totalPotassium = 0;
	}
	
	public void resetDay() {
		String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		
		resetValues();
	}
	
	public int getTotalCalories() {return totalCalories;}
	public int getTotalFat() {return totalFat;}
	public int getTotalCholesterol() {return totalCholesterol;}
	public int getTotalSodium() {return totalSodium;}
	public int getTotalCarbs() {return totalCarbs;}
	public int getTotalProtein() {return totalProtein;}
	
	private String foodsToString() {
		String foods = "";
		
		for(int i = 0; i < foodsEaten.size(); i++) {
			foodsEaten.get(i).toString();
		}
		
		return foods;
	}
	
	private String mealsToString() {
		String meals = "";
		
		for(int i = 0; i < mealsEaten.size(); i++) {
			mealsEaten.get(i).toString();
		}
		
		return meals;
	}
	
	public String toString() {
		return "Day: " + mDay +
				foodsToString() +
				mealsToString();
	}
	
	public String nutritionToString() {
		return "Today's Calories: " + totalCalories + "\n" +
				"Today's Total Fat: " + totalFat + "\n" +
				"Today's Cholesterol: " + totalCholesterol + "\n" +
				"Today's Sodium: " + totalSodium + "\n" +
				"Today's Potassium: " + totalPotassium + "\n" +
				"Today's Carbs: " + totalCarbs + "\n" +
				"Today's Protein: " + totalProtein;
	}
}