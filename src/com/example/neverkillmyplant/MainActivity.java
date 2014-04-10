package com.example.neverkillmyplant;

import javaClass.Plant;
import javaClass.PlantArray;
import javaClass.clickPlantButton;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private PlantArray planteListe;
	private PlantArray plantesAffichees;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Creation de la page de base
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// les boutons ont besoin d'être réaffiché.
		planteListe = new PlantArray();
		plantesAffichees = new PlantArray();
		plantesAffichees.save("affichage.data");

		// on set la methode du bouton add
		Button addPlanButton = (Button) findViewById(R.id.button1);
		Typeface FONT = Typeface.createFromAsset(getAssets(),
				"fonts/MoonFlower.ttf");
		addPlanButton.setTypeface(FONT);
		addPlanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent add = new Intent(MainActivity.this,
						AddPlantActivity.class);
				startActivity(add);
			}
		});
	}

	/******* cycle de vie *********/

	public void onPause() {
		super.onPause();
		planteListe.save("liste.data");
		plantesAffichees.save("affichage.data");
	}

	public void onResume() {
		super.onResume();
		planteListe.load("liste.data");
		plantesAffichees.load("affichage.data");
		this.ajoutBouton();
	}

	/************************** methode auxiliaires ******************/

	public void ajoutBouton() {
		// on crée dynamiquement un bouton par l'intermédiaire d'un layout
		LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layoutTest);
		LinearLayout A = new LinearLayout(this);
		A.setOrientation(LinearLayout.VERTICAL);

		for (Plant plant : planteListe) {
			if (!plantesAffichees.isIn(plant)) {
				plantesAffichees.add(plant);

				// on crée
				Button bouton = new Button(this);
				bouton.setText(plant.getName());
				// on affiche
				A.addView(bouton);
				// on va vers la fiche de la plante lors du click
				bouton.setOnClickListener(new clickPlantButton(plant, this));
				Typeface FONT = Typeface.createFromAsset(getAssets(),
						"fonts/MoonFlower.ttf");
				bouton.setTypeface(FONT);
			}
		}
		layoutOfDynamicContent.addView(A);
	}

	// methode de debug
	public void reset() {
		planteListe = new PlantArray();
		plantesAffichees = new PlantArray();

		planteListe.save("liste.data");
		plantesAffichees.save("affichage.data");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
