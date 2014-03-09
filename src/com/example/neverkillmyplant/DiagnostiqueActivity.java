package com.example.neverkillmyplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DiagnostiqueActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagnostique);
		
		ImageButton testHydra = (ImageButton) findViewById(R.id.imageButton1);
		testHydra.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent toto = new Intent(DiagnostiqueActivity.this,
						DiagHyd.class);
				startActivity(toto);
			}
		});
		
		ImageButton testFer = (ImageButton) findViewById(R.id.imageButton2);
		testFer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent toto = new Intent(DiagnostiqueActivity.this,
						DiagFer.class);
				startActivity(toto);
			}
		});
	}
}