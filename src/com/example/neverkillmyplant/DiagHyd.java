package com.example.neverkillmyplant;

import com.example.neverkillmyplant.R;
import com.example.neverkillmyplant.R.id;
import com.example.neverkillmyplant.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DiagHyd extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagfer);
		
		TextView text = (TextView) findViewById(R.id.textView1);
		text.setText("Sympt�me : Le bout des vieilles feuilles devient jaune, les jeunes feuille sont vertes. \nDiagnostic : Un exc�s d'arrosage est une cause fr�quente de jaunissement, notamment pour les plantes en pot. Un substrat trop d�tremp� induit le pourrissement des racines, ce qui est irr�m�diable : la plante ne peut plus se nourrir correctement, ses feuilles jaunissent (commence par des vieilles feuilles). \nConseils : N�arrosez pas trop vos prot�g�es surtout si la temp�rature n�est pas �l�v�e. En effet, plus il fait frais moins il faut les abreuver, laissez la terre s�cher un peu en surface et rappelez-vous que les plantes meurent plus souvent d�un exc�s que d�un manque d�arrosage.");
		}
}
