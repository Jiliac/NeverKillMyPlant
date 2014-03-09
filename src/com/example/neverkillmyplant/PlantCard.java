package com.example.neverkillmyplant;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import diagnostique.segmentation.intervalle.DiagHydra;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class PlantCard extends Activity {

	// IL FAUDRAIT FAIRE UN BOUTON REMOVE

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plant_card_activity);

		// definition du bouton retour
		Button retour = (Button) findViewById(R.id.button1);
		retour.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		// ajout d'un bouton pour le module diagnostique
		ajoutBoutonDiagnostique();

		// on r�cup�re la plante que l'on doit afficher
		Intent startIntent = getIntent();
		Plant plant = startIntent.getParcelableExtra("plant");

		// et on definit la TextView
		TextView plantName = (TextView) findViewById(R.id.textView1);
		plantName.setText(plant.getName());
		TextView plantEspece = (TextView) findViewById(R.id.textView2);
		plantEspece.setText(plant.getEspece());
		TextView santePlant = (TextView) findViewById(R.id.textView3);
		getSantePlant(santePlant, "http://google.com");
	}

	/******************** recupere la sante d'une plante sur le serveur *****************/
	public void getSantePlant(final TextView santePlant, String url) {
		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				santePlant.setText(result);
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
						return "No string.";
					}
				} catch (Exception e) {
					return "Network problem";
				}
			}
		}

		new TheTask().execute(url);
	}

	/********* on recupere une photo pour le module diagnostique ************/

	int TAKE_PHOTO_CODE = 0;

	private void ajoutBoutonDiagnostique() {
		// on cr�er le dossier de reception
		final String dir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
				+ "/picFolder/";
		File newdir = new File(dir);
		newdir.mkdirs();

		Button photo = (Button) findViewById(R.id.button2);
		photo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String file = dir + "photo.jpg";
				File newfile = new File(file);
				try {
					newfile.createNewFile();
				} catch (IOException e) {
				}
				Uri outputFileUri = Uri.fromFile(newfile);
				Intent cameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);

				DiagHydra dh = new DiagHydra(file);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
			Log.d("CameraDemo", "Pic saved");

		}
	}

	/************ menu ***************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
