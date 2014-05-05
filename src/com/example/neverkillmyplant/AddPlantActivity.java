package com.example.neverkillmyplant;

import java.io.File;
import java.util.Calendar;

import javaClass.Plant;
import javaClass.PlantArray;
import javaClass.Serveur;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.os.Parcelable;

public class AddPlantActivity extends Activity implements View.OnClickListener {
    private Spinner espece;
    private Spinner sticker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.add_plant);
        else
            setContentView(R.layout.add_plant_hor);

        Typeface FONT = Typeface.createFromAsset(getAssets(),
                "fonts/MoonFlower.ttf");

        Button addPlantButton = (Button) findViewById(R.id.button1);
        addPlantButton.setOnClickListener(this);
        addPlantButton.setTypeface(FONT);

        espece = (Spinner) findViewById(R.id.spinner1);
        sticker = (Spinner) findViewById(R.id.spinner2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // IL FAUT PREVOIR LE CAS OU L'UTILISATEUR UTILISE LE BOUTON BACK
    // p-e faire que onPause appelle onDestroy et faire add...

    public void onClick(View v) {
        finish();
    }

    public void onPause() {
        super.onPause();
        addPlant();
    }

    public void addPlant() {
        EditText et = (EditText) findViewById(R.id.editText1);
        String name = et.getText().toString();

        // creation de la plante
        String textSticker = sticker.getSelectedItem().toString();
        String textespece = espece.getSelectedItem().toString();
        Plant plant = new Plant(name, textespece, textSticker);

        // sauvegarde de cette plante
        if (name.length() > 0) {
            PlantArray planteListe = new PlantArray("neverkillmyplant"
                    + File.separator + "liste.data");
            plant.setId(planteListe.size());
            planteListe.add(plant);
            planteListe
                    .save("neverkillmyplant" + File.separator + "liste.data");
        }

        Serveur.addPlant(plant);

        // on cr�e l'alarme concernant cette plante
        //setAlarm(plant);

        if(plant.getSante().charAt(0) == 'n')
            notificationCreator(plant);

        finish();
    }

    private void setAlarm(Plant plante) {
        // get today date
        Calendar c = Calendar.getInstance();
		/*
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.HOUR, 12);
		int today = c.get(Calendar.DAY_OF_WEEK);
		c.set(Calendar.DAY_OF_WEEK, today + 1);
		*/

        // set alarm
        Intent i = new Intent(getApplicationContext(), CheckHealth.class);
        i.putExtra("plante", (Parcelable) plante);
        PendingIntent pi = PendingIntent.getService(getApplicationContext(), 0,
                i, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC, c.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }

    public static int ID_NOTIFICATION = 0;

    private void notificationCreator(Plant plante) {
        String name = plante.getName();

        Intent notificationIntent = new Intent(AddPlantActivity.this,
                AddPlantActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(
                AddPlantActivity.this, 0, notificationIntent, 0);

        Notification notification = new Notification(R.drawable.ficus,
                "Alerte", System.currentTimeMillis());
        notification.setLatestEventInfo(AddPlantActivity.this, name + " est malade!",
                "Vous devriez l'arroser ou v�rifier qu'elle n'est pas malade.",
                contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(ID_NOTIFICATION, notification);
    }
}
