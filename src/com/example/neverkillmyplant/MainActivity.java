package com.example.neverkillmyplant;

import java.io.File;
import java.util.ArrayList;

import javaClass.Plant;
import javaClass.PlantArray;
import javaClass.clickPlantButton;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {
	private PlantArray planteListe;
	private PlantArray plantesAffichees;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Creation de la page de base
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface FONT = Typeface.createFromAsset(getAssets(),
				"fonts/MoonFlower.ttf");

		// les boutons ont besoin d'être réaffiché.
		planteListe = new PlantArray();
		plantesAffichees = new PlantArray();
		plantesAffichees.save("neverkillmyplant" + File.separator
				+ "affichage.data");

		// on set la methode du bouton add
		Button addPlanButton = (Button) findViewById(R.id.button1);
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
		planteListe.save("neverkillmyplant" + File.separator + "liste.data");
		plantesAffichees.save("neverkillmyplant" + File.separator
				+ "affichage.data");
	}

	public void onResume() {
		super.onResume();
		
		planteListe.load("neverkillmyplant" + File.separator + "liste.data");
		/*
		plantesAffichees.load("neverkillmyplant" + File.separator
				+ "affichage.data");
		this.ajoutBouton();
		*/
		handleListView((ListView) findViewById(R.id.listView1));
	}

	/************************** methode auxiliaires ******************/

	public void ajoutBouton() {
		// on crée dynamiquement un bouton par l'intermédiaire d'un layout
		LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layout);
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

	// TEST DES LISTVIEW
	public void handleListView(ListView view) {
		// creation des données
		ArrayList<String> strs = new ArrayList<String>();
		for (Plant plant : planteListe)
			strs.add(plant.getName());

		// creation de l'adapteur
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs);

		// on associe la listview et l'adapter
		view.setAdapter(adapter);

		// on definie ce qu'il ce passe lorsqu'on appuie sur un element
		view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Plant plant = planteListe.get(position);
				Intent intent = new Intent(MainActivity.this, PlantCard.class);
				intent.putExtra("plant", (Parcelable) plant);
				startActivity(intent);
			}

		});
	}

	// methode de debug
	public void reset() {
		planteListe = new PlantArray();
		plantesAffichees = new PlantArray();

		planteListe.save("neverkillmyplant" + File.separator + "liste.data");
		plantesAffichees.save("neverkillmyplant" + File.separator
				+ "affichage.data");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
