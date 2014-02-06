package com.example.neverkillmyplant;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	ArrayList<Plant> plantList = new ArrayList<Plant>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Creation de la page de base
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button addPlanButton = (Button) findViewById(R.id.button1);
		addPlanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent add = new Intent(MainActivity.this,
						AddPlantActivity.class);
				startActivity(add);
			}
		});
		
		// ajout dynamique de bouton pour les plantes
		for(Plant plant : plantList){
			ajoutBouton(plant.getName());
			
		}
	}
	
	protected void onResume(){
		super.onResume();
		
		Intent intent = getIntent();
		Plant plant;
		if (intent.getParcelableExtra("plantList") != null)
			plant = intent.getParcelableExtra("plantList");
		else
			plant = new Plant("Bug");
		ajoutBouton(plant.getName());
	}
	
	
	/************************** methode auxiliaires ******************/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ajoutBouton(String str) {
		LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layoutTest);
		layoutOfDynamicContent.removeAllViewsInLayout();
		
		Button bouton = new Button(this);
		bouton.setText(str);
		
		LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutOfDynamicContent.addView(bouton, layoutParam);
		
	}
	
	
}
