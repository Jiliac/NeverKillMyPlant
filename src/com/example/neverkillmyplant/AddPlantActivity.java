package com.example.neverkillmyplant;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlantActivity extends Activity implements View.OnClickListener{
	ArrayList<Plant> plantList = new ArrayList<Plant>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_plant);

		Button addPlanButton = (Button) findViewById(R.id.button1);
		addPlanButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {
		
		EditText et = (EditText) findViewById(R.id.editText1);
		String name = et.getText().toString();
		
		
		Plant plant = new Plant(name);
		plantList.add(plant);
		Intent retour = new Intent(AddPlantActivity.this,
				MainActivity.class);
		
		// ajout d'un extra
		retour.putExtra("plantList",plant);
		
		
		startActivity(retour);
	}
	
}
