package com.example.neverkillmyplant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javaClass.ExpandableListAdapter;
import javaClass.Plant;
import javaClass.PlantArray;
import javaClass.clickPlantButton;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private PlantArray planteListe = new PlantArray();

	protected void onCreate(Bundle savedInstanceState) {
		// Creation de la page de base
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// cree le dossier de stockage si necessaire
		createDirectory();

		// la police
		Typeface FONT = Typeface.createFromAsset(getAssets(),
				"fonts/MoonFlower.ttf");

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
	}

	public void onResume() {
		super.onResume();

		planteListe.load("neverkillmyplant" + File.separator + "liste.data");
		handleListView((ExpandableListView) findViewById(R.id.expandableListView1));
	}

	public void handleListView(ExpandableListView view) {
		ArrayList<String> titres = new ArrayList<String>();
		HashMap<String, List<String[]>> fils = new HashMap<String, List<String[]>>();

		for (Plant plant : planteListe) {
			titres.add(plant.getName());

			List<String[]> plantAttributs = new ArrayList<String[]>();
			String[] str = new String[3];
			str[0] = "ADRESSE";
			str[1] = "Espece : " + plant.getEspece();
			str[2] = "Sante : " + "A RECUPER";
			plantAttributs.add(str);

			fils.put(plant.getName(), plantAttributs);
		}

		ExpandableListAdapter adapter = new ExpandableListAdapter(this, titres,
				fils);
		view.setAdapter(adapter);
	}

	/********************************************************************************/

	// methode de debug
	public void reset() {
		planteListe = new PlantArray();

		planteListe.save("neverkillmyplant" + File.separator + "liste.data");
	}

	// methode auxiliaire
	private void createDirectory() {
		String sdCardDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		File mainDirectory = new File(sdCardDir + File.separator
				+ "neverkillmyplant");
		if (!mainDirectory.isDirectory()) {
			File appDirectory = new File(sdCardDir, "neverkillmyplant");
			appDirectory.mkdir();
		}
	}
}
