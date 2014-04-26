package com.example.neverkillmyplant;

import javaClass.Plant;
import javaClass.Serveur;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class CheckHealth extends IntentService {
	private final static String TAG = "CheckHealth";
	Plant plant;

	public CheckHealth() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent startIntent) {
		// récupère la plante dont on crée le service
		plant = (Plant) startIntent.getParcelableExtra("plant");

		String adresse = "https://www.google.fr/"; // à faire à partir de la plante...
		String retour = Serveur.getSante(adresse);

		// CREATION ET ENVOI DE LA NOTIF
		notificationCreator();
	}

	/* variable pour le test des notifications */
	public static int ID_NOTIFICATION = 0;

	private void notificationCreator() {
		Intent notificationIntent = new Intent(CheckHealth.this,
				CheckHealth.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(
				CheckHealth.this, 0, notificationIntent, 0);

		Notification notification = new Notification(R.drawable.ficus, "Titre",
				System.currentTimeMillis());
		notification.setLatestEventInfo(CheckHealth.this, "Titre", "Texte",
				contentIntent);

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(ID_NOTIFICATION, notification);
	}
}
