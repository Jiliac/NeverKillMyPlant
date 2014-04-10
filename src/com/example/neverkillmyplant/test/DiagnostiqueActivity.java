package com.example.neverkillmyplant.test;

import java.io.File;
import java.util.ArrayList;

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

import diagnostique.reconnaissance.Collection;
import diagnostique.reconnaissance.Ensemble;
import diagnostique.reconnaissance.Point;
import diagnostique.segmentation.Colour;
import diagnostique.segmentation.Global;
import diagnostique.segmentation.Kmoyenne;
import diagnostique.segmentation.Pixel;
import diagnostique.segmentation.intervalle.DiagHydra;
import diagnostique.xiao.Xiao;

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
				Xiao dh = new Xiao(str);
				Intent toto = new Intent(DiagnostiqueActivity.this, dh
						.diagnostique());
				startActivity(toto);
			}
		});

		ImageButton testFer = (ImageButton) findViewById(R.id.imageButton2);
		testFer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Xiao dh = new Xiao(Environment.getExternalStorageDirectory()
						.getAbsolutePath()
						+ File.separator
						+ "neverkillmyplant" + File.separator + "testfer.jpg");
				Intent toto = new Intent(DiagnostiqueActivity.this, dh
						.diagnostique());
				startActivity(toto);
			}
		});

		Button testCentroide = (Button) findViewById(R.id.button1);
		testCentroide.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Bitmap img = analyse();
				Ensemble ens = new Ensemble(img, Point.convertisseur(pixels));
				ArrayList<Collection> compoCo = ens.CompoCo();

				// affichage des compoCo
				Bitmap result = afficherCompoCo(img, compoCo);
				
				// on affiche le résultat du test
				Intent display = new Intent(DiagnostiqueActivity.this,
						DisplayBitmap.class);
				display.putExtra("image", img);
				display.putExtra("resultat", result);
				
				startActivity(display);
			}

		});
	}

	private Pixel[][] pixels;

	private Bitmap analyse() {
		String str = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "neverkillmyplant"
				+ File.separator + "testCentroide.jpg";
		Bitmap img = BitmapFactory.decodeFile(str);
		pixels = Kmoyenne.analyse(img);

		// configure l'image de test
		img = img.copy(Bitmap.Config.RGB_565, true);
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Colour colour = Global.baseDApprentissage.get(
						pixels[x][y].getGroupe()).getColor();

				img.setPixel(
						x,
						y,
						Color.rgb(colour.getRed(), colour.getGreen(),
								colour.getBlue()));
			}
		}
		return img;
	}

	private Bitmap afficherCompoCo(Bitmap img, ArrayList<Collection> compoCo) {
		Bitmap retour = img.copy(Bitmap.Config.RGB_565, true);

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				retour.setPixel(x, y, Color.BLACK);
			}
		}
		for (Collection collec : compoCo) {
			for (Point point : collec) {
				retour.setPixel(point.getPosX(), point.getPosY(), Color.WHITE);
			}
		}

		return retour;
	}
}
