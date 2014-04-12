package com.example.neverkillmyplant;

import java.io.File;
import javaClass.Plant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.neverkillmyplant.test.DiagnostiqueActivity;

import diagnostique.xiao.Xiao;

public class PlantCard extends Activity implements View.OnClickListener {

	// IL FAUDRAIT FAIRE UN BOUTON REMOVE

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plant_card_activity);
		Typeface FONT = Typeface.createFromAsset(getAssets(),
				"fonts/MoonFlower.ttf");

		// definition du bouton retour
		Button retour = (Button) findViewById(R.id.button1);
		retour.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		// test d'integration du module diagnostique
		Button testDiag = (Button) findViewById(R.id.button3);
		testDiag.setOnClickListener(this);

		// ajout d'un bouton pour le module diagnostique
		ajoutBoutonDiagnostique();

		// on récupère la plante que l'on doit afficher
		Intent startIntent = getIntent();
		Plant plant = startIntent.getParcelableExtra("plant");

		// et on definit la TextView
		TextView plantName = (TextView) findViewById(R.id.textView1);
		plantName.setText(plant.getName());
		plantName.setTypeface(FONT);
		TextView plantEspece = (TextView) findViewById(R.id.textView2);
		plantEspece.setText(plant.getEspece());
		plantEspece.setTypeface(FONT);
		TextView santePlant = (TextView) findViewById(R.id.textView3);
		santePlant.setTypeface(FONT);
		// getSantePlant(santePlant,
		// "http://89.156.29.238:8080/rpztix/plants/1/sante?method=plain");
	}

	/******************** recupere la sante d'une plante sur le serveur *****************/
	public void getSantePlant(final TextView santePlant, String url) {
		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				int res = Integer.parseInt(result);
				String str;
				if (res == 1)
					str = "bonne";
				else
					str = "mauvaise";
				santePlant.setText("La sante de cette plante est: " + str);
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... params) {
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost method = new HttpPost(params[0]);
					HttpResponse response = httpclient.execute(method);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity);
					} else {
						return "No string";
					}
				} catch (Exception e) {
					return "Network problem";
				}
			}
		}

		new TheTask().execute(url);
	}

	/********* on recupere une photo pour le module diagnostique ************/

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
			Intent diag = new Intent(PlantCard.this, dh.diagnostique());
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

	/************ menu ***************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent toto = new Intent(PlantCard.this, DiagnostiqueActivity.class);
		startActivity(toto);
	}
}
