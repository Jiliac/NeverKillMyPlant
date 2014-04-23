package com.example.neverkillmyplant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.neverkillmyplant.test.DiagnostiqueActivity;

import diagnostique.xiao.Xiao;

import javaClass.ExpandableListAdapter;
import javaClass.Plant;
import javaClass.PlantArray;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;


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
		
		// ajout d'un bouton pour le module diagnostique
		ajoutBoutonDiagnostique();
		
		// bouton provisoire pour test
		boutonTestCentroide();
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
	
	/************** bouton test centroide ******************/
	
	private void boutonTestCentroide(){
		Button testCentro = (Button) findViewById(R.id.button3);
		testCentro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent toto = new Intent(MainActivity.this, DiagnostiqueActivity.class);
				startActivity(toto);	
			}
		});
	}
	
	
	/************** methode pour les listView ****************/

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
	
	/************************************** bouton diagnostique **********************/
	
	static int TAKE_PICTURE = 1;
	String file;
	Bitmap img;

	
	private void ajoutBoutonDiagnostique() {
		// on créer le dossier de reception
		final String dir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
				+ "/picFolder/";
		File newdir = new File(dir);
		newdir.mkdirs();

		Button photo = (Button) findViewById(R.id.button2);
		photo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, TAKE_PICTURE);
			}
		});
		Typeface FONT = Typeface.createFromAsset(getAssets(),
				"fonts/MoonFlower.ttf");
		photo.setTypeface(FONT);
	}

	private void analyse(Bitmap img) {
		Xiao dh = new Xiao(img);
		if (dh.diagnostique() != null) {
			Intent diag = new Intent(this, dh.diagnostique());
			startActivity(diag);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK
				&& data != null) {
			Bundle extras = data.getExtras();

			img = (Bitmap) extras.get("data");
			this.analyse(img);
		}
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
