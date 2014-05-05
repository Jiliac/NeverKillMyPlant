package com.example.neverkillmyplant;

import javaClass.Plant;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class CheckHealth extends IntentService {
	private final static String TAG = "CheckHealth";

	public CheckHealth() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent startIntent) {
		// r�cup�re la plante dont on cr�e le service
		Plant plante = (Plant) startIntent.getParcelableExtra("plant");

		//String sante = Serveur.getSante(plante);
				
		// CREATION ET ENVOI DE LA NOTIF

		//if(sante == "0")
		//notificationCreator(plante);
		
	}

	/* variable pour les notifications */
	public static int ID_NOTIFICATION = 0;

	private void notificationCreator(Plant plante) {
		String name = plante.getName();

        Intent notificationIntent = new Intent(CheckHealth.this,
				CheckHealth.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(
				CheckHealth.this, 0, notificationIntent, 0);

		Notification notification = new Notification(R.drawable.ficus,
				"Alerte", System.currentTimeMillis());
		notification.setLatestEventInfo(CheckHealth.this, name + " est malade!",
				"Vous devriez l'arroser ou v�rifier qu'elle n'est pas malade.",
				contentIntent);

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(ID_NOTIFICATION, notification);
	}
}
