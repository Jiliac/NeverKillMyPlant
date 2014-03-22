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
		text.setText("Symptôme : Le bout des vieilles feuilles devient jaune, les jeunes feuille sont vertes. \nDiagnostic : Un excès d'arrosage est une cause fréquente de jaunissement, notamment pour les plantes en pot. Un substrat trop détrempé induit le pourrissement des racines, ce qui est irrémédiable : la plante ne peut plus se nourrir correctement, ses feuilles jaunissent (commence par des vieilles feuilles). \nConseils : N’arrosez pas trop vos protégées surtout si la température n’est pas élévée. En effet, plus il fait frais moins il faut les abreuver, laissez la terre sécher un peu en surface et rappelez-vous que les plantes meurent plus souvent d’un excés que d’un manque d’arrosage.");
		}
}
