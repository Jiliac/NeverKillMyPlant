package com.example.neverkillmyplant.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.neverkillmyplant.R;

public class DiagSec extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagfer);

		TextView text = (TextView) findViewById(R.id.textView1);
		String str = "Symptomes : Le bout des feuilles devient marron et se desseche+\nDiagnostique : On rencontre souvent ce phenomene sur les dracaenas ou les palmiers installes dans des pieces trop chauffees. \nConseilsÂ : vos plantes ont besoin d'une bonne humidite ambiante et souffrent d'un air trop sec, il suffira de bien brumiser le feuillage pour qu'il redevienne beau.";
		text.setText(str);
	}

}