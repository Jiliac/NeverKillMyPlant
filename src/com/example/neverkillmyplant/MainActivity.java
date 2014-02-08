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

	}

	protected void onResume() {
		super.onResume();

		// on ajoute la nouvelle plant à la liste
		Plant plant;
		Intent intent = getIntent(); // ATTENTION que se passe-t-il si on vient
										// de PlatCard activity
		if (intent.getParcelableExtra("plant") != null)
			plant = intent.getParcelableExtra("plant");
		else
			plant = new Plant("Bug!"); /* c'est juste pour le debug */
		plantList.add(plant);

		ajoutBouton();
	}

	/************************** methode auxiliaires ******************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ajoutBouton() {
		// on crée dynamiquement un bouton par l'intermédiaire d'un layout
		LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layoutTest);
		LinearLayout A = new LinearLayout(this);
		A.setOrientation(LinearLayout.HORIZONTAL);
		for (Plant plant : plantList) {
			// on crée
			Button bouton = new Button(this);
			bouton.setText(plant.getName());
			// on affiche
			A.addView(bouton);
			// on va vers la fiche de la plante lors du click
			bouton.setOnClickListener(new clickPlantButton(plant, this));
		}
		layoutOfDynamicContent.addView(A);

	}

}
