package com.example.neverkillmyplant.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.neverkillmyplant.R;

public class DisplayBitmap extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaybitmap);

		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		Intent display = getIntent();
		Bitmap img = display.getParcelableExtra("image");
		imageView.setImageBitmap(img);
	}
}
