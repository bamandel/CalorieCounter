package com.JNJABA.caloriecounter;

import java.util.ArrayList;
import java.util.Calendar;

public class Day {
	private String mDay;
	private ArrayList<Food> foodsEaten;
	private ArrayList<Meal> mealsEaten;
	private int totalCalories, totalFat, totalCholesterol, totalSodium, totalCarbs, totalProtein = 0;
	
	public Day(String day) {
		mDay = day;
		foodsEaten = new ArrayList<Food>();
		mealsEaten = new ArrayList<Meal>();
	}
	
	public Day() {
		mDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		foodsEaten = new ArrayList<Food>();
		mealsEaten = new ArrayList<Meal>();
	}
	
	public Day(ArrayList<Food> foods, ArrayList<Meal> meals) {
		foodsEaten = foods;
		mealsEaten = meals;
		
		updateDay();
	}
	
	public String getDay() {return mDay;}
	public void setDay(String day) {mDay = day;}
	
	public void addFood(Food food) {
		foodsEaten.add(food);
		updateDay();
	}
	public Food getFoodItem(int item) {return foodsEaten.get(item);}
	public void addMeal(Meal meal) {
		mealsEaten.add(meal);
		updateDay();
	}
	public Meal getMealItem(int item) {return mealsEaten.get(item);}
	
	public void updateDay() {
		resetValues();
		
		for(int i = 0; i < foodsEaten.size(); i++) {
			totalCalories += foodsEaten.get(i).getCalories();
			totalFat += foodsEaten.get(i).getTotalFat();
			totalCholesterol += foodsEaten.get(i).getCholesterol();
			totalSodium += foodsEaten.get(i).getSodium();
			totalCarbs += foodsEaten.get(i).getTotalCarbs();
			totalProtein += foodsEaten.get(i).getProtein();
		}
		for(int i = 0; i < mealsEaten.size(); i++) {
			totalCalories += mealsEaten.get(i).getTotalCalories();
			totalFat += mealsEaten.get(i).getTotalFat();
			totalCholesterol += mealsEaten.get(i).getTotalCholesterol();
			totalSodium += mealsEaten.get(i).getTotalSodium();
			totalCarbs += mealsEaten.get(i).getTotalCarbs();
			totalProtein += mealsEaten.get(i).getTotalProtein();
		}
	}
	
	private void resetValues() {
		totalCalories = 0;
		totalFat = 0;
		totalCholesterol = 0;
		totalSodium = 0;
		totalCarbs = 0;
		totalProtein = 0;
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
	
	public static void main(String []args) {
		Food snapple = new Food("Snapple", 10, 0, 0, 0, 15, 1, 0, 20);
		Food arnoldPalmer = new Food("Arnold Palmer", 80, 0, 0, 0, 15, 21, 0, 11.5);
		Food sprite = new Food("Sprite", 140, 1, 1, 1, 65, 38, 1, 12);
		Day today = new Day("Tuesday");
		
		Meal dinner = new Meal("Dinner");
		dinner.addFood(sprite);
		dinner.addFood(arnoldPalmer);
		dinner.addFood(snapple);
		dinner.updateMeal();
		
		arnoldPalmer.addFatNutrition("Saturated Fat", 0);
		arnoldPalmer.addFatNutrition("Trans Fat", 10);
		
		sprite.addCarbNutrition("Sugars", 38);

		today.addFood(sprite);
		today.addFood(snapple);
		today.addFood(arnoldPalmer);
		today.addMeal(dinner);
		today.updateDay();
		
		System.out.println(today.toString());
	}
	
}