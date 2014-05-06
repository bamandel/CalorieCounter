package com.JNJABA.caloriecounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class Profile {
	public static final int WEIGHT_KEEP = 0;
	public static final int WEIGHT_LOSS = 1;
	public static final int WEIGHT_GAIN = 2;
	
	public static final int ACTIVITY_LOW = 0;
	public static final int ACTIVITY_MEDIUM = 1;
	public static final int ACTIVITY_HIGH = 2;
	
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	
	private String firstName;
	private String lastName;
	private int age;
	private int sex;
	private int height;
	private int weight;
	
	/*
	 * BMI Chart
	 * 
	 * Below 18.5     = Underweight
	 * 18.5 - 24.9    = Normal
	 * 25.0 - 29.9    = Overweight
	 * 30.0 and above = Obese
	 * 
	 */
	
	private double BMI;
	
	//Depends on the gender but relatively similar
	private int caloriesNeeded;
	
	//Use above values to determine weight program and activity level;
	
	private int weightProgram;
	private int activityLevel;
	
	private static Context context;
	
	public Profile(Context context) {
		Profile.context = context;
		
		firstName = "";
		lastName = "";
		age = 0;
		sex = GENDER_MALE;
		height = 0;
		weight = 0;
		BMI = calculateBMI();
		caloriesNeeded = calculateCaloriesNeeded();
		weightProgram = WEIGHT_KEEP;
		activityLevel = ACTIVITY_LOW;
		
	}
	
	public static class Holder {
		private static final Profile INSTANCE = new Profile(context);
	}
	
	public static Profile getInstance() {
		return Holder.INSTANCE;
	}
	
	public double getBMI() {return BMI;}
	public void setBMI(double value) {BMI = value;}
	public double calculateBMI() {
		//Use Pounds and Inches
		
		return (weight / (int) Math.pow(height, 2)) * 703;	
	}
	
	public int getCaloriesNeeded() {return caloriesNeeded;}
	public void setCaloriesNeeded(int value) {caloriesNeeded = value;}
	public int calculateCaloriesNeeded() {
		//Uses Killograms and Centimeters
		
		if(sex == GENDER_MALE)
			return (int) (10 * LBtoKG(weight) + (6.25 * INtoCM(height)) - (5 * age) + 5);
		return (int) (10 * LBtoKG(weight) + (6.25 * INtoCM(height)) - (5 * age) - 161);
	}
	
	private double LBtoKG(double value) {
		return value / 2.2046;
		//KGtoLB is value * 2.2046;
	}
	
	private double INtoCM(double value) {
		return value * 2.54;
		//CMtoIN is value * .3937008;
	}
	
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public int getSex() {return sex;}
	public void setSex(int sex) {this.sex = sex;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public int getWeight() {return weight;}
	public void setWeight(int weight) {this.weight = weight;}
	public int getWeightProgram() {return weightProgram;}
	public void setWeightProgram(int weightProgram) {this.weightProgram = weightProgram;}
	public int getActivityLevel() {return activityLevel;}
	public void setActivityLevel(int activityLevel) {this.activityLevel = activityLevel;}
	
	public String toString() {
		return firstName + "\n" +
				lastName + "\n" +
				age + "\n" +
				sex + "\n" +
				height + "\n" +
				weight + "\n" +
				BMI + "\n" +
				caloriesNeeded + "\n" +
				weightProgram + "\n" +
				activityLevel + "\n";
	}
	
	public void writeAllDataToFile() {
		try {
			OutputStreamWriter output = new OutputStreamWriter(context.openFileOutput("profile.txt", Context.MODE_PRIVATE));
			output.write(toString());
			output.close();
		} catch (IOException e) {
			Log.d("Profile", e.getMessage());
		}
	}
	
	public void readFromFile() {
		try {
			InputStream input = context.openFileInput("profile.txt");
			
			if(input != null) {
				InputStreamReader inputReader = new InputStreamReader(input);
				BufferedReader bufferedReader = new BufferedReader(inputReader);
				String value = "";
				
				int count = 0;
				
				while((value = bufferedReader.readLine()) != null) {
					switch(count) {
					case 0:
						firstName = value;
						break;
					case 1:
						lastName = value;
						break;
					case 2:
						age = Integer.parseInt(value);
						break;
					case 3:
						sex = Integer.parseInt(value);
						break;
					case 4:
						height = Integer.parseInt(value);
						break;
					case 5:
						weight = Integer.parseInt(value);
						break;
					case 6:
						BMI = Double.parseDouble(value);
						break;
					case 7:
						caloriesNeeded = Integer.parseInt(value);
						break;
					case 8:
						weightProgram = Integer.parseInt(value);
						break;
					case 9:
						activityLevel = Integer.parseInt(value);
					default:
						break;
					}
					
					count++;
				}
				
				input.close();
			}
		} catch (FileNotFoundException e) {
			Log.d("Profile", e.getMessage());
		} catch (IOException e) {
			Log.d("Profile", e.getMessage());
		}
	}
}
