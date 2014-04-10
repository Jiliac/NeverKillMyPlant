package com.example.neverkillmyplant.test;

import com.example.neverkillmyplant.R;
import com.example.neverkillmyplant.R.id;
import com.example.neverkillmyplant.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DefaultPage extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagfer);

		TextView text = (TextView) findViewById(R.id.textView1);
		text.setText("Aucune maladie n'a pu etre diagnostiquee.");
	}
}
