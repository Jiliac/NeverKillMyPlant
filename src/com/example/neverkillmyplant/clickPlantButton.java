package com.example.neverkillmyplant;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class clickPlantButton implements View.OnClickListener {
	private Plant plant;
	private Activity departureActivity;

	public clickPlantButton(Plant plant, Activity da) {
		this.plant = plant;
		this.departureActivity = da;
	}

	public void onClick(View arg0) {
		Intent intent = new Intent(departureActivity, PlantCard.class);
		intent.putExtra("plant", plant);
		departureActivity.startActivity(intent);
	}

}
