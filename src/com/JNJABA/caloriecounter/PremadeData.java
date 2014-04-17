package com.JNJABA.caloriecounter;

public enum PremadeData {
	EINSTEIN_BAGLE("Einstein Bagle", 120, 30, 15, 0, 0, 0, 0, 1),
	WATER("Water", 0, 0, 0, 0, 0, 0, 0, 1),
	BIG_MAC("Big Mac", 500, 40, 69, 300, 40, 15, 1000, 1),
	MCDONALDS_FRIES("Mcdonald's Fries", 100, 30, 30, 20, 0, 0, 400, 1),
	SPRITE("Sprite", 120, 40, 0, 0, 0, 0, 0, 1);
	
	private String foodName;
	private int foodCalories;
	private int foodTotalCarbs;
	private int foodTotalFat;
	private int foodCholesterol;
	private int foodPotassium;
	private int foodProtein;
	private int foodSodium;
	private double foodServingSize;
	
	private String mealName;
	private Food[] mealFoods;
	private int mealFoodCount;

	PremadeData(String name, Food[] foods, int count) {
		mealName = name;
		mealFoods = foods;
		mealFoodCount = count;
	}
	
	PremadeData(String name, int calories, int totalCarbs, int totalFat,
			int cholesterol, int potassium, int protein, int sodium, double servingSize) {
		foodName = name;
		foodCalories = calories;
		foodTotalCarbs = totalCarbs;
		foodTotalFat = totalFat;
		foodCholesterol = cholesterol;
		foodPotassium = potassium;
		foodProtein = protein;
		foodSodium = sodium;
		foodServingSize = servingSize;
	}
	
	public String getFoodName() {return foodName;}
	public int getFoodCalories() {return foodCalories;}
	public int getFoodTotalCarbs() {return foodTotalCarbs;}
	public int getFoodTotalFat() {return foodTotalFat;}
	public int getFoodCholesterol() {return foodCholesterol;}
	public int getFoodPotassium() {return foodPotassium;}
	public int getFoodProtein() {return foodProtein;}
	public int getFoodSodium() {return foodSodium;}
	public double getFoodServingSize() {return foodServingSize;}
	
	public String getMealName() {return mealName;}
	public Food[] getMealFoods() {return mealFoods;}
	public int getMealFoodCount() {return mealFoodCount;}
}
