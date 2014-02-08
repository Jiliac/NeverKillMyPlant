package com.example.neverkillmyplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

public class PlantCard extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plant_card_activity);
		
		// on s'occupe de définir le premier bouton
		Button retour = (Button) findViewById(R.id.button2);
		retour.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				startActivity(new Intent(PlantCard.this,MainActivity.class));
			}
		});
		
		// puis le deuxieme
		Button photo = (Button) findViewById(R.id.button1);
		photo.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//	A DEFINIR !!!!!!!!!!
			}
		});
		
		// on récupère la plante que l'on doit afficher
		Intent startIntent = getIntent();
		Plant plant = startIntent.getParcelableExtra("plant");
		
		// et on definit la TextView
		TextView plantName = (TextView) findViewById(R.id.textView1);
		plantName.setText(plant.getName());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
