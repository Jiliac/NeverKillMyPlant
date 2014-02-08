package com.example.neverkillmyplant;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.net.Uri;

public class PlantCard extends Activity {

	int PHOTO_RESULT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plant_card_activity);

		// on s'occupe de d�finir le premier bouton
		Button retour = (Button) findViewById(R.id.button1);
		retour.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(PlantCard.this, MainActivity.class));
			}
		});

		// puis le deuxieme
		Button photo = (Button) findViewById(R.id.button2);
		photo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				File mFichier = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
				Uri fileUri = Uri.fromFile(mFichier);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				startActivityForResult(intent, PHOTO_RESULT);
			}
		});

		// on r�cup�re la plante que l'on doit afficher
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
