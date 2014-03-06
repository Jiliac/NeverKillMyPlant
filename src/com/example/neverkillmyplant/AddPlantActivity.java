package com.example.neverkillmyplant;

import android.app.Activity;
import android.content.Intent;
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

	public void onClick(View v) {

		EditText et = (EditText) findViewById(R.id.editText1);
		String name = et.getText().toString();

		Intent retour = new Intent(AddPlantActivity.this, MainActivity.class);

		// ajout d'un extra
		String textSticker = sticker.getSelectedItem().toString();
		String textespece = espece.getSelectedItem().toString();
		Plant plant = new Plant(name, textespece, textSticker);
		retour.putExtra("plant", plant);

		startActivity(retour);
	}
}
