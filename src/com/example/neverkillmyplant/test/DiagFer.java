package com.example.neverkillmyplant.test;

import com.example.neverkillmyplant.R;
import com.example.neverkillmyplant.R.id;
import com.example.neverkillmyplant.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DiagFer extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagfer);

		TextView text = (TextView) findViewById(R.id.textView1);
		text.setText("Sympt�me : Les feuilles deviennent jaun�tres, lesnervures restent vertes.\nDiagnostic : chlorose ferrique , une maladie � cause d�un manque de fer\nConseil :  un produit cibl� anti-chlorose pour r��quilibrer le r�gime de la plante. L��limination de cette carence peut demander plusieurs saisons. En pr�vention, apportez du compost et des engrais organiques aux sols calcaires, pauvres, secs ou humides.");
	}
}
