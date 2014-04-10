package com.example.neverkillmyplant.test;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.neverkillmyplant.R;

import diagnostique.segmentation.Colour;
import diagnostique.segmentation.Global;
import diagnostique.segmentation.Kmoyenne;
import diagnostique.segmentation.Pixel;
import diagnostique.segmentation.intervalle.DiagHydra;

public class DiagnostiqueActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagnostique);

		ImageButton testHydra = (ImageButton) findViewById(R.id.imageButton1);
		testHydra.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String str = Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ File.separator
						+ "neverkillmyplant" + File.separator + "testhydra.jpg";
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
						+ File.separator
						+ "neverkillmyplant"
						+ File.separator
						+ "testfer.jpg");
				Intent toto = new Intent(DiagnostiqueActivity.this, dh
						.diagnostique());
				startActivity(toto);
			}
		});

		Button testCentroide = (Button) findViewById(R.id.button1);
		testCentroide.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String str = Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ File.separator
						+ "neverkillmyplant"
						+ File.separator
						+ "testCentroide.jpg";
				Bitmap img = BitmapFactory.decodeFile(str);
				Pixel[][] pixels = Kmoyenne.analyse(img);

				// configure l'image de test
				img = img.copy(Bitmap.Config.RGB_565, true);
				for (int x = 0; x < img.getWidth(); x++) {
					for (int y = 0; y < img.getHeight(); y++) {
						Colour colour = Global.baseDApprentissage.get(
								pixels[x][y].getGroupe()).getColor();

						img.setPixel(x, y, Color.rgb(colour.getRed(),
								colour.getGreen(), colour.getBlue()));
					}
				}

				// on affiche le résultat du test
				Intent display = new Intent(DiagnostiqueActivity.this,
						DisplayBitmap.class);
				display.putExtra("image", img);
				startActivity(display);
			}

		});
	}
}
