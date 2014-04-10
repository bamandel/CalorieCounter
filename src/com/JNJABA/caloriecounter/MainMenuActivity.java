package com.JNJABA.caloriecounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		Button bHealth = (Button) findViewById(R.id.bHealth);
		bHealth.setOnClickListener(this);
		Button bJustAte = (Button) findViewById(R.id.bJustAte);
		bJustAte.setOnClickListener(this);
		Button bMeals = (Button) findViewById(R.id.bCalendar);
		bMeals.setOnClickListener(this);
		Button bSettings = (Button) findViewById(R.id.bSettings);
		bSettings.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.bHealth:
			startActivity(new Intent(this, HealthActivity.class));
			break;
		case R.id.bJustAte:
			startActivity(new Intent(this, JustAteActivity.class));
			break;
		case R.id.bCalendar:
			//Will currently do nothing

			break;
		case R.id.bSettings:
			
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
