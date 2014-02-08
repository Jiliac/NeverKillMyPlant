package com.example.neverkillmyplant;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	static ArrayList<Plant> plantList = new ArrayList<Plant>();
	/*variable pour le test des notifications*/public int ID_NOTIFICATION = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Creation de la page de base
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * // on récupère la sauvegarde if
		 * (getIntent().getParcelableArrayListExtra("plantList") != null)
		 * plantList = getIntent().getParcelableArrayListExtra("plantList");
		 */

		Button addPlanButton = (Button) findViewById(R.id.button1);
		addPlanButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent add = new Intent(MainActivity.this,
						AddPlantActivity.class);
				startActivity(add);
			}
		});
	}

	protected void onResume() {
		super.onResume();

		// on ajoute la nouvelle plant à la liste
		Plant plant = null;
		Intent intent = getIntent();
		if (intent.getParcelableExtra("plant") != null)
			plant = intent.getParcelableExtra("plant");
		if (plant != null) {
			plantList.add(plant);
			
			/* TEST */
			Notification notification = new Notification(R.drawable.ficus, "Titre", System.currentTimeMillis());
			Intent notificationIntent = new Intent(MainActivity.this,
					MainActivity.class);
			notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent contentIntent = PendingIntent.getActivity(
					MainActivity.this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(MainActivity.this, "Titre",
					"Texte", contentIntent);
		
	        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);             
	        manager.notify(ID_NOTIFICATION, notification);
			/* FIN TEST */
		}
		ajoutBouton();
	}

	/************************** methode auxiliaires ******************/

	public void ajoutBouton() {
		// on crée dynamiquement un bouton par l'intermédiaire d'un layout
		LinearLayout layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layoutTest);
		LinearLayout A = new LinearLayout(this);
		A.setOrientation(LinearLayout.VERTICAL);
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

	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList("plantList", plantList);
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
