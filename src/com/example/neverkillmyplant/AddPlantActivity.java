package com.example.neverkillmyplant;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddPlantActivity extends Activity implements View.OnClickListener {
	private Spinner espece;
	private Spinner sticker;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_plant);

		Button addPlanButton = (Button) findViewById(R.id.button1);
		addPlanButton.setOnClickListener(this);

		espece = (Spinner) findViewById(R.id.spinner1);
		sticker = (Spinner) findViewById(R.id.spinner2);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//  IL FAUT PREVOIR LE CAS OU L'UTILISATEUR UTILISE LE BOUTON BACK
	// p-e faire que onPause appelle onDestroy et faire add...
	
	public void onClick(View v) {

		EditText et = (EditText) findViewById(R.id.editText1);
		String name = et.getText().toString();

		// creation de la plante
		String textSticker = sticker.getSelectedItem().toString();
		String textespece = espece.getSelectedItem().toString();
		Plant plant = new Plant(name, textespece, textSticker);

		// sauvegarde de cette plante
		PlantArray planteListe = new PlantArray("liste.data");
		plant.setId(planteListe.size());
		planteListe.add(plant);
		planteListe.save("liste.data");
		
		
		finish();
	}
}
