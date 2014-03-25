package com.example.neverkillmyplant;

import java.io.File;

import com.example.neverkillmyplant.R;
import com.example.neverkillmyplant.R.id;
import com.example.neverkillmyplant.R.layout;

import diagnostique.segmentation.intervalle.DiagHydra;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;

public class DiagnostiqueActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagnostique);

		ImageButton testHydra = (ImageButton) findViewById(R.id.imageButton1);
		testHydra.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String str = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator + "testhydra.jpg";
				DiagHydra dh = new DiagHydra(str);
				Intent toto = new Intent(DiagnostiqueActivity.this, dh
						.diagnostique());
				startActivity(toto);
			}
		});

		ImageButton testFer = (ImageButton) findViewById(R.id.imageButton2);
		testFer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DiagHydra dh = new DiagHydra(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ File.separator + "testfer.jpg");
				Intent toto = new Intent(DiagnostiqueActivity.this, dh
						.diagnostique());
				startActivity(toto);
			}
		});
	}
}
